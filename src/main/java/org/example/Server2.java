package org.example;
import java.io.*;
import java.net.*;


public class Server2 {
    public static void main(String args[]) throws Exception {


        // Create server Socket
        ServerSocket ss = new ServerSocket(888);

        // connect it to the client socket
        Socket s = ss.accept();
        System.out.println("Connection established");

        // to send  data to the client
        PrintStream ps = new PrintStream(s.getOutputStream());

        // read data coming from the client
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        // read data from the keyboard
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));

        //server executes continuously
        while (true) {
            String str, str1;

            //read from client
            while ((str = br.readLine()) != null) {
                System.out.println(str);
                str1 = kb.readLine();

                // send to client
                ps.println(str1);

                ps.close();
                br.close();
                kb.close();
                ss.close();
                s.close();

                System.exit(0);
            }
        }
    }
}
