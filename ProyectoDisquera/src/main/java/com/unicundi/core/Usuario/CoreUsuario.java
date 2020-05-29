/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.core.Usuario;

import com.unicundi.BD.DAOUsuario;
import com.unicundi.utilitarios.UUsuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Clase que maneja la logica del usuario
 * @author Camilo Sanabria
 * @version 1.0.0
 */
public class CoreUsuario implements Serializable{
    
    /**
     * Inicia la sesion del usuario
     * @param username Username que esta iniciando sesion
     * @param contrasenia Contraseña del usuario
     */
    public void iniciarSesion(String username, String contrasenia){
        UUsuario usuario = new UUsuario();
        usuario.setUsername(username);
        usuario.setContrasenia(contrasenia);
        UUsuario usuarioLogueado = new DAOUsuario().iniciarSesion(usuario);
        if(usuarioLogueado != null){
            if(usuarioLogueado.getIdRol() == 1){
                //Crea la sesion del administrador
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("administrador", usuarioLogueado);
                //Redirecciona a la pagina de inicio del administrador
                redireccionar("/ProyectoDisquera/faces/Administrador/administrador.xhtml");
            }else{
                //Crea la sesion del usuario
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarioLogueado);
                //Redirecciona a la pagina de inicio del usuario
                redireccionar("/ProyectoDisquera/faces/Usuario/usuario.xhtml");
            }
        }else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "DATOS INCORRECTOS", null);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }
    }
    
    /**
     * Registra los datos de usuarop
     * @param usuario Datos de usuario a registrar
     * @param confirmacionContrasenia Contraseña que es validada para que coincida
     */
    public void registrar(UUsuario usuario, String confirmacionContrasenia){
        UUsuario usuarioValidador = new DAOUsuario().buscarPorUsername(usuario.getUsername());
        if(usuarioValidador == null){
            if(usuario.getContrasenia().equals(confirmacionContrasenia) == true){
                usuario = new UUsuario(0, usuario.getNombre(), usuario.getApellido(), usuario.getUsername(), usuario.getContrasenia(), usuario.getIdRol());
                new DAOUsuario().registrar(usuario);
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "USUARIO " + usuario.getUsername() + " REGISTRADO CON ÉXITO", null);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                redireccionar("index.xhtml");
            }else{
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "LAS CONTRASEÑAS DEBEN COINCIDIR", null);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
            }
        }else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "EL USERNAME YA SE ENCUENTRA REGISTRADO", null);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }
    }
    
    /**
     * Redirecciona a la direccion especificada
     * @param url Direccion a la que se desea ir
     */
    public void redireccionar(String url){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(CoreUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
