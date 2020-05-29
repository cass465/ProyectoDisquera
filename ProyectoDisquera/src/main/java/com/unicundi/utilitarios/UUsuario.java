/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.utilitarios;

import java.io.Serializable;

/**
 * Modelo de usuario
 * @author Camilo Sanabria
 * @version 1.0.0
 */
public class UUsuario implements Serializable{
    
    /**
     * Id de usuario
     */
    private int id;
    
    /**
     * Nombre de usuario
     */
    private String nombre; 
    
    /**
     * Apellido de usuario
     */
    private String apellido;
    
    /**
     * Username para entrar al sistema
     */
    private String username;
    
    /**
     * Contraseña con la que ingresa al sistema
     */
    private String contrasenia;
    
    /**
     * Id del rol al que pertenece el usuario (administrador/usuario)
     */
    private int idRol;
    
    /**
     * Constructor de clase
     */
    public UUsuario() {
        
    }
    
    /**
     * Constructor de clase
     * @param id
     * @param nombre
     * @param apellido
     * @param username
     * @param contrasenia
     * @param idRol 
     */
    public UUsuario(int id, String nombre, String apellido, String username, String contrasenia, int idRol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.contrasenia = contrasenia;
        this.idRol = idRol;
    }

    /**
     * Obtiene el id del usuario
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id del usuario
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
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
     * Obtiene el id rol de usuario
     * @return 
     */
    public int getIdRol() {
        return idRol;
    }

    /**
     * Establece el id rol de usuario
     * @param idRol 
     */
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    
}
