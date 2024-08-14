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
                        out.writeUTF("₡ 1,200,000 ");
                        break;
                    case "Base":
                        out.writeUTF("₡1,000,000");
                        break;
                    case "Alero":
                        out.writeUTF("₡1,100,000");
                        break;
                    case "Ala_Pívot":
                        out.writeUTF("₡1,300,000");
                        break;
                    case "Pívot":
                        out.writeUTF("₡1,400,000");
                        break;
                    default:
                        out.writeUTF("Posicion no encontrada.");
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
