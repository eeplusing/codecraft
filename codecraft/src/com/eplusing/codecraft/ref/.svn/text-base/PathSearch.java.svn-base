package com.eplusing.codecraft.ref;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/************************************************************************************
 * @Title        : PathSearch.java
 * @Todo         :<----功能描述---->
 * @Author       : CaoPeng
 * @DateTime     : 2016年3月17日 下午10:01:28
 * @Copyright    : 2016 LLC All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class PathSearch
{
	boolean flag = true;
	// 一张地图
	static int[][] map = new int[][]// 地图数组
	{
	{ 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	{ 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	{ 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 },
	{ 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 },
	{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0 },
	{ 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 },
	{ 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 },
	{ 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	{ 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	{ 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 },
	{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 },
	{ 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0 },
	{ 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0 },
	{ 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0 },
	{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	{ 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0 },
	{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0 },
	{ 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	{ 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	{ 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	//深度优先所使用的栈
	Stack<int[][]> stack = new Stack<int[][]>();
	HashMap<String,int[][]> hm=new HashMap<String,int[][]>();//结果路径记录
	//出发点
	int[] source = {2,2};
	//目标点
	int[] target = {21,22};
	//已经访问过的点
	int[][] visited=new int[map.length][map[0].length];//0 未去过 1 去过
	//当前点的八方
	int[][] sequence={
	{0,1},{0,-1},
	{-1,0},{1,0},
	{-1,1},{-1,-1},
	{1,-1},{1,1}
	};
	//访问经过的路径总长度
	int count = 0;
	LinkedList<int[][]> queue = new LinkedList<int[][]>();

	//记录到每个点的最短路径 for Dijkstra
	HashMap<String,ArrayList<int[][]>> hmPath=new HashMap<String,ArrayList<int[][]>>();
	//记录路径长度 for Dijkstra
	int[][] length=new int[map.length][map[0].length];


	/**
	 * @param args
	 */
	public static void main(String[] args) {
	PathSearch a = new PathSearch();
	System.out.println("-----------深度优先搜索算法实现---------");
	a.DFS();
	System.out.println(a.count);

	int[] temp = a.target;
	while(true){
	int[][] tempA=a.hm.get(temp[0]+":"+temp[1]);
	System.out.println("(" + tempA[0][0] + "," + tempA[0][1] + ")" + "--" + "(" + tempA[1][0] + "," + tempA[1][1] + ")");
	if(tempA[1][0]==a.source[0]&&tempA[1][1]==a.source[1]){//判断有否到出发点
	break;
	}
	temp=tempA[1];
	}

	// for(int i = 0; i < a.stack.size(); i++){
	// System.out.println("(" + a.stack.get(i)[0][0] + "," + a.stack.get(i)[0][1] + ")" + "--" + "(" + a.stack.get(i)[1][0] + "," + a.stack.get(i)[1][1] + ")");
	// }
	a.visited=new int[map.length][map[0].length];
	System.out.println("-----------广度优先搜索算法实现---------");
	a.BFS();
	System.out.println(a.count);
	temp = a.target;
	while(true){
	int[][] tempA=a.hm.get(temp[0]+":"+temp[1]);
	System.out.println("(" + tempA[0][0] + "," + tempA[0][1] + ")" + "--" + "(" + tempA[1][0] + "," + tempA[1][1] + ")");
	if(tempA[1][0]==a.source[0]&&tempA[1][1]==a.source[1]){//判断有否到出发点
	break;
	}
	temp=tempA[1];
	}
	// for(int i = 0; i < a.queue.size(); i++){
	// System.out.println("(" + a.queue.get(i)[0][0] + "," + a.queue.get(i)[0][1] + ")" + "--" + "(" + a.queue.get(i)[1][0] + "," + a.queue.get(i)[1][1] + ")");
	// }

	a.visited=new int[map.length][map[0].length];
	System.out.println("----------Dijkstra算法实现--------------");
	a.Dijkstra();
	System.out.println(a.count);
	ArrayList<int[][]> alPath=a.hmPath.get(a.target[0]+":"+a.target[1]);
	for(int[][] t: alPath){
	System.out.println("(" + t[0][0] + "," + t[0][1] + ")" + "--" + "(" + t[1][0] + "," + t[1][1] + ")");
	}

	}


	/**
	 * 深度优先搜索算法
	 */
	public void DFS() {
	//开始路径
	int[][] start = {{source[0],source[1]},{source[0],source[1]}};
	stack.push(start);
	while(flag){
	int[][] currentEdge=stack.pop();//从栈顶取出边
	int[] tempTarget=currentEdge[1];//取出此边的目的点
	//判断目的点是否去过，若去过则直接进入下次循环
	if(visited[tempTarget[1]][tempTarget[0]]==1){
	continue;
	}
	count++;
	visited[tempTarget[1]][tempTarget[0]]=1;//标识目的点为访问过
	//记录此临时目的点的父节点
	hm.put(tempTarget[0]+":"+tempTarget[1],new int[][]{currentEdge[1],currentEdge[0]});
	//判断有否找到目的点
	if(tempTarget[0]==target[0]&&tempTarget[1]==target[1]){
	break;
	}
	//将所有可能的边入栈
	int currCol=tempTarget[0];
	int currRow=tempTarget[1];
	//将它的周围相连点都加入到栈中
	for(int[] rc:sequence){
	int i=rc[1];
	int j=rc[0];
	if(i==0&&j==0){continue;}
	if(currRow+i>=0&&currRow+i<map.length&&currCol+j>=0&&currCol+j<map[0].length&&
	map[currRow+i][currCol+j]!=1){
	//将此边入栈
	int[][] tempEdge={
	{tempTarget[0],tempTarget[1]},
	{currCol+j,currRow+i}
	};
	stack.push(tempEdge);
	}
	}
	}
	}

	/**
	 * 深度优先算法
	 */
	public void BFS(){
	count = 0;
	hm.clear();

	//开始路径
	int[][] start = {{source[0],source[1]},{source[0],source[1]}};
	queue.offer(start);
	while(flag){
	int[][] currentEdge=queue.poll();//从队首取出边
	int[] tempTarget=currentEdge[1];//取出此边的目的点
	//判断目的点是否去过，若去过则直接进入下次循环
	if(visited[tempTarget[1]][tempTarget[0]]==1){
	continue;
	}
	count++;
	visited[tempTarget[1]][tempTarget[0]]=1;//标识目的点为访问过
	//记录此临时目的点的父节点
	hm.put(tempTarget[0]+":"+tempTarget[1],new int[][]{currentEdge[1],currentEdge[0]});
	//判断有否找到目的点
	if(tempTarget[0]==target[0]&&tempTarget[1]==target[1]){
	break;
	}
	//将所有可能的边入队列
	int currCol=tempTarget[0];
	int currRow=tempTarget[1];
	for(int[] rc:sequence){
	int i=rc[1];
	int j=rc[0];
	if(i==0&&j==0){continue;}
	if(currRow+i>=0&&currRow+i<map.length
	&&currCol+j>=0&&currCol+j<map[0].length&&
	map[currRow+i][currCol+j]!=1){
	int[][] tempEdge={
	{tempTarget[0],tempTarget[1]},
	{currCol+j,currRow+i}
	};
	queue.offer(tempEdge);
	}
	}
	}
	}

	/**
	 * 广度优先A*算法
	 */
	public void BFSAStar(){

	}

	/**
	 * Dijkstra算法
	 */
	public void Dijkstra(){

	for(int i=0;i<length.length;i++){
	for(int j=0;j<length[0].length;j++){
	length[i][j]=9999;//初始路径长度为最大距离都不可能的那么大
	}
	}
	count = 0;

	int[] start={source[0],source[1]};//开始点col,row
	visited[source[1]][source[0]]=1;
	for(int[] rowcol:sequence){//计算此点所有可以到达点的路径及长度

	int trow=start[1]+rowcol[1];
	int tcol=start[0]+rowcol[0];
	//超出地图
	if(trow<0||trow>18||tcol<0||tcol>18)continue;
	//碰到墙壁
	if(map[trow][tcol]!=0)continue;
	//记录路径长度
	length[trow][tcol]=1;
	//计算路径  
	String key=tcol+":"+trow;
	ArrayList<int[][]> al=new ArrayList<int[][]>();
	al.add(new int[][]{{start[0],start[1]},{tcol,trow}});
	hmPath.put(key,al);
	count++;  
	}
	//bre跳出循环标记
	bre:while(flag){
	//找到当前扩展点K 要求扩展点K为从开始点到此点目前路径最短，且此点未考察过
	int[] k=new int[2];
	int minLen=9999;
	for(int i=0;i<visited.length;i++){
	for(int j=0;j<visited[0].length;j++){
	if(visited[i][j]==0){
	if(minLen>length[i][j]){
	minLen=length[i][j];
	k[0]=j;//col
	k[1]=i;//row
	}
	}
	}
	}
	visited[k[1]][k[0]]=1;//设置去过的点
	int dk=length[k[1]][k[0]];//取出开始点到K的路径长度
	ArrayList<int[][]> al=hmPath.get(k[0]+":"+k[1]);//取出开始点到K的路径
	//循环计算所有K点能直接到的点到开始点的路径长度
	for(int[] rowcol:sequence){
	int trow=k[1]+rowcol[1];//计算出新的要计算的点的坐标
	int tcol=k[0]+rowcol[0];
	//若要计算的点超出地图边界或地图上此位置为障碍物则舍弃考察此点
	if(trow<0||trow>map.length-1||tcol<0||tcol>map[0].length-1)continue;
	if(map[trow][tcol]!=0)continue;
	int dj=length[trow][tcol];//取出开始点到此点的路径长度
	int dkPluskj=dk+1;//计算经K点到此点的路径长度
	//若经K点到此点的路径长度比原来的小则修改到此点的路径
	if(dj>dkPluskj){
	String key=tcol+":"+trow;
	//克隆开始点到K的路径
	ArrayList<int[][]> tempal=(ArrayList<int[][]>)al.clone();
	//将路径中加上一步从K到此点
	tempal.add(new int[][]{{k[0],k[1]},{tcol,trow}});
	//将此路径设置为从开始点到此点的路径
	hmPath.put(key,tempal);
	//修改到从开始点到此点的路径长度
	length[trow][tcol]=dkPluskj;
	//若此点从未计算过路径长度则将此点加入考察过程记录
	if(dj==9999){//将去过的点记录
	count++;
	}
	}
	//看是否找到目的点
	if(tcol==target[0]&&trow==target[1]){
	//终止循环
	break bre;
	}
	}
	}
}
