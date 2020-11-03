package lapr.project.model;

import lapr.project.data.FaturaBD;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;


public class FaturaTest {
    
    FaturaBD faturaBD;
    Fatura fatura;
    
    public FaturaTest() {
    }
        
    @BeforeEach
    public void setUp() {
        faturaBD = Mockito.spy(new FaturaBD());
        fatura= new Fatura();
        fatura.setBd(faturaBD);
    }
    /**
     * Test of getBd method, of class Fatura.
     */
    @Test
    public void testGetBd() {
        System.out.println("getBd");
        Fatura instance = new Fatura();
        instance.setBd(faturaBD);
        assertEquals(faturaBD, instance.getBd());
    }

    /**
     * Test of setBd method, of class Fatura.
     */
    @Test
    public void testSetBd() {
        System.out.println("getBd");
        Fatura instance = new Fatura();
        instance.setBd(null);
        assertEquals(null, instance.getBd());
    }

    /**
     * Test of getIdFatura method, of class Fatura.
     */
    @Test
    public void testGetIdFatura() {
        System.out.println("getIdFatura");
        Fatura instance = new Fatura();
        int expResult = 0;
        int result = instance.getIdFatura();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdFatura method, of class Fatura.
     */
    @Test
    public void testSetIdFatura() {
        System.out.println("setIdFatura");
        int idFatura = 1;
        Fatura instance = new Fatura();
        instance.setIdFatura(1);
        int result = instance.getIdFatura();
        assertEquals(1, result);
    }

    /**
     * Test of getUsername method, of class Fatura.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        Fatura instance = new Fatura();
        instance.setUsername("josh");
        String expResult = "josh";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class Fatura.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "abc";
        Fatura instance = new Fatura();
        instance.setUsername(username);
        String expResult = "abc";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMes method, of class Fatura.
     */
    @Test
    public void testGetMes() {
        System.out.println("getMes");
        Fatura instance = new Fatura();
        instance.setMes(1);
        int expResult = 1;
        int result = instance.getMes();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setMes method, of class Fatura.
     */
    @Test
    public void testSetMes() {
        System.out.println("setMes");
        Fatura instance = new Fatura();
        instance.setMes(4);
        int expResult = 4;
        int result = instance.getMes();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPontosAnteriores method, of class Fatura.
     */
    @Test
    public void testGetPontosAnteriores() {
        System.out.println("getPontosAnteriores");
        Fatura instance = new Fatura();
        int expResult = 0;
        int result = instance.getPontosAnteriores();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPontosAnteriores method, of class Fatura.
     */
    @Test
    public void testSetPontosAnteriores() {
        System.out.println("getPontosAnteriores");
        Fatura instance = new Fatura();
        instance.setPontosAnteriores(20);
        int expResult = 20;
        int result = instance.getPontosAnteriores();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPontosGanhos method, of class Fatura.
     */
    @Test
    public void testGetPontosGanhos() {
        System.out.println("getPontosGanhos");
        Fatura instance = new Fatura();
        int expResult = 0;
        int result = instance.getPontosGanhos();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPontosGanhos method, of class Fatura.
     */
    @Test
    public void testSetPontosGanhos() {
        System.out.println("getPontosGanhos");
        Fatura instance = new Fatura();
        instance.setPontosGanhos(10);
        int expResult = 10;
        int result = instance.getPontosGanhos();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPontosDescontados method, of class Fatura.
     */
    @Test
    public void testGetPontosDescontados() {
        System.out.println("getPontosDescontados");
        Fatura instance = new Fatura();
        int expResult = 0;
        int result = instance.getPontosDescontados();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPontosDescontados method, of class Fatura.
     */
    @Test
    public void testSetPontosDescontados() {
        System.out.println("getPontosDescontados");
        Fatura instance = new Fatura();
        instance.setPontosDescontados(10);
        int expResult = 10;
        int result = instance.getPontosDescontados();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPontosAtuais method, of class Fatura.
     */
    @Test
    public void testGetPontosAtuais() {
        System.out.println("getPontosAtuais");
        Fatura instance = new Fatura();
        int expResult = 0;
        int result = instance.getPontosAtuais();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPontosAtuais method, of class Fatura.
     */
    @Test
    public void testSetPontosAtuais() {
        System.out.println("getPontosAtuais");
        Fatura instance = new Fatura();
        instance.setPontosAtuais(10);
        int expResult = 10;
        int result = instance.getPontosAtuais();
        assertEquals(expResult, result);
    }

    /**
     * Test of getValorCobrado method, of class Fatura.
     */
    @Test
    public void testGetValorCobrado() {
        System.out.println("getValorCobrado");
        Fatura instance = new Fatura();
        double expResult = 0.0;
        double result = instance.getValorCobrado();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setValorCobrado method, of class Fatura.
     */
    @Test
    public void testSetValorCobrado() {
        System.out.println("getValorCobrado");
        Fatura instance = new Fatura();
        instance.setValorCobrado(20.0);
        double expResult = 20.0;
        double result = instance.getValorCobrado();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getValorPago method, of class Fatura.
     */
    @Test
    public void testGetValorPago() {
        System.out.println("getValorPago");
        Fatura instance = new Fatura();
        double expResult = 0.0;
        double result = instance.getValorPago();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setValorPago method, of class Fatura.
     */
    @Test
    public void testSetValorPago() {
        System.out.println("getValorPago");
        Fatura instance = new Fatura();
        instance.setValorPago(10);
        double expResult = 10.0;
        double result = instance.getValorPago();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of usaPontos method, of class Fatura.
     */
    @Test
    public void testUsaPontos() {
        doReturn(fatura).when(faturaBD).getFatura(anyString(), anyInt());
        doReturn(true).when(faturaBD).addFatura(any(Fatura.class));
        doReturn(true).when(faturaBD).atualizaFatura(any(Fatura.class));
        
        fatura.setPontosAtuais(20);
        fatura.setValorCobrado(7);
        
        assertEquals(false, fatura.usaPontos(30));
        assertEquals(false, fatura.usaPontos(5));
        assertEquals(true, fatura.usaPontos(10));
    }



    /**
     * Test of hashCode method, of class Fatura.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Fatura instance = new Fatura("josh", 2);
        System.out.println("Hash : " + instance.hashCode());
        int expResult = 238614883;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Fatura.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Fatura instance = new Fatura();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        obj = new Fatura("josh", 2);
        instance = new Fatura("josh", 2);
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of save method, of class Fatura.
     */
    @Test
    public void testSave() {
        doReturn(fatura).when(faturaBD).getFatura(anyString(), anyInt());
        doReturn(true).when(faturaBD).addFatura(any(Fatura.class));
        doReturn(true).when(faturaBD).atualizaFatura(any(Fatura.class));
        
        
        doThrow(IllegalArgumentException.class).when(faturaBD).getFatura(anyString(), anyInt());
        doReturn(true).when(faturaBD).addFatura(any(Fatura.class));
        assertEquals(true, fatura.save());
        
        doReturn(false).when(faturaBD).addFatura(any(Fatura.class));
        assertEquals(false, fatura.save());
        
        doReturn(fatura).when(faturaBD).getFatura(anyString(), anyInt());
        doReturn(true).when(faturaBD).atualizaFatura(any(Fatura.class));
        assertEquals(true, fatura.save());
        
        doReturn(fatura).when(faturaBD).getFatura(anyString(), anyInt());
        doReturn(false).when(faturaBD).atualizaFatura(any(Fatura.class));
        assertEquals(false, fatura.save());
    }
    
}
