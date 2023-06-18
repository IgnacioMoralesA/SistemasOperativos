import java.net.*;
import java.io.*;
import java.util.Scanner;

public class clienteTCP {
    //creamos Socket
    static String HOST = "localhost";
    static int PUERTO = 5000;
    Socket sc;
    DataInputStream entrada;
    DataOutputStream salida;
    String mensajeRecibido;

    public void initCliente(){
        Scanner leer = new Scanner(System.in);
        try{
            sc = new Socket(HOST,PUERTO);
            //esto inicia la salida de mensajes
            salida = new DataOutputStream(sc.getOutputStream());
            //aqui se inicia la entrada de mensajes
            entrada = new DataInputStream(sc.getInputStream());
            String mensaje = "";
            //aqui se realiza el envio de mensaje
            while (!mensaje.equals("fin")){
                System.out.println("Escriba un mensaje para el servidor");
                mensaje = leer.nextLine();
                salida.writeUTF(mensaje);
                //recepcion de mensajes
                mensajeRecibido = entrada.readUTF();
                System.out.println(mensajeRecibido);
            }
            // close de conexion
            sc.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //main
        clienteTCP ctpc = new clienteTCP();
        ctpc.initCliente();
    }
}
