/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.core.Administrador;

import com.unicundi.BD.DAOArtista;
import com.unicundi.BD.DAOCancion;
import com.unicundi.BD.DAODisco;
import com.unicundi.utilitarios.UCancion;
import com.unicundi.utilitarios.UDisco;
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
public class CoreDisco implements Serializable {

    /**
     * Registrar disco
     *
     * @param disco
     * @param cancion
     */
    public void registrar(UDisco disco, UCancion cancion) {
        UDisco discoAux = new DAODisco().obtenerExistente(disco);
        UCancion cancionAux = new DAOCancion().obtenerExistente(cancion);
        if (cancionAux.getNombre() != null || discoAux.getNombre() != null) {
            if (discoAux.getNombre() != null) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El disco ya existe", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            if (cancionAux.getNombre() != null) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La canción ya existe", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        } else {

            disco.setPrecio(cancion.getPrecio());
            new DAODisco().registrar(disco);
            new CoreCancion().registrarDisco(cancion, disco.getNombre());
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado Satisfactoriamente", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }

    }

    /**
     * Listar todos los discos
     *
     * @return
     */
    public ArrayList<UDisco> listar() {
        return new DAODisco().listar();
    }

    /**
     * Listar disco activos
     *
     * @return
     */
    public ArrayList<UDisco> listarActivos() {
        return new DAODisco().listarActivos();
    }

    /**
     * Modificar disco
     *
     * @param disco
     */
    public void modificar(UDisco disco) {

        UDisco discoAux = new DAODisco().obtenerExistente(disco);
        boolean estadoArtista = new DAOArtista().obtenerEstado(disco.getNombreArtista());
        if (discoAux.getNombre() != null && discoAux.getId() != disco.getId()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El disco ya existe", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            if (estadoArtista == false && disco.isEstado() == true) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No puede activar el disco, artista inactivo", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                if (new DAODisco().obtenerEstado(disco.getNombre()) && disco.isEstado()) {
                    new DAODisco().modificar(disco);
                } else {
                    new DAODisco().modificar(disco);
                    new DAOCancion().cambiarEstadoDisco(disco.getId(), disco.isEstado());
                }
                new DAODisco().actualizarNCanciones();
                new DAODisco().actualizarPrecio();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Satisfactoriamente", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            }
        }

    }
}
