package com.XJK.sparsearry;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * @author 
 */
public class SparseArray {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		// �ȶ���һ������
		int[][] arr = new int[8][6];
		// �����������ӡ� 1��ʾ���壬2��ʾ����
		arr[1][2] = 1;
		arr[2][3] = 2;
		arr[4][4] = 1;
		arr[3][2] = 2;
		arr[1][4] = 1;
		// ��ӡ������
		System.out.println("��ӡ����");
		System.out.println();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.printf("%d\t", arr[i][j]);
			}
			System.out.println();
		}

		/*
		 * ����ϡ������ ϡ�����������Ϊ������+1������Ϊ���� ϡ�������һ�еĵ�һ��ֵ��ԭ�����������ڶ���ֵ��ԭ����������������ֵ�����Ӹ��� ��һ�������Ӻ�����
		 * �ڶ��������������� ������������ֵ
		 */
		// 1.���ҳ����������ӵĸ���
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] != 0) {
					sum++;
				}
			}
		}
		System.out.println("���Ӹ�����" + sum);
		// 2.�������Ӹ�������ϡ������
		int[][] sparseArr = new int[sum + 1][3];
		sparseArr[0][0] = 8;
		sparseArr[0][1] = 6;
		sparseArr[0][2] = sum;
		int count = 1;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] != 0) {
					sparseArr[count][0] = i;
					sparseArr[count][1] = j;
					sparseArr[count][2] = arr[i][j];
					count++;
				}
			}
		}
		// ��ӡϡ������
		System.out.println("��ӡϡ������");
		System.out.println();
		for (int i = 0; i < sparseArr.length; i++) {
			for (int j = 0; j < sparseArr[0].length; j++) {
				System.out.printf("%d\t", sparseArr[i][j]);
			}
			System.out.println();
		}
		// ��ϡ������д���ļ�
		writeToFile(sparseArr, "F:\\DataStructureWorkSpace\\DataStructures\\src\\DATA\\SparArr.data");

		
		
		// ���ļ���ȡϡ������
		System.out.println("��ȡϡ�����飺");
		sparseArr = readFromFile("F:\\DataStructureWorkSpace\\DataStructures\\src\\DATA\\SparArr.data");
		// ��ϡ������õ�ԭ����
		int[][] reSparse = new int[sparseArr[0][0]][sparseArr[0][1]];
		for (int i = 0; i < sparseArr[0][2]; i++) {
			reSparse[sparseArr[i + 1][0]][sparseArr[i + 1][1]] = sparseArr[i + 1][2];
		}
		// ��ӡ������
		System.out.println("��ӡ����");
		System.out.println();
		for (int i = 0; i < reSparse.length; i++) {
			for (int j = 0; j < reSparse[0].length; j++) {
				System.out.printf("%d\t", reSparse[i][j]);
			}
			System.out.println();
		}
	}

	// ���ļ�д��ϡ������
	public static void writeToFile(int[][] arr, String fileString) throws FileNotFoundException, IOException {
		try (FileOutputStream f = new FileOutputStream(fileString)) {
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[0].length; j++) {
					f.write(arr[i][j]);
					f.write(' '); // ���ڵ������Կո����
				}
				f.write('\n'); // ����
			}
		}
	}

	// ���ļ���ȡϡ������
	public static int[][] readFromFile(String fileString) throws FileNotFoundException, IOException {
		try (FileInputStream f = new FileInputStream(fileString)) {
			int temp;
			int i = 0, j = 0;
			List<Integer> l = new ArrayList<Integer>(); // ��ʱlist
			while ((temp = f.read()) != -1) {
				if ((char) temp == ' ') {
					j++;
				} else if ((char) temp == '\n') {
					j = 0;
					i++;
				} else {
					l.add(Integer.valueOf(temp));
				}
			}
			// ����õ�list�����ά����
			int count = 0;
			int[][] tempArr = new int[i][3];
			for (int k = 0; k < i; k++) {
				for (int k2 = 0; k2 < 3; k2++) {
					tempArr[k][k2] = l.get(count); // �Զ�ת��
					count += 1;
					System.out.printf("%d\t", tempArr[k][k2]);
				}
				System.out.println();
			}
			return tempArr;
		}
	}

}









