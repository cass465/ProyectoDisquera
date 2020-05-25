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
    //
    private String genero;
    private String nombreCompletoArtista;
    private String disco;
    private int numeroCanciones;

    public UCompraDisco(int id, int idUsuario, int idDisco, float valorCompra, Date fechaCompra) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idDisco = idDisco;
        this.valorCompra = valorCompra;
        this.fechaCompra = fechaCompra;
    }

    public UCompraDisco(float valorCompra, Date fechaCompra, String genero, String nombreCompletoArtista, String disco, int numeroCanciones) {
        this.valorCompra = valorCompra;
        this.fechaCompra = fechaCompra;
        this.genero = genero;
        this.nombreCompletoArtista = nombreCompletoArtista;
        this.disco = disco;
        this.numeroCanciones = numeroCanciones;
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

    public String getDisco() {
        return disco;
    }

    public void setDisco(String disco) {
        this.disco = disco;
    }

    public int getNumeroCanciones() {
        return numeroCanciones;
    }

    public void setNumeroCanciones(int numeroCanciones) {
        this.numeroCanciones = numeroCanciones;
    }
    
}
