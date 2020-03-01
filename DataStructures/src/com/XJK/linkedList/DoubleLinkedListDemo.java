package com.XJK.linkedList;

/**
 * @author XJack ˫������
 */
public class DoubleLinkedListDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HeroNode2 hero1 = new HeroNode2(1, "�ν�", "��ʱ��");
		HeroNode2 hero2 = new HeroNode2(2, "¬����", "������");
		HeroNode2 hero3 = new HeroNode2(3, "����", "�Ƕ���");
		HeroNode2 hero4 = new HeroNode2(4, "�ֳ�", "����ͷ");

		DoubleLinkedList d = new DoubleLinkedList();
		d.addByOrder(hero1);
		d.addByOrder(hero4);
		d.addByOrder(hero3);
		d.addByOrder(hero2);
		d.show();

		d.delete(2);
		d.delete(8);
		d.show();

		d.update(new HeroNode2(3, "�ν�1", "��ʱ��1"));
		d.show();

	}

}

/**
 * ˫���б���
 * 
 * @author MSI ˫���б�� "��"��"��"��"��"��"ɾ"
 */
class DoubleLinkedList {
	// ����ͷ�ڵ��������ͷָ�룬�������Ա��ⵥ�����ǵ�һ���ڵ㣨�������ͷָ�룬��һ���ڵ�Ҫ�����ó��� ʹͷָ��ָ������
	private HeroNode2 head = new HeroNode2(0, "", ""); // ����ͷ�ڵ�

	/**
	 * 
	 * @return
	 */
	public HeroNode2 getHead() {
		return head;
	}

	/**
	 * ��β����ӽڵ�
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
	 * �������������Ԫ��
	 * 
	 * @param h
	 */
	public void addByOrder(HeroNode2 h) {
		HeroNode2 temp = head.next;
		while (true) {
			if (temp == null) { // ��β�����
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
				System.out.println("�Ѵ���");
				break;
			}
			temp = temp.next;
		}
	}

	/**
	 * ����Ų���
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
	 * �޸�(���ڲ��ҵ��ٸ���)
	 * 
	 * @param h
	 */
	public void update(HeroNode2 h) {
		HeroNode2 h1 = get(h.no);
		if (h1 == null) {
			System.out.println("������");
		} else {
			h1.name = h.name;
			h1.nickName = h.nickName;
			System.out.println("���޸�");
		}
	}

	/**
	 * ɾ��(���ҵ�����ɾ��)
	 * 
	 * @param n
	 */
	public void delete(int n) {
		HeroNode2 h1 = get(n);
		if (h1 == null) {
			System.out.println("������");
		} else {
			h1.pre.next = h1.next;
			if (h1.next != null) {
				h1.next.pre = h1.pre;
				System.out.println("���޸�");
			}
		}
	}

	/**
	 * ��ӡ���б�
	 */
	public void show() {
		// �ж��б��Ƿ�Ϊ��
		if (head.next == null) {
			System.out.println("�б�Ϊ��");
			return;
		}
		// ������ʱָ�룬��ָ���ͷָ���ĵ�һ���ڵ�
		HeroNode2 temp = head.next;
		while (true) {
			System.out.println(temp);
			if (temp.next == null) { // ��ָ��Ϊβ��ָ��
				break;
			}
			// ָ��ָ����һ���ڵ�
			temp = temp.next;
		}
	}
}

/**
 * ����Ӣ�۽ڵ�
 * 
 * @author XJack
 *
 */
class HeroNode2 {
	public int no;
	public String name;
	public String nickName;
	public HeroNode2 next; // ָ������ָ��
	public HeroNode2 pre; // ָ��ǰ���ָ��

	/**
	 * �������캯��
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
	 * ��дtoString()���� ��ݽ���shift + alt + s
	 */
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickName=" + nickName + "]";
	}

}
