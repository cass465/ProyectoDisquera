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
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author cass465
 */
public class CoreDisco implements Serializable {

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

    public ArrayList<UDisco> listar() {
        return new DAODisco().listar();
    }

    public ArrayList<UDisco> listarActivos() {
        return new DAODisco().listarActivos();
    }

    public void modificar(UDisco disco) {

        UDisco discoAux = new DAODisco().obtenerExistente(disco);
        boolean estadoArtista = new DAOArtista().obtenerEstado(disco.getNombreArtista());
        if (discoAux.getNombre() != null && discoAux.getId() != disco.getId()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El disco ya existe", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            if (estadoArtista==false && disco.isEstado()==true) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No puede activar el disco, artista inactivo", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                new DAODisco().modificar(disco);
                new DAOCancion().cambiarEstadoDisco(disco.getId(), disco.isEstado());
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado Satisfactoriamente", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            }
        }

    }
}
