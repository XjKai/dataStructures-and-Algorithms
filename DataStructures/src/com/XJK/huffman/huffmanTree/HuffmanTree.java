package com.XJK.huffman.huffmanTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 霍夫曼树
 * @author MSI
 *
 */
public class HuffmanTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {13, 7, 8, 3, 29, 6, 1};
		creatrHuffmanTree(arr);
	}
	
    /**
     * 根据数组产生产生一个相应的霍夫曼树
     * @param arr
     * @return   返回一个霍夫曼树的头结点
     */
	public static Node creatrHuffmanTree(int[] arr) {
		
		//根据数组的大小生成相应的列表
		List<Node> nodes = new ArrayList<Node>();
		for (int i : arr) {
			nodes.add(new Node(i));
		}
		System.out.println(nodes);
		Collections.sort(nodes);
		System.out.println(nodes);
		//当list中只有一个结点的时候，霍夫曼树就生成了，且剩下的那个结点为霍夫曼数的root结点
		while(nodes.size() > 1) {
			//获取前两个结点
			Node leftNode = nodes.get(0);
			Node rightNode = nodes.get(1);	
			//并移出
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			
			//产生一个新的根结点
			Node newRootNode = new Node(leftNode.getValue() + rightNode.getValue());
			newRootNode.setLeftNode(leftNode);
			newRootNode.setRighNode(rightNode);
			
			//将新结点加入到nodes中
			nodes.add(newRootNode);
			
			//重新排序
			Collections.sort(nodes);
		}
		
		//遍历
		nodes.get(0).preOrder();
		
		return nodes.get(0);
	}
	

}


class Node implements Comparable<Node> {  //霍夫曼树的结点
	private Node leftNode;
	private Node righNode;
	private int value;
	
	public Node getLeftNode() {
		return leftNode;
	}


	public void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}


	public Node getRighNode() {
		return righNode;
	}


	public void setRighNode(Node righNode) {
		this.righNode = righNode;
	}
	
	
	public Node(int value) {
		super();
		this.value = value;
	}


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}

	//前序遍历
	public void preOrder() {
		if (this.getLeftNode() == null) {
			System.out.println(this);
		}
		
		
		if (this.getLeftNode() != null) {   //左递归
			this.getLeftNode().preOrder();
		}
		if (this.getRighNode() != null) {   //右递归
			this.getRighNode().preOrder();
		}
	}
	
	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}


	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		//返回负数，说明this < o
		return this.value - o.value;
	}
}