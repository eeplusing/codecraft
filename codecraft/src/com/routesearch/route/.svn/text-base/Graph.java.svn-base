package com.routesearch.route;

import java.util.ArrayList;

/************************************************************************************
 * @Title        : Graph.java
 * @Todo         : 图
 * @Author       : CaoPeng
 * @DateTime     : 2016年3月17日 下午9:00:08
 * @Copyright    : 2016 LLC All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class Graph
{
	final int INFINITY = 100000;
	//存储点的链表
	private ArrayList<Integer> vertexList = new ArrayList<Integer>();
	//邻接矩阵，用来存储边
    private int[][] edges;
    //边的数目
	private int numOfEdges;
   // private int numOfVertexs;//顶点的数目
    public Graph(int n)
	{
    	super();
		this.edges = new int[n][n];
        edges = new int[n][n];
        //初始化邻接矩阵
        for(int i = 0; i < n; i++)
        {
        	for(int j = 0; j < n; j++)
            {
        		edges[i][j] = INFINITY;
            }
        }
	}

	public Graph()
	{
		super();
	}

    //返回结点i的数据
    public int getValueByIndex(int i) 
    {
        return vertexList.get(i);
    }

    //返回v1到v2的权值:arg0：起点,arg1:终点
    public int getWeight(int v1,int v2) 
    {
        return edges[v1][v2];
    }

    //插入结点
    public void insertVertex(Integer vertex) 
    {
        vertexList.add(vertexList.size(),vertex);
    }

    /**
     * 插入边
    * @param v1 起点
    * @param v2 终点
    * @param weight 权重
     */
    public void insertEdge(int v1,int v2,int weight) 
    {
    	if(getWeight(v1, v2) < INFINITY)
    	{
    		edges[v1][v2] = weight < edges[v1][v2] ? weight : edges[v1][v2];
    	}
    	else
    	{
    		edges[v1][v2] = weight;
    		numOfEdges++;
    		
    		/*添加点*/
    		if(!vertexList.contains((Object)v1))
    		{
    			vertexList.add(v1);
    		}
    		if(!vertexList.contains((Object)v2))
    		{
    			vertexList.add(v2);
    		}
    	}
    	
    }
    
    /*对外公开函数，深度优先遍历，与其同名私有函数属于方法重载*/
    public void depthFirstSearch(int startVertex) 
    {
    	//记录结点是否已经被访问的数组
        boolean[] isVisited=new boolean[getNumOfVertexs()];
        
        for (int i = 0; i < getNumOfVertexs(); i++) 
        {
        	//把所有节点置为未访问
            isVisited[i] = false;
        }
        
        depthFirstSearch(isVisited,startVertex);
        //
        for(int i = 0; i < getNumOfVertexs(); i++)
        {
            //因为对于非连通图来说，并不是通过一个结点就一定可以遍历所有结点的。
            if (!isVisited[i]) 
            {
            	//调用私有方法
            }
        }
    }

    
    //私有函数，深度优先遍历
    private void depthFirstSearch(boolean[] isVisited, int  i) 
    {
        //首先访问该结点，在控制台打印出来
        System.out.print(getValueByIndex(i)+"  ");
        //置该结点为已访问
        isVisited[i] = true;

        int w = getFirstNeighbor(i);//
        while (w != -1) 
        {
            if (!isVisited[w]) 
            {
                depthFirstSearch(isVisited, w);
            }
            w = getNextNeighbor(i, w);
        }
        
    }
    
    
    //得到第一个邻接结点的下标
    public int getFirstNeighbor(int index) 
    {
        for(int j = 0; j < vertexList.size(); j++) 
        {
            if (edges[index][j] < INFINITY) 
            {
                return j;
            }
        }
        return -1;
    }
    
  //根据前一个邻接结点的下标来取得下一个邻接结点
    public int getNextNeighbor(int v1, int v2) 
    {
        for (int j = v2 + 1; j<vertexList.size();j++)
        {
            if (edges[v1][j] < INFINITY) 
            {
                return j;
            }
        }
        return -1;
    }
    
    //得到结点的个数
    public int getNumOfVertexs()
	{
		return vertexList.size();
	}

    //得到边的数目
    public int getNumOfEdges() 
    {
        return numOfEdges;
    }

}
