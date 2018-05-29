/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testm1;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrew
 */
public class TestM1 
{
    public static void main(String[] args)  {
         
        TestM1 test=new TestM1();
        try {
            test.sendImage("127.0.0.1", 8101,"C:\\Users\\Andrew\\Desktop\\object-recognition-tensorflow-server_client\\Thermoflow\\download (2).jpg","jpg");
        } catch (IOException ex) {
            Logger.getLogger(TestM1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void sendImage(String host, int port, String fileLocation, String fileExtension) throws IOException
    {
        try (Socket socket = new Socket(host, port))
        {
            OutputStream outputStream = socket.getOutputStream();

            BufferedImage image = ImageIO.read(new File(fileLocation));

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            
            //ImageIO.write(image, fileExtension, byteArrayOutputStream); //fileExtension -> jpg, png..etc
            
            ImageIO.write(image, "bmp", socket.getOutputStream());
            
            BufferedReader in = new BufferedReader (
            new InputStreamReader(socket.getInputStream())) ;
            
            String response = in.readLine ();
            System.out.println(response);
            
            
            byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
            outputStream.write(size);
            outputStream.write(byteArrayOutputStream.toByteArray());
            outputStream.flush();
            System.out.println("Flushed: " + System.currentTimeMillis());

            Thread.sleep(120000);
            System.out.println("Closing: " + System.currentTimeMillis());
    } catch (InterruptedException e)
        {
            System.err.println("Conexiune intrerupta!");
        } catch (UnknownHostException e)
        {
            System.err.println("Host necunoscut!");
        }
    }

}
    

