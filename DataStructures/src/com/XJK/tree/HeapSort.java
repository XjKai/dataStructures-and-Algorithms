package com.XJK.tree;

import java.util.Arrays;
import java.util.Calendar;

/**
 * 堆排序
 * @author MSI
 *8000万数据 ： 1s
 */
public class HeapSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] t = new int[80000];
		for (int i = 0; i < t.length; i++) {
			t[i] = (int)(Math.random()*800000);
		}
		int[] arr = {4,6,8,5,9};
		getTimr();
		heapSort(t);
		getTimr();
		//System.out.println(Arrays.toString(t));

	}
	
	
	
	
	
	/**
	 * 堆排序
	 * @param arr
	 */
	public static void heapSort(int[] arr) {
		//先将数组变成一个大顶堆
		FormBigTopHeap(arr);
		for (int i = 0; i < arr.length - 1; i++) {    //length个元素，需要交换调整length - 1次
			//交换
			exchange(arr,0,arr.length - i - 1);
			//调整(因为除了堆顶，后面的结点都符合大顶堆的特点，所以只需要从0开始调整)
			adjustNode(arr,0 , arr.length - i - 1);
		}
		
	}
	/**
	 * 从最后一个非叶子结点(arr.length/2 - 1)开始，从右至左、从下至上构成大顶堆
	 * @param arr  数组
	 * @param i    当前结点位置
	 */
	public static void FormBigTopHeap(int[] arr) {
		//从右至左、从下至上构成大顶堆
		for (int j = (arr.length/2 - 1); j >= 0; j--) {
			adjustNode(arr,j ,arr.length);
		}
	}
	/**
	 * 调整第i个结点，使i结点的所有子结点(到len之前)都满足大顶堆,这里定义了一个len是因为后面在交换后，尾部交换的那个值已经不参与大顶堆的构成
	 * @param arr
	 * @param i
	 */
	public static void adjustNode(int[] arr, int i, int len) {
		if(i*2 + 1 < len && i*2 + 2 < len) {    //i结点的左右结点都存在
			if(arr[i*2 + 1] > arr[i] && arr[i*2 + 1] > arr[i*2 + 2]) {    //左结点最大
				exchange(arr, i, i*2 + 1);
				//由于i左结点的值被修改了(变小了 )，如果i左结点不是叶子结点，需要递归判断i左结点的当前值是否大于i左结点的子结点
				if (i*2 + 1 <= (arr.length/2 - 1)) {   //i的左结点不是叶子结点
					adjustNode(arr, i*2 + 1, len);
				}
				
			} else if(arr[i*2 + 2] > arr[i] && arr[i*2 + 2] > arr[i*2 + 1]) {    //右结点最大
				exchange(arr, i, i*2 + 2);
				//由于i右结点的值被修改了(变小了 )，如果i右结点不是叶子结点，需要递归判断i右结点的当前值是否大于i右结点的子结点
				if (i*2 + 2 <= (arr.length/2 - 1)) {   //i的右结点不是叶子结点
					adjustNode(arr, i*2 + 2, len);
				}
			}
		} else if(i*2 + 1 < len) {        //i结点的左结点存在     
			if(arr[i*2 + 1] > arr[i]) {    //i的左结点值大于i结点的值
				exchange(arr, i, i*2 + 1);
				//由于i左结点的值被修改了(变小了 )，如果i左结点不是叶子结点，需要递归判断i左结点的当前值是否大于i左结点的子结点
				if (i*2 + 1 <= (arr.length/2 - 1)) {   //i的左结点不是叶子结点
					adjustNode(arr, i*2 + 1, len);
				}
			}
		}
		//由于是完全二叉树，如果i的左结点不存在，那右结点一定不存在
	}
	/**
	 * 交换数组两个位置的值
	 * @param arr
	 * @param i
	 * @param j
	 */
	public static void exchange(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	/**
	 * 计时
	 */
	public static void getTimr() {
		Calendar c = Calendar.getInstance();
        int mm = c.get(Calendar.MINUTE);
        int ss = c.get(Calendar.SECOND);
        int ms = c.get(Calendar.MILLISECOND);

		System.out.println("分:"+mm+" 秒:"+ss+" 毫秒:"+ms);
	}
}
