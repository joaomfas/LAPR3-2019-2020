/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.Date;
import lapr.project.data.ViagemBD;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

/**
 *
 * @author mdias
 */
public class ViagemTest {

    Viagem v1;
    Viagem v2;
    Date dataI = Date.valueOf("2019-12-30");
    Date dataF = Date.valueOf("2019-12-31");

    @BeforeEach
    public void setUp() {
        v1 = new Viagem(1, "testeDesc", "testeID");
        v2 = new Viagem(919, "testeDesc", "testeID");
        v2.setDataHoraInicio(dataI);
        v2.setDataHoraFim(dataF);
    }

    @Test
    public void ViagemConTest() {
        Viagem v = new Viagem(1, "testeDesc", "testeID");
        assertEquals(119, v.hashCode());
        v = new Viagem();
        assertEquals(null, v.getDataHoraFim());
    }
    
    @Test
    public void ViagemConTest1() {
        Viagem v = new Viagem(1, "testDesc", "testeID", null);
        assertEquals(119, v.hashCode());
        v = new Viagem();
        assertEquals(null, v.getDataHoraFim());
    }
    
    @Test
    public void ViagemConTest2() {
        Viagem v = new Viagem(1, 1, "testeDesc", "ParkID", "ParkeID2", null, null);
        assertEquals(120, v.hashCode());
        v = new Viagem();
        assertEquals(null, v.getDataHoraFim());
    }

