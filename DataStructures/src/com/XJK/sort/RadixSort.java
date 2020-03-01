package com.XJK.sort;

import java.util.Arrays;
import java.util.Calendar;

//1024Byte(�ֽ�) = 1KB
//1�ֽ� = 8λ��bit��

/**7
 * ��������(Ͱ����) ���ռ任ʱ��
 * @author MSI
 *ȱ�㣺�޷�����������Ҫ�ռ��
 *���Ҫ�Ÿ����Ļ�����Ҫ19��Ͱ
 *
 *8�����ݣ�60ms
 *80�����ݣ�150ms
 *800�����ݣ�1s
 *4000�����ݣ�5s
 *80000�����ݣ���Ҫ�ռ�̫�������
 */
public class RadixSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[] t = {5,10,3,0,1,0,6};
		int[] t = new int[8000000];
		for (int i = 0; i < t.length; i++) {
			t[i] = (int)(Math.random()*80000000);
		}
		getTimr();
		radixSort(t);
		getTimr();
	}
	/**
	 * 
	 * @param arr
	 */
	public static void radixSort(int[] arr) {
		//8ǧ��ʱ����Ҫ3.3G�ڴ�
		int[][]  bucket = new int[10][arr.length];  //�ȶ����Ǹ�Ͱ��Ϊ���������ÿ��Ͱ�Ĵ�СΪarr.length
		int[]   lengthOfBucket = new int[10];      //��¼ÿ��Ͱ�д�ŵ�Ԫ�صĸ���
		//���ڻ�����ֶ�Ӧ��λ��
		//��Ӧλ�õ�ֵ = (num%frontTen)/rearTen   ����(num/ten)%10
		//��ȡһ�����ֶ�Ӧλ�õ�ֵ�ķ�����1.��ȡ����ȡģ 2.��ȡģ��ȡ��
		int ten = 1;
		int putInCount = 0;   //��¼��arr�з���ĸ���
        int max = arr[0];   //����������������
        
        //�ȱ���һ�����飬��������������Ǹ�ֵ
        for (int i = 1; i < arr.length; i++) {
			if(arr[i] > max) {
				max = arr[i];
			}
		}
        int roundTimes;  //forѭ���Ĵ������������������Ǹ�ֵ�ĳ���
        roundTimes = (max + "").length();    //�ֻصĴ���
    
        
        //��һ�֣����ݸ�λ����
        //�ڶ��֣�����ʮλ����
        //�����֣����ݰ�λ����
        //������
		for(int n = 0; n < roundTimes; n++) {
			putInCount=0;     //ÿһ�ֻؾ���0
			
			//��Ͱ��������
			for (int i = 0; i < arr.length; i++) {  //(arr[i]%frontTen)/rearTen ����ǰ���ָ�λ��ֵ(��λ��ʮλ����λ...)�Ĵ�С ����ڼ���Ͱ
				//����Ͱ��
				bucket[(arr[i]/ten)%10][lengthOfBucket[(arr[i]/ten)%10]] = arr[i];
				//��Ͱ���ѷ��õ����ݵĸ�����һ
				lengthOfBucket[(arr[i]/ten)%10]++;
			}
	
			//��Ͱ�������ó�����
			for (int j = 0; j < bucket.length; j++) {
				for (int k = 0; k < lengthOfBucket[j]; k++) {
					arr[putInCount] = bucket[j][k];
					//bucket[j][k] = 0;    
					putInCount++;
				}
				lengthOfBucket[j] = 0;  //��Ͱ����������󣬽���Ͱ���ѷ��õ����ݵĸ�����0
			}
			ten *= 10;
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
