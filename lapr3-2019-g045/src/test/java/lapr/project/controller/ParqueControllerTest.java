/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Parque;
import lapr.project.model.Pesquisa;
import lapr.project.model.Transacao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

/**
 *
 * @author Thais Farias
 */
public class ParqueControllerTest {

    public ParqueControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of addParques method, of class ParqueController.
     */
    @Test
    public void testAddParques() {
        ParqueController instance = Mockito.spy(new ParqueController());
        String[] parque1 = {"id1", "14.241234", " 8.123456", "4", "parque3", "3", "20", "10", "1"};
        String[] parque2 = {"id2", "12.241234", " 4.443456", "5", "parque2", "4", "10", "30", "5"};

        List<String[]> paths = new ArrayList<>();
        paths.add(parque1);
        paths.add(parque2);

        Transacao t = Mockito.mock(Transacao.class);
        instance.setTransacao(t);

        // teste com os parques falhando ao serem adicionados
        when(t.addAllParques(any())).thenReturn(false);
        assertEquals(0, instance.addParques(paths));

        // teste com os parques sendo adicionados	 com sucesso
        when(t.addAllParques(any())).thenReturn(true);
        assertEquals(2, instance.addParques(paths));
    }

    /**
     * Test of salvarParque method, of class ParqueController.
     */
    @Test
    public void testSalvarParque() {
        System.out.println("salvarParque");
        ParqueController instance = new ParqueController();

        Parque p = Mockito.mock(Parque.class);
        when(p.save()).thenReturn(true);

        boolean expResult = true;
        boolean result = instance.salvarParque(p);
        assertEquals(expResult, result);

        when(p.save()).thenReturn(false);
        expResult = false;
        result = instance.salvarParque(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of atualizaParque method, of class ParqueController.
     */
    @Test
    public void testAtualizaParque() {
        System.out.println("atualizaParque");

        ParqueController instance = Mockito.spy(new ParqueController());

        String dados[] = {"testeID", "12.241234", "4.443456", "5", "parque 4", "2", "10", "30", "5"};
        Parque p = new Parque();
        p.setIdParque("testeID");

        // não existe o parque
        doReturn(null).when(instance).getParque("testeID");
        assertEquals(false, instance.atualizaParque(dados));

        // existe e a atualizacao funciona
        doReturn(p).when(instance).getParque("testeID");
        doReturn(true).when(instance).salvarParque(p);
        assertEquals(true, instance.atualizaParque(dados));

        assertEquals("testeID", p.getIdParque());
        assertEquals(12.241234, p.getLatitude());
        assertEquals(4.443456, p.getLongitude());
        assertEquals(5, p.getElevacao());
        assertEquals("parque 4", p.getDescricao());
        assertEquals(2, p.getLotBike());
        assertEquals(10, p.getLotScooter());
        assertEquals(30, p.getVoltagem());
        assertEquals(5, p.getCorrente());

        // existe mas nao consegue atualizar
        doReturn(p).when(instance).getParque("testeID");
        doReturn(false).when(instance).salvarParque(p);
        assertEquals(false, instance.atualizaParque(dados));
        assertEquals(12.241234, p.getLatitude());
        assertEquals(4.443456, p.getLongitude());
        assertEquals(5, p.getElevacao());
        assertEquals("parque 4", p.getDescricao());
        assertEquals(2, p.getLotBike());
        assertEquals(10, p.getLotScooter());
        assertEquals(30, p.getVoltagem());
        assertEquals(5, p.getCorrente());

    }

    /**
     * Test of removerParque method, of class ParqueController.
     */
    @Test
    public void testRemoveParque() {
        System.out.println("removerParque");

        ParqueController instance = Mockito.spy(new ParqueController());
        Pesquisa pb = Mockito.mock(Pesquisa.class);
        instance.setPesquisa(pb);

        Parque parque = new Parque("testeID", 12.123, 12.123, 10, "testeDesc", 10, 10, 220, 16);

        //  teste  null remove parque sem sucesso
        doThrow(IllegalArgumentException.class).when(pb).getParque("testeID");
        assertEquals(false, instance.removeParque("testeID"));

        // existe e remove parque 
        doReturn(parque).when(pb).getParque("testeID");
        doReturn(true).when(instance).salvarParque(parque);
        assertEquals(true, instance.removeParque("testeID"));
        assertEquals(0, parque.getAtivo());
    }

    /**
     * Test of removerParque method, of class ParqueController.
     */
    @Test
    public void testGetParque() {
        System.out.println("getParque");

        ParqueController instance = Mockito.spy(new ParqueController());
        Pesquisa pb = Mockito.mock(Pesquisa.class);
        instance.p = pb;

        // 1o teste: veiculo nao encontrado
        doReturn(null).when(pb).getParque("testeID");
        Parque result = instance.getParque("testeID");
        assertTrue(result == null);

        // Parque encontrado
        Parque expResult = new Parque("testeID", 12.123, 12.123, 10, "testeDesc", 10, 10, 220, 16);
        doReturn(expResult).when(pb).getParque("testeID");
        result = instance.getParque("testeID");
    }

    /**
     * Test of setTransacao method, of class ParqueController.
     */
    @Test
    public void testSetTransacao() {
        System.out.println("setTransacao");
        Transacao expResult = null;
        ParqueController instance = new ParqueController();
        instance.setTransacao(expResult);
        assertEquals(null, instance.t);
    }

    /**
     * Test of lugaresDisponiveisBicicletas method, of class ParqueController.
     */
    @Test
    public void testverificaLugaresDisponiveisParque() {
        System.out.println("lugaresDisponiveis");

        ParqueController instance = Mockito.spy(new ParqueController());
        Pesquisa pb = Mockito.mock(Pesquisa.class);
        instance.setPesquisa(pb);
        String id = "testeID";
        String username = "testeUser";

        // 1o teste: veiculo nao encontrado devolve 0
        doReturn(null).when(pb).getParque("testeID");
        int result = instance.verificaLugaresDisponiveisParque(id, username);
        assertTrue(result == 0);

        // 2o teste: retorno correto
        Parque parque = new Parque("testeID", 12.241234, 4.443456, 5, "parque2", 4, 15, 30, 5);
        parque.setIdParque("testeID");
        doReturn(parque).when(pb).getParque("testeID");
        doReturn(4).when(pb).verificaLugaresDisponiveis(id, username);
        result = instance.verificaLugaresDisponiveisParque(id, username);
        assertTrue(result == 4);

        // 3o teste: veiculo nao encontrado devolve 0
        doThrow(IllegalArgumentException.class).when(instance).getParque("testeID");
        doReturn(0).when(pb).verificaLugaresDisponiveis(id, username);
        result = instance.verificaLugaresDisponiveisParque(id, username);
        assertTrue(result == 0);

    }

    /**
     * Test of getParques method, of class ParqueController.
     */
    @Test
    public void getParques() {
        System.out.println("getParques");

        List<Parque> parques = new ArrayList<>();
        Parque p1 = new Parque("id", 1.0, 1.0, 1, "1", 1, 1, 1, 1);
        Parque p2 = new Parque("id", 2.0, 2.0, 2, "2", 2, 2, 2, 2);
        parques.add(p1);
        parques.add(p2);

        ParqueController instance = Mockito.spy(new ParqueController());
        Pesquisa pb = Mockito.mock(Pesquisa.class);
        instance.setPesquisa(pb);
        when(pb.getParques()).thenReturn(parques);
        assertEquals(parques, instance.getParques());
    }
}
