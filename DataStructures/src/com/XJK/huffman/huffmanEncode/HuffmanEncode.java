package com.XJK.huffman.huffmanEncode;
/**
 * ���Ұ汾
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class HuffmanEncode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String string = "i like like like java do you like a java jjjj";
		byte[] bytes= string.getBytes();
		System.out.println("ԭ��"+ Arrays.toString(bytes) + "����"+bytes.length);
		huffmanZip(bytes);
		//System.out.println(Arrays.toString(huffmanZip(bytes)));
		
	
	}
	
	/**
	 *   ���ַ���ת��Ϊ��Ӧ��Node�б�
	 * @param string
	 * @return
	 */
	public static List<Node> createNodeList(byte[] bytes) {
		Map<Byte, Integer> map = new HashMap<Byte, Integer>();
		for (byte b : bytes) {
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
	public static Node createHuffmanTree(List<Node> list) {
		while (list.size() > 1) {
			Collections.sort(list);   //������
			Node firstNode = list.get(0);
			Node secondNode = list.get(1);	
			//�Ƴ�
			list.remove(firstNode);
			list.remove(secondNode);
			//��������
			Node tempNode = new Node(null, firstNode.getWeight() + secondNode.getWeight());
			tempNode.setLeftNode(firstNode);
			tempNode.setRightNode(secondNode);
			list.add(tempNode);
		}
		return list.get(0);
	}
	/**
	 * ���ط���
	 * @param root
	 * @return
	 */
	public static Map<Byte, String> createHuffmanCode(Node root) {
		Map< Byte, String> map = new HashMap<Byte, String>();
		if (root.getData() != null) {
			createHuffmanCode(root,map, "1");
			return map;
		}
		createHuffmanCode(root,map, "");
		return map;
	}
	/**
	 * ���ɻ����������Ӧ��Map
	 * @param huffmanTree      //�ɴ���������ݵõ��Ļ�������
	 * @param map              //��Ż����������map,�ڱ����Ĺ�����ֻ��һ��map
	 * @param code             //������򣬼���ǰ�������һ���������㻹���ҽ��(��:0 , ��:1)���������ĵ����� ���ֵֻ������һ��������
	 * @param stringBuilder    //������ϱ��룬ÿ�����ӵ��һ�������Լ���stringBuilder
	 */
	public static void createHuffmanCode(Node node,Map<Byte, String> map,String code) {
		//ÿһ����㶼���Լ���һ��StringBuilder
		//StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);   //��ԭ��stringBuilder��������ݸ��Ƶ�stringBuilder2��ȥ������stringBuilder2�ĳ�������չ16
		//node.setCodeString(code);
		if (node.getData() == null) {   //��Ҷ�ӽ��
			createHuffmanCode(node.getLeftNode(), map, code + "0");   //��ݹ�
			createHuffmanCode(node.getRightNode(), map, code + "1");   //�ҵݹ�
		}
		if (node.getData() != null) {  //��ʾ�ҵ���Ҷ�ӽ��
			//��Ҷ�ӽ���Data�ͱ������map��
			map.put(node.getData(), code);
		}
	}
	/**
	 *        ����һ��byte���飬���û��������뽫��ѹ��
	 * @param bytes    ԭʼ���ַ�����Ӧ���ֽ�����
	 * @return     ѹ������ֽ�����  (ѹ�����ֽ���������Ϊ�������ڼ�����������ֽ�(byte)����ʽ�����)
	 */
	public static byte[] huffmanZip(byte[] bytes) {
		List<Node> nodes = createNodeList(bytes);          //��ȡ��Ӧ���б��������ɻ�������
		Node huffmanRootNode = createHuffmanTree(nodes);   //��û����������������ɴ洢�����������map
		Map<Byte, String> huffmanCodesMap = createHuffmanCode(huffmanRootNode);    //��ô洢�����������map
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
        	stringBuilder.append(huffmanCodesMap.get(b));   //������������Ľ���ϳ�һ�����ַ���
		}
        System.out.println("����"+stringBuilder.toString());
        System.out.println(huffmanCodesMap);
        int len = 0;
        len = (stringBuilder.length() + 7)/8;

        int index = 0;
        byte[] huffmanCodesBytes = new byte[len];   //��Ż�������������byte��ʽ
        for (int i = 0; i < stringBuilder.length(); i+=8) {
        	String string = "";
        	if (i + 8 < stringBuilder.length()) {
        		string = stringBuilder.substring(i, i+8);
			} else {
				string = stringBuilder.substring(i);
			}
        	huffmanCodesBytes[index] = (byte)Integer.parseInt(string, 2);//����2����ת��������string���������ƣ�
        	index++;
		}
        deode(huffmanCodesBytes,huffmanCodesMap);
        return huffmanCodesBytes;
	}
	/**
	 *        ��byte���͵�bת��Ϊ��Ӧ�Ķ����Ʋ���
	 * @param b         byte����
	 * @param addFlag   �Ƿ���Ҫ���������в�0
	 * @return
	 */
	public static String bytetoString(byte b,boolean addFlag) {
		int temp = b; 
		StringBuilder byteStringBuilder = new StringBuilder();//int������16��bit
		//int������16��bit,�������������Integer.toBinaryString(temp)��Ĩȥ��ǰ��0��һ������ƣ�����Ǹ������������Ϊ��1�����Բ���Ĩȥ
		//������Ҫ��ȡ
		//������Ҫ��0(�������byte���������һ��byte����������ô������ֵ�Ƿ���Ҫ���뻹Ҫ���ݱ������ֽ������Ƿ���8��������������)
		if (temp >= 0 ) {
			if (addFlag) {
				temp |= 256;   //1 0000 0000| 101 = 1 0000 0101    ���ڵھŸ�bit������1�������8λ����ԭ���ݻ᲻��ĻᲹ��
				String s = Integer.toBinaryString(temp);  //Integer.toBinaryString�ǽ�tempת��Ϊ��Ӧ�Ĳ���
				byteStringBuilder.append(s.substring(s.length() - 8)); 	
			} else {
				String s = Integer.toBinaryString(temp);   //������
				byteStringBuilder.append(s); 	
			}
		} else { //������Ҫ��ȡ
			String s = Integer.toBinaryString(temp);
			//byteStringBuilder.append("1");
			byteStringBuilder.append(s.substring(s.length() - 8));  //int��������ĵڰ�λ�ض�Ϊ1
		}
		return byteStringBuilder.toString();
		
	}
	/**
	  *  ����
	 * @param huffmanCodesBytes
	 * @param huffmanCodesMap
	 * @return
	 */
	public static byte[] deode(byte[] huffmanCodesBytes, Map<Byte, String> huffmanCodesMap) {
		System.out.println(Arrays.toString(huffmanCodesBytes));
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < huffmanCodesBytes.length; i++) {
			if (i == huffmanCodesBytes.length - 1) {    //����Ҫ����ʵ������ж�
				stringBuilder.append(bytetoString(huffmanCodesBytes[i], false));
			} else {
				stringBuilder.append(bytetoString(huffmanCodesBytes[i], true));
			}
		}
		Map<String, Byte> reMap = new HashMap<String, Byte>();
		for (Map.Entry<Byte, String> mEntry : huffmanCodesMap.entrySet()) {
			reMap.put(mEntry.getValue(), mEntry.getKey());
		}
		System.out.println("����"+stringBuilder.toString());
		System.out.println("����"+reMap);
		
		List<Byte> list = new ArrayList<Byte>();
		//i��ǰָ�룬j�Ǻ�ָ��.һֱ�ж�i~j֮����ַ����Ƿ���map��
		 //��ͷ��ʼ�������Ƿ������reMap��
		int head = 0;
		
		for (int j = 0; j <= stringBuilder.length(); j++) {
		
			Byte temp ;
			if ((temp = reMap.get(stringBuilder.substring(head,j))) != null) {
				list.add(temp);
				head=j;
			}
		}

		System.out.println();
		byte[] bytes = new byte[list.size()];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = list.get(i);
			
			System.out.print((char)bytes[i]);
		}
		System.out.println();
		System.out.println("����"+Arrays.toString(bytes));
		return bytes;
	}
	/**
	 * ǰ�����
	 */
	public static void PreOrder(Node root) {
		if (root != null) {
			root.preOrder();
		} else {
			System.out.println("��ǰ��Ϊ��");
		}
	}
	
}

class Node implements Comparable<Node> {
	private Byte data;     //�ַ�
	private int weight;    //Ȩֵ(����)
	private Node leftNode;  //����
	private Node rightNode;  //�ҽ��


	public Node(Byte data, int weight) {
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
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
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
