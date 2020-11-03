package lapr.project.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import lapr.project.model.Fatura;
import lapr.project.model.Pesquisa;
import lapr.project.model.Utilizador;
import lapr.project.utils.Calculos;

public class FaturaController {
    
    Pesquisa p = new Pesquisa();
    
    //retorna total 
    public double getDebitoAtual(String username, List<String> detalhe) {
        
        Utilizador utilizador = p.getUtilizadorByUserName(username);
        if(utilizador == null)
            return 0;
        
        //Debito atual não faturado
        List<String> detalheDebitoAtual = p.getDetalheAtual(username);
        double total = 0;
        for(String s : detalheDebitoAtual) {
            String[] p = s.split(";");
            total += Double.valueOf(p[p.length-1]);
        }
        
//******************descomentar se for debito global***************************//
//        double totalFaturado = 0;
//        List<Fatura> faturasUtilizador = p.getFaturasUtilizador(username);
//        for(Fatura f : faturasUtilizador) {
//            totalFaturado += f.getValorCobrado() - f.getValorPago();
//        }
//        total+=totalFaturado;
//*****************************************************************************//
        
        detalhe.addAll(detalheDebitoAtual);
        return Calculos.round2Decimals(total);
    }
    
    
    public double getUserCurrentPoints(String username, List<String> detalhe) {
        
        Utilizador utilizador = p.getUtilizadorByUserName(username);
        if(utilizador == null)
            return 0;
        
        List<String> detalhePontos = p.getDetalhePontos(username);
        double total = 0;
        for(String s : detalhePontos) {
            String[] p = s.split(";");
            total += Double.valueOf(p[p.length-1]);
        }
        
//**************comentar se for não for total de pontos************************//
        List<Fatura> faturasUtilizador = p.getFaturasUtilizador(username);
        
        for(Fatura f : faturasUtilizador) {
            total -= f.getPontosDescontados();
        }
//*****************************************************************************//        
        
        detalhe.addAll(detalhePontos);
        return total;
    }
    
    
    public boolean emiteFatura(String username, Integer mes) {
        //data agora
        Date date = new Date(System.currentTimeMillis());
        LocalDate today = date.toLocalDate();
        int mesAtual = today.getMonthValue();
        if(mesAtual == mes)
            return false;
        Utilizador utilizador = p.getUtilizadorByUserName(username);
        if(utilizador == null)
            return false;
        Fatura fatura = new Fatura(username, mes);
        return salvarFatura(fatura);
    }
    
    //retorna total 
    public double getFatura(String username, Integer mes, List<String> efatura, List<String> detalhe) {
        
        Utilizador utilizador = p.getUtilizadorByUserName(username);
        if(utilizador == null)
            return 0;
        
        Fatura fatura = p.getFatura(username, mes);
        if(fatura == null) {
            efatura.add(username);
            efatura.add("Previous points:0");
            efatura.add("Earned points:0");
            efatura.add("Discounted points:0");
            efatura.add("Atual points:0");
            efatura.add("Charged Value:0");
            return 0;
        }
        
        efatura.add(fatura.getUsername());
        efatura.add("Previous points:"+fatura.getPontosAnteriores());
        efatura.add("Earned points:"+fatura.getPontosGanhos());
        efatura.add("Discounted points:"+fatura.getPontosDescontados());
        efatura.add("Atual points:"+fatura.getPontosAtuais());
        efatura.add("Charged Value:"+Calculos.round2Decimals(fatura.getValorCobrado()));
       
        //--- get detalhe from bd
        detalhe.addAll(p.getDetalheFatura(fatura));

        return Calculos.round2Decimals(fatura.getValorCobrado());        
    }
    
    public boolean salvarFatura(Fatura f) {
        return f.save();
    }
    
    
}
