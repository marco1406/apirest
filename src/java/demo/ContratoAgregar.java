package demo;

/**
 *
 * @author marco
 */
import java.sql.Timestamp;
import modelo.dao.contratosDAO;
import modelo.daoimp.contratosDAOIMP;
import modelo.dto.TipContratoDTO;
import modelo.dto.contratosDTO;

public class ContratoAgregar {

    private contratosDAO dao;
    private contratosDTO dto;

    public ContratoAgregar() {

        dto = new contratosDTO();
        String fecha_inicio = "2015-05-12 09:02:02.645";
        String fecha_fin = "2016-05-12 09:02:02.645";
        dto.setNro_contrato("001-001-00001");
        dto.setNombre_llamado("Concurso Libre 3");
        dto.setFecha_inicio(Timestamp.valueOf(fecha_inicio));
        dto.setFecha_fin(Timestamp.valueOf(fecha_fin));
        dto.setMonto_contrato(15000);
        dto.setTipocontrato(new TipContratoDTO(1));

        dao = new contratosDAOIMP();
        if (dao.agregarRegistro(dto)) {
            System.out.println("Operación Exitosa");
        } else {
            System.out.println("Operación Erronea");
        }
    }

    public static void main(String[] args) {
        new ContratoAgregar();
    }

}
