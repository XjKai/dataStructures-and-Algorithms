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
		// 先定义一个棋盘
		int[][] arr = new int[8][6];
		// 定义三颗棋子。 1表示黑棋，2表示白棋
		arr[1][2] = 1;
		arr[2][3] = 2;
		arr[4][4] = 1;
		arr[3][2] = 2;
		arr[1][4] = 1;
		// 打印出棋盘
		System.out.println("打印棋盘");
		System.out.println();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.printf("%d\t", arr[i][j]);
			}
			System.out.println();
		}

		/*
		 * 产生稀疏数组 稀疏数组的行数为棋子数+1，列数为三列 稀疏数组第一行的第一个值是原数组行数、第二个值是原棋盘列数、第三个值是棋子个数 第一列是棋子横坐标
		 * 第二列是棋子综坐标 第三列是棋子值
		 */
		// 1.先找出棋盘中棋子的个数
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] != 0) {
					sum++;
				}
			}
		}
		System.out.println("棋子个数：" + sum);
		// 2.根据棋子个数生成稀疏数组
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
		// 打印稀疏数组
		System.out.println("打印稀疏数组");
		System.out.println();
		for (int i = 0; i < sparseArr.length; i++) {
			for (int j = 0; j < sparseArr[0].length; j++) {
				System.out.printf("%d\t", sparseArr[i][j]);
			}
			System.out.println();
		}
		// 将稀疏数组写入文件
		writeToFile(sparseArr, "F:\\DataStructureWorkSpace\\DataStructures\\src\\DATA\\SparArr.data");

		
		
		// 从文件读取稀疏数组
		System.out.println("读取稀疏数组：");
		sparseArr = readFromFile("F:\\DataStructureWorkSpace\\DataStructures\\src\\DATA\\SparArr.data");
		// 由稀疏数组得到原棋盘
		int[][] reSparse = new int[sparseArr[0][0]][sparseArr[0][1]];
		for (int i = 0; i < sparseArr[0][2]; i++) {
			reSparse[sparseArr[i + 1][0]][sparseArr[i + 1][1]] = sparseArr[i + 1][2];
		}
		// 打印出棋盘
		System.out.println("打印棋盘");
		System.out.println();
		for (int i = 0; i < reSparse.length; i++) {
			for (int j = 0; j < reSparse[0].length; j++) {
				System.out.printf("%d\t", reSparse[i][j]);
			}
			System.out.println();
		}
	}

	// 向文件写入稀疏数组
	public static void writeToFile(int[][] arr, String fileString) throws FileNotFoundException, IOException {
		try (FileOutputStream f = new FileOutputStream(fileString)) {
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[0].length; j++) {
					f.write(arr[i][j]);
					f.write(' '); // 行内的数据以空格隔开
				}
				f.write('\n'); // 换行
			}
		}
	}

	// 从文件读取稀疏数组
	public static int[][] readFromFile(String fileString) throws FileNotFoundException, IOException {
		try (FileInputStream f = new FileInputStream(fileString)) {
			int temp;
			int i = 0, j = 0;
			List<Integer> l = new ArrayList<Integer>(); // 临时list
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
			// 将获得的list放入二维数组
			int count = 0;
			int[][] tempArr = new int[i][3];
			for (int k = 0; k < i; k++) {
				for (int k2 = 0; k2 < 3; k2++) {
					tempArr[k][k2] = l.get(count); // 自动转型
					count += 1;
					System.out.printf("%d\t", tempArr[k][k2]);
				}
				System.out.println();
			}
			return tempArr;
		}
	}

}









