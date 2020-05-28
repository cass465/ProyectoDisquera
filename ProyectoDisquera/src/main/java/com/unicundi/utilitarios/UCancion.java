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
 * Clase que encapsula los valores de la cancion
 *
 * @author Yeison Cifuentes
 * @version 1.0.0
 */
public class UCancion implements Serializable {

    /**
     * Id de la cancion
     */
    private int id;

    /**
     * Nombre de la cancion
     */
    @Pattern(regexp = "^([a-zA-Z0-9 ])*$")
    @Size(min = 2, max = 30)
    private String nombre;

    /**
     * Duracion de la cancion
     */
    @Pattern(regexp = "^([2-9]):[0-5][0-9]$", message = "Duracion no valida debe estar entre 2:00 a 9:00 minutos")
    private String duracion;

    /**
     * Precio de la cancion
     */
    @Min(1000)
    @Max(10000000)
    private int precio;

    /**
     * Id del disco al que pertenece la cancion
     */
    @NotNull
    private int idDisco;

    /**
     * Estado de la cancion
     */
    @NotNull
    private boolean estado;

    /**
     * Nombre del disco
     */
    private String nombreDisco;

    /**
     * Genero de la cancion
     */
    private String genero;

    /**
     * Nombre del artista
     */
    private String nombreArtista;

    /**
     * Constructor vacio
     */
    public UCancion() {

    }

    /**
     * Constructor con los valores
     *
     * @param id
     * @param nombre
     * @param duracion
     * @param precio
     * @param idDisco
     * @param estado
     * @param nombreDisco
     */
    public UCancion(int id, String nombre, String duracion, int precio, int idDisco, boolean estado, String nombreDisco) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.precio = precio;
        this.idDisco = idDisco;
        this.estado = estado;
        this.nombreDisco = nombreDisco;
    }

    /**
     * Constructo con valores
     *
     * @param id
     * @param nombre
     * @param duracion
     * @param precio
     * @param idDisco
     * @param genero
     * @param nombreArtista
     */
    public UCancion(int id, String nombre, String duracion, int precio, int idDisco, String genero, String nombreArtista) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.precio = precio;
        this.idDisco = idDisco;
        this.genero = genero;
        this.nombreArtista = nombreArtista;
    }

    /**
     * Constructor con valores
     *
     * @param id
     * @param idDisco
     * @param nombre
     * @param duracion
     * @param precio
     * @param nombreDisco
     * @param genero
     * @param nombreArtista
     */
    public UCancion(int id, int idDisco, String nombre, String duracion, int precio, String nombreDisco, String genero, String nombreArtista) {
        this.id = id;
        this.idDisco = idDisco;
        this.nombre = nombre;
        this.duracion = duracion;
        this.precio = precio;
        this.nombreDisco = nombreDisco;
        this.genero = genero;
        this.nombreArtista = nombreArtista;
    }

    /**
     * Obtiene el id
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la duracion
     *
     * @return
     */
    public String getDuracion() {
        return duracion;
    }

    /**
     * Establece la duracion
     *
     * @param duracion
     */
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    /**
     * Obtiene el precio
     *
     * @return
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Establece el precio
     *
     * @param precio
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el id del disco
     *
     * @return
     */
    public int getIdDisco() {
        return idDisco;
    }

    /**
     * Establece el valor del id del disco
     *
     * @param idDisco
     */
    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;

    }

    /**
     * Obtiene el estado
     *
     * @return
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Establece el valor del estado
     *
     * @param estado
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el nombre del disco
     *
     * @return
     */
    public String getNombreDisco() {
        return nombreDisco;
    }

    /**
     * Establece el nombre del disoc
     *
     * @param nombreDisco
     */
    public void setNombreDisco(String nombreDisco) {
        this.nombreDisco = nombreDisco;
    }

    /**
     * Obtiene el genero de la cancion
     *
     * @return
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Establece el genero de la cancion
     *
     * @param genero
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     *Obtiene el nombre del artista
     * @return
     */
    public String getNombreArtista() {
        return nombreArtista;
    }

    /**
     * Estable el nombre del artista
     * @param nombreArtista 
     */
    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

}
