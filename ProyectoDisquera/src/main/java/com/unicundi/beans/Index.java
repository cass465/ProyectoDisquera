/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.beans;

import com.unicundi.core.Usuario.CoreUsuario;
import com.unicundi.utilitarios.UUsuario;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Managed Bean de la vista index.xhtml (login)
 * @author Camilo Sanabria
 * @version 1.0.0
 */
@Named(value = "index")
@RequestScoped
public class Index implements Serializable {
    
    /**
     * Username de logueo
     */
    @Pattern(regexp="^[a-zA-Z0-9]*$")
    @Size(min = 2, max = 20)
    private String username;
    
    /**
     * Contraseña de logueo
     */
    @Pattern(regexp="^[a-zA-Z0-9]*$")
    @Size(min = 2, max = 20)
    private String contrasenia;
    
    /**
     * Datos de usuario logueado
     */
    private UUsuario usuarioLogueado;
    
    /**
     * Constructor de clase
     */
    public Index() {
    }
    
    /**
     * Valida la sesion de las vistas de administrador
     * @param event 
     */
    public void validarSesionAdministrador(ComponentSystemEvent event){
        //Obtiene la sesion de administrador
        UUsuario administrador = (UUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("administrador");
        
        if(administrador == null){
            redireccionar("../index.xhtml");
        }
    }
    
    /**
     * Valida la sesion de las vistas de usuario
     * @param event 
     */
    public void validarSesionUsuario(ComponentSystemEvent event){
        //Obtiene la sesion de usuario
        UUsuario usuario = (UUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        if(usuario == null){
            redireccionar("../index.xhtml");
        }
    }
    
    /**
     * Inicia la sesion de usuario segun sea el rol
     */
    public void iniciarSesion(){
        new CoreUsuario().iniciarSesion(username, contrasenia);
    }
    
    /**
     * Cierra la sesion del rol administrador
     */
    public void cerrarSesionAdministrador(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("administrador");
        redireccionar("../index.xhtml");
    }
    
    /**
     * Cierra la sesion del rol usuario
     */
    public void cerrarSesionUsuario(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuario");
        redireccionar("../index.xhtml");
    }
    
    /**
     * Redirecciona a la direccion especificada
     * @param url Direccion a la que se desea ir
     */
    public void redireccionar(String url){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
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
     * Obtiene el usuario logueado
     * @return 
     */
    public UUsuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    /**
     * Establece el usuario logueado
     * @param usuarioLogueado 
     */
    public void setUsuarioLogueado(UUsuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }
    
}
