package com.XJK.tree.AVLtree;
/**
 * ƽ�������(������������߶Ȳ�ƽ��) / ƽ����������� / AVL��
 * ƽ���������ǰ���Ƕ�������������ƽ�����������---˫��ת--->ƽ�������
 * ͬһ�����ݵ�����������������кܶ���(������Ĳ�ͬ����ͬ)��ƽ�����������ʹ��������������˸߶Ȳ����1
 * @author XJK
 *
 */
public class AVLtreeDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {14,15,7,6,8,9,10,11,12,13};
		AVLtree avLtree = new AVLtree();
		for (int i = 0; i < arr.length; i++) {
			avLtree.add(new Node(arr[i]));
		}
		System.out.println("leftHeight:" + avLtree.rootNode.getLeftNode().height());
		System.out.println("rightHeight:" + avLtree.rootNode.getRightNode().height());
	}

}


/**
 * ƽ�������
 * @author MSI
 *
 */
class AVLtree {
	public Node rootNode;
	
	
	/**
	 * ��ƽ���������ӽ��
	 * @param node
	 */
	public void add(Node node) {
		if (rootNode == null) {
			rootNode = node;
		} else {
			rootNode.add(node);
		}
	}
	
	
}


class Node {
	private int value;
	private Node leftNode;
	private Node rightNode;
	
	public Node(int value) {
		super();
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Node getLeftNode() {
		return leftNode;
	}
	public void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}
	public Node getRightNode() {
		return rightNode;
	}
	public void setRightNode(Node rightNode) {
		this.rightNode = rightNode;
	}
	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}
	
	/**
	 * ���ն�����������ƽ��������ķ�����ӽ��
	 * @param node
	 */
	public void add(Node node) {
		if (node.value < this.value) {
			if (this.leftNode == null) {
				this.leftNode = node;
			} else {
				this.leftNode.add(node);	
			}
		}else {
			if (this.rightNode == null) {
					this.rightNode = node;		
			} else {
				this.rightNode.add(node);
			}
		}		
		
		//ÿ�����һ�����󣬾����˳��ݹ�Ĺ������ж��Ƿ���֦������ƽ�������(���ҽ��ĸ߶Ȳ����1)
		//��ǰ������֦�߶ȱ���֦�ĸ߶Ȳ����1����Ҫ����
		if (this.leftHeight() - this.rightHeight() > 1) {
			//��������ǰ�����жϵ�ǰ���������������߶��Ƿ�С�ڵ����������ĸ߶ȣ������ǾͶ������һ������ ������
			if (this.leftNode.rightHeight() > this.leftNode.leftHeight()) {
				this.leftNode.leftRotate();
			}
			this.rightRotate();
		}
		//��ǰ������֦�߶ȱ���֦�ĸ߶Ȳ����1����Ҫ����
		if (this.rightHeight() - this.leftHeight() > 1) {
			//��������ǰ�����жϵ�ǰ����ҽ����������ĸ߶��Ƿ�С�ڵ����������ĸ߶ȣ������Ǿ������һ������������
			if (this.rightNode.leftHeight() > this.rightNode.rightHeight()) {
				this.rightNode.rightRotate();
			}
			this.leftRotate();
		}
	}
	/**
	 * ��õ�ǰ���ĸ߶�
	 * @return
	 */
	public int height() {
		return Math.max(this.leftNode == null ? 0 : this.leftNode.height(), this.rightNode == null ? 0 : this.rightNode.height()) + 1;
	}
	/**
	 * ��õ�ǰ�������ĸ߶�
	 * @return
	 */
	public int leftHeight() {
		return leftNode == null ? 0 : leftNode.height();
	}
	/**
	 * ��õ�ǰ����ҽ��ĸ߶�
	 * @return
	 */
    public int rightHeight() {
		return rightNode == null ? 0 : rightNode.height();
	}
	/**
	 * �Ե�ǰ���Ϊ������������(��ת�Ĳ����ǹ̶���)��
	 * ��ת����Ȼ��������С��������
	 * �����󣬵�ǰ�������Ҹ߶Ȳ��뵱ǰ����ҽ�����֦�йأ�
	 */
	public void leftRotate() {
		//1.����һ��ֵΪ��ǰ���ֵ���½��
		Node newNode = new Node(this.value);
		//2.�½�����ָ��ָ��ǰ��������
		newNode.leftNode = this.leftNode;
		//3.�½�����ָ��ָ��ǰ����ҽ������㣨��Ϊ��ǰ�����ҽ��Ҫ��Ϊ�µĸ���㣬ֻ�е�ǰ�����ҽ�����֧���õ����ȥ��
	    newNode.rightNode = this.rightNode.leftNode;
	    //4.�ѵ�ǰ����ֵ��Ϊ��ǰ����ҽ���ֵ
	    this.value = this.rightNode.value;
	    //5.�ѵ�ǰ������ָ��ָ��ǰ����ҽ����ҽ��
	    this.rightNode = this.rightNode.rightNode;
	    //6.��ǰ������ָ��ָ���½��
	    this.leftNode = newNode;
	}   
	/**
	 * �Ե�ǰ���Ϊ������������(��ת�Ĳ����ǹ̶���)��
	 * ��ת����Ȼ��������С��������
	 * �����󣬵�ǰ�������Ҹ߶Ȳ��뵱ǰ����������֦�йأ�
	 */
	public void rightRotate() {
		//1.����һ��ֵΪ��ǰ���ֵ���½��
		Node newNode = new Node(this.value);
		//2.�½�����ָ��ָ��ǰ�����ҽ��
		newNode.rightNode = this.rightNode;
		//3.�½�����ָ��ָ��ǰ���������ҽ��
		newNode.leftNode = this.leftNode.rightNode;
		//4.����ǰ����ֵ��Ϊ��ǰ��������ֵ
		this.value = this.leftNode.value;
		//5.��ǰ������ָ��ָ��ǰ������������
		this.leftNode = this.leftNode.leftNode;
		//6.��ǰ������ָ��ָ���½��
		this.rightNode = newNode;	
	}
	
}