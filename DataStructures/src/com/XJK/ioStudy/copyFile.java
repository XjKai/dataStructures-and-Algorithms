package com.XJK.ioStudy;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//将一个文件夹复制到另外一个文件夹下
public class copyFile {
	public static void main(String[] args) throws  IOException {
		//先判断输入的是文件还是文件夹
		File fI = new File(args[0]);
		File fO = new File(args[1]);
		if(fI.isFile() && fO.isFile()) {
			//实例化输入文件和输出文件
			try(InputStream fIn = new FileInputStream(args[0]); OutputStream fOut = new FileOutputStream(args[1])){
				int temp;
				while((temp = fIn.read()) != -1) {
					fOut.write(temp);
					fOut.flush();
					System.out.println((char)temp);
				}
				System.out.println("复制成功");
			}
		}
		
		
		int m =0 ,n =0;
		m =n;
		
	}
}
