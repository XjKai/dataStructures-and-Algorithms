package com.XJK.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**4
 * @author MSI
 * 希尔排序法(缩小增量排序)，由插入排序的基础上改进而来
 * 以增量为间隔的一组数据经行排序，直到增量为1。
 * 思想：这样对于位于末尾的最小数可以快速排到前面，位于头部的最大数可以快速排到后面
 * 增量：一组内每个数的间隔
 * 8万个数据： 40ms   
 * 80万数据：220ms
 * 800万数据：3s
 * 8000万数据：39s
 */
public class ShellSort {
	static int NUMS = 2;    //初始的组内元素个数,偶数为2，奇数为3（感觉影响不大。。。）
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[] t = {5,1,2,3,0,0,10,0,-1};
//		shellSort(t);
//		System.out.println(Arrays.toString(t));
		int[] longArr = new int[80000000];
		for (int i = 0; i < longArr.length; i++) {
			longArr[i] = (int)(Math.random()*800000000);
		}
          
		getTimr();
		shellSort(longArr);
		getTimr();

	}

	/**
	 * 希尔排序
	 * @param arr
	 */
	public static void shellSort(int[] arr) {
		int temp = 0;
		int index = 0;     
		for (int gap = arr.length/NUMS; gap >= 1; gap/=NUMS) {   //增量递减到1，执行完本轮for循环后就退出
			//从第gap个元素开始，逐个对其所在的组进行插入排序
			//从gap往后遍历，即从第一组的第二个元素开始插入排序
			//(如果每组组内元素数为2)，遍历顺序为:第一组的第二个元素进行插入排序 >>第二组的第二个元素进行插入排序>>第三组的第二个元素进行插入排序······
			//(如果每组组内元素数为3)，遍历顺序为:第一组的第二个元素进行插入排序 >>第一组的第三个元素进行插入排序 >>第二组的第二个元素进行插入排序>>第二组的第三个元素进行插入排序 >>第三组的第二个元素进行插入排序 >>第三组的第三个元素进行插入排序······
			//·····
			for (int i = gap; i < arr.length; i++) {
				//遍历该位置前面属于该组的元素，插入排序
				temp = arr[i];        
				
				
		//1.交换法!!!!!!!!!!  比移动法多执行一步
//				for (int j = i; j >= gap ; j -= gap) {    //同理插入排序，不过遍历的下限有所不同
//					if (temp < arr[j - gap]) {
//						arr[j] = arr[j - gap];
//						arr[j - gap] = temp;
//					}else {           //优化
//						arr[j] = temp;
//						break;
//					}
//				}
				
				
		//2.移动法(同插入排序里的方法), 不用每次都执行arr[j - gap] = temp;!!!!!!!!!!    是交换法的优化
				index = i;//这里的index使用的是当前元素的位置，在insertSort里面使用的是当前元素的前一个位置（其实方法是一样的，定义不同而已）
				while (true) {
					if(index < gap || temp > arr[index - gap]) {    //当前位置为第0个位置或者小于前一个位置的元素
						break;
					}
					arr[index] = arr[index - gap];
					index-=gap;
				}
				if(index != i) {
					arr[index] = temp;
				}
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
