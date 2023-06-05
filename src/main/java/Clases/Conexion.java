package Clases;

import Usuario.Usuario;
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

public class Conexion {

    private static final Logger log;
    public static Connection cn;
    public static Usuario currentUser;
    
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(Conexion.class.getName());
    }
    
    public void conectar() throws Exception {
        log.info("Loading application properties");
        Properties properties = new Properties();
        properties.load(Conexion.class.getClassLoader().getResourceAsStream("application.properties"));

        log.info("Connecting to the database");
        cn = DriverManager.getConnection(properties.getProperty("url"), properties);
        log.info("Database connection test: " + cn.getCatalog());
    }
    
    public boolean login (String usuario, String clave)  throws SQLException{
        PreparedStatement loginCheck = cn.prepareStatement("SELECT * FROM Usuario WHERE usuario=? AND clave=?;");
        loginCheck.setString(1, usuario);
        loginCheck.setString(2, clave);
        ResultSet rs = loginCheck.executeQuery();
        if (rs.next()) {
            currentUser = new Usuario(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getInt(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9));
            return true;
        }
        return false;
    }

    public void startDemo(String[] args) throws Exception {

/*
        log.info("Create database schema");
        Scanner scanner = new Scanner(Conexion.class.getClassLoader().getResourceAsStream("schema.sql"));
        Statement statement = connection.createStatement();
        while (scanner.hasNextLine()) {
            statement.execute(scanner.nextLine());
        }
*/
        Todo todo = new Todo(1L, "configuration", "congratulations, you have set up JDBC correctly!", true);
        insertData(todo, cn);
        todo = readData(cn);
        todo.setDetails("congratulations, you have updated data!");
        updateData(todo, cn);
        deleteData(todo, cn);
        
        log.info("Closing database connection");
        cn.close();
    }

    private void insertData(Todo todo, Connection connection) throws SQLException {
        log.info("Insert data");
        PreparedStatement insertStatement = connection
                .prepareStatement("INSERT INTO todo (id, description, details, done) VALUES (?, ?, ?, ?);");

        insertStatement.setLong(1, todo.getId());
        insertStatement.setString(2, todo.getDescription());
        insertStatement.setString(3, todo.getDetails());
        insertStatement.setBoolean(4, todo.isDone());
        insertStatement.executeUpdate();
    }

    private Todo readData(Connection connection) throws SQLException {
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
    
    
}
