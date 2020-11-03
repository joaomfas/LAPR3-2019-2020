/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;


import lapr.project.data.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

/**
 *
 * @author Thais Farias
 */
public class POITest {

    POI teste1;
    POI teste2;
    POI teste3;

    @Test
    public void POIConTest() {
        teste2 = new POI(40.002345, -8.450984, 6, "Ribeira");
        Double expResult = 40.002345;
        teste3 = new POI();
        assertEquals(expResult, teste2.getLatitude());
        assertEquals(null, teste3.getLatitude());
    }

    @BeforeEach
    public void setUp() {
        teste1 = new POI(41.14961, -8.610997, 4, "Aliados");

    }

    /**
     * Test of getLatitude method, of class POI.
     */
    @Test
    public void testGetLatitude() {
        Double expResult = 41.14961;
        Double result = teste1.getLatitude();
        assertEquals(expResult, result);

    }

    /**
     * Test of setLatitude method, of class POI.
     */
    @Test
    public void testSetLatitude() {
        Double expResult = 40.123654;
        teste1.setLatitude(expResult);
        Double result = teste1.getLatitude();
        assertEquals(expResult, result);

    }

    /**
     * Test of getLongitude method, of class POI.
     */
    @Test
    public void testGetLongitude() {

        Double expResult = -8.610997;
        Double result = teste1.getLongitude();
        assertEquals(expResult, result);

    }

    /**
     * Test of setLongitude method, of class POI.
     */
    @Test
    public void testSetLongitude() {

        Double expResult = -5.123654;
        teste1.setLongitude(expResult);
        Double result = teste1.getLongitude();
        assertEquals(expResult, result);
    }

    /**
     * Test of getElevacao method, of class POI.
     */
    @Test
    public void testGetElevacao() {

        Integer expResult = 4;
        Integer result = teste1.getElevacao();
        assertEquals(expResult, result);

    }

    /**
     * Test of setElevacao method, of class POI.
     */
    @Test
    public void testSetElevacao() {

        Integer expResult = 8;
        teste1.setElevacao(expResult);
        Integer result = teste1.getElevacao();
        assertEquals(expResult, result);

    }

    /**
     * Test of getDescricao method, of class POI.
     */
    @Test
    public void testGetDescricao() {

        String expResult = "Aliados";
        String result = teste1.getDescricao();
        assertEquals(expResult, result);

    }

    /**
     * Test of setDescricao method, of class POI.
     */
    @Test
    public void testSetDescricao() {

        String expResult = "Porto";
        teste1.setDescricao(expResult);
        String result = teste1.getDescricao();
        assertEquals(expResult, result);

    }

    /**
     * Test of save method, of class POI.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testSave() {
        System.out.println("save");
        POI p = new POI(1.0, 2.0, 4, "teste");
        POIBD pbd = Mockito.spy(new POIBD());
        p.setBd(pbd);

        // TESTE 1 - POI não está na BD ainda. Testamos p/ quando ele é adicionado
        // dizemos que quando getPOI for chamado, deve ser lançada a sua excessão.
        doThrow(IllegalArgumentException.class).when(pbd).getPOI(1.0, 2.0);
        // nessa situação, deve ser chamado addPOI(). queremos que a operação seja bem sucedida
        doReturn(true).when(pbd).addPOI(p);
        // então testamos o método. nessa situação, save() retorna true
        boolean result = p.save();
        assertEquals(true, result);

        // agora vamos testar quando o addPOI não funciona. save() então retorna false
        doReturn(false).when(pbd).addPOI(p);
        result = p.save();
        assertEquals(false, result);

        // TESTE 2 - POI já está na BD. Precisamos atualizar
        // dizemos que getPOI consegue retornar o poi e que o update funciona
        doReturn(p).when(pbd).getPOI(1.23d, 1.23d);
        // verificamos que o save() retorna true
        result = p.save();
        assertEquals(false, result);
        
        // TESTE 3 - POI é null e o save() retorna false
        doReturn(null).when(pbd).getPOI(1.0, 2.0);
        result = p.save();
        assertEquals(false, result);
    }

    /**
     * Test of remove method, of class POI.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testRemove() {
        System.out.println("remove");
        POIBD bd = Mockito.spy(new POIBD());
        POI poi = new POI(1.0, 2.0, 4, "teste");
        poi.setBd(bd);

        // TESTE 1 - POI não está na BD. Testamos p/ quando ele é removido
        // dizemos que quando removePOI for chamado, deve ser lançada a sua excessão.
        doThrow(IllegalArgumentException.class).when(bd).removePOI(poi);
        boolean result = poi.remove();
        assertEquals(false, result);

        // Teste 2 - POI removido sem sucesso
        doReturn(false).when(bd).removePOI(poi);
        result = poi.remove();
        assertEquals(false, result);

        // Teste 3 - POI removido com sucesso
        doReturn(true).when(bd).removePOI(poi);
        result = poi.remove();
        assertEquals(true, result);
    }

    /**
     * Test of equals method, of class POI.
     */
    @Test
    public void testEquals() {
        POI result1 = new POI(41.14961, -8.610997, 4, "Aliados");
        assertEquals(teste1, result1);
        POI result2 = new POI();
        assertNotEquals(teste1, result2);
    }

    /**
     * Test of equals method, of class POI.
     */
    @Test
    public void testHashNull() {
        int expResult1 = 750104718;
        int result1 = teste1.hashCode();
        assertEquals(expResult1, result1);
    }

    /**
     * Test of toString method, of class Veiculo.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        teste1.setDescricao("desc");
        assertEquals("desc", teste1.toString());
        teste1.setDescricao(null);
        assertEquals("", teste1.toString());
    }
}
