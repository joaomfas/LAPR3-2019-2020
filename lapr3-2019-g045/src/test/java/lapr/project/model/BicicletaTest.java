/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Thais Farias
 */
public class BicicletaTest {

    Bicicleta teste1;
    Bicicleta teste2;

    @BeforeEach
    public void setUp() {
        teste1 = new Bicicleta(16, "CasteloQueijo", "bicicleta 45", 4, 45.5d, 52.5d);
        teste2 = new Bicicleta(16, "CasteloQueijo", "bicicleta 45", 4, 45.5d, 52.5d);
        
        //(Integer tamanho, String id_parque, String descricao, Integer peso, Double areaFrontal, Double coeficienteAerodinamico)
    }

    @Test
    public void BicicletaConTest() {
        Bicicleta b = new Bicicleta(16, "CasteloQueijo", "bicicleta 45", 4, 45.5d, 52.5d);
        Bicicleta b2 = new Bicicleta();
        assertEquals(null, b2.getTamanho());
        assertEquals(16, b.getTamanho());
    }

    /**
     * Test of getTamanho method, of class Bicicleta.
     */
    @Test
    public void testGetTamanho() {
        Integer expResult = 16;
        Integer result = teste1.getTamanho();

        Integer expResult2 = 16;
        Integer result2 = teste2.getTamanho();
        assertEquals(expResult, result);
        assertEquals(expResult2, result2);

    }

    /**
     * Test of setTamanho method, of class Bicicleta.
     */
    @Test
    public void testSetTamanho() {
        Integer expResult = 10;
        teste1.setTamanho(expResult);
        Integer result = teste1.getTamanho();
        Integer expResult2 = 8;
        teste2.setTamanho(expResult2);
        Integer result2 = teste2.getTamanho();
        assertEquals(expResult, result);
        assertEquals(expResult2, result2);

    }

}
