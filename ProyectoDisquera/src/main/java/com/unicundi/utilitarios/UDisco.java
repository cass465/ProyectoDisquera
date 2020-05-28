/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.utilitarios;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author cass465
 */
public class UDisco implements Serializable {

    private int id;

    @Pattern(regexp = "^([a-zA-Z0-9 ])*$")
    @Size(min = 2, max = 30)
    private String nombre;

    private int numeroCanciones;

    @Min(1000)
    @Max(10000000)
    private int precio;

    @NotNull
    private int idArtista;
    
    //
    @NotNull
    private boolean estado;

    private boolean seleccionado;
    private String nombreArtista;
    private String genero;
    private List<UCancion> cancionesDisco;
    
    public UDisco() {

    }

    public UDisco(int id, String nombre, int numeroCanciones, int precio, int idArtista, boolean estado, String nombreArtista) {
        this.id = id;
        this.nombre = nombre;
        this.numeroCanciones = numeroCanciones;
        this.precio = precio;
        this.idArtista = idArtista;
        this.estado = estado;
        this.nombreArtista=nombreArtista;
    }

    public UDisco(int id, String nombre, int numeroCanciones, int precio, int idArtista, 
            String nombreArtista, String genero, List<UCancion> cancionesDisco) {
        this.id = id;
        this.nombre = nombre;
        this.numeroCanciones = numeroCanciones;
        this.precio = precio;
        this.idArtista = idArtista;
        this.nombreArtista = nombreArtista;
        this.genero = genero;
        this.cancionesDisco = cancionesDisco;
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

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public List<UCancion> getCancionesDisco() {
        return cancionesDisco;
    }

    public void setCancionesDisco(List<UCancion> cancionesDisco) {
        this.cancionesDisco = cancionesDisco;
    }
    
}
