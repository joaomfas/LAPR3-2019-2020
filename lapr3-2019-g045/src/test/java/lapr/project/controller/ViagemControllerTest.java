/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import lapr.project.model.Bicicleta;
import lapr.project.model.Local;
import lapr.project.model.Parque;
import lapr.project.model.Pesquisa;
import lapr.project.model.Scooter;
import lapr.project.model.Utilizador;
import lapr.project.model.Veiculo;
import lapr.project.model.Viagem;
import org.junit.After;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

/**
 *
 * @author mdias
 */
public class ViagemControllerTest {

    public ViagemControllerTest() {
    }

    ViagemController controller;
    Pesquisa p;

    @BeforeEach
    public void init() {
        controller = Mockito.spy(new ViagemController());
        p = Mockito.mock(Pesquisa.class);
        controller.p = p;
    }

    @After
    public void tearDown() throws Exception {
    }
    
    //unLock(Utilizador utilizador, Veiculo veiculo, Parque parqueOrigem, Date date)
    @Test
    public void testUnLock() {
        
        Utilizador utilizador = new Utilizador();
        
        Scooter scooter = new Scooter();
        
        Parque parqueOrigem = new Parque();
        
        Date date = new Date(100000);
        
        doReturn(false).when(controller).salvarViagem(any());
        
        long expect = 0;
        long result = controller.unLock(utilizador, scooter, parqueOrigem, date);
        
        assertEquals(expect, result);

        result = controller.unLock(utilizador, scooter, null, date);
        assertEquals(expect, result);
        
        result = controller.unLock(utilizador, null, null, date);
        assertEquals(expect, result);
        
        result = controller.unLock(null, null, null, date);
        assertEquals(expect, result);
        
        doReturn(true).when(controller).salvarViagem(any());
        
        expect = 100000;
        result = controller.unLock(utilizador, scooter, parqueOrigem, date);
        assertEquals(expect, result);
        
    }
    
    //public long lock(Utilizador utilizador, Veiculo veiculo, Parque parqueDestino, Date date)
    @Test
    public void testLock() {
        
        Utilizador utilizador = new Utilizador();
        utilizador.setIdUtilizador(1);
        
        System.out.println(">>" + utilizador.getIdUtilizador());
        
        Scooter scooter = new Scooter();
        scooter.setDescricao("veiculo amarelo");
        scooter.setRemovido(false);
        
        Parque parqueDestino = new Parque();
        
        Date date = new Date(100000);
        
        Viagem viagem = new Viagem();
        viagem.setIdViagem(1);
        viagem.setIdUtilizador(1);
        viagem.setDescVeiculo("veiculo amarelo");
        viagem.setIdParqueInicio("1");
        viagem.setIdParqueFim("1");
        viagem.setDataHoraInicio(date);
        viagem.setDataHoraFim(date);
        
        
        doReturn(false).when(controller).salvarViagem(any(Viagem.class));
        doReturn(viagem).when(p).getViagemUtilizador(1);
        
        long expect = 0;
        long result = controller.lock(utilizador, scooter, parqueDestino, date);
        assertEquals(expect, result);
        result = controller.lock(utilizador, scooter, null, date);
        assertEquals(expect, result);
        result = controller.lock(utilizador, null, null, date);
        assertEquals(expect, result);
        result = controller.lock(null, null, null, date);
        assertEquals(expect, result);
        doReturn(true).when(controller).salvarViagem(any(Viagem.class));
        expect = 100000;
        result = controller.lock(utilizador, scooter, parqueDestino, date);
        assertEquals(expect, result);        
        scooter.setRemovido(true);
        expect = 0;
        result = controller.lock(utilizador, scooter, parqueDestino, date);
        assertEquals(expect, result);
        scooter.setRemovido(false);
        viagem.setDescVeiculo(null);
        expect = 0;
        result = controller.lock(utilizador, scooter, parqueDestino, date);
        assertEquals(expect, result);
        viagem.setDescVeiculo("def");
        scooter.setDescricao(null);
        expect = 0;
        result = controller.lock(utilizador, scooter, parqueDestino, date);
        assertEquals(expect, result);
        scooter.setDescricao("abc");
        expect = 0;
        result = controller.lock(utilizador, scooter, parqueDestino, date);
        assertEquals(expect, result);
        scooter.setDescricao("def");
        doReturn(null).when(p).getViagemUtilizador(1);
        expect = 0;
        result = controller.lock(utilizador, scooter, parqueDestino, date);
        assertEquals(expect, result);
        
    }
    
    
    @Test
    public void testUnlockVeiculo() {
        
        Utilizador uu = new Utilizador();
        uu.setIdUtilizador(1);
        uu.setUsername("abcd");
        Scooter veiculo = new Scooter();
        veiculo.setDescricao("veiculo amarelo");
        veiculo.setIdParque("2");
        Parque parqueOrigem = new Parque();
        parqueOrigem.setIdParque("2");
        
        Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        

        doReturn(uu).when(p).getUtilizadorByUserName("abcd");
        doReturn(veiculo).when(p).getVeiculo("veiculo amarelo");
        doReturn(parqueOrigem).when(p).getParque(veiculo.getIdParque());                
        doReturn(date).when(controller).dateInstance();
        
        doReturn(date.getTime()).when(controller).unLock(any(Utilizador.class), any(Veiculo.class), any(Parque.class), any(java.sql.Date.class));
        
        long expected = date.getTime();
        long result = controller.unlockVeiculo("veiculo amarelo", "abcd");
        assertEquals(expected, result);        
        
        
        doReturn(null).when(p).getVeiculo("veiculo amarelo");
        expected = 0;
        result = controller.unlockVeiculo("veiculo amarelo", "abcd");
        assertEquals(expected, result);   
        
        
        expected = date.getTime();
        result = controller.unlockVeiculo("veiculo amarelo", "1", "abcd");
        assertEquals(expected, result);   
    }
    
    
    
    
    @Test
    public void testlockVeiculo() {
        
        Utilizador uu = new Utilizador();
        uu.setIdUtilizador(1);
        uu.setUsername("abcd");
        Scooter veiculo = new Scooter();
        veiculo.setDescricao("veiculo amarelo");
        veiculo.setIdParque("2");
        Parque parqueOrigem = new Parque();
        parqueOrigem.setDescricao("abc");
        parqueOrigem.setIdParque("2");
        
        Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        


        doReturn(uu).when(p).getUtilizadorByUserName("abcd");
        doReturn(veiculo).when(p).getVeiculo("veiculo amarelo");
        doReturn(parqueOrigem).when(p).getParque("1");  
        doReturn(parqueOrigem).when(p).getParque(10, 10); 
        doReturn(date).when(controller).dateInstance();
        
        doReturn(date.getTime()).when(controller).lock(any(Utilizador.class), any(Veiculo.class), any(Parque.class), any(java.sql.Date.class));
        
        long expected = date.getTime();
        long result = controller.lockVeiculo("veiculo amarelo", 10, 10, "abcd");
        assertEquals(expected, result);    
        
        expected = date.getTime();
        result = controller.lockVeiculo("veiculo amarelo", parqueOrigem.getDescricao(), "abcd");
        assertEquals(expected, result); 
    }
    
