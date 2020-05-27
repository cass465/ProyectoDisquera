/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.beans.Usuario;

import com.unicundi.core.Usuario.CoreCompras;
import com.unicundi.utilitarios.UCancion;
import com.unicundi.utilitarios.UDisco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author cass465
 */
@Named(value = "compras")
@ViewScoped
public class Compras implements Serializable {

    private List<UDisco> discosDisponibles;
    private List<UCancion> cancionesDisponibles;
    private List<UDisco> discosAgregados;
    private List<UCancion> cancionesAgregadas;

    private List<UCancion> cancionesDisco;
    private int precioTotal;

    /**
     * Creates a new instance of Compras
     */
    public Compras() {

    }

    @PostConstruct
    public void cargar() {
        this.discosDisponibles = new CoreCompras().listarDiscosDisponibles();
        this.cancionesDisponibles = new CoreCompras().listarCancionesDisponibles();
        this.discosAgregados = new ArrayList<UDisco>();
        this.cancionesAgregadas = new ArrayList<UCancion>();
        this.precioTotal = 0;
    }

    public void buscarCancionesPorDisco(UDisco disco) {
        this.cancionesDisco = new CoreCompras().buscarPorDisco(disco.getId());
        //Abrir modal de canciones
        RequestContext.getCurrentInstance().execute("PF('cancionesDialog').show();");
    }

    public void agregarDisco(UDisco disco) {
        List<UCancion> removerCanciones = new CoreCompras().listarCancionesARemover(cancionesAgregadas, disco);
        
        //Si hay canciones para remover
        if (removerCanciones.size() > 0) {
            this.cancionesDisponibles.addAll(removerCanciones);
            this.cancionesAgregadas.removeAll(removerCanciones);
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "MENSAJE", "SE HAN REMOVIDO LAS CANCIONES DEL DISCO AGREGADO EN LA LISTA DE COMPRAS");
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }
        //Despues de sacar las canciones (si es el caso) se agrega el disco
        this.discosAgregados.add(disco);
        this.discosDisponibles.remove(disco);

        this.precioTotal = new CoreCompras().calcularTotal(discosAgregados, cancionesAgregadas);
    }

    public void desAgregarDisco(UDisco disco) {
        this.discosDisponibles.add(disco);
        this.discosAgregados.remove(disco);

        this.precioTotal = new CoreCompras().calcularTotal(discosAgregados, cancionesAgregadas);
    }

    public void agregarCancion(UCancion cancion) {
        boolean aceptado = new CoreCompras().validarDiscoAgregado(discosAgregados, cancion);

        if (aceptado) {
            UDisco disco = new CoreCompras().obtenerDiscoPorCancion(discosDisponibles, cancion);
            int numeroCanciones = new CoreCompras().contarCancionesAgregadasDeDisco(cancionesAgregadas, disco.getId());
            
            /*Si el numero de canciones agregadas mas la que se está agregando
            es igual al total de canciones del disco entonces agregue el disco y
            desagregue las canciones*/
            if ((numeroCanciones + 1) == disco.getNumeroCanciones()) {
                this.discosAgregados.add(disco);
                this.discosDisponibles.remove(disco);
                
                List<UCancion> cancionesARemover = new CoreCompras().listarCancionesARemover(cancionesAgregadas, disco);
                
                this.cancionesDisponibles.addAll(cancionesARemover);
                this.cancionesAgregadas.removeAll(cancionesARemover);
                
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                        "DISCO AGREGADO!!", "NO SE PUEDEN AGREGAR TODAS LAS CANCIONES DE UN DISCO");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
            } else {
                this.cancionesAgregadas.add(cancion);
                this.cancionesDisponibles.remove(cancion);
            }
        }

        this.precioTotal = new CoreCompras().calcularTotal(discosAgregados, cancionesAgregadas);
    }

    public void desAgregarCancion(UCancion cancion) {
        this.cancionesDisponibles.add(cancion);
        this.cancionesAgregadas.remove(cancion);

        this.precioTotal = new CoreCompras().calcularTotal(discosAgregados, cancionesAgregadas);
    }

    public void comprar() {
        new CoreCompras().registrar(discosAgregados, cancionesAgregadas);
        cargar();
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

    public int getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }

}
