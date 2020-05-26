/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.core.Administrador;

import com.unicundi.BD.DAOCancion;
import com.unicundi.BD.DAODisco;
import com.unicundi.utilitarios.UCancion;
import com.unicundi.utilitarios.UDisco;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author cass465
 */
public class CoreCancion implements Serializable {

    public void registrarDisco(UCancion cancion, String nombreDisco) {
        new DAOCancion().registrarDisco(cancion, nombreDisco);
    }

    public void registrar(UCancion cancion) {
        UCancion cancionAux = new DAOCancion().obtenerExistente(cancion);
        int precioDisco = new DAODisco().obtenerPrecio(cancion.getIdDisco(), null, 1);
        if (cancionAux.getNombre() != null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cancion ya existe", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            if (precioDisco <= cancion.getPrecio()) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La canción no puede vale mas que el Disco", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                new DAOCancion().registrar(cancion);
                new DAODisco().actualizarNCanciones();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado Satisfactoriamente", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }

    }

    public ArrayList<UCancion> listar() {
        System.out.println("entre a core");
        return new DAOCancion().listar();
    }

    public void modificar(UCancion cancion) {
        UCancion cancionAux = new DAOCancion().obtenerExistente(cancion);
        int precioDisco = new DAODisco().obtenerPrecio(0, cancion.getNombreDisco(), 2);
        if (cancionAux.getNombre() != null && cancionAux.getId() != cancion.getId()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cancion ya existe", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            if (precioDisco <= cancion.getPrecio()) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La canción no puede vale mas que el Disco", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                new DAOCancion().modificar(cancion);
                new DAODisco().actualizarNCanciones();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Satisfactoriamente", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }

    }

}
