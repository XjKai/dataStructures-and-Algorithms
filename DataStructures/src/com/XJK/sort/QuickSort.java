package com.XJK.sort;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;



/**5
 * 快速排序法(冒泡排序法改进)，与冒泡排序法相比 1.减少了交换次数 2.数据移动跨度大
 * 思想:随便拿出一个数，使它左边的数全部比它小，右边的数全部比它大，然后递归(空间换时间)对每一段都执行前面两句话
 * 利用分支的思想
 * @author MSI
 * 8万数据：60ms
 * 80万数据：150ms
 * 800万数据：1.5s
 * 8000万数据：13s
 */
public class QuickSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[] t = {5,5,1,2,3,0,0,10,0,-1};
		int[] t = new int[8000000];
		for (int i = 0; i < t.length; i++) {
			t[i] = (int)(Math.random()*80000000);
		}

		getTimr();
		quickSort(t,0,t.length-1);
		//Arrays.sort(t);             //java自带的数组排序
		getTimr();
		//System.out.println(Arrays.toString(t));
		
	}
	/**
	 * 
	 * @param arr      //要排序的原始数组
	 * @param left    //要去排序的这一段的起点
	 * @param right     //要去排序的这一段的终点
	 */
	public static void quickSort(int[] arr, int left, int right) {
	    //一般直接用头或者尾比较好
	    int ranNum = left;//记录下第一个数据的值（第几个数据可以实随机的，但必须要存在这段数据中）
		int Data = arr[ranNum]; //记录下第一个数据的值（第几个数据可以实随机的，但必须要存在这段数据中）
		int l = left;   //用于判断的左指针
		int r = right;  //用于判断的右指针
		int temp = 0;
		while (true) {
			if (r == l) {
				break;
			}
			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			//!!!!!!!!!右边循环一定要在左边循环的前面执行!!!!!!这样才能l=r时，所停的位置的值一定小于Data!!!!!!!!!
			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			//如果取的初始值Data在左边->右边循环一定要在左边循环前面
			//如果取的初始值Data在右边->左边循环一定要在右边循环前面
			//或者在l=r后面加一个判断语句:如果l=r < ranNum但arr[l=r] < arr[ranNum] 就将l=r 移动到 l=r < ranNum && arr[l=r] > arr[ranNum]位置上去
			while (r != l && arr[r] >= Data) {    //从右向左找比Data大的数据
				r--;					
			}
			while (l != r && arr[l] <= Data) {
				l++;
				
			}
			if(r != l) {
				temp = arr[r];
				arr[r] = arr[l];
				arr[l] = temp;
			}
		}
		//把l=r位置的值与Data交换
		temp = arr[l];
		arr[l] = Data;
		arr[ranNum] = temp;  //和前面记录的是第几个数据要匹配
		
		//左递归
		if (l != left) {       //只有一个数据就不递归了
			quickSort(arr,left,l - 1);
		}
		//右递归
		if (r != right) {	   //只有一个数据就不递归了
			quickSort(arr,r + 1,right);
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
