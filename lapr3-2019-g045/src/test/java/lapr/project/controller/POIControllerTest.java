/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

/**
 *
 * @author joaoferreira
 */
public class POIControllerTest {

    public POIControllerTest() {
    }

    /**
     * Test of carregarPOIs method, of class POIController.
     */
    @Test
    public void testCarregarPOIs() {
        System.out.println("carregarPOIs");
        POIController controller = Mockito.spy(new POIController());
        String[] poi1 = {"1", "1", "1", "1", "1", "1", "1"};
        String[] poi2 = {"2", "2", "2", "2", "2", "2", "2"};
        String[] poi3 = {"3", "3", "3", "3", "3", "3", "3"};
        List<String[]> pois = new ArrayList<>();
        pois.add(poi1);
        pois.add(poi2);
        pois.add(poi3);

        Transacao t = Mockito.mock(Transacao.class);
        controller.setTransacao(t);

        // teste com os caminhos falhando ao serem adicionados
        when(t.addAllPOI(any())).thenReturn(false);
        assertEquals(0, controller.carregarPOIs(pois));

        // teste com os caminhos sendo adicionados	 com sucesso
        when(t.addAllPOI(any())).thenReturn(true);
        assertEquals(3, controller.carregarPOIs(pois));
    }

    /**
     * Test of salvarPOI method, of class POIController.
     */
    @Test
    public void testSalvarPOI() {
        System.out.println("salvarPOI");
        POIController instance = new POIController();

        POI c = Mockito.mock(POI.class);
        when(c.save()).thenReturn(true);

        boolean expResult = true;
        boolean result = instance.salvarPOI(c);
        assertEquals(expResult, result);

        when(c.save()).thenReturn(false);
        expResult = false;
        result = instance.salvarPOI(c);
        assertEquals(expResult, result);
    }

    /**
     * Test of removerPOI method, of class POIController.
     */
    @Test
    public void testRemoverPOI() {
        System.out.println("removerPOI");
        POIController instance = Mockito.spy(new POIController());
        Pesquisa pb = Mockito.spy(new Pesquisa());
        instance.pb = pb;
        POI poi = new POI(2.0, 3.0, 4, "teste");

        // teste 1: POI não existe
        doThrow(IllegalArgumentException.class).when(pb).getPOI(2.0, 3.0);
        boolean result1 = instance.removerPOI(2.0, 3.0);
        assertEquals(false, result1);
        
        doThrow(Exception.class).when(instance).removerPOI(poi);
        boolean result = instance.removerPOI(2.0, 3.0);
        assertEquals(false, result);

        // teste 2: remove POI com sucesso
        doReturn(poi).when(pb).getPOI(2.0, 3.0);
        doReturn(true).when(instance).removerPOI(poi);
        boolean result2 = instance.removerPOI(2.0, 3.0);
        assertEquals(true, result2);
        
        // teste 3: remove POI sem sucesso
        doReturn(false).when(instance).removerPOI(poi);
        boolean result3 = instance.removerPOI(2.0, 3.0);
        assertEquals(false, result3);
        
        
        POI poi2 = Mockito.mock(POI.class);
        when(poi2.remove()).thenReturn(true);
        result = instance.removerPOI(poi2);
        assertEquals(true, result);

        poi2 = Mockito.mock(POI.class);
        when(poi2.remove()).thenReturn(false);
        result = instance.removerPOI(poi2);
        assertEquals(false, result);
    }
    
    /**
     * Test of setTransacao method, of class POIController.
     */
    @Test
    public void testSetTransacao() {
        System.out.println("setTransacao");
        Transacao t = new Transacao();
        POIController instance = new POIController();
        instance.setTransacao(t);
    }
    
    /**
     * Test of setPesquisa method, of class POIController.
     */
    @Test
    public void testSetPesquisa() {
        System.out.println("setPesquisa");
        Pesquisa p = new Pesquisa();
        POIController instance = new POIController();
        instance.setPesquisa(p);
    }
}
