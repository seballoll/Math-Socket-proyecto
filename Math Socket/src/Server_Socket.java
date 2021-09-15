import java.io.*;
import java.net.*;

public class Server_Socket {

    static ServerSocket ss;
    static Socket s ;

    public static void main(String[] args) throws IOException {

        //declaraciones para la coneccion del servidor y el cliente

        final int port_num = 4545;
        ss = new ServerSocket(port_num);
        System.out.println("server online");//notifica al activar el socket del servidor
        s = ss.accept();
        System.out.println("client conected");//notifica cuando un cliente se conecta

        try {

            while(true) {
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }


    }
    }

