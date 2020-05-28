/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.beans.Administrador;

import com.unicundi.core.Administrador.CoreArtista;
import com.unicundi.utilitarios.UArtista;
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
@Named(value = "artista")
@ViewScoped
public class Artista implements Serializable {

    /**
     * artista que se manejara
     */
    private UArtista artista;

    /**
     * Lista de artista para mostrar en la tabla
     */
    private ArrayList<UArtista> artistas = new CoreArtista().listar();

    /**
     * Constructor
     */
    public Artista() {
        artista = new UArtista();
    }

    /**
     * Registra usuario
     */
    public void registrar() {
        new CoreArtista().registrar(artista);
        artistas = new CoreArtista().listar();
    }

    /**
     * Editar desde la tabla
     *
     * @param event
     */
    public void onRowEdit(RowEditEvent event) {
        UArtista artistaEdit = (UArtista) event.getObject();
        new CoreArtista().modificar(artistaEdit);

    }

    /**
     * Cancelar la operacion
     *
     * @param event
     */
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("OPERACIÓN CANCELADA");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Obtiene el artista
     *
     * @return
     */
    public UArtista getArtista() {
        return artista;
    }

    /**
     * Establece el valor del artista
     *
     * @param artista
     */
    public void setArtista(UArtista artista) {
        this.artista = artista;
    }

    /**
     * Obtiene el valor de la lista
     *
     * @return
     */
    public ArrayList<UArtista> getArtistas() {
        return artistas;
    }

    /**
     * Establece el valor de la vista
     *
     * @param artistas
     */
    public void setArtistas(ArrayList<UArtista> artistas) {
        this.artistas = artistas;
    }

}
