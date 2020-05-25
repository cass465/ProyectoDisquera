/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.beans.Administrador;

import com.unicundi.core.Administrador.CoreArtista;
import com.unicundi.core.Administrador.CoreCancion;
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
 *
 * @author YEISON
 */
@Named(value = "disco")
@ViewScoped
public class Disco implements Serializable {

    private UDisco disco;
    private UCancion cancion;
    private ArrayList<UDisco> discos = new CoreDisco().listar();

    private ArrayList<UArtista> artista = new CoreArtista().listarActivos();

    /**
     * Creates a new instance of Disco
     */
    public Disco() {
        disco = new UDisco();
        cancion = new UCancion();
    }

    public void registrar() {
        new CoreDisco().registrar(disco, cancion);
        discos = new CoreDisco().listar();
    }

    public void onRowEdit(RowEditEvent event) {
        UDisco discoEdit = (UDisco) event.getObject();
        new CoreDisco().modificar(discoEdit);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Operacion Cancelada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public UDisco getDisco() {
        return disco;
    }

    public void setDisco(UDisco disco) {
        this.disco = disco;
    }

    public ArrayList<UDisco> getDiscos() {
        return discos;
    }

    public void setDiscos(ArrayList<UDisco> discos) {
        this.discos = discos;
    }

    public ArrayList<UArtista> getArtista() {
        return artista;
    }

    public void setArtista(ArrayList<UArtista> artista) {
        this.artista = artista;
    }

    public UCancion getCancion() {
        return cancion;
    }

    public void setCancion(UCancion cancion) {
        this.cancion = cancion;
    }

}
