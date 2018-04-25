package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
    // Client Socket
    public static void startSender() {
        try {
            Socket s = new Socket("localhost", 52018);
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(s.getOutputStream()));
            while (true) {
                out.write("1|0|-1");
                out.flush();
                Thread.sleep(200);
            }
        } catch (UnknownHostException e) {
        } catch (IOException | InterruptedException e) {
        }
    }
    // Server Socket
    public static void startServer() {
        (new Thread() {
            @Override
            public void run() {
                ServerSocket ss;
                try {
                    ss = new ServerSocket(52018);
                    Socket s = ss.accept();
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(s.getInputStream()));
                    String line = null;
                    while ((line = in.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                }
            }
        }).start();
    }
}