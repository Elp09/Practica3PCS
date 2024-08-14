package com.mycompany.practica3pcs;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSalario {

    public static void main(String[] args) {
        try {
            System.out.println("Servidor conectado!\nServer corriendo...");
            ServerSocket servidor = new ServerSocket(6100); // 127.0.0.1 localhost
            int count = 1;
            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente " + count + " conectado");

                DataInputStream in = new DataInputStream(cliente.getInputStream());
                DataOutputStream out = new DataOutputStream(cliente.getOutputStream());

                String posicion = in.readUTF();
                switch (posicion) {
                    case "Escolta":
                        out.writeUTF("El salario de un escolta es de 1,200,000 colones");
                        break;
                    case "Base":
                        out.writeUTF("El salario de un base es de 1,000,000 colones");
                        break;
                    case "Alero":
                        out.writeUTF("El salario de un alero es de 1,100,000 colones");
                        break;
                    case "Ala_Pívot":
                        out.writeUTF("El salario de un ala pivot es de 1,300,000 colones");
                        break;
                    case "Pívot":
                        out.writeUTF("El salario de un pivtot es de 1,400,000 colones");
                        break;
                    default:
                        out.writeUTF("Posicion no encontradaadadadadada");
                        break;
                }

                count++;
                cliente.close();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
