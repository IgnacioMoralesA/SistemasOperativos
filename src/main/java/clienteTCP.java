import java.net.*;
import java.io.*;
import java.util.Scanner;

public class clienteTCP {

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
            salida = new DataOutputStream(sc.getOutputStream());
            entrada = new DataInputStream(sc.getInputStream());
            String mensaje = "";
            while (!mensaje.equals("fin")){
                System.out.println("Escriba un mensaje para el servidor");
                mensaje = leer.nextLine();
                salida.writeUTF(mensaje);
                mensajeRecibido = entrada.readUTF();
                System.out.println(mensajeRecibido);
            }

            sc.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        clienteTCP ctpc = new clienteTCP();
        ctpc.initCliente();
    }
}
