/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.core.Administrador;

import com.unicundi.BD.DAOCancion;
import com.unicundi.BD.DAODisco;
import com.unicundi.utilitarios.UCancion;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Clase core que tiene la logica de la aplicación
 *
 * @author Yeison Cifuentes
 * @version 1.0.0
 */
public class CoreCancion implements Serializable {

    /**
     * Registrar cancion desde la vista disco
     *
     * @param cancion
     * @param nombreDisco
     */
    public void registrarDisco(UCancion cancion, String nombreDisco) {
        new DAOCancion().registrarDisco(cancion, nombreDisco);
    }

    /**
     * Registrar cancion
     *
     * @param cancion
     */
    public void registrar(UCancion cancion) {
        UCancion cancionAux = new DAOCancion().obtenerExistente(cancion);

        if (cancionAux.getNombre() != null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cancion ya existe", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {

            new DAOCancion().registrar(cancion);
            new DAODisco().actualizarNCanciones();
            new DAODisco().actualizarPrecio();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado Satisfactoriamente", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }

    }

    /**
     * Listar todas las canciones
     *
     * @return
     */
    public ArrayList<UCancion> listar() {
        System.out.println("entre a core");
        return new DAOCancion().listar();
    }

    /**
     * Modificar la cancion
     *
     * @param cancion
     */
    public void modificar(UCancion cancion) {
        UCancion cancionAux = new DAOCancion().obtenerExistente(cancion);
        boolean estadoDisco = new DAODisco().obtenerEstado(cancion.getNombreDisco());
        if (cancionAux.getNombre() != null && cancionAux.getId() != cancion.getId()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cancion ya existe", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            if (estadoDisco == false && cancion.isEstado()) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No puede activar la cancion, disco inactivo", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                new DAOCancion().modificar(cancion);
                new DAODisco().actualizarNCanciones();
                new DAODisco().actualizarPrecio();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Satisfactoriamente", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        }
    }

}
