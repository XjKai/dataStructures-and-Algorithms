package com.XJK.huffman.huffmanEncode;
/**
 * 杂乱版本
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
		System.out.println("原码"+ Arrays.toString(bytes) + "长度"+bytes.length);
		huffmanZip(bytes);
		//System.out.println(Arrays.toString(huffmanZip(bytes)));
		
	
	}
	
	/**
	 *   将字符串转换为对应的Node列表
	 * @param string
	 * @return
	 */
	public static List<Node> createNodeList(byte[] bytes) {
		Map<Byte, Integer> map = new HashMap<Byte, Integer>();
		for (byte b : bytes) {
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
	public static Node createHuffmanTree(List<Node> list) {
		while (list.size() > 1) {
			Collections.sort(list);   //先排序
			Node firstNode = list.get(0);
			Node secondNode = list.get(1);	
			//移除
			list.remove(firstNode);
			list.remove(secondNode);
			//构建新树
			Node tempNode = new Node(null, firstNode.getWeight() + secondNode.getWeight());
			tempNode.setLeftNode(firstNode);
			tempNode.setRightNode(secondNode);
			list.add(tempNode);
		}
		return list.get(0);
	}
	/**
	 * 重载方法
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
	 * 生成霍夫曼编码对应的Map
	 * @param huffmanTree      //由待编码的数据得到的霍夫曼树
	 * @param map              //存放霍夫曼编码的map,在遍历的过程中只有一个map
	 * @param code             //编码规则，即当前结点是上一个结点的左结点还是右结点(左:0 , 右:1)，由于树的单向性 这个值只能由上一个结点给出
	 * @param stringBuilder    //用于组合编码，每个结点拥有一个属于自己的stringBuilder
	 */
	public static void createHuffmanCode(Node node,Map<Byte, String> map,String code) {
		//每一个结点都有自己的一个StringBuilder
		//StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);   //把原来stringBuilder里面的内容复制到stringBuilder2里去，并把stringBuilder2的长度再扩展16
		//node.setCodeString(code);
		if (node.getData() == null) {   //非叶子结点
			createHuffmanCode(node.getLeftNode(), map, code + "0");   //左递归
			createHuffmanCode(node.getRightNode(), map, code + "1");   //右递归
		}
		if (node.getData() != null) {  //表示找到了叶子结点
			//将叶子结点的Data和编码放入map中
			map.put(node.getData(), code);
		}
	}
	/**
	 *        输入一个byte数组，并用霍夫曼编码将其压缩
	 * @param bytes    原始的字符串对应的字节数组
	 * @return     压缩后的字节数组  (压缩成字节数组是因为，数据在计算机中是以字节(byte)的形式传输的)
	 */
	public static byte[] huffmanZip(byte[] bytes) {
		List<Node> nodes = createNodeList(bytes);          //获取对应的列表，用于生成霍夫曼树
		Node huffmanRootNode = createHuffmanTree(nodes);   //获得霍夫曼树，用于生成存储霍夫曼编码的map
		Map<Byte, String> huffmanCodesMap = createHuffmanCode(huffmanRootNode);    //获得存储霍夫曼编码的map
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
        	stringBuilder.append(huffmanCodesMap.get(b));   //将霍夫曼编码的结果合成一个长字符串
		}
        System.out.println("编码"+stringBuilder.toString());
        System.out.println(huffmanCodesMap);
        int len = 0;
        len = (stringBuilder.length() + 7)/8;

        int index = 0;
        byte[] huffmanCodesBytes = new byte[len];   //存放霍夫曼编码结果的byte形式
        for (int i = 0; i < stringBuilder.length(); i+=8) {
        	String string = "";
        	if (i + 8 < stringBuilder.length()) {
        		string = stringBuilder.substring(i, i+8);
			} else {
				string = stringBuilder.substring(i);
			}
        	huffmanCodesBytes[index] = (byte)Integer.parseInt(string, 2);//按照2进制转换（即把string看作二进制）
        	index++;
		}
        deode(huffmanCodesBytes,huffmanCodesMap);
        return huffmanCodesBytes;
	}
	/**
	 *        将byte类型的b转换为对应的二进制补码
	 * @param b         byte数据
	 * @param addFlag   是否需要对正数进行补0
	 * @return
	 */
	public static String bytetoString(byte b,boolean addFlag) {
		int temp = b; 
		StringBuilder byteStringBuilder = new StringBuilder();//int类型是16个bit
		//int类型是16个bit,如果是正数，则Integer.toBinaryString(temp)是抹去了前面0的一组二进制，如果是负数，由于最高为是1，所以不会抹去
		//负数需要截取
		//正数需要补0(若编码的byte数组中最后一个byte是正数，那么最后这个值是否需要补齐还要根据编码后的字节数据是否是8的整数倍决定的)
		if (temp >= 0 ) {
			if (addFlag) {
				temp |= 256;   //1 0000 0000| 101 = 1 0000 0101    即在第九个bit上留个1，后面的8位除了原数据会不足的会补零
				String s = Integer.toBinaryString(temp);  //Integer.toBinaryString是将temp转换为对应的补码
				byteStringBuilder.append(s.substring(s.length() - 8)); 	
			} else {
				String s = Integer.toBinaryString(temp);   //不补齐
				byteStringBuilder.append(s); 	
			}
		} else { //负数需要截取
			String s = Integer.toBinaryString(temp);
			//byteStringBuilder.append("1");
			byteStringBuilder.append(s.substring(s.length() - 8));  //int负数补码的第八位必定为1
		}
		return byteStringBuilder.toString();
		
	}
	/**
	  *  解码
	 * @param huffmanCodesBytes
	 * @param huffmanCodesMap
	 * @return
	 */
	public static byte[] deode(byte[] huffmanCodesBytes, Map<Byte, String> huffmanCodesMap) {
		System.out.println(Arrays.toString(huffmanCodesBytes));
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < huffmanCodesBytes.length; i++) {
			if (i == huffmanCodesBytes.length - 1) {    //这里要根据实际情况判断
				stringBuilder.append(bytetoString(huffmanCodesBytes[i], false));
			} else {
				stringBuilder.append(bytetoString(huffmanCodesBytes[i], true));
			}
		}
		Map<String, Byte> reMap = new HashMap<String, Byte>();
		for (Map.Entry<Byte, String> mEntry : huffmanCodesMap.entrySet()) {
			reMap.put(mEntry.getValue(), mEntry.getKey());
		}
		System.out.println("解码"+stringBuilder.toString());
		System.out.println("解码"+reMap);
		
		List<Byte> list = new ArrayList<Byte>();
		//i是前指针，j是后指针.一直判断i~j之间的字符串是否在map中
		 //从头开始遍历，是否存在于reMap中
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
		System.out.println("解码"+Arrays.toString(bytes));
		return bytes;
	}
	/**
	 * 前序遍历
	 */
	public static void PreOrder(Node root) {
		if (root != null) {
			root.preOrder();
		} else {
			System.out.println("当前树为空");
		}
	}
	
}

class Node implements Comparable<Node> {
	private Byte data;     //字符
	private int weight;    //权值(次数)
	private Node leftNode;  //左结点
	private Node rightNode;  //右结点


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
