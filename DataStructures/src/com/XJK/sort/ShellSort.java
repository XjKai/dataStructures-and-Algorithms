package com.XJK.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**4
 * @author MSI
 * ϣ������(��С��������)���ɲ�������Ļ����ϸĽ�����
 * ������Ϊ�����һ�����ݾ�������ֱ������Ϊ1��
 * ˼�룺��������λ��ĩβ����С�����Կ����ŵ�ǰ�棬λ��ͷ������������Կ����ŵ�����
 * ������һ����ÿ�����ļ��
 * 8������ݣ� 40ms   
 * 80�����ݣ�220ms
 * 800�����ݣ�3s
 * 8000�����ݣ�39s
 */
public class ShellSort {
	static int NUMS = 2;    //��ʼ������Ԫ�ظ���,ż��Ϊ2������Ϊ3���о�Ӱ�첻�󡣡�����
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[] t = {5,1,2,3,0,0,10,0,-1};
//		shellSort(t);
//		System.out.println(Arrays.toString(t));
		int[] longArr = new int[80000000];
		for (int i = 0; i < longArr.length; i++) {
			longArr[i] = (int)(Math.random()*800000000);
		}
          
		getTimr();
		shellSort(longArr);
		getTimr();

	}

	/**
	 * ϣ������
	 * @param arr
	 */
	public static void shellSort(int[] arr) {
		int temp = 0;
		int index = 0;     
		for (int gap = arr.length/NUMS; gap >= 1; gap/=NUMS) {   //�����ݼ���1��ִ���걾��forѭ������˳�
			//�ӵ�gap��Ԫ�ؿ�ʼ������������ڵ�����в�������
			//��gap������������ӵ�һ��ĵڶ���Ԫ�ؿ�ʼ��������
			//(���ÿ������Ԫ����Ϊ2)������˳��Ϊ:��һ��ĵڶ���Ԫ�ؽ��в������� >>�ڶ���ĵڶ���Ԫ�ؽ��в�������>>������ĵڶ���Ԫ�ؽ��в������򡤡���������
			//(���ÿ������Ԫ����Ϊ3)������˳��Ϊ:��һ��ĵڶ���Ԫ�ؽ��в������� >>��һ��ĵ�����Ԫ�ؽ��в������� >>�ڶ���ĵڶ���Ԫ�ؽ��в�������>>�ڶ���ĵ�����Ԫ�ؽ��в������� >>������ĵڶ���Ԫ�ؽ��в������� >>������ĵ�����Ԫ�ؽ��в������򡤡���������
			//����������
			for (int i = gap; i < arr.length; i++) {
				//������λ��ǰ�����ڸ����Ԫ�أ���������
				temp = arr[i];        
				
				
		//1.������!!!!!!!!!!  ���ƶ�����ִ��һ��
//				for (int j = i; j >= gap ; j -= gap) {    //ͬ��������򣬲�������������������ͬ
//					if (temp < arr[j - gap]) {
//						arr[j] = arr[j - gap];
//						arr[j - gap] = temp;
//					}else {           //�Ż�
//						arr[j] = temp;
//						break;
//					}
//				}
				
				
		//2.�ƶ���(ͬ����������ķ���), ����ÿ�ζ�ִ��arr[j - gap] = temp;!!!!!!!!!!    �ǽ��������Ż�
				index = i;//�����indexʹ�õ��ǵ�ǰԪ�ص�λ�ã���insertSort����ʹ�õ��ǵ�ǰԪ�ص�ǰһ��λ�ã���ʵ������һ���ģ����岻ͬ���ѣ�
				while (true) {
					if(index < gap || temp > arr[index - gap]) {    //��ǰλ��Ϊ��0��λ�û���С��ǰһ��λ�õ�Ԫ��
						break;
					}
					arr[index] = arr[index - gap];
					index-=gap;
				}
				if(index != i) {
					arr[index] = temp;
				}
			}
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
