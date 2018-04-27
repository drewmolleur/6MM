package model;

import static javax.swing.UIManager.getString;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import gui.MenuGUI;


public class Server {
    public static ServerSocket serverSocket = null;
    public static Socket clientSocket = null;
    public static void startSender( String enemyIP ) {
        try {
            InetAddress enemyAddress = null;
            try {
                enemyAddress = InetAddress.getByName( getString( enemyIP ) );
            } catch (UnknownHostException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            byte[] bytes = enemyAddress.getAddress();
            try {
                enemyAddress = enemyAddress.getByAddress( bytes );

            } catch (UnknownHostException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            System.out.println("Client Started");
            clientSocket = new Socket( enemyIP, 52018 );
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(clientSocket.getOutputStream()));
            while (true) {
                out.write( "1|0|-1" );
                out.flush();
                Thread.sleep( 200 );
            }
        } catch (UnknownHostException e) {
        } catch (IOException | InterruptedException e) {
        }
    }
    public static void startServer( ) {
        (new Thread() {
            @Override
            public void run() {
                ServerSocket ss;
                try {
                    ss = new ServerSocket( 52018, 5 );
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
    public static void sendMove(Move move) {
        if (clientSocket != null) {
            BufferedWriter out = null;
            try {
                out = new BufferedWriter(
                        new OutputStreamWriter(clientSocket.getOutputStream()));
                try {
                	String sentData= move.getFormattedData();
                	System.out.println(sentData);
                    out.write( sentData );
                    out.flush();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }
    public static void receiveMove(Move move) throws IOException {
        ServerSocket ss = Server.serverSocket;
        Socket s = ss.accept();
        BufferedReader in = new BufferedReader(
                            new InputStreamReader(s.getInputStream()));
                    String line = null;
                    while ((line = (String)in.readLine()) != null) {
                        System.out.println(line);
                    }
    }
}