package com.XJK.queue;

//����

import java.util.Scanner;

/**
 * @author MSI
 *����ģ�ⵥ�����
 */
public class ArrayQueueDemo {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayQueue queue = new ArrayQueue(3);
		char key = ' ';   //�����û�����
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		//����˵�
		while (loop) {
			System.out.println("s:��ʾ����");
			System.out.println("a:������ݵ�����");
			System.out.println("g:�Ӷ���ȡ������");
			System.out.println("h:�鿴����ͷ������");
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


//ģ��ʵ�ֶ���
class ArrayQueue{
	private int maxSize;   //��󳤶�
	private int front;     //ָ��ͷ,��ʼ��Ϊ-1  �� ��ʼ����ָ���Ԫ�ض�Ϊ������
	private int rear;      //ָ��β,��ʼ��Ϊ-1   ���������ݺ�ָ���Ԫ��ʼ�ն�Ϊ��Ч����
	private int[] arrQueue;     //����
	
	//���캯��,��ʼ�����г���
	public ArrayQueue(int n) {
		maxSize = n;
		arrQueue = new int[n];
		front = -1;
		rear = -1;
	}
	//�����Ƿ�Ϊ��
	public boolean isEmpty() {
		return front == rear;
	}
	//�����Ƿ���
	public boolean isFull() {
		return rear == maxSize-1;
	}
	//������ݵ�����
	public void add(int data) {
		if(isFull()) {
			System.out.println("��������!");
			return;
		}
		arrQueue[++rear] = data;
	}
	//�Ӷ����ó�����
	public int get() {
		if(isEmpty()) {
			throw new RuntimeException("���п�!");
		}
		return arrQueue[++front];
	}
	//��ʾ��������
	public void show() {
		if (isEmpty()) {
			System.out.println("����Ϊ��!");
			return;
		}
		for (int i = 0; i < arrQueue.length; i++) {
			System.out.println(arrQueue[i]);
		}
	}
	//��ȡָ��ָ��������
	public int fistData() {
		System.out.println("front:"+front);
		System.out.println("rea:r"+rear);
		if (isEmpty()) {
			throw new RuntimeException("����Ϊ��!");
		}
		return arrQueue[front+1];
	} 
}
