import java.io.*;
import java.net.*;

public class Server_Socket {

    static ServerSocket ss;
    static Socket s ;
    static String host_user;

    public static void main() throws IOException {

        //declaraciones para la coneccion del servidor y el cliente

        final int port_num = 4545;
        ss = new ServerSocket(port_num);
        System.out.println("server online");//notifica al activar el socket del servidor
        s = ss.accept();
        System.out.println("client conected");//notifica cuando un cliente se conecta
        InputStreamReader RD = new InputStreamReader(s.getInputStream());
        BufferedReader BFRD = new BufferedReader(RD);
        PrintWriter WRT = new PrintWriter(s.getOutputStream());
        String client_user =  BFRD.readLine();
        System.out.println(client_user);
        WRT.println(host_user);
        WRT.flush();

        try {

            while(true) {
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }


    }
    public static void setName(String name){
        host_user = name;
    }
    }

