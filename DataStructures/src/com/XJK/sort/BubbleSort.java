package com.XJK.sort;


import java.util.Calendar;

/**1
 * @author MSI
 * ð������
 * ʱ�临�Ӷȣ�O(n^2)
 * 8�����ݣ�15s
 */
public class BubbleSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//int[] longArr = {5,3,2,1,0};
	    int[] longArr = new int[80000];
		for (int i = 0; i < longArr.length; i++) {
			longArr[i] = (int)(Math.random()*800000);
		}
		getTimr();
		Bsort(longArr);
		getTimr();
	}
	
	//ð������
	public static int[] Bsort(int[] t) {
		int temp, count = 0;
		boolean flag = true;
		int[] s = t.clone(); // ����һ�ݣ���Ϊ�������������ͣ������ں������޸ĵľ���ԭ����
		for (int i = s.length - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {   //��ͷ��ʼ�뵽i��������Ƚϣ��õ����ڵ�i��λ�õĴ�С������
				count++;
				if (s[j] > s[j + 1]) {
					flag = false;
					temp = s[j + 1];
					s[j + 1] = s[j];
					s[j] = temp;
				}
			}
			if (flag == true) {   //�Ż��������һ��û�н�������˵��������ϣ�ֱ���˳�
				break;
			}else {
				flag = true;
			}
		}
		System.out.println("�����ˣ�" + count);
		return s;
	}

//	public static void show(int[] j) {
//		for (int i = 0; i < j.length; i++) {
//			System.out.print(j[i] + " ");
//		}
//		System.out.println();
//	}
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
