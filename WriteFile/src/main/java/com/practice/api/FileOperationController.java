package com.practice.api;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.bitbucket.openisoj.formatter.BinaryFormatter;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileOperationController {
	
	@PostMapping(value="/send/chunk")
	public String readMultipartData(HttpServletRequest request) {
		System.out.println("here i am in now.......");
		Map<String, String[]> parameterMap = request.getParameterMap();
		parameterMap.entrySet().stream().forEach(System.out::println);
//		String[] strArr = parameterMap.get("chunk");
//		byte[] to_byte = to_byte(strArr);
//		writeByte(to_byte);
		System.out.println(parameterMap);
		
		return "Sucessfully called";
	}
	
	
	@RequestMapping(value="/writechunk", method=RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String writeChunkData(@RequestBody MultiValueMap<String, Object> formData){
//		List<byte[]> list2 = formData.get("fileName");
		List<Object> list = formData.get("chunk");
		List<Object> list3 = formData.get("file");
		System.out.println("list::::"+list.get(0));
		System.out.println("list3::::"+list3.get(0));
		//System.out.println(Arrays.toString(list.get(0)));
		//System.out.println("list2::::"+list2.get(0));
		ObjectToByteArray(list.get(0));
		try(FileOutputStream fileOut = new FileOutputStream("d:/qbc.txt");
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
        objectOut.writeObject(list3.get(0));
		} catch(Exception e) {
			e.printStackTrace();
		}
		//byte[] bs = list.get(0);
		System.out.println("Working as escpeted");
		return "Sucessfull";
	}
	
	// Convert an object to a byte array
	private void ObjectToByteArray(Object obj) {

		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			ObjectOutput out = new ObjectOutputStream(bos); 
			out.writeObject(obj);
			out.flush();
			byte[] yourBytes = bos.toByteArray();
			writeByte(yourBytes);
		} catch(IOException e) {
			e.printStackTrace();
		}

	}

    // Method which write the bytes into a file 
    public void writeByte(byte[] bytes) { 
    	String s = Base64.getEncoder().encodeToString(bytes);
    	System.out.println("stringstringstring::::"+s);
    	String string = new String(bytes, StandardCharsets.UTF_8);
    	System.out.println("stringstringstring::::"+string);
    	// ENCODING
        String encodedDoc= Base64.getEncoder().encodeToString(bytes);

        System.out.println(encodedDoc);

        // DECODING
        int size = bytes.length;
        InputStream isfilecontent = null;
        //byte[] b = new byte[size];
        isfilecontent = new ByteArrayInputStream(Base64.getDecoder().decode(encodedDoc));
    	
        
        byte[] buf2 = new byte[2048 * 2048];
    	// Initialize a pointer 
        // in file using OutputStream 
        try (OutputStream os  = new FileOutputStream(new File("d:/test1.txt")); ) { 
        	int b_read = 0;
        	while ( (b_read = isfilecontent.read(buf2)) > 0) {
        		os.write(buf2, 0, b_read);
            }
            os.flush();
            //closed the output stream
            os.close();
            // Starts writing the bytes in it 
            //os.write(isfilecontent); 
            System.out.println("Successfully byte inserted"); 
            // Close the file 
        } 
        catch (Exception e) { 
            System.out.println("Exception: " + e); 
        } 
    } 
    
//    private byte[] JoinArrays(IEnumerable<byte[]> arrays) {
//        int offset = 0;
//        byte[] fullArray = new byte[arrays.Sum(a => a.Length)];
//        foreach (byte[] array in arrays)
//        {
//            Buffer.BlockCopy(array, 0, fullArray, offset, array.Length);
//            offset += array.Length;
//        }
//        return fullArray;
//    }
    
    public  byte[] to_byte(String[] strs) {
        byte[] bytes=new byte[strs.length];
        for (int i=0; i<strs.length; i++) {
            bytes[i]=Byte.parseByte(strs[i]);
        }
        return bytes;
    }

}
