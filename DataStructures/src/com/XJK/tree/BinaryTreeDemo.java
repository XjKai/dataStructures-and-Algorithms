package com.XJK.tree;
/**
 * ������
 * @author MSI
 *
 */
public class BinaryTreeDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HearoNode h1 = new HearoNode(1, "1");
		HearoNode h2 = new HearoNode(2, "2");
		HearoNode h3 = new HearoNode(3, "3");
		HearoNode h4 = new HearoNode(4, "4");
		HearoNode h5 = new HearoNode(5, "5");
		
		h1.setLeft(h2);
		h1.setRight(h3);
		h3.setLeft(h5);
		h3.setRight(h4);
		
		BinaryTree b = new BinaryTree();
		b.root = h1;
		
		b.preOrder();
		b.delete(3);
		b.preOrder();
//		System.out.println();
//		b.midOrder();
//		System.out.println();
//		b.postOrder();
//		
//		System.out.println(b.preOrderSearch(5));	
//		System.out.println(b.midOrderSearch(5));
//		System.out.println(b.postOrderSearch(5));
	}

}



/**
 *  ������
 * @author MSI
 *
 */
class BinaryTree {
	public HearoNode root;   //���ڵ�
	
	
	/**
	 * ǰ�����
	 */	
	private void preRecursion(HearoNode h) {  //���ڵݹ�
		System.out.println(h);      //��������ڵ�
		if(h.getLeft() != null) {      //������ڵ�
			preRecursion(h.getLeft());
		}
		if(h.getRight() != null) {     //�����ҽڵ�
			preRecursion(h.getRight());
		}
	}
	public void preOrder() {    
		if(this.root != null) {
			preRecursion(root);
		} else {
			System.out.println("��Ϊ��");
		}
	}
	
	
	/**
	 * �������
	 */	
	private void midRecursion(HearoNode h) {  //���ڵݹ�
		if(h.getLeft() != null) {      //������ڵ�
			midRecursion(h.getLeft());
		}
		System.out.println(h);      //�м�������ڵ�
		if(h.getRight() != null) {     //�����ҽڵ�
			midRecursion(h.getRight());
		}
	}
	public void midOrder() {    
		if(this.root != null) {
			midRecursion(root);
		} else {
			System.out.println("��Ϊ��");
		}
	}
	
	/**
	 * �������
	 */	
	private void postRecursion(HearoNode h) {  //���ڵݹ�
		if(h.getLeft() != null) {      //������ڵ�
			postRecursion(h.getLeft());
		}
		if(h.getRight() != null) {     //�����ҽڵ�
			postRecursion(h.getRight());
		}
		System.out.println(h);      //��������ڵ�
	}
	public void postOrder() {    
		if(this.root != null) {
			postRecursion(root);
		} else {
			System.out.println("��Ϊ��");
		}
	}
	
	
	
	/**
	 *  ǰ�����
	 */
	private HearoNode preSearchRecursion(int no, HearoNode h) {   //���ڵݹ�
		HearoNode temp = null;
		System.out.println("xxxxxxx");
		if(h.getId() == no) {
			return h;
		}
		if(h.getLeft() != null) {
			temp = preSearchRecursion(no, h.getLeft());
		}
		if(temp != null) {    //�ҵ��� �ݹ�������ݵ�����Ͳ�����ִ�к���Ĵ����ˡ�ֱ��һ�����ݷ����˳�
			return temp;
		}
		if(h.getRight() != null) {
			temp = preSearchRecursion(no, h.getRight());
		}
		return temp;    //���temp�����������ҵĽ��
	}
	public HearoNode preOrderSearch(int no) {
		if(root != null) {
			return preSearchRecursion(no, root);
		} else {
			System.out.println("��Ϊ��");
			return null;
		}
	}
	
	
	/**
	 * �������
	 */
	private HearoNode midSearchRecursion(int no, HearoNode h) {     //���ڵݹ�
		HearoNode temp = null;
		if(h.getLeft() != null) {
			temp = midSearchRecursion(no, h.getLeft());
		}
		if(temp != null) {    //�ҵ��ˣ����ٵݹ�
			return temp;
		}
		System.out.println("xxxxxxx");
		if(h.getId() == no) {
			return h;
		}
		if (h.getRight() != null) {
			temp = midSearchRecursion(no, h.getRight());
		}
		return temp;      //���temp�����������ҵĽ��
	}
	public HearoNode midOrderSearch(int no) {
		if(root != null) {
			return midSearchRecursion(no, root);
		} else {
			System.out.println("��Ϊ��");
			return null;
		}
	}
	
	/**
	 * �������
	 */
	private HearoNode postSearchRecursion(int no, HearoNode h) {
		HearoNode temp = null;
		if(h.getLeft() != null) {
			temp = postSearchRecursion(no, h.getLeft());
		}
		if(temp != null) {    //�ҵ��ˣ����ٵݹ�
			return temp;
		}
		if (h.getRight() != null) {
			temp = postSearchRecursion(no, h.getRight());
		}
		if(temp != null) {  //�ҵ��ˣ������ж�
			return temp;
		}
		System.out.println("xxxxxxx");
		if(h.getId() == no) {
			return h;
		}
		return temp;
	}
	public HearoNode postOrderSearch(int no) {
		if(root != null) {
			return postSearchRecursion(no, root);
		} else {
			System.out.println("��Ϊ��");
			return null;
		}
	}
	/**
	 * 
	 * @param no
	 */
	public void delete(int no) {
		if (root == null) {   //�����Ϊ��
			System.out.println("��Ϊ��");
			return;
		}
		//�����Ҫ�����жϣ���Ϊɾ������㲻�ô�������һ�����ɾ��
		if (root.getId() == no) {  //�����������Ҫɾ���Ľ��
			root = null;
			return;
		}
		//���õݹ麯���жϺ���Ľ��
		root.simpleDelete(no);
	}
}
/**
 * �ڵ�
 * @author MSI
 *
 */
class HearoNode {
	private int id;
	private String name;
	private HearoNode left;
	private HearoNode right;
	public HearoNode(int id, String name) {
		super();
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
	public HearoNode getLeft() {
		return left;
	}
	public void setLeft(HearoNode left) {
		this.left = left;
	}
	public HearoNode getRight() {
		return right;
	}
	public void setRight(HearoNode right) {
		this.right = right;
	}
	@Override
	public String toString() {
		return "HearoNode [id=" + id + ", name=" + name + "]";
	}
	
	public void simpleDelete(int no) {     //���ڵ����ԣ�����ɾ��Ҫ�ҵ���ɾ��������һ���ڵ�
		if (this.left != null && this.left.id == no) {    //�ȿ���ǰ��������ǲ���
			this.left = null;
			return;    //ɾ�����˳��ݹ�
		}
		if (this.right != null && this.right.id == no) {   //���㲻���ٿ��ҽ���ǲ���
			this.right = null;
			return;	   //ɾ�����˳��ݹ�
		}
		//��������û���ҵ�
		if (this.left != null) {         //�ݹ鵽��ǰ��������
			this.left.simpleDelete(no);
		}
		if (this.right != null) {        //�ݹ鵽��ǰ�����ҽ��
			this.right.simpleDelete(no);
		}
	}
	
}