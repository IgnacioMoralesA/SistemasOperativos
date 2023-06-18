import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ServerTCP {
    static int PUERTO = 5000;
    ServerSocket socket;
    Socket so;
    DataInputStream entrada;
    DataOutputStream salida;
    String mensajeRecibido;

    public void initServidor(){
        Scanner leer = new Scanner(System.in);
        try {
            socket = new ServerSocket(PUERTO);
            so = new Socket();

            System.out.println("Esperando conexion...");
            so = socket.accept();
            System.out.println("Se conecto el cliente!");
            entrada = new DataInputStream(so.getInputStream());
            salida = new DataOutputStream(so.getOutputStream());
            String mensaje = "";
            while(!mensaje.equals("fin")){

                mensajeRecibido = entrada.readUTF();
                System.out.println(mensajeRecibido);
                System.out.println("Escriba un mensaje para el cliente");
                mensaje = leer.nextLine();
                salida.writeUTF(""+mensaje);

            }
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] argv) {
        ServerTCP tcp = new ServerTCP();
        tcp.initServidor();
    }
}