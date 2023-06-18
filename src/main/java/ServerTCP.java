import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ServerTCP {
    //creamos el Socket
    static int PUERTO = 5000;
    ServerSocket socket;
    Socket so;
    DataInputStream entrada;
    DataOutputStream salida;
    String mensajeRecibido;

    public void initServidor(){
        Scanner leer = new Scanner(System.in);
        try {
            //empezamos la conexion
            socket = new ServerSocket(PUERTO);
            so = new Socket();

            System.out.println("Esperando conexion...");
            so = socket.accept();
            System.out.println("Se conecto el cliente!");
            entrada = new DataInputStream(so.getInputStream());
            salida = new DataOutputStream(so.getOutputStream());
            String mensaje = "";
            while(!mensaje.equals("fin")){
                //envio de mensajes para el cliente
                mensajeRecibido = entrada.readUTF();
                System.out.println(mensajeRecibido);
                System.out.println("Escriba un mensaje para el cliente");
                mensaje = leer.nextLine();
                salida.writeUTF(""+mensaje);

            }
            //cierre de conexion
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] argv) {
        //main
        ServerTCP tcp = new ServerTCP();
        tcp.initServidor();
    }
}
