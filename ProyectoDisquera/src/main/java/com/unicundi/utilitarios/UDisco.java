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
 * Clase que encapsula los valores del disco
 *
 * @author Yeison Cifuentes
 * @version 1.0.0
 */
public class UDisco implements Serializable {

    /**
     * Id del disco
     */
    private int id;

    /**
     * Nombre
     */
    @Pattern(regexp = "^([a-zA-Z0-9 ])*$")
    @Size(min = 2, max = 30)
    private String nombre;

    /**
     * Numero de canciones
     */
    private int numeroCanciones;

    /**
     * Precio
     */
    @Min(1000)
    @Max(10000000)
    private int precio;

    /**
     * Id del artista al que pertenece el disco
     */
    @NotNull
    private int idArtista;

    /**
     * Estado del disco
     */
    @NotNull
    private boolean estado;

    /**
     * Seleccionado, para validar
     */
    private boolean seleccionado;

    /**
     * Nombre del artista
     */
    private String nombreArtista;

    /**
     * Genero
     */
    private String genero;

    /**
     * Lista de canciones del disco
     */
    private List<UCancion> cancionesDisco;

    /**
     * Constructor vacio
     */
    public UDisco() {

    }

    /**
     * Constructor con valores
     *
     * @param id
     * @param nombre
     * @param numeroCanciones
     * @param precio
     * @param idArtista
     * @param estado
     * @param nombreArtista
     */
    public UDisco(int id, String nombre, int numeroCanciones, int precio, int idArtista, boolean estado, String nombreArtista) {
        this.id = id;
        this.nombre = nombre;
        this.numeroCanciones = numeroCanciones;
        this.precio = precio;
        this.idArtista = idArtista;
        this.estado = estado;
        this.nombreArtista = nombreArtista;
    }

    /**
     * Constructor con valores
     *
     * @param id
     * @param nombre
     * @param numeroCanciones
     * @param precio
     * @param idArtista
     * @param nombreArtista
     * @param genero
     * @param cancionesDisco
     */
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
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el numero de canciones
     *
     * @return
     */
    public int getNumeroCanciones() {
        return numeroCanciones;
    }

    /**
     * Establece el numero de canciones
     *
     * @param numeroCanciones
     */
    public void setNumeroCanciones(int numeroCanciones) {
        this.numeroCanciones = numeroCanciones;
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
     * Obtiene el id del artista
     *
     * @return
     */
    public int getIdArtista() {
        return idArtista;
    }

    /**
     * Establece el id dle artista
     *
     * @param idArtista
     */
    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
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
     * Establece el estado
     *
     * @param estado
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el valor
     *
     * @return
     */
    public boolean isSeleccionado() {
        return seleccionado;
    }

    /**
     * Establece el valor
     *
     * @param seleccionado
     */
    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    /**
     * Obtiene el nombre del artista
     *
     * @return
     */
    public String getNombreArtista() {
        return nombreArtista;
    }

    /**
     * Establece el nombre del artista
     *
     * @param nombreArtista
     */
    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    /**
     * Obtiene el genero
     *
     * @return
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Establece el genero
     *
     * @param genero
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Obtiene la lista de las canciones
     *
     * @return
     */
    public List<UCancion> getCancionesDisco() {
        return cancionesDisco;
    }

    /**
     * Establece la lista de las canciones
     *
     * @param cancionesDisco
     */
    public void setCancionesDisco(List<UCancion> cancionesDisco) {
        this.cancionesDisco = cancionesDisco;
    }

}
