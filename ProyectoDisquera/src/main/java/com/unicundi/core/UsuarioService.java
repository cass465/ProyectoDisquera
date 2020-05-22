/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.core;

import com.unicundi.BD.DAOUsuario;
import com.unicundi.model.Usuario;
import java.io.Serializable;

/**
 *
 * @author cass465
 */
public class UsuarioService implements Serializable{
    
    public Usuario iniciarSesion(String username, String contrasenia){
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setContrasenia(contrasenia);
        return new DAOUsuario().iniciarSesion(usuario);
    }
    
    public Usuario buscarPorUsername(String username){
        return new DAOUsuario().buscarPorUsername(username);
    }
    
    public void registrar(Usuario usuario){
        new DAOUsuario().registrar(usuario);
    }
}
