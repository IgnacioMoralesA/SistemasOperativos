import java.net.*;

public class servidorudp {

    public static void main(String[] argv) {

        DatagramSocket socket;

        try {
            //Creamos el socket
            socket = new DatagramSocket(6000);
            byte[] mensaje_bytes = new byte[256];
            String mensaje;
            String mensajeComp ="";

            DatagramPacket paquete = new DatagramPacket(mensaje_bytes,256);
            DatagramPacket envpaquete = new DatagramPacket(mensaje_bytes,256);

            int puerto;
            InetAddress address;
            byte[] mensaje2_bytes;

             //Iniciamos el bucle
            do {
                // Recibimos el paquete
                socket.receive(paquete);
                // Lo formateamos
                mensaje = new String(mensaje_bytes).trim();
                // Lo mostramos por pantalla
                System.out.println(mensaje);
                //Obtenemos IP Y PUERTO
                puerto = paquete.getPort();
                address = paquete.getAddress();

                if (mensaje.startsWith("fin")) {
                    mensajeComp="chao cliente";
                }

                if (mensaje.startsWith("hola")) {
                    mensajeComp="hola cliente";
                }

                if(mensaje.startsWith("a")){
                    mensajeComp="Ok";
                }

                //formateamos el mensaje de salida
                mensaje2_bytes = mensajeComp.getBytes();

                //Preparamos el paquete que queremos enviar
                envpaquete = new DatagramPacket(mensaje2_bytes,mensajeComp.length(),address,puerto);

                // realizamos el envio
                socket.send(envpaquete);

            } while (true);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}