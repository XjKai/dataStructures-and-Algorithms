package com.XJK.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 插值查找(和二分法的不同之处在于 mid的寻找方法不同)
 * mid = left + (right - left)*(num - arr[left])/(arr[left] - arr[right])
 * (num - arr[left])/(arr[left] - arr[right]):相当于归一化操作，0<它<1
 * 要求数组有序
 *  
 * 优点：对于{1,2,3,3,5,6,7,9,9,10}这种线性性比较好的数组，查找的非常快
 * 缺点：对于{1,2,2,2,2,2,2,2,3,10}这种线性性比较差的数组会起到反作用，比如查找3,线性差值会将mid定位到0+(9-0)*(3-1)/(10-1)=2    
 * 在数据不均匀时，效果还没二分法好  
 * @author MSI
 *
 */
public class InsertValueSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {0,1,2,3,4,5,6,6,6,6,7,8,9,10,11,11,11,12};
		System.out.println(insertValueSearch(arr, 0, arr.length-1, 0).toString());
	}
	
	/**
	 * 插值查找
	 * @param arr
	 * @param left
	 * @param right
	 * @param num
	 * @return
	 */
	public static List<Integer> insertValueSearch(int[] arr, int left, int right, int num) {

		if(arr[left] == arr[right]) {   //如果数组最左端值和最右段相等，就不能根据差值计算mid，直接查询返回
			if(num == arr[left]) {
				List<Integer> list = new ArrayList<Integer>();
				list.add(-1);        //如果返回-1，则整个数组都符合条件
				return list;
			}else {
				return new ArrayList<Integer>();
			}
		}

		
		if(left > right || num < arr[0] || num > arr[arr.length-1]) {     //这里是left > right 是因为 在这之前left = right的情况还没有判断，判断完了后就是left大于right了
			return new ArrayList<Integer>();
		}
		
		//插值
		int mid = left + (right - left)*(num - arr[left])/(arr[right] - arr[left]);
		
		if (arr[mid] < num) {
			return insertValueSearch(arr, mid + 1, right, num);      //在最后一层递归的else里得到的数据要么从这里的return要么从下面的一个return给返回到外面
		}else if (arr[mid] > num) {
			return insertValueSearch(arr, left, mid - 1, num);
		}else {                                    //这里只会执行一次，这里是也是一个出递归的位置
			List<Integer> list = new ArrayList<Integer>();
			list.add(mid);
			
			int mid_l = mid-1;
			int mid_r = mid+1;
			while(mid_l >= 0 && arr[mid_l] == num) {    //向左查找，是否还存在目标数据
				list.add(mid_l);
				mid_l--;
			}
			while(mid_r < arr.length && arr[mid_r] == num) {  //向右查找，是否还存在目标数据
				list.add(mid_r);
				mid_r++;
			}
			return list;
		}
		
	}
}
