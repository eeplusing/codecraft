package com.eplusing.codecraft.ref;

public class Prim
{
	int min, i, j, k;
	public void shortestTree(Graph graph, int[] adjvex, int[] lowcost, int INFINITY)
	{
		lowcost[0] = 0;//初始化第一个权值为0，即V0加入生成树  
		adjvex[0] = 0; //初始化第一个顶点下标为0
		 
		for (i = 1; i<graph.numVertexes; i++)  
		{  
			lowcost[i] = graph.arc[0][i];  
			adjvex[i] = 0;//将v0顶点与之有边的权值存入数组  并初始化都为v0的下标  
		}  
		for (i=1; i<graph.numVertexes; i++) 
		{
			min = INFINITY;  
			j = 1;  
			k = 0;  
			while (j < graph.numVertexes)
			{
				if (lowcost[j]!=0 && lowcost[j]<min)
				{
					min = lowcost[j];
					k = j;
				}
				j++;
			}
		}
		
		System.out.println(adjvex[k] +";" + k);   //输出当前顶点边中权值最小的边  
		lowcost[k] = 0;                     //将当前顶点的权值设为0，表示此顶点已经完成任务  
		for (j=1; j<graph.numVertexes; ++j)  
		{  
			if (lowcost[j]!=0 && graph.arc[k][j]<lowcost[j])
			{
				lowcost[j] =graph.arc[k][j];
				adjvex[j] = k;
			}  
		 }  
	}
}
