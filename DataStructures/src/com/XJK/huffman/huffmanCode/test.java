package com.XJK.huffman.huffmanCode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class test {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		String string = "12345678910          123";
		byte[] sbytes= string.getBytes();
		Encode encode = new Encode(); //编码
		Decode decode = new Decode();//解码
		byte[] huffmanbyte = encode.encode(sbytes);
		byte[] out = decode.decode(huffmanbyte,encode.getHuffmanMap());
		System.out.println(Arrays.toString(out));
		for (int i = 0; i < out.length; i++) {
			System.out.print((char)out[i]);
		}
		
//		HuffmancodeFileZip.fileZip("E:\\java学习资料\\java数据结构与算法资料\\资料\\压缩测试文件\\m2.png","F:\\fileForTest\\t2\\test3.zip");
//		HuffmancodeFileZip.fileUnZip("F:\\fileForTest\\t2\\test3.zip", "F:\\fileForTest\\t2\\src1.bmp");
	}

}
