package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.net.InetAddress;

public class MyClientSocket {
    private final Socket socket;
    private final Scanner scanner;
    private final String serverIP = "98.162.212.185";
    public MyClientSocket( InetAddress serverAddress, int serverPort ) throws Exception {
        this.socket = new Socket( serverAddress, serverPort );
        this.scanner = new Scanner( System.in );
    }
    public void start() throws IOException {
        String input;
        while ( true ) {
            input = scanner.nextLine();
            PrintWriter out = new PrintWriter( this.socket.getOutputStream(), true );
            out.println( input );
            out.flush();
        }
    }
}