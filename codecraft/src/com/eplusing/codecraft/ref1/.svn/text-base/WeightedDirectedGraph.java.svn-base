package com.eplusing.codecraft.ref1;
/**
 * 
 * 带权重有方向图
 * 介绍了Dijkstra算法：最短路径算法
 * 
 * Dijkstra算法原理：
 *  1) 适用条件&范围：
        a) 单源最短路径(从源点s到其它所有顶点v);
        b) 有向图&无向图(无向图可以看作(u,v),(v,u)同属于边集E的有向图)
        c) 所有边权非负(任取(i,j)∈E都有W(i,j)≥0);
    2) 算法描述：
        a) 初始化：dis[v]=maxint(v∈V,v≠s);//s到v的距离
         		dis[s]=0; //s到本身的距离为0
         		pre[s]=s; //s的前驱是本身
         		S={s};//将最短路径放入S集
        b) For i:=1 to n
                1.取V-S中的一顶点u使得dis[u]=min{dis[v]|v∈V-S}
                2.S=S+{u}
                3.For V-S中每个顶点v do Relax(u,v,W(u,v))
        c) 算法结束：dis[i]为s到i的最短距离；pre[i]为i的前驱节点
    3) 算法优化：
        a) 使用二叉堆(Binary Heap)来实现每步的DeleteMin(ExtractMin，即算法步骤b中第1步)操作，算法复杂度从O(V^2)降到O((V+E)㏒V)。推荐对稀疏图使用。
        b) 使用Fibonacci Heap(或其他Decrease操作O(1),DeleteMin操作O(logn)的数据结构)可以将复杂度降到O(E+V㏒V)；如果边权值均为不大于C的正整数，则使用Radix Heap可以达到O(E+V㏒C)。
 * 
 */
public class WeightedDirectedGraph {
	private final int           MAX_VERTS    = 20;
    private final int           INFINITY    = 1000000;
    private Vertex[]           	vertexList;            // list of vertices
    private int[][]             adjMat;                // adjacency matrix
    private int                 nVerts;                // current number of vertices
    private int                 nTree;                    // number of verts in tree
    private DistanceParent[]	shortestPathList;                    // array for shortest-path data
    private int                 currentVertex;            // current vertex
    private int                 startToCurrentDistance;        // distance to currentVert
    
    
    public WeightedDirectedGraph()
    {
        vertexList = new Vertex[MAX_VERTS];
        // adjacency matrix
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        nTree = 0;
        for ( int j = 0; j < MAX_VERTS; j++ )
        {
            // set adjacency
            for ( int k = 0; k < MAX_VERTS; k++ )
            {
                adjMat[j][k] = INFINITY;
            }
        }
        
        shortestPathList = new DistanceParent[MAX_VERTS]; // shortest paths
        
    }
    
    
    /**
     * 求最短路径算法：Dijkstra算法。
     */
    public void findshortestPathList()
    {
        int startTree = 0;//从0节点开始
        vertexList[startTree].isInTree = true;//将该节点放入树中
        nTree = 1; //在树中的节点号
        
        //初始化最短路径表，以邻接矩阵中的 startTree 行数据初始化
        for ( int i = 0; i < nVerts; i++ )
        {
        	//将所有边设置为不可达
            shortestPathList[i] = new DistanceParent( startTree, adjMat[startTree][i] );
        }
        
        while( nTree < nVerts )
        {
            int indexMin = getMinFromshortestPathList();// 从最短路径表中得到目前的最小值
            
            int minDist = shortestPathList[indexMin].distance;
            
            if ( minDist == INFINITY ) // 如果为 INFINITY ，表明不可达，或者都在树中了。
            { 
                System.out.println( "There are unreachable vertices" );
                break; // sPath is complete
            } else
            {
                currentVertex = indexMin; // 将最小的赋值给currentVert，为即将进入树中作准备
                startToCurrentDistance = shortestPathList[indexMin].distance;//路径权重最小
            }
            // 将当前节点放入树中
            vertexList[currentVertex].isInTree = true;
            nTree++;
            updateShortestPathList(); // 更新最短路径表
        }//end while
        
        displayPaths(); // display sPath[] contents
        
        nTree = 0; // clear tree
        for(int j=0; j<nVerts; j++)
        {
            vertexList[j].isInTree = false;
        }
    }
    
    
    /**
     * 显示最短路径
     */
    public void displayPaths()
    {
        for ( int j = 0; j < nVerts; j++ ) // display contents of sPath[]
        {
            System.out.print( vertexList[j].label + "=" ); // B=
            if ( shortestPathList[j].distance == INFINITY )
            {
                System.out.print( "inf" ); // inf
            }
            else
            {
                System.out.print( shortestPathList[j].distance ); // 50
            }
            char parent = vertexList[shortestPathList[j].parentVert].label;
            System.out.print( "(" + parent + ") " ); // (A)
        }
        System.out.println( "" );
    }
    
    
    /**
     * 更新最短路径表
     */
    public void updateShortestPathList()
    {
        for ( int column=1; column < nVerts;column++ )//跳过自身，从1开始
        {
            if ( false == vertexList[column].isInTree )//如果不在树中
            {
                // calculate distance for one sPath entry
                
                
                int currentToFringe = adjMat[currentVertex][column];// 当前点到column的距离
                int startToFringe = startToCurrentDistance + currentToFringe;//计算起始点到column的距离
                
                // 与原来最短路径表中的权重值进行比较
                if ( startToFringe < shortestPathList[column].distance ) // 如果新值更小，就更新最短路径表
                { 
                    shortestPathList[column].parentVert = currentVertex;
                    shortestPathList[column].distance = startToFringe;
                }//end if
            }//end if
        }//end for
    }
    
    /**
     * 从最短路径表中得到目前的最小值
     * @return 返回最小值的index
     */
    public int getMinFromshortestPathList()
    {
        
        int minDist = INFINITY; // assume minimum
        int indexMin = 0;
        for ( int j = 1; j < nVerts; j++ ) // for each vertex,
        { // if it's in tree and
            if ( !vertexList[j].isInTree && // smaller than old one
                    shortestPathList[j].distance < minDist )
            {
                minDist = shortestPathList[j].distance;
                indexMin = j; // update minimum
            }
        } // end for
        return indexMin;
    }
    
    /**
     * 添加一条边
     * @param start 边的起点
     * @param end 边的终点
     * @param weight 边的权重
     */
    public void addEdge( int start, int end, int weight )
    {
        adjMat[start][end] = weight;    //有方向
    }
    
    /**
     * 添加一个节点
     * 
     * @param lab
     */
    public void addVertex( char lab ) // argument is label
    {
        vertexList[nVerts++ ] = new Vertex( lab );
    }

}
