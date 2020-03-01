package com.XJK.tree;
/**
 * 顺序存储二叉树(应用场景:堆排序)
 * 以二叉树的方式遍历顺序存储的数组；
 * 树可以转换为数组,数组也可以转换为数
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
	//以树的方式前序遍历
	public void preOrder() {
		preOrder(0);
	}
	//以树的方式中序遍历
	public void midOrder() {
		midOrder(0);
	}
	//以树的方式后序遍历
	public void postOrder() {
		postOrder(0);
	}
	/**
	 * 以树的方式前序遍历
	 * @param index
	 */
	public void preOrder(int index) {
		if (arr != null && index < arr.length) {
			System.out.println(arr[index]);
			if (2*index + 1 < arr.length) {    //左递归
				preOrder(2*index + 1);
			}
			if (2*index + 2 < arr.length) {    //右递归
				preOrder(2*index + 2);
			}
		}
	}
	/**
	 * 以树的方式中序遍历
	 * @param index
	 */
	public void midOrder(int index) {
		if (arr != null && index < arr.length) {
			if (2*index + 1 < arr.length) {    //左递归
				midOrder(2*index + 1);
			}
			System.out.println(arr[index]);
			if (2*index + 2 < arr.length) {    //右递归
				midOrder(2*index + 2);
			}
		}
	}
	/**
	 * 以树的方式后序遍历
	 * @param index
	 */
	public void postOrder(int index) {
		if (arr != null && index < arr.length) {
			if (2*index + 1 < arr.length) {    //左递归
				postOrder(2*index + 1);
			}
			if (2*index + 2 < arr.length) {    //右递归
				postOrder(2*index + 2);
			}
			System.out.println(arr[index]);
		}
	}
}