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
public class UCompraCancion implements Serializable{
    private int id;
    private int idUsuario;
    private int idCancion;
    private int valorCompra;
    private Date fechaCompra;
    //
    private String genero;
    private String nombreCompletoArtista;
    private String cancion;
    private String duracion;

    public UCompraCancion(int id, int idUsuario, int idCancion, int valorCompra, Date fechaCompra) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idCancion = idCancion;
        this.valorCompra = valorCompra;
        this.fechaCompra = fechaCompra;
    }

    public UCompraCancion(int valorCompra, Date fechaCompra, String genero, String nombreCompletoArtista, String cancion, String duracion) {
        this.valorCompra = valorCompra;
        this.fechaCompra = fechaCompra;
        this.genero = genero;
        this.nombreCompletoArtista = nombreCompletoArtista;
        this.cancion = cancion;
        this.duracion = duracion;
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

    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public int getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(int valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNombreCompletoArtista() {
        return nombreCompletoArtista;
    }

    public void setNombreCompletoArtista(String nombreCompletoArtista) {
        this.nombreCompletoArtista = nombreCompletoArtista;
    }

    public String getCancion() {
        return cancion;
    }

    public void setCancion(String cancion) {
        this.cancion = cancion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
    
}
