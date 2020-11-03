/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import lapr.project.data.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author joaoferreira
 */
public class TransacaoTest {

    @Mock
    ParqueBD pqbd;
    @Mock
    VeiculoBD vbd;
    @Mock
    CaminhoBD cbd;
    @Mock
    POIBD poibd;
    @Mock
    UtilizadorBD userbd;

    Transacao t;

    public TransacaoTest() {
    }

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        t = new Transacao();
        t.setCbd(cbd);
        t.setPoibd(poibd);
        t.setPqbd(pqbd);
        t.setUbd(userbd);
        t.setVbd(vbd);
    }

    /**
     * Test of addAllParques method, of class Transacao.
     */
    @Test
    public void testAddAllParques() {
        System.out.println("addAllParques");

        ArrayList<Parque> pqs = new ArrayList<>();
        when(pqbd.addAll(pqs)).thenReturn(true);
        assertTrue(t.addAllParques(pqs));
    }

    /**
     * Test of addAllVeiculos method, of class Transacao.
     */
    @Test
    public void testAddAllVeiculos() {
        System.out.println("addAllVeiculos");
        ArrayList<Veiculo> vcs = new ArrayList<>();

        when(vbd.addAll(vcs)).thenReturn(true);
        assertTrue(t.addAllVeiculos(vcs));
    }

    /**
     * Test of addAllCaminhos method, of class Transacao.
     */
    @Test
    public void testAddAllCaminhos() {
        System.out.println("addAllCaminhos");
        ArrayList<Caminho> cms = new ArrayList<>();

        when(cbd.addAll(cms)).thenReturn(true);
        assertTrue(t.addAllCaminhos(cms));
    }

    /**
     * Test of addAllPOI method, of class Transacao.
     */
    @Test
    public void testAddAllPOI() {
        System.out.println("addAllPOI");
        ArrayList<POI> pois = new ArrayList<>();

        when(poibd.addAll(pois)).thenReturn(true);
        assertTrue(t.addAllPOI(pois));
    }

    /**
     * Test of addAllUsers method, of class Transacao.
     */
    @Test
    public void testAddAllUsers() {
        System.out.println("addAllUsers");
        ArrayList<Utilizador> users = new ArrayList<>();

        when(userbd.addAll(users)).thenReturn(true);
        assertTrue(t.addAllUsers(users));
    }

    /**
     * Test of setPqbd method, of class Transacao.
     */
    @org.junit.Test
    public void testSetPqbd() {
        System.out.println("setPqbd");

        t.setPqbd(pqbd);
    }

    /**
     * Test of setVbd method, of class Transacao.
     */
    @org.junit.Test
    public void testSetVbd() {
        System.out.println("setVbd");

        t.setVbd(vbd);
    }

    /**
     * Test of setCbd method, of class Transacao.
     */
    @org.junit.Test
    public void testSetCbd() {
        System.out.println("setCbd");

        t.setCbd(cbd);
    }

    /**
     * Test of setPoibd method, of class Transacao.
     */
    @org.junit.Test
    public void testSetPoibd() {
        System.out.println("setPoibd");

        t.setPoibd(poibd);
    }

    /**
     * Test of setUbd method, of class Transacao.
     */
    @org.junit.Test
    public void testSetUbd() {
        System.out.println("setUbd");

        t.setUbd(userbd);
    }

}
