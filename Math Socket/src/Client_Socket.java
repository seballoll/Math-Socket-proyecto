import java.io.*;
import java.net.*;

public class Client_Socket extends ChessBoardWithColumnsAndRows {
    static Socket s;
    static String username ;

    /**
     * starts client
     * @throws IOException
     */
    public static void main() throws IOException {
        s = new Socket("LocalHost",4545);
        InputStreamReader RD = new InputStreamReader(s.getInputStream());
        BufferedReader BFRD = new BufferedReader(RD);
        PrintWriter WRT = new PrintWriter(s.getOutputStream());
        WRT.println(username);
        WRT.flush();
        String server_user = BFRD.readLine();
        System.out.println(server_user);

    }
    /**
     * pone el nombre del jugador
     */
    public static void setName(String name){
        username = name;
    }

    /**
     * recibe el tablero enviado desde el servidor
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void Receive_Board() throws IOException, ClassNotFoundException {
        Lista_Doble casillas;
        ObjectInputStream OIRD = new ObjectInputStream(s.getInputStream()) ;
            casillas = (Lista_Doble) OIRD.readObject();
            OIRD.close();

        System.out.println(casillas);
        ChessBoardWithColumnsAndRows.setCasillas(casillas);
    }



}
