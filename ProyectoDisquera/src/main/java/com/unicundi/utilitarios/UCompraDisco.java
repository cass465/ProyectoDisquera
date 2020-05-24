/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.utilitarios;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author cass465
 */
public class UCompraDisco implements Serializable{
    private int id;
    private int idUsuario;
    private int idDisco;
    private float valorCompra;
    private Date fechaCompra;

    public UCompraDisco(int id, int idUsuario, int idDisco, float valorCompra, Date fechaCompra) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idDisco = idDisco;
        this.valorCompra = valorCompra;
        this.fechaCompra = fechaCompra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdDisco() {
        return idDisco;
    }

    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;
    }

    public float getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(float valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    
}
