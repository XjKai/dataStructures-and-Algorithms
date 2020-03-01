package com.XJK.queue;

//队列

import java.util.Scanner;

/**
 * @author MSI
 *数组模拟单向队列
 */
public class ArrayQueueDemo {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayQueue queue = new ArrayQueue(3);
		char key = ' ';   //接收用户输入
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		//输出菜单
		while (loop) {
			System.out.println("s:显示队列");
			System.out.println("a:添加数据到队列");
			System.out.println("g:从队列取出数据");
			System.out.println("h:查看队列头的数据");
			key = scanner.next().charAt(0);  //接收一个字符
			switch (key) {
			case 's':
				queue.show();
				break;

			case 'a':
				System.out.println("输入一个数");
				int data = scanner.nextInt();
				queue.add(data);;
				break;
				
			case 'g':
				try {
					System.out.println(queue.get());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				break;
				
			case 'h':
				try {
					System.out.println(queue.fistData());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + key);
			}
		}
		scanner.close();
	}
}


//模拟实现队列
class ArrayQueue{
	private int maxSize;   //最大长度
	private int front;     //指针头,初始化为-1  ， 从始至终指向的元素都为空数据
	private int rear;      //指针尾,初始化为-1   ，放入数据后，指向的元素始终都为有效数据
	private int[] arrQueue;     //队列
	
	//构造函数,初始化队列长度
	public ArrayQueue(int n) {
		maxSize = n;
		arrQueue = new int[n];
		front = -1;
		rear = -1;
	}
	//队列是否为空
	public boolean isEmpty() {
		return front == rear;
	}
	//队列是否满
	public boolean isFull() {
		return rear == maxSize-1;
	}
	//添加数据到队列
	public void add(int data) {
		if(isFull()) {
			System.out.println("队列满了!");
			return;
		}
		arrQueue[++rear] = data;
	}
	//从队列拿出数据
	public int get() {
		if(isEmpty()) {
			throw new RuntimeException("队列空!");
		}
		return arrQueue[++front];
	}
	//显示所有数据
	public void show() {
		if (isEmpty()) {
			System.out.println("队列为空!");
			return;
		}
		for (int i = 0; i < arrQueue.length; i++) {
			System.out.println(arrQueue[i]);
		}
	}
	//获取指针指的首数据
	public int fistData() {
		System.out.println("front:"+front);
		System.out.println("rea:r"+rear);
		if (isEmpty()) {
			throw new RuntimeException("队列为空!");
		}
		return arrQueue[front+1];
	} 
}
