package com.XJK.recursion;

//û�����͸��


/**
 * @author MSI
 *  �ݹ����Թ�����
 */
public class MiGong {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] map = new int[10][10];       //��һ����ͼ  1:ǽ��2:�߹��ĵط���3:�߹��һ��ݹ��ĵط�
		//�����ܱߵ�ǽ
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
     * @param map   ��ͼ
     * @param x     ��Ҫ�жϵĵ�ĺ�����
     * @param y     ��Ҫ�жϵĵ��������
     * @return
     */
    public static boolean findNextStep(int[][] map, int x, int y) {
    	if(map[8][8] == 2) {      //�����յ㣬���ٵݹ�
    		return true;
    	}else  if (map[x][y] == 0) {
    		map[x][y] = 2;

			if(findNextStep(map,x+1,y)) { //��            ����ʱ����һ����·���߻᷵��true�����򷵻�false
				return true;
			} 
			else if(findNextStep(map,x,y+1)) { //��
				return true;
			}
			else if(findNextStep(map,x,y-1)) { //��
				return true;
			}			
			else if(findNextStep(map,x-1,y)) { //��
				return true;
			}else {
				map[x][y] = 3;    //����3������ݹ�������ò�ݹ��˳��������ڲ�����ˣ�������·���߶�����
				return false;     //���ݵ�1
			}
		}else {
			return false;     //���ݵ�2
		}

	}
}
