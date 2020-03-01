package com.XJK.sort;

import java.util.Arrays;
import java.util.Calendar;

//1024Byte(字节) = 1KB
//1字节 = 8位（bit）

/**7
 * 基数排序(桶排序) ，空间换时间
 * @author MSI
 *缺点：无法排序负数，需要空间大
 *如果要排负数的话，需要19个桶
 *
 *8万数据：60ms
 *80万数据：150ms
 *800万数据：1s
 *4000万数据：5s
 *80000万数据：需要空间太大而崩溃
 */
public class RadixSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[] t = {5,10,3,0,1,0,6};
		int[] t = new int[8000000];
		for (int i = 0; i < t.length; i++) {
			t[i] = (int)(Math.random()*80000000);
		}
		getTimr();
		radixSort(t);
		getTimr();
	}
	/**
	 * 
	 * @param arr
	 */
	public static void radixSort(int[] arr) {
		//8千万时，需要3.3G内存
		int[][]  bucket = new int[10][arr.length];  //先定义是个桶，为避免溢出，每个桶的大小为arr.length
		int[]   lengthOfBucket = new int[10];      //记录每个桶中存放的元素的个数
		//用于获得数字对应的位数
		//对应位置的值 = (num%frontTen)/rearTen   或者(num/ten)%10
		//获取一个数字对应位置的值的方法：1.先取余再取模 2.先取模再取余
		int ten = 1;
		int putInCount = 0;   //记录向arr中放入的个数
        int max = arr[0];   //数组里面最大的数字
        
        //先遍历一遍数组，求出数组中最大的那个值
        for (int i = 1; i < arr.length; i++) {
			if(arr[i] > max) {
				max = arr[i];
			}
		}
        int roundTimes;  //for循环的次数，即数组中最大的那个值的长度
        roundTimes = (max + "").length();    //轮回的次数
    
        
        //第一轮：根据个位排序
        //第二轮：根据十位排序
        //第三轮：根据百位排序
        //。。。
		for(int n = 0; n < roundTimes; n++) {
			putInCount=0;     //每一轮回就置0
			
			//向桶放入数据
			for (int i = 0; i < arr.length; i++) {  //(arr[i]%frontTen)/rearTen ，当前数字该位置值(个位、十位、百位...)的大小 代表第几个桶
				//放入桶中
				bucket[(arr[i]/ten)%10][lengthOfBucket[(arr[i]/ten)%10]] = arr[i];
				//该桶内已放置的数据的个数加一
				lengthOfBucket[(arr[i]/ten)%10]++;
			}
	
			//从桶中依次拿出数据
			for (int j = 0; j < bucket.length; j++) {
				for (int k = 0; k < lengthOfBucket[j]; k++) {
					arr[putInCount] = bucket[j][k];
					//bucket[j][k] = 0;    
					putInCount++;
				}
				lengthOfBucket[j] = 0;  //该桶的数据拿完后，将该桶内已放置的数据的个数置0
			}
			ten *= 10;
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
