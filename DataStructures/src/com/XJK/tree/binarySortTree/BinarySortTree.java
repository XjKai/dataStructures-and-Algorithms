package com.XJK.tree.binarySortTree;



/**
 * ���������� : �����κη�Ҷ�ӽ��Ҫ�����ӽ���ֵС�ڵ�ǰ��㣬���ӽ���ֵ���ڵ�ǰ���
 * �Ӹ���㿴����벿�ֵ�ֵȫ��С���Ұ벿�ֵ�ֵ
 * ���������� ���澡���������ظ�����
 * @author MSI
 *
 */
public class BinarySortTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {1,1,1};
		Tree tree = new Tree();
		for (int i = 0; i < arr.length; i++) {
			if (tree.search(arr[i]) == null) {
				tree.add(new Node(arr[i]));
			}
			
		}
		
		tree.infixOrder();



		System.out.println();
		tree.infixOrder();
		
		System.out.println();
		System.out.println(tree.rootNode);
		
	}

}
class Tree{
	public Node rootNode;
	
	/**
	 * �������������ӽ��
	 * @param node
	 */
	public void add(Node node) {
		if (rootNode == null) {
			rootNode = node;
		} else {
			rootNode.add(node);
		}
	}
	/**
	 * �������
	 */
	public void infixOrder() {
		if (rootNode ==null) {
			System.out.println("��Ϊ��");
		} else {
			rootNode.infixOrder();
		}
	}
	public Node search(int value) {
		if (rootNode == null) {
			return null;
		}
		return rootNode.searchNode(value);
	}
	
	/**
	 * 
	 * �ҵ�headNode�����֧����С���,�����ҵ��Ľ���滻headNode
	 * @param headNode    �����3���Ǵ�ɾ���Ľ��
	 * @return
	 */
	public Node delRightTreeMin(Node headNode) {
		Node ParentNode =  headNode;
		Node minNode = headNode.getRightNode();
		if (minNode.getLeftNode() != null) {
			while (minNode.getLeftNode() != null) {
				ParentNode = minNode;
				minNode = minNode.getLeftNode();
			}	
			ParentNode.setLeftNode(minNode.getRightNode());
		} else {    //headNode�����ӽڵ�����Ϊ��
			ParentNode.setRightNode(minNode.getRightNode());
		}
		minNode.setLeftNode(headNode.getLeftNode());
		minNode.setRightNode(headNode.getRightNode());
		return minNode;
	}

