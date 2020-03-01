package com.XJK.search;
//û���͸��
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * 쳲���������
 * 
 * �����ڼ���midʱʹ�õ���쳲��������У�����û���õ��������������ҹ���ֻ�õ��˼Ӽ���
 * ����ڶ��ַ���û�г˷��ͳ���������ͳ����˵�����
 * @author MSI
 *
 */
public class FibonacciSearch {

	public static void main(String[] args) {
		int[] arr = {0,1,2,2,2,5,6,7,7,7,7};
		System.out.println(fibonacciSort(arr,7));
	}

	
	/**
	 * ����쳲���������
	 * 
	 * @param maxSize ��Ҫ����󳤶�
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
	 * ����������õݹ麯��
	 * @param arr
	 * @param num
	 * @return
	 */
	public static List<Integer> fibonacciSort(int[] arr, int num) {
		//����쳲������������ҵ�һ����ӽ����Ҵ���arr���ȵ�쳲�������
		int[] fibArr = fibonacciArray(20);   //�����20Ӧ���Ǹ�������ĳ��ȶ�̬�仯��
		int k = 0;
		//F(n)-1 = F(n-1)-1 + F(n-2)-1 + 1
		//��Σ�left ~ left + F(n-1) - 2
		//�м�ֵ�� left + F(n-1) - 1
		//�ҶΣ�left + F(n-1) ~ right
		for (int i = 2; i < fibArr.length; i++) {     //쳲���������ǰ��������1��ֱ�Ӵӷ�1������ʼѰ��
			if(fibArr[i] - 1 >= arr.length && fibArr[i - 1] - 1 < arr.length) {
				k = i;
				break;
			}
		}
		//��ԭ������䵽��Ӧ쳲��������Ĵ�С
		int[] arr1 = Arrays.copyOf(arr, fibArr[k] - 1);
		for(int j = arr.length; j < arr1.length; j++) {
			arr1[j] = arr[arr.length - 1];
		}
		//���õݹ麯��
		return recursionFuc(arr1, fibArr, arr.length, k, 0, arr1.length - 1, num);
	}
	/**
	 * �ݹ麯��
	 * @param arr1             ����������
	 * @param fiboarr    쳲���������
	 * @param arrLen     ԭ���鳤��
	 * @param k          ������쳲���������ڼ���ֵ��
	 * @param left       �ö���߽���
	 * @param right      �ö��ұ߽���
	 * @param num        Ҫ�ҵ�ֵ
	 * @return
	 */
	public static List<Integer> recursionFuc(int[] arr1, int[] fiboarr, int arrLen, int k, int left, int right, int num) {
		//F(n)-1 = F(n-1)-1 + F(n-2)-1 + 1
		//left:
		if(left > right) {
			return new ArrayList<Integer>();
		}
		
		int mid = left + fiboarr[k-1] - 1;  //�м�λ��
		if(num == arr1[mid] && mid < arrLen) {
			List<Integer> list = new ArrayList<Integer>();
			list.add(mid);
			
			int mid_l = mid-1;
			int mid_r = mid+1;
			while(mid_l >= 0 && arr1[mid_l] == num) {    //������ң��Ƿ񻹴���Ŀ������
				list.add(mid_l);
				mid_l--;
			}
			while(mid_r < arrLen && arr1[mid_r] == num) {  //���Ҳ��ң��Ƿ񻹴���Ŀ������
				list.add(mid_r);
				mid_r++;
			}
			return list;
		}else if (num > arr1[mid]) {  //��
			return recursionFuc(arr1,fiboarr, arrLen, k-2, mid + 1,right, num);
		}else{
			return recursionFuc(arr1,fiboarr, arrLen, k-1, left, mid-1, num);
		}
	}

}
