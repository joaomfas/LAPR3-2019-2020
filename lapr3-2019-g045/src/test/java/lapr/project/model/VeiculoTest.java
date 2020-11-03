/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.data.*;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

/**
 *
 * @author mdias
 */
public class VeiculoTest {

    Bicicleta b1;
    Bicicleta b2;
    Scooter s1;
    Scooter s2;

    @BeforeEach
    public void setUp() {
        b1 = new Bicicleta(15, "testeID", "bicicleta 1", 10, 0.1, 0.3);
        b2 = new Bicicleta(17, "testeID", "bicicleta 2", 12, 0.15, 0.35);
        s1 = new Scooter(500D, 400, "city", 200, "testeID", "scooter 1", 18, 0.3, 0.5);
        s2 = new Scooter(700D, 350, "off-road", 200, "testeID", "scooter 2", 17, 0.25, 0.4);
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void VeiculoConTest() {
        Bicicleta b1 = new Bicicleta(15, "testeID", "bicicleta 1", 10, 0.1, 0.3);
        Bicicleta b2 = new Bicicleta();
        assertEquals(15, b1.getTamanho());
        assertEquals(null, b2.getTamanho());
        Scooter s1 = new Scooter(500D, 400, "city", 200, "testeID", "scooter 1", 18, 0.3, 0.5);
        Scooter s2 = new Scooter();
        assertEquals("city", s1.getTipoScooter());
        assertEquals(null, s2.getTipoScooter());
    }

    /**
     * Test of getId_parque method, of class Veiculo.
     */
    @Test
    public void testGetId_parque() {
        String expResult = "testeID";
        String result1 = b1.getIdParque();
        String result2 = b2.getIdParque();
        String result3 = s1.getIdParque();
        String result4 = s2.getIdParque();
        assertEquals(expResult, result1);
        assertEquals(expResult, result2);
        assertEquals(expResult, result3);
        assertEquals(expResult, result4);
    }

    /**
     * Test of setId_parque method, of class Veiculo.
     */
    @Test
    public void testSetId_parque() {
        String expResult = "id";
        b1.setIdParque(expResult);
        b2.setIdParque(expResult);
        s1.setIdParque(expResult);
        s2.setIdParque(expResult);
        String result1 = b1.getIdParque();
        String result2 = b2.getIdParque();
        String result3 = s1.getIdParque();
        String result4 = s2.getIdParque();
        assertEquals(expResult, result1);
        assertEquals(expResult, result2);
        assertEquals(expResult, result3);
        assertEquals(expResult, result4);
    }

    /**
     * Test of getDescricao method, of class Veiculo.
     */
    @Test
    public void testGetDescricao() {
        String expResult1 = "bicicleta 1";
        String expResult2 = "bicicleta 2";
        String expResult3 = "scooter 1";
        String expResult4 = "scooter 2";
        String result1 = b1.getDescricao();
        String result2 = b2.getDescricao();
        String result3 = s1.getDescricao();
        String result4 = s2.getDescricao();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResult4, result4);

    }

