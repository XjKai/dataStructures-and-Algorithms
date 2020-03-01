package com.XJK.huffman.huffmanCode;
/**
 * ����
 * @author MSI
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Decode {
	/**
	 * ��byte����ת��Ϊ��Ӧ��
	 * @param bytes
	 * @return
	 */
	private String bytes2String(byte[] bytes) {
		StringBuilder stringBuilder = new StringBuilder();
		String tempString = "";
		for (int i = 0; i < bytes.length - 1; i++) {
			if (i < bytes.length - 2) {     //�����ڶ���byte֮ǰ����ȡ8bit
				tempString = byte2bitString(bytes[i], 8);
			} else {    //�����ڶ���byte�Ľ�ȡλ���ɵ�����һ��byte����
				tempString = byte2bitString(bytes[i],(int)bytes[i+1]);
			}
			stringBuilder.append(tempString);
		}
		return stringBuilder.toString();
	}
	/**
	  * ��byteת����bit(String��ʾ)
	 * @param b
	 * @param bitNums    ��Ϊbyteתbit������Ϊ��byte->int->bit,���Դ�int->bitʱ��Ҫ��ȡ�����˵����ڶ���byte(���һ��byteΪЭ��)
	  * ǰ����ֽڶ���Ҫ��ȡ��8bit(bitNums = 8),�������ڶ����ֽڵĽ�ȡ�����ǲ�һ���� ��Ҫ�������һ��Э���ֽڵ�ֵ����
	 *   
	 * @return
	 */
	private String byte2bitString(byte b, int bitNums) {
		int tempInt = b;
		tempInt = tempInt|256;
		String bitString = Integer.toBinaryString(tempInt);    //����õ�����tempInt�����ƵĲ���
		return bitString.substring(bitString.length() - bitNums);
	}
	/**
	 * 
	 * @param huffmanCodeBytesStream
	 * @param huffmanMap
	 * @return
	 */
	public byte[] decode(byte[] huffmanCodeBytesStream,Map<Byte, String> huffmanMap) {
		String sourceBit = bytes2String(huffmanCodeBytesStream);
		Map<String, Byte> reMap =new HashMap<String, Byte>();    //���ڴ�bit�ҵ���Ӧ��byte
		for (Map.Entry<Byte, String> mEntry : huffmanMap.entrySet()) {
			reMap.put(mEntry.getValue(), mEntry.getKey());
		}
		List<Byte> sourceByteList = new ArrayList<Byte>();
		//i��ǰָ�룬j�Ǻ�ָ��.һֱ�ж�i~j֮����ַ����Ƿ���map��
		 //��ͷ��ʼ�������Ƿ������reMap��
		int head = 0;
		Byte temp ;
		for (int j = 0; j <= sourceBit.length(); j++) {
			if ((temp = reMap.get(sourceBit.substring(head,j))) != null) {
				sourceByteList.add(temp);
				head=j;
			}
		}
		//��list׼��Ϊbyte����
		byte[] bytes = new byte[sourceByteList.size()];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = sourceByteList.get(i);
		}
		return bytes;
	}
}
