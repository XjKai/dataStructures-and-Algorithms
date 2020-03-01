package com.XJK.huffman.huffmanCode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 对文件进行压缩和解压
 * @author MSI
 *
 */
public class HuffmancodeFileZip {
	
	
	/**
	  *        对文件压缩
	 * @param srcFile
	 * @param outZipFile
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void fileZip(String srcFile, String outZipFile) throws FileNotFoundException, IOException {
		Encode encode = new Encode(); //编码
		byte[]  srcBytes;   //存放原文件的字节流
		//获取文件输入流
		try(InputStream srcInputStream = new FileInputStream(srcFile)){
			srcBytes = new byte[srcInputStream.available()];   //获得源文件字节流
			srcInputStream.read(srcBytes);
		}
		
		//压缩
		byte[] huffmanCodeByte = encode.encode(srcBytes);   //压缩后的字节流
		Map<Byte, String> huffmanCodeMap = encode.getHuffmanMap();  //对应的霍夫曼编码表
		
		//序列化输出java对象到文件
		try (OutputStream zipOutputStream =new FileOutputStream(outZipFile); ObjectOutputStream zipObjOutput = new ObjectOutputStream(zipOutputStream)) {
			zipObjOutput.writeObject(huffmanCodeByte);
			zipObjOutput.writeObject(huffmanCodeMap);
		}
	}
	/**
	 * j解压
	 * @param zipFile
	 * @param outUnzipFile
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void fileUnZip(String zipFile, String outUnzipFile) throws FileNotFoundException, IOException, ClassNotFoundException {
		Decode decode = new Decode();//解码
		byte[] huffmanCodeByte;      //从解压文件中获得的霍夫曼编码字节
		Map<Byte, String> huffmanCodeMap; //从解压文件中获得的霍夫曼编码表
		//获取压缩文件中的byte[]对象和Map对象
		try(InputStream zipInputStream = new FileInputStream(zipFile);ObjectInputStream zipObjectInput = new ObjectInputStream(zipInputStream)){
			huffmanCodeByte = (byte[])zipObjectInput.readObject();
			huffmanCodeMap = (Map<Byte, String>)zipObjectInput.readObject();
		}
		//解压(解码)
		byte[] unZipSrc = decode.decode(huffmanCodeByte, huffmanCodeMap);
		//将解压结果输出
		try(OutputStream unZipSrcFileOutputStream = new FileOutputStream(outUnzipFile)){
				unZipSrcFileOutputStream.write(unZipSrc);		
		}
	}
}
