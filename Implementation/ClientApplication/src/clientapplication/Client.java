/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientapplication;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.imageio.ImageIO;


import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Andrew
 */
public class Client {
    private final static String SERVER_ADDRESS = "127.0.0.1";
    private final static int PORT = 8100;
    
    private final static int clientPort = 8101;
    private static ServerSocket clientServerSocket;
    private boolean running;
    
        //aici e response[]
    private static JsonTransform createObject(String[] results) { 
        JsonTransform obj = new JsonTransform();
        
        //aici poate sa dea Array out of bounds exception daca String[] e 
        obj.setFilepath(results[0]);
        obj.setClasss(results[1]);
        obj.setConcept(results[2]);
        obj.setMetadata(results[3]);
        obj.setClassmatching(results[4]);
        obj.setConceptmatching(results[5]);
        obj.setOthers(results[6]);
        obj.setDescription(results[7]);  
        return obj;
    }
    
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        
        ClientServer server = new ClientServer();
        server.init();
        server.waitForClients(); 
            
       
            /*
            BufferedImage img = null;
            img = ImageIO.read(new File("C:\\Users\\Andrew\\Desktop\\Client+Server\\Server\\download.jpg"));
           
            
            
            client.sendRequestToServer(img);
            */
        
    }

    public String sendRequestToServer(BufferedImage request) throws IOException {
       try{
       Socket socket = new Socket(Client.SERVER_ADDRESS, Client.PORT);
       //PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
       
       BufferedReader in = new BufferedReader (
        new InputStreamReader(socket.getInputStream())) ;
           
	ImageIO.write(request, "bmp", socket.getOutputStream());
       
       String response = in.readLine ();
       
       
       
       
       
        String results[] ={" "," "," "," "," "," "," "," "};
     
        String temporar = "";
        int temporarvar = 0;
        
        for(int i = 0 ; i!=response.length() ;i++ ){
            char c = response.charAt(i);
            if(c!= '%'){
            if(c != ' ') {
                temporar = temporar + c;
                
            }
            else{
               temporar = temporar.replace(",",".");
               results[temporarvar] = temporar;
               temporarvar = temporarvar + 1;
           //    System.out.println(temporar);
               temporar = "";
            }
            }
        }
        results[temporarvar] = temporar;
        //System.out.println(Arrays.toString(results));
        
      
        // aici fac transformarea din String in String[];
      
       
      // System.out.println(Arrays.toString(results));
       
       JsonTransform obj = null;
       obj = createObject(results);
       ObjectMapper mapper = new ObjectMapper();
       String json = mapper.writeValueAsString(obj);
       
       //System.out.println(json);
       
       return json;
       

       }
        catch (UnknownHostException e)
        {
           System.err.println("No server listening... " + e); 
           return "err";
        }
    }

}