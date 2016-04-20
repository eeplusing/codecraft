package com.eplusing.codecraft.ref;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final int INFINITY = 65535;
		int vertexNum = 9;
		int arc[][] = new int[][]{
				{0,     1, 	   5,     65535, 65535, 65535, 65535, 65535, 65535},
				{1,     0,     3,     7,     5,     65535, 65535, 65535, 65535},
				{5,     3,     0,     65535, 1,     7,     65535, 65535, 65535},
				{65535, 7,     65535, 0,     2,     65535, 3,     65535, 65535},
				{65535, 5,     1,     2,     0,     3,     6,     9,     65535},
				{65535, 65535, 7,     65535, 3,     0,     65535, 5,     65535},
				{65535, 65535, 65535, 3,     6,     65535, 0,     2,     7    },
				{65535, 65535, 65535, 65535, 9,     5,     2,     0,     4    },
				{65535, 65535, 65535, 65535, 65535, 65535, 7,     4,     0    },
		};
		
		Graph graph = new Graph(vertexNum, arc);
		
		Dijkstra dijkstra = new Dijkstra();
		dijkstra.shortestPath_dijkstra(graph, 0, dijkstra.pathArc, dijkstra.distance);
		
		System.out.println(dijkstra.distance[8]);
		/**
	     * 显示最短路径
	     */
		int temp = 8,temp2;
		while(dijkstra.pathArc[temp] != 0){
			temp2 = dijkstra.pathArc[temp];
			System.out.println(dijkstra.pathArc[temp]);
			temp = temp2;
		}
		
		 // int adjvex[] = new int[MaxVex];    //保存相关顶点下标  
		   // int lowcost[] = new int[MaxVex];   //保存相关顶点间边的权值  
	   
	}
}
