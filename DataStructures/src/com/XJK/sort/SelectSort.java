package com.XJK.sort;


import java.util.Arrays;
import java.util.Calendar;

/**2
 * @author MSI
 * ѡ������
 * ʱ�临�Ӷȣ�O(n^2)
 * �ٶȱ�ð�ݿ죨��Ϊð�����򽻻�����Ԫ�صĴ�����ѡ������ࣩ
 * ��ð�������ʱ�临�Ӷ�һ������ѭ����ִ�еĲ�����ͬ
 * 8�������2s
 * ˼�룺��ǰ�������Ԫ�أ���Ѱ�ҵ����ڵ�ǰλ�õ���СԪ�أ���ô�Ǹ�Ԫ�ؾͺ͵�ǰλ��Ԫ�ؽ���λ�ã��ٽ������������ֱ�������������ڶ���
 */
public class SelectSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[] t = {5,1,2,3,0,0,10,0,-1};
		int[] longArr = new int[800000];
		for (int i = 0; i < longArr.length; i++) {
			longArr[i] = (int)(Math.random()*800000);
		}
		int[] longArr1 =longArr.clone();
		getTimr();
		selectSort(longArr);    //ѡ������ͬ������1s
		getTimr();
		//BubbleSort.Bsort(longArr1);  //ð������ͬ������13s
		//getTimr();
		//System.out.println(Arrays.toString(selectSort(t)));
	}

	//ѡ������
	public static int[] selectSort(int[] arr) {
		int min,minIndex;
		for (int i = 0; i < arr.length - 1; i++) {      //���������һ��ֵ���ú͡�����Ƚϡ����������ڶ���ִ������������һ��ֵһ��Ϊ���ֵ
			min = arr[i];
			minIndex =i;
			for (int j = i + 1; j < arr.length; j++) {//ȥ�����ڵ�i��λ�õ����ݣ�����λ��i�������е����ݱȽϱ���С�ͺ�������
				if(min > arr[j]) {
					min = arr[j];
					minIndex = j;
				}
			}
			//������һȦ���ȥ��������
			if(minIndex != i) {    //�Ż�������ҵ��˱�����С��ֵ�ͽ���
				arr[minIndex] = arr[i];
				arr[i] = min;
			}
			//System.out.println(Arrays.toString(arr));
		}
		return arr;
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
