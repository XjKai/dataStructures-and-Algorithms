package com.XJK.linkedList;


/*
 * 有序单项链表
 * */


//指针指向（等于）谁，理解为“指针”“就是”谁**************
//头是不能用指针获取到的，只能用名字获取到，除非定义一个新指针指向它************

/**
 * @author MSI    //alt+shift+J
 *
 */
public class SingleLinkedListDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero3 = new HeroNode(3, "卢俊义", "玉麒麟");
		HeroNode hero5 = new HeroNode(5, "吴用", "智多星");
		HeroNode hero7 = new HeroNode(7, "林冲", "豹子头");
		HeroNode hero9 = new HeroNode(9, "55", "555");
		
	
		SingleLinkedList s  = new SingleLinkedList();
		
		//测试按顺序添加链表
		s.addByOrder(hero1);
		s.addByOrder(hero3);
		s.addByOrder(hero5);
		s.addByOrder(hero7);
		s.addByOrder(hero9);
		s.show();
		

		
		//测试修改链表
		//s.update(new HeroNode(3, "宋江1", "及时雨1"));
		//s.show();
		
		//测试查找
		//s.get(1);
	
		//reverseLinkedList(s.getHead());
		//s.show();
		//测试删除
		s.delete(5);
		s.show();
		
		s.delete(1);
		s.show();
		
		s.delete(3);
		s.show();
		
		s.delete(4);
		s.show();	
		
		s.delete(2);
		s.show();
	}

	/**
	 * 练习题三,单链表反转
	 * @param heaNode  头节点
	 * @return   
	 */
	public static void reverseLinkedList(HeroNode headNode) {
		HeroNode temp = headNode.nextHeroNode;  //为第一个节点
		HeroNode arr;       //用于保留temp的下一个节点,使节点不断裂
		HeroNode reverseTemp = new HeroNode(-1, null, null);   //定义一个临时的头
		if(temp  == null || temp.nextHeroNode == null) {
			//链表为空
			System.out.println("链表为空");
			return ;
		}
		
		while (true) {
			if(temp == null) {   //没有了
				break;
			}
			arr = temp.nextHeroNode;   //保留
			//把 拿出来的temp插入到临时的头和它下一个节点之间
			temp.nextHeroNode = reverseTemp.nextHeroNode;
			reverseTemp.nextHeroNode = temp;
			//temp再从原链表 拿节出来
			temp = arr;
		}
		headNode.nextHeroNode = reverseTemp.nextHeroNode;
	}
	
	/**
	 * 练习五
	 * 合并两个有序链表，合并后的链表依然有序
	 * @param s1
	 * @param s2
	 */

	
}

//创建单项列表类
class SingleLinkedList {
	// 创建头指针
	private HeroNode head = new HeroNode(0, "", "");

	/*
	 * 方法有：增(无序、有序)、删、改、查
	 * 
	 * */
	
	public  HeroNode getHead() {
		return head;
	}
	
	/**
	 * 添加节点
	 * @param n
	 */
	public void add(HeroNode n) {
		// 创建临时指针，并指向头指针
		HeroNode temp = head;
		// 寻找尾部指针
		while (true) { 
			if(temp.nextHeroNode == null) {  //该指针为尾部指针
				break;
			}
			// 指针指向下一个英雄节点
			temp = temp.nextHeroNode;
		}
		// 尾部指针指向新添加的英雄节点
		temp.nextHeroNode = n;
	}

	
	
	/**
	 * 按顺序添加节点
	 * @param n
	 */
	public void addByOrder(HeroNode n) {
		//定义辅助指针
		HeroNode temp = head;
		while(true) {
			//如果链表里存在这个数，就退出，不放入
			if(temp.no == n.no) {
				System.out.println("已存在");
				break;
			}
			//如果找到了最后一个之或者找到了它后面的一个(第一个比它大的)
			if(temp.nextHeroNode == null  || temp.nextHeroNode.no > n.no) {
				n.nextHeroNode = temp.nextHeroNode;
				temp.nextHeroNode = n;
				break;
			}
			temp = temp.nextHeroNode;
		}
		
	}

	
	
