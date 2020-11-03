package lapr.project.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class RotasTest {

	Rotas r = new Rotas();

	List<Caminho> caminhos = new ArrayList<>();
	List<Parque> parques = new ArrayList<>();
	List<POI> pois = new ArrayList<>();

	// objetos auxiliares p/ gerar o grafo
	Parque A = new Parque("A", 0.0, 0.0, 100, "A", null, null, null, null);
	POI B = new POI(3.0, 0.0, 100, "B");
	POI C = new POI(0.0, 4.0, 100, "C");
	POI D = new POI(11.0, 4.0, 100, "D");
	POI E = new POI(8.0, 0.0, 100, "E");
	POI F = new POI(11.0, 0.0, 100, "F");
	Parque G = new Parque("G", 14.0, 0.0, 100, "G", null, null, null, null);
	
	Caminho AC = new Caminho(0.0, 0.0, 0.0, 4.0, 1.0, 1.0, 1.0);
	Caminho AB = new Caminho(0.0, 0.0, 3.0, 0.0, 1.0, 1.0, 1.0);
	Caminho BC = new Caminho(3.0, 0.0, 0.0, 4.0, 1.0, 1.0, 1.0);
	Caminho BE = new Caminho(3.0, 0.0, 8.0, 0.0, 1.0, 1.0, 1.0);
	Caminho CA = new Caminho(0.0, 4.0, 0.0, 0.0, 1.0, 1.0, 1.0);
	Caminho CB = new Caminho(0.0, 4.0, 3.0, 0.0, 1.0, 1.0, 1.0);
	Caminho EF = new Caminho(8.0, 0.0, 11.0, 0.0, 1.0, 1.0, 1.0);
	Caminho ED = new Caminho(8.0, 0.0, 11.0, 4.0, 1.0, 1.0, 1.0);
	Caminho FG = new Caminho(11.0, 0.0, 14.0, 0.0, 1.0, 1.0, 1.0);
	Caminho FD = new Caminho(11.0, 0.0, 11.0, 4.0, 1.0, 1.0, 1.0);
	Caminho DE = new Caminho(11.0, 4.0, 8.0, 0.0, 1.0, 1.0, 1.0);
	Caminho DG = new Caminho(11.0, 4.0, 14.0, 0.0, 1.0, 1.0, 1.0);
	Caminho GD = new Caminho(14.0, 0.0, 11.0, 4.0, 1.0, 1.0, 1.0);

	Utilizador u = new Utilizador("A", null, null, "A", null, 100, 2, 30, null);
	Veiculo v = new Scooter(null, null, null, null, null, "A", 100, 10.0, 1.0);
	
	LinkedList<Local> result1;
	LinkedList<Local> result2;
	
	/**
	 * setup dos testes
	 */
	public RotasTest() {

		parques.add(A);
		pois.add(B);
		pois.add(C);
		pois.add(D);
		pois.add(E);
		pois.add(F);
		parques.add(G);


		caminhos.add(AC);
		caminhos.add(AB);
		caminhos.add(BC);
		caminhos.add(BE);
		caminhos.add(CA);
		caminhos.add(CB);
		caminhos.add(EF);
		caminhos.add(ED);
		caminhos.add(FG);
		caminhos.add(FD);
		caminhos.add(DE);
		caminhos.add(DG);
		caminhos.add(GD);

		// resultados utilizados nos testes
		result1= new LinkedList<>();
		result1.addLast(A);
		result1.addLast(C);
		result1.addLast(B);
		result1.addLast(E);
		result1.addLast(F);
		result1.addLast(D);
		result1.addLast(G);
		result2 = new LinkedList<>();
		result2.addLast(A);
		result2.addLast(C);
		result2.addLast(B);
		result2.addLast(E);
		result2.addLast(D);
		result2.addLast(E);
		result2.addLast(F);
		result2.addLast(G);
		
		// setup do mock 
		Pesquisa p = Mockito.mock(Pesquisa.class);
		r.setPesquisa(p);
		when(p.getPOIs()).thenReturn(pois);
		when(p.getTodosParques()).thenReturn(parques);
		when(p.getCaminhos()).thenReturn(caminhos);
		r.loadGraph();

	}

	/**
	 * Test of distanciaParaUmaRota method, of class Rotas.
	 */
	@Test
	public void testDistanciaParaUmaRota() {
		System.out.println("distanciaParaUmaRota");
		List<Local> rota = new ArrayList<>();
		rota.add(A);
		rota.add(B);
		rota.add(E);
		rota.add(F);
		rota.add(G);
		double expResult = 1556728;
		double result = (int) r.distanciaParaUmaRota(rota);
		assertEquals(expResult, result);
	}

	/**
	 * Test of energiaParaUmaRota method, of class Rotas.
	 */
	@Test
	public void testEnergiaParaUmaRota() {
		System.out.println("energiaParaUmaRota");
		List<Local> rota = new ArrayList<>();
		rota.add(A);
		rota.add(B);
		rota.add(E);
		rota.add(F);
		rota.add(G);
		double expResult = 929.874;
		double result = r.energiaParaUmaRota(rota, u, v);
		assertEquals(expResult, result, 1);
	}

	/**
	 * Test of RotaMaisCurta method, of class Rotas.
	 */
	@Test
	public void testRotaMaisCurta() {
		System.out.println("RotaMaisCurta");
		Local orig = A;
		Local dest = G;
		List<Local> expResult = new ArrayList<>();
		expResult.add(A);
		expResult.add(B);
		expResult.add(E);
		expResult.add(F);
		expResult.add(G);
		List<Local> result = r.rotaMaisCurta(orig, dest);
		assertEquals(expResult, result);
	}

	/**
	 * Test of RotaMaisEficiente method, of class Rotas.
	 */
	@Test
	public void testRotaMaisEficiente() {
		System.out.println("RotaMaisEficiente");
		Local orig = A;
		Local dest = G;
		List<Local> expResult = new ArrayList<>();
		expResult.add(A);
		expResult.add(B);
		expResult.add(E);
		expResult.add(F);
		expResult.add(G);
		List<Local> result = r.rotaMaisEficiente(orig, dest, v, u);
		assertEquals(expResult, result);
	}

	/**
	 * Test of RotasMaisCurtasComPOIS method, of class Rotas.
	 */
	@Test
	public void testRotasMaisCurtasComPOIS() {
		System.out.println("RotasMaisCurtasComPOIS");
		Local orig = A;
		Local dest = G;
		List<Local> between = new ArrayList<>();
		between.add(C);
		between.add(D);
		between.add(F);
		int n = 2;
		List<LinkedList<Local>> expResult = new ArrayList<>();
		expResult.add(result1);
		expResult.add(result2);
		List<LinkedList<Local>> result = r.rotasMaisCurtasComPOIS(orig, dest, between, n);
//		System.out.println("RESULT");
//		result.forEach(r -> {
//				System.out.println(r.toString());
//		});
//		System.out.println("\nEXP");
//		expResult.forEach(r -> {
//				System.out.println(r.toString());
//		});
		assertEquals(expResult, result);
	}
	
	 /**
	 * Test of RotasMaisCurtasComPOIS method, of class Rotas.
	 */
	@Test
	public void testRotasMaisEficientesComPOIS() {
		System.out.println("RotasMaisEficientesComPOIS");
		Local orig = A;
		Local dest = G;
		List<Local> between = new ArrayList<>();
		between.add(C);
		between.add(D);
		between.add(F);
		int n = 2;
		List<LinkedList<Local>> expResult = new ArrayList<>();
		expResult.add(result1);
		expResult.add(result2);
		List<LinkedList<Local>> result = r.rotasMaisEficientesComPOIS(orig, dest, v, u, between, n);
//		System.out.println("RESULT");
//		result.forEach(r -> {
//				System.out.println(r.toString());
//		});
//		System.out.println("\nEXP");
//		expResult.forEach(r -> {
//				System.out.println(r.toString());
//		});
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of RotasMaisCurtasComPOIS method, of class Rotas.
	 */
	@Test
	public void testTodasRotasMaisCurtasComPOIS() {
		System.out.println("TodasRotasMaisCurtasComPOIS");
		Local orig = A;
		Local dest = G;
		List<Local> between = new ArrayList<>();
		between.add(C);
		between.add(D);
		between.add(F);
		int n = 2;
		List<LinkedList<Local>> expResult = new ArrayList<>();
		LinkedList<Local> result1 = new LinkedList<>();
		result1.addLast(A);
		result1.addLast(C);
		result1.addLast(B);
		result1.addLast(E);
		result1.addLast(F);
		result1.addLast(D);
		result1.addLast(G);
		expResult.add(result1);
		List<LinkedList<Local>> result = r.todasRotasMaisCurtasComPOIS(orig, dest, between, n);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of RotasMaisCurtasComPOIS method, of class Rotas.
	 */
	@Test
	public void testTodasRotasMaisEficientesComPOIS() {
		System.out.println("TodasRotasMaisEficientesComPOIS");
		Local orig = A;
		Local dest = G;
		List<Local> between = new ArrayList<>();
		between.add(C);
		between.add(D);
		between.add(F);
		int n = 2;
		List<LinkedList<Local>> expResult = new ArrayList<>();
		LinkedList<Local> result1 = new LinkedList<>();
		result1.addLast(A);
		result1.addLast(C);
		result1.addLast(B);
		result1.addLast(E);
		result1.addLast(F);
		result1.addLast(D);
		result1.addLast(G);
		expResult.add(result1);
		List<LinkedList<Local>> result = r.todasRotasMaisEficientesComPOIS(orig, dest, v, u, between, n);
		assertEquals(expResult, result);
	}

}
