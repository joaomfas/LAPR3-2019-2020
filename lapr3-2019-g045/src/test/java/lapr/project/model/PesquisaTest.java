package lapr.project.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.data.*;
import org.junit.After;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

public class PesquisaTest {

    @Mock
    POIBD poibd;

    @Mock
    ParqueBD pbd;

    @Mock
    CaminhoBD cbd;

    @Mock
    VeiculoBD vbd;

    @Mock
    ViagemBD vibd;

    @Mock
    UtilizadorBD ubd;

    @Mock
    FaturaBD fdb;

    Pesquisa p;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        p = new Pesquisa();
        p.setCbd(cbd);
        p.setPbd(pbd);
        p.setPoibd(poibd);
        p.setUbd(ubd);
        p.setVbd(vbd);
        p.setVibd(vibd);
        p.setFdb(fdb);
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getParques method, of class Pesquisa.
     */
    @Test
    public void testGetParques() {
        System.out.println("getParques");

        when(pbd.getParques(true)).thenReturn(null);
        assertEquals(null, p.getParques());

        List<Parque> lista = new ArrayList<>();
        when(pbd.getParques(true)).thenReturn(lista);
        assertEquals(lista, p.getParques());
    }

    /**
     * Test of getBicicletas method, of class Pesquisa.
     */
    @Test
    public void testGetBicicletas() {
        System.out.println("getBicicletas");

        when(vbd.getBicicletas(true)).thenReturn(null);
        assertEquals(null, p.getBicicletas(true));

        List<Bicicleta> lista = new ArrayList<>();
        when(vbd.getBicicletas(true)).thenReturn(lista);
        assertEquals(lista, p.getBicicletas(true));

    }

    /**
     * Test of getScooters method, of class Pesquisa.
     */
    @Test
    public void testGetScooters() {
        System.out.println("getScooters");

        when(vbd.getScooters(false)).thenReturn(null);
        assertEquals(null, p.getScooters(false));

        List<Scooter> lista = new ArrayList<>();
        when(vbd.getScooters(false)).thenReturn(lista);
        assertEquals(lista, p.getScooters(false));

    }

    /**
     * Test of getCaminhos method, of class Pesquisa.
     */
    @Test
    public void testGetCaminhos() {
        System.out.println("getCaminhos");
        when(cbd.getCaminhos()).thenReturn(null);
        assertEquals(null, p.getCaminhos());

        List<Caminho> lista = new ArrayList<>();
        when(cbd.getCaminhos()).thenReturn(lista);
        assertEquals(lista, p.getCaminhos());
    }

    /**
     * Test of getCaminhos method, of class Pesquisa.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetCaminho() {
        System.out.println("getCaminho");
        when(cbd.getCaminho(0, 0, 0, 0)).thenReturn(null);
        assertEquals(null, p.getCaminho(0, 0, 0, 0));

        Caminho c = new Caminho(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0);
        when(cbd.getCaminho(0, 0, 0, 0)).thenReturn(c);
        assertEquals(c, p.getCaminho(0, 0, 0, 0));

        when(cbd.getCaminho(0, 0, 0, 0)).thenThrow(Exception.class);
        assertEquals(null, p.getCaminho(0, 0, 0, 0));
    }

    /**
     * Test of getPOIs method, of class Pesquisa.
     */
    @Test
    public void testGetPOIs() {
        System.out.println("getPOIs");

        when(poibd.getPOIs()).thenReturn(null);
        assertEquals(null, p.getPOIs());

        List<POI> lista = new ArrayList<>();
        when(poibd.getPOIs()).thenReturn(lista);
        assertEquals(lista, p.getPOIs());
    }

    /**
     * Test of getBicicletasNumParque method, of class Pesquisa.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetBicicletasNumParque() {
        System.out.println("getBicicletasNumParque");
        when(vbd.getBicicletasNumParque(any())).thenReturn(null);
        assertEquals(null, p.getBicicletasNumParque("testeID"));
        assertEquals(null, p.getBicicletasNumParque(null));

        List<Bicicleta> lista = new ArrayList<>();
        when(vbd.getBicicletasNumParque(any())).thenReturn(lista);
        assertEquals(lista, p.getBicicletasNumParque("testeID"));

        when(vbd.getBicicletasNumParque(any())).thenThrow(Exception.class);
        assertEquals(null, p.getBicicletasNumParque("testeID"));
    }

    /**
     * Test of getScootersNumParque method, of class Pesquisa.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetScootersNumParque() {
        System.out.println("getScootersNumParque");

        when(vbd.getScootersNumParque(any())).thenReturn(null);
        assertEquals(null, p.getScootersNumParque("testeID"));
        assertEquals(null, p.getScootersNumParque(null));

        List<Scooter> lista = new ArrayList<>();
        when(vbd.getScootersNumParque(any())).thenReturn(lista);
        assertEquals(lista, p.getScootersNumParque("testeId"));

        when(vbd.getScootersNumParque(any())).thenThrow(Exception.class);
        assertEquals(null, p.getScootersNumParque("testeId"));
    }

    /**
     * Test of getParque method, of class Pesquisa.
     */
    @Test
    public void testGetParque_Integer() {
        System.out.println("getParque");
        Parque parque = new Parque();
        when(pbd.getParque("testeID")).thenReturn(parque);
        assertEquals(parque, p.getParque("testeID"));
    }

