/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientapplication;

import com.sun.corba.se.spi.activation.Server;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Andrew
 */
public class ClientServer {
    private static final int PORT = 8101;
    private ServerSocket serverSocket;
    private boolean running = false;
    
    public void setRunning(boolean running) {
        this.running = running;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
	
    public void stop() throws IOException {
        this.running = false;
    }	

    public void init() {
        try {
            this.serverSocket=new ServerSocket(PORT);
            this.running=true;
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void waitForClients() {
       
        
        Client client=new Client();
        
       while(this.running)
       {
           try {
               
               Socket socket=this.serverSocket.accept();
               BufferedImage img = ImageIO.read(socket.getInputStream());
              
               
               
               String response=client.sendRequestToServer(img);
               
               System.out.println(response);
               
               PrintWriter out = new PrintWriter(socket.getOutputStream()); //server -> client stream
               out.println(response);
               out.flush();
               
               
           } catch (IOException ex) {
               Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
    }
}
