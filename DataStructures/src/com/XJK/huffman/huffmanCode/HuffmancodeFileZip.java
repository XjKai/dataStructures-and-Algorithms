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
 * ���ļ�����ѹ���ͽ�ѹ
 * @author MSI
 *
 */
public class HuffmancodeFileZip {
	
	
	/**
	  *        ���ļ�ѹ��
	 * @param srcFile
	 * @param outZipFile
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void fileZip(String srcFile, String outZipFile) throws FileNotFoundException, IOException {
		Encode encode = new Encode(); //����
		byte[]  srcBytes;   //���ԭ�ļ����ֽ���
		//��ȡ�ļ�������
		try(InputStream srcInputStream = new FileInputStream(srcFile)){
			srcBytes = new byte[srcInputStream.available()];   //���Դ�ļ��ֽ���
			srcInputStream.read(srcBytes);
		}
		
		//ѹ��
		byte[] huffmanCodeByte = encode.encode(srcBytes);   //ѹ������ֽ���
		Map<Byte, String> huffmanCodeMap = encode.getHuffmanMap();  //��Ӧ�Ļ����������
		
		//���л����java�����ļ�
		try (OutputStream zipOutputStream =new FileOutputStream(outZipFile); ObjectOutputStream zipObjOutput = new ObjectOutputStream(zipOutputStream)) {
			zipObjOutput.writeObject(huffmanCodeByte);
			zipObjOutput.writeObject(huffmanCodeMap);
		}
	}
	/**
	 * j��ѹ
	 * @param zipFile
	 * @param outUnzipFile
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void fileUnZip(String zipFile, String outUnzipFile) throws FileNotFoundException, IOException, ClassNotFoundException {
		Decode decode = new Decode();//����
		byte[] huffmanCodeByte;      //�ӽ�ѹ�ļ��л�õĻ����������ֽ�
		Map<Byte, String> huffmanCodeMap; //�ӽ�ѹ�ļ��л�õĻ����������
		//��ȡѹ���ļ��е�byte[]�����Map����
		try(InputStream zipInputStream = new FileInputStream(zipFile);ObjectInputStream zipObjectInput = new ObjectInputStream(zipInputStream)){
			huffmanCodeByte = (byte[])zipObjectInput.readObject();
			huffmanCodeMap = (Map<Byte, String>)zipObjectInput.readObject();
		}
		//��ѹ(����)
		byte[] unZipSrc = decode.decode(huffmanCodeByte, huffmanCodeMap);
		//����ѹ������
		try(OutputStream unZipSrcFileOutputStream = new FileOutputStream(outUnzipFile)){
				unZipSrcFileOutputStream.write(unZipSrc);		
		}
	}
}
