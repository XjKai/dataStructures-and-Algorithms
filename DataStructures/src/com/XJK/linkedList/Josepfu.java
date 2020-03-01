package com.XJK.linkedList;


/**
 * @author MSI
 *单向"环形"链表     解决约瑟夫问题 
 */
public class Josepfu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JosepfuProbelm(10,5,6);
	}

	
	
	//求解约瑟夫问题
	/**
	 * 
	 * @param nums    人数
	 * @param m       从第几个人开始
	 * @param k       每次数多少个数
	 */
	public static void JosepfuProbelm(int nums, int m, int k) {
		CircleSingleLinkedList c = new CircleSingleLinkedList();
		c.addNums(nums);
		if(nums < 1 || m > nums || c.getFirst() == null) {
			System.out.println("参数错误");
			return;
		}
		Boy helper ;     //“帮助指针”，因为单向链表在删除节点时，始终需要一个指针指向待删除节点的前一个节点，防止链表断裂。first始终指向待删除的节点
		//让helper指向first的前一个位置
		helper = c.getFirst();   //让helper从头开始找
		while (true) {
			if(helper.getNext() == c.getFirst()) {
				break;
			}
			helper = helper.getNext();
		}
		//当helper指向first时，就只剩下一个人了
		while(helper != c.getFirst()) {
			//数k个数字，即移动k-1次
			for(int i = 0; i < k - 1; i ++) {
				c.setFirst(c.getFirst().getNext());
				helper = helper.getNext();
			}
			System.out.println(c.getFirst().getNo());
			//移除first所指向的人
			c.setFirst(c.getFirst().getNext());
			helper.setNext(c.getFirst());
		}
		System.out.println(helper.getNo());
	}
	
	
}

class CircleSingleLinkedList{
	private Boy first;     //定义首指针，只指向第一个小孩
	
	
	public Boy getFirst() {
		return first;
	}

	public void setFirst(Boy first) {
		this.first = first;
	}

	//单个添加
	/**
	 * 
	 * @param b
	 */
	public void add(Boy b) {
		if(first == null) {   //第一个单独考虑
			first = b;
			b.setNext(first);
		}else {
			Boy temp = first;   //定义临时指针
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
	
	// 一次添加多个
	/**
	 * 
	 * @param n
	 */
	public void addNums(int n) {
		Boy temp = null;    //始终指向最新添加的节
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
	
	//遍历，即显示函数
	/**
	 * 
	 */
	public void show() {
		if (first == null) {
			System.out.println("链表为空");
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