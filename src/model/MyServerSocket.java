package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyServerSocket {
    
    private ServerSocket ss;
    
    public MyServerSocket( String clientIP ) throws UnknownHostException {
        
        InetAddress ip = InetAddress.getByName( "23.115.205.253" );
        
        byte[] bytes = ip.getAddress();
        
        (new Thread() {
            
            //Create Server Socket
            @Override
            public void run() {
                try {
                /**
                * Returns an {@code InetAddress} object given the raw IP address .
                * The argument is in network byte order: the highest order
                * byte of the address is in {@code getAddress()[0]}.
                *
                * <p> This method doesn't block, i.e. no reverse name service lookup
                * is performed.
                *
                * <p> IPv4 address byte array must be 4 bytes long and IPv6 byte array
                * must be 16 bytes long
                *
                * @param addr the raw IP address in network byte order
                * @return  an InetAddress object created from the raw IP address.
                * @exception  UnknownHostException  if IP address is of illegal length
                * @since 1.4
                */
                    ss = new ServerSocket( 52018, 50, InetAddress.getByAddress( bytes ) );
                } catch ( UnknownHostException ex ) {
                    Logger.getLogger( MyServerSocket.class.getName() ).log( Level.SEVERE, null, ex );
                } catch ( IOException ex ) {
                    Logger.getLogger( MyServerSocket.class.getName() ).log( Level.SEVERE, null, ex );
                }
                
                //Creates unknown client server (Ours? Theirs? Both?)
                Socket s = null;
                try {
                    s = ss.accept();
                } catch ( IOException ex ) {
                    Logger.getLogger( MyServerSocket.class.getName() ).log( Level.SEVERE, null, ex );
                }
                
                //Read from unknown client server (Ours? Theirs? Both?)
                BufferedReader in = null;
                try {
                    in = new BufferedReader ( new InputStreamReader( s.getInputStream() ) );
                    System.out.println( in );
                } catch ( IOException ex ) {
                    Logger.getLogger( MyServerSocket.class.getName() ).log( Level.SEVERE, null, ex );
                }
                
                //Prints buffered reader
                String line = null;
                try {
                    while ( ( line = in.readLine() ) != null ) {
                        System.out.println( line );
                    }
                } catch ( IOException ex ) {
                    Logger.getLogger( MyServerSocket.class.getName() ).log( Level.SEVERE, null, ex );
                }
            }   //Starts something or the other....
        }       ).start();
    }
}