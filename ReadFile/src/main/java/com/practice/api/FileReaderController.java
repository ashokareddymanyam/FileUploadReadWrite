package com.practice.api;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileReaderController {
	
	@RequestMapping(value = "/file/{dummyparam}.pdf", method = RequestMethod.GET, produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void getFile(@PathVariable("dummyparam") String dummyparam, HttpServletResponse response) {
//
//	   // InputStream is = new FileInputStream(resultFile);
//
//	    response.setHeader("Content-Disposition", "attachment; filename=\"dummyname " + dummyparam + ".pdf\"");
//
//	    int read=0;
//	    byte[] bytes = new byte[BYTES_DOWNLOAD];
//	    OutputStream os = response.getOutputStream();
//
//	    while((read = is.read(bytes))!= -1){
//	        os.write(bytes, 0, read);
//	    }
//	    os.flush();
//	    os.close(); 
//	    
//	    try(BufferedInputStream in = new BufferedInputStream(new FileInputStream("d:/text.txt"))) {
//	        byte[] bbuf = new byte[4096];
//	        int len;
//	        while ((len = in.read(bbuf)) != -1) {
//	            // process data here: bbuf[0] thru bbuf[len - 1]
//	        }
//	    }
	}

}
