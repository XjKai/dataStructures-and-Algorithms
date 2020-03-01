package com.XJK.tree;

import java.util.Arrays;
import java.util.Calendar;

/**
 * ������
 * @author MSI
 *8000������ �� 1s
 */
public class HeapSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] t = new int[80000];
		for (int i = 0; i < t.length; i++) {
			t[i] = (int)(Math.random()*800000);
		}
		int[] arr = {4,6,8,5,9};
		getTimr();
		heapSort(t);
		getTimr();
		//System.out.println(Arrays.toString(t));

	}
	
	
	
	
	
	/**
	 * ������
	 * @param arr
	 */
	public static void heapSort(int[] arr) {
		//�Ƚ�������һ���󶥶�
		FormBigTopHeap(arr);
		for (int i = 0; i < arr.length - 1; i++) {    //length��Ԫ�أ���Ҫ��������length - 1��
			//����
			exchange(arr,0,arr.length - i - 1);
			//����(��Ϊ���˶Ѷ�������Ľ�㶼���ϴ󶥶ѵ��ص㣬����ֻ��Ҫ��0��ʼ����)
			adjustNode(arr,0 , arr.length - i - 1);
		}
		
	}
	/**
	 * �����һ����Ҷ�ӽ��(arr.length/2 - 1)��ʼ���������󡢴������Ϲ��ɴ󶥶�
	 * @param arr  ����
	 * @param i    ��ǰ���λ��
	 */
	public static void FormBigTopHeap(int[] arr) {
		//�������󡢴������Ϲ��ɴ󶥶�
		for (int j = (arr.length/2 - 1); j >= 0; j--) {
			adjustNode(arr,j ,arr.length);
		}
	}
	/**
	 * ������i����㣬ʹi���������ӽ��(��len֮ǰ)������󶥶�,���ﶨ����һ��len����Ϊ�����ڽ�����β���������Ǹ�ֵ�Ѿ�������󶥶ѵĹ���
	 * @param arr
	 * @param i
	 */
	public static void adjustNode(int[] arr, int i, int len) {
		if(i*2 + 1 < len && i*2 + 2 < len) {    //i�������ҽ�㶼����
			if(arr[i*2 + 1] > arr[i] && arr[i*2 + 1] > arr[i*2 + 2]) {    //�������
				exchange(arr, i, i*2 + 1);
				//����i�����ֵ���޸���(��С�� )�����i���㲻��Ҷ�ӽ�㣬��Ҫ�ݹ��ж�i����ĵ�ǰֵ�Ƿ����i������ӽ��
				if (i*2 + 1 <= (arr.length/2 - 1)) {   //i�����㲻��Ҷ�ӽ��
					adjustNode(arr, i*2 + 1, len);
				}
				
			} else if(arr[i*2 + 2] > arr[i] && arr[i*2 + 2] > arr[i*2 + 1]) {    //�ҽ�����
				exchange(arr, i, i*2 + 2);
				//����i�ҽ���ֵ���޸���(��С�� )�����i�ҽ�㲻��Ҷ�ӽ�㣬��Ҫ�ݹ��ж�i�ҽ��ĵ�ǰֵ�Ƿ����i�ҽ����ӽ��
				if (i*2 + 2 <= (arr.length/2 - 1)) {   //i���ҽ�㲻��Ҷ�ӽ��
					adjustNode(arr, i*2 + 2, len);
				}
			}
		} else if(i*2 + 1 < len) {        //i�����������     
			if(arr[i*2 + 1] > arr[i]) {    //i������ֵ����i����ֵ
				exchange(arr, i, i*2 + 1);
				//����i�����ֵ���޸���(��С�� )�����i���㲻��Ҷ�ӽ�㣬��Ҫ�ݹ��ж�i����ĵ�ǰֵ�Ƿ����i������ӽ��
				if (i*2 + 1 <= (arr.length/2 - 1)) {   //i�����㲻��Ҷ�ӽ��
					adjustNode(arr, i*2 + 1, len);
				}
			}
		}
		//��������ȫ�����������i�����㲻���ڣ����ҽ��һ��������
	}
	/**
	 * ������������λ�õ�ֵ
	 * @param arr
	 * @param i
	 * @param j
	 */
	public static void exchange(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
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