	/**
	 * 打印单列表
	 */
	public void show() {
		// 判断列表是否为空
		if (head.nextHeroNode == null) {
			System.out.println("列表为空");
			return;
		}
		// 创建临时指针，并指向除头指针后的第一个节点
		HeroNode temp = head.nextHeroNode;
		while (true) {
			System.out.println(temp);
			System.out.println();
			if (temp.nextHeroNode == null) { // 该指针为尾部指针
				break;
			}
			// 指针指向下一个节点
			temp = temp.nextHeroNode;
		}
	}
	
	
	
	/**
	 * 修改节点
	 * @param n
	 */
	public void update(HeroNode n) {
		//先判断链表是否为空
		if(head.nextHeroNode == null) {
			System.out.println("链表为空");
			return;
		}
		
		//链表不为空，寻找是否存在相应节点
		HeroNode temp = head;   //创建临时指针，并指向头节点
		while (true) {
			//找到了最后一个节点还没有找到
			if (temp == null) {
				System.out.println("不存在该节点");
				break;
			}
			//存在相应节点，就修改
			if(temp.no == n.no) {
				temp.name = n.name;
				temp.nickName = n.nickName;
				System.out.println("修改成功");
				break;
			}
			temp = temp.nextHeroNode;
		}
	}
	
	
	
	/**
	 * 删除节点
	 * @param n
	 */
	public void delete(int n) {
		//先判断链表是否为空
		if(head.nextHeroNode == null) {
			System.out.println("链表为空");
			return;
		}
		//寻找节点
		//定义临时指针
		HeroNode temp = head;
		while (true) {
			//找到了尾部
			if (temp.nextHeroNode == null) {
				System.out.println("不存在该节点");
				break;
			}
			//找到的temp所在的位置始终是目标节点的前一个节点，所以目标节点为temp.nextHeroNode
			if (temp.nextHeroNode.no == n) {
				//temp的指针就指向它所在的位置的下下个节点
				temp.nextHeroNode = temp.nextHeroNode.nextHeroNode; 
				System.out.println("删除成功");
				break;
			}
			temp = temp.nextHeroNode;
		}
		
	}
	
	
	
	/**
	 * 获取某个节点
	 * @param n
	 */
	public HeroNode get(int n) {
		//先判断链表是否为空
		if(head.nextHeroNode == null) {
			System.out.println("链表为空");
			return null;
		}
		HeroNode temp = head;
		while(true) {
			if (temp == null) {
				System.out.println("不存在该节点");
				break;
			}
			if (temp.no == n) {
				System.out.println("找到的节点为：" + temp.toString());
			    break;
			}
			temp = temp.nextHeroNode;
		}
		return temp;
	}
	/**
	 * 获取链表大小
	 * @return 
	 */
	public int size() {
		HeroNode temp = head;
		int count = 0;
		while (temp.nextHeroNode != null) {
			
			count++;
			temp = temp.nextHeroNode; 
		}
		return count;
	}
	
}


/**
 * 创建英雄节点
 * @author MSI
 *
 */
class HeroNode {
	public int no;
	public String name;
	public String nickName;
	HeroNode nextHeroNode; // 初始化为null

	
	/**
	 * 创建构造函数
	 * @param n
	 * @param name
	 * @param nickname
	 */
	public HeroNode(int n, String name, String nickname) {
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

	/*
	 * 如果打印nextHeroNode 会造成从链头打印到链尾的原因是重写的toString方法，在打印对象时
	 * 会调用对象的toString方法，碰到打印class：HeroNode 就会调用HeroNode的toString方法，
	 * 这样就会造成迭代调用，若不覆写toString，换一个名字就还好了
	 */
	
	/*
	 * print(对象) 其实调用的就是对象覆写的toString()函数
	 */
}
