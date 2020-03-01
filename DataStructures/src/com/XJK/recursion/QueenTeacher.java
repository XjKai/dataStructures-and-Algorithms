package com.XJK.recursion;


public class QueenTeacher {
	int MAX = 8;   //���ʺ���
	//��һά�����ʾ���һ�ֿ���
	int[] arr = new int[MAX];      //int[2] = 3.   ��ʾ���ڵ�1�е�2�еĻʺ�
	int count = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QueenTeacher queenTeacher = new QueenTeacher();
		queenTeacher.findPoint(0);     //�൱������8��forѭ��
	}

	/**
	 * 
	 * @param n   ���ڼ����ʺ�
	 * @return
	 */
	private  boolean ifRequired(int n) {
		//�����ж��У���Ϊ�ڸ������������������λ�þͻ�ݹ鵽��һ��ȥ�ˡ�
		for(int i = 0; i < n; i++) {   //�жϸ�λ���Ƿ����������Ѿ����ڵĵ��ͻ��
			if (arr[n] == arr[i] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {      //��һά�������ж��Ƿ�ͬ�У�ͬб��
				return false;
			}
		}
		return true;
	}
	/**
	 * 
	 * @param n
	 */
	private  void findPoint(int n) {
		if(n == MAX) {            //��������
			count++;
			System.out.println("�ⷨ: "+count);
			System.out.println();
			show(arr);
			System.out.println();
			return;
		}
		for (int i = 0; i < MAX; i++) {
			arr[n] = i;
			if (ifRequired(n)) {   //���������͵ݹ�
				findPoint(n+1);
			}
		}
		
	}
	private  void show(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "  ");
		}
		System.out.println();
	}
}
