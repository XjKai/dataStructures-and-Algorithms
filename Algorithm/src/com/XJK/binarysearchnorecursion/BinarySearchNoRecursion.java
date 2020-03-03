package com.XJK.binarysearchnorecursion;

import java.util.ArrayList;
import java.util.List;

/**
 * ��ʹ�õݹ�Ķ��ֲ���
 * �ٶȱ��õݹ�ķ�ʽ�죬��Ϊ�ݹ鿪�ٿռ���Ҫ����
 * @author XJK
 *
 */
public class BinarySearchNoRecursion {

	public static void main(String[] args) {
		//����
		int[] arr = {1,2,5,6,6,6,8,9};
		List<Integer> list = binarySearchNoRec(arr, 6);
		System.out.println(list.toString());

	}

	/**
	 * �ǵݹ�Ķ��ֲ���
	 * @param arr
	 * @param target Ŀ��ֵ
	 * @return       ���б���ʽ���ؽ��
	 */
	public static List<Integer> binarySearchNoRec(int[] arr, int target) {
		//�����������߽�
		int left = 0;  
		//����������ұ߽�
		int right = arr.length - 1;
		while (left <= right) {
			//���ֵ�
			int mid = (left + right)/2; 
			if (arr[mid] == target) {
				//�ҵ�
				List<Integer> list = new ArrayList<Integer>();
				list.add(mid);
						//���������Ŀ��������Χ�ظ���Ŀ������
						int mid_l = mid-1;
						int mid_r = mid+1;
						while(mid_l >= 0 && arr[mid_l] == target) {    //������ң��Ƿ񻹴����ظ�Ŀ������
							list.add(mid_l);
							mid_l--;
						}
						while(mid_r < arr.length && arr[mid_r] == target) {  //���Ҳ��ң��Ƿ񻹴����ظ�Ŀ������
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
		//û�ҵ�
		return null;
	}
}
