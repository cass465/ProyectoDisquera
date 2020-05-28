/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.core.Administrador;

import com.unicundi.BD.DAOArtista;
import com.unicundi.BD.DAOCancion;
import com.unicundi.BD.DAODisco;
import com.unicundi.utilitarios.UArtista;
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
public class CoreArtista implements Serializable {

    /**
     * Registrar artista
     *
     * @param artista
     */
    public void registrar(UArtista artista) {
        UArtista artistaAux = new DAOArtista().obtenerExistente(artista);
        if (artistaAux.getNombre() != null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El artista ya existe", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            new DAOArtista().registrar(artista);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado Satisfactoriamente", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    /**
     * Listar los artistas
     *
     * @return
     */
    public ArrayList<UArtista> listar() {
        System.out.println("entre a core");
        return new DAOArtista().listar();
    }

    /**
     * Listar los artistas activos
     *
     * @return
     */
    public ArrayList<UArtista> listarActivos() {
        System.out.println("entre a core");
        return new DAOArtista().listarActivos();
    }

    /**
     * Modificar artista
     *
     * @param artista
     */
    public void modificar(UArtista artista) {
        UArtista artistaAux = new DAOArtista().obtenerExistente(artista);
        if (artistaAux.getNombre() != null && artistaAux.getId() != artista.getId()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El artista ya existe", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {

            if (new DAOArtista().obtenerEstado(artista.getNombre() + " " + artista.getApellido()) && artista.isEstado()) {
                new DAOArtista().modificar(artista);
            } else {
                new DAOArtista().modificar(artista);
                new DAODisco().cambiarEstadoArtista(artista.getId(), artista.isEstado());
                new DAOCancion().cambiarEstadoArtista(artista.getId(), artista.isEstado());

            }

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Satisfactoriamente", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    /**
     * Eliminar artista, no utilizado
     *
     * @param artista
     */
    public void eliminar(UArtista artista) {
        System.out.println("core Modificar");
        new DAOArtista().eliminar(artista);
    }

}
