package com.XJK.sort;


import java.util.Arrays;
import java.util.Calendar;


/**3
 * @author MSI 
 * ��n���������Ԫ�ط�Ϊ�����������(�����һ��Ԫ��Ϊ�����)
 * 8�����ݣ�1s
 * 80�����ݣ�����
 * ͬ��ѭ������ while()��������ж��������ʹ��break�˳���ʱ
 * ������������ǰ����ʱ��ǰ���Ԫ�ص�˳���Ѿ�ȷ�������˵ģ����Բ���ð�ݺ�ѡ������Ժ��������˳����λ�õģ�������Ҫȥ�Ƚ�ÿһ����
 */
public class InsertSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] longArr = new int[800000];
		for (int i = 0; i < longArr.length; i++) {
			longArr[i] = (int)(Math.random()*800000);
		}
		int[] longArr1 =longArr.clone();
		getTimr();
		insetSort(longArr);    //ѡ������ͬ������1s
		getTimr();
	}
	/**
	 * ��������
	 * �ѵ�һ��Ԫ����Ϊ���򣬺���Ԫ����Ϊ����������ǰ����
	 * @param arr
	 */
	public static void insetSort(int[] arr) {
		int temp,index;
		for (int i = 1; i < arr.length; i++) {
			temp = arr[i];   //����������Ԫ��
			index = i-1;  //�ӵ�ǰԪ�ص�ǰһ��Ԫ����ѯ��ǰ����
			//��Ϊ��֪��Ҫ��ǰ��ѯ���ٴΣ�����Ҫ��while
			//��ѯ���˵�һ������(index = 0)�ұȵ�һ��Ԫ��С����ֱ�Ӳ��뵽��һ�����˳�while 
			//��ѯ����һ���ȵ�ǰԪ��С��Ԫ�أ��ͽ���ǰԪ�ز��뵽����С��Ԫ�غ��棬�˳�while
			//while (index >=0 && temp < arr[index]) {       //�����ֻ����temp����arr[i]����Ϊarr[i]�ϵ�Ԫ�ػ����ƶ��б����ǵ�������  
			//��������������ʹ�õ�while��������ȽϵĻ���ȽϺ�ʱ
			while(true)	{    //����ʹ��for(;;) 
				if(!(index >=0 && temp < arr[index])) {
					break;
				}
				arr[index + 1] = arr[index];   //����������һλ
				index--;
			}
			//�ճ�����λ��Ϊindex+1����temp����ճ�����λ��
			if (index + 1 != i) {   //�Ż�,����һ��λ�ö�û���ƶ����Ͳ����Լ�������
				arr[index + 1] = temp;
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
