package com.mycompany.practica3pcs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private String url;
    private String db;
    private String user;
    private String password;
    private String driver;
    private Connection connection;

    public Conexion() {
        this.url = "jdbc:mysql://localhost:3306/";
        this.db = "restaurantedb";
        this.user = "root";
        this.password = "SGHPegasus";
        this.driver = "com.mysql.cj.jdbc.Driver";
    }

    public Connection conectar() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url + db, user, password);
            return connection;
        } catch (ClassNotFoundException ex) {
            System.out.println("Error class: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error sql: " + ex.getMessage());
        }
        return null;
    }

    public void desconectar() {
        if (connection != null) {
            try {
                System.out.println("Conexion finalizada");
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error sql: " + ex.getMessage());
            }
        }
    }

}