    /**
     * Test of setDescricao method, of class Veiculo.
     */
    @Test
    public void testSetDescricao() {
        String expResult1 = "bike 1";
        String expResult2 = "bike 2";
        String expResult3 = "moto 1";
        String expResult4 = "moto 2";
        b1.setDescricao(expResult1);
        b2.setDescricao(expResult2);
        s1.setDescricao(expResult3);
        s2.setDescricao(expResult4);
        String result1 = b1.getDescricao();
        String result2 = b2.getDescricao();
        String result3 = s1.getDescricao();
        String result4 = s2.getDescricao();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResult4, result4);
    }

    /**
     * Test of getPeso method, of class Veiculo.
     */
    @Test
    public void testGetPeso() {
        int expResult1 = 10;
        int expResult2 = 12;
        int expResult3 = 18;
        int expResult4 = 17;
        int result1 = b1.getPeso();
        int result2 = b2.getPeso();
        int result3 = s1.getPeso();
        int result4 = s2.getPeso();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResult4, result4);
    }

    /**
     * Test of setPeso method, of class Veiculo.
     */
    @Test
    public void testSetPeso() {
        int expResult1 = 100;
        int expResult2 = 120;
        int expResult3 = 180;
        int expResult4 = 170;
        b1.setPeso(expResult1);
        b2.setPeso(expResult2);
        s1.setPeso(expResult3);
        s2.setPeso(expResult4);
        int result1 = b1.getPeso();
        int result2 = b2.getPeso();
        int result3 = s1.getPeso();
        int result4 = s2.getPeso();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResult4, result4);
    }

    /**
     * Test of getAreaFrontal method, of class Veiculo.
     */
    @Test
    public void testGetAreaFrontal() {
        double expResult1 = 0.1;
        double expResult2 = 0.15;
        double expResult3 = 0.3;
        double expResult4 = 0.25;
        double result1 = b1.getAreaFrontal();
        double result2 = b2.getAreaFrontal();
        double result3 = s1.getAreaFrontal();
        double result4 = s2.getAreaFrontal();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResult4, result4);
    }

    /**
     * Test of setAreaFrontal method, of class Veiculo.
     */
    @Test
    public void testSetAreaFrontal() {
        double expResult1 = 0.3;
        double expResult2 = 0.35;
        double expResult3 = 0.4;
        double expResult4 = 0.35;
        b1.setAreaFrontal(expResult1);
        b2.setAreaFrontal(expResult2);
        s1.setAreaFrontal(expResult3);
        s2.setAreaFrontal(expResult4);
        double result1 = b1.getAreaFrontal();
        double result2 = b2.getAreaFrontal();
        double result3 = s1.getAreaFrontal();
        double result4 = s2.getAreaFrontal();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResult4, result4);
    }

    /**
     * Test of getCoeficienteAerodinamico method, of class Veiculo.
     */
    @Test
    public void testGetCoeficienteAerodinamico() {
        double expResult1 = 0.3;
        double expResult2 = 0.35;
        double expResult3 = 0.5;
        double expResult4 = 0.4;
        double result1 = b1.getCoeficienteAerodinamico();
        double result2 = b2.getCoeficienteAerodinamico();
        double result3 = s1.getCoeficienteAerodinamico();
        double result4 = s2.getCoeficienteAerodinamico();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResult4, result4);
    }

    /**
     * Test of setCoeficienteAerodinamico method, of class Veiculo.
     */
    @Test
    public void testSetCoeficienteAerodinamico() {
        double expResult1 = 0.1;
        double expResult2 = 0.2;
        double expResult3 = 0.3;
        double expResult4 = 0.4;
        b1.setCoeficienteAerodinamico(expResult1);
        b2.setCoeficienteAerodinamico(expResult2);
        s1.setCoeficienteAerodinamico(expResult3);
        s2.setCoeficienteAerodinamico(expResult4);
        double result1 = b1.getCoeficienteAerodinamico();
        double result2 = b2.getCoeficienteAerodinamico();
        double result3 = s1.getCoeficienteAerodinamico();
        double result4 = s2.getCoeficienteAerodinamico();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResult4, result4);
    }
    
    /**
     * Test of getRemovido method, of class Veiculo.
     */
    @Test
    public void testGetRemovido() {
        b1.setRemovido(true);
        b2.setRemovido(false);
        Boolean expResult1 = true;
        Boolean expResult2 = false;
        Boolean result1 = b1.getRemovido();
        Boolean result2 = b2.getRemovido();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }
    
    /**
     * Test of setRemovido method, of class Veiculo.
     */
    @Test
    public void testSetRemovido() {
        b1.setRemovido(true);
        b2.setRemovido(true);
        Boolean expResult1 = true;
        Boolean expResult2 = true;
        Boolean result1 = b1.getRemovido();
        Boolean result2 = b2.getRemovido();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }
    
    
    

    /**
     * Test of equals method, of class Veiculo.
     */
    @Test
    public void testEquals() {
        Veiculo expResult1 = new Bicicleta(15, "testeID", "bicicleta 1", 10, 0.1, 0.3);
        Veiculo expResult2 = new Bicicleta(17, "testeID", "bicicleta 2", 12, 0.15, 0.35);
        Veiculo expResult3 = new Scooter(500D, 400, "city", 200, "testeID", "scooter 1", 18, 0.3, 0.5);
        Veiculo expResult4 = new Scooter(700D, 350, "off-road", 200, "testeID", "scooter 2", 17, 0.25, 0.4);
        Veiculo result1 = b1;
        Veiculo result2 = b2;
        Veiculo result3 = s1;
        Veiculo result4 = s2;
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResult4, result4);
        assertNotEquals(b1, s1);
    }

    /**
     * Test of hashCode method, of class Veiculo.
     */
    @Test
    public void testHashCode() {
        int expResult1 = 1452796220;
        int result1 = b1.hashCode();
        assertEquals(expResult1, result1);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSave() {
        
        Veiculo c = new Bicicleta(Integer.MAX_VALUE, "testeID", "teste", Integer.SIZE, Double.MIN_NORMAL, Double.POSITIVE_INFINITY);

        VeiculoBD bd = Mockito.spy(new VeiculoBD());
        c.setBd(bd);

        // TESTE 1 - Caminho não está na BD ainda. Testamos p/ quando ele é adicionado
        // dizemos que quando getVeiculo for chamado, deve ser lançada a sua excessão.
        doThrow(IllegalArgumentException.class).when(bd).getVeiculo("teste");

        // nessa situação, deve ser chamado addVeiculo(). queremos que a operação seja bem sucedida
        doReturn(true).when(bd).addVeiculo(c);

        // então testamos o método. nessa situação, save() retorna true
        boolean result = c.save();
        assertEquals(true, result);

        // agora vamos testar quando o addVeiculo não funciona. save() então retorna false
        doReturn(false).when(bd).addVeiculo(c);
        result = c.save();
        assertEquals(false, result);

        // TESTE 2 - Veiculo já está na BD. Precisamos atualizar
        // dizemos que getVEiculo consegue retornar o Caminho e que o update funciona
        doReturn(c).when(bd).getVeiculo("teste");
        doReturn(true).when(bd).updateVeiculo(c);

        // verificamos que o save() retorna true
        result = c.save();
        assertEquals(true, result);

        // quando o getVeiculo funciona, mas o update não, então save() retorna false
        doReturn(false).when(bd).updateVeiculo(c);
        result = c.save();
        assertEquals(false, result);
    }

    /**
     * Test of toString method, of class Veiculo.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        s1.setDescricao("desc");
        assertEquals("desc", s1.toString());
        s1.setDescricao(null);
        assertEquals("", s1.toString());
    }
}
