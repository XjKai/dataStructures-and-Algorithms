package com.XJK.sort;


import java.util.Arrays;
import java.util.Calendar;

/**2
 * @author MSI
 * 选择排序
 * 时间复杂度：O(n^2)
 * 速度必冒泡快（因为冒泡排序交换数组元素的次数比选择排序多）
 * 与冒泡排序的时间复杂度一样，但循环内执行的操作不同
 * 8万个数据2s
 * 思想：从前往后遍历元素，即寻找到属于当前位置的最小元素，那么那个元素就和当前位置元素交换位置，再接着往后遍历，直到遍历到倒数第二个
 */
public class SelectSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[] t = {5,1,2,3,0,0,10,0,-1};
		int[] longArr = new int[800000];
		for (int i = 0; i < longArr.length; i++) {
			longArr[i] = (int)(Math.random()*800000);
		}
		int[] longArr1 =longArr.clone();
		getTimr();
		selectSort(longArr);    //选择排序，同样数据1s
		getTimr();
		//BubbleSort.Bsort(longArr1);  //冒泡排序，同样数据13s
		//getTimr();
		//System.out.println(Arrays.toString(selectSort(t)));
	}

	//选择排序
	public static int[] selectSort(int[] arr) {
		int min,minIndex;
		for (int i = 0; i < arr.length - 1; i++) {      //数组最后面一个值不用和“后面比较”，当倒数第二个执行完后，数组最后一个值一定为最大值
			min = arr[i];
			minIndex =i;
			for (int j = i + 1; j < arr.length; j++) {//去找属于第i个位置的数据，即与位置i后面所有的数据比较比它小就和它交换
				if(min > arr[j]) {
					min = arr[j];
					minIndex = j;
				}
			}
			//遍历完一圈后才去交换数组
			if(minIndex != i) {    //优化，如果找到了比它还小的值就交换
				arr[minIndex] = arr[i];
				arr[i] = min;
			}
			//System.out.println(Arrays.toString(arr));
		}
		return arr;
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
