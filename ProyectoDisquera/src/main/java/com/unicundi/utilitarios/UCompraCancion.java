/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.utilitarios;

import java.io.Serializable;
import java.util.Date;

/**
 * Modelo de las compras de canciones
 * @author Camilo Sanabria
 * @version 1.0.0
 */
public class UCompraCancion implements Serializable{
    
    /**
     * Id de compra de cancion
     */
    private int id;
    
    /**
     * Id del usuario al que pertenece la compra
     */
    private int idUsuario;
    
    /**
     * Id de la cancion que se compra
     */
    private int idCancion;
    
    /**
     * Valor de la compra de cancion
     */
    private int valorCompra;
    
    /**
     * Fecha en que se registra la compra
     */
    private Date fechaCompra;
    
    //Variables no mapeadas
    
    /**
     * Genero al que pertenece la cancion
     */
    private String genero;
    
    /**
     * Nombre del artista al que pertenece la cancion
     */
    private String nombreCompletoArtista;
    
    /**
     * Nombre textual de la cancion
     */
    private String cancion;
    
    /**
     * Duracion de la cancion
     */
    private String duracion;

    /**
     * Constructor de Clase
     * @param id
     * @param idUsuario
     * @param idCancion
     * @param valorCompra
     * @param fechaCompra 
     */
    public UCompraCancion(int id, int idUsuario, int idCancion, int valorCompra, Date fechaCompra) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idCancion = idCancion;
        this.valorCompra = valorCompra;
        this.fechaCompra = fechaCompra;
    }

    /**
     * Constructor de clase
     * @param valorCompra
     * @param fechaCompra
     * @param genero
     * @param nombreCompletoArtista
     * @param cancion
     * @param duracion 
     */
    public UCompraCancion(int valorCompra, Date fechaCompra, String genero, String nombreCompletoArtista, String cancion, String duracion) {
        this.valorCompra = valorCompra;
        this.fechaCompra = fechaCompra;
        this.genero = genero;
        this.nombreCompletoArtista = nombreCompletoArtista;
        this.cancion = cancion;
        this.duracion = duracion;
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
     * Obtiene el id de la cancion
     * @return 
     */
    public int getIdCancion() {
        return idCancion;
    }

    /**
     * Establece el id de la cancion
     * @param idCancion 
     */
    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
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
     * Obtiene la fecha de compra
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
     * Obtiene el genero de la cancion
     * @return 
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Establece el genero de la cancion
     * @param genero 
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Obtiene el nombre del artista de la cancion
     * @return 
     */
    public String getNombreCompletoArtista() {
        return nombreCompletoArtista;
    }

    /**
     * Establece el nombre del artista de la cancion
     * @param nombreCompletoArtista 
     */
    public void setNombreCompletoArtista(String nombreCompletoArtista) {
        this.nombreCompletoArtista = nombreCompletoArtista;
    }

    /**
     * Obtiene el nombre de la cancion
     * @return 
     */
    public String getCancion() {
        return cancion;
    }

    /**
     * Establece el nombre de la cancion
     * @param cancion 
     */
    public void setCancion(String cancion) {
        this.cancion = cancion;
    }

    /**
     * Obtiene la duracion de la cancion
     * @return 
     */
    public String getDuracion() {
        return duracion;
    }

    /**
     * Establece la duracion de la cancion
     * @param duracion 
     */
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
    
}
