package com.XJK.tree;
/**
 * 线索化二叉树
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
		
		
		//生成一个测试链表
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
	private HearoNode1 root = null;   //头结点
	//pre至关重要
	private HearoNode1 pre = null;  //在线索化二叉树时用于保存当前结点前一个结点的临时结点(这里的前后顺序是中(前/后)序遍历时的顺序)
	
	
	
	public ThreadedBTree(HearoNode1 root) {
		this.root = root;
	}
	
	//前序线索化二叉树
	public void threadedTreeByPreOrder() {
		threadedTreeByPreOrder(root);
	}
	//中序线索化二叉树
	public void threadedTreeByMidOrder() {
		threadedTreeByMidOrder(root);
	}
	//后序线索化二叉树
	public void threadedTreeByPostOrder() {
		threadedTreeByPostOrder(root);
	}
	/**
	 * 前序线索化二叉树
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
		if (h.getLeftType() != 1) {  //左递归
			threadedTreeByPreOrder(h.getLeft());
		}
		if (h.getRightType() != 1) {     //右递归
			threadedTreeByPreOrder(h.getRight());
		}
	}
	
	/**
	 * 中序线索化二叉树  (就是将线索话的语句放到中序遍历时输出的位置)
	 * @param h
	 */
	public void threadedTreeByMidOrder(HearoNode1 h) {
		
		if (h == null) { //当前结点为空，就不向下进行递归
			return;
		}
		if(h.getLeft() != null) {   //向左递归
			threadedTreeByMidOrder(h.getLeft());
		}
		
		//中序线索化(按中序的顺序检索每一个结点的左右结点是否为空，如果为空就将其指向前驱结点或者后驱结点)
		if(h.getLeft() == null) {      //线索化前驱结点是在当前结点的递归栈中修改
			h.setThreadLeft(pre);  //让当前结点的左指针指向前驱结点(如果当前结点的左指针为空的话)
		}
		if (pre != null && pre.getRight() ==null) {     // 线索化后趋结点是在待修改结点的下一个结点修改的
			//即当前递归里是修改上一个递归里结点的后趋结点(意会)
			pre.setThreadRight(h);//让前驱结点的右指针指向当前结点(如果前驱结点的右指针为空的话)
		}
		
		//!!!
		pre = h;    //每线索化一个结点以后，修改pre   (在遍历过程中它经过的顺序也是中序遍历的顺序)
		System.out.println(pre);
		
		if(h.getRight() != null) {   //向右递归
			threadedTreeByMidOrder(h.getRight());
		}
	}
	/**
	 * 后续线索化二叉树
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
 * 节点
 * @author MSI
 *
 */
class HearoNode1 {
	private int id;
	private String name; 
	private HearoNode1 left;     //指向左结点的左指针
	private HearoNode1 right;    //指向右结点的右指针
	private int leftType = 0;    //0代表左指针指向的是左结点，1代表的是左指针指向的是前驱结点
	private int rightType = 0;   //0代表右指针指向的是右结点，1代表的是右指针指向的是后趋结点	
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