    /**
     * Test of getParque method, of class Pesquisa.
     */
    @Test
    public void testGetParque_double_double() {
        System.out.println("getParque");
        Parque parque = new Parque();
        when(pbd.getParque(5.0, 5.0)).thenReturn(parque);
        assertEquals(parque, p.getParque(5.0, 5.0));
        
        when(pbd.getParque(5.0, 5.0)).thenReturn(null);
        assertEquals(null, p.getParque(5.0, 5.0));
        
        when(pbd.getParque(5.0, 5.0)).thenThrow(Exception.class);
        assertEquals(null, p.getParque(5.0, 5.0));
    }

    /**
     * Test of getVeiculo method, of class Pesquisa.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetVeiculo() {
        System.out.println("getVeiculo");
        Scooter scooter = new Scooter();
        when(vbd.getVeiculo("testeID")).thenReturn(scooter);
        assertEquals(scooter, p.getVeiculo("testeID"));

        when(vbd.getVeiculo(any())).thenThrow(Exception.class);
        assertEquals(null, p.getVeiculo("testeId"));
    }

    /**
     * Test of getUtilizador method, of class Pesquisa.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetUtilizador() {
        System.out.println("getUtilizador");
        Utilizador utilizador = new Utilizador("", "", "", "", "", 0, 0, 0, "");
        when(ubd.getUtilizador(1)).thenReturn(utilizador);
        assertEquals(utilizador, p.getUtilizador(1));

        when(ubd.getUtilizador(1)).thenThrow(Exception.class);
        assertEquals(null, p.getUtilizador(1));
    }



    /**
     * Test of getUtilizadorByEmail method, of class Pesquisa.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetViagemUtilizador() {
        System.out.println("getViagemUtilizador");
        Viagem viagem = new Viagem(1, "testeDesc", "testeID");
        
        when(vibd.getViagemByUtilizador(1)).thenReturn(viagem);
        assertEquals(viagem, vibd.getViagemByUtilizador(1));

        when(vibd.getViagemByUtilizador(1)).thenReturn(null);
        assertEquals(null, vibd.getViagemByUtilizador(1));

        when(vibd.getViagemByUtilizador(1)).thenThrow(Exception.class);
        assertEquals(null, p.getViagemUtilizador(1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testVerificaLugaresDisponiveis() {
        System.out.println("getVerificaLugaresDisponiveis");
        Parque parque = new Parque();
        parque.setLotBike(40);
        String id = "2";
        String username = "testeUser";
        when(pbd.verificaLugaresDisponiveis(id, username)).thenReturn(parque.getLotBike());
        assertEquals(parque.getLotBike(), p.verificaLugaresDisponiveis(id, username));

        when(pbd.verificaLugaresDisponiveis(any(), any())).thenThrow(Exception.class);
        assertEquals(0, p.verificaLugaresDisponiveis("", ""));
    }

    /**
     * Test of veiculosEmUtilizacaoRelatorio method, of class Pesquisa.
     */
    @Test
    public void testVeiculosEmUtilizacaoRelatorio() {
        System.out.println("veiculosEmUtilizacaoRelatorio");
        List<String> expResult = new LinkedList<>();
        when(vbd.veiculosEmUtilizacao()).thenReturn(expResult);
        assertEquals(expResult, p.veiculosEmUtilizacaoRelatorio());

    }

