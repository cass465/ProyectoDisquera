/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.utilitarios;

import java.io.Serializable;

/**
 *
 * @author cass465
 */
public class UCompraDiscoCancion implements Serializable{
    private int id;
    private int idCompraDisco;
    private int idCancion;
    //
    private String cancion;
    private String duracion;

    public UCompraDiscoCancion(int id, int idCompraDisco, int idCancion) {
        this.id = id;
        this.idCompraDisco = idCompraDisco;
        this.idCancion = idCancion;
    }

    public UCompraDiscoCancion(String cancion, String duracion) {
        this.cancion = cancion;
        this.duracion = duracion;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCompraDisco() {
        return idCompraDisco;
    }

    public void setIdCompraDisco(int idCompraDisco) {
        this.idCompraDisco = idCompraDisco;
    }

    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public String getCancion() {
        return cancion;
    }

    public void setCancion(String cancion) {
        this.cancion = cancion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
    
}
