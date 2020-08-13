package com.practice.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ReadFile {

		
public static void main(String[] args) throws Exception {
    byte[] array = new byte[1024];
    FileInputStream fis = new FileInputStream(new File("d:/text.txt"));
    FileOutputStream fos = new FileOutputStream(new File("d:/text23.txt"));
    int length = 0;
    while((length = fis.read(array)) != -1)
    {
    	System.out.println("Tiems called");
        fos.write(array, 0, length);
    }
    fis.close();
    fos.close();
}


}
