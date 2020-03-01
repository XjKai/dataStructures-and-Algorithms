package com.XJK.tree.binarySortTree;



/**
 * 二叉排序树 : 对于任何非叶子结点要求左子结点的值小于当前结点，右子结点的值大于当前结点
 * 从根结点看，左半部分的值全部小于右半部分的值
 * 二叉排序树 里面尽量不能有重复数字
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
	 * 向二叉排序树添加结点
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
	 * 中序遍历
	 */
	public void infixOrder() {
		if (rootNode ==null) {
			System.out.println("树为空");
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
	 * 找到headNode结点右支的最小结点,并用找到的结点替换headNode
	 * @param headNode    在情况3中是待删除的结点
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
		} else {    //headNode的右子节点的左边为空
			ParentNode.setRightNode(minNode.getRightNode());
		}
		minNode.setLeftNode(headNode.getLeftNode());
		minNode.setRightNode(headNode.getRightNode());
		return minNode;
	}

	/**
	 * 从二叉排序树中删除结点
	 * 分三种情况：
	 * 1.待删除的结点是叶子结点
	 * 2.待删除结点不是叶子结点，但其只有一个子结点
	 * 3.待删除结点不是叶子结点，且其有两个子结点
	 * 在以上三种情况中，若待删除结点是根结点，都要单独考虑
	 * @param value
	 */
	public void delete(int value) {
		
		if (rootNode == null) {
			System.out.println("二叉排序树为空");
			return;
		}
		Node targetNode = rootNode.searchNode(value);
		if (targetNode == null) {   //当前数没有该结点
			System.out.println("没有该节点");
			return;
		}
		//1.待删除的结点是叶子结点
		if (targetNode.getLeftNode() == null && targetNode.getRightNode() == null ) {//是叶子结点
			Node parentNode = rootNode.searchParentNode(value);//获取父结点
			if (parentNode == null) {  //是根结点，且根结点是叶子结点
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
		//3.待删除结点不是叶子结点，且其有两个子结点
		} else if (targetNode.getLeftNode() != null && targetNode.getRightNode() != null) {
			Node parentNode = rootNode.searchParentNode(value);//获取父结点

			if (parentNode != null && parentNode.getLeftNode() == targetNode) {  //父节点存在，且待删除结点在父节点的左边
				//用targetNode结点右结点的最小值或者targetNode结点左结点的最大值去替换
				parentNode.setLeftNode(delRightTreeMin(targetNode));
			} else if (parentNode != null && parentNode.getRightNode() == targetNode){    //父节点存在，且待删除结点在父节点的右边
				//用targetNode结点右结点的最小值或者targetNode结点左结点的最大值去替换
				parentNode.setRightNode(delRightTreeMin(targetNode));
			} else {     //父节点parentNode为空
				Node tempNode = rootNode;
				rootNode = delRightTreeMin(targetNode);
				rootNode.setLeftNode(tempNode.getLeftNode());
				rootNode.setRightNode(tempNode.getRightNode());
				return;
			}
			
		} else {
		//2.待删除结点不是叶子结点，但其只有一个子结点
			Node parentNode = rootNode.searchParentNode(value);//获取父结点

			if (targetNode.getLeftNode() != null) {  //targetNode的左边不为空
				if (parentNode == null) {  //如果目标结点是根结点
					rootNode = targetNode.getLeftNode();
					return;
				}
				if (parentNode.getLeftNode() != null) {
					parentNode.setLeftNode(targetNode.getLeftNode());
				} else {
					parentNode.setRightNode(targetNode.getLeftNode());
				}
			} else {
				if (parentNode == null) {  //如果目标结点是根结点
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
//结点
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
	 * 向排序二叉树添加结点
	 * @param value
	 */
	public void add(Node node) {
		if (node.value < this.value) {   //node的值比当前结点小
			if (this.leftNode == null) {  //当前结点的左孩子是为空
				this.leftNode = node;      //直接挂上去
				return;
			}
			this.leftNode.add(node);      //如果当前结点的左孩子存在，则递归向左孩子
		} else {                          //node值大于等于当前结点值
			if (this.rightNode == null) {
				this.rightNode = node;
				return;
			}
			this.rightNode.add(node);
		}
	}
	/**
	 * 查找value的位置
	 * @param node
	 * @return
	 */
	public Node searchNode(int value) {	
		if (this.value == value) {   //如果当前结点就是要找的值
			return this;
		}
		//由于二叉排序树的有序性，向左递归还是向右只有一种选择
		if (this.leftNode != null && value < this.value) {   //向左边查找
			return this.leftNode.searchNode(value);
		} 
		if (this.rightNode != null && value >= this.value) { //向右查找
			return this.rightNode.searchNode(value);
		}
		return null;               //没有找到
	}
	/**
	 * 查找value的父节点的位置
	 * @param value
	 * @return
	 */
	public Node searchParentNode(int value) {
		if ((this.leftNode != null && this.leftNode.value == value) ||(this.rightNode != null && this.rightNode.value  == value)) {
			return this;
		}
		//由于二叉排序树的有序性，向左递归还是向右只有一种选择
		if (this.leftNode != null && value < this.value) {     //向左查找
			return this.leftNode.searchParentNode(value);
		}
		if (this.rightNode != null && value >= this.value) {
			return this.rightNode.searchParentNode(value);
		}
		return null;      //没有找到
	}
	/**
	 * 中序遍历(排序二叉数中序遍历的结果就是从小到大的顺序)
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