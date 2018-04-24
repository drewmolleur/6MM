package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerSocket {
    private final ServerSocket server;
    public MyServerSocket( String clientIP ) throws Exception {
        if ( clientIP != null && !clientIP.isEmpty() ) {
          this.server = new ServerSocket( 52018, 1, InetAddress.getByName( clientIP ) );
        }
        else 
          this.server = new ServerSocket(52018, 1, InetAddress.getLocalHost());
    }
    public void listen( String clientIP ) throws Exception {
        String data = null;
        Socket client = this.server.accept();
        String clientAddress = clientIP;
        System.out.println("\r\nNew connection from " + clientAddress);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream()));        
        while ( (data = in.readLine()) != null ) {
            System.out.println("\r\nMessage from " + clientAddress + ": " + data);
        }
    }
    public InetAddress getSocketAddress() {
        return this.server.getInetAddress();
    }
    public int getPort() {
        return this.server.getLocalPort();
    }
}