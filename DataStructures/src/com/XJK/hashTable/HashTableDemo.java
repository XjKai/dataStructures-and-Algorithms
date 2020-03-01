package com.XJK.hashTable;
/**
 * ��ϣ�� = ���� + ����   / = ���� + ������
 * ��ϣ����Գ䵱���棬���ݼ�ֵ���ٲ�����Ϣ
 * ��ϣ����һ�����ݽṹ
 * hash:ɢ�С���ɢ 
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
		singleLinkedlists = new SingleLinkedlist[maxSize];   //����newֻ��new������!!!! ��û�� ����������ÿһ��SingleLinkedlistʵ���� 
		for (int i = 0; i < maxSize; i++) {     //Ҫʵ���������ÿһ��������ȻΪnull���޷�ʹ�á�
			singleLinkedlists[i] = new SingleLinkedlist();
		}
	}
	/**
	 * ��ɢ����������id�����ɢ������Ĳ�ͬλ��ȥ
	 * @param id
	 * @return
	 */
	public int disperseFuc(int id) {
		return id%maxSize;
	}
	/**
	 * ��
	 */
	public void add(Emp e) {
		singleLinkedlists[disperseFuc(e.id)].add(e);
	}
	public void addByOrder(Emp e) {
		singleLinkedlists[disperseFuc(e.id)].addByOrder(e);
	}
	/**
	 * ɾ
	 * @param id
	 */
	public void delete(int id) {
		singleLinkedlists[disperseFuc(id)].delete(id);
	}
	/**
	 * ��
	 * @param e
	 */
	public void update(Emp e) {
		singleLinkedlists[disperseFuc(e.id)].update(e);
	}
	/**
	 * ��
	 * @param id
	 * @return
	 */
	public Emp get(int id) {
		System.out.println("�ڵ�"+ (disperseFuc(id)+1)+"������");
		return singleLinkedlists[disperseFuc(id)].get(id);
	}
	public void show() {
		for (int i = 0; i < maxSize; i++) {
			singleLinkedlists[i].show(i);
		}
	}
}
/**
 * ��������
 *
 * @param 
 */
class SingleLinkedlist  {
	private Emp head;
	
	/**
	 * �������
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
	 * ����id�������
	 * @param e
	 */
	public void addByOrder(Emp e) {    //����id�Ĵ�С˳���������
		if(head == null) {
			head = e;
			return;
		}
		//��Ϊû�п��õ�ͷ�ڵ㣬����Ҫ�����ж�ͷ�ڵ����½ڵ��˳��
		if(head.id > e.id) {
			e.next = head;
			head = e;
			return;
		} else if (head.id == e.id) {
			System.out.println("�Ѵ���");
			return;
		}
		
		Emp curEmp = head;
		while (true) {
			if (curEmp.id == e.id) {
				System.out.println("�Ѵ���");
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
	 * ����
	 * @param id
	 * @return
	 */
	public Emp get(int id) {
		if(head == null) {
			System.out.println("����Ϊ��");
			return null;
		}
		Emp cuEmp = head; //��ʱָ��
		while (true) {
			if (cuEmp == null) {
				System.out.println("�����ڸ�id��Ϣ");
				break;
			}
			if(cuEmp.id == id) {
				System.out.printf("�ҵ��Ľڵ�Ϊ id: %d   name: %s",cuEmp.id,cuEmp.name);
				break;
			}
			cuEmp = cuEmp.next;
		}
		return cuEmp;
	}
	/**
	 * �޸�
	 * @param e
	 */
	public void update(Emp e) {
		Emp tempEmp = get(e.id);
		if (tempEmp == null) {
			System.out.println("�޸�ʧ��");
			return;
		}
		tempEmp.name = e.name;
	}
	/**
	 * ɾ����Ϣ
	 * @param id
	 */
	public void delete(int id) {
		if(head == null) {
			System.out.println("����Ϊ��");
			return;
		}
		//��Ϊû�п��õ�ͷ�ڵ㣬����Ҫ�����ж�ͷ�ڵ��Ƿ���Ҫɾ���Ľڵ�
		if(head.id == id) {
			head = null;
			return;
		}
		
		Emp cuEmp = head; //��ʱָ��
		while (true) {
			if (cuEmp.next == null) {
				System.out.println("�����ڸ�id��Ϣ");
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
	 * ��ʾ��һ������
	 */
	public void show(int i) {
		System.out.println("��"+ (i+1)+"������");
		if(head == null) {
			System.out.println("����Ϊ��");
		}
		Emp cuEmp = head;   //��ʱָ��
		while (cuEmp != null) {
			System.out.printf("id: %d  name: %s    ",cuEmp.id,cuEmp.name);	
			cuEmp = cuEmp.next;
		}
		System.out.println();
		System.out.println();
	}
}
/**
 *Ա���ڵ�
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