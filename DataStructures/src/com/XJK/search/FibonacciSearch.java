package com.XJK.search;
//没理解透彻
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * 斐波那契查找
 * 
 * 由于在计算mid时使用的是斐波拉契数列，所以没有用到除法。整个查找过程只用到了加减法
 * 相比于二分法，没有乘法和除法，所以统计上说会更快
 * @author MSI
 *
 */
public class FibonacciSearch {

	public static void main(String[] args) {
		int[] arr = {0,1,2,2,2,5,6,7,7,7,7};
		System.out.println(fibonacciSort(arr,7));
	}

	
	/**
	 * 产生斐波那契数列
	 * 
	 * @param maxSize 需要的最大长度
	 * @return
	 */
	public static int[] fibonacciArray(int maxSize) {
		int[] arr = new int[maxSize];
		arr[0] = arr[1] = 1;
		for (int i = 2; i < arr.length; i++) {
			arr[i] = arr[i - 1] + arr[i - 2];
		}
		return arr;
	}
	/**
	 * 在这里面调用递归函数
	 * @param arr
	 * @param num
	 * @return
	 */
	public static List<Integer> fibonacciSort(int[] arr, int num) {
		//现在斐波那契数列中找到一个最接近于且大于arr长度的斐波那契数
		int[] fibArr = fibonacciArray(20);   //这里的20应该是根据数组的长度动态变化的
		int k = 0;
		//F(n)-1 = F(n-1)-1 + F(n-2)-1 + 1
		//左段：left ~ left + F(n-1) - 2
		//中间值： left + F(n-1) - 1
		//右段：left + F(n-1) ~ right
		for (int i = 2; i < fibArr.length; i++) {     //斐波那契数列前两个都是1，直接从非1的数开始寻找
			if(fibArr[i] - 1 >= arr.length && fibArr[i - 1] - 1 < arr.length) {
				k = i;
				break;
			}
		}
		//将原数组填充到相应斐波那契数的大小
		int[] arr1 = Arrays.copyOf(arr, fibArr[k] - 1);
		for(int j = arr.length; j < arr1.length; j++) {
			arr1[j] = arr[arr.length - 1];
		}
		//调用递归函数
		return recursionFuc(arr1, fibArr, arr.length, k, 0, arr1.length - 1, num);
	}
	/**
	 * 递归函数
	 * @param arr1             补充后的数组
	 * @param fiboarr    斐波拉契数组
	 * @param arrLen     原数组长度
	 * @param k          索引到斐波那契数组第几个值了
	 * @param left       该段左边界限
	 * @param right      该段右边界限
	 * @param num        要找的值
	 * @return
	 */
	public static List<Integer> recursionFuc(int[] arr1, int[] fiboarr, int arrLen, int k, int left, int right, int num) {
		//F(n)-1 = F(n-1)-1 + F(n-2)-1 + 1
		//left:
		if(left > right) {
			return new ArrayList<Integer>();
		}
		
		int mid = left + fiboarr[k-1] - 1;  //中间位置
		if(num == arr1[mid] && mid < arrLen) {
			List<Integer> list = new ArrayList<Integer>();
			list.add(mid);
			
			int mid_l = mid-1;
			int mid_r = mid+1;
			while(mid_l >= 0 && arr1[mid_l] == num) {    //向左查找，是否还存在目标数据
				list.add(mid_l);
				mid_l--;
			}
			while(mid_r < arrLen && arr1[mid_r] == num) {  //向右查找，是否还存在目标数据
				list.add(mid_r);
				mid_r++;
			}
			return list;
		}else if (num > arr1[mid]) {  //右
			return recursionFuc(arr1,fiboarr, arrLen, k-2, mid + 1,right, num);
		}else{
			return recursionFuc(arr1,fiboarr, arrLen, k-1, left, mid-1, num);
		}
	}

}