    @Test
    public void testDateInstance() {
        Date date = new Date(1000000);
        Date result = new ViagemController().dateInstance();
        assertTrue(!date.equals(result));
    }
    /**
     * Test of salvarViagem method, of class ViagemController.
     */
    @Test
    public void testSalvarViagem() {
        System.out.println("salvarViagem");
        ViagemController instance = new ViagemController();
        Viagem v = Mockito.mock(Viagem.class);

        when(v.save()).thenReturn(true);
        boolean expResult = true;
        boolean result = instance.salvarViagem(v);
        assertEquals(expResult, result);

        when(v.save()).thenReturn(false);
        expResult = false;
        result = instance.salvarViagem(v);
        assertEquals(expResult, result);
    }

    /**
     * Test of getVeiculo method, of class ViagemController.
     */
    @Test
    public void testGetVeiculo() {
        System.out.println("getVeiculo");
        ViagemController controller = new ViagemController();
        Pesquisa pb = Mockito.mock(Pesquisa.class);
        controller.p = pb;
        
        // 1o teste: veiculo nao encontrado
        doReturn(null).when(pb).getVeiculo("testeDesc");
        Veiculo result = controller.getVeiculo("testeDesc");
        assertTrue(result == null);
        
        // 2o teste: veiculo encontrado
        Bicicleta expResult = new Bicicleta();
        expResult.setDescricao("testeDesc");
        doReturn(expResult).when(pb).getVeiculo("testeDesc");
        result = controller.getVeiculo("testeDesc");
        assertEquals(expResult, result);
    }

    /**
     * Test of caloriasEntreDoisPontos method, of class CaminhosController.
     */
    @Test
    public void testCaloriasEntreDoisPontos() {
        System.out.println("caloriasEntreDoisPontos");
        
        Parque teste1 = new Parque();
        teste1.setLatitude(41.167331);
        teste1.setLongitude(-8.688336);
        teste1.setElevacao(10);
        Parque teste2 = new Parque();
        teste2.setLatitude(41.150004);
        teste2.setLongitude(-8.676257);
        teste2.setElevacao(10);
        
        Bicicleta v1 = new Bicicleta();
        Utilizador u1 = new Utilizador();
        u1.setPeso(60f);
        u1.setVelocidadeMedia(5.5f);
        v1.setAreaFrontal(2);
        v1.setCoeficienteAerodinamico(1.1);
        v1.setPeso(20);
        
        ViagemController instance = new ViagemController();
        double expResult = 23360.973;
        double result = instance.caloriasEntreDoisPontos(teste1, teste2, v1, u1);
        assertEquals(expResult, result, 0.1);
    }
    
    /**
     * Test of caloriasEntreDoisPontos method, of class ViagemController.
     */
    @Test
    public void testCaloriasEntreDoisPontos_double() {
        System.out.println("caloriasEntreDoisPontos");
        double kwh = 1D;
        ViagemController instance = new ViagemController();
        double expResult = 860420.65;
        double result = instance.caloriasEntreDoisPontos(kwh);
        assertEquals(expResult, result, 0.0);
    }

}
