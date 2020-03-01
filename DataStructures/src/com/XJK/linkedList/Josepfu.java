package com.XJK.linkedList;


/**
 * @author MSI
 *����"����"����     ���Լɪ������ 
 */
public class Josepfu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JosepfuProbelm(10,5,6);
	}

	
	
	//���Լɪ������
	/**
	 * 
	 * @param nums    ����
	 * @param m       �ӵڼ����˿�ʼ
	 * @param k       ÿ�������ٸ���
	 */
	public static void JosepfuProbelm(int nums, int m, int k) {
		CircleSingleLinkedList c = new CircleSingleLinkedList();
		c.addNums(nums);
		if(nums < 1 || m > nums || c.getFirst() == null) {
			System.out.println("��������");
			return;
		}
		Boy helper ;     //������ָ�롱����Ϊ����������ɾ���ڵ�ʱ��ʼ����Ҫһ��ָ��ָ���ɾ���ڵ��ǰһ���ڵ㣬��ֹ������ѡ�firstʼ��ָ���ɾ���Ľڵ�
		//��helperָ��first��ǰһ��λ��
		helper = c.getFirst();   //��helper��ͷ��ʼ��
		while (true) {
			if(helper.getNext() == c.getFirst()) {
				break;
			}
			helper = helper.getNext();
		}
		//��helperָ��firstʱ����ֻʣ��һ������
		while(helper != c.getFirst()) {
			//��k�����֣����ƶ�k-1��
			for(int i = 0; i < k - 1; i ++) {
				c.setFirst(c.getFirst().getNext());
				helper = helper.getNext();
			}
			System.out.println(c.getFirst().getNo());
			//�Ƴ�first��ָ�����
			c.setFirst(c.getFirst().getNext());
			helper.setNext(c.getFirst());
		}
		System.out.println(helper.getNo());
	}
	
	
}

class CircleSingleLinkedList{
	private Boy first;     //������ָ�룬ָֻ���һ��С��
	
	
	public Boy getFirst() {
		return first;
	}

	public void setFirst(Boy first) {
		this.first = first;
	}

	//�������
	/**
	 * 
	 * @param b
	 */
	public void add(Boy b) {
		if(first == null) {   //��һ����������
			first = b;
			b.setNext(first);
		}else {
			Boy temp = first;   //������ʱָ��
			while(true) {
				if(temp.getNext() == first) {
					temp.setNext(b);
					b.setNext(first);
					break;
				}
				temp = temp.getNext();
			}
		}
	}
	
	// һ����Ӷ��
	/**
	 * 
	 * @param n
	 */
	public void addNums(int n) {
		Boy temp = null;    //ʼ��ָ��������ӵĽ�
		for (int i = 1; i <= n; i++) {
			if(i == 1) {
				first = temp = new Boy(i);
				
				temp.setNext(first);
			}else {
				temp.setNext(new Boy(i));
				temp = temp.getNext();
				temp.setNext(first);		
			}
		}
	}
	
	//����������ʾ����
	/**
	 * 
	 */
	public void show() {
		if (first == null) {
			System.out.println("����Ϊ��");
			return;
		}else {
			Boy temp = first;
			while(true) {
				System.out.println(temp);
				if (temp.getNext() == first) {
					break;
				}
				temp = temp.getNext();
			}
		}
	}
}


class Boy{
	private int no;
	private Boy next;
	public Boy(int no) {
		
		this.no = no;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Boy getNext() {
		return next;
	}
	public void setNext(Boy next) {
		this.next = next;
	}
	@Override
	public String toString() {
		return "Boy [no=" + no + "]";
	}
}