    /**
     * Test of getPOI method, of class Pesquisa.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetPOI() {
        System.out.println("getPOI");
        POI poi = new POI();
        when(poibd.getPOI(2.0, 3.0)).thenReturn(poi);
        assertEquals(poi, p.getPOI(2.0, 3.0));

        when(poibd.getPOI(any(), any())).thenThrow(Exception.class);
        assertEquals(null, p.getPOI(2.0, 3.0));
    }

    /**
     * Test of getScooterComCargaSuficiente method, of class Pesquisa.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetScooterComCargaSuficiente() {
        System.out.println("getScooterComCargaSuficiente");
        Double carga = 12.3;
        Parque parque = new Parque();
        parque.setDescricao("teste");

        Scooter sc = new Scooter();
        sc.setDescricao("teste");
        List<Scooter> exp = new ArrayList<>();
        exp.add(sc);

        when(vbd.scooterComCargaSuficiente(carga, parque)).thenReturn(exp);
        assertEquals(exp, p.getScooterComCargaSuficiente(carga, parque));

        when(vbd.scooterComCargaSuficiente(any(), any())).thenThrow(Exception.class);
        assertEquals(null, p.getScooterComCargaSuficiente(carga, parque));
    }

    /**
     * Test of getTodosParques method, of class Pesquisa.
     */
    @Test
    public void testGetTodosParques() {
        System.out.println("getTodosParques");

        List<Parque> lista = new ArrayList<>();
        when(pbd.getParques(false)).thenReturn(lista);
        assertEquals(lista, p.getParques());
        
        when(pbd.getParques(false)).thenReturn(lista);
        assertEquals(0, p.getParques().size());
    }

    /**
     * Test of getParque method, of class Pesquisa.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetParque_String() {
        System.out.println("getParque");
        Parque pk = new Parque();
        when(pbd.getParque("testeID")).thenReturn(pk);
        assertEquals(pk, p.getParque("testeID"));

        when(pbd.getParque("testeID")).thenThrow(Exception.class);
        assertEquals(null, p.getParque("testeID"));
    }

    /**
     * Test of getUtilizadorByUserName method, of class Pesquisa.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetUtilizadorByUserName() {
        System.out.println("getUtilizadorByUserName");

        Utilizador utilizador = new Utilizador("", "", "", "", "", 0, 0, 0, "");
        when(ubd.getUtilizadorByUserName("1")).thenReturn(utilizador);
        assertEquals(utilizador, p.getUtilizadorByUserName("1"));

        when(ubd.getUtilizadorByUserName("1")).thenThrow(Exception.class);
        assertEquals(null, p.getUtilizadorByUserName("1"));
    }

    /**
     * Test of getFatura method, of class Pesquisa.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testeGetFatura() {
        System.out.println("getFatura");

        Fatura fatura = new Fatura();
        fatura.setUsername("disc");
        fatura.setMes(1);

        when(fdb.getFatura("disc", 1)).thenReturn(fatura);
        assertEquals(fatura, p.getFatura("disc", 1));

        when(fdb.getFatura("disc", 1)).thenThrow(Exception.class);
        assertEquals(null, p.getFatura("disc", 1));
    }

    /**
     * Test of getTipoVeiculoPorUser method, of class Pesquisa.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testgetTipoVeiculoPorUser() {
        System.out.println("getTipoVeiculoPorUser");

        String username = "testeUser";
        String expResult = "Bicicleta";
        when(vbd.getTipoVeiculoPorUser(username)).thenReturn("Bicicleta");
        assertEquals(expResult, p.getTipoVeiculoPorUser(username));

        when(vbd.getTipoVeiculoPorUser(username)).thenReturn("Scooter");
        expResult = "Scooter";
        assertEquals(expResult, p.getTipoVeiculoPorUser(username));
    }

    /**
     * Test of getViagemVeiculo method, of class Pesquisa.
     */
    @Test
    public void testGetViagemVeiculo() {
        System.out.println("getViagemVeiculo");
        String descVeiculo = "testeDesc";
        Viagem v = new Viagem();
        when(vibd.getViagemByVeiculo(descVeiculo)).thenReturn(v);
        Viagem expResult = v;
        Viagem result = p.getViagemVeiculo(descVeiculo);
        assertEquals(expResult, result);

        when(vibd.getViagemByVeiculo(descVeiculo)).thenReturn(null);
        expResult = null;
        result = p.getViagemVeiculo(descVeiculo);
        assertEquals(expResult, result);

        when(vibd.getViagemByVeiculo(descVeiculo)).thenThrow(Exception.class);
        result = p.getViagemVeiculo(descVeiculo);
        expResult = null;
        assertEquals(expResult, result);
    }

    /**
     * Test of getFatura method, of class Pesquisa.
     */
    @Test
    public void testGetFatura() {
        System.out.println("getFatura");
        String username = "testeUser";
        Integer mes = 12;
        Fatura f = new Fatura();

        when(fdb.getFatura(username, mes)).thenReturn(f);
        assertEquals(f, p.getFatura(username, mes));

        when(fdb.getFatura(username, mes)).thenReturn(null);
        assertEquals(null, p.getFatura(username, mes));
        
        when(fdb.getFatura(username, mes)).thenThrow(Exception.class);
        assertEquals(null, p.getFatura(username, mes));
    }

