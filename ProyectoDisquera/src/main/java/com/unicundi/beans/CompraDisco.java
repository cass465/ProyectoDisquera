/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.beans;

import com.unicundi.core.Usuario.CoreCompraDisco;
import com.unicundi.utilitarios.UCancion;
import com.unicundi.utilitarios.UDisco;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author cass465
 */
@Named(value = "compraDisco")
@ViewScoped
public class CompraDisco implements Serializable{
    private List<UDisco> discos;
    private List<UCancion> canciones;
    
    /**
     * Creates a new instance of CompraDisco
     */
    public CompraDisco() {
        this.discos = new CoreCompraDisco().listarDiscosDisponibles();
    }
    
    public void comprar(){
        new CoreCompraDisco().registrar(discos);
    }
    
    public void buscarCancionesPorDisco(UDisco disco){
        //this.canciones = new CoreCancion().buscarPorDisco(disco.getId());
        //Abrir modal de canciones
        RequestContext.getCurrentInstance().execute("PF('cancionesDialog').show();");
    }

    public List<UDisco> getDiscos() {
        return discos;
    }

    public void setDiscos(List<UDisco> discos) {
        this.discos = discos;
    }

    public List<UCancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<UCancion> canciones) {
        this.canciones = canciones;
    }
    
}
