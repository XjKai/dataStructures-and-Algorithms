package com.XJK.recursion;


/**
 * @author MSI
 * �ݹ����˻ʺ�����
 */
public class EightQueen {
	public static int count = 0;
	static int[][] chessBoard = new int[10][10];  //����һ���˻ʺ���������Ҫ��8x8������,�ඨ��һ�� �������ʱԽ��
	public static void main(String[] strings) {
		findNextPoint(chessBoard,1);
	}
	/**
	 * 
	 * @param chessBoard
	 * @param lie
	 */
	static void findNextPoint(int[][] chessBoard, int hang) {
		if(hang==9) {   //�˳��ݹ�����
			count++;
			System.out.println("�� "+count+" ��:");
			showMap(chessBoard);
			System.out.println();
			System.out.println();
			
			return;
		}
		for(int lie = 1; lie < 9; lie++) {
			if(isRequired(chessBoard,hang,lie)) {        //��������������ͽ��еݹ�
				chessBoard[hang][lie] = 1;
				findNextPoint(chessBoard,hang+1);
			}
			chessBoard[hang][lie] = 0;    //������ʱ������
		}
	}
	/**
	 * 
	 * @param chessBoard
	 * @param x
	 * @param y
	 * @return
	 */
	static boolean isRequired(int[][] chessBoard, int x, int y) {    //�жϵ�ǰ�����Ƿ����Ҫ��
		boolean flag = true;
		
		for(int i = 1; i <9; i++) {     //�жϴ�ֱ��ˮƽ������
			if(chessBoard[x][i] ==1 || chessBoard[i][y] ==1)
			{
				flag = false;
				break;
			}
		}
		int b = x - y;
		int x1,y1;
		for( y1 = 1; y1 <9; y1++) {  //�ж�б��Ϊ1��б��
			x1 = y1 + b;
			if(x1 >= 1 && x1 <=8) {
				if(chessBoard[x1][y1] == 1) {
					flag = false;
					break;	
				}
			}
		}
		b = x + y;
		for( y1 = 1; y1 <9; y1++) { //�ж�б��Ϊ-1��б��
			x1 = -1*y1 + b;
			if(x1 >= 1 && x1 <=8) {
				if(chessBoard[x1][y1] == 1) {
					flag = false;
					break;	
				}
			}
		}	
		return flag;
	}
	/**
	 * 
	 * @param map
	 */
    public static void showMap(int[][] map) {
		for (int i = 1; i < map.length - 1; i++) {
			for (int j = 1; j < map[0].length - 1; j++) {
				System.out.printf("%d\t",map[i][j]);
			}
			System.out.println();
			System.out.println();
		}
	}
}
