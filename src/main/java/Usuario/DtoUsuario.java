/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Usuario;

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
public class DtoUsuario {
    
    private static final Logger log;
    private Connection cn;
    public Usuario currentUser;
    
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(Conexion.class.getName());
    }
    
    public DtoUsuario(){
        cn = new Conexion().cn;
    }
    
    public List<Usuario> getUsuarios()  throws SQLException{
        List<Usuario> userList = new ArrayList<Usuario>();
        PreparedStatement userps = cn.prepareStatement("SELECT * FROM Usuario;");
        ResultSet rs = userps.executeQuery();
        while (rs.next()) {
             userList.add( new Usuario(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getInt(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9)));
        }
        return userList;
    }
    
    public void newUsuario(Usuario user) throws SQLException {
        log.info("Insert data user");
        PreparedStatement ps = cn.prepareStatement(
            "INSERT INTO Usuario (nombre,apellido,clave,usuario,rol,foto) VALUES (?,?,?,?,?,?);"
        );

        ps.setString(1, user.getNombre());
        ps.setString(2, user.getApellido());
        ps.setString(3, user.getClave());
        ps.setString(4, user.getUsuario());
        ps.setInt(5, user.getRol());
        ps.setString(6, "null");
        int res = ps.executeUpdate();
        System.out.println(res);
    }
    
    public void updateUsuario(Usuario user) throws SQLException {
        log.info("Update data user");
        PreparedStatement ps = cn
                .prepareStatement("UPDATE Usuario SET nombre = ?, apellido = ?, clave = ?, usuario = ?, rol = ?, foto = ? WHERE id = ?;");

        ps.setString(1, user.getNombre());
        ps.setString(2, user.getApellido());
        ps.setString(3, user.getClave());
        ps.setString(4, user.getUsuario());
        ps.setInt(5, user.getRol());
        ps.setString(6, user.getFoto());
        ps.setInt(7, user.getId());
        ps.executeUpdate();
    }
    
    public void deleteUsuario(Usuario user) throws SQLException {
        log.info("Delete data user");
        PreparedStatement deleteStatement = cn.prepareStatement("DELETE FROM Usuario WHERE id = ?;");
        deleteStatement.setInt(1, user.getId());
        deleteStatement.executeUpdate();
    }

    /*private Todo readData(Connection connection) throws SQLException {
        log.info("Read data");
        PreparedStatement readStatement = connection.prepareStatement("SELECT * FROM todo;");
        ResultSet resultSet = readStatement.executeQuery();
        if (!resultSet.next()) {
            log.info("There is no data in the database!");
            return null;
        }
        Todo todo = new Todo();
        todo.setId(resultSet.getLong("id"));
        todo.setDescription(resultSet.getString("description"));
        todo.setDetails(resultSet.getString("details"));
        todo.setDone(resultSet.getBoolean("done"));
        log.info("Data read from the database: " + todo.toString());
        return todo;
    }

    private void updateData(Todo todo, Connection connection) throws SQLException {
        log.info("Update data");
        PreparedStatement updateStatement = connection
                .prepareStatement("UPDATE todo SET description = ?, details = ?, done = ? WHERE id = ?;");

        updateStatement.setString(1, todo.getDescription());
        updateStatement.setString(2, todo.getDetails());
        updateStatement.setBoolean(3, todo.isDone());
        updateStatement.setLong(4, todo.getId());
        updateStatement.executeUpdate();
        readData(connection);
    }

    private void deleteData(Todo todo, Connection connection) throws SQLException {
        log.info("Delete data");
        PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM todo WHERE id = ?;");
        deleteStatement.setLong(1, todo.getId());
        deleteStatement.executeUpdate();
        readData(connection);
    }
*/
    
}
