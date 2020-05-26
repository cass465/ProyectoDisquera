/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.beans;

import com.unicundi.core.Usuario.CoreUsuario;
import com.unicundi.utilitarios.UUsuario;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author cass465
 */
@Named(value = "registro")
@ViewScoped
public class Registro implements Serializable{
    private UUsuario usuario;
    @Pattern(regexp="^[a-zA-Z ]*$")
    @Size(min = 2, max = 20)
    private String nombre;
    
    @Pattern(regexp="^[a-zA-Z ]*$")
    @Size(min = 2, max = 20)
    private String apellido;
    
    @Pattern(regexp="^[a-zA-Z0-9]*$")
    @Size(min = 2, max = 20)
    private String username;
    
    @Pattern(regexp="^[a-zA-Z0-9]*$")
    @Size(min = 2, max = 20)
    private String contrasenia;
    
    @Pattern(regexp="^[a-zA-Z0-9]*$")
    @Size(min = 2, max = 20)
    private String confirmacionContrasenia;
    /**
     * Creates a new instance of Registro
     */
    public Registro() {
    }
    
    public void registrar(){
        usuario = new UUsuario(0, nombre, apellido, username, contrasenia, 2);
        new CoreUsuario().registrar(usuario, confirmacionContrasenia);
    }
    
    public UUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(UUsuario usuario) {
        this.usuario = usuario;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getConfirmacionContrasenia() {
        return confirmacionContrasenia;
    }

    public void setConfirmacionContrasenia(String confirmacionContrasenia) {
        this.confirmacionContrasenia = confirmacionContrasenia;
    }
    
}
