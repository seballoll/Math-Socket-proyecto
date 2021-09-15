import java.io.*;
import java.net.*;

public class Client_Socket {
    static Socket s;

    public static void main(String[] args) throws IOException {
        conect_socket();
    }
    public static void conect_socket() throws IOException {
        s = new Socket("localhost", 4545);

    }


}
