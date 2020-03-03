package com.XJK.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * 用数组表示无向无权图
 * @author XJK
 *
 */
public class Graph {

	public static void main(String[] strings) {

		String Vertexs[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
		//添加点
		Graph graph = new Graph(Vertexs.length);
		for (int i = 0; i < Vertexs.length; i++) {
			graph.addVertex(Vertexs[i]);
		}
		//添加边
		graph.addEdge(0, 1, 1);
		graph.addEdge(0, 2, 1);
		graph.addEdge(1, 3, 1);
		graph.addEdge(1, 4, 1);
		graph.addEdge(3, 7, 1);
		graph.addEdge(4, 7, 1);
		graph.addEdge(2, 5, 1);
		graph.addEdge(2, 6, 1);
		graph.addEdge(5, 6, 1);

		//显示
		graph.show();
		
		//图的深度优先遍历
		graph.dfs();
		System.out.println();
		//图的广度优先遍历
		graph.bfs();
	}
	
	
	
	
	
	//图中边的条数
	public int numsOfEdge;  
	//图中顶点
	public List<String> vertex; 
	//边
	public int[][] edge;
	//初始化图中顶点的个数
	public Graph(int numsOfVertex) {
		this.vertex =  new ArrayList<String>(numsOfVertex);
		this.edge = new int[numsOfVertex][numsOfVertex];

	}
	//向vertex中添加顶点
	public void addVertex(String string) {
		vertex.add(string);
	}
	//向添加边
	public void addEdge(int x, int y, int weight) {
		edge[x][y] = weight;
		edge[y][x] = weight;
		numsOfEdge ++;
	}
	//显示图
	public void show() {
		for (int[] e : edge) {
			System.out.println(Arrays.toString(e));
		}
	}
	
	
	
	/**
	 * DFS
	 * 图的深度优先遍历(纵向深入)
	 * 思想：每找到一个新的顶点，就以当前顶点为新的线索点进行递归寻找
	 */
	//方法重载
	public void dfs() {
		//第某个点是否被访问过
		boolean[] isVisited= new boolean[vertex.size()];
		//从0开始
		isVisited[0] = true;
		System.out.print(vertex.get(0)+"->");
		dfs(isVisited,edge,0);
	}
	private void dfs(boolean[] isVisited, int[][] edge, int line) {
		if (line < edge.length) {
			for (int i = 0; i < edge[0].length; i++) {
				//遇到一个没有到达过的点就向下递归，这里有一个回溯的过程，在回溯时会继续检查该点后面的点
				if (edge[line][i] > 0 && !isVisited[i]) {
					System.out.print(vertex.get(i)+"->");
					isVisited[i] =true;
					//以当前找到的列为新的行，进行递归(以当前顶点为新的线索点去寻找)
					dfs(isVisited,edge,i);
				}
			}
		}
	}
	
	/**
	 * BFS
	 * 图的广度优先遍历(横向寻找)
	 * 
	 * 思想：把每一个顶点有直接关系的顶点全部找到，再依次以找到的顶底啊为起点找到其所有直接相连的顶点
	 */
	private void bfs() {
		//第某个点是否被访问过
		boolean[] isVisited= new boolean[vertex.size()];
		//存放已经遍历到的顶点下标
		Queue<Integer> queue = new LinkedList<Integer>();
		//从第0个开始
		queue.add(0);
		isVisited[0] = true;
		System.out.print(vertex.get(0)+"->");
		//直到队列为空，所有顶点都被找到(利用的就是没有孤立的顶点，每个顶点之间都间接或直接的有关系)
		while (queue.size() > 0) {
			//从队列中拿出已经找到的顶点，从该顶点所在的行遍历每一列是否有未发现的顶点，如果有就加入队列
			int line = queue.remove();
			for (int i = 0; i < edge[line].length; i++) {
				//发现一个未发现过的顶点，就加入队列
				if (edge[line][i] > 0 && !isVisited[i]) {
					System.out.print(vertex.get(i)+"->");
					isVisited[i] = true;
					queue.add(i);
				}
			}
		}

	}
}
