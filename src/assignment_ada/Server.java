/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author valar
 */
public class Server {
    
    public static final int HOST_PORT = 6554;
    private boolean stopRequested;
    private final String official_password = "COBRAKAI@123!";
    static Server server = new Server();
    
    private Server() {
        stopRequested = false;
    }
    
    public void startServer() {
        stopRequested = false;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(HOST_PORT);
        }
        catch (IOException ex) {
            System.err.println("error: "+ex);
            System.exit(-1);
        }
        try
        {
            while (stopRequested == false) {
                Socket socket = serverSocket.accept();
                System.out.println("Connection made with"+socket.getInetAddress());
                Writer writer = new Writer(socket);
                Thread writerThread = new Thread(writer);
                writerThread.start();
            }
            serverSocket.close();
        }
        catch (IOException ex) {
            System.err.println("error: "+ex);
        }
    }
    //Writer class or method
    private class Writer implements Runnable{

        private Socket socket;
        private boolean connected;
        private PrintWriter writer;
        
        
        public Writer(Socket socket) {
            this.socket = socket;
            connected = true;
            try{
                writer = new PrintWriter(socket.getOutputStream(),true);
            }
            catch (IOException ex) {
                System.err.println("Error: " + ex);
            }
        }
        
        @Override
        public void run() {
            writer.println("Testing");
            while (connected == true) {
                
            }
            try {
                socket.close();
                writer.close();
            }
            catch (IOException ex) {
                System.err.println("Error: " + ex);
            }
        }
        
    }
    //Reader class 
    private class Reader implements Runnable{
        
        private Socket sockets;
        private boolean connected = false;
        private BufferedReader reader;
        
        public Reader(Socket sockets){
            this.sockets = sockets;
            connected = true;
        }
        
        @Override
        public void run() {
            while(connected == true){
                try {
                    reader = new BufferedReader(new InputStreamReader(sockets.getInputStream()));
                } catch (IOException ex) {
                    System.err.println("Error: " + ex);
                }
            }
        }  
    }
    
    public static void main(String[] args)
    {
        Server server = new Server();
        server.startServer();
    }
}
