package com.practice.api;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileAssambler {

	List<byte[]> bytesList;
	private String name;
	boolean finished=false;
	
	public FileAssambler(String name){
	    bytesList = new ArrayList<byte[]>();
	    this.name = name;
	}

	public void addBytes(byte[] bytes){
	    if(bytes.length<512)
	        finished=true;
	    bytesList.add(bytes);
	}

	public boolean isFinished(){
	    return finished;
	}

	public void createFile() throws IOException {
	    FileOutputStream fos = new FileOutputStream(name);
	    for (byte[] data: bytesList)
	        fos.write(data);
	}

}
