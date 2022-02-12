/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import modelo.dao.TipContratoDAO;
import modelo.dto.TipContratoDTO;
import modelo.daoimp.TiposContratoDAOIMP;

/**
 *
 * @author marco
 */
public class tipoContratoRecuperarRegistros {

    private TipContratoDAO dao;
    private TipContratoDTO dto;

    public tipoContratoRecuperarRegistros() {

        dao = new TiposContratoDAOIMP();
        for (TipContratoDTO x : dao.recuperarRegistros()) {
            System.out.println("Id recuperado = " + x.getId());
            System.out.println("Descripcion recuperado = " + x.getDescrip());
            System.out.println("Comentario recuperado " + x.getComentario());
            System.out.println("---------------------------------------------------------------------------");
        }

    }

    public static void main(String[] args) {
        new tipoContratoRecuperarRegistros();
    }

}
