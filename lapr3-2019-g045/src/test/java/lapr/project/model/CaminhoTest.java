package lapr.project.model;

import lapr.project.data.CaminhoBD;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class CaminhoTest {

    Caminho c;

    @BeforeEach
    public void iniciarTeste() {
        c = new Caminho(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0);
    }

    /**
     * Test of save method, of class Caminho.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testSave() {
        System.out.println("save");
        CaminhoBD bd = Mockito.spy(new CaminhoBD());
        c.setBd(bd);

        // TESTE 1 - Caminho não está na BD ainda. Testamos p/ quando ele é adicionado
        // dizemos que quando getCaminho for chamado, deve ser lançada a sua excessão.
        doThrow(IllegalArgumentException.class).when(bd).getCaminho(1.0, 2.0, 3.0, 4.0);

        // nessa situação, deve ser chamado addCaminho(). queremos que a operação seja bem sucedida
        doReturn(true).when(bd).addCaminho(c);

		// então testamos o método. nessa situação, save() retorna true
        boolean result = c.save();
        assertEquals(true, result);
		
        // agora vamos testar quando o addCaminho não funciona. save() então retorna false
        doReturn(false).when(bd).addCaminho(c);
        result = c.save();
        assertEquals(false, result);
		
        // TESTE 2 - Caminho já está na BD. Não pode adicionar.
        // dizemos que o save não funciona
        doReturn(c).when(bd).getCaminho(1.0, 2.0, 3.0, 4.0);
		doReturn(true).when(bd).updateCaminho(c);

        // verificamos que o save() retorna true
        result = c.save();
        assertEquals(true, result);

        // quando o getVeiculo funciona, mas o update não, então save() retorna false
        doReturn(false).when(bd).updateCaminho(c);
        result = c.save();
        assertEquals(false, result);

    }

    @Test
    @SuppressWarnings("unchecked")
    public void testDelete() {
        System.out.println("delete");

        CaminhoBD bd = Mockito.spy(new CaminhoBD());

        Caminho c = new Caminho(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0);

        c.setBd(bd);

        doThrow(IllegalArgumentException.class).when(bd).removeCaminho(c);

        boolean result = c.delete();

        assertEquals(false, result);

        doReturn(false).when(bd).removeCaminho(c);

        result = c.delete();

        assertEquals(false, result);

        doReturn(true).when(bd).removeCaminho(c);

        result = c.delete();

        assertEquals(true, result);

    }

    @Test
    public void constructorTest() {
        System.out.println("construtor");
        c = new Caminho(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0);
        assertEquals(-846140473, c.hashCode());
    }

    /**
     * Test of getLatA method, of class Caminho.
     */
    @Test
    public void testGetLatA() {
        System.out.println("getLatA");
        Caminho instance = c;
        double expResult = 1.0;
        double result = instance.getLatA();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setLatA method, of class Caminho.
     */
    @Test
    public void testSetLatA() {
        System.out.println("setLatA");
        double latA = 2.0;
        Caminho instance = c;
        instance.setLatA(latA);
        assertEquals(2.0, c.getLatA(), 0.0);
    }

    /**
     * Test of getLonA method, of class Caminho.
     */
    @Test
    public void testGetLonA() {
        System.out.println("getLonA");
        Caminho instance = c;
        double expResult = 2.0;
        double result = instance.getLonA();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setLonA method, of class Caminho.
     */
    @Test
    public void testSetLonA() {
        System.out.println("setLonA");
        double lonA = 2.0;
        Caminho instance = c;
        instance.setLonA(lonA);
        assertEquals(2.0, c.getLonA(), 0.0);
    }

    /**
     * Test of getLatB method, of class Caminho.
     */
    @Test
    public void testGetLatB() {
        System.out.println("getLatB");
        Caminho instance = c;
        double expResult = 3.0;
        double result = instance.getLatB();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setLatB method, of class Caminho.
     */
    @Test
    public void testSetLatB() {
        System.out.println("setLatB");
        double latB = 2.0;
        Caminho instance = c;
        instance.setLatB(latB);
        assertEquals(2.0, c.getLatB(), 0.0);
    }

    /**
     * Test of getLonB method, of class Caminho.
     */
    @Test
    public void testGetLonB() {
        System.out.println("getLonB");
        Caminho instance = c;
        double expResult = 4.0;
        double result = instance.getLonB();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setLonB method, of class Caminho.
     */
    @Test
    public void testSetLonB() {
        System.out.println("setLonB");
        double lonB = 2.0;
        Caminho instance = c;
        instance.setLonB(lonB);
        assertEquals(2.0, instance.getLonB(), 0.0);
    }

    /**
     * Test of getCoefCinetico method, of class Caminho.
     */
    @Test
    public void testGetCoefCinetico() {
        System.out.println("getCoefCinetico");
        Caminho instance = c;
        double expResult = 5.0;
        double result = instance.getCoefCinetico();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setCoefCinetico method, of class Caminho.
     */
    @Test
    public void testSetCoefCinetico() {
        System.out.println("setCoefCinetico");
        double coefCinetico = 2.0;
        Caminho instance = c;
        instance.setCoefCinetico(coefCinetico);
        assertEquals(2.0, c.getCoefCinetico(), 0.0);
    }

    /**
     * Test of getDirVento method, of class Caminho.
     */
    @Test
    public void testGetDirVento() {
        System.out.println("getDirVento");
        Caminho instance = c;
        double expResult = 6.0;
        double result = instance.getDirVento();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setDirVento method, of class Caminho.
     */
    @Test
    public void testSetDirVento() {
        System.out.println("setDirVento");
        double dirVento = 2.0;
        Caminho instance = c;
        instance.setDirVento(dirVento);
        assertEquals(2.0, c.getDirVento(), 0.0);
    }

    /**
     * Test of getVelVento method, of class Caminho.
     */
    @Test
    public void testGetVelVento() {
        System.out.println("getVelVento");
        Caminho instance = c;
        double expResult = 7.0;
        double result = instance.getVelVento();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setVelVento method, of class Caminho.
     */
    @Test
    public void testSetVelVento() {
        System.out.println("setVelVento");
        double velVento = 2.0;
        Caminho instance = c;
        instance.setVelVento(velVento);
        assertEquals(2.0, c.getVelVento(), 0.0);
    }

    /**
     * Test of equals method, of class Caminho.
     */
    @Test
    public void testEquals() {
        Caminho c2 = new Caminho(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0);
        assertEquals(c, c2);
    }

    /**
     * Test of equals method, of class Caminho.
     */
    @Test
    public void testSetBd() {
        CaminhoBD bd = Mockito.spy(new CaminhoBD());
        c.setBd(bd);
    }

    /**
     * Test of equals method, of class Caminho.
     */
    @Test
    public void testHashNull() {
        assertEquals(false, c.equals(null));
        Caminho c2 = new Caminho(1.0, null, null, null, null, null, null);
        assertEquals(false, c.equals(c2));
    }

}
