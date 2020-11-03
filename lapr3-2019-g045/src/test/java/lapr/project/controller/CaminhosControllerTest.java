package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class CaminhosControllerTest {

    public CaminhosControllerTest() {

    }

    /**
     * Test of adicionarCaminhos method, of class CaminhosController.
     */
    @Test
    public void testAdicionarCaminhos() {
        System.out.println("adicionarCaminhos");
        CaminhosController controller = Mockito.spy(new CaminhosController());
        String[] caminho1 = {"1", "1", "1", "1", "1", "1", "1"};
        String[] caminho2 = {"2", "2", "2", "2", "2", "2", "2"};
        String[] caminho3 = {"3", "3", "3", "3", "3", "3", "3"};
        List<String[]> paths = new ArrayList<>();
        paths.add(caminho1);
        paths.add(caminho2);
        paths.add(caminho3);

        Transacao t = Mockito.mock(Transacao.class);
        controller.setTransacao(t);

        // teste com os caminhos falhando ao serem adicionados
        when(t.addAllCaminhos(any())).thenReturn(false);
        assertEquals(0, controller.adicionarCaminhos(paths));

        // teste com os caminhos sendo adicionados	 com sucesso
        when(t.addAllCaminhos(any())).thenReturn(true);
        assertEquals(3, controller.adicionarCaminhos(paths));
    }

    /**
     * Test of salvarCaminho method, of class CaminhosController.
     */
    @Test
    public void testSalvarCaminho() {
        System.out.println("salvarCaminho");
        CaminhosController instance = new CaminhosController();

        Caminho c = Mockito.mock(Caminho.class);
        when(c.save()).thenReturn(true);

        boolean expResult = true;
        boolean result = instance.salvarCaminho(c);
        assertEquals(expResult, result);

        when(c.save()).thenReturn(false);
        expResult = false;
        result = instance.salvarCaminho(c);
        assertEquals(expResult, result);
    }

    @Test
    public void testRemoverCaminho_Caminho() {
        System.out.println("removerCaminho");

        CaminhosController controller = Mockito.spy(new CaminhosController());

        Pesquisa p = Mockito.spy(new Pesquisa());

        controller.p = p;

        Caminho c = new Caminho(2.0, 2.0, 3.0, 3.0, 1.0, 1.0, 1.0);

        doReturn(c).when(p).getCaminho(2.0, 2.0, 3.0, 3.0);

        doReturn(true).when(controller).removerCaminho(c);

        boolean result = controller.removerCaminho(2.0, 2.0, 3.0, 3.0);

        assertEquals(true, result);

        doReturn(false).when(controller).removerCaminho(c);

        result = controller.removerCaminho(2.0, 2.0, 3.0, 3.0);

        assertEquals(false, result);

        doThrow(Exception.class).when(controller).removerCaminho(c);

        result = controller.removerCaminho(2.0, 2.0, 3.0, 3.0);

        assertEquals(false, result);

        Caminho c2 = Mockito.mock(Caminho.class);
        when(c2.delete()).thenReturn(true);
        result = controller.removerCaminho(c2);
        assertEquals(true, result);

        c2 = Mockito.mock(Caminho.class);
        when(c2.delete()).thenReturn(false);
        result = controller.removerCaminho(c2);
        assertEquals(false, result);

    }

    /**
     * Test of removerCaminho method, of class CaminhosController.
     */
    @Test
    public void testRemoverCaminho_4args() {
        System.out.println("removerCaminho");

        CaminhosController controller = Mockito.spy(new CaminhosController());
        Pesquisa p = Mockito.spy(new Pesquisa());

        double lat1 = 2.0;
        double lon1 = 2.0;
        double lat2 = 3.0;
        double lon2 = 3.0;

        controller.p = p;

        Caminho c = new Caminho(2.0, 2.0, 3.0, 3.0, 1.0, 1.0, 1.0);

        doReturn(c).when(p).getCaminho(2.0, 2.0, 3.0, 3.0);

        doReturn(true).when(controller).removerCaminho(lat1, lon1, lat2, lon2);

        boolean result = controller.removerCaminho(2.0, 2.0, 3.0, 3.0);

        assertEquals(true, result);
    }

    /**
     * Test of atualizarInfoVento method, of class CaminhosController.
     */
    @Test
    public void testAtualizarInfoVento() {
        System.out.println("atualizarInfoVento");
        double lat1 = 2.0;
        double lon1 = 2.0;
        double lat2 = 3.0;
        double lon2 = 3.0;
        double velVento = 0.0;
        double dirVento = 0.0;

        CaminhosController controller = Mockito.spy(new CaminhosController());
        Pesquisa p = Mockito.spy(new Pesquisa());

        controller.p = p;
        Caminho c = new Caminho(2.0, 2.0, 3.0, 3.0, 1.0, 1.0, 1.0);

        // retorna parque e atualiza com sucesso
        doReturn(c).when(p).getCaminho(2.0, 2.0, 3.0, 3.0);
        doReturn(true).when(controller).salvarCaminho(c);
        assertTrue(controller.atualizarInfoVento(lat1, lon1, lat2, lon2, velVento, dirVento));

        //parque não existe
        doReturn(null).when(p).getCaminho(1.0, 1.0, 1.0, 1.0);
        assertFalse(controller.atualizarInfoVento(1.0, 1.0, 1.0, 1.0, velVento, dirVento));

        // retorna parque mas não consegue atualizar
        doReturn(c).when(p).getCaminho(2.0, 2.0, 3.0, 3.0);
        doReturn(false).when(controller).salvarCaminho(c);
        assertFalse(controller.atualizarInfoVento(lat1, lon1, lat2, lon2, velVento, dirVento));
        
        assertEquals(0.0, c.getVelVento());
        assertEquals(0.0, c.getDirVento());
    }

    /**
     * Test of setTransacao method, of class CaminhosController.
     */
    @Test
    public void testSetTransacao() {
        System.out.println("setTransacao");
        Transacao t = new Transacao();
        CaminhosController instance = new CaminhosController();
        instance.setTransacao(t);
    }
}
