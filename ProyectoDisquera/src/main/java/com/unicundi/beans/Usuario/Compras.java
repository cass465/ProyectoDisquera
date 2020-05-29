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
 * Managed Bean de la vista compras.xhtml
 * @author Camilo Sanabria
 * @version 1.0.0
 */
@Named(value = "compras")
@ViewScoped
public class Compras implements Serializable {

    /**
     * Lista de discos disponibles
     */
    private List<UDisco> discosDisponibles;
    
    /**
     * Lista de canciones disponibles
     */
    private List<UCancion> cancionesDisponibles;
    
    /**
     * Lista de discos agregados para comprar
     */
    private List<UDisco> discosAgregados;
    
    /**
     * Lista de canciones agregadas para comprar
     */
    private List<UCancion> cancionesAgregadas;

    /**
     * Lista de canciones segun el disco consultado
     */
    private List<UCancion> cancionesDisco;
    
    /**
     * Precio total de la compra de discos y canciones
     */
    private int precioTotal;

    /**
     * Lista que almacena el filtro de discos disponibles
     */
    private List<UDisco> filtroDiscosDisponibles;
    
    /**
     * Lista que almacena el filtro de canciones disponibles
     */
    private List<UCancion> filtroCancionesDisponibles;
    
    /**
     * Lista que almacena el filtro de discos agregados
     */
    private List<UDisco> filtroDiscosAgregados;
    
    /**
     * Lista que almacena el filtro de canciones agregadas
     */
    private List<UCancion> filtroCancionesAgregadas;

    /**
     * Constructor del Managed Bean Compras
     */
    public Compras() {

    }

    /**
     * Carga las listas despues de ejecutar el contructor
     */
    @PostConstruct
    public void cargar() {
        this.discosDisponibles = new CoreCompras().listarDiscosDisponibles();
        this.cancionesDisponibles = new CoreCompras().listarCancionesDisponibles();
        this.discosAgregados = new ArrayList<UDisco>();
        this.cancionesAgregadas = new ArrayList<UCancion>();

        this.precioTotal = 0;
    }

    /**
     * Busca las canciones del disco especificado y las agrega a la lista
     * @param disco Disco al que se le desea buscar sus canciones
     */
    public void buscarCancionesPorDisco(UDisco disco) {
        this.cancionesDisco = disco.getCancionesDisco();
        //Abrir modal de canciones
        RequestContext.getCurrentInstance().execute("PF('cancionesDialog').show();");
    }

    /**
     * Agrega el disco disponible a lista de agregados, 
     * haciendo las validaciones respectivas
     * @param disco Disco que se desea agregar
     */
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

    /**
     * Desagrega el disco agregado a lista de disponibles
     * @param disco Disco que se desea desagregar
     */
    public void desAgregarDisco(UDisco disco) {
        this.discosDisponibles.add(disco);
        this.discosAgregados.remove(disco);

        this.precioTotal = new CoreCompras().calcularTotal(discosAgregados, cancionesAgregadas);
    }

    /**
     * Agrega la cancion disponible a lista de agregadas,
     * haciendo las validaciones correspondientes
     * @param cancion Cancion que se desea agregar
     */
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

    /**
     * Desagrega la cancion agregada a lista de disponibles
     * @param cancion Cancion que se desea desagregar
     */
    public void desAgregarCancion(UCancion cancion) {
        this.cancionesDisponibles.add(cancion);
        this.cancionesAgregadas.remove(cancion);

        this.precioTotal = new CoreCompras().calcularTotal(discosAgregados, cancionesAgregadas);
    }

    /**
     * Registra las compras de discos y canciones, luego reinicia nuevamente las listas
     */
    public void comprar() {
        new CoreCompras().registrar(discosAgregados, cancionesAgregadas);
        cargar();
    }

    /**
     * Obtiene los discos disponibles
     * @return Discos disponibles
     */
    public List<UDisco> getDiscosDisponibles() {
        return discosDisponibles;
    }

    /**
     * Establece los discos disponibles
     * @param discosDisponibles Lista para establecer los discos disponibles
     */
    public void setDiscosDisponibles(List<UDisco> discosDisponibles) {
        this.discosDisponibles = discosDisponibles;
    }

