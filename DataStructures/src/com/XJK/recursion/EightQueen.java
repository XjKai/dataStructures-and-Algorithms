package com.XJK.recursion;


/**
 * @author MSI
 * 递归解决八皇后问题
 */
public class EightQueen {
	public static int count = 0;
	static int[][] chessBoard = new int[10][10];  //定义一个八皇后问题所需要的8x8的棋盘,多定义一层 以免访问时越界
	public static void main(String[] strings) {
		findNextPoint(chessBoard,1);
	}
	/**
	 * 
	 * @param chessBoard
	 * @param lie
	 */
	static void findNextPoint(int[][] chessBoard, int hang) {
		if(hang==9) {   //退出递归条件
			count++;
			System.out.println("第 "+count+" 种:");
			showMap(chessBoard);
			System.out.println();
			System.out.println();
			
			return;
		}
		for(int lie = 1; lie < 9; lie++) {
			if(isRequired(chessBoard,hang,lie)) {        //如果满足条件，就进行递归
				chessBoard[hang][lie] = 1;
				findNextPoint(chessBoard,hang+1);
			}
			chessBoard[hang][lie] = 0;    //回来的时候清零
		}
	}
	/**
	 * 
	 * @param chessBoard
	 * @param x
	 * @param y
	 * @return
	 */
	static boolean isRequired(int[][] chessBoard, int x, int y) {    //判断当前棋子是否符合要求
		boolean flag = true;
		
		for(int i = 1; i <9; i++) {     //判断垂直与水平两条线
			if(chessBoard[x][i] ==1 || chessBoard[i][y] ==1)
			{
				flag = false;
				break;
			}
		}
		int b = x - y;
		int x1,y1;
		for( y1 = 1; y1 <9; y1++) {  //判断斜率为1的斜线
			x1 = y1 + b;
			if(x1 >= 1 && x1 <=8) {
				if(chessBoard[x1][y1] == 1) {
					flag = false;
					break;	
				}
			}
		}
		b = x + y;
		for( y1 = 1; y1 <9; y1++) { //判断斜率为-1的斜线
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
