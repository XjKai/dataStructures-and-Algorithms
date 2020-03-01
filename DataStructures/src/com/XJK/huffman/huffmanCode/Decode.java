package com.XJK.huffman.huffmanCode;
/**
 * 解码
 * @author MSI
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Decode {
	/**
	 * 将byte数组转换为对应的
	 * @param bytes
	 * @return
	 */
	private String bytes2String(byte[] bytes) {
		StringBuilder stringBuilder = new StringBuilder();
		String tempString = "";
		for (int i = 0; i < bytes.length - 1; i++) {
			if (i < bytes.length - 2) {     //倒数第二个byte之前都截取8bit
				tempString = byte2bitString(bytes[i], 8);
			} else {    //倒数第二个byte的截取位数由倒数第一个byte决定
				tempString = byte2bitString(bytes[i],(int)bytes[i+1]);
			}
			stringBuilder.append(tempString);
		}
		return stringBuilder.toString();
	}
	/**
	  * 将byte转换成bit(String表示)
	 * @param b
	 * @param bitNums    因为byte转bit的流程为：byte->int->bit,所以从int->bit时需要截取，除了倒数第二个byte(最后一个byte为协议)
	  * 前面的字节都需要截取后8bit(bitNums = 8),但倒数第二个字节的截取长度是不一定的 需要根据最后一个协议字节的值决定
	 *   
	 * @return
	 */
	private String byte2bitString(byte b, int bitNums) {
		int tempInt = b;
		tempInt = tempInt|256;
		String bitString = Integer.toBinaryString(tempInt);    //这里得到的是tempInt二进制的补码
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
		Map<String, Byte> reMap =new HashMap<String, Byte>();    //用于从bit找到对应的byte
		for (Map.Entry<Byte, String> mEntry : huffmanMap.entrySet()) {
			reMap.put(mEntry.getValue(), mEntry.getKey());
		}
		List<Byte> sourceByteList = new ArrayList<Byte>();
		//i是前指针，j是后指针.一直判断i~j之间的字符串是否在map中
		 //从头开始遍历，是否存在于reMap中
		int head = 0;
		Byte temp ;
		for (int j = 0; j <= sourceBit.length(); j++) {
			if ((temp = reMap.get(sourceBit.substring(head,j))) != null) {
				sourceByteList.add(temp);
				head=j;
			}
		}
		//将list准换为byte数组
		byte[] bytes = new byte[sourceByteList.size()];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = sourceByteList.get(i);
		}
		return bytes;
	}
}
