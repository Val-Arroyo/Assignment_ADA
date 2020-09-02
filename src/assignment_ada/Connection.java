/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.BufferedReader; // Socket output reader
import java.io.IOException;
import java.io.InputStreamReader; // Socket Reader
import java.io.PrintWriter;
import java.net.Socket; // To enable to connection between servers


/**
 *
 * @author valar
 */
public class Connection {

    public static final String HOST_NAME = "localhost";
    public static final int HOST_PORT = 6554;
    private Socket sockets;
    private Writer writer;
    private Reader reader;

    //Constructor for Connection
    public Connection() {
        //DO NOTHING
    }

    private class Writer implements Runnable {

        //Output to the server
        PrintWriter writer;
        boolean connected = true;
        String inputMessage = null;
        
        public Writer() {
            
            try {
                writer = new PrintWriter(sockets.getOutputStream(), true);
            } catch (IOException ex) {
                System.err.println("Error printwriter: " + ex);
            }
        }

        @Override
        public void run() {
            // Keeping running or connected until the boolean becomes false
            
            while(connected == true){
                
                if(inputMessage != null){
                    writer.println(inputMessage);
                    inputMessage = null;
                }
            }
            writer.close();
        }
    }

    private class Reader implements Runnable {
        //Input to the server
        BufferedReader reader;
        boolean connected = true;
        
        public Reader(){
            try{
                reader = new BufferedReader(new InputStreamReader(sockets.getInputStream()));
            }
            catch(IOException ex){
                System.err.println("Error for Reader: " + ex);
            }
        }
        
        @Override
        public void run(){
            
            while(connected == true){
                
                try{
                    String serverMessage = reader.readLine();
                    System.out.println(serverMessage);
                }
                catch(IOException ex){
                    System.err.println("Reader Error: " + ex);
                }
            }
            try{
                reader.close();
            }
            catch(IOException ex){
                System.err.println("Error: " + ex );
            }
        }
    }

    public void startConnection() {

        sockets = null;

        try {
            sockets = new Socket(HOST_NAME, HOST_PORT);
        } catch (IOException ex) {
            System.err.println("Error: " + ex);
            System.exit(-1);
        }
       writer = new Writer();
       reader = new Reader();
       
       Thread writerRunner = new Thread(writer);
       Thread readerRunner = new Thread(reader);
       
       writerRunner.start();
       readerRunner.start();
    }
}
