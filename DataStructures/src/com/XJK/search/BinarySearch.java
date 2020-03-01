package com.XJK.search;
/**
 * 二分查找(输入的数组必须有序)
 * 速度很快，因为数据个数是对半减少的
 * 也可以查找到有重复的
 */
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author MSI
 *
 */
public class BinarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {0,1,2,3,4,5,6,6,6,6,7,8,9,10,11,11,11,12};
		//System.out.println(binarySearch(arr,6).toString());
		System.out.println(binarySearch2(arr, 0, arr.length-1, 12).toString());
	}
	
	
	/**
	  * 写法一（自己写的）
	 * @param arr
	 * @param num
	 * @return
	 */
	public static List<Integer> binarySearch(int[] arr,int num) {
		List<Integer> list = new ArrayList<Integer>();
		search(list, arr, 0, arr.length-1, num);
		return list;
	}
	public static void search(List<Integer> list, int[] arr, int left, int right, int num) {
		int mid = (right + left)/2;
		if(arr[mid] == num) {
			list.add(mid);
			int mid_l = mid-1;
			int mid_r = mid+1;
			//如果目标数字在数据中重复出现的话，一定是连续的，所以要向左向右查询下是否还有目标数据
			while(mid_l >= 0 && arr[mid_l] == num) {    //向左查找，是否还存在目标数据
				list.add(mid_l);
				mid_l--;
			}
			while(mid_r < arr.length && arr[mid_r] == num) {  //向右查找，是否还存在目标数据
				list.add(mid_r);
				mid_r++;
			}
			return;
		}else if (arr[mid] < num && left != right) {     //右递归，查找到left == right，还没找到，就不递归了
			search(list, arr, mid + 1, right, num);
		}else if (arr[mid] > num &&left != right) {       //左递归 查找到left == right，还没找到，就不递归了
			search(list, arr, left, mid - 1, num);
		}
	}
	
	
	/**
	 * 写法二（参照视频的写法）
	 * @param arr
	 * @param left
	 * @param right
	 * @param num
	 * @return
	 */
	public static List<Integer> binarySearch2(int[] arr, int left, int right, int num) {
		System.out.println("hello");
		int mid = (right + left)/2;
		if(left > right) {     //这里是left > right 是因为 在这之前left = right的情况还没有判断，判断完了后就是left大于right了
			return new ArrayList<Integer>();
		}else if (arr[mid] < num) {
			return binarySearch2(arr, mid + 1, right, num);      //在最后一层递归的else里得到的数据要么从这里的return要么从下面的一个return给返回到外面
		}else if (arr[mid] > num) {
			return binarySearch2(arr, left, mid - 1, num);
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
