package com.XJK.huffman.huffmanCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 编码
 * @author MSI
 *
 */
public class Encode {
	
	//霍夫曼编码的对照表
	private Map<Byte, String> huffmanMap = new HashMap<Byte, String>();
	//原始字节码
	private byte[] sourceBytesStream;  
	
	public Map<Byte, String> getHuffmanMap() {
		return huffmanMap;
	}

	public void setHuffmanMap(Map<Byte, String> huffmanMap) {
		this.huffmanMap = huffmanMap;
	}

	/**
	 * 创建byte数组对应的权值结点列表
	 * @param bytes
	 * @return
	 */
	private List<Node> createNodeList() {
		Map<Byte, Integer> map = new HashMap<Byte, Integer>();
		
		for (byte b : sourceBytesStream) {
			Integer count = map.get(b);   
			if (count == null) {  //当前字符权值为null，则还未加入map
				map.put(b, 1);   //第一次加入，权值为1
			} else {
				map.put(b, count+1);  //权值+1
			}
		}
		//将map中的值转换为Node类型的列表
		List<Node> list = new ArrayList<Node>();
		for (Map.Entry<Byte, Integer> entry : map.entrySet()) {    //使用entry遍历map   entry:条目
			list.add(new Node(entry.getKey(), entry.getValue()));
		}
		return list;
	}
	/**
	 * 将列表构建成霍夫曼树
	 * @param list
	 * @return  返回霍夫曼树的root结点
	 */
	private  Node createHuffmanTree(List<Node> nodesList) {
		while (nodesList.size() > 1) {
			Collections.sort(nodesList);   //先排序
			Node firstNode = nodesList.get(0);
			Node secondNode = nodesList.get(1);	
			//移除
			nodesList.remove(firstNode);
			nodesList.remove(secondNode);
			//构建新树
			Node tempNode = new Node(null, firstNode.getWeight() + secondNode.getWeight());
			tempNode.setLeftNode(firstNode);
			tempNode.setRightNode(secondNode);
			nodesList.add(tempNode);
		}
		if (nodesList.size() > 0) {
			return nodesList.get(0);
		}
		return null;
	}
	/**
	 * 得到霍夫曼编码
	 * @param root
	 */
	private void createHuffmanMap(Node root) {
		if (root == null) {
			return;
		}
		//树只有一个叶子结点
		if (root.getData() != null) {   
			createHuffmanMap(root,"1");
		} else {
			createHuffmanMap(root,"");
		}
	}
	private void createHuffmanMap(Node node, String code) {
		//非叶子结点
		if (node.getData() == null) {  
			createHuffmanMap(node.getLeftNode(), code+"0");
			createHuffmanMap(node.getRightNode(), code+"1");		
		}
		//叶子结点
		if (node.getData() != null) {
			huffmanMap.put(node.getData(), code);
		}
		
	}
	/**
	 * 根据霍夫曼编码结果的map对照表将原始字节流转换为编码后的字节流
	 * tip:最后一个字节的int值代表的是倒数第二个字节的有效bit数
	 * @return huffmanCodeBytesStream
	 */
	private byte[] createHuffmanCodeBytesStream() {
		StringBuilder stringBuilder = new StringBuilder();
		for (byte b : sourceBytesStream) {
			stringBuilder.append(huffmanMap.get(b));
		}
		//保证8个bit一组，能装的下
		int len = ((stringBuilder.length() + 7)/8) + 1; //多加一个字节，存放最后8个bit的有效位数
		byte[] huffmanCodeBytesStream = new byte[len];
		int count = 0;
		String string = "";
		for (int i = 0; i < stringBuilder.length(); i+=8) {
			if (i + 8 < stringBuilder.length()) {     //还没有到最后一组
				string = stringBuilder.substring(i,i+8);
			} else {
				string = stringBuilder.substring(i);
			}
			huffmanCodeBytesStream[count] = (byte)Integer.parseInt(string, 2);  //对应的是string这个二进制字符串的补码
			count++;
		}
		//最后一个字节存放最后8个bit的有效位数(最后一位的大小在1~7)
		huffmanCodeBytesStream[len - 1] = (byte) (stringBuilder.length()%8);
		return huffmanCodeBytesStream;
	}
	/**
	 * 霍夫曼编码
	 * @param sourceBytesStream   原始字节流
	 * @return                    编码后的字节流
	 */
	public byte[] encode(byte[] sourceBytesStream) {
		this.sourceBytesStream = sourceBytesStream;
		//获得每个不重复字节对应的Node列表
		List<Node> huffmanNodes = createNodeList();
		//获得霍夫曼树
		Node huffmanTreeNode = createHuffmanTree(huffmanNodes);
		//获得霍夫曼编码表
		createHuffmanMap(huffmanTreeNode);
		//根据霍夫曼编码表得到原始字节流编码后的字节流
		byte[] huffmanCodeBytesStream = createHuffmanCodeBytesStream();
		return huffmanCodeBytesStream;
		
	}
}


class Node implements Comparable<Node> {
	private Byte data;     //字符
	private Integer weight;    //权值(次数)
	private Node leftNode;  //左结点
	private Node rightNode;  //右结点


	public Node(Byte data, Integer weight) {
		super();
		this.data = data;
		this.weight = weight;
	}
	public Byte getData() {
		return data;
	}
	public void setData(Byte data) {
		this.data = data;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Node getLeftNode() {
		return leftNode;
	}
	public void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}
	public Node getRightNode() {
		return rightNode;
	}
	public void setRightNode(Node rightNode) {
		this.rightNode = rightNode;
	}
	
	@Override
	public String toString() {
		return "Node [data=" + data + ", weight=" + weight + "]";
	}
	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return this.weight - o.weight;
	}
	//前序遍历
	public void preOrder() {
		System.out.println(this);
		if (this.leftNode != null) {
			this.leftNode.preOrder();
		}
		if (this.rightNode != null) {
			this.rightNode.preOrder();
		}
	}
}