package model;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class MyClientSocket {
    private Socket socket;
    private Scanner scanner;
    
    private MyClientSocket(String serverAddress) throws Exception {
        this.socket = new Socket(serverAddress, 52018);
        this.scanner = new Scanner(System.in);
    }
    /*private void start() throws IOException {
        String input;
        while (true) {
            input = scanner.nextLine();
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            out.println(input);
            out.flush();
        }
    }*/
}