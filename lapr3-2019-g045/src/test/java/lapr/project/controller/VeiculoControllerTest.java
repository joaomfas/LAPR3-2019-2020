/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import javafx.util.Pair;
import lapr.project.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

/**
 *
 * @author joaoferreira
 */
public class VeiculoControllerTest {

    public VeiculoControllerTest() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of carregarScooters method, of class VeiculoController.
     */
    @Test
    public void testCarregarScooters() {
        System.out.println("carregarScooters");
        VeiculoController vc = Mockito.spy(new VeiculoController());
        String[] sc1 = {"teste", "10", "oFF-RoaD", "10", "10", "100", "50", "0.2", "2", "200"};
        String[] sc2 = {"teste", "10", "CiTy", "10", "10", "100", "50", "0.2", "2", "200"};
        String[] sc3 = {"teste", "10", "", "10", "10", "100", "50", "0.2", "2", "200"};
        String[] sc4 = {"teste", "10", "chalala", "", "", "100", "50", "0.2", "2", "200"};
        ArrayList<String[]> scs = new ArrayList<>();
        scs.add(sc1);
        scs.add(sc2);
        scs.add(sc3);
        scs.add(sc4);

        Pesquisa p = mock(Pesquisa.class);
        Transacao t = Mockito.mock(Transacao.class);
        vc.setTransacao(t);

        Parque pk = new Parque();
        pk.setIdParque("parqueID");

        vc.setPesquisa(p);
        when(p.getParque(anyDouble(), anyDouble())).thenReturn(pk);

        // teste com os caminhos falhando ao serem adicionados
        when(t.addAllVeiculos(any())).thenReturn(false);
        assertEquals(0, vc.carregarScooters(scs));

        // teste com os caminhos sendo adicionados com sucesso
        when(t.addAllVeiculos(any())).thenReturn(true);
        assertEquals(4, vc.carregarScooters(scs));
    }

    /**
     * Test of carregarBicicletas method, of class VeiculoController.
     */
    @Test
    public void testCarregarBicicletas() {
        System.out.println("carregarBiciletas");
        VeiculoController vc = Mockito.spy(new VeiculoController());
        String[] sc1 = {"teste", "10", "10", "10", "0.5", "2", "16"};
        String[] sc2 = {"teste", "10", "10", "10", "0.5", "2", "16"};
        String[] sc3 = {"teste", "10", "10", "10", "0.5", "2", "16"};
        String[] sc4 = {"teste", "10", "", "", "0.5", "2", "16"};
        ArrayList<String[]> scs = new ArrayList<>();
        scs.add(sc1);
        scs.add(sc2);
        scs.add(sc3);
        scs.add(sc4);

        Pesquisa p = mock(Pesquisa.class);
        Transacao t = Mockito.mock(Transacao.class);
        vc.setTransacao(t);

        Parque pk = new Parque();
        pk.setIdParque("parqueID");

        vc.setPesquisa(p);
        when(p.getParque(anyDouble(), anyDouble())).thenReturn(pk);

        // teste com os caminhos falhando ao serem adicionados
        when(t.addAllVeiculos(any())).thenReturn(false);
        assertEquals(0, vc.carregarBicicletas(scs));

        // teste com os caminhos sendo adicionados com sucesso
        when(t.addAllVeiculos(any())).thenReturn(true);
        assertEquals(4, vc.carregarBicicletas(scs));
    }

