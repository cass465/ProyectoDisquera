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
 *
 * @author cass465
 */
public class UArtista implements Serializable {

    private int id;

    @Pattern(regexp="^[a-zA-Z ]*$")
    @Size(min = 2, max = 20)
    private String nombre;

    @Pattern(regexp="^[a-zA-Z ]*$")
    @Size(min = 2, max = 20)
    private String apellido;

    @Size(min = 2, max = 20)
    private String genero;
    
    @NotNull
    private boolean estado;
    

    
    
    
    public UArtista(){
        
    }

    public UArtista(int id, String nombre, String apellido, String genero, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.estado= estado;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    
    

}
