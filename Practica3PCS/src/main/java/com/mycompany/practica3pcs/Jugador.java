package com.mycompany.practica3pcs;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Jugador {

    private int id;
    private String nombre;
    private Posicion posicion;
    private int edad;
    private Equipo equipo;
    private double puntuacionMedia;

    //CREAR
    public Jugador(String nombre, Posicion posicion, int edad, Equipo equipo, double puntuacionMedia) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.edad = edad;
        this.equipo = equipo;
        this.puntuacionMedia = puntuacionMedia;
    }

    //MODIFICAR
    public Jugador(int id, String nombre, Posicion posicion, int edad, Equipo equipo, double puntuacionMedia) {
        this.id = id;
        this.nombre = nombre;
        this.posicion = posicion;
        this.edad = edad;
        this.equipo = equipo;
        this.puntuacionMedia = puntuacionMedia;
    }

    //BORRAR
    public Jugador(int id) {
        this.id = id;
    }

    // METODOS CRUD
    // Metodo para consultar todas las jugadores
    public static DefaultTableModel consultar() {

        Conexion conexion = new Conexion();
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Id");
        model.addColumn("Nombre");
        model.addColumn("Posicion");
        model.addColumn("Edad");
        model.addColumn("Equipo");
        model.addColumn("Puntuacion");

        String datos[] = new String[6];

        try {
            Statement stmt = conexion.conectar().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM jugadoresB3X");

            while (rs.next()) {
                datos[0] = String.valueOf(rs.getInt("ID9R2"));
                datos[1] = rs.getString("NombreF1L");
                datos[2] = rs.getString("PosicionS4M");
                datos[3] = String.valueOf(rs.getInt("EdadH8N"));
                datos[4] = rs.getString("EquipoP3Q");
                datos[5] = String.valueOf(rs.getDouble("PuntuacionL0W"));

                model.addRow(datos);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al consultar los registros",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.desconectar();
        }

        return model;
    }

    // Metodo para agregar jugadores
    public void agregar() {
        Conexion conexion = new Conexion();

        String sql = "INSERT INTO jugadoresB3X "
                + "(NombreF1L, PosicionS4M, EdadH8N, EquipoP3Q, PuntuacionL0W) VALUES "
                + "(?, ?, ?, ?, ?)";

        try {
            CallableStatement cs = conexion.conectar().prepareCall(sql);

            cs.setString(1, this.nombre);
            cs.setString(2, String.valueOf(this.posicion));
            cs.setInt(3, this.edad);
            cs.setString(4, String.valueOf(this.equipo));
            cs.setDouble(5, this.puntuacionMedia);

            cs.execute();

            JOptionPane.showMessageDialog(null,
                    "Registro agregado con éxito.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al agregar el registro",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.desconectar();
        }
    }

    //Modificar
    public void modificar() {
        Conexion conexion = new Conexion();

        String sql = "UPDATE jugadoresB3X "
                + "SET NombreF1L = ?,"
                + "    PosicionS4M = ?,"
                + "    EdadH8N = ?,"
                + "    EquipoP3Q = ?,"
                + "    PuntuacionL0W = ? "
                + "WHERE ID9R2 = ?";

        try {
            CallableStatement cs = conexion.conectar().prepareCall(sql);

            cs.setString(1, this.nombre);
            cs.setString(2, String.valueOf(this.posicion));
            cs.setInt(3, this.edad);
            cs.setString(4, String.valueOf(this.equipo));
            cs.setDouble(5, this.puntuacionMedia);
            cs.setInt(6, this.id);

            cs.execute();

            JOptionPane.showMessageDialog(null,
                    "Registro modificado con éxito.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al modificar el registro",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.desconectar();
        }
    }

    //borrar
    public void eliminar() {
        Conexion conexion = new Conexion();

        String sql = "DELETE FROM jugadoresB3X WHERE ID9R2 = ?";

        try {
            CallableStatement cs = conexion.conectar().prepareCall(sql);
            cs.setInt(1, this.id);
            cs.execute();

            JOptionPane.showMessageDialog(null,
                    "Registro eliminado con éxito.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al eliminar el registro",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: " + ex.getMessage());
        } finally {
            conexion.desconectar();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Jugador() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public double getPuntuacionMedia() {
        return puntuacionMedia;
    }

    public void setPuntuacionMedia(double puntuacionMedia) {
        this.puntuacionMedia = puntuacionMedia;
    }

}
