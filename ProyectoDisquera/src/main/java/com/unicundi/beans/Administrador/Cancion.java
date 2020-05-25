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
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author YEISON
 */
@Named(value = "cancion")
@ViewScoped
public class Cancion implements Serializable {

    UCancion cancion;

    private ArrayList<UCancion> canciones = new CoreCancion().listar();

    private ArrayList<UDisco> discos = new CoreDisco().listarActivos();

    /**
     * Creates a new instance of Cancion
     */
    public Cancion() {
        cancion = new UCancion();
    }

    public void registrar() {
        new CoreCancion().registrar(cancion);
        canciones = new CoreCancion().listar();
    }

    public void onRowEdit(RowEditEvent event) {
        UCancion cancionEdit = (UCancion) event.getObject();
        new CoreCancion().modificar(cancionEdit);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Operacion Cancelada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public UCancion getCancion() {
        return cancion;
    }

    public void setCancion(UCancion cancion) {
        this.cancion = cancion;
    }

    public ArrayList<UCancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(ArrayList<UCancion> canciones) {
        this.canciones = canciones;
    }

    public ArrayList<UDisco> getDiscos() {
        return discos;
    }

    public void setDiscos(ArrayList<UDisco> discos) {
        this.discos = discos;
    }

}
