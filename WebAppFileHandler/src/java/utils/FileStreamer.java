/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giagkas
 */
public class FileStreamer {
    
    public static byte[] readInputStreamToBytes(InputStream input){
        byte[] buffer = new byte[8192];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            while((bytesRead=input.read(buffer))!=-1){
               output.write(buffer, 0, bytesRead);
            }
        } catch (IOException ex) {
            Logger.getLogger(FileStreamer.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        return output.toByteArray();
    }
    
    
    
    public static void inputStreamToOutputStream(InputStream inputStream, OutputStream outputStream){
        byte[] buffer = new byte[4096];
        int byteRead;
        try {
            while((byteRead=inputStream.read(buffer))!=-1){
                outputStream.write(buffer, 0, byteRead);
            }
        } catch (IOException ex) {
            Logger.getLogger(FileStreamer.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                inputStream.close();
                        } catch (IOException ex) {
                Logger.getLogger(FileStreamer.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                outputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(FileStreamer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
