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
    private int nCanciones;
    private float precio;
    private int idArtista;

    public UDisco(int id, String nombre, int nCanciones, float precio, int idArtista) {
        this.id = id;
        this.nombre = nombre;
        this.nCanciones = nCanciones;
        this.precio = precio;
        this.idArtista = idArtista;
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

    public int getnCanciones() {
        return nCanciones;
    }

    public void setnCanciones(int nCanciones) {
        this.nCanciones = nCanciones;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }
    
}