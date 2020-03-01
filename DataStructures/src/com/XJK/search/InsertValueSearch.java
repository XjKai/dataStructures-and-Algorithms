package com.XJK.search;

import java.util.ArrayList;
import java.util.List;

/**
 * ��ֵ����(�Ͷ��ַ��Ĳ�֮ͬ������ mid��Ѱ�ҷ�����ͬ)
 * mid = left + (right - left)*(num - arr[left])/(arr[left] - arr[right])
 * (num - arr[left])/(arr[left] - arr[right]):�൱�ڹ�һ��������0<��<1
 * Ҫ����������
 *  
 * �ŵ㣺����{1,2,3,3,5,6,7,9,9,10}���������ԱȽϺõ����飬���ҵķǳ���
 * ȱ�㣺����{1,2,2,2,2,2,2,2,3,10}���������ԱȽϲ��������𵽷����ã��������3,���Բ�ֵ�Ὣmid��λ��0+(9-0)*(3-1)/(10-1)=2    
 * �����ݲ�����ʱ��Ч����û���ַ���  
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
	 * ��ֵ����
	 * @param arr
	 * @param left
	 * @param right
	 * @param num
	 * @return
	 */
	public static List<Integer> insertValueSearch(int[] arr, int left, int right, int num) {

		if(arr[left] == arr[right]) {   //������������ֵ�����Ҷ���ȣ��Ͳ��ܸ��ݲ�ֵ����mid��ֱ�Ӳ�ѯ����
			if(num == arr[left]) {
				List<Integer> list = new ArrayList<Integer>();
				list.add(-1);        //�������-1�����������鶼��������
				return list;
			}else {
				return new ArrayList<Integer>();
			}
		}

		
		if(left > right || num < arr[0] || num > arr[arr.length-1]) {     //������left > right ����Ϊ ����֮ǰleft = right�������û���жϣ��ж����˺����left����right��
			return new ArrayList<Integer>();
		}
		
		//��ֵ
		int mid = left + (right - left)*(num - arr[left])/(arr[right] - arr[left]);
		
		if (arr[mid] < num) {
			return insertValueSearch(arr, mid + 1, right, num);      //�����һ��ݹ��else��õ�������Ҫô�������returnҪô�������һ��return�����ص�����
		}else if (arr[mid] > num) {
			return insertValueSearch(arr, left, mid - 1, num);
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
