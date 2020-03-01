package com.XJK.hashTable;
/**
 * 哈希表 = 数组 + 链表   / = 数组 + 二叉树
 * 哈希表可以充当缓存，根据键值快速查找信息
 * 哈希表是一种数据结构
 * hash:散列、分散 
 * @author MSI
 *
 */
public class HashTableDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashTab  h = new HashTab(6);
		h.addByOrder(new Emp(18, "TOM"));
		h.addByOrder(new Emp(12, "TOM"));
		h.addByOrder(new Emp(0, "TOM"));
		h.addByOrder(new Emp(10, "TOM"));
		h.addByOrder(new Emp(5, "TOM"));
		h.addByOrder(new Emp(9, "TOM"));
		h.addByOrder(new Emp(3, "TOM"));
		h.addByOrder(new Emp(20, "TOM"));
		//h.show();
		h.update(new Emp(3, "TOM1"));
		h.show();
		h.get(3);
	}

}


class HashTab{
	private int maxSize;
	SingleLinkedlist[] singleLinkedlists;
	
	public HashTab(int maxSize) {
		this.maxSize = maxSize;
		singleLinkedlists = new SingleLinkedlist[maxSize];   //这里new只是new了数组!!!! 并没有 将数组里面每一个SingleLinkedlist实例化 
		for (int i = 0; i < maxSize; i++) {     //要实例化里面的每一个链表，不然为null，无法使用。
			singleLinkedlists[i] = new SingleLinkedlist();
		}
	}
	/**
	 * 分散函数，根据id将其分散到数组的不同位置去
	 * @param id
	 * @return
	 */
	public int disperseFuc(int id) {
		return id%maxSize;
	}
	/**
	 * 增
	 */
	public void add(Emp e) {
		singleLinkedlists[disperseFuc(e.id)].add(e);
	}
	public void addByOrder(Emp e) {
		singleLinkedlists[disperseFuc(e.id)].addByOrder(e);
	}
	/**
	 * 删
	 * @param id
	 */
	public void delete(int id) {
		singleLinkedlists[disperseFuc(id)].delete(id);
	}
	/**
	 * 改
	 * @param e
	 */
	public void update(Emp e) {
		singleLinkedlists[disperseFuc(e.id)].update(e);
	}
	/**
	 * 查
	 * @param id
	 * @return
	 */
	public Emp get(int id) {
		System.out.println("在第"+ (disperseFuc(id)+1)+"条链表");
		return singleLinkedlists[disperseFuc(id)].get(id);
	}
	public void show() {
		for (int i = 0; i < maxSize; i++) {
			singleLinkedlists[i].show(i);
		}
	}
}
/**
 * 单向链表
 *
 * @param 
 */
class SingleLinkedlist  {
	private Emp head;
	
	/**
	 * 无序添加
	 * @param e
	 */
	public void add(Emp e) {    
		if(head == null) {
			head = e;
			return;
		}
		Emp curEmp = head;
		while (curEmp.next != null) {
			curEmp = curEmp.next;
			break;
		}
		curEmp.next = e;
	}
	/**
	 * 根据id有序添加
	 * @param e
	 */
	public void addByOrder(Emp e) {    //根据id的大小顺序有序添加
		if(head == null) {
			head = e;
			return;
		}
		//因为没有空置的头节点，所以要单独判断头节点与新节点的顺序
		if(head.id > e.id) {
			e.next = head;
			head = e;
			return;
		} else if (head.id == e.id) {
			System.out.println("已存在");
			return;
		}
		
		Emp curEmp = head;
		while (true) {
			if (curEmp.id == e.id) {
				System.out.println("已存在");
				break;
			}
			if (curEmp.next == null || curEmp.next.id > e.id) {
				e.next = curEmp.next;
				curEmp.next = e;
				break;
			}
			curEmp = curEmp.next;
		}
	}
	/**
	 * 查找
	 * @param id
	 * @return
	 */
	public Emp get(int id) {
		if(head == null) {
			System.out.println("链表为空");
			return null;
		}
		Emp cuEmp = head; //临时指针
		while (true) {
			if (cuEmp == null) {
				System.out.println("不存在该id信息");
				break;
			}
			if(cuEmp.id == id) {
				System.out.printf("找到的节点为 id: %d   name: %s",cuEmp.id,cuEmp.name);
				break;
			}
			cuEmp = cuEmp.next;
		}
		return cuEmp;
	}
	/**
	 * 修改
	 * @param e
	 */
	public void update(Emp e) {
		Emp tempEmp = get(e.id);
		if (tempEmp == null) {
			System.out.println("修改失败");
			return;
		}
		tempEmp.name = e.name;
	}
	/**
	 * 删除信息
	 * @param id
	 */
	public void delete(int id) {
		if(head == null) {
			System.out.println("链表为空");
			return;
		}
		//因为没有空置的头节点，所以要单独判断头节点是否是要删除的节点
		if(head.id == id) {
			head = null;
			return;
		}
		
		Emp cuEmp = head; //临时指针
		while (true) {
			if (cuEmp.next == null) {
				System.out.println("不存在该id信息");
				break;
			}
			if(cuEmp.next.id == id) {
				cuEmp.next = cuEmp.next.next;
				break;
			}
			cuEmp = cuEmp.next;
		}
	}
	/**
	 * 显示这一条链表
	 */
	public void show(int i) {
		System.out.println("第"+ (i+1)+"条链表");
		if(head == null) {
			System.out.println("链表为空");
		}
		Emp cuEmp = head;   //临时指针
		while (cuEmp != null) {
			System.out.printf("id: %d  name: %s    ",cuEmp.id,cuEmp.name);	
			cuEmp = cuEmp.next;
		}
		System.out.println();
		System.out.println();
	}
}
/**
 *员工节点
 *
 */
class Emp {
	public int id;
	public String name;
	public Emp next;
	
	
	public Emp(int id, String nameString) {
		this.id = id;
		this.name = nameString;
	}
	
	
}