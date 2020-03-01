package com.XJK.huffman.huffmanTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * ��������
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
     * ���������������һ����Ӧ�Ļ�������
     * @param arr
     * @return   ����һ������������ͷ���
     */
	public static Node creatrHuffmanTree(int[] arr) {
		
		//��������Ĵ�С������Ӧ���б�
		List<Node> nodes = new ArrayList<Node>();
		for (int i : arr) {
			nodes.add(new Node(i));
		}
		System.out.println(nodes);
		Collections.sort(nodes);
		System.out.println(nodes);
		//��list��ֻ��һ������ʱ�򣬻��������������ˣ���ʣ�µ��Ǹ����Ϊ����������root���
		while(nodes.size() > 1) {
			//��ȡǰ�������
			Node leftNode = nodes.get(0);
			Node rightNode = nodes.get(1);	
			//���Ƴ�
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			
			//����һ���µĸ����
			Node newRootNode = new Node(leftNode.getValue() + rightNode.getValue());
			newRootNode.setLeftNode(leftNode);
			newRootNode.setRighNode(rightNode);
			
			//���½����뵽nodes��
			nodes.add(newRootNode);
			
			//��������
			Collections.sort(nodes);
		}
		
		//����
		nodes.get(0).preOrder();
		
		return nodes.get(0);
	}
	

}


class Node implements Comparable<Node> {  //���������Ľ��
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

	//ǰ�����
	public void preOrder() {
		if (this.getLeftNode() == null) {
			System.out.println(this);
		}
		
		
		if (this.getLeftNode() != null) {   //��ݹ�
			this.getLeftNode().preOrder();
		}
		if (this.getRighNode() != null) {   //�ҵݹ�
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
		//���ظ�����˵��this < o
		return this.value - o.value;
	}
}