package lapr.project.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Transacao;
import lapr.project.model.Utilizador;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class UtilizadorControllerTest {

    public UtilizadorControllerTest() {
    }

    /**
     * Test of addUsers method, of class UtilizadorController.
     */
    @Test
    public void testAddUsers() {
        System.out.println("addUsers");
        
        UtilizadorController controller = Mockito.spy(new UtilizadorController());
        String[] utilizador1 = {"username_1", "email11", "10", "10", "10", "123456789101234567", "10", "10", "10", "M", "password"};
        String[] utilizador2 = {"username_2", "email12", "10", "10", "10", "123456789101234567", "10", "10", "10", "M", "password"};
        String[] utilizador3 = {"username_3", "email13", "10", "10", "10", "123456789101234567", "10", "10", "10", "M", "password"};
        String[] utilizador4 = {"username_4", "email14", "10", "10", "10", "123456789101234567", "10", "10", "10", "M", "password"};
        
        
        List<String[]> utilizadores = new ArrayList<>();
        utilizadores.add(utilizador1);
        utilizadores.add(utilizador2);
        utilizadores.add(utilizador3);
        utilizadores.add(utilizador4);

        Transacao t = Mockito.mock(Transacao.class);
        controller.setTransacao(t);

        when(t.addAllUsers(any())).thenReturn(false);
        assertEquals(0, controller.addUsers(utilizadores));

        when(t.addAllUsers(any())).thenReturn(true);
        assertEquals(4, controller.addUsers(utilizadores));
    }

    /**
     * Test of salvarCaminho method, of class CaminhosController.
     */
    @Test
    public void testSalvarUtilizador() {
        System.out.println("salvarUtilizador");
        UtilizadorController instance = new UtilizadorController();

        Utilizador u = Mockito.mock(Utilizador.class);
        when(u.save()).thenReturn(true);

        boolean expResult = true;
        boolean result = instance.salvarUtilizador(u);
        assertEquals(expResult, result);

        when(u.save()).thenReturn(false);
        expResult = false;
        result = instance.salvarUtilizador(u);
        assertEquals(expResult, result);
    }

    @Test
    public void registerUser() {
        UtilizadorController controller = Mockito.spy(new UtilizadorController());
        doReturn(true).when(controller).salvarUtilizador(any(Utilizador.class));
        assertEquals(1, controller.registerUser("username", "email", "password", "visaCardNumber", 0, 0, new BigDecimal("0"), "gender"));
        doReturn(false).when(controller).salvarUtilizador(any(Utilizador.class));
        assertEquals(0, controller.registerUser(null, null, null, null, 0, 0, new BigDecimal("0"), null));
        
        
    }
    
    
}
