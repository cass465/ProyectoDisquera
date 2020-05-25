/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.beans;

import com.unicundi.core.CoreCancion;
import com.unicundi.core.CoreCompraCancion;
import com.unicundi.utilitarios.UCancion;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author cass465
 */
@Named(value = "compraCancion")
@ViewScoped
public class CompraCancion implements Serializable{
    private List<UCancion> canciones;
    /**
     * Creates a new instance of CompraCancion
     */
    public CompraCancion() {
        this.canciones = new CoreCancion().listar();
    }

    public void comprar(UCancion cancion){
        new CoreCompraCancion().registrar(cancion);
    }
    
    public List<UCancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<UCancion> canciones) {
        this.canciones = canciones;
    }
    
}