	/**
	 * �Ӷ�����������ɾ�����
	 * �����������
	 * 1.��ɾ���Ľ����Ҷ�ӽ��
	 * 2.��ɾ����㲻��Ҷ�ӽ�㣬����ֻ��һ���ӽ��
	 * 3.��ɾ����㲻��Ҷ�ӽ�㣬�����������ӽ��
	 * ��������������У�����ɾ������Ǹ���㣬��Ҫ��������
	 * @param value
	 */
	public void delete(int value) {
		
		if (rootNode == null) {
			System.out.println("����������Ϊ��");
			return;
		}
		Node targetNode = rootNode.searchNode(value);
		if (targetNode == null) {   //��ǰ��û�иý��
			System.out.println("û�иýڵ�");
			return;
		}
		//1.��ɾ���Ľ����Ҷ�ӽ��
		if (targetNode.getLeftNode() == null && targetNode.getRightNode() == null ) {//��Ҷ�ӽ��
			Node parentNode = rootNode.searchParentNode(value);//��ȡ�����
			if (parentNode == null) {  //�Ǹ���㣬�Ҹ������Ҷ�ӽ��
				rootNode = null;
				return;
			}
			if (parentNode != null) {   
				if (parentNode.getLeftNode() !=null && parentNode.getLeftNode() == targetNode) {
					parentNode.setLeftNode(null);
					return;
				}
				if (parentNode.getRightNode() != null && parentNode.getRightNode() == targetNode) {
					parentNode.setRightNode(null);
					return;
				}
			}
		//3.��ɾ����㲻��Ҷ�ӽ�㣬�����������ӽ��
		} else if (targetNode.getLeftNode() != null && targetNode.getRightNode() != null) {
			Node parentNode = rootNode.searchParentNode(value);//��ȡ�����

			if (parentNode != null && parentNode.getLeftNode() == targetNode) {  //���ڵ���ڣ��Ҵ�ɾ������ڸ��ڵ�����
				//��targetNode����ҽ�����Сֵ����targetNode�����������ֵȥ�滻
				parentNode.setLeftNode(delRightTreeMin(targetNode));
			} else if (parentNode != null && parentNode.getRightNode() == targetNode){    //���ڵ���ڣ��Ҵ�ɾ������ڸ��ڵ���ұ�
				//��targetNode����ҽ�����Сֵ����targetNode�����������ֵȥ�滻
				parentNode.setRightNode(delRightTreeMin(targetNode));
			} else {     //���ڵ�parentNodeΪ��
				Node tempNode = rootNode;
				rootNode = delRightTreeMin(targetNode);
				rootNode.setLeftNode(tempNode.getLeftNode());
				rootNode.setRightNode(tempNode.getRightNode());
				return;
			}
			
		} else {
		//2.��ɾ����㲻��Ҷ�ӽ�㣬����ֻ��һ���ӽ��
			Node parentNode = rootNode.searchParentNode(value);//��ȡ�����

			if (targetNode.getLeftNode() != null) {  //targetNode����߲�Ϊ��
				if (parentNode == null) {  //���Ŀ�����Ǹ����
					rootNode = targetNode.getLeftNode();
					return;
				}
				if (parentNode.getLeftNode() != null) {
					parentNode.setLeftNode(targetNode.getLeftNode());
				} else {
					parentNode.setRightNode(targetNode.getLeftNode());
				}
			} else {
				if (parentNode == null) {  //���Ŀ�����Ǹ����
					rootNode = targetNode.getRightNode();
					return;
				}
				if (parentNode.getLeftNode() != null) {
					parentNode.setLeftNode(targetNode.getRightNode());
				} else {
					parentNode.setRightNode(targetNode.getRightNode());
				}
			}
		}


	}

}
//���
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
	 * �������������ӽ��
	 * @param value
	 */
	public void add(Node node) {
		if (node.value < this.value) {   //node��ֵ�ȵ�ǰ���С
			if (this.leftNode == null) {  //��ǰ����������Ϊ��
				this.leftNode = node;      //ֱ�ӹ���ȥ
				return;
			}
			this.leftNode.add(node);      //�����ǰ�������Ӵ��ڣ���ݹ�������
		} else {                          //nodeֵ���ڵ��ڵ�ǰ���ֵ
			if (this.rightNode == null) {
				this.rightNode = node;
				return;
			}
			this.rightNode.add(node);
		}
	}
	/**
	 * ����value��λ��
	 * @param node
	 * @return
	 */
	public Node searchNode(int value) {	
		if (this.value == value) {   //�����ǰ������Ҫ�ҵ�ֵ
			return this;
		}
		//���ڶ����������������ԣ�����ݹ黹������ֻ��һ��ѡ��
		if (this.leftNode != null && value < this.value) {   //����߲���
			return this.leftNode.searchNode(value);
		} 
		if (this.rightNode != null && value >= this.value) { //���Ҳ���
			return this.rightNode.searchNode(value);
		}
		return null;               //û���ҵ�
	}
	/**
	 * ����value�ĸ��ڵ��λ��
	 * @param value
	 * @return
	 */
	public Node searchParentNode(int value) {
		if ((this.leftNode != null && this.leftNode.value == value) ||(this.rightNode != null && this.rightNode.value  == value)) {
			return this;
		}
		//���ڶ����������������ԣ�����ݹ黹������ֻ��һ��ѡ��
		if (this.leftNode != null && value < this.value) {     //�������
			return this.leftNode.searchParentNode(value);
		}
		if (this.rightNode != null && value >= this.value) {
			return this.rightNode.searchParentNode(value);
		}
		return null;      //û���ҵ�
	}
	/**
	 * �������(�����������������Ľ�����Ǵ�С�����˳��)
	 */
	public void infixOrder() {
		if (this.leftNode != null) {
			this.leftNode.infixOrder();
		}
		System.out.println(this);
		if (this.rightNode != null) {
			this.rightNode.infixOrder();
		}
	}
}