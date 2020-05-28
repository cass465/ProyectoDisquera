/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.beans.Administrador;

import com.unicundi.core.Administrador.CoreCancion;
import com.unicundi.core.Administrador.CoreDisco;
import com.unicundi.utilitarios.UCancion;
import com.unicundi.utilitarios.UDisco;
import java.io.Serializable;
import java.util.ArrayList;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 * Clase bean que se comunica con la vista
 *
 * @author Yeison Cifuentes
 * @version 1.0.0
 */
@Named(value = "cancion")
@ViewScoped
public class Cancion implements Serializable {

    /**
     * Cancion para obtener los datos
     */
    UCancion cancion;

    /**
     * Lista de canciones
     */
    private ArrayList<UCancion> canciones = new CoreCancion().listar();

    /**
     * Lista de discos activos
     */
    private ArrayList<UDisco> discos = new CoreDisco().listarActivos();

    /**
     * Constructor de la clase
     */
    public Cancion() {
        cancion = new UCancion();
    }

    /**
     * Registra cancion
     */
    public void registrar() {
        new CoreCancion().registrar(cancion);
        canciones = new CoreCancion().listar();
    }

    /**
     * Editar cancion
     *
     * @param event
     */
    public void onRowEdit(RowEditEvent event) {
        UCancion cancionEdit = (UCancion) event.getObject();
        new CoreCancion().modificar(cancionEdit);
    }

    /**
     * Cancelar operacion
     *
     * @param event
     */
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Operacion Cancelada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Obtiene la cancion
     *
     * @return
     */
    public UCancion getCancion() {
        return cancion;
    }

    /**
     * Estable los valores de la cancion
     *
     * @param cancion
     */
    public void setCancion(UCancion cancion) {
        this.cancion = cancion;
    }

    /**
     * Obtiene la lista de canciones
     *
     * @return
     */
    public ArrayList<UCancion> getCanciones() {
        return canciones;
    }

    /**
     * Establece los valores de la lista
     *
     * @param canciones
     */
    public void setCanciones(ArrayList<UCancion> canciones) {
        this.canciones = canciones;
    }

    /**
     * Obtiene la lista de discos
     *
     * @return
     */
    public ArrayList<UDisco> getDiscos() {
        return discos;
    }

    /**
     * Establece los valores de la lista
     *
     * @param discos
     */
    public void setDiscos(ArrayList<UDisco> discos) {
        this.discos = discos;
    }

}
