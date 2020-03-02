package com.XJK.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 用数组表示无向无权图
 * @author XJK
 *
 */
public class Graph {
	//图中边的条数
	public int numsOfEdge;  
	//图中顶点的个数
	public List<String> vertex; 
	//边
	public int[][] edge;
	
	public static void main(String[] strings) {

		String Vertexs[] = {"A", "B", "C", "D", "E"};
		//添加点
		Graph graph = new Graph(Vertexs.length);
		for (int i = 0; i < Vertexs.length; i++) {
			graph.addVertex(Vertexs[i]);
		}
		//添加边
		graph.addEdge(0, 1, 1);
		graph.addEdge(0, 2, 1);
		graph.addEdge(1, 2, 1);
		graph.addEdge(1, 3, 1);
		graph.addEdge(1, 4, 1);
		//显示
		graph.show();
	}
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
}
