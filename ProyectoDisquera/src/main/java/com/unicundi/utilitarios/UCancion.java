/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.utilitarios;

import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author cass465
 */
public class UCancion implements Serializable {

    private int id;

    @Pattern(regexp = "^([a-zA-Z0-9 ])*$")
    @Size(min = 2, max = 20)
    private String nombre;

    @Pattern(regexp = "^[0-9]+([:])?([0-9]+)?$")
    private String duracion;

    @Min(1000)
    @Max(10000000)
    private int precio;

    @NotNull
    private int idDisco;

    @NotNull
    private boolean estado;

    private String nombreDisco;
    private String genero;
    private String nombreArtista;

    public UCancion() {

    }

    public UCancion(int id, String nombre, String duracion, int precio, int idDisco, boolean estado, String nombreDisco) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.precio = precio;
        this.idDisco = idDisco;
        this.estado = estado;
        this.nombreDisco = nombreDisco;
    }

    public UCancion(int id, String nombre, String duracion, int precio, int idDisco, String genero, String nombreArtista) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.precio = precio;
        this.idDisco = idDisco;
        this.genero = genero;
        this.nombreArtista = nombreArtista;
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

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getIdDisco() {
        return idDisco;
    }

    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;

    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getNombreDisco() {
        return nombreDisco;
    }

    public void setNombreDisco(String nombreDisco) {
        this.nombreDisco = nombreDisco;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }
    
}
