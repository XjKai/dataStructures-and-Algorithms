package com.XJK.tree;
/**
 * ������������
 * 
 * @author MSI
 *
 */
public class ThreadedBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HearoNode1 h1 = new HearoNode1(1, "1");
		HearoNode1 h2 = new HearoNode1(2, "2");
		HearoNode1 h3 = new HearoNode1(3, "3");
		HearoNode1 h4 = new HearoNode1(4, "4");
		HearoNode1 h5 = new HearoNode1(5, "5");
		HearoNode1 h6 = new HearoNode1(6, "6");
		
		
		//����һ����������
		h1.setLeft(h2); h1.setRight(h3);
		h2.setLeft(h4); h2.setRight(h5);
		h3.setLeft(h6);
		
		ThreadedBTree t = new ThreadedBTree(h1);
		t.threadedTreeByPostOrder();
		
		//System.out.println(h5.getLeft());
		//System.out.println(h5.getRight());
	}

}
class ThreadedBTree{
	private HearoNode1 root = null;   //ͷ���
	//pre������Ҫ
	private HearoNode1 pre = null;  //��������������ʱ���ڱ��浱ǰ���ǰһ��������ʱ���(�����ǰ��˳������(ǰ/��)�����ʱ��˳��)
	
	
	
	public ThreadedBTree(HearoNode1 root) {
		this.root = root;
	}
	
	//ǰ��������������
	public void threadedTreeByPreOrder() {
		threadedTreeByPreOrder(root);
	}
	//����������������
	public void threadedTreeByMidOrder() {
		threadedTreeByMidOrder(root);
	}
	//����������������
	public void threadedTreeByPostOrder() {
		threadedTreeByPostOrder(root);
	}
	/**
	 * ǰ��������������
	 * @param h
	 */
	public void threadedTreeByPreOrder(HearoNode1 h) {
		if(h == null) {
			return;
		}
		if (h.getLeft() == null) {
			h.setThreadLeft(pre);
		}
		if (pre != null && pre.getRight() == null) {
			pre.setThreadRight(h);
		}
		pre = h;
		System.out.println(pre);
		if (h.getLeftType() != 1) {  //��ݹ�
			threadedTreeByPreOrder(h.getLeft());
		}
		if (h.getRightType() != 1) {     //�ҵݹ�
			threadedTreeByPreOrder(h.getRight());
		}
	}
	
	/**
	 * ����������������  (���ǽ������������ŵ��������ʱ�����λ��)
	 * @param h
	 */
	public void threadedTreeByMidOrder(HearoNode1 h) {
		
		if (h == null) { //��ǰ���Ϊ�գ��Ͳ����½��еݹ�
			return;
		}
		if(h.getLeft() != null) {   //����ݹ�
			threadedTreeByMidOrder(h.getLeft());
		}
		
		//����������(�������˳�����ÿһ���������ҽ���Ƿ�Ϊ�գ����Ϊ�վͽ���ָ��ǰ�������ߺ������)
		if(h.getLeft() == null) {      //������ǰ��������ڵ�ǰ���ĵݹ�ջ���޸�
			h.setThreadLeft(pre);  //�õ�ǰ������ָ��ָ��ǰ�����(�����ǰ������ָ��Ϊ�յĻ�)
		}
		if (pre != null && pre.getRight() ==null) {     // ����������������ڴ��޸Ľ�����һ������޸ĵ�
			//����ǰ�ݹ������޸���һ���ݹ�����ĺ������(���)
			pre.setThreadRight(h);//��ǰ��������ָ��ָ��ǰ���(���ǰ��������ָ��Ϊ�յĻ�)
		}
		
		//!!!
		pre = h;    //ÿ������һ������Ժ��޸�pre   (�ڱ�����������������˳��Ҳ�����������˳��)
		System.out.println(pre);
		
		if(h.getRight() != null) {   //���ҵݹ�
			threadedTreeByMidOrder(h.getRight());
		}
	}
	/**
	 * ����������������
	 * @param h
	 */
	public void threadedTreeByPostOrder(HearoNode1 h) {
		if (h == null) {
			return;
		}
		if (h.getLeft() != null) {
			threadedTreeByPostOrder(h.getLeft());
		}
		if (h.getRight() != null) {
			threadedTreeByPostOrder(h.getRight());
		}
		if (h.getLeft() == null) {
			h.setThreadLeft(null);
		}
		if (pre !=null && pre.getRight() == null) {
			pre.setThreadRight(h);
		}
		pre = h;
		System.out.println(h);
	}
}
/**
 * �ڵ�
 * @author MSI
 *
 */
class HearoNode1 {
	private int id;
	private String name; 
	private HearoNode1 left;     //ָ���������ָ��
	private HearoNode1 right;    //ָ���ҽ�����ָ��
	private int leftType = 0;    //0������ָ��ָ��������㣬1���������ָ��ָ�����ǰ�����
	private int rightType = 0;   //0������ָ��ָ������ҽ�㣬1���������ָ��ָ����Ǻ������	
	public HearoNode1(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HearoNode1 getLeft() {
		return left;
	}
	public void setLeft(HearoNode1 left) {
		this.leftType = 0;
		this.left = left;
	}
	public HearoNode1 getRight() {
		return right;
	}
	public void setRight(HearoNode1 right) {
		this.rightType = 0;
		this.right = right;
	}
	public int getLeftType() {
		return leftType;
	}
	public void setThreadLeft(HearoNode1 left) {
		this.left = left;
		this.leftType = 1;
	}
	public int getRightType() {
		return rightType;
	}
	public void setThreadRight(HearoNode1 right) {
		this.right = right;
		this.rightType = 1;
	}
	@Override
	public String toString() {
		return "HearoNode1 [id=" + id + ", name=" + name + "]";
	}
}