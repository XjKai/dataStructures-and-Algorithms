package com.XJK.search;
/**
 * ���ֲ���(����������������)
 * �ٶȺܿ죬��Ϊ���ݸ����Ƕ԰���ٵ�
 * Ҳ���Բ��ҵ����ظ���
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
	  * д��һ���Լ�д�ģ�
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
			//���Ŀ���������������ظ����ֵĻ���һ���������ģ�����Ҫ�������Ҳ�ѯ���Ƿ���Ŀ������
			while(mid_l >= 0 && arr[mid_l] == num) {    //������ң��Ƿ񻹴���Ŀ������
				list.add(mid_l);
				mid_l--;
			}
			while(mid_r < arr.length && arr[mid_r] == num) {  //���Ҳ��ң��Ƿ񻹴���Ŀ������
				list.add(mid_r);
				mid_r++;
			}
			return;
		}else if (arr[mid] < num && left != right) {     //�ҵݹ飬���ҵ�left == right����û�ҵ����Ͳ��ݹ���
			search(list, arr, mid + 1, right, num);
		}else if (arr[mid] > num &&left != right) {       //��ݹ� ���ҵ�left == right����û�ҵ����Ͳ��ݹ���
			search(list, arr, left, mid - 1, num);
		}
	}
	
	
	/**
	 * д������������Ƶ��д����
	 * @param arr
	 * @param left
	 * @param right
	 * @param num
	 * @return
	 */
	public static List<Integer> binarySearch2(int[] arr, int left, int right, int num) {
		System.out.println("hello");
		int mid = (right + left)/2;
		if(left > right) {     //������left > right ����Ϊ ����֮ǰleft = right�������û���жϣ��ж����˺����left����right��
			return new ArrayList<Integer>();
		}else if (arr[mid] < num) {
			return binarySearch2(arr, mid + 1, right, num);      //�����һ��ݹ��else��õ�������Ҫô�������returnҪô�������һ��return�����ص�����
		}else if (arr[mid] > num) {
			return binarySearch2(arr, left, mid - 1, num);
		}else {                                    //����ֻ��ִ��һ�Σ�������Ҳ��һ�����ݹ��λ��
			List<Integer> list = new ArrayList<Integer>();
			list.add(mid);
			
			int mid_l = mid-1;
			int mid_r = mid+1;
			while(mid_l >= 0 && arr[mid_l] == num) {    //������ң��Ƿ񻹴���Ŀ������
				list.add(mid_l);
				mid_l--;
			}
			while(mid_r < arr.length && arr[mid_r] == num) {  //���Ҳ��ң��Ƿ񻹴���Ŀ������
				list.add(mid_r);
				mid_r++;
			}
			return list;
		}
		
	}
}
