import java.io.*;
import java.net.*;

public class Client_Socket {
    static Socket s;
    static String username ;

    public static void main() throws IOException {
        s = new Socket("LocalHost", 4545);
        InputStreamReader RD = new InputStreamReader(s.getInputStream());
        BufferedReader BFRD = new BufferedReader(RD);
        PrintWriter WRT = new PrintWriter(s.getOutputStream());
        WRT.println(username);
        WRT.flush();
        String server_user = BFRD.readLine();
        System.out.println(server_user);
        try {

            while(true) {
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public static void setName(String name){
        username = name;
    }



}
