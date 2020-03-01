package com.XJK.recursion;


public class QueenTeacher {
	int MAX = 8;   //最大皇后数
	//用一维数组表示存放一种可能
	int[] arr = new int[MAX];      //int[2] = 3.   表示放在第1行第2列的皇后
	int count = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QueenTeacher queenTeacher = new QueenTeacher();
		queenTeacher.findPoint(0);     //相当于用了8个for循环
	}

	/**
	 * 
	 * @param n   检查第几个皇后
	 * @return
	 */
	private  boolean ifRequired(int n) {
		//不用判断行，因为在该行如果有满足条件的位置就会递归到下一行去了。
		for(int i = 0; i < n; i++) {   //判断该位置是否与数组里已经存在的点冲突。
			if (arr[n] == arr[i] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {      //在一维数组里判断是否同列，同斜线
				return false;
			}
		}
		return true;
	}
	/**
	 * 
	 * @param n
	 */
	private  void findPoint(int n) {
		if(n == MAX) {            //回溯条件
			count++;
			System.out.println("解法: "+count);
			System.out.println();
			show(arr);
			System.out.println();
			return;
		}
		for (int i = 0; i < MAX; i++) {
			arr[n] = i;
			if (ifRequired(n)) {   //满足条件就递归
				findPoint(n+1);
			}
		}
		
	}
	private  void show(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "  ");
		}
		System.out.println();
	}
}
