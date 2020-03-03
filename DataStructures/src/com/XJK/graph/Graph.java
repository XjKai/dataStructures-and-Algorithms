package com.XJK.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * �������ʾ������Ȩͼ
 * @author XJK
 *
 */
public class Graph {

	public static void main(String[] strings) {

		String Vertexs[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
		//��ӵ�
		Graph graph = new Graph(Vertexs.length);
		for (int i = 0; i < Vertexs.length; i++) {
			graph.addVertex(Vertexs[i]);
		}
		//��ӱ�
		graph.addEdge(0, 1, 1);
		graph.addEdge(0, 2, 1);
		graph.addEdge(1, 3, 1);
		graph.addEdge(1, 4, 1);
		graph.addEdge(3, 7, 1);
		graph.addEdge(4, 7, 1);
		graph.addEdge(2, 5, 1);
		graph.addEdge(2, 6, 1);
		graph.addEdge(5, 6, 1);

		//��ʾ
		graph.show();
		
		//ͼ��������ȱ���
		graph.dfs();
		System.out.println();
		//ͼ�Ĺ�����ȱ���
		graph.bfs();
	}
	
	
	
	
	
	//ͼ�бߵ�����
	public int numsOfEdge;  
	//ͼ�ж���
	public List<String> vertex; 
	//��
	public int[][] edge;
	//��ʼ��ͼ�ж���ĸ���
	public Graph(int numsOfVertex) {
		this.vertex =  new ArrayList<String>(numsOfVertex);
		this.edge = new int[numsOfVertex][numsOfVertex];

	}
	//��vertex����Ӷ���
	public void addVertex(String string) {
		vertex.add(string);
	}
	//����ӱ�
	public void addEdge(int x, int y, int weight) {
		edge[x][y] = weight;
		edge[y][x] = weight;
		numsOfEdge ++;
	}
	//��ʾͼ
	public void show() {
		for (int[] e : edge) {
			System.out.println(Arrays.toString(e));
		}
	}
	
	
	
	/**
	 * DFS
	 * ͼ��������ȱ���(��������)
	 * ˼�룺ÿ�ҵ�һ���µĶ��㣬���Ե�ǰ����Ϊ�µ���������еݹ�Ѱ��
	 */
	//��������
	public void dfs() {
		//��ĳ�����Ƿ񱻷��ʹ�
		boolean[] isVisited= new boolean[vertex.size()];
		//��0��ʼ
		isVisited[0] = true;
		System.out.print(vertex.get(0)+"->");
		dfs(isVisited,edge,0);
	}
	private void dfs(boolean[] isVisited, int[][] edge, int line) {
		if (line < edge.length) {
			for (int i = 0; i < edge[0].length; i++) {
				//����һ��û�е�����ĵ�����µݹ飬������һ�����ݵĹ��̣��ڻ���ʱ��������õ����ĵ�
				if (edge[line][i] > 0 && !isVisited[i]) {
					System.out.print(vertex.get(i)+"->");
					isVisited[i] =true;
					//�Ե�ǰ�ҵ�����Ϊ�µ��У����еݹ�(�Ե�ǰ����Ϊ�µ�������ȥѰ��)
					dfs(isVisited,edge,i);
				}
			}
		}
	}
	
	/**
	 * BFS
	 * ͼ�Ĺ�����ȱ���(����Ѱ��)
	 * 
	 * ˼�룺��ÿһ��������ֱ�ӹ�ϵ�Ķ���ȫ���ҵ������������ҵ��Ķ��װ�Ϊ����ҵ�������ֱ�������Ķ���
	 */
	private void bfs() {
		//��ĳ�����Ƿ񱻷��ʹ�
		boolean[] isVisited= new boolean[vertex.size()];
		//����Ѿ��������Ķ����±�
		Queue<Integer> queue = new LinkedList<Integer>();
		//�ӵ�0����ʼ
		queue.add(0);
		isVisited[0] = true;
		System.out.print(vertex.get(0)+"->");
		//ֱ������Ϊ�գ����ж��㶼���ҵ�(���õľ���û�й����Ķ��㣬ÿ������֮�䶼��ӻ�ֱ�ӵ��й�ϵ)
		while (queue.size() > 0) {
			//�Ӷ������ó��Ѿ��ҵ��Ķ��㣬�Ӹö������ڵ��б���ÿһ���Ƿ���δ���ֵĶ��㣬����оͼ������
			int line = queue.remove();
			for (int i = 0; i < edge[line].length; i++) {
				//����һ��δ���ֹ��Ķ��㣬�ͼ������
				if (edge[line][i] > 0 && !isVisited[i]) {
					System.out.print(vertex.get(i)+"->");
					isVisited[i] = true;
					queue.add(i);
				}
			}
		}

	}
}
