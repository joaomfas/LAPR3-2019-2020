/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.data.ParqueBD;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

/**
 *
 * @author Thais Farias
 */
public class ParqueTest {

    Parque teste1;
    Parque teste2;
    Parque teste3;
    Parque teste4;

    @BeforeEach
    public void setUp() {
        teste1 = new Parque("testeID", 12.123, 12.123, 10, "testeDesc", 10, 10, 220, 16);
        teste2 = new Parque("testeID1", 12.123, 12.123, 10, "testeDesc", 10, 10, 220, 16);
    }

    @Test
    public void ParqueConTest() {
        teste3 = new Parque();
        assertEquals(null, teste3.getDescricao());
        teste4 = new Parque("testeID4", 12.554, 8.123, 10, "teste Parque", 10, 10, 220, 16);
        assertEquals("teste Parque", teste4.getDescricao());
    }

    /**
     * Test of getIdParque method, of class Parque.
     */
    @Test
    public void testGetIdParque() {
        String expResult = "testeID";
        teste1.setIdParque("testeID");
        String result = teste1.getIdParque();
        assertEquals(expResult, result);

    }

    /**
     * Test of setId_parque method, of class Parque.
     */
    @Test
    public void testSetId_parque() {
        String expResult = "testeID";
        teste2.setIdParque(expResult);
        String result = teste2.getIdParque();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLatitude method, of class Parque.
     */
    @Test
    public void testGetLatitude() {
        double expResult = 12.123;
        double result = teste1.getLatitude();

        assertEquals(expResult, result);

    }

    /**
     * Test of setLatitude method, of class Parque.
     */
    @Test
    public void testSetLatitude() {
        double expResult = 5.144545;
        teste2.setLatitude(expResult);
        double result = teste2.getLatitude();
        assertEquals(expResult, result);

    }

    /**
     * Test of getLongitude method, of class Parque.
     */
    @Test
    public void testGetLongitude() {
        double expResult = 12.123;
        double result = teste2.getLongitude();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setLongitude method, of class Parque.
     */
    @Test
    public void testSetLongitude() {

        double expResult = 11.345678;
        teste2.setLongitude(expResult);
        double result = teste2.getLongitude();
        assertEquals(expResult, result);
    }

    /**
     * Test of getElevacao method, of class Parque.
     */
    @Test
    public void testGetElevacao() {

        Integer expResult = 15;
        teste1.setElevacao(expResult);
        Integer result = teste1.getElevacao();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAtivo method, of class Parque.
     */
    @Test
    public void testGetAtivo() {
        Integer expResult = 0;
        teste1.removerParque();
        Integer result = teste1.getAtivo();
        assertEquals(expResult, result);
    }

    /**
     * Test of setElevacao method, of class Parque.
     */
    @Test
    public void testSetElevacao() {
        Integer expResult = 41;
        teste1.setElevacao(expResult);
        Integer result = teste1.getElevacao();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescricao method, of class Parque.
     */
    @Test
    public void testGetDescricao() {
        String expResult = "Parque Trindade";
        String result = teste2.getDescricao();
        assertEquals(false, result.equals(expResult));

    }

    /**
     * Test of setDescricao method, of class Parque.
     */
    @Test
    public void testSetDescricao() {
        String expResult = "Parque Trindade";

        teste2.setDescricao(expResult);
        String result = teste2.getDescricao();
        assertTrue(result.equals(expResult));

    }

    /**
     * Test of getLot_bike method, of class Parque.
     */
    @Test
    public void testGetLot_bike() {
        Integer expResult = 120;
        Integer result = teste2.getLotBike();
        assertEquals(false, expResult.equals(result));

    }

    /**
     * Test of setLot_bike method, of class Parque.
     */
    @Test
    public void testSetLotBike() {

        Integer expResult = 15;
        teste1.setLotBike(expResult);
        Integer result = teste1.getLotBike();
        assertEquals(expResult, result);

    }

    /**
     * Test of getLot_scooter method, of class Parque.
     */
    @Test
    public void testGetLotScooter() {
        Integer expResult = 40;
        Integer result = teste2.getLotScooter();
        assertEquals(false, expResult.equals(result));

    }

    /**
     * Test of setLot_scooter method, of class Parque.
     */
    @Test
    public void testSetLot_scooter() {
        Integer expResult = 20;
        teste1.setLotScooter(expResult);
        Integer result = teste1.getLotScooter();
        assertEquals(expResult, result);

    }

    /**
     * Test of getVoltagem method, of class Parque.
     */
    @Test
    public void testGetVoltagem() {

        Integer expResult = 220;
        Integer result = teste1.getVoltagem();
        assertEquals(expResult, result);

    }

    /**
     * Test of setVoltagem method, of class Parque.
     */
    @Test
    public void testSetVoltagem() {
        Integer expResult = 15;
        teste2.setVoltagem(expResult);
        Integer result = teste2.getVoltagem();
        assertEquals(expResult, result);

    }

    /**
     * Test of getCorrente method, of class Parque.
     */
    @Test
    public void testGetCorrente() {
        Integer expResult = 16;
        Integer result = teste1.getCorrente();
        assertEquals(expResult, result);

    }

    /**
     * Test of setCorrente method, of class Parque.
     */
    @Test
    public void testSetCorrente() {
        Integer expResult = 30;
        teste2.setCorrente(expResult);
        Integer result = teste2.getCorrente();
        assertEquals(expResult, result);
    }

       /**
     * Test of setAtivo method, of class Parque.
     */
    @Test
    public void testSetAtivo() {
        Integer expResult = 1;
        teste2.setAtivo(expResult);
        Integer result = teste2.getAtivo();
        assertEquals(expResult, result);
    }
    /**
     * Test of hashCode method, of class Parque.
     */
    @Test
    public void testHashCode() {
        int expResult = -1422450537;
        int result = teste1.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Parque.
     */
    @Test
    public void testEquals() {

        boolean expResult = false;
        boolean result = teste1.equals(teste2);
        assertEquals(expResult, result);
        assertEquals(false, teste1.equals(null));

    }

    /**
     * Test of  toString method, of class Parque.
     */
    @Test
    public void testToString() {

        String expResult = teste1.getDescricao();
        String result = teste1.toString();
        assertEquals(expResult, result);

    }

    /**
     * Test of save method, of class Parque.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testSave() {

        ParqueBD bd = Mockito.spy(new ParqueBD());
        teste1.setBd(bd);

        // TESTE 1 - Caminho não está na BD ainda. Testamos p/ quando ele é adicionado
        // dizemos que quando getCaminho for chamado, deve ser lançada a sua excessão.
        doThrow(IllegalArgumentException.class).when(bd).getParque("testeID");

        // nessa situação, deve ser chamado addCaminho(). queremos que a operação seja bem sucedida
        doReturn(true).when(bd).addParque(teste1);

        // então testamos o método. nessa situação, save() retorna true
        boolean result = teste1.save();
        assertEquals(true, result);

        // agora vamos testar quando o addCaminho não funciona. save() então retorna false
        doReturn(false).when(bd).addParque(teste1);
        result = teste1.save();
        assertEquals(false, result);

        // TESTE 2 - Caminho já está na BD. Precisamos atualizar
        // dizemos que getCaminho consegue retornar o Caminho e que o update funciona
        doReturn(teste1).when(bd).getParque("testeID");
        doReturn(true).when(bd).atualizaParque(teste1);

        // verificamos que o save() retorna true
        result = teste1.save();
        assertEquals(true, result);

        // quando o getCaminho funciona, mas o update não, então save() retorna false
        doReturn(false).when(bd).atualizaParque(teste1);
        result = teste1.save();
        assertEquals(false, result);

    }

    /**
     * Test of getPotenciaParque method, of class Parque.
     */
    @Test
    public void testGetPotenciaParque() {
        System.out.println("getPotenciaParque");
        Double expResult = 3520.0;
        Double result = teste1.getPotenciaParque();
        assertEquals(expResult, result);
    }
}
