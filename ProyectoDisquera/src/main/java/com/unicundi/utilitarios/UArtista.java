/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.utilitarios;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Clase que encapsula los valores del artista
 *
 * @author Yeison Cifuentes
 * @version 1.0.0
 */
public class UArtista implements Serializable {

    /**
     * Id del artista en la BD
     */
    private int id;

    /**
     * Nombre
     */
    @Pattern(regexp = "^[a-zA-Z ]*$")
    @Size(min = 2, max = 30)
    private String nombre;

    /**
     * Apellido
     */
    @Pattern(regexp = "^[a-zA-Z ]*$")
    @Size(min = 2, max = 30)
    private String apellido;

    /**
     * Genero que canta
     */
    @Pattern(regexp = "^([a-zA-Z0-9 ])*$")
    @Size(min = 2, max = 30)
    private String genero;

    /**
     * Estado del artista
     */
    @NotNull
    private boolean estado;

    /**
     * Constructor vacio
     */
    public UArtista() {

    }

    /**
     * Constructor con valores
     *
     * @param id
     * @param nombre
     * @param apellido
     * @param genero
     * @param estado
     */
    public UArtista(int id, String nombre, String apellido, String genero, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.estado = estado;
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
     * Obtiene el apellido
     *
     * @return
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido
     *
     * @param apellido
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
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

}
