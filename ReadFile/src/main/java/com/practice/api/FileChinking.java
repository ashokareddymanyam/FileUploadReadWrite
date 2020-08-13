package com.practice.api;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileChinking {

	public static void main(String[] args) {

	}

	
	public static List<File> splitFile(File file, int sizeOfFileInMB) throws IOException {
		  int counter = 1;
		  List<File> files = new ArrayList<>();

		  int sizeOfChunk = 1024 * 1024 * sizeOfFileInMB;
		  byte[] buffer = new byte[sizeOfChunk];

		  try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
		    String name = file.getName();

		    int tmp = 0;
		    while ((tmp = bis.read(buffer)) > 0) {
		        File newFile = new File(file.getParent(), name + "."
		                + String.format("%03d", counter++));
		        try (FileOutputStream out = new FileOutputStream(newFile)) {
		            out.write(buffer, 0, tmp);
		        }

		        files.add(newFile);
		    }
		  }

		  return files;
		}
}
