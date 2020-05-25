/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.utilitarios;

import java.io.Serializable;

/**
 *
 * @author cass465
 */
public class UDisco implements Serializable{
    private int id;
    private String nombre;
    private int numeroCanciones;
    private int precio;
    private int idArtista;
    private boolean estado;
    //
    private boolean seleccionado;
    private String nombreCompletoArtista;
    private String genero;

    public UDisco(int id, String nombre, int numeroCanciones, int precio, int idArtista, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.numeroCanciones = numeroCanciones;
        this.precio = precio;
        this.idArtista = idArtista;
        this.estado = estado;
    }

    public UDisco(int id, String nombre, int numeroCanciones, int precio, int idArtista, String nombreCompletoArtista, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.numeroCanciones = numeroCanciones;
        this.precio = precio;
        this.idArtista = idArtista;
        this.nombreCompletoArtista = nombreCompletoArtista;
        this.genero = genero;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroCanciones() {
        return numeroCanciones;
    }

    public void setNumeroCanciones(int numeroCanciones) {
        this.numeroCanciones = numeroCanciones;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public String getNombreCompletoArtista() {
        return nombreCompletoArtista;
    }

    public void setNombreCompletoArtista(String nombreCompletoArtista) {
        this.nombreCompletoArtista = nombreCompletoArtista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
}