    /**
     * Test of getDetalheFatura method, of class Pesquisa.
     */
    @Test
    public void testGetDetalheFatura() {
        System.out.println("getDetalheFatura");
        Fatura fatura = new Fatura();
        List<String> lista = new ArrayList<>();

        when(fdb.getDetalheFatura(fatura)).thenReturn(lista);
        assertEquals(lista, p.getDetalheFatura(fatura));

        when(fdb.getDetalheFatura(fatura)).thenReturn(null);
        assertEquals(null, p.getDetalheFatura(fatura));
        
        when(fdb.getDetalheFatura(fatura)).thenThrow(Exception.class);
        assertEquals(0, p.getDetalheFatura(fatura).size());
    }

    /**
     * Test of getDetalheAtual method, of class Pesquisa.
     */
    @Test
    public void testGetDetalheAtual() {
        System.out.println("getDetalheAtual");
        String username = "user";
        List<String> lista = new ArrayList<>();

        when(fdb.getDetalheAtual(username)).thenReturn(lista);
        assertEquals(lista, p.getDetalheAtual(username));

        when(fdb.getDetalheAtual(username)).thenReturn(null);
        assertEquals(null, p.getDetalheAtual(username));
        
        when(fdb.getDetalheAtual(username)).thenThrow(Exception.class);
        assertEquals(0, p.getDetalheAtual(username).size());
    }

    /**
     * Test of getDetalhePontos method, of class Pesquisa.
     */
    @Test
    public void testGetDetalhePontos() {
        System.out.println("getDetalhePontos");
        String username = "user";
        List<String> lista = new ArrayList<>();

        when(fdb.getDetalhePontos(username)).thenReturn(lista);
        assertEquals(lista, p.getDetalhePontos(username));

        when(fdb.getDetalhePontos(username)).thenReturn(null);
        assertEquals(null, p.getDetalhePontos(username));
        
        when(fdb.getDetalhePontos(username)).thenThrow(Exception.class);
        assertEquals(0, p.getDetalhePontos(username).size());
    }

    /**
     * Test of getFaturasUtilizador method, of class Pesquisa.
     */
    @Test
    public void testGetFaturasUtilizador() {
        System.out.println("getFaturasUtilizador");
        String username = "user";
        List<Fatura> lista = new ArrayList<>();

        when(fdb.getFaturasUtilizador(username)).thenReturn(lista);
        assertEquals(lista, p.getFaturasUtilizador(username));

        when(fdb.getFaturasUtilizador(username)).thenReturn(null);
        assertEquals(null, p.getFaturasUtilizador(username));
        
        when(fdb.getFaturasUtilizador(username)).thenThrow(Exception.class);
        assertEquals(0, p.getFaturasUtilizador(username).size());
    }

    /**
     * Test of getTipoVeiculoPorUser method, of class Pesquisa.
     */
    @Test
    public void testGetTipoVeiculoPorUser() {
        System.out.println("getTipoVeiculoPorUser");
        String username = "user";
        String tipo = "tipo";

        when(vbd.getTipoVeiculoPorUser(username)).thenReturn(tipo);
        assertEquals(tipo, p.getTipoVeiculoPorUser(username));

        when(vbd.getTipoVeiculoPorUser(username)).thenReturn(null);
        assertEquals(null, p.getTipoVeiculoPorUser(username));
        
        when(vbd.getTipoVeiculoPorUser(username)).thenThrow(Exception.class);
        assertEquals(null, p.getTipoVeiculoPorUser(username));
    }

    /**
     * Test of getVeiculoComTipoESpecs method, of class Pesquisa.
     */
    @Test
    public void testGetVeiculoComTipoESpecs() {
        System.out.println("getVeiculoComTipoESpecs");
        String typeOfVehicle = "bicycle";
        String vehicleSpecs = "16";
        Veiculo v = new Bicicleta();

        when(vbd.getVeiculoComTipoESpecs(typeOfVehicle, vehicleSpecs)).thenReturn(v);
        assertEquals(v, p.getVeiculoComTipoESpecs(typeOfVehicle, vehicleSpecs));

        when(vbd.getVeiculoComTipoESpecs(typeOfVehicle, vehicleSpecs)).thenReturn(null);
        assertEquals(null, p.getVeiculoComTipoESpecs(typeOfVehicle, vehicleSpecs));
        
        when(vbd.getVeiculoComTipoESpecs(typeOfVehicle, vehicleSpecs)).thenThrow(Exception.class);
        assertEquals(null, p.getVeiculoComTipoESpecs(typeOfVehicle, vehicleSpecs));
    }
}