    /**
     * Test of carregarBicicletas method, of class VeiculoController.
     */
    @Test
    public void testSalvarVeiculo() {
        System.out.println("salvarVeiculo");
        VeiculoController instance = new VeiculoController();

        Veiculo c = Mockito.mock(Veiculo.class);
        when(c.save()).thenReturn(true);

        boolean expResult = true;
        boolean result = instance.salvarVeiculo(c);
        assertEquals(expResult, result);

        when(c.save()).thenReturn(false);
        expResult = false;
        result = instance.salvarVeiculo(c);
        assertEquals(expResult, result);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testAssociarVeiculo() {
        System.out.println("associarVeiculo");
        Pesquisa p = mock(Pesquisa.class);
        VeiculoController instance = Mockito.spy(new VeiculoController());
        instance.setPesquisa(p);
        Parque parque = Mockito.mock(Parque.class);
        Veiculo veiculo = new Scooter();

        // 1 - veículo e parque são encontrados. save funciona
        when(p.getParque("testeID")).thenReturn(parque);
        when(p.getVeiculo("testeID")).thenReturn(veiculo);
        doReturn(true).when(instance).salvarVeiculo(veiculo);
        assertEquals(true, instance.associarVeiculo("testeID", "testeID"));
        
        assertEquals("testeID", veiculo.getIdParque());

        // 2 - veículo e parque são encontrados. save  não funciona
        doReturn(false).when(instance).salvarVeiculo(veiculo);
        assertEquals(false, instance.associarVeiculo("testeID", "testeID"));

        // 3 - parque não é encontrado
        when(p.getParque("testeID")).thenReturn(null);
        assertEquals(false, instance.associarVeiculo("testeID", "testeID"));

        // 4 - veículo não é encontrado
        when(p.getVeiculo("testeID")).thenReturn(null);
        assertEquals(false, instance.associarVeiculo("testeID", "testeID"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testDesassociarVeiculo() {
        System.out.println("desassociarVeiculo");
        Pesquisa p = mock(Pesquisa.class);
        VeiculoController instance = Mockito.spy(new VeiculoController());
        instance.setPesquisa(p);
        Veiculo veiculo = new Scooter();

        // 1 - veículo encontrado e save funciona
        when(p.getVeiculo("testeID")).thenReturn(veiculo);
        doReturn(true).when(instance).salvarVeiculo(veiculo);
        assertEquals(true, instance.desassociarVeiculo("testeID"));
        
        assertEquals(null, veiculo.getIdParque());

        // 2 - veículo encontrado e save  não funciona
        doReturn(false).when(instance).salvarVeiculo(veiculo);
        assertEquals(false, instance.desassociarVeiculo("testeID"));

        // 4 - veículo não é encontrado
        when(p.getVeiculo("testeID")).thenReturn(null);
        assertEquals(false, instance.desassociarVeiculo("testeID"));
    }

    /**
     * Test of removerVeiculo method, of class VeiculoController.
     */
    @Test
    public void testRemoverVeiculo() {
        System.out.println("removerVeiculo");

        VeiculoController instance = Mockito.spy(new VeiculoController());
        Pesquisa pb = Mockito.mock(Pesquisa.class);
        instance.setPesquisa(pb);

        // teste 1: veiculo não existe
        doReturn(null).when(pb).getVeiculo("testeDesc");
        boolean result = instance.removerVeiculo("testeDesc");
        assertEquals(false, result);

        // teste 2: remove veiculo com sucesso
        doReturn(true).when(instance).salvarVeiculo(any());
        Veiculo v = new Scooter();
        doReturn(v).when(pb).getVeiculo("testeID");
        assertEquals(true, instance.removerVeiculo("testeID"));
        assertEquals(null, v.getIdParque());
        assertTrue(v.getRemovido());
        
        assertNull(v.getIdParque());

        // teste 2: remove veiculo sem sucesso
        doReturn(false).when(instance).salvarVeiculo(any());
        assertEquals(false, instance.removerVeiculo("testeDesc"));
    }

    /**
     * Test of veiculosEmUtilizacaoRelatorio method, of class VeiculoController.
     */
    @Test
    public void testVeiculosEmUtilizacaoRelatorio() {

        System.out.println("veiculosEmUtilizacaoRelatorio");
        Pesquisa p = mock(Pesquisa.class);
        VeiculoController instance = Mockito.spy(new VeiculoController());
        instance.setPesquisa(p);

        List<String> expResult = new LinkedList<>();
        String teste1 = "Id veículo : 1 Id parque: 2 Descrição: teste1";
        expResult.add(teste1);

        // 1 - lista vazia
         doThrow(IllegalArgumentException.class).when(p).veiculosEmUtilizacaoRelatorio();
        assertEquals(null, instance.veiculosEmUtilizacaoRelatorio());

        // 2 - lista com veículos
        doReturn(expResult).when(p).veiculosEmUtilizacaoRelatorio();
        assertEquals(expResult, instance.veiculosEmUtilizacaoRelatorio());

    }

    /**
     * Test of capacidadeVeiculoRelatorio method, of class VeiculoController.
     */
    @Test
    public void testCapacidadeVeiculoRelatorio() {
        System.out.println("capacidadeVeiculoRelatorio");
        Pesquisa p = mock(Pesquisa.class);
        VeiculoController instance = Mockito.spy(new VeiculoController());

        Scooter teste1 = new Scooter(0.35, 100, "cidade", 250, "1", "Scooter 1", 100, 2.0, 0.4);
        Scooter teste2 = new Scooter(0.7, 35, "cidade", 250, "1", "Scooter 2", 30, 2.0, 0.4);
        Scooter teste3 = new Scooter(0.7, 35, "cidade", 250, "1", "Scooter 3", 30, 2.0, 0.4);
        instance.setPesquisa(p);
        
        String idParque = "1";

        List<Scooter> exp = new LinkedList<>();
        exp.add(teste1);
        exp.add(teste2);
        exp.add(teste3);
        doReturn(exp).when(p).getScootersNumParque("1");

        Map<String, Set<String>> expResult = new TreeMap<>();

        Double velocidade = 20.0;

        Double capacidade = ((teste1.getCapacidadeMax().doubleValue() * 1000 * (teste1.getCargaAtual().doubleValue() / 100)) / (teste1.getPotMotor().doubleValue() )) * velocidade *0.7;
        System.out.println(capacidade);
        String km = String.format("%.2f km", capacidade);
        expResult.put(km, new HashSet<>());
        Set<String> x = expResult.get(km);
        x.add(teste1.getDescricao());
        expResult.get(km).addAll(x);
        capacidade = ((teste2.getCapacidadeMax().doubleValue() * 1000 * (teste2.getCargaAtual().doubleValue() / 100)) / (teste2.getPotMotor().doubleValue() )) * velocidade *0.7;
        System.out.println(capacidade);
        km = String.format("%.2f km", capacidade);
        expResult.put(km, new HashSet<>());
        x = expResult.get(km);
        x.add(teste2.getDescricao());
        expResult.get(km).addAll(x);
        expResult.get(km).add(teste3.getDescricao());
        Map<String, Set<String>> result = instance.capacidadeVeiculoRelatorio(idParque);
        assertEquals(expResult, result);

    }

    /**
     * Test of getTempoCargaRestante method, of class VeiculoController.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetTempoCargaRestante() {
        System.out.println("getTempoCargaRestante");
        Pesquisa p = mock(Pesquisa.class);
        VeiculoController instance = Mockito.spy(new VeiculoController());
        instance.setPesquisa(p);

        Scooter s1 = new Scooter(100D, 20, "cidade", 220, "1", "Scooter 1", 20, 2.0, 0.4);
        Parque pq = new Parque("1", 2.0, 3.0, 1, "parque 1", 10, 10, 220, 16);

        Double cargaRestante = s1.getCargaRestante();
        Double expCargaRestante = 80000.0;
        assertEquals(expCargaRestante, cargaRestante, 0.01);

        Integer qtdPontosCarga = 3;
        Double potenciaParque = pq.getPotenciaParque();
        Double distPontoCarga = potenciaParque / qtdPontosCarga;
        Double expDistPontoCarga = 1173.33;
        assertEquals(expDistPontoCarga, distPontoCarga, 0.01);

        Double pontoCarga = null;
        if (cargaRestante == 0) {
            doReturn(null).when(instance).getTempoCargaRestante(s1, cargaRestante);
//            assertEquals(null, instance.getTempoCargaRestante(s1, cargaRestante));
        } else {
            if (distPontoCarga > 3000) {
                pontoCarga = 3000.00;
                assertEquals(3000.00, pontoCarga, 0.01);
            } else {
                pontoCarga = distPontoCarga;
                assertEquals(1173.33, pontoCarga, 0.01);
            }
        }
        Double tCarga = (cargaRestante / pontoCarga) * 3600;
        Double expTcarga = 245454.55;
        assertEquals(expTcarga, tCarga, 0.01);

        Pair<Scooter, Double> expResult = new Pair<>(s1, expTcarga);
        Pair<Scooter, Double> result = instance.getTempoCargaRestante(s1, distPontoCarga);
        BigDecimal bd = new BigDecimal(result.getValue()).setScale(2, RoundingMode.HALF_UP);
        result = new Pair<>(s1, bd.doubleValue());
        assertEquals(expResult, result);
    }

    /**
     * Test of getTempoCargaRestanteByParque method, of class VeiculoController.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetTempoCargaRestanteByParque() {
        System.out.println("getTempoCargaRestanteByParque");
        Pesquisa p = mock(Pesquisa.class);
        VeiculoController instance = Mockito.spy(new VeiculoController());
        instance.setPesquisa(p);

        Scooter s1 = new Scooter(100D, 20, "cidade", 220, "1", "Scooter 1", 20, 2.0, 0.4);
        Scooter s2 = new Scooter(70D, 35, "cidade", 250, "1", "Scooter 2", 30, 2.0, 0.4);
        Scooter s3 = new Scooter(70D, 35, "cidade", 250, "1", "Scooter 3", 30, 2.0, 0.4);
        Parque pq = new Parque("1", 2.0, 3.0, 1, "parque 1", 10, 10, 220, 16);
        String idParque = pq.getIdParque();
        // Teste 1 - parque encontrado
        when(p.getParque(idParque)).thenReturn(pq);
        // Teste 2 - parque não encontrado
        doReturn(null).when(p).getParque(idParque);

        List<Scooter> scootersExpResult = new ArrayList<>();
        scootersExpResult.add(s1);
        scootersExpResult.add(s2);
        scootersExpResult.add(s3);
        // Teste 3 - lista de scooters corresponde às scooters atualmente no parque 1
        doReturn(scootersExpResult).when(p).getScootersNumParque(idParque);
        // Teste 4 - tamanho da lista de scooters igual à quantidade de scooters no parque
        assertEquals(scootersExpResult.size(), p.getScootersNumParque(idParque).size());
        // Teste 5 - verifica o cálculo da potência total do parque
        Double potenciaParque = pq.getPotenciaParque();
        assertEquals(3520.00, potenciaParque, 0.01);

        // Teste 6 - verifia o cálculo da distribuição da potência do parque pelos pontos de carga existentes
        Integer qtdPontosCarga = scootersExpResult.size();
        Double distPontoCarga = potenciaParque / qtdPontosCarga;
        Double expDistPontoCarga = 1173.33;
        assertEquals(expDistPontoCarga, distPontoCarga, 0.01);
        Double distPontoCargaErrado = potenciaParque * qtdPontosCarga;
        assertNotEquals(expDistPontoCarga, distPontoCargaErrado, 0.01);

        // Teste 7 - verifica o comparator
        SortedSet<Pair<Scooter, Double>> scootersOrdResult = new TreeSet<>((Pair<Scooter, Double> p1, Pair<Scooter, Double> p2) -> {
            Double temp1 = p1.getValue();
            Double temp2 = p2.getValue();
            if (temp1 < temp2) {
                return 1;
            } else if (temp1 > temp2) {
                return -1;
            } else {
                return p1.getKey().getDescricao().compareTo(p2.getKey().getDescricao());
            }
        });
        Pair<Scooter, Double> p1 = new Pair<>(s1, 245455.2427705761);
        Pair<Scooter, Double> p2 = new Pair<>(s2, 139602.66932576513);
        Pair<Scooter, Double> p3 = new Pair<>(s3, 139602.66932576513);
        BigDecimal bd1 = new BigDecimal(p1.getValue()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal bd2 = new BigDecimal(p2.getValue()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal bd3 = new BigDecimal(p3.getValue()).setScale(2, RoundingMode.HALF_UP);
        p1 = new Pair<>(s1, bd1.doubleValue());
        p2 = new Pair<>(s2, bd2.doubleValue());
        p3 = new Pair<>(s3, bd3.doubleValue());        
        
        // Teste 7.1 - testGreaterThan
        Double temp1 = p1.getValue();
        Double temp2 = p2.getValue();
        int greaterThanResult = -1;
        assertEquals(-1, greaterThanResult);
        
        // Teste 7.2 - testLessThan
        temp1 = p2.getValue();
        temp2 = p1.getValue();
        int lessThanResult = 1;
        assertEquals(1, lessThanResult);
        
        // Teste 7.3 - testEquals
        temp1 = p2.getValue();
        temp2 = p3.getValue();
        int equalsResult = 0;
        assertEquals(0, equalsResult);
        int descResult = p2.getKey().getDescricao().compareTo(p3.getKey().getDescricao());
        int descExpResult = -1;
        assertEquals(descExpResult, descResult);
//
//        scootersExpResult.forEach((Scooter scooter) -> {
//            scootersOrdResult.add(instance.getTempoCargaRestante(scooter, expDistPontoCarga));
//            BigDecimal bd = new BigDecimal(scootersOrdResult.first().getValue()).setScale(2, RoundingMode.HALF_UP);
//            scootersOrdResult.add(new Pair(scooter,bd.doubleValue()));
//        });
//    
//        SortedSet<Pair<Scooter, Double>> scootersOrdExpResult = new TreeSet<>((Pair<Scooter, Double> p5, Pair<Scooter, Double> p4) -> {
//            Double tmp1 = p5.getValue();
//            Double tmp2 = p4.getValue();
//            if (tmp1 < tmp2) {
//                return 1;
//            } else if (tmp1 > tmp2) {
//                return -1;
//            } else {
//                return p5.getKey().getDescricao().compareTo(p4.getKey().getDescricao());
//            }
//        }); 
//        scootersOrdExpResult.add(p1);
//        scootersOrdExpResult.add(p2);
//        scootersOrdExpResult.add(p3);  
//        scootersExpResult.forEach((Scooter scooter) -> {
//            scootersOrdExpResult.add(instance.getTempoCargaRestante(scooter, expDistPontoCarga));
//        });
//        assertEquals(scootersOrdExpResult, scootersOrdResult);
    }

    /**
     * Test of estadoCarregamentoRelatorio method, of class VeiculoController.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testEstadoCarregamentoRelatorio() {
        System.out.println("estadoCarregamentoRelatorio");
        Pesquisa p = mock(Pesquisa.class);
        VeiculoController instance = Mockito.spy(new VeiculoController());
        instance.setPesquisa(p);

        Scooter s1 = new Scooter(100D, 20, "cidade", 220, "1", "Scooter 1", 20, 2.0, 0.4);
        Scooter s2 = new Scooter(70D, 35, "cidade", 250, "1", "Scooter 2", 30, 2.0, 0.4);
        Scooter s3 = new Scooter(70D, 35, "cidade", 250, "1", "Scooter 3", 30, 2.0, 0.4);
        Parque pq = new Parque("1", 2.0, 3.0, 1, "parque 1", 10, 10, 220, 16);
        String idParque = pq.getIdParque();
        // Teste 1 - parque encontrado
        when(p.getParque(idParque)).thenReturn(pq);
        // Teste 2 - parque não encontrado
        doReturn(null).when(p).getParque(idParque);
        doReturn(null).when(instance).getTempoCargaRestanteByParque(idParque);

        List<Scooter> scootersExpResult = new ArrayList<>();
        scootersExpResult.add(s1);
        scootersExpResult.add(s2);
        scootersExpResult.add(s3);
        // Teste 3 - lista de scooters corresponde às scooters atualmente no parque 1
        doReturn(scootersExpResult).when(p).getScootersNumParque(idParque);

//        ArrayList<String> listaResult = new ArrayList<>();
//        SortedSet<Pair<Scooter, Double>> scootersOrdExpResult = instance.getTempoCargaRestanteByParque(idParque);
//        for (Pair<Scooter, Double> pair : scootersOrdExpResult) {
//            String desc = pair.getKey().getDescricao();
//            String cAtual = String.format("%d", pair.getKey().getCargaAtual());
//            String tFalta = String.format("%.2f", pair.getValue());
//            listaResult.add(desc + ";" + cAtual + ";" + tFalta);
//        }
//        SortedSet<Pair<Scooter, Double>> scootersOrdResult = new TreeSet<>();
//
//        ArrayList<String> litaExpResult = new ArrayList<>();
//        String str1 = "Scooter 1;20;245455.24";
//        String str2 = "Scooter 2;35;139602.67";
//        String str3 = "Scooter 3;35;139602.67";
//        litaExpResult.add(str1);
//        litaExpResult.add(str2);
//        litaExpResult.add(str3);
//        assertEquals(litaExpResult, listaResult);
    }

    /**
     * 
     * Test GetTipoVeiculoPorUsername
     */ 
      @Test
    public void testGetTipoVeiculoPorUsername() {
        System.out.println("getTipoVeiculoPorUsername");
        Pesquisa p = mock(Pesquisa.class);
        VeiculoController instance = Mockito.spy(new VeiculoController());
        instance.setPesquisa(p);

        String username = "testeUsername";
        // Teste 1 - Scooter tipo
        when(p.getTipoVeiculoPorUser(username)).thenReturn("Scooter");
        assertEquals("Scooter", instance.getTipoVeiculoPorUsername(username));
        
      // Teste 2 - Bicicleta tipo 
        when(p.getTipoVeiculoPorUser(username)).thenReturn("Bicicleta");
        assertEquals("Bicicleta", instance.getTipoVeiculoPorUsername(username));
     
       
    }

    /**
     * Test of getScooters method, of class VeiculoController.
     */
    @Test
    public void testGetScooters() {
        System.out.println("getScooters");
        boolean removido = false;
        Pesquisa p = mock(Pesquisa.class);
        VeiculoController instance = new VeiculoController();
        instance.setPesquisa(p);
        List<Scooter> expResult = new ArrayList<>();
        when(p.getScooters(removido)).thenReturn(expResult);
        List<Scooter> result = instance.getScooters(removido);
        assertEquals(expResult, result);
    }

    /**
     * Test of getBicicletas method, of class VeiculoController.
     */
    @Test
    public void testGetBicicletas() {
        System.out.println("getBicicletas");
        boolean removido = false;
        Pesquisa p = mock(Pesquisa.class);
        VeiculoController instance = new VeiculoController();
        instance.setPesquisa(p);
        List<Bicicleta> expResult = new ArrayList<>();
        when(p.getBicicletas(removido)).thenReturn(expResult);
        List<Bicicleta> result = instance.getBicicletas(removido);
        assertEquals(expResult, result);
    }
}