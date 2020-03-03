package com.XJK.sort;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;



/**5
 * ��������(ð�����򷨸Ľ�)����ð��������� 1.�����˽������� 2.�����ƶ���ȴ�
 * ˼��:����ó�һ������ʹ����ߵ���ȫ������С���ұߵ���ȫ��������Ȼ��ݹ�(�ռ任ʱ��)��ÿһ�ζ�ִ��ǰ�����仰
 * ���÷�֧��˼��
 * @author MSI
 * 8�����ݣ�60ms
 * 80�����ݣ�150ms
 * 800�����ݣ�1.5s
 * 8000�����ݣ�13s
 */
public class QuickSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[] t = {5,5,1,2,3,0,0,10,0,-1};
		int[] t = new int[8000000];
		for (int i = 0; i < t.length; i++) {
			t[i] = (int)(Math.random()*80000000);
		}

		getTimr();
		quickSort(t,0,t.length-1);
		//Arrays.sort(t);             //java�Դ�����������
		getTimr();
		//System.out.println(Arrays.toString(t));
		
	}
	/**
	 * 
	 * @param arr      //Ҫ�����ԭʼ����
	 * @param left    //Ҫȥ�������һ�ε����
	 * @param right     //Ҫȥ�������һ�ε��յ�
	 */
	public static void quickSort(int[] arr, int left, int right) {
	    //һ��ֱ����ͷ����β�ȽϺ�
	    int ranNum = left;//��¼�µ�һ�����ݵ�ֵ���ڼ������ݿ���ʵ����ģ�������Ҫ������������У�
		int Data = arr[ranNum]; //��¼�µ�һ�����ݵ�ֵ���ڼ������ݿ���ʵ����ģ�������Ҫ������������У�
		int l = left;   //�����жϵ���ָ��
		int r = right;  //�����жϵ���ָ��
		int temp = 0;
		while (true) {
			if (r == l) {
				break;
			}
			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			//!!!!!!!!!�ұ�ѭ��һ��Ҫ�����ѭ����ǰ��ִ��!!!!!!��������l=rʱ����ͣ��λ�õ�ֵһ��С��Data!!!!!!!!!
			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			//���ȡ�ĳ�ʼֵData�����->�ұ�ѭ��һ��Ҫ�����ѭ��ǰ��
			//���ȡ�ĳ�ʼֵData���ұ�->���ѭ��һ��Ҫ���ұ�ѭ��ǰ��
			//������l=r�����һ���ж����:���l=r < ranNum��arr[l=r] < arr[ranNum] �ͽ�l=r �ƶ��� l=r < ranNum && arr[l=r] > arr[ranNum]λ����ȥ
			while (r != l && arr[r] >= Data) {    //���������ұ�Data�������
				r--;					
			}
			while (l != r && arr[l] <= Data) {
				l++;
				
			}
			if(r != l) {
				temp = arr[r];
				arr[r] = arr[l];
				arr[l] = temp;
			}
		}
		//��l=rλ�õ�ֵ��Data����
		temp = arr[l];
		arr[l] = Data;
		arr[ranNum] = temp;  //��ǰ���¼���ǵڼ�������Ҫƥ��
		
		//��ݹ�
		if (l != left) {       //ֻ��һ�����ݾͲ��ݹ���
			quickSort(arr,left,l - 1);
		}
		//�ҵݹ�
		if (r != right) {	   //ֻ��һ�����ݾͲ��ݹ���
			quickSort(arr,r + 1,right);
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
