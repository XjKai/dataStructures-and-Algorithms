package com.XJK.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * �������ʾ������Ȩͼ
 * @author XJK
 *
 */
public class Graph {
	//ͼ�бߵ�����
	public int numsOfEdge;  
	//ͼ�ж���ĸ���
	public List<String> vertex; 
	//��
	public int[][] edge;
	
	public static void main(String[] strings) {

		String Vertexs[] = {"A", "B", "C", "D", "E"};
		//��ӵ�
		Graph graph = new Graph(Vertexs.length);
		for (int i = 0; i < Vertexs.length; i++) {
			graph.addVertex(Vertexs[i]);
		}
		//��ӱ�
		graph.addEdge(0, 1, 1);
		graph.addEdge(0, 2, 1);
		graph.addEdge(1, 2, 1);
		graph.addEdge(1, 3, 1);
		graph.addEdge(1, 4, 1);
		//��ʾ
		graph.show();
	}
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
}
