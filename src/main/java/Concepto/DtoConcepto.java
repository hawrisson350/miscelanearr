/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Concepto;

import Clases.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author hawri
 */
public class DtoConcepto {
    private static final Logger log;
    private Connection cn;
    public Concepto currentUser;
    
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(Conexion.class.getName());
    }
    
    public DtoConcepto(){
        cn = new Conexion().cn;
    }
    
    public List<Concepto> getConceptos()  throws SQLException{
        List<Concepto> conceptoList = new ArrayList<Concepto>();
        PreparedStatement ps = cn.prepareStatement("SELECT * FROM Concepto;");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
             conceptoList.add( new Concepto(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9)));
        }
        return conceptoList;
    }
    
    public void newConcepto(Concepto concept) throws SQLException {
        log.info("Insert data concepto");
        PreparedStatement ps = cn.prepareStatement(
            "INSERT INTO Concepto (nombre,tipo,precio,estado,descripcion,foto) VALUES (?,?,?,?,?,?);"
        );

        ps.setString(1, concept.getNombre());
        ps.setInt(2, concept.getTipo());
        ps.setString(3, concept.getPrecio());
        ps.setInt(4, concept.getEstado());
        ps.setString(5, concept.getDescripcion());
        ps.setString(6, "null");
        int res = ps.executeUpdate();
        System.out.println(res);
    }
    
    public void updateConcepto(Concepto concept) throws SQLException {
        log.info("Update data concepto");
        PreparedStatement ps = cn
                .prepareStatement("UPDATE Concepto SET nombre = ?, tipo = ?, precio = ?, estado = ?, descripcion = ?, foto = ? WHERE id = ?;");

        ps.setString(1, concept.getNombre());
        ps.setInt(2, concept.getTipo());
        ps.setString(3, concept.getPrecio());
        ps.setInt(4, concept.getEstado());
        ps.setString(5, concept.getDescripcion());
        ps.setString(6, concept.getFoto());
        ps.setInt(7, concept.getId());
        ps.executeUpdate();
    }
    
    public void deleteUsuario(Concepto user) throws SQLException {
        log.info("Delete data concepto");
        PreparedStatement deleteStatement = cn.prepareStatement("DELETE FROM Concepto WHERE id = ?;");
        deleteStatement.setInt(1, user.getId());
        deleteStatement.executeUpdate();
    }
}
