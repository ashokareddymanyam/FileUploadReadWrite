package com.practice.api;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

// Convert a byte array to String in Java
class Main
{
	public static void main(String[] args) throws IOException
	{
		byte[] bytes = "Techie Delight sfsjkfs dfjk jksd fsfj skldfj sdjfk ksdfj skldfj sldfjk lsdfj sdfj skldjf sldfjk sldkfj sdfj sdklfj dfj ireteroituer iogdogjkd flkgj fdjg".getBytes(StandardCharsets.UTF_8);
		
		System.out.println(bytes);
		
		try(FileOutputStream fos = new FileOutputStream("d:/helo.txt")) {
		fos.write(bytes);
		}
		// Create a string from the byte array with "UTF-8" encoding
		String string = new String(bytes, StandardCharsets.UTF_8);
		System.out.println(string);
	}
}
