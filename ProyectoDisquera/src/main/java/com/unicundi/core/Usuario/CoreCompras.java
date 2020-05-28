/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicundi.core.Usuario;

import com.unicundi.BD.DAOCancion;
import com.unicundi.BD.DAOCompraCancion;
import com.unicundi.BD.DAOCompraDisco;
import com.unicundi.BD.DAOCompraDiscoCancion;
import com.unicundi.utilitarios.UCancion;
import com.unicundi.utilitarios.UCompraCancion;
import com.unicundi.utilitarios.UCompraDisco;
import com.unicundi.utilitarios.UCompraDiscoCancion;
import com.unicundi.utilitarios.UDisco;
import com.unicundi.utilitarios.UUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author cass465
 */
public class CoreCompras implements Serializable {

    public List<UDisco> listarDiscosDisponibles() {
        return new DAOCompraDisco().listarDiscosDisponibles();
    }

    public List<UCancion> listarCancionesDisponibles() {
        return new DAOCompraCancion().listarCancionesDisponibles();
    }

    public List<UCancion> buscarPorDisco(int idDisco) {
        return new DAOCancion().buscarPorDisco(idDisco);
    }
    
    public List<UCompraDiscoCancion> buscarPorCompraDisco(int idCompraDisco) {
        return new DAOCompraDiscoCancion().buscarPorCompraDisco(idCompraDisco);
    }

    public void registrar(List<UDisco> discosAgregados, List<UCancion> cancionesAgregadas) {
        int nCompras = 0;
        int idUsuario = ((UUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")).getId();
        Date fechaCompra = new Date();

        for (UDisco disco : discosAgregados) {
            int idDisco = disco.getId();
            int valorCompra = disco.getPrecio();
            int numeroCanciones = disco.getCancionesDisco().size();
            UCompraDisco compra = new UCompraDisco(0, idUsuario, idDisco, valorCompra, fechaCompra, numeroCanciones);
            new DAOCompraDisco().registrar(compra);
            int idCompraDisco = new DAOCompraDisco().obtenerIdMayor();
            
            List<UCancion> cancionesDisco = disco.getCancionesDisco();
            for (UCancion cancionDisco : cancionesDisco){
                int idCancion = cancionDisco.getId();
                UCompraDiscoCancion compraCancion = new UCompraDiscoCancion(0, idCompraDisco, idCancion);
                new DAOCompraDiscoCancion().registrar(compraCancion);
            }
            
            nCompras++;
        }

        for (UCancion cancion : cancionesAgregadas) {
            int idCancion = cancion.getId();
            int valorCompra = cancion.getPrecio();
            UCompraCancion compra = new UCompraCancion(0, idUsuario, idCancion, valorCompra, fechaCompra);
            new DAOCompraCancion().registrar(compra);
            nCompras++;
        }

        if (nCompras > 0) {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "ÉXITO", "ELEMENTOS COMPRADOS: " + nCompras);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "ATENCIÓN!!", "LISTA DE COMPRAS VACIA");
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }
    }

    public List<UCompraDisco> listarDiscosComprados() {
        UUsuario usuario = (UUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return new DAOCompraDisco().listarPorUsuario(usuario);
    }

    public List<UCompraCancion> listarCancionesCompradas() {
        UUsuario usuario = (UUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return new DAOCompraCancion().listarPorUsuario(usuario);
    }

    public List<UCancion> listarCancionesARemover(List<UCancion> cancionesAgregadas, UDisco disco) {
        //Recorre las canciones agregadas y se listan las que tienen que salir
        List<UCancion> removerCanciones = new ArrayList<UCancion>();
        for (UCancion cancionAgregada : cancionesAgregadas) {
            if (cancionAgregada.getIdDisco() == disco.getId()) {
                removerCanciones.add(cancionAgregada);
            }
        }
        return removerCanciones;
    }

    public boolean validarDiscoAgregado(List<UDisco> discosAgregados, UCancion cancion) {
        boolean aceptado = true;
        for (UDisco discoAgregado : discosAgregados) {
            if (discoAgregado.getId() == cancion.getIdDisco()) {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "NO SE PUEDE AGREGAR LA CANCIÓN PORQUE EL DISCO YA ESTÁ AGREGADO");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                aceptado = false;
                break;
            }
        }
        return aceptado;
    }

    public UDisco obtenerDiscoPorCancion(List<UDisco> discosDisponibles, UCancion cancion) {
        UDisco discoCompleto = new UDisco();
        for (UDisco disco : discosDisponibles) {
            if (disco.getId() == cancion.getIdDisco()) {
                discoCompleto = disco;
            }
        }
        return discoCompleto;
    }
    
    public int contarCancionesAgregadasDeDisco(List<UCancion> cancionesAgregadas, int idDisco) {
        int nCanciones = 0;
        for (UCancion cancion : cancionesAgregadas) {
            if (cancion.getIdDisco() == idDisco) {
                nCanciones++;
            }
        }
        return nCanciones;
    }

    public int calcularTotal(List<UDisco> discosAgregados, List<UCancion> cancionesAgregadas) {
        int total = 0;
        for (UDisco disco : discosAgregados) {
            total += disco.getPrecio();
        }

        for (UCancion cancion : cancionesAgregadas) {
            total += cancion.getPrecio();
        }

        return total;
    }
}
