package com.XJK.queue;

//���ζ���

import java.util.Scanner;

/**
 * @author MSI
 *����ģ�⻷�ζ���
 */
public class CiecleArrayQueue {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CircleArrayQueue queue = new CircleArrayQueue(5);
		char key = ' ';   //�����û�����
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		//����˵�
		while (loop) {
			System.err.println("s:��ʾ����");
			System.err.println("a:������ݵ�����");
			System.err.println("g:�Ӷ���ȡ������");
			System.err.println("h:�鿴����ͷ������");
			key = scanner.next().charAt(0);  //����һ���ַ�
			switch (key) {
			case 's':
				queue.show();
				break;

			case 'a':
				System.out.println("����һ����");
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


//ģ��ʵ�ֻ��ζ���
class CircleArrayQueue{
	private int maxSize;   //��󳤶�,��ʵ�����õĳ���ΪmaxSize-1����Ϊ��һ����ΪԼ��λ�ã�Լ����λ���ڶ�̬�仯��
	private int front;     
	private int rear;           
	private int[] arrQueue;     //����
	
	//���캯��,��ʼ�����г���
	public CircleArrayQueue(int n) {
		maxSize = n;
		arrQueue = new int[n];
		front = 0;              //�������ݺ�ָ���λ��ʼ��Ϊ��Ч
		rear = 0;               //��ʼ����ָ���Ԫ�ض�Ϊ������
	}
	//�����Ƿ�Ϊ��
	public boolean isEmpty() {
		return front == rear;
		
	}
	//�����Ƿ���
	public boolean isFull() {
		//ȡģ����Ϊ����ѭ������
		return (rear + 1) % maxSize == front;
		//return front - rear == 1 || (rear == maxSize - 1 && front == 0);
	}
	//������ݵ�����
	public void add(int data) {
		if (isFull()) {
			System.out.println("��������!");
			return;
		}
		// ��Ϊrear��ָ��λ��ʼ��Ϊ��
		arrQueue[rear] = data;
		// βָ�����һ��
		// ��֤��rearʼ����maxSize��Χ�ڣ��ȼ���if(rear == maxSize + 1){rear = 0;}
		rear = (rear + 1) % maxSize;
	}
	//�Ӷ����ó�����
	public int get() {
		if (isEmpty()) {
			throw new RuntimeException("���п�!");
		}
		// frontʼ��ָ�������Ч����
		// 1.���ó�����
		int temp = arrQueue[front];
		arrQueue[front] = 0; // ���
		// 2.ͷָ����"��"��һ��
		front = (front + 1) % maxSize;
		return temp;
	}
	//��ʾ��������
	public void show() {
		if (isEmpty()) {
			System.out.println("����Ϊ��!");
			return;
		}
//		//���βָ������ָ����棬������˳���ӡ
//		if(rear > front) {
//			for (int i = front; i < rear; i++) {
//			    System.out.println(arrQueue[i]);
//		    }	
//		}else {//���βָ������ָ��ǰ�棬�ȴ�ӡ��ָ�뵽maxSize-1�����ݣ��ٴ�ӡ0��rear-1������
//			//�ȴ�ӡ��ָ�뵽maxSize-1������
//			for(int i = front; i < maxSize; i++) {
//				System.out.println(arrQueue[i]);
//			}
//			//�ٴ�ӡ0��rear-1������
//			for (int j = 0; j < rear; j++) {
//				System.out.println(arrQueue[j]);
//			}
//		}
		for (int i = front; i < front + size(); i++) {
			System.out.println(arrQueue[i % maxSize]);
			// i % maxSize �ȼ���i���С��maxSize�ȴ�ӡ�ϰ룬iҪ�Ǵ�����maxSize�ʹ�ӡ�°�
		}

	}
	
	//��ȡ��Ч���ݵĳ���
	public int size() {
		return (rear + maxSize - front) % maxSize;
	}
	//��ȡָ��ָ��������
	public int fistData() {
		System.out.println("front : " + front);
		System.out.println("rear : " + rear);
		if (isEmpty()) {
			throw new RuntimeException("����Ϊ��!");
		}
		return arrQueue[front];
	}
}
