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

public class ContratoModificar {

    private contratosDAO dao;
    private contratosDTO dto;

    public ContratoModificar() {

        dto = new contratosDTO();

        String fecha_inicio = "2021-10-16 13:00:00";
        String fecha_fin = "2021-10-17 01:24:23";

        dto.setId(3);
        dto.setNro_contrato("001-001-000012");
        dto.setNombre_llamado("Modificado");
        dto.setFecha_inicio(Timestamp.valueOf(fecha_inicio));
        dto.setFecha_fin(Timestamp.valueOf(fecha_fin));
        dto.setMonto_contrato(10000);
        dto.setTipocontrato(new TipContratoDTO(1));

        dao = new contratosDAOIMP();
        if (dao.modificarRegistro(dto)) {
            System.out.println("Operación Exitosa");
        } else {
            System.out.println("Operación Erronea");
        }
    }

    public static void main(String[] args) {
        new ContratoModificar();
    }

}
