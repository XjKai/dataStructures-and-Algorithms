package com.XJK.recursion;

//没有理解透彻


/**
 * @author MSI
 *  递归解决迷宫问题
 */
public class MiGong {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] map = new int[10][10];       //建一个地图  1:墙、2:走过的地方、3:走过且回溯过的地方
		//生成周边的墙
		for(int i = 0; i<map[0].length; i++) {
			map[0][i] = 1;
			map[map.length - 1][i] = 1;
		}
		for (int j = 0; j < map.length; j++) {
			map[j][0] = 1;
			map[j][map[0].length - 1] = 1;
		}
		map[6][2] = 1;
		map[7][2] = 1;
		map[8][2] = 1;
		map[9][2] = 1;
		
		showMap(map);
		findNextStep(map,1,1);
		System.out.println();
		showMap(map);
	}
	/**
	 * 
	 * @param map
	 */
    public static void showMap(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.printf("%d",map[i][j]);
			}
			System.out.println();
			
		}
	}
    /**
     * 
     * @param map   地图
     * @param x     需要判断的点的横坐标
     * @param y     需要判断的点的综坐标
     * @return
     */
    public static boolean findNextStep(int[][] map, int x, int y) {
    	if(map[8][8] == 2) {      //到达终点，不再递归
    		return true;
    	}else  if (map[x][y] == 0) {
    		map[x][y] = 2;

			if(findNextStep(map,x+1,y)) { //下            回溯时在下一步有路可走会返回true，否则返回false
				return true;
			} 
			else if(findNextStep(map,x,y+1)) { //右
				return true;
			}
			else if(findNextStep(map,x,y-1)) { //左
				return true;
			}			
			else if(findNextStep(map,x-1,y)) { //上
				return true;
			}else {
				map[x][y] = 3;    //等于3代表回溯过，代表该层递归退出，即从内层回来了，代表无路可走而回来
				return false;     //回溯点1
			}
		}else {
			return false;     //回溯点2
		}

	}
}
