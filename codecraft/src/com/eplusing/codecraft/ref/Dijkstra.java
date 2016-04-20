package com.eplusing.codecraft.ref;

public class Dijkstra 
{
    final int MAXVALUE = 9;
    final int INFINITY = 65535;
	
	int pathArc[] = new int[MAXVALUE];//存储最短路径下标的数组
	int distance[] = new int[MAXVALUE];//存储到各点最短路径的权值和
	
	public void shortestPath_dijkstra(Graph G, int v0, int[] pathArc, int[] distance)
	{
		int v, w, k = 0, min;
		boolean[] flag = new boolean[MAXVALUE]; //flag[w] = true,表示已经求得顶点V0到Vw的最短路径
		
		/*初始化数据*/
		for(v = 0; v < G.numVertexes; v++)
		{
			flag[v] = false; //全部顶点初始化为未找到最短路径
			distance[v] = G.arc[v0][v];//将与v0顶点有连线的顶点加上权值
			pathArc[v] = 0; //初始化路径数组为0
		}
		distance[v0] = 0;//v0到自身的权值和为0
		flag[v0] = true; //v0到v0已为最短路径
		
		/*开始主循环每次求得v0到某个v顶点的最短路径*/
		for(v = 1; v < G.numVertexes; v++)
		{
			min = INFINITY;
			for(w = 0; w < G.numVertexes; w++)
			{
				if(!flag[w] && distance[w]< min)
				{
					k = w;
					min = distance[w];
				}
			}
			
			flag[k] = true; //将目前找的最近的点标记为true
			
			/*修改当前记录的最短路径及距离*/
			for(w = 1; w < G.numVertexes; w++)//跳过自身将
			{
				/*如果经过k顶点的路径比现在这条路径的长度短的话,将其更新为最短路径长度*/
				if(!flag[w] && (min + G.arc[k][w] < distance[w]))
				{
					distance[w] = min + G.arc[k][w]; //修改当前路径长度
					pathArc[w] = k; //存放前驱顶点
				}
			}
		}
	}
}
