package com.XJK.queue;

//环形队列

import java.util.Scanner;

/**
 * @author MSI
 *数组模拟环形队列
 */
public class CiecleArrayQueue {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CircleArrayQueue queue = new CircleArrayQueue(5);
		char key = ' ';   //接收用户输入
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		//输出菜单
		while (loop) {
			System.err.println("s:显示队列");
			System.err.println("a:添加数据到队列");
			System.err.println("g:从队列取出数据");
			System.err.println("h:查看队列头的数据");
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


//模拟实现环形队列
class CircleArrayQueue{
	private int maxSize;   //最大长度,其实最大可用的长度为maxSize-1，因为有一个做为约定位置（约定的位置在动态变化）
	private int front;     
	private int rear;           
	private int[] arrQueue;     //队列
	
	//构造函数,初始化队列长度
	public CircleArrayQueue(int n) {
		maxSize = n;
		arrQueue = new int[n];
		front = 0;              //放入数据后，指向的位置始终为有效
		rear = 0;               //从始至终指向的元素都为空数据
	}
	//队列是否为空
	public boolean isEmpty() {
		return front == rear;
		
	}
	//队列是否满
	public boolean isFull() {
		//取模就是为了能循环利用
		return (rear + 1) % maxSize == front;
		//return front - rear == 1 || (rear == maxSize - 1 && front == 0);
	}
	//添加数据到队列
	public void add(int data) {
		if (isFull()) {
			System.out.println("队列满了!");
			return;
		}
		// 因为rear所指的位置始终为空
		arrQueue[rear] = data;
		// 尾指向后移一个
		// 保证了rear始终在maxSize范围内，等价于if(rear == maxSize + 1){rear = 0;}
		rear = (rear + 1) % maxSize;
	}
	//从队列拿出数据
	public int get() {
		if (isEmpty()) {
			throw new RuntimeException("队列空!");
		}
		// front始终指向的是有效数据
		// 1.先拿出数据
		int temp = arrQueue[front];
		arrQueue[front] = 0; // 清空
		// 2.头指针向"后"移一个
		front = (front + 1) % maxSize;
		return temp;
	}
	//显示所有数据
	public void show() {
		if (isEmpty()) {
			System.out.println("队列为空!");
			return;
		}
//		//如果尾指针在首指针后面，正常按顺序打印
//		if(rear > front) {
//			for (int i = front; i < rear; i++) {
//			    System.out.println(arrQueue[i]);
//		    }	
//		}else {//如果尾指针在首指针前面，先打印首指针到maxSize-1的数据，再打印0到rear-1的数据
//			//先打印首指针到maxSize-1的数据
//			for(int i = front; i < maxSize; i++) {
//				System.out.println(arrQueue[i]);
//			}
//			//再打印0到rear-1的数据
//			for (int j = 0; j < rear; j++) {
//				System.out.println(arrQueue[j]);
//			}
//		}
		for (int i = front; i < front + size(); i++) {
			System.out.println(arrQueue[i % maxSize]);
			// i % maxSize 等价于i如果小于maxSize先打印上半，i要是大于了maxSize就打印下半
		}

	}
	
	//获取有效数据的长度
	public int size() {
		return (rear + maxSize - front) % maxSize;
	}
	//获取指针指的首数据
	public int fistData() {
		System.out.println("front : " + front);
		System.out.println("rear : " + rear);
		if (isEmpty()) {
			throw new RuntimeException("队列为空!");
		}
		return arrQueue[front];
	}
}
