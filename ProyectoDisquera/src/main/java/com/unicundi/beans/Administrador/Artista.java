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
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author YEISON
 */
@Named(value = "artista")
@ViewScoped
public class Artista implements Serializable{

    private UArtista artista;
    
    private  ArrayList<UArtista> artistas=new CoreArtista().listar();

    /**
     * Creates a new instance of Artista
     */
    public Artista() {
        artista = new UArtista();        
        System.out.println("hola index");
    }

    public void registrar() {       
        new CoreArtista().registrar(artista);
        artistas=new CoreArtista().listar();
    }
    
  

       public void onRowEdit(RowEditEvent event) {
        UArtista artistaEdit=  (UArtista) event.getObject();
        new CoreArtista().modificar(artistaEdit);    
       
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Operacion Cancelada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    } 
    
    
    public UArtista getArtista() {
        return artista;
    }

    public void setArtista(UArtista artista) {
        this.artista = artista;
    }

    public ArrayList<UArtista> getArtistas() {
        return artistas;
    }

    public void setArtistas(ArrayList<UArtista> artistas) {
        this.artistas = artistas;
    }
    
    

}
