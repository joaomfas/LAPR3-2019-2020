package lapr.project.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import lapr.project.model.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class RotasControllerTest {

    Pesquisa p;
    Rotas r;
    RotasController instance;

    public RotasControllerTest() {
        p = Mockito.mock(Pesquisa.class);
        r = Mockito.mock(Rotas.class);
        instance = new RotasController();
        instance.setP(p);
        instance.setR(r);
    }

    /**
     * Test of rotaMaisCurta method, of class RotasController.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testRotaMaisCurta() {
        System.out.println("rotaMaisCurta");
        Parque p1 = new Parque();
        Parque p2 = new Parque();
        when(p.getParque(0.0, 0.0)).thenReturn(p1);
		when(p.getParque("1")).thenReturn(p1);
        when(p.getParque("2")).thenReturn(p2);
        LinkedList<Local> localList = new LinkedList<>();
        when(r.rotaMaisCurta(p1, p2)).thenReturn(localList);
        when(r.distanciaParaUmaRota(localList)).thenReturn(100.00);
        int expResult = 100;
		
		List<Local> rotaMaisCurta = instance.rotaMaisCurta(0.0, 0.0, 1.0, 1.0);
        int result = (int) instance.distanciaRota(rotaMaisCurta);
		List<Local> rotaMaisCurta2 =  instance.rotaMaisCurta("1", "2");
		int result2 = (int) instance.distanciaRota(rotaMaisCurta2);
        assertEquals(expResult, result);
		assertEquals(expResult, result2);

        // teste não encontrando o parque
        when(p.getParque(0.0, 0.0)).thenReturn(null);
		when(p.getParque("1")).thenReturn(null);
        rotaMaisCurta = instance.rotaMaisCurta(0.0, 0.0, 1.0, 1.0);
		rotaMaisCurta2 =  instance.rotaMaisCurta("1", "2");
        assertEquals(new ArrayList<Local>(), rotaMaisCurta);
        assertEquals(new ArrayList<Local>(), rotaMaisCurta2);

		
        when(p.getParque(1.0, 1.0)).thenReturn(null);
		when(p.getParque("2")).thenReturn(null);
        rotaMaisCurta = instance.rotaMaisCurta(0.0, 0.0, 1.0, 1.0);
		rotaMaisCurta2 =  instance.rotaMaisCurta("1", "2");
        assertEquals(new ArrayList<Local>(), rotaMaisCurta);
        assertEquals(new ArrayList<Local>(), rotaMaisCurta2);

        when(p.getParque(0.0, 0.0)).thenReturn(p1);
		when(p.getParque("1")).thenReturn(p1);
        rotaMaisCurta = instance.rotaMaisCurta(0.0, 0.0, 1.0, 1.0);
		rotaMaisCurta2 =  instance.rotaMaisCurta("1", "2");
        assertEquals(new ArrayList<Local>(), rotaMaisCurta);
        assertEquals(new ArrayList<Local>(), rotaMaisCurta2);	
    }
	
	/**
     * Test of energiaRota method, of class RotasController.
     */
    @Test
	public void testEnergiaRota() {
        System.out.println("energiaRota");
		LinkedList<Local> rota = new LinkedList<>();
		when(r.energiaParaUmaRota(rota)).thenReturn(100.0);
		assertEquals(100L, instance.energiaRota(rota));
	}
	
	/**
     * Test of energiaRota method, of class RotasController.
     */
    @Test
	public void testRotaToFileString() {
        System.out.println("energiaRota");
		Local A = new Parque("idA", 1.0, 1.0, 1, "pA", 1, 1, 1, 1);
		Local B = new Parque("idB", 2.0, 2.0, 2, "pB", 2, 2, 2, 2);
		Local C = new Parque("idC", 3.0, 3.0, 3, "pC", 3, 3, 3, 3);
		LinkedList<Local> rota1 = new LinkedList<>();
		rota1.add(A);
		rota1.add(B);
		rota1.add(C);
		LinkedList<Local> rota2 = new LinkedList<>();
		rota2.add(A);
		rota2.add(C);
		List<LinkedList<Local>> rotas = new ArrayList<>();
		rotas.add(rota1);
		rotas.add(rota2);
		when(r.energiaParaUmaRota(rota1)).thenReturn(50.0);
		when(r.energiaParaUmaRota(rota2)).thenReturn(75.0);
		when(r.distanciaParaUmaRota(rota1)).thenReturn(120.0);
		when(r.distanciaParaUmaRota(rota2)).thenReturn(170.0);
		String result = instance.rotaToFileString(rotas);
		System.out.println(result);
		String expResult = "Path 001"
					+ "\ntotal_distance:120"
					+ "\ntotal_energy:50.00"
                                        + "\nelevation:-2"
					+ "\n1.000000;1.000000"
					+ "\n2.000000;2.000000"
					+ "\n3.000000;3.000000"
					+ "\nPath 002"
					+ "\ntotal_distance:170"
					+ "\ntotal_energy:75.00"
                                        + "\nelevation:-2"
					+ "\n1.000000;1.000000"
					+ "\n3.000000;3.000000\n";
		System.out.println(expResult);
		System.out.println(result);
		assertEquals(expResult, result);
		
	}
	
	

    /**
     * Test of rotaMaisEficiente method, of class RotasController.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testRotaMaisEficiente() {
        System.out.println("rotaMaisEficiente");
        Utilizador user = new Utilizador();
        when(p.getUtilizadorByUserName("user")).thenReturn(user);
        Parque p1 = new Parque();
        Parque p2 = new Parque();
        Veiculo v = new Scooter(null, null, null, null, null, null, 10, 10.0, 10.0);
		when(p.getVeiculoComTipoESpecs(any(), any())).thenReturn(v);
        when(p.getParque("p1")).thenReturn(p1);
        when(p.getParque("p2")).thenReturn(p2);
        LinkedList<Local> expResult = new LinkedList<>();
        when(r.rotaMaisEficiente(any(), any(), any(), any())).thenReturn(expResult);
        expResult.add(p1);
        expResult.add(p2);
        List<Local> result = instance.rotaMaisEficiente("p1", "p2", "scooter", "10;10;10", "user");
        assertEquals(expResult, result);
        when(p.getParque("p1")).thenReturn(null);
        result = instance.rotaMaisEficiente("p1", "p2", "bycicle", "10;10;10", "user");
        assertEquals(new ArrayList<Local>(), result);
    }

    /**
     * Test of distanciaRota method, of class RotasController.
     */
//    @Test
//    public void testDistanciaRota() {
//        System.out.println("distanciaRota");
//        List<Local> rota = new ArrayList<>();
//        rota.add(new POI(0.0, 0.0, 0, "A"));
//        rota.add(new POI(0.0, 0.0, 0, "B"));
//        rota.add(new POI(0.0, 0.0, 0, "C"));
//        long expResult = 100L;
//        when(r.distanciaParaUmaRota(rota)).thenReturn(100.0);
//        long result = instance.distanciaRota(rota);
//        assertEquals(expResult, result);
//    }

    /**
     * Test of rotasToString method, of class RotasController.
     */
    @Test
    public void testRotasToString() {
        System.out.println("rotasToString");
        List<List<Local>> rotas = new ArrayList<>();
        List<Local> rota1 = new ArrayList<>();
        rota1.add(new POI(1.0, 1.0, 1, "A"));
        rota1.add(new POI(2.0, 2.0, 2, "B"));
        rota1.add(new POI(3.0, 3.0, 3, "C"));
        List<Local> rota2 = new ArrayList<>();
        rota2.add(new POI(4.0, 4.0, 4, "D"));
        rota2.add(new POI(5.0, 5.0, 5, "E"));
        rota2.add(new POI(6.0, 6.0, 6, "F"));
        rotas.add(rota1);
        rotas.add(rota2);
        List<String> expResult = new ArrayList<>();
        expResult.add("1.000000;1.000000\n2.000000;2.000000\n3.000000;3.000000\n");
        expResult.add("4.000000;4.000000\n5.000000;5.000000\n6.000000;6.000000\n");
        List<String> result = instance.rotasToString(rotas);
        assertEquals(expResult, result);
    }

	   /**
     * Test of rotasMaisCurtasComPOIs method, of class RotasController.
     */
    @Test
    public void testRotasMaisCurtasComPOIs() {
        System.out.println("rotasMaisCurtasComPOIs");
        LinkedList<Local> rota1 = new LinkedList<>();
        rota1.add(new POI(1.0, 1.0, 1, "A"));
        rota1.add(new POI(2.0, 2.0, 2, "B"));
        rota1.add(new POI(3.0, 3.0, 3, "C"));
        LinkedList<Local> rota2 = new LinkedList<>();
        rota2.add(new POI(4.0, 4.0, 4, "D"));
        rota2.add(new POI(5.0, 5.0, 5, "E"));
        rota2.add(new POI(6.0, 6.0, 6, "F"));
        List<LinkedList<Local>> expResult = new ArrayList<>();
		expResult.add(rota1);
		expResult.add(rota2);
		List<String[]> sPois = new ArrayList<>();
		String[] sPOI1 = {"7.0", "7.0", "7", "G"};
		String[] sPOI2 = {"8.0", "8.0", "8", "H"};
		sPois.add(sPOI1);
		sPois.add(sPOI2);
		List<Local> pois = instance.parsePOI(sPois);
		Parque A = new Parque("idA", 1.0, 1.0, 1, "pA", 1, 1, 1, 1);
		Parque B = new Parque("idB", 2.0, 2.0, 2, "pB", 2, 2, 2, 2);
        when(r.rotasMaisCurtasComPOIS(A, B, pois, 100)).thenReturn(expResult);
		when(p.getParque("idA")).thenReturn(A);
		when(p.getParque("idB")).thenReturn(B);
        List<LinkedList<Local>> result = instance.rotasMaisCurtasComPOIS("idA", "idB", sPois);
        assertEquals(expResult, result);
		
		when(p.getParque(1.0, 1.0)).thenReturn(A);
		when(p.getParque(2.0, 2.0)).thenReturn(B);
		result = instance.rotasMaisCurtasComPOIS(1.0, 1.0, 2.0, 2.0, sPois);
        assertEquals(expResult, result);
    }


}
