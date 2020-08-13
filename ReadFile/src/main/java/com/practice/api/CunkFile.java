package com.practice.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class CunkFile {

	public static void main(String[] args) throws FileNotFoundException {
		
//		//Path pp = FileSystems.getDefault().getPath("logs", "access.log");
//	    final int BUFFER_SIZE = 1024*1024; //this is actually bytes
//	    MultiValueMap<String, Object> formData;
//	    String fileName = "film.mkv";
//	    try (FileInputStream fis = new FileInputStream("d:/text.txt")){
//		    byte[] buffer = new byte[BUFFER_SIZE]; 
//		    int read = 0;
//			while( ( read = fis.read( buffer ) ) > 0 ){
//				 formData = new LinkedMultiValueMap<>();
//		            formData.add("fileName", fileName);
//		            formData.add("chunk", buffer);
//		            formData.add("length", realSize);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	    
//
//
//        String fileName = "film.mkv";
//
//        RandomAccessFile raf = new RandomAccessFile(new File("src/test/resources/" + fileName), "rw");
//        long realSize = raf.length();
//        List<String> strings = webTestClient.head().uri("/files/get/uploaded/size?fileName=" + fileName)
//                .exchange().expectBody().returnResult().getResponseHeaders().get("upload-offset");
//
//        long uploadedSize = Long.valueOf(strings.get(0));
//
//        boolean f = false;
//
//        int sizeBuffer = 256 * 1024;
//        byte[] buffer = new byte[sizeBuffer];
//
//        MultiValueMap<String, Object> formData;
//
//        WebTestClient.ResponseSpec exchange = null;
//
//        System.out.println("first uploaded Size ; " + uploadedSize);
//
//        raf.seek(uploadedSize);
//        while (raf.read(buffer) != -1) {
//            formData = new LinkedMultiValueMap<>();
//            formData.add("fileName", fileName);
//            formData.add("chunk", buffer);
//            formData.add("length", realSize);
//
//            exchange = webTestClient.put().uri("/files/upload/resume")
//                    .contentType(MediaType.MULTIPART_FORM_DATA)
//                    .body(BodyInserters.fromMultipartData(formData))
//                    .exchange();
//            exchange.expectStatus().isOk();
//            if (exchange.expectBody().returnResult().getStatus().is5xxServerError()) {
//                return;
//            }
//
//
//            if (uploadedSize + 256 * 1024 > realSize) {
//                sizeBuffer = ((int) (realSize - uploadedSize));
//                System.out.println(sizeBuffer);
//                uploadedSize = uploadedSize + sizeBuffer;
//                System.out.println(uploadedSize);
//                buffer = new byte[sizeBuffer];
//                f=true;
//            } else uploadedSize = uploadedSize + sizeBuffer;
//
//            if (f) System.out.println(uploadedSize);
//            //System.out.println(uploadedSize);
//
//            float percent = ((float) uploadedSize / realSize * 100);
//            System.out.format("%.2f\n", percent);
//        }
//        if (exchange!=null)
//            exchange.expectStatus().isOk();
    

	}
	
	

}
