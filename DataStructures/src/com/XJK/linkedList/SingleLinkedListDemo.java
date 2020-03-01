package com.XJK.linkedList;


/*
 * ����������
 * */


//ָ��ָ�򣨵��ڣ�˭�����Ϊ��ָ�롱�����ǡ�˭**************
//ͷ�ǲ�����ָ���ȡ���ģ�ֻ�������ֻ�ȡ�������Ƕ���һ����ָ��ָ����************

/**
 * @author MSI    //alt+shift+J
 *
 */
public class SingleLinkedListDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HeroNode hero1 = new HeroNode(1, "�ν�", "��ʱ��");
		HeroNode hero3 = new HeroNode(3, "¬����", "������");
		HeroNode hero5 = new HeroNode(5, "����", "�Ƕ���");
		HeroNode hero7 = new HeroNode(7, "�ֳ�", "����ͷ");
		HeroNode hero9 = new HeroNode(9, "55", "555");
		
	
		SingleLinkedList s  = new SingleLinkedList();
		
		//���԰�˳���������
		s.addByOrder(hero1);
		s.addByOrder(hero3);
		s.addByOrder(hero5);
		s.addByOrder(hero7);
		s.addByOrder(hero9);
		s.show();
		

		
		//�����޸�����
		//s.update(new HeroNode(3, "�ν�1", "��ʱ��1"));
		//s.show();
		
		//���Բ���
		//s.get(1);
	
		//reverseLinkedList(s.getHead());
		//s.show();
		//����ɾ��
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
	 * ��ϰ����,������ת
	 * @param heaNode  ͷ�ڵ�
	 * @return   
	 */
	public static void reverseLinkedList(HeroNode headNode) {
		HeroNode temp = headNode.nextHeroNode;  //Ϊ��һ���ڵ�
		HeroNode arr;       //���ڱ���temp����һ���ڵ�,ʹ�ڵ㲻����
		HeroNode reverseTemp = new HeroNode(-1, null, null);   //����һ����ʱ��ͷ
		if(temp  == null || temp.nextHeroNode == null) {
			//����Ϊ��
			System.out.println("����Ϊ��");
			return ;
		}
		
		while (true) {
			if(temp == null) {   //û����
				break;
			}
			arr = temp.nextHeroNode;   //����
			//�� �ó�����temp���뵽��ʱ��ͷ������һ���ڵ�֮��
			temp.nextHeroNode = reverseTemp.nextHeroNode;
			reverseTemp.nextHeroNode = temp;
			//temp�ٴ�ԭ���� �ýڳ���
			temp = arr;
		}
		headNode.nextHeroNode = reverseTemp.nextHeroNode;
	}
	
	/**
	 * ��ϰ��
	 * �ϲ��������������ϲ����������Ȼ����
	 * @param s1
	 * @param s2
	 */

	
}

//���������б���
class SingleLinkedList {
	// ����ͷָ��
	private HeroNode head = new HeroNode(0, "", "");

	/*
	 * �����У���(��������)��ɾ���ġ���
	 * 
	 * */
	
	public  HeroNode getHead() {
		return head;
	}
	
	/**
	 * ��ӽڵ�
	 * @param n
	 */
	public void add(HeroNode n) {
		// ������ʱָ�룬��ָ��ͷָ��
		HeroNode temp = head;
		// Ѱ��β��ָ��
		while (true) { 
			if(temp.nextHeroNode == null) {  //��ָ��Ϊβ��ָ��
				break;
			}
			// ָ��ָ����һ��Ӣ�۽ڵ�
			temp = temp.nextHeroNode;
		}
		// β��ָ��ָ������ӵ�Ӣ�۽ڵ�
		temp.nextHeroNode = n;
	}

	
	
	/**
	 * ��˳����ӽڵ�
	 * @param n
	 */
	public void addByOrder(HeroNode n) {
		//���帨��ָ��
		HeroNode temp = head;
		while(true) {
			//����������������������˳���������
			if(temp.no == n.no) {
				System.out.println("�Ѵ���");
				break;
			}
			//����ҵ������һ��֮�����ҵ����������һ��(��һ���������)
			if(temp.nextHeroNode == null  || temp.nextHeroNode.no > n.no) {
				n.nextHeroNode = temp.nextHeroNode;
				temp.nextHeroNode = n;
				break;
			}
			temp = temp.nextHeroNode;
		}
		
	}

	
	