    /**
     * Obtiene las canciones disponibles
     * @return Canciones disponibles
     */
    public List<UCancion> getCancionesDisponibles() {
        return cancionesDisponibles;
    }

    /**
     * Establece las canciones disponibles
     * @param cancionesDisponibles Lista de canciones a establecer
     */
    public void setCancionesDisponibles(List<UCancion> cancionesDisponibles) {
        this.cancionesDisponibles = cancionesDisponibles;
    }

    /**
     * Obtiene los discos agregados
     * @return Discos agregados
     */
    public List<UDisco> getDiscosAgregados() {
        return discosAgregados;
    }

    /**
     * Establece los discos agregados
     * @param discosAgregados Lista de discos a establecer
     */
    public void setDiscosAgregados(List<UDisco> discosAgregados) {
        this.discosAgregados = discosAgregados;
    }

    /**
     * Obtiene las canciones agregadas
     * @return Canciones agregadas
     */
    public List<UCancion> getCancionesAgregadas() {
        return cancionesAgregadas;
    }

    /**
     * Establece las canciones agregadas
     * @param cancionesAgregadas Lista de canciones a establecer
     */
    public void setCancionesAgregadas(List<UCancion> cancionesAgregadas) {
        this.cancionesAgregadas = cancionesAgregadas;
    }

    /**
     * Obtiene las canciones segun el disco
     * @return Canciones segun disco consultado
     */
    public List<UCancion> getCancionesDisco() {
        return cancionesDisco;
    }

    /**
     * Establece canciones segun el disco
     * @param cancionesDisco Lista de canciones a establecer
     */
    public void setCancionesDisco(List<UCancion> cancionesDisco) {
        this.cancionesDisco = cancionesDisco;
    }

    /**
     * Obtiene el precio total de compra
     * @return Precio total de compra
     */
    public int getPrecioTotal() {
        return precioTotal;
    }

    /**
     * Establece precio total de compra
     * @param precioTotal Precio total calculado
     */
    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }

    /**
     * Obtiene los discos disponibles que han sido filtrados
     * @return Discos disponibles que han sido filtrados
     */
    public List<UDisco> getFiltroDiscosDisponibles() {
        return filtroDiscosDisponibles;
    }

    /**
     * Establece los discos disponibles segun el filtro
     * @param filtroDiscosDisponibles Lista de discos a establecer
     */
    public void setFiltroDiscosDisponibles(List<UDisco> filtroDiscosDisponibles) {
        this.filtroDiscosDisponibles = filtroDiscosDisponibles;
    }

    /**
     * Obtiene las canciones disponibles que han sido filtradas
     * @return Canciones isponibles que han sido filtradas
     */
    public List<UCancion> getFiltroCancionesDisponibles() {
        return filtroCancionesDisponibles;
    }

    /**
     * Establece las canciones disponibles filtradas
     * @param filtroCancionesDisponibles Lista de canciones a establecer
     */
    public void setFiltroCancionesDisponibles(List<UCancion> filtroCancionesDisponibles) {
        this.filtroCancionesDisponibles = filtroCancionesDisponibles;
    }

    /**
     * Obtiene los discos agregados que han sido filtrados
     * @return Discos agregados que han sido filtrados
     */
    public List<UDisco> getFiltroDiscosAgregados() {
        return filtroDiscosAgregados;
    }

    /**
     * Establece los discos agregados segun el filtro
     * @param filtroDiscosAgregados Lista de discos a establecer 
     */
    public void setFiltroDiscosAgregados(List<UDisco> filtroDiscosAgregados) {
        this.filtroDiscosAgregados = filtroDiscosAgregados;
    }

    /**
     * Obtiene las canciones agregadas segun el filtro
     * @return Canciones agregadas segun el filtro
     */
    public List<UCancion> getFiltroCancionesAgregadas() {
        return filtroCancionesAgregadas;
    }

    /**
     * Establece las canciones agregadas segun el filtro
     * @param filtroCancionesAgregadas Lista de canciones a establecer
     */
    public void setFiltroCancionesAgregadas(List<UCancion> filtroCancionesAgregadas) {
        this.filtroCancionesAgregadas = filtroCancionesAgregadas;
    }

}
