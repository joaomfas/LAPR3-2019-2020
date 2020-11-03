/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import lapr.project.model.*;
import org.junit.After;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author joaoferreira
 */
public class CalculosTest {

    Parque teste1;
    Parque teste2;
    Parque teste3;
    Caminho c1;
    Utilizador u1;
    Bicicleta v1;

    @BeforeEach
    public void iniciarTeste() {
        teste1 = new Parque();
        teste1.setLatitude(41.167331);
        teste1.setLongitude(-8.688336);
        teste1.setElevacao(10);

        teste2 = new Parque();
        teste2.setLatitude(41.150004);
        teste2.setLongitude(-8.676257);
        teste2.setElevacao(10);

        teste3 = new Parque();
        teste3.setLatitude(41.157905);
        teste3.setLongitude(-8.629128);
        teste3.setElevacao(105);

        c1 = new Caminho();

        u1 = new Utilizador();

        v1 = new Bicicleta();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void CalculosConTest() {
        Calculos c = new Calculos();
        assertTrue(c instanceof Calculos);
    }

    /**
     * Test of distEntreDoisLocais method, of class Calculos.
     */
    @org.junit.Test
    public void testDistEntreDoisLocais() {
        System.out.println("distEntreDoisLocais");
        double latA = 35;
        double lonA = 32;
        double latB = 40;
        double lonB = 39;
        double expResult = 830D;
        double result = Calculos.distEntreDoisLocais(latA, lonA, latB, lonB);
        assertEquals(expResult, result, 0.5);
    }

    /**
     * Test of direcaoEntreDoisLocais method, of class Calculos.
     */
    @Test
    public void testDirecaoEntreDoisLocais() {
        System.out.println("direcaoEntreDoisLocais");
        Parque A = new Parque();
        Parque B = new Parque();
        A.setLatitude(39.099912);
        A.setLongitude(-94.581213);
        B.setLatitude(38.627089);
        B.setLongitude(-90.200203);
        Integer expected = 96;
        Integer actual = Calculos.direcaoEntreDoisLocais(A, B).intValue();
        assertEquals(expected, actual);
    }

    /**
     * Test of velocidadeRelativaVento method, of class Calculos.
     */
    @Test
    public void testVelocidadeRelativaVento() {
        System.out.println("velocidadeRelativaVento");
        Parque A = new Parque();
        Parque B = new Parque();
        A.setLatitude(39.099912);
        A.setLongitude(-94.581213);
        B.setLatitude(38.627089);
        B.setLongitude(-90.200203);

        Integer expected = 3;
        Integer actual = Calculos.velocidadeRelativaVento(45.0, 10.0, A, B).intValue();
        assertEquals(expected, actual);
    }

    /**
     * Test of energiaEntreLocais method, of class Calculos.
     */
    @Test
    public void testEnergiaEntreLocais() {
        System.out.println("energiaEntreLocais");
                double expected = 0.046;
        double val = Calculos.energiaEntreLocais(null, null, null, teste1, teste2);
        assertEquals(expected, val, 0.1);

        val = Calculos.energiaEntreLocais(c1, u1, v1, teste1, teste2);
        assertEquals(expected, val, 0.1);

        c1.setLatA(41.167331);
        c1.setLonA(-8.688336);
        c1.setLatB(41.150004);
        c1.setLonB(-8.676257);
        c1.setDirVento(0D);
        c1.setVelVento(0D);
        c1.setCoefCinetico(0.0053);
        u1.setPeso(80f);
        u1.setVelocidadeMedia(20f);
        v1.setAreaFrontal(2);
        v1.setCoeficienteAerodinamico(0.5);
        v1.setPeso(20);
        expected = 0.15;
        val = Calculos.energiaEntreLocais(c1, u1, v1, teste1, teste2);
        assertEquals(expected, val, 0.1);

        expected = 0.021;
        val = Calculos.energiaEntreLocais(null, null, null, null, null);
        assertEquals(expected, val, 0.1);
    }

    /**
     * Test of diferencaTempoSegundos method, of class Calculos.
     */
    @Test
	@SuppressWarnings("deprecation")
    public void testDiferencaTempoSegundos() {
        System.out.println("diferencaTempoSegundos");
        Date dataI = new Date(2020,1,1);
        Date dataF = new Date(2020,1,2);
        long expResult = 86400L;
        long result = Calculos.diferencaTempoSegundos(dataI, dataF);
        assertEquals(expResult, result);
    }
}
