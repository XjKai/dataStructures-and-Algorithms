package com.XJK.sort;

import java.util.Arrays;
import java.util.Calendar;

/**6
 * 归并排序
 * @author MSI
 * 先分解再合并(分治排序)
 * 思想：分解>合并。向将数据递归分开，分到每段只有一个元素为止，然后再回溯的过程中去进行合并（利用临时数组有序的合并）
 * 分治思想，将数据分到各个栈，然后求解这些小问题
 * 8万数据：35ms
 * 80万数据：160ms
 * 800万数据：1~2s
 * 4000万数据：8s
 * 8000万数据：17s
 */
public class MergedSort {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[] t = {5,10,3,0,-1,0,6};
		int[] t = new int[4000000];
		for (int i = 0; i < t.length; i++) {
			t[i] = (int)(Math.random()*40000000);
		}
		int[] temp = new int[t.length];
		getTimr();
		mergedSort(t,0,t.length-1,temp);
		getTimr();
		//System.out.println(Arrays.toString(temp));
	}
	/**
	 *  将这一段分为两段，然后进行合并
	 * @param arr    //原始数组
	 * @param left   //该段起始位置
	 * @param right  //该段终止位置
	 * @param temp   //临时数组
	 */
	public static void mergedSort(int[] arr, int left, int right, int[] temp) {
		
		//分（二分法）
		int mid = (right + left)/2;   //分成两段的中中界线
		//左边一段：left~mid
		//右边一段：mid+1~right
		//左递归
		if(left != mid) {   //该段数据个数大于一个，就继续进行"分"
			mergedSort(arr, left, mid, temp);
		}
		//右递归
		if (mid+1 != right) {  //该段数据个数大于一个，就继续进行"分"
			mergedSort(arr, mid+1, right, temp);
		}
		 
		//合
		merge(arr, left,mid,right,temp);
	}
	/**
	 * 合并
	 * @param arr    原始数组
	 * @param left   左段数据的左边
	 * @param mid    左段数据的右边     mid+1：右段数据的左边
	 * @param right  右段数据的右边
	 * @param temp   临时数组，用于在有序合并时存放数据（空间换时间的操作）
	 */
	public static void merge(int[] arr, int left,int mid, int right, int[] temp) {
		//左段 left~mid
		//右段 mid+1~right
		int l = left;
		int r = mid + 1;
		int t = 0;
		//此时的左段和右段已经是有序数组了（因为当该段元素只有一个时，该段就是有序的）
		while(l <= mid && r <= right) {   //同时遍历两段，将较小的元素放入临时数组temp
			if(arr[l] < arr[r]) {    //左段的数据小鱼右段的数据
				temp[left + t] = arr[l];
				l++;
			}else {
				temp[left + t] = arr[r];
				r++;
			}
			t++;
		}
		//比较完后只剩下两种情况：1.左段还有一部分有序数据，右段空了 2.左段空了，右段还有一部分有序数据
		//1.左段还有一部分有序数据，右段空了
		while(l <= mid) {
			temp[left + t] = arr[l];
			l++;
			t++;
		}
		//2.左段空了，右段还有一部分有序数据
		while (r <= right) {
			temp[left + t] = arr[r];
			r++;
			t++;
		}
		//将原始数组temp里的这段数据置换为有序的，以进行后面的判断
		for (int i = left; i <= right; i++) {
			arr[i] = temp[i];
		}
		
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
