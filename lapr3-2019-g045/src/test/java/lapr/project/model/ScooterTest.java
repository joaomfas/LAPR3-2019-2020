/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.After;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Thais Farias
 */
public class ScooterTest {

    Scooter teste1;

    @BeforeEach
    public void setUp() {
        teste1 = new Scooter(100D, 50, "cidade", 200, "idTeste", "testeDesc", 20, 2.0, 0.4);
    }

    @After
    public void tearDown() throws Exception {
    }

    public ScooterTest() {
        Scooter teste2 = new Scooter();
        Scooter teste3 = new Scooter(100D, 50, "off-road", 200, "idTeste", "testeDesc", 20, 2.0, 0.4);
        assertEquals(null, teste2.getAreaFrontal());
        assertEquals(20, teste3.getPeso());

    }

    /**
     * Test of getCapacidadeMax method, of class Scooter.
     */
    @Test
    public void testGetCapacidadeMax() {
        Double expResult = 100.0;
        Double result = teste1.getCapacidadeMax();
        assertEquals(expResult, result);

    }

    /**
     * Test of setCapacidadeMax method, of class Scooter.
     */
    @Test
    public void testSetCapacidadeMax() {
        Double expResult = 96D;
        teste1.setCapacidadeMax(expResult);
        Double result = teste1.getCapacidadeMax();
        assertEquals(expResult, result);

    }

    /**
     * Test of getCargaAtual method, of class Scooter.
     */
    @Test
    public void testGetCargaAtual() {
        Integer expResult = 50;
        Integer result = teste1.getCargaAtual();
        assertEquals(expResult, result);

    }

    /**
     * Test of setCargaAtual method, of class Scooter.
     */
    @Test
    public void testSetCargaAtual() {
        Integer expResult = 8;
        teste1.setCargaAtual(expResult);
        Integer result = teste1.getCargaAtual();
        assertEquals(expResult, result);

    }

    /**
     * Test of getTipoScooter method, of class Scooter.
     */
    @Test
    public void testGetTipoScooter() {
        String expResult = "cidade";
        String result = teste1.getTipoScooter();
        assertEquals(expResult, result);

    }

    /**
     * Test of setTipoScooter method, of class Scooter.
     */
    @Test
    public void testSetTipoScooter() {
        String expResult = "city";
        teste1.setTipoScooter(expResult);
        String result = teste1.getTipoScooter();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPotMotor method, of class Scooter.
     */
    @org.junit.Test
    public void testGetPotMotor() {
        System.out.println("getPotMotor");
         Scooter instance = new Scooter(100D, 50, "off-road", 200, "idTeste", "testeDesc", 20, 2.0, 0.4);
        Integer expResult = 200;
        Integer result = instance.getPotMotor();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPotMotor method, of class Scooter.
     */
    @org.junit.Test
    public void testSetPotMotor() {
        System.out.println("setPotMotor");
        Integer potMotor = 200;
        Scooter instance = new Scooter();
        instance.setPotMotor(potMotor);
    }
    
    /**
     * Test of getCargaRestante method, of class Scooter.
     */
    @org.junit.Test
    public void testGetCargaRestante() {
        System.out.println("getCargaRestante");
        Scooter instance = new Scooter(100D, 20, "off-road", 200, "idTeste", "testeDesc", 20, 2.0, 0.4);
        Double expResult = 80.000;
        Double result = instance.getCargaRestante();
        assertEquals(expResult, result);
    }

}
