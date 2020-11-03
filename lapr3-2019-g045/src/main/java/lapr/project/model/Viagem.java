package lapr.project.model;

import java.sql.Date;
import java.util.Objects;
import lapr.project.data.ViagemBD;

public class Viagem {

    private Integer idViagem;
    private Integer idUtilizador;
    private String descVeiculo;
    private String idParqueInicio;
    private String idParqueFim;
    private Date dataHoraInicio;
    private Date dataHoraFim;
    private ViagemBD bd = new ViagemBD();

    public Viagem() {
    }

    public Viagem(Integer idUtilizador, String descVeiculo, String idParqueInicio, Date dataHoraInicio) {
        this.idUtilizador = idUtilizador;
        this.descVeiculo = descVeiculo;
        this.idParqueInicio = idParqueInicio;
        if(dataHoraInicio!=null)
            this.dataHoraInicio = (Date) dataHoraInicio.clone();
    }
    
    public Viagem(Integer idUtilizador, String descVeiculo, String idParqueInicio) {
        this.idUtilizador = idUtilizador;
        this.descVeiculo = descVeiculo;
        this.idParqueInicio = idParqueInicio;
    }

    public Viagem(Integer idViagem, Integer idUtilizador, String descVeiculo, String idParqueInicio, String idParqueFim, Date dataHoraInicio, Date dataHoraFim) {
        this.idViagem = idViagem;
        this.idUtilizador = idUtilizador;
        this.descVeiculo = descVeiculo;
        this.idParqueInicio = idParqueInicio;
        this.idParqueFim = idParqueFim;
        if(dataHoraInicio!=null)
            this.dataHoraInicio = (Date) dataHoraInicio.clone();
        if(dataHoraFim!=null)
            this.dataHoraFim = (Date) dataHoraFim.clone();
    }
    
    public int getIdViagem() {
        return idViagem;
    }

    public int getIdUtilizador() {
        return idUtilizador;
    }



    public Date getDataHoraInicio() {
        if(dataHoraInicio == null)
            return null;
        return (Date) dataHoraInicio.clone();
    }

    public Date getDataHoraFim() {
        if(dataHoraFim == null)
            return null;
        return (Date) dataHoraFim.clone();
    }

    public void setIdViagem(Integer idViagem) {
        this.idViagem = idViagem;
    }

    public void setIdUtilizador(Integer idUtilizador) {
        this.idUtilizador = idUtilizador;
    }

    public String getDescVeiculo() {
        return descVeiculo;
    }

    public void setDescVeiculo(String descVeiculo) {
        this.descVeiculo = descVeiculo;
    }

    public String getIdParqueInicio() {
        return idParqueInicio;
    }

    public void setIdParqueInicio(String idParqueInicio) {
        this.idParqueInicio = idParqueInicio;
    }

    public String getIdParqueFim() {
        return idParqueFim;
    }

    public void setIdParqueFim(String idParqueFim) {
        this.idParqueFim = idParqueFim;
    }

    public void setDataHoraInicio(Date dataHoraInicio) {
        if(dataHoraInicio!=null)
            this.dataHoraInicio = (Date) dataHoraInicio.clone();
    }

    public void setDataHoraFim(Date dataHoraFim) {
        if(dataHoraFim!=null)
            this.dataHoraFim = (Date) dataHoraFim.clone();
    }

    public void setBd(ViagemBD bd) {
        this.bd = bd;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.idViagem);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else {
            return this.hashCode() == obj.hashCode();
        }
    }

    public boolean save() {
        try {
            bd.getViagemByUtilizador(this.idUtilizador);
        } catch (IllegalArgumentException e) {
            return bd.addViagem(this);
        }
        return bd.updateViagem(this);
    }

}
