/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.beans;

import com.unicundi.core.CoreUsuario;
import com.unicundi.utilitarios.UUsuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author cass465
 */
@Named(value = "registro")
@ViewScoped
public class Registro implements Serializable{
    private UUsuario usuario;
    private String nombre;
    private String apellido;
    private String username;
    private String contrasenia;
    private String confirmacionContrasenia;
    /**
     * Creates a new instance of Registro
     */
    public Registro() {
    }
    
    public void registrar(){
        UUsuario usuarioValidador = new CoreUsuario().buscarPorUsername(username);
        if(usuarioValidador == null){
            if(contrasenia.equals(confirmacionContrasenia) == true){
                usuario = new UUsuario(0, nombre, apellido, username, contrasenia, 2);
                new CoreUsuario().registrar(usuario);
                redireccionarIndex();
            }else{
                FacesMessage mensaje = new FacesMessage("LAS CONTRASEÑAS DEBEN COINCIDIR");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
            }
        }else{
            FacesMessage mensaje = new FacesMessage("EL USERNAME YA SE ENCUENTRA REGISTRADO");
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }
    }
    
    public void redireccionarIndex(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
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
