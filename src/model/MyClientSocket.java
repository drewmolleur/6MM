package model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClientSocket {
    
    public MyClientSocket() {
        
        (new Thread() {
            
            @Override
            public void run() {

                //Creates Buffered Writer Client
                try {
                    Socket s = new Socket( "98.162.212.185" , 52018);
                    BufferedWriter out = new BufferedWriter( 
                                            new OutputStreamWriter( 
                                            s.getOutputStream() 
                                            ));
                    //Infinite loop
                    while (true) {
                        //Supposed to send our move
                        out.write("0|1|-1");
                        System.out.println("0|1|-1");
                        out.flush();
                        Thread.sleep(200);
                    }
                } catch (UnknownHostException e) {
                } catch (IOException | InterruptedException e) {
                }
            }   //Starts something or the other...
        }       ).start();
    }
}