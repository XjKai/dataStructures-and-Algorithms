package com.XJK.tree.AVLtree;
/**
 * 平衡二叉树(解决左右子树高度不平衡) / 平衡二叉搜索树 / AVL树
 * 平衡二叉树的前提是二叉排序树，不平衡排序二叉树---双旋转--->平衡二叉树
 * 同一组数据的排序二叉树的树形有很多种(因根结点的不同而不同)，平衡二叉树就是使排序二叉树的两端高度差不大于1
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
 * 平衡二叉树
 * @author MSI
 *
 */
class AVLtree {
	public Node rootNode;
	
	
	/**
	 * 向平衡二叉树添加结点
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
	 * 按照二叉排序树和平衡二叉树的方法添加结点
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
		
		//每添加完一个结点后，就在退出递归的过程中判断是否有枝不满足平衡二叉树(左右结点的高度差不大于1)
		//当前结点的左枝高度比右枝的高度差大于1，需要右旋
		if (this.leftHeight() - this.rightHeight() > 1) {
			//进行右旋前，先判断当前结点左结点的右子树高度是否小于等于左子树的高度，若不是就对其进行一次左旋 ！！！
			if (this.leftNode.rightHeight() > this.leftNode.leftHeight()) {
				this.leftNode.leftRotate();
			}
			this.rightRotate();
		}
		//当前结点的右枝高度比左枝的高度差大于1，需要左旋
		if (this.rightHeight() - this.leftHeight() > 1) {
			//进行左旋前，先判断当前结点右结点的左子树的高度是否小于等于右子树的高度，若不是就其进行一次右旋！！！
			if (this.rightNode.leftHeight() > this.rightNode.rightHeight()) {
				this.rightNode.rightRotate();
			}
			this.leftRotate();
		}
	}
	/**
	 * 获得当前结点的高度
	 * @return
	 */
	public int height() {
		return Math.max(this.leftNode == null ? 0 : this.leftNode.height(), this.rightNode == null ? 0 : this.rightNode.height()) + 1;
	}
	/**
	 * 获得当前结点左结点的高度
	 * @return
	 */
	public int leftHeight() {
		return leftNode == null ? 0 : leftNode.height();
	}
	/**
	 * 获得当前结点右结点的高度
	 * @return
	 */
    public int rightHeight() {
		return rightNode == null ? 0 : rightNode.height();
	}
	/**
	 * 以当前结点为根结点进行左旋(旋转的步骤是固定的)；
	 * 旋转后仍然满足左树小于右树；
	 * 左旋后，当前结点的左右高度差与当前结点右结点的左枝有关；
	 */
	public void leftRotate() {
		//1.创建一个值为当前结点值的新结点
		Node newNode = new Node(this.value);
		//2.新结点的左指针指向当前结点的左结点
		newNode.leftNode = this.leftNode;
		//3.新结点的右指针指向当前结点右结点的左结点（因为当前结点的右结点要成为新的根结点，只有当前结点的右结点的左支能拿到左边去）
	    newNode.rightNode = this.rightNode.leftNode;
	    //4.把当前结点的值换为当前结点右结点的值
	    this.value = this.rightNode.value;
	    //5.把当前结点的右指针指向当前结点右结点的右结点
	    this.rightNode = this.rightNode.rightNode;
	    //6.当前结点的左指针指向新结点
	    this.leftNode = newNode;
	}   
	/**
	 * 以当前结点为根结点进行右旋(旋转的步骤是固定的)；
	 * 旋转后仍然满足左树小于右树；
	 * 右旋后，当前结点的左右高度差与当前结点左结点的右枝有关；
	 */
	public void rightRotate() {
		//1.创建一个值为当前结点值的新结点
		Node newNode = new Node(this.value);
		//2.新结点的右指针指向当前结点的右结点
		newNode.rightNode = this.rightNode;
		//3.新结点的左指针指向当前结点左结点的右结点
		newNode.leftNode = this.leftNode.rightNode;
		//4.将当前结点的值改为当前结点左结点的值
		this.value = this.leftNode.value;
		//5.当前结点的左指针指向当前结点左结点的左结点
		this.leftNode = this.leftNode.leftNode;
		//6.当前结点的右指针指向新结点
		this.rightNode = newNode;	
	}
	
}