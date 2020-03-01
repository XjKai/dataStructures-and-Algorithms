package com.XJK.linkedList;

/**
 * @author XJack 双向链表
 */
public class DoubleLinkedListDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
		HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
		HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
		HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

		DoubleLinkedList d = new DoubleLinkedList();
		d.addByOrder(hero1);
		d.addByOrder(hero4);
		d.addByOrder(hero3);
		d.addByOrder(hero2);
		d.show();

		d.delete(2);
		d.delete(8);
		d.show();

		d.update(new HeroNode2(3, "宋江1", "及时雨1"));
		d.show();

	}

}

/**
 * 双向列表类
 * 
 * @author MSI 双向列表的 "增"、"查"、"改"、"删"
 */
class DoubleLinkedList {
	// 创建头节点而不创建头指针，这样可以避免单独考虑第一个节点（如果创建头指针，第一个节点要单独拿出来 使头指针指向它）
	private HeroNode2 head = new HeroNode2(0, "", ""); // 创建头节点

	/**
	 * 
	 * @return
	 */
	public HeroNode2 getHead() {
		return head;
	}

	/**
	 * 向尾部添加节点
	 * 
	 * @param h
	 */
	public void add(HeroNode2 h) {
		HeroNode2 temp = head;
		while (temp.next != null) {
			temp = temp.next;
		}
		temp.next = h;
		h.pre = temp;
	}

	/**
	 * 向链表有序添加元素
	 * 
	 * @param h
	 */
	public void addByOrder(HeroNode2 h) {
		HeroNode2 temp = head.next;
		while (true) {
			if (temp == null) { // 向尾部添加
				add(h);
				break;
			}
			if (temp.no > h.no) {
				temp.pre.next = h;
				h.pre = temp.pre;
				h.next = temp;
				temp.pre = h;
				break;
			}
			if (temp.no == h.no) {
				System.out.println("已存在");
				break;
			}
			temp = temp.next;
		}
	}

	/**
	 * 按标号查找
	 * 
	 * @param n
	 * @return
	 */
	public HeroNode2 get(int n) {
		HeroNode2 temp = head.next;
		while (true) {
			if (temp == null) {
				break;
			}
			if (temp.no == n) {
				break;
			}
			temp = temp.next;
		}
		return temp;
	}

	/**
	 * 修改(基于查找到再更新)
	 * 
	 * @param h
	 */
	public void update(HeroNode2 h) {
		HeroNode2 h1 = get(h.no);
		if (h1 == null) {
			System.out.println("不存在");
		} else {
			h1.name = h.name;
			h1.nickName = h.nickName;
			System.out.println("已修改");
		}
	}

	/**
	 * 删除(查找到后再删除)
	 * 
	 * @param n
	 */
	public void delete(int n) {
		HeroNode2 h1 = get(n);
		if (h1 == null) {
			System.out.println("不存在");
		} else {
			h1.pre.next = h1.next;
			if (h1.next != null) {
				h1.next.pre = h1.pre;
				System.out.println("已修改");
			}
		}
	}

	/**
	 * 打印单列表
	 */
	public void show() {
		// 判断列表是否为空
		if (head.next == null) {
			System.out.println("列表为空");
			return;
		}
		// 创建临时指针，并指向除头指针后的第一个节点
		HeroNode2 temp = head.next;
		while (true) {
			System.out.println(temp);
			if (temp.next == null) { // 该指针为尾部指针
				break;
			}
			// 指针指向下一个节点
			temp = temp.next;
		}
	}
}

/**
 * 创建英雄节点
 * 
 * @author XJack
 *
 */
class HeroNode2 {
	public int no;
	public String name;
	public String nickName;
	public HeroNode2 next; // 指向后向的指针
	public HeroNode2 pre; // 指向前向的指针

	/**
	 * 创建构造函数
	 * 
	 * @param n
	 * @param name
	 * @param nickname
	 */
	public HeroNode2(int n, String name, String nickname) {
		this.no = n;
		this.name = name;
		this.nickName = nickname;
	}

	/**
	 * 重写toString()函数 快捷健：shift + alt + s
	 */
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickName=" + nickName + "]";
	}

}
