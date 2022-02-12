/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.sql.Timestamp;

/**
 *
 * @author marco
 */
public class contratosDTO {

    private Integer id;
    private String nro_contrato;
    private String nombre_llamado;
    private Timestamp fecha_inicio;
    private Timestamp fecha_fin;
    private Integer monto_contrato;

    private TipContratoDTO tipocontrato;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNro_contrato() {
        return nro_contrato;
    }

    public void setNro_contrato(String nro_contrato) {
        this.nro_contrato = nro_contrato;
    }

    public String getNombre_llamado() {
        return nombre_llamado;
    }

    public void setNombre_llamado(String nombre_llamado) {
        this.nombre_llamado = nombre_llamado;
    }

    public Timestamp getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Timestamp fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Timestamp getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Timestamp fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Integer getMonto_contrato() {
        return monto_contrato;
    }

    public void setMonto_contrato(Integer monto_contrato) {
        this.monto_contrato = monto_contrato;
    }

    public TipContratoDTO getTipocontrato() {
        return tipocontrato;
    }

    public void setTipocontrato(TipContratoDTO tipocontrato) {
        this.tipocontrato = tipocontrato;
    }
    
    
    
}
