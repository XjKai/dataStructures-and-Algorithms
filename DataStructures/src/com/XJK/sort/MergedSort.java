package com.XJK.sort;

import java.util.Arrays;
import java.util.Calendar;

/**6
 * �鲢����
 * @author MSI
 * �ȷֽ��ٺϲ�(��������)
 * ˼�룺�ֽ�>�ϲ��������ݵݹ�ֿ����ֵ�ÿ��ֻ��һ��Ԫ��Ϊֹ��Ȼ���ٻ��ݵĹ�����ȥ���кϲ���������ʱ��������ĺϲ���
 * ����˼�룬�����ݷֵ�����ջ��Ȼ�������ЩС����
 * 8�����ݣ�35ms
 * 80�����ݣ�160ms
 * 800�����ݣ�1~2s
 * 4000�����ݣ�8s
 * 8000�����ݣ�17s
 */
public class MergedSort {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[] t = {5,10,3,0,-1,0,6};
		int[] t = new int[4000000];
		for (int i = 0; i < t.length; i++) {
			t[i] = (int)(Math.random()*40000000);
		}
		int[] temp = new int[t.length];
		getTimr();
		mergedSort(t,0,t.length-1,temp);
		getTimr();
		//System.out.println(Arrays.toString(temp));
	}
	/**
	 *  ����һ�η�Ϊ���Σ�Ȼ����кϲ�
	 * @param arr    //ԭʼ����
	 * @param left   //�ö���ʼλ��
	 * @param right  //�ö���ֹλ��
	 * @param temp   //��ʱ����
	 */
	public static void mergedSort(int[] arr, int left, int right, int[] temp) {
		
		//�֣����ַ���
		int mid = (right + left)/2;   //�ֳ����ε����н���
		//���һ�Σ�left~mid
		//�ұ�һ�Σ�mid+1~right
		//��ݹ�
		if(left != mid) {   //�ö����ݸ�������һ�����ͼ�������"��"
			mergedSort(arr, left, mid, temp);
		}
		//�ҵݹ�
		if (mid+1 != right) {  //�ö����ݸ�������һ�����ͼ�������"��"
			mergedSort(arr, mid+1, right, temp);
		}
		 
		//��
		merge(arr, left,mid,right,temp);
	}
	/**
	 * �ϲ�
	 * @param arr    ԭʼ����
	 * @param left   ������ݵ����
	 * @param mid    ������ݵ��ұ�     mid+1���Ҷ����ݵ����
	 * @param right  �Ҷ����ݵ��ұ�
	 * @param temp   ��ʱ���飬����������ϲ�ʱ������ݣ��ռ任ʱ��Ĳ�����
	 */
	public static void merge(int[] arr, int left,int mid, int right, int[] temp) {
		//��� left~mid
		//�Ҷ� mid+1~right
		int l = left;
		int r = mid + 1;
		int t = 0;
		//��ʱ����κ��Ҷ��Ѿ������������ˣ���Ϊ���ö�Ԫ��ֻ��һ��ʱ���öξ�������ģ�
		while(l <= mid && r <= right) {   //ͬʱ�������Σ�����С��Ԫ�ط�����ʱ����temp
			if(arr[l] < arr[r]) {    //��ε�����С���Ҷε�����
				temp[left + t] = arr[l];
				l++;
			}else {
				temp[left + t] = arr[r];
				r++;
			}
			t++;
		}
		//�Ƚ����ֻʣ�����������1.��λ���һ�����������ݣ��Ҷο��� 2.��ο��ˣ��Ҷλ���һ������������
		//1.��λ���һ�����������ݣ��Ҷο���
		while(l <= mid) {
			temp[left + t] = arr[l];
			l++;
			t++;
		}
		//2.��ο��ˣ��Ҷλ���һ������������
		while (r <= right) {
			temp[left + t] = arr[r];
			r++;
			t++;
		}
		//��ԭʼ����temp�����������û�Ϊ����ģ��Խ��к�����ж�
		for (int i = left; i <= right; i++) {
			arr[i] = temp[i];
		}
		
	}
	/**
	 * ��ʱ
	 */
	public static void getTimr() {
		Calendar c = Calendar.getInstance();
        int mm = c.get(Calendar.MINUTE);
        int ss = c.get(Calendar.SECOND);
        int ms = c.get(Calendar.MILLISECOND);

		System.out.println("��:"+mm+" ��:"+ss+" ����:"+ms);
	}
}
