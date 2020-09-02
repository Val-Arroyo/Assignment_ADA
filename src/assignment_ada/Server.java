/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
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
                
            }
        }
        
    }
    
    private class Reader implements Runnable{

        @Override
        public void run() {
            
        }
        
    }
    
    public static void main(String[] args)
    {
        Server server = new Server();
        server.startServer();
    }
}
