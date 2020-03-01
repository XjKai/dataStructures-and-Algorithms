package com.XJK.huffman.huffmanCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * ����
 * @author MSI
 *
 */
public class Encode {
	
	//����������Ķ��ձ�
	private Map<Byte, String> huffmanMap = new HashMap<Byte, String>();
	//ԭʼ�ֽ���
	private byte[] sourceBytesStream;  
	
	public Map<Byte, String> getHuffmanMap() {
		return huffmanMap;
	}

	public void setHuffmanMap(Map<Byte, String> huffmanMap) {
		this.huffmanMap = huffmanMap;
	}

	/**
	 * ����byte�����Ӧ��Ȩֵ����б�
	 * @param bytes
	 * @return
	 */
	private List<Node> createNodeList() {
		Map<Byte, Integer> map = new HashMap<Byte, Integer>();
		
		for (byte b : sourceBytesStream) {
			Integer count = map.get(b);   
			if (count == null) {  //��ǰ�ַ�ȨֵΪnull����δ����map
				map.put(b, 1);   //��һ�μ��룬ȨֵΪ1
			} else {
				map.put(b, count+1);  //Ȩֵ+1
			}
		}
		//��map�е�ֵת��ΪNode���͵��б�
		List<Node> list = new ArrayList<Node>();
		for (Map.Entry<Byte, Integer> entry : map.entrySet()) {    //ʹ��entry����map   entry:��Ŀ
			list.add(new Node(entry.getKey(), entry.getValue()));
		}
		return list;
	}
	/**
	 * ���б����ɻ�������
	 * @param list
	 * @return  ���ػ���������root���
	 */
	private  Node createHuffmanTree(List<Node> nodesList) {
		while (nodesList.size() > 1) {
			Collections.sort(nodesList);   //������
			Node firstNode = nodesList.get(0);
			Node secondNode = nodesList.get(1);	
			//�Ƴ�
			nodesList.remove(firstNode);
			nodesList.remove(secondNode);
			//��������
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
	 * �õ�����������
	 * @param root
	 */
	private void createHuffmanMap(Node root) {
		if (root == null) {
			return;
		}
		//��ֻ��һ��Ҷ�ӽ��
		if (root.getData() != null) {   
			createHuffmanMap(root,"1");
		} else {
			createHuffmanMap(root,"");
		}
	}
	private void createHuffmanMap(Node node, String code) {
		//��Ҷ�ӽ��
		if (node.getData() == null) {  
			createHuffmanMap(node.getLeftNode(), code+"0");
			createHuffmanMap(node.getRightNode(), code+"1");		
		}
		//Ҷ�ӽ��
		if (node.getData() != null) {
			huffmanMap.put(node.getData(), code);
		}
		
	}
	/**
	 * ���ݻ�������������map���ձ�ԭʼ�ֽ���ת��Ϊ�������ֽ���
	 * tip:���һ���ֽڵ�intֵ������ǵ����ڶ����ֽڵ���Чbit��
	 * @return huffmanCodeBytesStream
	 */
	private byte[] createHuffmanCodeBytesStream() {
		StringBuilder stringBuilder = new StringBuilder();
		for (byte b : sourceBytesStream) {
			stringBuilder.append(huffmanMap.get(b));
		}
		//��֤8��bitһ�飬��װ����
		int len = ((stringBuilder.length() + 7)/8) + 1; //���һ���ֽڣ�������8��bit����Чλ��
		byte[] huffmanCodeBytesStream = new byte[len];
		int count = 0;
		String string = "";
		for (int i = 0; i < stringBuilder.length(); i+=8) {
			if (i + 8 < stringBuilder.length()) {     //��û�е����һ��
				string = stringBuilder.substring(i,i+8);
			} else {
				string = stringBuilder.substring(i);
			}
			huffmanCodeBytesStream[count] = (byte)Integer.parseInt(string, 2);  //��Ӧ����string����������ַ����Ĳ���
			count++;
		}
		//���һ���ֽڴ�����8��bit����Чλ��(���һλ�Ĵ�С��1~7)
		huffmanCodeBytesStream[len - 1] = (byte) (stringBuilder.length()%8);
		return huffmanCodeBytesStream;
	}
	/**
	 * ����������
	 * @param sourceBytesStream   ԭʼ�ֽ���
	 * @return                    �������ֽ���
	 */
	public byte[] encode(byte[] sourceBytesStream) {
		this.sourceBytesStream = sourceBytesStream;
		//���ÿ�����ظ��ֽڶ�Ӧ��Node�б�
		List<Node> huffmanNodes = createNodeList();
		//��û�������
		Node huffmanTreeNode = createHuffmanTree(huffmanNodes);
		//��û����������
		createHuffmanMap(huffmanTreeNode);
		//���ݻ����������õ�ԭʼ�ֽ����������ֽ���
		byte[] huffmanCodeBytesStream = createHuffmanCodeBytesStream();
		return huffmanCodeBytesStream;
		
	}
}


class Node implements Comparable<Node> {
	private Byte data;     //�ַ�
	private Integer weight;    //Ȩֵ(����)
	private Node leftNode;  //����
	private Node rightNode;  //�ҽ��


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
	//ǰ�����
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