import java.io.*;
import java.net.*;

public class Client_Socket extends ChessBoardWithColumnsAndRows {
    static Socket s;
    static String username ;

    /**
     * metodo para iniciar el socket (debe tener un servidor al que conectar)
     * @throws IOException
     */
    public static void main() throws IOException {
        s = new Socket("LocalHost", 4545);
        InputStreamReader RD = new InputStreamReader(s.getInputStream());
        BufferedReader BFRD = new BufferedReader(RD);
        PrintWriter WRT = new PrintWriter(s.getOutputStream());
        WRT.println(username);
        WRT.flush();
        String server_user = BFRD.readLine();
        System.out.println(server_user);

    }

    public static void setName(String name){
        username = name;
    }

    /**
     * metodo para recibir el tablero de juego, carga el tablero directamente a la interfaz
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void Receive_Board() throws IOException, ClassNotFoundException {
        ObjectInputStream OIRD = new ObjectInputStream(s.getInputStream());
        Lista_Doble casillas = (Lista_Doble) OIRD.readObject();
        OIRD.close();
        System.out.println(casillas);
        ChessBoardWithColumnsAndRows.setCasillas(casillas);
    }



}
