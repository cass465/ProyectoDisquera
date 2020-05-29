/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.utilitarios;

import java.io.Serializable;

/**
 * Modelo de las compras de canciones con el disco
 * @author Camilo Sanabria
 * @version 1.0.0
 */
public class UCompraDiscoCancion implements Serializable{
    
    /**
     * Id de la compra
     */
    private int id;
    
    /**
     * Id de la compra del disco
     */
    private int idCompraDisco;
    
    /**
     * Id de la cancion de la compra
     */
    private int idCancion;
    
    //Variables no mapeadas
    
    /**
     * Nombre textual de la cancion de la compra
     */
    private String cancion;
    
    /**
     * Duracion de la cancion de la compra
     */
    private String duracion;

    /**
     * Constructor de clase
     * @param id
     * @param idCompraDisco
     * @param idCancion 
     */
    public UCompraDiscoCancion(int id, int idCompraDisco, int idCancion) {
        this.id = id;
        this.idCompraDisco = idCompraDisco;
        this.idCancion = idCancion;
    }

    /**
     * Constructor de clase
     * @param cancion
     * @param duracion 
     */
    public UCompraDiscoCancion(String cancion, String duracion) {
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
     * Obtiene el id de la compra de disco
     * @return 
     */
    public int getIdCompraDisco() {
        return idCompraDisco;
    }

    /**
     * Establece el id de la compra del disco
     * @param idCompraDisco 
     */
    public void setIdCompraDisco(int idCompraDisco) {
        this.idCompraDisco = idCompraDisco;
    }

    /**
     * Obtiene el id de la cancion de la compra
     * @return 
     */
    public int getIdCancion() {
        return idCancion;
    }

    /**
     * Establece el id de la cancion de la compra
     * @param idCancion 
     */
    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
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
