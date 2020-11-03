
package lapr.project.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class FaturaControllerTest {
    
    public FaturaControllerTest() {
    }
    
    FaturaController controller;
    Pesquisa p;

    @BeforeEach
    public void init() {
        controller = Mockito.spy(new FaturaController());
        p = Mockito.mock(Pesquisa.class);
        controller.p = p;
    }
    
    @Test
    public void testSalvarFatura() {
        System.out.println("salvarFatura");
        FaturaController instance = new FaturaController();
        Fatura f = Mockito.mock(Fatura.class);

        when(f.save()).thenReturn(true);
        boolean expResult = true;
        boolean result = instance.salvarFatura(f);
        assertEquals(expResult, result);

        when(f.save()).thenReturn(false);
        expResult = false;
        result = instance.salvarFatura(f);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetDebitoAtual() {
        System.out.println("getDebitoAtual");
        
        Utilizador utilizador = new Utilizador("username", "email", "password", "visaCardNumber", 10f, 10f, 10f,"M");
        
        Fatura fatura = new Fatura();
        fatura.setValorCobrado(10);
        fatura.setValorPago(10);
        List<Fatura> faturasBD = new ArrayList<>();
        faturasBD.add(fatura);
        
        List<String> detalheBD = new ArrayList<>();
        detalheBD.add("bla;bla;10");
        detalheBD.add("bla;bla;20");
        detalheBD.add("bla;bla;30");
        
        List<String> detalhe = new ArrayList<>();
        doReturn(null).when(p).getUtilizadorByUserName(anyString());
        assertEquals(0, controller.getDebitoAtual("josuemotta", detalhe));
        doReturn(utilizador).when(p).getUtilizadorByUserName(anyString());
        doReturn(detalheBD).when(p).getDetalheAtual(anyString());
        doReturn(faturasBD).when(p).getFaturasUtilizador(anyString());
        
        assertEquals(60.0, controller.getDebitoAtual("josuemotta", detalhe));
    }
    
    
    @Test
    public void testGetUserCurrentPoints() {
        System.out.println("getUserCurrentPoints");
        
        Utilizador utilizador = new Utilizador("username", "email", "password", "visaCardNumber", 10f, 10f, 10f,"M");
        
        Fatura fatura = new Fatura();
        fatura.setValorCobrado(10);
        fatura.setValorPago(10);
        fatura.setPontosDescontados(5);
        List<Fatura> faturasBD = new ArrayList<>();
        faturasBD.add(fatura);
        
        List<String> detalheBD = new ArrayList<>();
        detalheBD.add("bla;bla;10");
        detalheBD.add("bla;bla;20");
        detalheBD.add("bla;bla;30");
        List<String> detalheBD2 = new ArrayList<>();
        
        List<String> detalhe = new ArrayList<>();
        doReturn(null).when(p).getUtilizadorByUserName(anyString());
        assertEquals(0, controller.getUserCurrentPoints("josuemotta", detalhe));
        doReturn(utilizador).when(p).getUtilizadorByUserName(anyString());
        doReturn(detalheBD).when(p).getDetalhePontos(anyString());
        doReturn(faturasBD).when(p).getFaturasUtilizador(anyString());
        
        assertEquals(55.0, controller.getUserCurrentPoints("josuemotta", detalhe));
        fatura.setPontosDescontados(-5);
        assertEquals(65.0, controller.getUserCurrentPoints("josuemotta", detalhe));
        fatura.setPontosDescontados(5);
        doReturn(detalheBD2).when(p).getDetalhePontos(anyString());
        assertEquals(-5.0, controller.getUserCurrentPoints("josuemotta", detalhe));
        
    }
    
    @Test
    public void testEmiteFatura() {
        System.out.println("emiteFatura");
        
        Utilizador utilizador = new Utilizador("username", "email", "password", "visaCardNumber", 10f, 10f, 10f, "M");
        
        Fatura fatura = new Fatura();
        fatura.setValorCobrado(10);
        fatura.setValorPago(10);
        
        doReturn(null).when(p).getUtilizadorByUserName(anyString());
        assertEquals(false, controller.emiteFatura("josuemotta", 1));
        doReturn(utilizador).when(p).getUtilizadorByUserName(anyString());
        doReturn(true).when(controller).salvarFatura(any(Fatura.class));
        assertEquals(false, controller.emiteFatura("josuemotta", 1));
        assertEquals(true, controller.emiteFatura("josuemotta", 12));
    }
    
    @Test
    public void testGetFatura() {
        System.out.println("getFatura");
        
        Utilizador utilizador = new Utilizador("username", "email", "password", "visaCardNumber", 10f, 10f, 10f, "M");
        
        Fatura fatura = new Fatura();
        fatura.setValorCobrado(10);
        fatura.setValorPago(10);
        
        List<String> header = new ArrayList<>();
        List<String> detalhe = new ArrayList<>();
        
        doReturn(null).when(p).getUtilizadorByUserName(anyString());
        assertEquals(0, controller.getFatura("josuemotta", 1, header, detalhe));
        
        doReturn(utilizador).when(p).getUtilizadorByUserName(anyString());
        doReturn(null).when(p).getFatura(anyString(), anyInt());
        assertEquals(0, controller.getFatura("josuemotta", 1, header, detalhe));
        doReturn(fatura).when(p).getFatura(anyString(), anyInt());
        assertEquals(10, controller.getFatura("josuemotta", 1, header, detalhe));
        
    }
    
    
    
    
    
    
    
    
}
