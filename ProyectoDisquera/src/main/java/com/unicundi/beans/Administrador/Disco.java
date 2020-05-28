/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.beans.Administrador;

import com.unicundi.core.Administrador.CoreArtista;
import com.unicundi.core.Administrador.CoreDisco;
import com.unicundi.utilitarios.UArtista;
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
@Named(value = "disco")
@ViewScoped
public class Disco implements Serializable {

    /**
     * Disco para obtener los datos
     */
    private UDisco disco;
    
    /**
     * Cancion para obtener los datos
     */
    private UCancion cancion;
    
    /**
     * Lista de discos
     */
    private ArrayList<UDisco> discos = new CoreDisco().listar();

    /**
     * Lista de artistas activos
     */
    private ArrayList<UArtista> artista = new CoreArtista().listarActivos();

    /**
     * Constructor de la clase
     */
    public Disco() {
        disco = new UDisco();
        cancion = new UCancion();
    }

    /**
     * Registra disco
     */
    public void registrar() {
        new CoreDisco().registrar(disco, cancion);
        discos = new CoreDisco().listar();
    }

    /**
     * Editar disco
     * @param event 
     */
    public void onRowEdit(RowEditEvent event) {
        UDisco discoEdit = (UDisco) event.getObject();
        new CoreDisco().modificar(discoEdit);
    }

    /**
     * Cancelar operacion 
     * @param event 
     */
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Operacion Cancelada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Obtiene el disco
     * @return 
     */
    public UDisco getDisco() {
        return disco;
    }

    /**
     * Establece los valores del disco
     * @param disco 
     */
    public void setDisco(UDisco disco) {
        this.disco = disco;
    }

    /**
     * Obtiene la lista de discos
     * @return 
     */
    public ArrayList<UDisco> getDiscos() {
        return discos;
    }

    /**
     * Establece la lista
     * @param discos 
     */
    public void setDiscos(ArrayList<UDisco> discos) {
        this.discos = discos;
    }

    /**
     * Obtiene los valores de la lista artista
     * @return 
     */
    public ArrayList<UArtista> getArtista() {
        return artista;
    }

    /**
     * Establece los valores de la lista
     * @param artista 
     */
    public void setArtista(ArrayList<UArtista> artista) {
        this.artista = artista;
    }

    /**
     * Obtiene los valores de la cancion 
     * @return 
     */
    public UCancion getCancion() {
        return cancion;
    }

    /**
     * Establece los valores de la cancion 
     * @param cancion 
     */
    public void setCancion(UCancion cancion) {
        this.cancion = cancion;
    }

}
