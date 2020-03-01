package com.XJK.sort;


import java.util.Arrays;
import java.util.Calendar;


/**3
 * @author MSI 
 * 把n个待排序的元素分为有序表和无序表(例如第一个元素为有序表)
 * 8万数据：1s
 * 80万数据：崩溃
 * 同样循环次数 while()括号里加判断条件会比使用break退出耗时
 * 插入排序再往前插入时，前面的元素的顺序已经确定下来了的，所以不像冒泡和选择排序对后面的数据顺序是位置的，导致需要去比较每一个。
 */
public class InsertSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] longArr = new int[800000];
		for (int i = 0; i < longArr.length; i++) {
			longArr[i] = (int)(Math.random()*800000);
		}
		int[] longArr1 =longArr.clone();
		getTimr();
		insetSort(longArr);    //选择排序，同样数据1s
		getTimr();
	}
	/**
	 * 插入排序
	 * 把第一个元素视为有序，后面元素视为无序，依次往前插入
	 * @param arr
	 */
	public static void insetSort(int[] arr) {
		int temp,index;
		for (int i = 1; i < arr.length; i++) {
			temp = arr[i];   //保存待插入的元素
			index = i-1;  //从当前元素的前一个元素轮询往前插入
			//因为不知道要往前轮询多少次，所以要用while
			//查询到了第一个数据(index = 0)且比第一个元素小，就直接插入到第一个，退出while 
			//查询到了一个比当前元素小的元素，就将当前元素插入到比它小的元素后面，退出while
			//while (index >=0 && temp < arr[index]) {       //这里的只能用temp不能arr[i]，因为arr[i]上的元素会在移动中被覆盖掉！！！  
			//！！！！！这里使用的while括号里面比较的话会比较耗时
			while(true)	{    //或者使用for(;;) 
				if(!(index >=0 && temp < arr[index])) {
					break;
				}
				arr[index + 1] = arr[index];   //依次往后移一位
				index--;
			}
			//空出来的位置为index+1，把temp插入空出来的位置
			if (index + 1 != i) {   //优化,对于一个位置都没有移动，就不和自己交换了
				arr[index + 1] = temp;
			}
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
