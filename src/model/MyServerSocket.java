package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyServerSocket {
    private ServerSocket ss;
    public MyServerSocket(String clientIP) {
        (new Thread() {
            //Creates Server Socket
            @Override
            public void run() {
                try {
                    ss = new ServerSocket( 52018 );
                } catch (UnknownHostException ex) {
                    Logger.getLogger(MyServerSocket.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MyServerSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
                //Creates unknown client server (Ours? Theirs? Both?)
                Socket s = null;
                try {
                    s = ss.accept();
                } catch (IOException ex) {
                    Logger.getLogger(MyServerSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
                //Reading from unknown client server
                BufferedReader in = null;
                try {
                    in = new BufferedReader ( new InputStreamReader( s.getInputStream() ) );
                    System.out.println( in );
                } catch (IOException ex) {
                    Logger.getLogger(MyServerSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
                //Prints buffered reader
                String line = null;
                try {
                    while (( line = in.readLine() ) != null ) {
                        System.out.println( line );
                    }
                } catch ( IOException ex ) {
                    Logger.getLogger(MyServerSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }
}