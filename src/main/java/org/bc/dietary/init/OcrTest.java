package org.bc.dietary.init;

import java.io.File;
import java.io.IOException;

public class OcrTest {
	public static void main(String[] args) {  
        String path = "E:\\java\\git\\dietary\\11.jpg";       
        System.out.println("ORC Test Begin......");  
        try {       
            String valCode = new OCR().recognizeText(new File(path), "jpg");       
            System.out.println(valCode);       
        } catch (IOException e) {       
            e.printStackTrace();
        } catch (Exception e) {    
            e.printStackTrace();    
        }         
        System.out.println("ORC Test End......");  
    }
}
