import java.io.*;
import java.net.*;

public class Client_Socket {
    static Socket s;

    public static void main(String[] args) throws IOException {
        s = new Socket("localhost", 4545);
        InputStreamReader RD = new InputStreamReader(s.getInputStream());
        BufferedReader BFRD = new BufferedReader(RD);
        PrintWriter WRT = new PrintWriter(s.getOutputStream());

        try {

            while(true) {
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }



}
