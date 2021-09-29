import java.io.*;
import java.net.*;


public class Server_Socket extends ChessBoardWithColumnsAndRows {

    static ServerSocket ss;
    static Socket s ;
    static String host_user;

    /**
     * metodo para inicar el servidor (espera hasta que un cliente se conecte)
     * @throws IOException
     */
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




    }
    public static void setName(String name){
        host_user = name;
    }

    /**
     * metodo para enviar el tablero al cliente
     * @param Casillas Lista_doble con el tablero de juego
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void Send_Board(Lista_Doble Casillas) throws IOException, ClassNotFoundException { System.out.println(Casillas);
        ObjectOutputStream OOSTR = new ObjectOutputStream(s.getOutputStream());
        OOSTR.writeUnshared(Casillas);
        OOSTR.close();


    }
    }

