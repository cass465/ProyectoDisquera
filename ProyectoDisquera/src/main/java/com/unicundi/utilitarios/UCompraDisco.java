/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.utilitarios;

import java.io.Serializable;
import java.util.Date;

/**
 * Modelo de las compras de disco
 * @author Camilo Sanabria
 * @version 1.0.0
 */
public class UCompraDisco implements Serializable{
    
    /**
     * Id de la compra de disco
     */
    private int id;
    
    /**
     * Id del usuario
     */
    private int idUsuario;
    
    /**
     * Id del disco de la compra
     */
    private int idDisco;
    
    /**
     * Valor de la compra de disco
     */
    private int valorCompra;
    
    /**
     * Fecha de la compra del disco
     */
    private Date fechaCompra;
    
    //Variables no mapeadas
    
    /**
     * Genero al que pertenece el disco
     */
    private String genero;
    
    /**
     * Nombre del artista al que pertenece el disco
     */
    private String nombreCompletoArtista;
    
    /**
     * Nombre textual del disco
     */
    private String disco;
    
    /**
     * Numero de canciones del disco
     */
    private int numeroCanciones;

    /**
     * Constructor clase
     * @param id
     * @param idUsuario
     * @param idDisco
     * @param valorCompra
     * @param fechaCompra
     * @param numeroCanciones 
     */
    public UCompraDisco(int id, int idUsuario, int idDisco, int valorCompra, Date fechaCompra, int numeroCanciones) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idDisco = idDisco;
        this.valorCompra = valorCompra;
        this.fechaCompra = fechaCompra;
        this.numeroCanciones = numeroCanciones;
    }

    /**
     * Constructor de clase
     * @param id
     * @param valorCompra
     * @param fechaCompra
     * @param genero
     * @param nombreCompletoArtista
     * @param disco
     * @param numeroCanciones 
     */
    public UCompraDisco(int id, int valorCompra, Date fechaCompra, String genero, String nombreCompletoArtista, String disco, int numeroCanciones) {
        this.id = id;
        this.valorCompra = valorCompra;
        this.fechaCompra = fechaCompra;
        this.genero = genero;
        this.nombreCompletoArtista = nombreCompletoArtista;
        this.disco = disco;
        this.numeroCanciones = numeroCanciones;
    }
  
    /**
     * Obtiene el id de la compra
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el id de la compra
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el id del usuario
     * @return 
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el id del usuario
     * @param idUsuario 
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene el id del disco
     * @return 
     */
    public int getIdDisco() {
        return idDisco;
    }

    /**
     * Establece el id del disco
     * @param idDisco 
     */
    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;
    }

    /**
     * Obtiene el valor de la compra
     * @return 
     */
    public int getValorCompra() {
        return valorCompra;
    }

    /**
     * Establece el valor de la compra
     * @param valorCompra 
     */
    public void setValorCompra(int valorCompra) {
        this.valorCompra = valorCompra;
    }

    /**
     * Obtiene la fecha de la compra
     * @return 
     */
    public Date getFechaCompra() {
        return fechaCompra;
    }

    /**
     * Establece la fecha de compra
     * @param fechaCompra 
     */
    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    /**
     * Obtiene el genero del disco
     * @return 
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Establece el genero del disco
     * @param genero 
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Obtiene el nombre del artista al que pertenece el disco
     * @return 
     */
    public String getNombreCompletoArtista() {
        return nombreCompletoArtista;
    }

    /**
     * Establece el nombre del artista al que pertenece el disco
     * @param nombreCompletoArtista 
     */
    public void setNombreCompletoArtista(String nombreCompletoArtista) {
        this.nombreCompletoArtista = nombreCompletoArtista;
    }

    /**
     * Obtiene el nombre del disco
     * @return 
     */
    public String getDisco() {
        return disco;
    }

    /**
     * Establece el nombre del disco
     * @param disco 
     */
    public void setDisco(String disco) {
        this.disco = disco;
    }

    /**
     * Obtiene el numero de canciones del disco
     * @return 
     */
    public int getNumeroCanciones() {
        return numeroCanciones;
    }

    /**
     * Establece el numero de canciones del disco
     * @param numeroCanciones 
     */
    public void setNumeroCanciones(int numeroCanciones) {
        this.numeroCanciones = numeroCanciones;
    }
    
}
