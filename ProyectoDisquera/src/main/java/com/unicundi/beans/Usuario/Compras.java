/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.beans.Usuario;

import com.unicundi.core.Usuario.CoreCompraCancion;
import com.unicundi.core.Usuario.CoreCompraDisco;
import com.unicundi.utilitarios.UCancion;
import com.unicundi.utilitarios.UDisco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author cass465
 */
@Named(value = "compras")
@ViewScoped
public class Compras implements Serializable{
    private List<UDisco> discosDisponibles;
    private List<UCancion> cancionesDisponibles;
    private List<UDisco> discosAgregados;
    private List<UCancion> cancionesAgregadas;
    
    private List<UCancion> cancionesDisco;
    
    /**
     * Creates a new instance of Compras
     */
    public Compras() {
        this.discosDisponibles = new CoreCompraDisco().listarDiscosDisponibles();
        this.cancionesDisponibles = new CoreCompraCancion().listarCancionesDisponibles();
        this.discosAgregados = new ArrayList<UDisco>();
        this.cancionesAgregadas = new ArrayList<UCancion>();
    }
    
    public void buscarCancionesPorDisco(UDisco disco){
        this.cancionesDisco = new CoreCompraDisco().buscarPorDisco(disco.getId());
        //Abrir modal de canciones
        RequestContext.getCurrentInstance().execute("PF('cancionesDialog').show();");
    }
    
    public void agregarDisco(UDisco disco){
        this.discosAgregados.add(disco);
        this.discosDisponibles.remove(disco);
    }
    
    public void desAgregarDisco(UDisco disco){
        this.discosDisponibles.add(disco);
        this.discosAgregados.remove(disco);
    }
    
    public void agregarCancion(UCancion cancion){
        this.cancionesAgregadas.add(cancion);
        this.cancionesDisponibles.remove(cancion);
    }
    
    public void desAgregarCancion(UCancion cancion){
        this.cancionesDisponibles.add(cancion);
        this.cancionesAgregadas.remove(cancion);
    }
    
    public List<UDisco> getDiscosDisponibles() {
        return discosDisponibles;
    }

    public void setDiscosDisponibles(List<UDisco> discosDisponibles) {
        this.discosDisponibles = discosDisponibles;
    }

    public List<UCancion> getCancionesDisponibles() {
        return cancionesDisponibles;
    }

    public void setCancionesDisponibles(List<UCancion> cancionesDisponibles) {
        this.cancionesDisponibles = cancionesDisponibles;
    }

    public List<UDisco> getDiscosAgregados() {
        return discosAgregados;
    }

    public void setDiscosAgregados(List<UDisco> discosAgregados) {
        this.discosAgregados = discosAgregados;
    }

    public List<UCancion> getCancionesAgregadas() {
        return cancionesAgregadas;
    }

    public void setCancionesAgregadas(List<UCancion> cancionesAgregadas) {
        this.cancionesAgregadas = cancionesAgregadas;
    }

    public List<UCancion> getCancionesDisco() {
        return cancionesDisco;
    }

    public void setCancionesDisco(List<UCancion> cancionesDisco) {
        this.cancionesDisco = cancionesDisco;
    }
    
}
