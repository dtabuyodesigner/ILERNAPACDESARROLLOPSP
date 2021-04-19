package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket servidor;//Tipo de socket que permite aceptar conexiones
    private int PUERTO = 9876;

    public Server() {
        try {
            servidor = new ServerSocket(PUERTO);
        } catch (IOException ex) {
            System.err.println("Problema con el puerto seleccionado");
        }
    }

    /* Método para arrancar el servidor. Voy a mostrar mensajes en el servidor
     aunque realmente no son necesarios pero así veo la recepción y emisión de
     mensajes entre Cliente y Servidor.*/
    public void arrancaservidor() {
        try {
            Socket socket = servidor.accept();//Esperando hasta que un cliente se conecte
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            System.out.println("Cliente conectado con éxito");
            output.writeUTF("Servidor: ¿Cómo te llamas?");//Mandamos este texto al cliente
            System.out.println("Cliente: " + input.readUTF());
            output.writeUTF("¿Cuántas tareas deseas realizar?");
            int numTareas = input.readInt();//Capturamos el nº tareas que nos pasa cliente
            System.out.println("Cliente:las tareas a realizar son " + numTareas);
            ArrayList<Tarea> tareas = new ArrayList<>(numTareas);
            for (int i = 0; i < numTareas; i++) {
                output.writeUTF("El número de la tarea es " + (i + 1));//Servidor envía a cliente el número de la tarea
                output.writeUTF("Dime qué tarea es");
                String descripcion = input.readUTF();
                System.out.println("Cliente: la tarea es " + descripcion);
                output.writeUTF("¿En qué estado está la tarea?");
                String estado = input.readUTF();
                System.out.println("Cliente: " + estado);
                //Guardamnos las tareas
                Tarea tarea = new Tarea(descripcion, estado);
                tareas.add(tarea);
            }
            output.writeUTF("Te envio tu lista de tareas");
            for (int i = 0; i < numTareas; i++) {
                output.writeUTF(tareas.get(i).toString());
            }
        } catch (IOException e) {
            System.err.println("No se puede aceptar la petición del cliente");
        }
    }
}

