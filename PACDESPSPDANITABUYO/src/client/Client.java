package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket cliente;
    private int PUERTO = 9876;
    private String HOST = "localhost";

    //Creamos el constructor del cliente

    public Client() {
        try {
            cliente = new Socket(HOST, PUERTO);

        } catch (IOException ex) {
            System.err.println("Error al conectarse al host: " + HOST);
        }
    }

    public void arrancarcliente() {
        try {
            Scanner sc = new Scanner(System.in);
            DataInputStream input = new DataInputStream(cliente.getInputStream());
            DataOutputStream output = new DataOutputStream(cliente.getOutputStream());
            System.out.println("Servidor: " + input.readUTF());//Leemos el mensaje que nos manda el servidor
            String nombre = sc.nextLine();
            output.writeUTF(nombre);
            System.out.println("Servidor: " + input.readUTF());
            int numTareas = sc.nextInt();
            sc.nextLine();
            output.writeInt(numTareas);
            for (int i = 0; i < numTareas; i++) {
                System.out.println("Servidor: " + input.readUTF());
                System.out.println("Servidor: " + input.readUTF());
                String tarea_es = sc.nextLine();
                output.writeUTF(tarea_es);

                System.out.println("Servidor: " + input.readUTF());
                String estado_tarea = sc.nextLine();
                output.writeUTF(estado_tarea);
            }
            System.out.println("Servidor: " + input.readUTF());
            for (int i = 0; i < numTareas; i++) {
                System.out.println("Servidor: " + input.readUTF());
            }
        } catch (IOException e) {
            System.err.println("Problema para hablar con el servidor");
        }

    }
}

