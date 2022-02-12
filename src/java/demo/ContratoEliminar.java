package demo;

/**
 *
 * @author marco
 */
import modelo.dao.contratosDAO;
import modelo.daoimp.contratosDAOIMP;
import modelo.dto.contratosDTO;

public class ContratoEliminar {

    private contratosDAO dao;
    private contratosDTO dto;

    public ContratoEliminar() {

        dto = new contratosDTO();

        dto.setId(3);

        dao = new contratosDAOIMP();
        if (dao.eliminarRegistro(dto)) {
            System.out.println("Operación Exitosa");
        } else {
            System.out.println("Operación Erronea");
        }
    }

    public static void main(String[] args) {
        new ContratoEliminar();
    }

}
