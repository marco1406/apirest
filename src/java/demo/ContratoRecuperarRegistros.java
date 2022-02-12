package demo;

/**
 *
 * @author marco
 */
import modelo.dao.contratosDAO;
import modelo.daoimp.contratosDAOIMP;
import modelo.dto.contratosDTO;
import modelo.dto.TipContratoDTO;

import java.sql.Timestamp;

public class ContratoRecuperarRegistros {

    private contratosDAO dao;
    private contratosDTO dto;

    public ContratoRecuperarRegistros() {

        dto = new contratosDTO();

        dao = new contratosDAOIMP();
        for (contratosDTO x : dao.recuperarRegistros()) {
            System.out.println("Id recuperado = " + x.getId());
            System.out.println("Nro Contrato recuperado = " + x.getNro_contrato());
            System.out.println("Nombre llamado = " + x.getNombre_llamado());
            System.out.println("Fecha Inico = " + x.getFecha_inicio());
            System.out.println("Fecha Fino = " + x.getFecha_fin());
            System.out.println("Tipo de Contrato = " + x.getTipocontrato().getDescrip());
            System.out.println("---------------------------------------------------------------------------");
        }
    }

    public static void main(String[] args) {
        new ContratoRecuperarRegistros();
    }

}
