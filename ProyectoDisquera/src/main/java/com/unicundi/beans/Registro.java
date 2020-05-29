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
 * Managed Bean de la vista registro.xhtml
 * @author Camilo Sanabria
 * @version 1.0.0
 */
@Named(value = "registro")
@ViewScoped
public class Registro implements Serializable{
    
    /**
     * Usuario que se registra
     */
    private UUsuario usuario;
    
    /**
     * Nombre de usuario
     */
    @Pattern(regexp="^[a-zA-Z ]*$")
    @Size(min = 2, max = 30)
    private String nombre;
    
    /**
     * Apellido de usuario
     */
    @Pattern(regexp="^[a-zA-Z ]*$")
    @Size(min = 2, max = 30)
    private String apellido;
    
    /**
     * Username de usuario
     */
    @Pattern(regexp="^[a-zA-Z0-9]*$")
    @Size(min = 2, max = 30)
    private String username;
    
    /**
     * Contraseña de usuario
     */
    @Pattern(regexp="^[a-zA-Z0-9]*$")
    @Size(min = 2, max = 20)
    private String contrasenia;
    
    /**
     * Confirmacion de contraseña de usuario
     */
    @Pattern(regexp="^[a-zA-Z0-9]*$")
    @Size(min = 2, max = 20)
    private String confirmacionContrasenia;
    
    /**
     * Constructor de clase
     */
    public Registro() {
    }
    
    /**
     * Registra los datos de usuario
     */
    public void registrar(){
        usuario = new UUsuario(0, nombre, apellido, username, contrasenia, 2);
        new CoreUsuario().registrar(usuario, confirmacionContrasenia);
    }
    
    /**
     * Obtiene el usuario registrado
     * @return 
     */
    public UUsuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario registrado
     * @param usuario 
     */
    public void setUsuario(UUsuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene el nombre de usuario
     * @return 
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de usuario
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido de usuario
     * @return 
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido de usuario
     * @param apellido 
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene el username de usuario
     * @return 
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el username de usuario
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene la contraseña de usuario
     * @return 
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Establece la contraseña de usuario
     * @param contrasenia 
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Obtiene la confirmacion de contraseña de usuario
     * @return 
     */
    public String getConfirmacionContrasenia() {
        return confirmacionContrasenia;
    }

    /**
     * Establece la confirmacion de contraseña de usuario
     * @param confirmacionContrasenia 
     */
    public void setConfirmacionContrasenia(String confirmacionContrasenia) {
        this.confirmacionContrasenia = confirmacionContrasenia;
    }
    
}