	/**
	 * ��ӡ���б�
	 */
	public void show() {
		// �ж��б��Ƿ�Ϊ��
		if (head.nextHeroNode == null) {
			System.out.println("�б�Ϊ��");
			return;
		}
		// ������ʱָ�룬��ָ���ͷָ���ĵ�һ���ڵ�
		HeroNode temp = head.nextHeroNode;
		while (true) {
			System.out.println(temp);
			System.out.println();
			if (temp.nextHeroNode == null) { // ��ָ��Ϊβ��ָ��
				break;
			}
			// ָ��ָ����һ���ڵ�
			temp = temp.nextHeroNode;
		}
	}
	
	
	
	/**
	 * �޸Ľڵ�
	 * @param n
	 */
	public void update(HeroNode n) {
		//���ж������Ƿ�Ϊ��
		if(head.nextHeroNode == null) {
			System.out.println("����Ϊ��");
			return;
		}
		
		//����Ϊ�գ�Ѱ���Ƿ������Ӧ�ڵ�
		HeroNode temp = head;   //������ʱָ�룬��ָ��ͷ�ڵ�
		while (true) {
			//�ҵ������һ���ڵ㻹û���ҵ�
			if (temp == null) {
				System.out.println("�����ڸýڵ�");
				break;
			}
			//������Ӧ�ڵ㣬���޸�
			if(temp.no == n.no) {
				temp.name = n.name;
				temp.nickName = n.nickName;
				System.out.println("�޸ĳɹ�");
				break;
			}
			temp = temp.nextHeroNode;
		}
	}
	
	
	
	/**
	 * ɾ���ڵ�
	 * @param n
	 */
	public void delete(int n) {
		//���ж������Ƿ�Ϊ��
		if(head.nextHeroNode == null) {
			System.out.println("����Ϊ��");
			return;
		}
		//Ѱ�ҽڵ�
		//������ʱָ��
		HeroNode temp = head;
		while (true) {
			//�ҵ���β��
			if (temp.nextHeroNode == null) {
				System.out.println("�����ڸýڵ�");
				break;
			}
			//�ҵ���temp���ڵ�λ��ʼ����Ŀ��ڵ��ǰһ���ڵ㣬����Ŀ��ڵ�Ϊtemp.nextHeroNode
			if (temp.nextHeroNode.no == n) {
				//temp��ָ���ָ�������ڵ�λ�õ����¸��ڵ�
				temp.nextHeroNode = temp.nextHeroNode.nextHeroNode; 
				System.out.println("ɾ���ɹ�");
				break;
			}
			temp = temp.nextHeroNode;
		}
		
	}
	
	
	
	/**
	 * ��ȡĳ���ڵ�
	 * @param n
	 */
	public HeroNode get(int n) {
		//���ж������Ƿ�Ϊ��
		if(head.nextHeroNode == null) {
			System.out.println("����Ϊ��");
			return null;
		}
		HeroNode temp = head;
		while(true) {
			if (temp == null) {
				System.out.println("�����ڸýڵ�");
				break;
			}
			if (temp.no == n) {
				System.out.println("�ҵ��Ľڵ�Ϊ��" + temp.toString());
			    break;
			}
			temp = temp.nextHeroNode;
		}
		return temp;
	}
	/**
	 * ��ȡ�����С
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
 * ����Ӣ�۽ڵ�
 * @author MSI
 *
 */
class HeroNode {
	public int no;
	public String name;
	public String nickName;
	HeroNode nextHeroNode; // ��ʼ��Ϊnull

	
	/**
	 * �������캯��
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
	 * ��дtoString()���� ��ݽ���shift + alt + s
	 */
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickName=" + nickName + "]";
	}

	/*
	 * �����ӡnextHeroNode ����ɴ���ͷ��ӡ����β��ԭ������д��toString�������ڴ�ӡ����ʱ
	 * ����ö����toString������������ӡclass��HeroNode �ͻ����HeroNode��toString������
	 * �����ͻ���ɵ������ã�������дtoString����һ�����־ͻ�����
	 */
	
	/*
	 * print(����) ��ʵ���õľ��Ƕ���д��toString()����
	 */
}
