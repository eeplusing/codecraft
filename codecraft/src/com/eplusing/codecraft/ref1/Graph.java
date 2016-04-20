package com.eplusing.codecraft.ref1;

import com.eplusing.codecraft.ref1.PriorityQ;

/**
 * 
 */
public class Graph {
	private final int MAX_VERTS = 20;
	private final int INFINITY = 1000000;
	private Vertex[] vertexList;
	private int[][] adjmat;
	private int nVerts;
	private int currentVert;
	private PriorityQ thePQ;
	private int nTree;
	
	public Graph(){
		//��ʼ��
		vertexList = new Vertex[MAX_VERTS];
		nVerts = 0;
		adjmat = new int[MAX_VERTS][MAX_VERTS];
		for(int i =0;i<MAX_VERTS;i++){
			for(int j =0;j<MAX_VERTS;j++){
				adjmat[i][j]=INFINITY;
			}
		}
		thePQ = new PriorityQ();
	}
	//��ӽڵ�
	public void addVertex(String label){
		vertexList[nVerts++]=new Vertex(label);
	}
	//��ӱ�
	public void addEdge(int start,int end,int weight){
		adjmat[start][end]=weight;
		adjmat[end][start]=weight;         //��ע������������ͼ
	}
	//���ָ���ڵ���Ϣ
	public void displayVertex(int v){
		System.out.println(vertexList[v].label);
	}
	//��Ȩ������ͼ��С�������prim�㷨
	public void mstw(){
		currentVert = 0;
		int d=0;      //���·������
		while(nTree<nVerts-1){
			vertexList[currentVert].isInTree=true;
			nTree++;
			for(int i =0;i<nVerts;i++){
				if(i==currentVert)continue;
				if(vertexList[i].isInTree)continue;
				int distance = adjmat[currentVert][i];
				if(distance==INFINITY)continue;
				putInPQ(i,distance);
			}
			if(thePQ.size()==0){
				System.out.println("ERROR,GRAPH IS NOT CONNECT!");
				return ;
			}
			Edge edge = thePQ.removeMin();
            currentVert = edge.endVert;
            d += edge.distance;
            //��� 
			int srcVertex = edge.srcVert;
			int endVertex = edge.endVert;
			System.out.print(vertexList[srcVertex].label);
			System.out.print(vertexList[endVertex].label);
			System.out.println();
		}
		System.out.println("���·������Ϊ:"+d);
		//����ͼ�ڵ�״̬
		for(int i =0;i<nVerts;i++){
			vertexList[i].isInTree=false;
		}
	}
	//ͨ��÷�����ʹֻ��һ������ĳ���ض�����ı�
	public void putInPQ(int j,int distance){
		int old = thePQ.find(j);
		Edge edge;
		//������Ѿ����ڵ��������м����е�һ������C�ıߣ���������ߵ�distance�������¶��㵽C��distance����ô��ɾ���ϱߣ������±�
		if(old!=-1){
			Edge oldEdge = thePQ.peekN(old);
			if(oldEdge.distance>distance){
				thePQ.removeN(old);
				edge = new Edge(currentVert,j,distance);
				thePQ.insert(edge);
			}
		}
		//�����ڵ�C�ıߣ��Ͳ������¶��㵽C�ı�
		else{
			edge = new Edge(currentVert,j,distance);
			thePQ.insert(edge);
		}
	}
	//Kruskal�㷨����С�����
	public void mstw2(){
		Edge[] edge = new Edge[nVerts*nVerts];
		int nEdge = 0;
		int d =0;
		StringBuffer path = new StringBuffer();
		//˫��ѭ��ɨ�����еı�
		for(int i =0;i<nVerts;i++){
			for(int j=0;j<nVerts;j++){
				if(adjmat[i][j]==INFINITY)continue;
				edge[nEdge++]=new Edge(i,j,adjmat[i][j]);
			}
		}
		//����adjmat��������¼��ͨ��
		for(int i =0;i<nVerts;i++){
			for(int j=0;j<nVerts;j++){
				adjmat[i][j]=0;
			}
		}
		//�ѱ�����
		sortEdge(edge,nEdge);
		//Kruskral�ؼ�
		for(int k=0;k<nEdge;k++){
			int i = edge[k].srcVert;
			int j = edge[k].endVert;
			if(adjmat[i][j]==1)continue;
			adjmat[i][j] = 1;
			d+=edge[k].distance;
			path.append(vertexList[i].label).append(vertexList[j].label).append("-->");
		//���֪�����������ߺ���γɻ�·
			for(int l = 0;l<nVerts;l++){
					if(adjmat[l][i]==1){
						adjmat[l][j]=1;
						for(int m =0;m<nVerts;m++){
							if(adjmat[j][m]==1){
								adjmat[l][m]=1;
								adjmat[i][m]=1;
							}
						}
					}
				}
			
		}
		System.out.println("·��:"+path.toString());
		System.out.println("���·������Ϊ:"+d);
		}
	//�����бߴ�С��������:ð������
	public void sortEdge(Edge[] edge,int nEdge){
		for(int i =0;i<nEdge;i++){
			for(int j =0;j<nEdge-i-1;j++){
				if(edge[j].distance>edge[j+1].distance){
					Edge temp = edge[j];
					edge[j]=edge[j+1];
					edge[j+1]=temp;
				}
			}
		}
	}
}
