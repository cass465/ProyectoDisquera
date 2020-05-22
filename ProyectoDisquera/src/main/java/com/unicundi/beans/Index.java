/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.beans;

import com.unicundi.core.UsuarioService;
import com.unicundi.model.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author cass465
 */
@Named(value = "index")
@SessionScoped
public class Index implements Serializable {
    private String username;
    private String contrasenia;
    private Usuario usuarioLogueado;
    /**
     * Creates a new instance of Index
     */
    public Index() {
    }
    
    public String iniciarSesion(){
        usuarioLogueado = new UsuarioService().iniciarSesion(username, contrasenia);
        if(usuarioLogueado != null){
            if(usuarioLogueado.getIdRol() == 1){
                return "faces/administrador.xhtml?faces-redirect=true";
            }else{
                return "faces/usuario.xhtml?faces-redirect=true";
            }
        }else{
            FacesMessage mensaje = new FacesMessage("DATOS INCORRECTOS");
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
            return "index";
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
    
    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }
    
}
