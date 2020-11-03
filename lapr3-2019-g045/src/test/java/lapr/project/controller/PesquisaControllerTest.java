package lapr.project.controller;

import java.util.ArrayList;
import lapr.project.model.*;
import java.util.List;
import lapr.project.utils.Calculos;
import org.junit.After;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class PesquisaControllerTest {

    PesquisaController c;
    Pesquisa p;

    @BeforeEach
    public void init() {
        c = new PesquisaController();
        p = Mockito.mock(Pesquisa.class);
        c.setP(p);
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of parquesMaisProximos method, of class PesquisaController.
     */
    @Test
    public void testParquesMaisProximos() {
        System.out.println("parquesMaisProximos");
        String expResult1 = "41.39021;2.15401;903213"; // Barcelona
        String expResult2 = "48.86472;2.34901;1215069"; // Paris
        //String expResult3 = "55.751244;37.618420;3690000"; // Moscow
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add(expResult1);
        expResult.add(expResult2);
        //expResult.add(expResult3);

        Parque p1 = new Parque("A", 41.390205, 2.154007, 0, "", 0, 0, 0, 0); // Barcelona
        Parque p2 = new Parque("B", 48.864716, 2.349014, 0, "", 0, 0, 0, 0); // Paris
        Parque p3 = new Parque("C", 55.751244, 37.61842, 0, "", 0, 0, 0, 0); // Moscow
        List<Parque> listaTeste = new ArrayList<>(); // Unordered list
        listaTeste.add(p2);
        listaTeste.add(p3);
        listaTeste.add(p1);

        when(p.getParques()).thenReturn(listaTeste);
        List<String> result = c.parquesMaisProximos(41.1622023, -8.6569733, 2000); //Porto
        assertEquals(expResult, result);
		
        result = c.parquesMaisProximos(41.1622023, -8.6569733); //Porto
        assertEquals(new ArrayList<>(), result);
		
    }

    /**
     * Test of bicicletasNumParque method, of class PesquisaController.
     */
    @Test
    public void testBicicletasNumParque_id() {
        System.out.println("bicicletasNumParque");
        Bicicleta b1 = new Bicicleta(16, "testeParque", "A", 10, 2.2, 0.2);
        Bicicleta b2 = new Bicicleta(16, "testeParque", "B", 10, 2.2, 0.2);
        List<Bicicleta> lista = new ArrayList<>();
        lista.add(b1);
        lista.add(b2);

        when(p.getBicicletasNumParque("testeParque")).thenReturn(lista);
        List<String> result = c.bicicletasNumParque("testeParque");
        List<String> expResult = new ArrayList<>();
        expResult.add("A;16");
        expResult.add("B;16");
        assertEquals(expResult, result);
    }

    /**
     * Test of bicicletasNumParque method, of class PesquisaController.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testBicicletasNumParque_coordenadas() {
        System.out.println("bicicletasNumParque");
        Bicicleta b1 = new Bicicleta(16, "testeParque", "A", 10, 2.0, 0.2);
        Bicicleta b2 = new Bicicleta(16, "testeParque", "B", 10, 2.0, 0.2);
        List<Bicicleta> lista = new ArrayList<>();
        lista.add(b1);
        lista.add(b2);

        when(p.getBicicletasNumParque("testeParque")).thenReturn(lista);

        Parque parque = new Parque();
        parque.setIdParque("testeParque");

        // testando para quando é encontrado o parque
        when(p.getParque(10.0, 10.0)).thenReturn(parque);

        List<String> expResult = new ArrayList<>();
        expResult.add("A;16");
        expResult.add("B;16");

        List<String> result;
        result = c.bicicletasNumParque(10.0, 10.0);
        assertEquals(expResult, result);

        // testando para quando o parque não é encontrado
        when(p.getParque(10.0, 10.0)).thenReturn(null);
        result = c.bicicletasNumParque(10.0, 10.0);
        expResult = new ArrayList<>();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDistanciaUtilizadorParque method, of class PesquisaController.
     */
    @Test
    public void testGetDistanciaUtilizadorParque() {
        System.out.println("getDistanciaUtilizadorParque");
        double lat = 20.0d;
        double lon = 30.0d;
        String idParque = "parqueTeste";

        Parque parque = new Parque("parqueTeste", 20.123, 20.123, 10, "descParque", 10, 10, 220, 16);
        //String idParque, Double latitude, Double longitude, Integer elevacao, String descricao, 
        //Integer lot_bike, Integer lot_scooter, Integer voltagem, Integer corrente)
        Pesquisa pesquisa = Mockito.spy(new Pesquisa());
        PesquisaController instance = new PesquisaController();
        instance.p = pesquisa;

        System.out.println(">>" + Calculos.distEntreDoisLocais(2, 2, 3, 2));

        doReturn(parque).when(pesquisa).getParque("parqueTeste");
        double expResult = 1031574d;
        double result = instance.getDistanciaUtilizadorParque(lat, lon, idParque);
        assertEquals(expResult, result, 1);

        doReturn(null).when(pesquisa).getParque("parqueTeste");
        expResult = 0.0d;
        result = instance.getDistanciaUtilizadorParque(lat, lon, idParque);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of scootersParqueOrdenadosPorMaiorCarga method, of class
     * PesquisaController.
     */
    @Test
    public void testScootersParqueOrdenadosPorMaiorCarga() {
        System.out.println("scootersParqueOrdenadosPorMaiorCarga");
        String id = "Mary";
		Scooter s1 = new Scooter(100D, 50, "a", 10, "a", "a", 1, 1.0, 1.0); // 2o
		Scooter s2 = new Scooter(100D, 75, "b", 10, "b", "b", 1, 1.0, 1.0); // 1o
		Scooter s3 = new Scooter(200D, 10, "c", 10, "c", "c", 1, 1.0, 1.0); // 3o
        List<Scooter> expResult = new ArrayList<>();
		expResult.add(s2);
		expResult.add(s1);
		expResult.add(s3);
		List<Scooter> unorderedList = new ArrayList<>();
		unorderedList.add(s2);
		unorderedList.add(s1);
		unorderedList.add(s3);
		
		when(p.getScootersNumParque(id)).thenReturn(unorderedList);
		System.out.println(p.getScootersNumParque("Mary"));
        List<Scooter> result = c.scootersParqueOrdenadosPorMaiorCarga(id);
        assertEquals(expResult, result);
		
		when(p.getScootersNumParque(id)).thenReturn(null);
        result = c.scootersParqueOrdenadosPorMaiorCarga(id);
        assertEquals(new ArrayList<>(), result);

		// outros testes
        id = "";
        expResult = new ArrayList<>();
        Scooter sc1 = new Scooter(100D, 100, id, Integer.BYTES, id, id, Integer.SIZE, Double.MIN_NORMAL, Double.POSITIVE_INFINITY);
        Scooter sc2 = new Scooter(200D, 100, id, Integer.BYTES, id, id, Integer.SIZE, Double.MIN_NORMAL, Double.POSITIVE_INFINITY);
        expResult.add(sc1);
        expResult.add(sc2);
        when(p.getScootersNumParque(id)).thenReturn(expResult);

        result = c.scootersParqueOrdenadosPorMaiorCarga(id);
        assertEquals(expResult, result);
        assertEquals(sc2, result.get(0));
        assertEquals(sc1, result.get(1));
    }

    /**
     * Test of getUtilizadorByUsername method, of class PesquisaController.
     */
    @Test
    public void testGetUtilizadorByUsername() {
        System.out.println("getUtilizadorByUsername");
        String username = "Mary";
        Utilizador expResult = 
			new Utilizador(0, "usr", "s", "mail", "name", "1", 1, 2, (float) 14, "t");
        when(p.getUtilizadorByUserName(username)).thenReturn(expResult);
        Utilizador result = c.getUtilizadorByUsername(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of getLocalComCoordenadas method, of class PesquisaController.
     */
    @Test
    public void testGetLocalComCoordenadas() {
        System.out.println("getLocalComCoordenadas");
        Double lat = 1.2;
        Double lon = 3.4;
        Parque parque = new Parque("testePk", lat, lon, 10, "desc", Integer.SIZE, Integer.MIN_VALUE, Integer.BYTES, Integer.SIZE);
        when(p.getParque(lat, lon)).thenReturn(parque);
        Local result = c.getLocalComCoordenadas(lat, lon);
        assertEquals(parque, result);
		
		POI poi = new POI(lat, lon, 1, "hello");
	    when(p.getParque(lat, lon)).thenReturn(null);
		when(p.getPOI(lat, lon)).thenReturn(poi);
        result = c.getLocalComCoordenadas(lat, lon);
        assertEquals(poi, result);

	    when(p.getParque(lat, lon)).thenReturn(null);
		when(p.getPOI(lat, lon)).thenReturn(null);
        result = c.getLocalComCoordenadas(lat, lon);
        assertEquals(null, result);
	
    }

    /**
     * Test of scootersNumParque method, of class PesquisaController.
     */
    @Test
    public void testScootersNumParque_double_double() {
        System.out.println("scootersNumParque");
        Scooter b1 = new Scooter(Double.MAX_VALUE, 100, "tipo", Integer.BYTES, "testeID", "A", Integer.SIZE, Double.MIN_NORMAL, Double.POSITIVE_INFINITY);
        Scooter b2 = new Scooter(Double.MAX_VALUE, 100, "tipo", Integer.BYTES, "testeID", "B", Integer.SIZE, Double.MIN_NORMAL, Double.POSITIVE_INFINITY);
        List<Scooter> lista = new ArrayList<>();
        lista.add(b1);
        lista.add(b2);

        when(p.getScootersNumParque("testeID")).thenReturn(lista);

        Parque parque = new Parque();
        parque.setIdParque("testeID");

        // testando para quando é encontrado o parque
        when(p.getParque(10.0, 10.0)).thenReturn(parque);

        List<String> expResult = new ArrayList<>();
        expResult.add("A;tipo;100");
        expResult.add("B;tipo;100");

        List<String> result;
        result = c.scootersNumParque(10.0, 10.0);
        assertEquals(expResult, result);

        // testando para quando o parque não é encontrado
        when(p.getParque(10.0, 10.0)).thenReturn(null);
        result = c.scootersNumParque(10.0, 10.0);
        expResult = new ArrayList<>();
        assertEquals(expResult, result);
    }

    /**
     * Test of scootersNumParque method, of class PesquisaController.
     */
    @Test
    public void testScootersNumParque_String() {
        System.out.println("scootersNumParque");
        Scooter b1 = new Scooter(Double.MAX_VALUE, 100, "tipo", Integer.BYTES, "testeID", "A", Integer.SIZE, Double.MIN_NORMAL, Double.POSITIVE_INFINITY);
        Scooter b2 = new Scooter(Double.MAX_VALUE, 100, "tipo", Integer.BYTES, "testeID", "B", Integer.SIZE, Double.MIN_NORMAL, Double.POSITIVE_INFINITY);
        List<Scooter> lista = new ArrayList<>();
        lista.add(b1);
        lista.add(b2);

        when(p.getScootersNumParque("testeID")).thenReturn(lista);

        Parque parque = new Parque();
        parque.setIdParque("testeID");

        // testando para quando é encontrado o parque
        when(p.getParque(10.0, 10.0)).thenReturn(parque);

        List<String> expResult = new ArrayList<>();
        expResult.add("A;tipo;100");
        expResult.add("B;tipo;100");

        List<String> result;
        result = c.scootersNumParque("testeID");
        assertEquals(expResult, result);

        // testando para quando o parque não é encontrado
        when(p.getParque(10.0, 10.0)).thenReturn(null);
        result = c.scootersNumParque(10.0, 10.0);
        expResult = new ArrayList<>();
        assertEquals(expResult, result);
    }
}
