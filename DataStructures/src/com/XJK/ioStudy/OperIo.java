package com.XJK.ioStudy;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;




public class OperIo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub   
        try(InputStream f = new FileInputStream("F:\\fileForTest\\tF.txt")){
        	int temp;
        	while((temp = f.read()) != -1) {
        		System.out.println((char)temp == '\n');
        	}
            
	}
	}

}
