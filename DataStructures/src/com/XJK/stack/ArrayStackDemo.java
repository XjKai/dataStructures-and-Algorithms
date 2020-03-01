package com.XJK.stack;

import java.util.Scanner;

/**
 * @author MSI
 * 数组模拟栈
 */
public class ArrayStackDemo {

	public static void main(String[] args) {
		
		ArrayStack a = new ArrayStack(4);
		
		boolean loop = true;
		String key = " ";
		Scanner scanner = new Scanner(System.in);
		while (loop) {
			System.out.println("push:入栈");
			System.out.println("pop:出栈");
			System.out.println("show:显示栈");
			System.out.println("exit:退出");
			key = scanner.next();
			switch (key) {
			case "push":
				System.out.println("请输入一个数");
				int value = scanner.nextInt();
				a.push(value);
				break;
			case "pop":
				try {
					System.out.println(a.pop()+" 出栈");
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
	private int top = -1;  //栈顶指针初始化指向-1
	
	public ArrayStack(int size) {
		this.size = size;
		arr =new int[size];
	}
	/**
	 * 栈是否满
	 * @return
	 */
	public boolean isFull() {
		if(top == size-1) {
			return true;
		}
		return false;
	}
	/**
	 * 栈是否空
	 * @return
	 */
	public boolean isEmpty() {
		if(top == -1) {
			return true;
		}
		return false;
	}
	/**
	 * 查看栈顶的值
	 * @return
	 */
	public int peek() {
		if (isEmpty()) {
			throw new RuntimeException("栈为空");
		}
		return arr[top];
	}
	/**
	 * 入栈
	 * @param n
	 */
	public void push(int n) {
		if (isFull()) {
			System.out.println("栈已满");
			return;
		}
		top++;
		arr[top] = n;
	}
	/**
	 * 出栈
	 * @return
	 */
	public int pop() {
		if(isEmpty()) {
			throw new RuntimeException("栈为空");
		}
		int value = arr[top];
		top--;
		return value;
	}
	/**
	 * 显示栈，先显示后进的
	 */
	public void show() {
		if (isEmpty()) {
			System.out.println("栈为空");
			return;
		}
		for (int i = top; i >= 0; i--) {
			System.out.printf("arr[%d] : %d \n",i,arr[i]);
		}
	}
}