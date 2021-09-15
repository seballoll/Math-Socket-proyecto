import java.io.*;
import java.net.*;

public class Client_Socket {
    static Socket s;

    public static void main(String[] args) throws IOException {
        s = new Socket("localhost", 4545);

        try {

            while(true) {
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }



}
