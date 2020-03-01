package com.XJK.tree;
/**
 * 二叉树
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
 *  二叉树
 * @author MSI
 *
 */
class BinaryTree {
	public HearoNode root;   //根节点
	
	
	/**
	 * 前序遍历
	 */	
	private void preRecursion(HearoNode h) {  //用于递归
		System.out.println(h);      //先输出根节点
		if(h.getLeft() != null) {      //遍历左节点
			preRecursion(h.getLeft());
		}
		if(h.getRight() != null) {     //遍历右节点
			preRecursion(h.getRight());
		}
	}
	public void preOrder() {    
		if(this.root != null) {
			preRecursion(root);
		} else {
			System.out.println("树为空");
		}
	}
	
	
	/**
	 * 中序遍历
	 */	
	private void midRecursion(HearoNode h) {  //用于递归
		if(h.getLeft() != null) {      //遍历左节点
			midRecursion(h.getLeft());
		}
		System.out.println(h);      //中间输出根节点
		if(h.getRight() != null) {     //遍历右节点
			midRecursion(h.getRight());
		}
	}
	public void midOrder() {    
		if(this.root != null) {
			midRecursion(root);
		} else {
			System.out.println("树为空");
		}
	}
	
	/**
	 * 后序遍历
	 */	
	private void postRecursion(HearoNode h) {  //用于递归
		if(h.getLeft() != null) {      //遍历左节点
			postRecursion(h.getLeft());
		}
		if(h.getRight() != null) {     //遍历右节点
			postRecursion(h.getRight());
		}
		System.out.println(h);      //后输出根节点
	}
	public void postOrder() {    
		if(this.root != null) {
			postRecursion(root);
		} else {
			System.out.println("树为空");
		}
	}
	
	
	
	/**
	 *  前序查找
	 */
	private HearoNode preSearchRecursion(int no, HearoNode h) {   //用于递归
		HearoNode temp = null;
		System.out.println("xxxxxxx");
		if(h.getId() == no) {
			return h;
		}
		if(h.getLeft() != null) {
			temp = preSearchRecursion(no, h.getLeft());
		}
		if(temp != null) {    //找到后 递归如果回溯到这里就不会在执行后面的代码了。直接一层层回溯返回退出
			return temp;
		}
		if(h.getRight() != null) {
			temp = preSearchRecursion(no, h.getRight());
		}
		return temp;    //这个temp是来自右树找的结果
	}
	public HearoNode preOrderSearch(int no) {
		if(root != null) {
			return preSearchRecursion(no, root);
		} else {
			System.out.println("树为空");
			return null;
		}
	}
	
	
	/**
	 * 中序查找
	 */
	private HearoNode midSearchRecursion(int no, HearoNode h) {     //用于递归
		HearoNode temp = null;
		if(h.getLeft() != null) {
			temp = midSearchRecursion(no, h.getLeft());
		}
		if(temp != null) {    //找到了，不再递归
			return temp;
		}
		System.out.println("xxxxxxx");
		if(h.getId() == no) {
			return h;
		}
		if (h.getRight() != null) {
			temp = midSearchRecursion(no, h.getRight());
		}
		return temp;      //这个temp是来自右树找的结果
	}
	public HearoNode midOrderSearch(int no) {
		if(root != null) {
			return midSearchRecursion(no, root);
		} else {
			System.out.println("树为空");
			return null;
		}
	}
	
	/**
	 * 后序查找
	 */
	private HearoNode postSearchRecursion(int no, HearoNode h) {
		HearoNode temp = null;
		if(h.getLeft() != null) {
			temp = postSearchRecursion(no, h.getLeft());
		}
		if(temp != null) {    //找到了，不再递归
			return temp;
		}
		if (h.getRight() != null) {
			temp = postSearchRecursion(no, h.getRight());
		}
		if(temp != null) {  //找到了，不再判断
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
			System.out.println("树为空");
			return null;
		}
	}
	/**
	 * 
	 * @param no
	 */
	public void delete(int no) {
		if (root == null) {   //根结点为空
			System.out.println("树为空");
			return;
		}
		//根结点要单独判断，因为删除根结点不用从他的上一个结点删除
		if (root.getId() == no) {  //如果根结点就是要删除的结点
			root = null;
			return;
		}
		//调用递归函数判断后面的结点
		root.simpleDelete(no);
	}
}
/**
 * 节点
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
	
	public void simpleDelete(int no) {     //由于单向性，所以删除要找到待删除结点的上一个节点
		if (this.left != null && this.left.id == no) {    //先看当前结点的左边是不是
			this.left = null;
			return;    //删除后退出递归
		}
		if (this.right != null && this.right.id == no) {   //左结点不是再看右结点是不是
			this.right = null;
			return;	   //删除后退出递归
		}
		//上面两步没有找到
		if (this.left != null) {         //递归到当前结点的左结点
			this.left.simpleDelete(no);
		}
		if (this.right != null) {        //递归到当前结点的右结点
			this.right.simpleDelete(no);
		}
	}
	
}