package com.XJK.ioStudy;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//��һ���ļ��и��Ƶ�����һ���ļ�����
public class copyFile {
	public static void main(String[] args) throws  IOException {
		//���ж���������ļ������ļ���
		File fI = new File(args[0]);
		File fO = new File(args[1]);
		if(fI.isFile() && fO.isFile()) {
			//ʵ���������ļ�������ļ�
			try(InputStream fIn = new FileInputStream(args[0]); OutputStream fOut = new FileOutputStream(args[1])){
				int temp;
				while((temp = fIn.read()) != -1) {
					fOut.write(temp);
					fOut.flush();
					System.out.println((char)temp);
				}
				System.out.println("���Ƴɹ�");
			}
		}
		
		
		int m =0 ,n =0;
		m =n;
		
	}
}
