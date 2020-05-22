/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.core;

import com.unicundi.BD.DAOUsuario;
import com.unicundi.utilitarios.UUsuario;
import java.io.Serializable;

/**
 *
 * @author cass465
 */
public class CoreUsuario implements Serializable{
    
    public UUsuario iniciarSesion(String username, String contrasenia){
        UUsuario usuario = new UUsuario();
        usuario.setUsername(username);
        usuario.setContrasenia(contrasenia);
        return new DAOUsuario().iniciarSesion(usuario);
    }
    
    public UUsuario buscarPorUsername(String username){
        return new DAOUsuario().buscarPorUsername(username);
    }
    
    public void registrar(UUsuario usuario){
        new DAOUsuario().registrar(usuario);
    }
}
