package com.practice.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/files")
public class FileController {
	
@PostMapping("/")
public String handleFileUpload(final HttpServletRequest request, RedirectAttributes redirectAttributes) {
        
  boolean isMultipart = ServletFileUpload.isMultipartContent(request);
  
  if (!isMultipart) {
	  return "";
  }
  
  // Create a new file upload handler
  ServletFileUpload upload = new ServletFileUpload();
  
  FileItemIterator iter;
  InputStream fileStream = null;
  String name = null;
  // retrieve the multi-part constituent items parsed from the request
  try {
	iter = upload.getItemIterator(request);
    while (iter.hasNext()) {
        FileItemStream item = iter.next();
        name = item.getName();
        fileStream = item.openStream();
        // check if the item is a file
        if (!item.isFormField()) {                
            System.out.println("File field " + name + " with file name " + item.getName() + " detected.");                
            break; // break here so that the input stream can be processed  
        }
    }
    
  } catch (FileUploadException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
        
  if (fileStream != null) {
  }
redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + name + "!");
return "redirect:/";
}

@GetMapping("/file")
public String getfile() {
	return "File";
}
	    
@Autowired
RestTemplate restTemplate;

@RequestMapping(value = "/template/products")
public String getProductList() {
	
   HttpHeaders headers = new HttpHeaders();
   headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
   HttpEntity <String> entity = new HttpEntity<String>(headers);
   
   return restTemplate.exchange("http://localhost:8080/products", HttpMethod.GET, entity, String.class).getBody();
}

//@RequestMapping(value = "/send/file", method = RequestMethod.POST)
//public String sendChunkData(@RequestBody Product product) {
//   HttpHeaders headers = new HttpHeaders();
//   headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//   HttpEntity<Product> entity = new HttpEntity<Product>(product,headers);
//   
//   return restTemplate.exchange("http://localhost:8080/products", HttpMethod.POST, entity, String.class).getBody();
//}

}
