package com.practice.api;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

public class FileSplit {

	public static void splitFile(File f) throws IOException {
        int partCounter = 1;//I like to name parts from 001, 002, 003, ...
                            //you can change it to 0 if you want 000, 001, ...

        int sizeOfFiles = 1024 * 1024;// 1MB
        byte[] buffer = new byte[sizeOfFiles];

        String fileName = f.getName();
        MultiValueMap<String, Object> formData;
        //try-with-resources to ensure closing stream
        try (FileInputStream fis = new FileInputStream(f);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            int bytesAmount = 0;
            while ((bytesAmount = bis.read(buffer)) > 0) {
            	
            	formData = new LinkedMultiValueMap<>();
	              formData.add("fileName", fileName);
	              formData.add("chunk", buffer);
	             // formData.add("length", realSize);
                //write each chunk of data into separate file with different number in name
//                String filePartName = String.format("%s.%03d", fileName, partCounter++);
//                File newFile = new File(f.getParent(), filePartName);
//                try (FileOutputStream out = new FileOutputStream(newFile)) {
//                    out.write(buffer, 0, bytesAmount);
//                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        splitFile(new File("D:\\destination\\myFile.mp4"));
    }
    
    @Autowired
    RestTemplate restTemplate;

    public void sendChunkData(MultiValueMap<String, Object> formData) {
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    	HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(formData, headers);
//
//    	ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
//	   HttpHeaders headers = new HttpHeaders();
//	   headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//	   HttpEntity entity = new HttpEntity(formData,headers);
	   
	   restTemplate.exchange("http://localhost:8080/products", HttpMethod.POST, entity, String.class).getBody();
    }

}
