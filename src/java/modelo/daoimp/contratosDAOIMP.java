/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.daoimp;

import base.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.dao.contratosDAO;
import modelo.dto.TipContratoDTO;
import modelo.dto.contratosDTO;

/**
 *
 * @author marco
 */
public class contratosDAOIMP implements contratosDAO {

    private String sql;
    private Conexion conexion;
    private PreparedStatement ps;
    private ResultSet rs;

    public contratosDAOIMP() {
        conexion = new Conexion();
    }

    @Override
    public boolean agregarRegistro(contratosDTO dto) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "INSERT INTO public.contratos(nro_contrato, nombre_llamado, fecha_inicio, fecha_fin, monto_contrato, id_tipo)\n"
                    + "	VALUES (?, ?, ?, ?, ?, ?);";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setString(1, dto.getNro_contrato());
            ps.setString(2, dto.getNombre_llamado());
            ps.setTimestamp(3, dto.getFecha_inicio());
            ps.setTimestamp(4, dto.getFecha_fin());
            ps.setInt(5, dto.getMonto_contrato());
            ps.setInt(6, dto.getTipocontrato().getId());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(contratosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(contratosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean modificarRegistro(contratosDTO dto) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "UPDATE public.contratos SET nro_contrato=?, nombre_llamado=?, fecha_inicio=?, fecha_fin=?, monto_contrato=?, id_tipo=?"
                    + "  WHERE id_contra=?;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setString(1, dto.getNro_contrato());
            ps.setString(2, dto.getNombre_llamado());
            ps.setTimestamp(3, dto.getFecha_inicio());
            ps.setTimestamp(4, dto.getFecha_fin());
            ps.setInt(5, dto.getMonto_contrato());
            ps.setInt(6, dto.getTipocontrato().getId());
            ps.setInt(7, dto.getId());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(contratosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(contratosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean eliminarRegistro(contratosDTO dto) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "DELETE FROM  public.contratos  WHERE id_contra=?;";
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
            Logger.getLogger(contratosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(contratosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public contratosDTO recuperarRegistro(Integer id) {
        try {
            contratosDTO dto = null;
            sql = "SELECT a.id_contra, a.nro_contrato, a.nombre_llamado, a.fecha_inicio, a.fecha_fin, a.monto_contrato, a.id_tipo, b.descrip as tipocontrato\n" +
"FROM public.contratos as a INNER JOIN tipos_contrato as b ON b.id_tipo = a.id_tipo WHERE a.id_contra= ?";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                dto = new contratosDTO();
                dto.setId(rs.getInt("id_contra"));
                dto.setNro_contrato(rs.getString("nro_contrato"));
                dto.setNombre_llamado(rs.getString("nombre_llamado"));
                dto.setFecha_inicio(rs.getTimestamp("fecha_inicio"));
                dto.setFecha_fin(rs.getTimestamp("fecha_fin"));
                dto.setMonto_contrato(rs.getInt("monto_contrato"));
                dto.setTipocontrato(new TipContratoDTO(rs.getInt("id_tipo"), rs.getString("tipocontrato")));
            }
            return dto;
        } catch (SQLException ex) {
            Logger.getLogger(contratosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(contratosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<contratosDTO> recuperarRegistros() {
        try {
            contratosDTO dto = null;
            List<contratosDTO> lista;
            sql = "SELECT a.id_contra, a.nro_contrato, a.nombre_llamado, a.fecha_inicio, a.fecha_fin, a.monto_contrato, a.id_tipo, b.descrip as tipocontrato\n"
                    + "FROM public.contratos as a INNER JOIN tipos_contrato as b ON b.id_tipo = a.id_tipo";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                dto = new contratosDTO();
                dto.setId(rs.getInt("id_contra"));
                dto.setNro_contrato(rs.getString("nro_contrato"));
                dto.setNombre_llamado(rs.getString("nombre_llamado"));
                dto.setFecha_inicio(rs.getTimestamp("fecha_inicio"));
                dto.setFecha_fin(rs.getTimestamp("fecha_fin"));
                dto.setMonto_contrato(rs.getInt("monto_contrato"));
                dto.setTipocontrato(new TipContratoDTO(rs.getInt("id_tipo"), rs.getString("tipocontrato")));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(contratosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(contratosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
