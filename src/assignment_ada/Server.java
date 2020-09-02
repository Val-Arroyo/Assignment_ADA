/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author valar
 */
public class Server {
    
    public static final int HOST_PORT = 6554;
    private boolean stopRequested;
    ServerSocket serversocket = null;
    private final String official_password = "FIFA@123!";
    
    public Server(){
        
    }
    public void startServer(){
        stopRequested = false;
        ServerSocket serversocket = null;
    }
}
