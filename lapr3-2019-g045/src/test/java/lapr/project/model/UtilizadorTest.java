package lapr.project.model;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lapr.project.data.UtilizadorBD;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;


public class UtilizadorTest {
    
    private Utilizador utilizador;
    
    @BeforeEach
    public void iniciarTeste() {
        utilizador = new Utilizador(1, "username", "password", "email@alternativo.pt", "nome", "123123123123", 10, 10, 10F, "M");	
    }
    
    
    /**
     * Test of getUtilizadorByEmail method, of class Utilizador.
     */
    @Ignore
    public void testGetUtilizadorByEmail() {
        System.out.println("getUtilizadorByEmail");
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class Utilizador.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testSave() {
        
        UtilizadorBD bd = Mockito.spy(new UtilizadorBD());
        utilizador.setBd(bd);
        
        // TESTE 1 - Adicionar
	// Quando chamamos a função Utilizador.save(), a bd deve lançar exception : "utilizador não encontrado" logo tenta criar.
        doThrow(IllegalArgumentException.class).when(bd).getUtilizadorByUserName("username");
        
        // nessa situação, deve ser chamado addUtilizador(). queremos que a operação seja bem sucedida
	doReturn(true).when(bd).addUtilizador(utilizador);
        
        // então testamos o método. nessa situação, save() retorna true
        boolean result = utilizador.save();
        assertEquals(true, result);
        
        // agora vamos testar quando o addCaminho não funciona. save() então retorna false
	doReturn(false).when(bd).addUtilizador(utilizador);
        result = utilizador.save();
	assertEquals(false, result);
        
        // TESTE 2 - Utilizador já está na BD. Precisamos atualizar
	// dizemos que getUtilizadorByEmail consegue retornar o Utilizador
        doReturn(utilizador).when(bd).getUtilizadorByUserName("username");
        doReturn(false).when(bd).atualizaUtilizador(utilizador);
	result = utilizador.save();
	assertEquals(false, result);

    }

    /**
     * Test of getId_utilizador method, of class Utilizador.
     */
    @Test
    public void testGetId_utilizador() {
        System.out.println("getId_utilizador");
        long expResult = 1L;
        long result = utilizador.getIdUtilizador();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsername method, of class Utilizador.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        String expResult = "username";
        String result = utilizador.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class Utilizador.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        String expResult = "password";
        String result = utilizador.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail method, of class Utilizador.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        String expResult = "email@alternativo.pt";
        String result = utilizador.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNome method, of class Utilizador.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        String expResult = "nome";
        String result = utilizador.getNome();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCartaoCredito method, of class Utilizador.
     */
    @Test
    public void testGetCartaoCredito() {
        System.out.println("getCartaoCredito");
        String expResult = "123123123123";
        String result = utilizador.getCartaoCredito();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPeso method, of class Utilizador.
     */
    @Test
    public void testGetPeso() {
        System.out.println("getPeso");
        float expResult = 10F;
        float result = utilizador.getPeso();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getAltura method, of class Utilizador.
     */
    @Test
    public void testGetAltura() {
        System.out.println("getAltura");
        float expResult = 10F;
        float result = utilizador.getAltura();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getVelocidadeMedia method, of class Utilizador.
     */
    @Test
    public void testGetVelocidadeMedia() {
        System.out.println("getVelocidadeMedia");
        float expResult = 10F;
        float result = utilizador.getVelocidadeMedia();
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getGenero method, of class Utilizador.
     */
    @Test
    public void testGetGenero() {
        System.out.println("getGenero");
        String expResult = "M";
        String result = utilizador.getGenero();
        assertEquals(expResult, result);
    }

    
    @Test
    public void testHashCode() {
        int expResult = -458550737;
        System.out.println(utilizador.hashCode());
        int result = utilizador.hashCode();
        assertEquals(expResult, result);
    
    }
    
    
    /**
     * Test of equals method, of class Utilizador.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Utilizador instance = new Utilizador(2, "username", "password", "email@alternativo.pt", "nome", "123123123123", 10, 10, 10F, "M");
        assertEquals(utilizador, instance);
        Utilizador instance2 = new Utilizador(2, "username2", "password2", "email@alternativo.pt2", "nome2", "1231231231232", 10, 10, 10F, "M");
        assertTrue(utilizador.equals(instance2) == false);
        assertTrue(utilizador.equals(null) == false);
    }
    
    
    
    /**
     * Test of setIdUtilizador method, of class Utilizador.
     */
    @Test
    public void testSetIdUtilizador() {
        System.out.println("setIdUtilizador");
        utilizador.setIdUtilizador(1);
        assertEquals(1, utilizador.getIdUtilizador());        
    }
    /**
     * Test of setIdUtilizador method, of class Utilizador.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setIdUtilizador");
        utilizador.setUsername("disc");
        assertEquals("disc", utilizador.getUsername());        
    }
    /**
     * Test of setIdUtilizador method, of class Utilizador.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        utilizador.setPassword("qwerty");
        assertEquals("qwerty", utilizador.getPassword());        
    }
    /**
     * Test of setIdUtilizador method, of class Utilizador.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        utilizador.setEmail("john@doe.pt");
        assertEquals("john@doe.pt", utilizador.getEmail());        
    }
    /**
     * Test of setIdUtilizador method, of class Utilizador.
     */
    @Test
    public void testSetNome() {
        System.out.println("setNome");
        utilizador.setNome("John Doe");
        assertEquals("John Doe", utilizador.getNome());        
    }

    @Test
    public void testSetCartaoCredito() {
        System.out.println("setNome");
        utilizador.setCartaoCredito("123321123321212");
        assertEquals("123321123321212", utilizador.getCartaoCredito());        
    }
    
    
    @Test
    public void testSetAltura() {
        System.out.println("setAltura");
        utilizador.setAltura(1.5f);
        assertEquals(1.5f, utilizador.getAltura());        
    }
    
    @Test
    public void testSetGenero() {
        System.out.println("setGenero");
        utilizador.setGenero("M");
        assertEquals("M", utilizador.getGenero());        
    }
    
    @Test
    public void testSetPontos() {
        System.out.println("setPontos");
        utilizador.setPontos(10);
        assertEquals(10, utilizador.getPontos());        
    }
    
    @Test
    public void testGetPontos() {
        System.out.println("getPontos");
        utilizador.setPontos(20);
        assertEquals(20, utilizador.getPontos());        
    }
    
    
}
