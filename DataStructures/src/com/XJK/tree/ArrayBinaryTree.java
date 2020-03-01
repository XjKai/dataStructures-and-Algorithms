package com.XJK.tree;
/**
 * ˳��洢������(Ӧ�ó���:������)
 * �Զ������ķ�ʽ����˳��洢�����飻
 * ������ת��Ϊ����,����Ҳ����ת��Ϊ��
 * @author MSI
 *
 */
public class ArrayBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[]  arr = {1,2,3,4,5,6,7};
		
		Tree t = new Tree(arr);
		t.postOrder();
	}

}

class Tree{
	private int[]  arr;
	
	
	public Tree(int[] arr) {
		this.arr = arr;
	}
	//�����ķ�ʽǰ�����
	public void preOrder() {
		preOrder(0);
	}
	//�����ķ�ʽ�������
	public void midOrder() {
		midOrder(0);
	}
	//�����ķ�ʽ�������
	public void postOrder() {
		postOrder(0);
	}
	/**
	 * �����ķ�ʽǰ�����
	 * @param index
	 */
	public void preOrder(int index) {
		if (arr != null && index < arr.length) {
			System.out.println(arr[index]);
			if (2*index + 1 < arr.length) {    //��ݹ�
				preOrder(2*index + 1);
			}
			if (2*index + 2 < arr.length) {    //�ҵݹ�
				preOrder(2*index + 2);
			}
		}
	}
	/**
	 * �����ķ�ʽ�������
	 * @param index
	 */
	public void midOrder(int index) {
		if (arr != null && index < arr.length) {
			if (2*index + 1 < arr.length) {    //��ݹ�
				midOrder(2*index + 1);
			}
			System.out.println(arr[index]);
			if (2*index + 2 < arr.length) {    //�ҵݹ�
				midOrder(2*index + 2);
			}
		}
	}
	/**
	 * �����ķ�ʽ�������
	 * @param index
	 */
	public void postOrder(int index) {
		if (arr != null && index < arr.length) {
			if (2*index + 1 < arr.length) {    //��ݹ�
				postOrder(2*index + 1);
			}
			if (2*index + 2 < arr.length) {    //�ҵݹ�
				postOrder(2*index + 2);
			}
			System.out.println(arr[index]);
		}
	}
}