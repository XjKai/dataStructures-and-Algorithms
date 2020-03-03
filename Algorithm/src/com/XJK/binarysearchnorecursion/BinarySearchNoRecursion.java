package com.XJK.binarysearchnorecursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 不使用递归的二分查找
 * 速度比用递归的方式快，因为递归开辟空间需要花销
 * @author XJK
 *
 */
public class BinarySearchNoRecursion {

	public static void main(String[] args) {
		//测试
		int[] arr = {1,2,5,6,6,6,8,9};
		List<Integer> list = binarySearchNoRec(arr, 6);
		System.out.println(list.toString());

	}

	/**
	 * 非递归的二分查找
	 * @param arr
	 * @param target 目标值
	 * @return       以列表形式返回结果
	 */
	public static List<Integer> binarySearchNoRec(int[] arr, int target) {
		//查找区域的左边界
		int left = 0;  
		//查找区域的右边界
		int right = arr.length - 1;
		while (left <= right) {
			//二分点
			int mid = (left + right)/2; 
			if (arr[mid] == target) {
				//找到
				List<Integer> list = new ArrayList<Integer>();
				list.add(mid);
						//这里是输出目标数据周围重复的目标数据
						int mid_l = mid-1;
						int mid_r = mid+1;
						while(mid_l >= 0 && arr[mid_l] == target) {    //向左查找，是否还存在重复目标数据
							list.add(mid_l);
							mid_l--;
						}
						while(mid_r < arr.length && arr[mid_r] == target) {  //向右查找，是否还存在重复目标数据
							list.add(mid_r);
							mid_r++;
						}
				return list;
			} else if(arr[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}		
		}
		//没找到
		return null;
	}
}
