package com.eplusing.codecraft.ref;

/**
 * �����ڽӾ��������ͼ
 * @author lmning
 *Feb 16, 2009 8:33:57 AM
 */
public class GraphD {
	private final int MAX_VERTS = 20;
	private final int INFINITY = 1000000;
	private Vertex[] vertexList;
	private int[][] adjmat;
	private DistPar[] sPath;
	private int nVerts;
	private int nTree;
	private int currentVertex;
	private int startToCurrent;
	
	//Constructor
	public GraphD(){
		vertexList = new Vertex[MAX_VERTS];
		adjmat = new int[MAX_VERTS][MAX_VERTS];
		nVerts =0;
		nTree =0;
		for(int i =0;i<MAX_VERTS;i++){
			for(int j =0;j<MAX_VERTS;j++){
				adjmat[i][j]=INFINITY;
			}
		}
		sPath = new DistPar[MAX_VERTS];
	}
	//��ӽڵ�
	public void addVertex(String label){
		vertexList[nVerts++]=new Vertex(label);
	}
	//��ӱ�
	public void addEdge(int start,int end,int weight){
		adjmat[start][end]=weight;
	}
	//���ָ���ڵ���Ϣ
	public void displayVertex(int v){
		System.out.println(vertexList[v].label);
	}
	//�ҳ���startTree���������������·��
    /**
     * Dijkstra�㷨
     */
	public void path(){
		//�����V0��ʼ
		int startTree = 0;
		vertexList[startTree].isInTree = true;
		nTree = 1;
		//��ʼ��sPath����
		for(int i =0;i<nVerts;i++){
			int distance = adjmat[startTree][i];
			sPath[i] = new DistPar(startTree,distance);
		}
		
		while(nTree<nVerts){
			displayPaths();
			int indexMin = getMin();
			int minDist = sPath[indexMin].distance;
			
			if(minDist == INFINITY){
				System.out.println("There are unreachable vertices");
				break;
			}
			else{
				currentVertex = indexMin;
				startToCurrent = sPath[indexMin].distance;
			}
			vertexList[currentVertex].isInTree = true;
			nTree++;

			adjust_sPath();
		}
		displayPaths();
	}
	//���ص�ĿǰΪֹ��ÿ���������̾���
	public int getMin(){
		int minDist = INFINITY;
		int indexMin = 0;
		for(int i =1;i<nVerts;i++){
			if(!vertexList[i].isInTree&&sPath[i].distance<minDist){
				indexMin = i;
				minDist = sPath[i].distance;
			}
		}
		return indexMin;
	}
	//���µ���sPath����
	public void adjust_sPath(){
		for(int j=1;j<nVerts;j++){
			if(vertexList[j].isInTree){
				continue;
			}
			int currentToFringe = adjmat[currentVertex][j];
			int newDist = currentToFringe+startToCurrent;
			if(newDist<sPath[j].distance){
				sPath[j].distance=newDist;
				sPath[j].parent = currentVertex;
			}
		}
	}
	//�����V0����������·��������
	public void displayPaths(){
		
		for(int i=0;i<nVerts;i++){
			System.out.print(vertexList[0].label+"-->"+vertexList[i].label);
			if(sPath[i].distance==INFINITY)System.out.print(" : inf");
			else System.out.print(" : "+sPath[i].distance);
			String parent = vertexList[sPath[i].parent].label;
			System.out.println("("+parent+")");
		}
		System.out.println("-------------------------");
	}
	//���������㷨(Floyd)���ҳ�ÿ�Զ�������·��(����Ȩ��ֻ��Ѱ��һ�������ܷ񵽴�����㣬����Wallshall�㷨)
	public void floyd(){
		//��floyd��������ÿ�Զ���֮�����̾��룬�������丸�ڵ㣬����Ѱ�����·��
		DistPar[][] floyd = new DistPar[MAX_VERTS][MAX_VERTS];
		//��ʼ��
		for(int i=0;i<nVerts;i++){
			for(int j=0;j<nVerts;j++){
				int distance = adjmat[i][j];
				floyd[i][j] = new DistPar(-1,distance);
			}
		}
		for(int i=0;i<nVerts;i++){
			for(int j=0;j<nVerts;j++){
				int dij = floyd[i][j].distance;
				if(dij<INFINITY){
					for(int k=0;k<nVerts;k++){
						int dki = floyd[k][i].distance;
						if(dki+dij<floyd[k][j].distance){
							floyd[k][j].distance=dki+dij;
							int parent = floyd[i][j].parent==-1?i:floyd[i][j].parent;
							floyd[k][j].parent = parent;           //����Ǽ�¼·���Ĺؼ�
						}
					}
				}
			}
		}
		displayFloyd(floyd);
	}
	
	public void displayFloyd(DistPar[][] floyd){
		//display floyd[][]
		for(int i=0;i<nVerts;i++){
			System.out.println("----------------------");
			for(int j=0;j<nVerts;j++){
				/*StringBuffer str = new StringBuffer();
				int distance = floyd[i][j].distance;
				int parent = floyd[i][j].parent;
				if(parent==-1)str.append(" ");
				else str.append(vertexList[parent].label+":");
				if(distance==INFINITY)str.append("inf");
				else str.append(distance);
				System.out.print(str.toString()+"    ");*/
				System.out.print(vertexList[i].label+"-->"+vertexList[j].label+":"+floyd[i][j].distance+" ·�� ��");
				StringBuffer str = new StringBuffer();
				str.append(vertexList[j].label);
				int k = floyd[i][j].parent;
				while(k!=-1&&k!=i){
					str.append(vertexList[k].label);
					k=floyd[i][k].parent;
				}
				str.append(vertexList[i].label);
				System.out.println(str.reverse().toString());
			}
		}	
	}
	
}
