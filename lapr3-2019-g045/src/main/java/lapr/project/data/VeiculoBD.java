package lapr.project.data;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

public class VeiculoBD extends DataHandler {

    public boolean addVeiculo(Veiculo v) {
        ArrayList<Veiculo> vs = new ArrayList<>();
        vs.add(v);
        return addAll(vs);
    }

    public Veiculo getVeiculo(String id) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getVeiculoComDescricao(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.setString(2, id);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                Integer idTipoVeic = rSet.getInt("id_tipo_veiculo");
                String idParque = rSet.getString("id_parque");
                String descricao = rSet.getString("descricao");
                Integer peso = rSet.getInt("peso");
                Double areaFrontal = rSet.getDouble("area_frontal");
                Double coefAero = rSet.getDouble("coeficiente_aero");
                Integer tamanho = rSet.getInt("tamanho");
                Double capMax = rSet.getDouble("capacidade_max");
                Integer cargaAtual = rSet.getInt("carga_atual");
                String tipo = rSet.getString("tipo");
                Integer potMotor = rSet.getInt("pot_motor");
                switch (idTipoVeic) {
                    case 1:
                        return new Scooter(capMax, cargaAtual, tipo, potMotor, idParque, descricao, peso, areaFrontal, coefAero);
                    case 2:
                        return new Bicicleta(tamanho, idParque, descricao, peso, areaFrontal, coefAero);
                    default:
                        return null;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(VeiculoBD.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("Não existe veiculo com o ID:" + id);
    }

    public boolean updateVeiculo(Veiculo v) {
        CallableStatement callStmt = null;
        Boolean opSucc = false;
        try {
            callStmt = getConnection().prepareCall("{ call updateVeiculo(?,?,?,?,?,?,?,?,?,?,?) }");

            callStmt.setObject("p_id_parque", v.getIdParque());
            callStmt.setObject("p_descricao", v.getDescricao());
            callStmt.setObject("p_peso", v.getPeso());
            callStmt.setObject("p_area_frontal", v.getAreaFrontal());
            callStmt.setObject("p_coef_aero", v.getCoeficienteAerodinamico());
            if (v instanceof Bicicleta) {
                Bicicleta bi = (Bicicleta) v;
                callStmt.setObject("p_tamanho", bi.getTamanho());
                callStmt.setObject("p_cap_max", null);
                callStmt.setObject("p_cap_atual", null);
                callStmt.setObject("p_tipo_scooter", null);
                callStmt.setObject("p_pot_motor", null);
            } else {
                callStmt.setObject("p_tamanho", null);
                Scooter sc = (Scooter) v;
                callStmt.setObject("p_cap_max", sc.getCapacidadeMax());
                callStmt.setObject("p_cap_atual", sc.getCargaAtual());
                callStmt.setObject("p_tipo_scooter", sc.getTipoScooter());
                callStmt.setObject("p_pot_motor", sc.getPotMotor());
            }
            if (v.getRemovido()) {
                callStmt.setObject("p_removido", 1);
            } else {
                callStmt.setObject("p_removido", 0);
            }
            callStmt.execute();
            opSucc = true;
            return opSucc;
        } catch (SQLException e) {
            Logger.getLogger(VeiculoBD.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeAll();
        }
        return opSucc;
    }

    public List<Bicicleta> getBicicletas(boolean removido) {
        List<Bicicleta> lista = null;

        int rm = removido ? 1 : 0;

        try {
            CallableStatement callStmt = null;
            callStmt = getConnection().prepareCall("{ ? = call getBicicletas(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.setObject(2, rm);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            lista = bicicletasFromResultset(rSet);
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<Scooter> getScooters(boolean removido) {
        List<Scooter> lista = null;

        int rm = removido ? 1 : 0;

        try {
            CallableStatement callStmt = null;
            callStmt = getConnection().prepareCall("{ ? = call getScooters(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.setObject(2, rm);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            lista = scootersFromResultset(rSet);
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<Bicicleta> getBicicletasNumParque(String id_parque) {
        try {
            CallableStatement callStmt = null;
            callStmt = getConnection().prepareCall("{ ? = call getBicicletasNoParque(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.setObject(2, id_parque);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            return bicicletasFromResultset(rSet);
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new IllegalArgumentException("Não existe parque com o ID:" + id_parque);
    }

    public List<Scooter> getScootersNumParque(String id_parque) {
        try {
            CallableStatement callStmt = null;
            callStmt = getConnection().prepareCall("{ ? = call getScootersNoParque(?) }");
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.setString(2, id_parque);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            return scootersFromResultset(rSet);
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new IllegalArgumentException("Não existe parque com o ID:" + id_parque);
    }

    private List<Scooter> scootersFromResultset(ResultSet rSet) {
        ArrayList<Scooter> scs = new ArrayList<>();

        try {
            while (rSet.next()) {
                String idParque = rSet.getString("id_parque");
                String descricao = rSet.getString("descricao");
                Integer peso = rSet.getInt("peso");
                Double areaFrontal = rSet.getDouble("area_frontal");
                Double coefAero = rSet.getDouble("coeficiente_aero");
                Double capMax = rSet.getDouble("capacidade_max");
                Integer cargaAtual = rSet.getInt("carga_atual");
                String tipo = rSet.getString("tipo");
                Integer potMotor = rSet.getInt("pot_motor");
                Integer removido = rSet.getInt("removido");

                Scooter sc = new Scooter(capMax, cargaAtual, tipo, potMotor, idParque, descricao, peso, areaFrontal, coefAero);
                if (removido == 0) {
                    sc.setRemovido(false);
                } else {
                    sc.setRemovido(true);
                }
                scs.add(sc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scs;
    }

    private List<Bicicleta> bicicletasFromResultset(ResultSet rSet) {
        ArrayList<Bicicleta> bcs = new ArrayList<>();

        try {
            while (rSet.next()) {
                String idParque = rSet.getString("id_parque");
                String descricao = rSet.getString("descricao");
                Integer peso = rSet.getInt("peso");
                Double areaFrontal = rSet.getDouble("area_frontal");
                Double coefAero = rSet.getDouble("coeficiente_aero");
                Integer tamanho = rSet.getInt("tamanho");
                Integer removido = rSet.getInt("removido");

                Bicicleta bi = new Bicicleta(tamanho, idParque, descricao, peso, areaFrontal, coefAero);
                if (removido == 0) {
                    bi.setRemovido(false);
                } else {
                    bi.setRemovido(true);
                }
                bcs.add(bi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bcs;
    }

    public boolean addAll(ArrayList<Veiculo> vcs) {
        CallableStatement callStmt = null;
        openConnection();
        try {
            for (Veiculo v : vcs) {
                callStmt = getConnection().prepareCall("{call addVeiculo(?,?,?,?,?,?,?,?,?,?,?,?) }");

                if (v instanceof Scooter) {
                    callStmt.setInt("id_tipo", 1);
                } else {
                    callStmt.setInt("id_tipo", 2);
                }
                callStmt.setObject("id_parque", v.getIdParque());

                callStmt.setObject("descricao", v.getDescricao());

                callStmt.setObject("peso", v.getPeso());
                callStmt.setObject("area_frontal", v.getAreaFrontal());
                callStmt.setObject("coef_aero", v.getCoeficienteAerodinamico());
                callStmt.setInt("estado", 0);

                if (v instanceof Scooter) {
                    callStmt.setObject("tamanho", null);
                    Scooter sc = (Scooter) v;
                    callStmt.setObject("cap_max", sc.getCapacidadeMax());
                    callStmt.setObject("cap_atual", sc.getCargaAtual());
                    callStmt.setObject("tipo_scooter", sc.getTipoScooter());
                    callStmt.setObject("pot_motor", sc.getPotMotor());
                } else {
                    Bicicleta bici = (Bicicleta) v;
                    callStmt.setObject("tamanho", bici.getTamanho());
                    callStmt.setObject("cap_max", null);
                    callStmt.setObject("cap_atual", null);
                    callStmt.setObject("tipo_scooter", null);
                    callStmt.setObject("pot_motor", null);
                }

                callStmt.execute();
            }
            commit();
            return true;
        } catch (SQLException e) {
            rollback();
            Logger.getLogger(ParqueBD.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            closeAll();
        }
    }

    public List<String> veiculosEmUtilizacao() {
        CallableStatement callStmt = null;
        openConnection();
        List<String> lista = new LinkedList<>();
        try {
            callStmt = getConnection().prepareCall("{ ? = call getVeiculosEmUtilizacao(?) }");

            
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setDate(2, new Date(System.currentTimeMillis()));
            callStmt.execute();

            ResultSet rs = (ResultSet) callStmt.getObject(1);
           
            while (rs.next()) {
                System.out.println(rs);
                int idParque = rs.getInt("id_parque");
                int id = rs.getInt("id_utilizador");
                String descricao = rs.getString("descricao");
                String saida = String.format("Id utilizador : %d ; descrição do Veículo: %s ; Id Parque Inicio : %d;", id, descricao, idParque);
                lista.add(saida);
                return lista;
            }
        } catch (SQLException e) {
            Logger.getLogger(VeiculoBD.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } finally {
            closeAll();
        }
        return lista;
    }

    public List<Scooter> scooterComCargaSuficiente(Double carga, Parque parque) {
        CallableStatement callStmt = null;
        List<Scooter> lista = new ArrayList<>();
        try {
            callStmt = getConnection().prepareCall("{ ? = call getScooterComCarga(?,?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.setObject(2, carga);
            callStmt.setObject(3, parque.getIdParque());

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            return scootersFromResultset(rSet);
        } catch (SQLException e) {
            Logger.getLogger(VeiculoBD.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeAll();
        }
        throw new IllegalArgumentException("Não existem veiculos com carga suficiente em: " + parque.getDescricao());
    }

    public String getTipoVeiculoPorUser(String username) {
        CallableStatement callStmt = null;
        String tipo = null;

        try {
            callStmt = getConnection().prepareCall("{ ? = call getTipoVeiculoPorUser(?) }");

            callStmt.registerOutParameter(1, OracleTypes.VARCHAR);

            callStmt.setObject(2, username);

            callStmt.execute();
            tipo = (String) callStmt.getObject(1);

            return tipo;
        } catch (SQLException e) {
            Logger.getLogger(VeiculoBD.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeAll();
        }
        return tipo;
    }
	
	public Veiculo getVeiculoComTipoESpecs(String typeOfVehicle, String vehicleSpecs) {
		List<Scooter> lista = null;
		try {
			CallableStatement callStmt = null;
			callStmt = getConnection().prepareCall("{ ? = call veiculoComTipoESpecs(?, ?) }");
			callStmt.registerOutParameter(1, OracleTypes.CURSOR);

			callStmt.setObject(2, typeOfVehicle.toLowerCase());
			callStmt.setObject(3, vehicleSpecs.toLowerCase());

			callStmt.execute();

			ResultSet rSet = (ResultSet) callStmt.getObject(1);

			lista = scootersFromResultset(rSet);
			if (!lista.isEmpty()) {
				return lista.get(0);
			}
		} catch (SQLException e) {
			Logger.getLogger(VeiculoBD.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			closeAll();
		}
		throw new IllegalArgumentException("Não veículos do tipo: " + typeOfVehicle + " e com specs: " + vehicleSpecs);
	}
}