    /**
     * Test of getIdViagem method, of class Viagem.
     */
    @Test
    public void testGetId_viagem() {
        Integer expResult = 919;
        v2.setIdViagem(919);
        Integer result = v2.getIdViagem();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdViagem method, of class Viagem.
     */
    @Test
    public void testSetId_viagem() {
        Integer expResult = 828;
        v2.setIdViagem(expResult);
        Integer result = v2.getIdViagem();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdUtilizador method, of class Viagem.
     */
    @Test
    public void testGetId_utilizador() {
        Integer expResult1 = 1;
        Integer expResult2 = 919;
        Integer result1 = v1.getIdUtilizador();
        Integer result2 = v2.getIdUtilizador();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of setIdUtilizador method, of class Viagem.
     */
    @Test
    public void testSetId_utilizador() {
        Integer expResult1 = 8;
        Integer expResult2 = 9;
        v1.setIdUtilizador(expResult1);
        v2.setIdUtilizador(expResult2);
        Integer result1 = v1.getIdUtilizador();
        Integer result2 = v2.getIdUtilizador();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of getIdVeiculo method, of class Viagem.
     */
    @Test
    public void testGetId_veiculo() {
        String expResult1 = "testeDesc";
        String expResult2 = "testeDesc";
        String result1 = v1.getDescVeiculo();
        String result2 = v2.getDescVeiculo();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of setIdVeiculo method, of class Viagem.
     */
    @Test
    public void testSetId_veiculo() {
        String expResult1 = "tD";
        String expResult2 = "tD";
        v1.setDescVeiculo(expResult1);
        v2.setDescVeiculo(expResult2);
        String result1 = v1.getDescVeiculo();
        String result2 = v2.getDescVeiculo();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of getIdParqueInicio method, of class Viagem.
     */
    @Test
    public void testGetId_parque_inicio() {
        String expResult = "testeID";
        String result1 = v1.getIdParqueInicio();
        String result2 = v2.getIdParqueInicio();
        assertEquals(expResult, result1);
        assertEquals(expResult, result2);
    }

    /**
     * Test of setIdParqueInicio method, of class Viagem.
     */
    @Test
    public void testSetId_parque_inicio() {
        String expResult = "tI";
        v1.setIdParqueInicio(expResult);
        v2.setIdParqueInicio(expResult);
        String result1 = v1.getIdParqueInicio();
        String result2 = v2.getIdParqueInicio();
        assertEquals(expResult, result1);
        assertEquals(expResult, result2);
    }

    /**
     * Test of getIdParqueFim method, of class Viagem.
     */
    @Test
    public void testGetId_parque_fim() {
        String expResult = null;
        String result = v2.getIdParqueFim();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId_parque_fim method, of class Viagem.
     */
    @Test
    public void testSetId_parque_fim() {
        String expResult = "tI";
        v2.setIdParqueFim(expResult);
        String result = v2.getIdParqueFim();
        assertEquals(expResult, result);
    }

    /**
     * Test of getData_hora_inicio method, of class Viagem.
     */
    @Test
    public void testGetData_hora_inicio() {
        assertNull(v1.getDataHoraInicio());
        Date expResult = dataI;
        Date result = v2.getDataHoraInicio();
        assertEquals(expResult, result);
    }

    /**
     * Test of setData_hora_inicio method, of class Viagem.
     */
    @Test
    public void testSetData_hora_inicio() {
        dataI = Date.valueOf("2020-01-01");
        Date expResult = dataI;
        v2.setDataHoraInicio(dataI);
        Date result = v2.getDataHoraInicio();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDataHoraFim method, of class Viagem.
     */
    @Test
    public void testGetData_hora_fim() {
        Date expResult = dataF;
        Date result = v2.getDataHoraFim();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setData_hora_fim method, of class Viagem.
     */
    @Test
    public void testSetData_hora_fim() {
        dataF = Date.valueOf("2020-01-02");
        Date expResult = dataF;
        v2.setDataHoraFim(dataF);
        Date result = v2.getDataHoraFim();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Viagem.
     */
    @Test
    public void testHashCode() {
        int expResult1 = 119;
        int expResult2 = 119;
        int result1 = v1.hashCode();
        int result2 = v2.hashCode();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of equals method, of class Viagem.
     */
    @Test
    public void testEquals() {
        Viagem expResult1 = new Viagem(1, "testeDesc", "testeID");
        expResult1.setIdViagem(1);
        Viagem expResult2 = new Viagem(919, "testeDesc", "testeID");
        expResult2.setIdViagem(919);
        expResult2.setDataHoraInicio(dataI);
        expResult2.setDataHoraFim(dataF);
        Viagem result1 = v1;
        v1.setIdViagem(1);
        Viagem result2 = v2;
        v2.setIdViagem(919);
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        
        assertNotEquals(v1, v2);
        
        Viagem v3 = null;
        boolean expResult = false;
        boolean result = v1.equals(v3);
        assertEquals(expResult, result);
    }

    /**
     * Test of save method, of class Viagem.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testSave() {
 
        Viagem v = new Viagem(1, "1", "va");
        ViagemBD bd = Mockito.spy(new ViagemBD());

        v.setBd(bd);
        v.setIdViagem(9);

        // TESTE 1 - Viagem não está na BD ainda. Testamos p/ quando ele é adicionado
        // dizemos que quando getViagem for chamado, deve ser lançada a sua exceção.
        doThrow(IllegalArgumentException.class).when(bd).getViagemByUtilizador(v.getIdUtilizador());

        // nessa situação, deve ser chamado addViagem(). queremos que a operação seja bem sucedida
        doReturn(true).when(bd).addViagem(v);

        // então testamos o método. nessa situação, save() retorna true
        boolean result = v.save();
        assertEquals(true, result);

        // agora vamos testar quando o addViagem não funciona. save() então retorna false
        doReturn(false).when(bd).addViagem(v);
        result = v.save();
        assertEquals(false, result);

        // TESTE 2 - Viagem já está na BD. Precisamos atualizar
        // dizemos que getViagem consegue retornar a Viagem e que o update funciona
        doReturn(v).when(bd).getViagemByUtilizador(v.getIdUtilizador());
        doReturn(true).when(bd).updateViagem(v);

        // verificamos que o save() retorna true
        result = v.save();
        assertEquals(true, result);

        // quando o getViagem funciona, mas o update não, então save() retorna false
        doReturn(false).when(bd).updateViagem(v);
        result = v.save();
        assertEquals(false, result);

    }

}
