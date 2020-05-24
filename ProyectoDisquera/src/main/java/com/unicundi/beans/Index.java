/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.beans;

import com.unicundi.core.CoreUsuario;
import com.unicundi.utilitarios.UUsuario;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author cass465
 */
@Named(value = "index")
@RequestScoped
public class Index implements Serializable {
    private String username;
    private String contrasenia;
    private UUsuario usuarioLogueado;
    /**
     * Creates a new instance of Index
     */
    public Index() {
    }
    
    public void validarSesionAdministrador(ComponentSystemEvent event){
        //Obtiene la sesion de administrador
        UUsuario administrador = (UUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("administrador");
        
        if(administrador == null){
            redireccionar("../index.xhtml");
        }
    }
    
    public void validarSesionUsuario(ComponentSystemEvent event){
        //Obtiene la sesion de usuario
        UUsuario usuario = (UUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        if(usuario == null){
            redireccionar("../index.xhtml");
        }
    }
    
    public void iniciarSesion(){
        new CoreUsuario().iniciarSesion(username, contrasenia);
    }
    
    public void cerrarSesionAdministrador(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("administrador");
        redireccionar("../index.xhtml");
    }
    
    public void cerrarSesionUsuario(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuario");
        redireccionar("../index.xhtml");
    }
    
    public void redireccionar(String url){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public UUsuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(UUsuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }
    
}
