package com.eplusing.codecraft.ref2;

public class Dijkstra 
{
    private int MAXVALUE;
    private int INFINITY;
    private int[] pathArc;
    private int[] costSum;
    
	public Dijkstra(int mAXVALUE, int iNFINITY, int[] pathArc, int[] costSum)
	{
		super();
		this.MAXVALUE = mAXVALUE;
		this.INFINITY = iNFINITY;
		this.pathArc = pathArc;
		this.costSum = costSum;
	}




	public void shortestPath_dijkstra(Graph G, int startID, int[] pathArc, int[] distance)
	{
		int v, w, k = 0, min;
		boolean[] flag = new boolean[MAXVALUE]; //flag[w] = true,表示已经求得顶点startID到Vw的最短路径
		
		/*初始化数据*/
		for(v = 0; v < G.numVertexs; v++)
		{
			flag[v] = false; //全部顶点初始化为未找到最短路径
			distance[v] = G.arc[startID][v];//将与startID顶点有连线的顶点加上权值
			pathArc[v] = 0; //初始化路径数组为0
		}
		distance[startID] = 0;//startID到自身的权值和为0
		flag[startID] = true; //startID到startID已为最短路径
		
		/*开始主循环每次求得startID到某个v顶点的最短路径*/
		for(v = 1; v < G.numVertexs; v++)
		{
			min = INFINITY;
			for(w = 0; w < G.numVertexs; w++)
			{
				if(!flag[w] && distance[w]< min)
				{
					k = w;
					min = distance[w];
				}
			}
			
			flag[k] = true; //将目前找的最近的点标记为true
			
			/*修改当前记录的最短路径及距离*/
			for(w = 1; w < G.numVertexs; w++)//跳过自身将
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
