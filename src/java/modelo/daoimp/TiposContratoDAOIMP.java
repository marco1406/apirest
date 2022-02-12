
package modelo.daoimp;
/**
 *
 * @author marco
 */

import base.Conexion;
import modelo.dto.TipContratoDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.dao.TipContratoDAO;


public class TiposContratoDAOIMP implements TipContratoDAO {
    private String sql;
    private Conexion conexion;
    private PreparedStatement ps;
    private ResultSet rs;

    public TiposContratoDAOIMP() {
        conexion = new Conexion();
    }

    @Override
    public boolean agregarRegistro(TipContratoDTO dto) {
       try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "INSERT INTO public.tipos_contrato(descrip, comentario) VALUES (?, ?);";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setString(1, dto.getDescrip());
            ps.setString(2, dto.getComentario());            
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TiposContratoDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(TiposContratoDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean modificarRegistro(TipContratoDTO dto) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "UPDATE public.tipos_contrato SET descrip=?, comentario=? WHERE id_tipo=?;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setString(1, dto.getDescrip());
            ps.setString(2, dto.getComentario());          
            ps.setInt(3, dto.getId());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TiposContratoDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(TiposContratoDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean eliminarRegistro(TipContratoDTO dto) {
    try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "DELETE FROM  public.tipos_contrato  WHERE id_tipo=?;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, dto.getId());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TiposContratoDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(TiposContratoDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public TipContratoDTO recuperarRegistro(Integer id) {
    try {
            TipContratoDTO dto = null;
            sql = "SELECT id, descrip, comentario FROM public.tipos_contrato WHERE id_tipo = ? ";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                dto = new TipContratoDTO();
                dto.setId(rs.getInt("id_tipo"));
                dto.setDescrip(rs.getString("descrip"));
                dto.setComentario(rs.getString("comentario"));           
            }
            return dto;
        } catch (SQLException ex) {
            Logger.getLogger(TiposContratoDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(TiposContratoDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<TipContratoDTO> recuperarRegistros() {
    try {
            TipContratoDTO dto = null;
            List<TipContratoDTO> lista;
            sql = "SELECT id_tipo, descrip, comentario FROM public.tipos_contrato";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                dto = new TipContratoDTO();
                dto.setId(rs.getInt("id_tipo"));
                dto.setDescrip(rs.getString("descrip"));
                dto.setComentario(rs.getString("comentario"));             
                lista.add(dto);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(TiposContratoDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(TiposContratoDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
