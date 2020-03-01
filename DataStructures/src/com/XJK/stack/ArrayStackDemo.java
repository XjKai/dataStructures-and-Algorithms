package com.XJK.stack;

import java.util.Scanner;

/**
 * @author MSI
 * ����ģ��ջ
 */
public class ArrayStackDemo {

	public static void main(String[] args) {
		
		ArrayStack a = new ArrayStack(4);
		
		boolean loop = true;
		String key = " ";
		Scanner scanner = new Scanner(System.in);
		while (loop) {
			System.out.println("push:��ջ");
			System.out.println("pop:��ջ");
			System.out.println("show:��ʾջ");
			System.out.println("exit:�˳�");
			key = scanner.next();
			switch (key) {
			case "push":
				System.out.println("������һ����");
				int value = scanner.nextInt();
				a.push(value);
				break;
			case "pop":
				try {
					System.out.println(a.pop()+" ��ջ");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case "show":
				a.show();
				break;
			case "exit":
				scanner.close();
				loop = false;
				break;
			default:
			}
		}

	}

}
class ArrayStack{
	private int size;
	private int[] arr;
	private int top = -1;  //ջ��ָ���ʼ��ָ��-1
	
	public ArrayStack(int size) {
		this.size = size;
		arr =new int[size];
	}
	/**
	 * ջ�Ƿ���
	 * @return
	 */
	public boolean isFull() {
		if(top == size-1) {
			return true;
		}
		return false;
	}
	/**
	 * ջ�Ƿ��
	 * @return
	 */
	public boolean isEmpty() {
		if(top == -1) {
			return true;
		}
		return false;
	}
	/**
	 * �鿴ջ����ֵ
	 * @return
	 */
	public int peek() {
		if (isEmpty()) {
			throw new RuntimeException("ջΪ��");
		}
		return arr[top];
	}
	/**
	 * ��ջ
	 * @param n
	 */
	public void push(int n) {
		if (isFull()) {
			System.out.println("ջ����");
			return;
		}
		top++;
		arr[top] = n;
	}
	/**
	 * ��ջ
	 * @return
	 */
	public int pop() {
		if(isEmpty()) {
			throw new RuntimeException("ջΪ��");
		}
		int value = arr[top];
		top--;
		return value;
	}
	/**
	 * ��ʾջ������ʾ�����
	 */
	public void show() {
		if (isEmpty()) {
			System.out.println("ջΪ��");
			return;
		}
		for (int i = top; i >= 0; i--) {
			System.out.printf("arr[%d] : %d \n",i,arr[i]);
		}
	}
}