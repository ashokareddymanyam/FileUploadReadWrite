package com.practice.api;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class FileUploadController {

	@Autowired
	RestTemplate restTemplate;

	@PostMapping("/upload")
	public String handleUpload(HttpServletRequest request) throws Exception {

		File f = new File("d:/text.txt");

		int sizeOfFiles = 1024 * 1024;// 1MB
		byte[] buffer = new byte[sizeOfFiles];

		String fileName = f.getName();
		// try-with-resources to ensure closing stream
		try (FileInputStream fis = new FileInputStream(f); BufferedInputStream bis = new BufferedInputStream(fis)) {

			int partCounter = 1;
			int bytesAmount = 0;
			while ((bytesAmount = bis.read(buffer)) > 0) {

				System.out.println("buffer:buffer:buffer:buffer:buffer:::::"+buffer);
				System.out.println("number of times called");
				MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
				//formData.add("fileName", fileName);
				

				//sendChunkData(formData);
				// formData.add("length", realSize);
				// write each chunk of data into separate file with different number in name
                String filePartName = String.format("%s.%03d", fileName+"_11", partCounter++);
                File newFile = new File(f.getParent(), filePartName);
//                try (FileOutputStream out = new FileOutputStream(newFile)) {
//                	System.out.println("Numverssss");
//                    out.write(buffer);
//                }
                String byteArrayStr= new String(Base64.encodeBase64(buffer));

                FileOutputStream fos = new FileOutputStream(newFile);
                byte[] bytes = byteArrayStr.getBytes();
                formData.add("chunk", bytes);
                sendChunkData(formData,bytes);
                fos.write(Base64.decodeBase64(bytes));
                fos.close();
			}
		}

		return "success!";
	}

	public void sendChunkData(MultiValueMap<String, Object> formData, byte[] fileContents) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		ByteArrayResource contentsAsResource = new ByteArrayResource(fileContents) {
	        @Override
	        public String getFilename() {
	            return "abc.txt"; // Filename has to be returned in order to be able to post.
	        }
	    };

	    formData.add("file", contentsAsResource);
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(formData, headers);
//
//    	ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
//	   HttpHeaders headers = new HttpHeaders();
//	   headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//	   HttpEntity entity = new HttpEntity(formData,headers);

		String body = restTemplate.exchange("http://localhost:8082/writechunk", HttpMethod.POST, entity, String.class)
				.getBody();

		System.out.println(body);
	}

	public void uplode(HttpServletRequest request) throws FileUploadException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		factory.setSizeThreshold(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD);
		factory.setFileCleaningTracker(null);
		ServletFileUpload upload = new ServletFileUpload(factory);

		List items = upload.parseRequest(request);

		Iterator iter = items.iterator();
		while (iter.hasNext()) {
//	        FileItem item = iter.next();
//	 
//	        if (!item.isFormField()) {
//	            try (
//	              InputStream uploadedStream = item.getInputStream();
//	              OutputStream out = new FileOutputStream("file.mov");) {
//	 
//	                IOUtils.copy(uploadedStream, out);
//	            }
//	        }
		}
	}
}
