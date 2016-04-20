/**
 * 实现代码文件
 * 
 * @author HUANGWEI
 * @since 2016-3-4
 * @version V1.0
 */
package com.routesearch.route1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

public final class Route
{
	
    /**
     * 你需要完成功能的入口
     * 
     * @author HUANGWEI
     * @since 2016-3-4
     * @version V1
     */
	
	private static Map<Integer, Head> headMap = new HashMap<Integer, Head>();
	private static int[][] distanceMatrix;
	private static int start;
	private static int end;
	private static int[] vIndex;
	private static int minCost = Integer.MAX_VALUE;
	private static List<Integer> minpath;
	private static int[] costs;
	
    public static String searchRoute(String graphContent, String condition)
    {
    	
    	initParmater(graphContent, condition);
		List<Integer> path=new ArrayList<Integer>();
		List<Integer>points=new ArrayList<Integer>();
		
		if(headMap.keySet().size()<=20)
		{
			dfs(headMap.get(start),path,points);
		}
		else
		{
			antSearch();
		}
		
		dfs(headMap.get(start),path,points);
		
		StringBuilder sb=new StringBuilder();
		if(minpath==null||minpath.size()==0){
			return "NA";
		}
		else{
			for(int r:minpath){
				sb.append(r);
				sb.append("|");
			}
			sb.delete(sb.lastIndexOf("|"), sb.length());
		}
		return sb.substring(0, sb.length());
    }

	private static void initParmater(String graphContent, String condition)
	{
		start = Integer.parseInt(condition.split(",")[0]);
		end = Integer.parseInt(condition.split(",")[1]);
		
		String[] v = condition.split(",")[2].split("\\|");
		vIndex = new int[v.length];
		
		costs = new int[4801];
		distanceMatrix=new int[601][601];
		
		for (int i = 0; i < v.length; i++) 
		{
			vIndex[i] = Integer.parseInt(v[i].trim());
		}
		
		String[] routes = graphContent.split("\n");
		for (String route : routes) 
		{
			String info[] = route.split(",");
			int cost = Integer.parseInt(info[3].trim());
			int pathId = Integer.parseInt(info[0].trim());
			int to = Integer.parseInt(info[2].trim());
			int begin = Integer.parseInt(info[1].trim());
			
			if(begin == to)continue;
			distanceMatrix[begin][to]=cost;
			distanceMatrix[begin][begin]=0;
			distanceMatrix[to][to]=0;
			costs[pathId] = cost;
			
			Head head = new Route().new Head();
			head.setId(begin);
			ArcNode node = new Route().new ArcNode();
			node.setCost(cost);
			node.setId(pathId);
			node.setEnd(to);
			node.setNext(null);
			if (headMap.containsKey(begin)) 
			{
				Head h = headMap.get(begin);
				ArcNode cur = h.getNext();
				ArcNode pre = node;
				if(cur==null)
				{
					h.setNext(node);
				}
				else
				{
					if ( node.getCost() < cur.getCost()) 
					{
						h.setNext(node);
						node.setNext(cur);
					} 
					else 
					{
						while (cur != null) 
						{
							if (to == cur.getEnd()) 
							{
								cur.setCost(Math.min(cost, cur.getCost()));
							} 
							else 
							{
								if (node.getCost() < cur.getCost()) 
								{
									pre.setNext(node);
									node.setNext(cur);
									break;
								}
							}
							pre = cur;
							cur = cur.getNext();
						}
						if (cur == null) 
						{
							pre.setNext(node);
						}
					}
				}
			}
			else
			{
				head.setNext(node);
				headMap.put(begin, head);
				if(!headMap.containsKey(to))
				{
					Head endHead=new Route().new Head();
					endHead.setId(to);
					headMap.put(to, endHead);
				}
			}
		}
		
	}
	
	private static void dfs(Head head, List<Integer> path, List<Integer> points) 
	{
		if(head==null)
		{
			return; 
		}
		
		if (head.getId() == end) 
		{
			for (int p : vIndex) 
			{
				if (!points.contains(p)) 
				{
					return;
				}
			}
			int sum = 0;
			for (int id : path) 
			{
				sum += costs[id];
			}
			if (sum < minCost) 
			{
				minpath=new ArrayList<Integer>();
				for(int i=0;i<path.size();i++)
				{
					minpath.add(path.get(i));
				}
				
				minCost = sum;
			}
		} 
		else 
		{
			ArcNode cur = head.getNext();
			if (cur == null) 
			{
				return;
			} 
			else 
			{
				while (cur != null) 
				{
					int to=cur.getEnd();
					if (!points.contains(to)) 
					{
                          path.add(cur.getId());
                          points.add(to);
                          dfs(headMap.get(to),path,points);
                          path.remove((Integer)cur.getId());
                          points.remove((Integer)to);
					}
					cur=cur.getNext();
				}
			}
		}
	}
	
	private  static void antSearch()
	{
		int cityNum=headMap.keySet().size();
		System.out.println(cityNum);
		int antNum=(int) (cityNum*10);
		int Max_Gen=20;
		float a= 0.8f;
		float b=0.7f;
		float r=0.8f;
		int searchCount=cityNum*2;
		ACO aco=new Route().new ACO(cityNum,antNum,Max_Gen,a,b,r,searchCount,vIndex,start,end);
		aco.init(distanceMatrix,headMap);
		aco.solve();
		minpath=aco.getBestPath();
		
	}

	public class Head
	{
		private int id;
		private ArcNode next;
		private boolean isVisited;

		public boolean isVisited() {
			return isVisited;
		}
		public void setVisited(boolean isVisited) {
			this.isVisited = isVisited;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public ArcNode getNext() {
			return next;
		}
		public void setNext(ArcNode next) {
			this.next = next;
		}
	}
	
	public class ArcNode
	{
		private int id;
		private int cost;
		private ArcNode next;
		private int end;

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getCost() {
			return cost;
		}
		public void setCost(int cost) {
			this.cost = cost;
		}
		public ArcNode getNext() {
			return next;
		}
		public void setNext(ArcNode next) {
			this.next = next;
		}
		public int getEnd() {
			return end;
		}
		public void setEnd(int end) {
			this.end = end;
		}
	}
	
	public class ACO {
		private Ant[] ants;
		private int antNum;
		private int cityNum;
		private int Max_Gen;
		private float[][] pheromone;
		private int[][] distance;
		private int bestLength;
		private float alpha;  
		private float beta;  
		private float rho;  
		private List<Integer> bestPath;
		private Map<Integer,Head> neibor;
		private int[] index;
		private int start;
		private int end;
		private int searchCount;
		public ACO(int n, int m, int g, float a, float b, float r,int searchcount,int[] vIndex,int start,int end) {  
		    cityNum = n;  
		    antNum = m;  
		    ants = new Ant[antNum];  
		    Max_Gen = g;  
		    alpha = a;  
		    beta = b;  
		    rho = r;
		    index=vIndex;
		    this.start=start;
		    this.end=end;
		    this.searchCount=searchcount;
		}
		private void initfix(int[][] distanceMatrix,Map<Integer,Head>headMap){
			
		   
		      
		    distance =distanceMatrix;  
		   
		    neibor=headMap;
		    
		    // 计算距离矩阵  
		    // 针对具体问题，距离计算方法也不一样，此处用的是att48作为案例，它有48个城市，距离计算方法为伪欧氏距离，最优值为10628  

		    pheromone = new float[cityNum][cityNum];  
		    for (int i:  headMap.keySet()) {  
		        for (int j: headMap.keySet()) {  
		            pheromone[i][j] = 0.1f; // 初始化为0.1  
		        }  
		    }  
		    bestLength = Integer.MAX_VALUE;  
		    bestPath= new ArrayList<Integer>();  
		    // 随机放置蚂蚁  
		    for (int i = 0; i < antNum; i++) {  
		        ants[i] = new Ant(cityNum);  
		        ants[i].initfixAnt(distance, alpha, beta,headMap,index,start,end);  
		    }  
		}
		public void init(int[][] distanceMatrix,Map<Integer,Head>headMap) {  
		    // 读取数据       
		    distance =distanceMatrix;    
		    neibor=headMap;
		    // 计算距离矩阵  
		    // 针对具体问题，距离计算方法也不一样，此处用的是att48作为案例，它有48个城市，距离计算方法为伪欧氏距离，最优值为10628  

		    pheromone = new float[cityNum][cityNum];  
		    for (int i:  headMap.keySet()) {  
		        for (int j: headMap.keySet()) {  
		            pheromone[i][j] = 0.1f; // 初始化为0.1  
		        }  
		    }  
		    bestLength = Integer.MAX_VALUE;  
		    bestPath= new ArrayList<Integer>();  
		    // 随机放置蚂蚁  
		    for (int i = 0; i < antNum; i++) {  
		        ants[i] = new Ant(cityNum);  
		        ants[i].init(distance, alpha, beta,headMap,index,start,end);  
		    }  
		} 
		public void solve(){
			for (int g = 0; g < Max_Gen; g++) {
				       for (int i = 0; i < antNum; i++) { 
				         for (int j = 1; j < searchCount; j++) {
				           if(ants[i].getAllowedNodes().size()==0)break;
				           ants[i].selectNextNode(pheromone);
				         }
				         
				         //更新每只蚂蚁的信息素
				         if(ants[i].getLength()>0){
				        	
				         for (int j = 0; j < ants[i].getTabu().size()-1; j++) {
				           ants[i].getDelta()[ants[i].getTabu().get(j)][ants[i].getTabu().get(j+1)] = (float) ((ants[i].getVisted())*index.length/Math.pow(ants[i].getLength(),5));
				          
				         }
				         }
				         else{
				        	 for (int j = 0; j < ants[i].getTabu().size()-1; j++) {
						           ants[i].getDelta()[ants[i].getTabu().get(j)][ants[i].getTabu().get(j+1)] = 0;
						          
						         }
				         }
				       }
				       
				       //更新信息素
				       updatePheromone();
				       
				       //重新初始化蚂蚁
				           for(int i=0;i<antNum;i++){  
				              
				               ants[i].init(distance, alpha, beta,neibor,index,start,end);  
				           }  
				     }
		    for(int i=0;i<antNum;i++){  
		        
		        ants[i].initfixAnt(distance, alpha, beta,neibor,index,start,end);  
		    } 
			for (int g = 0; g < Max_Gen; g++) {
			       for (int i = 0; i < antNum; i++) { 
			    	   System.out.println("ants "+i);
			         for (int j = 1; j < searchCount; j++) {
			           if(ants[i].getAllowedNodes().size()==0)break;
			           ants[i].selectNextNode(pheromone);
			           if(ants[i].isSuccess()){
			        	   if(ants[i].getLength()<bestLength){
			        		   bestPath=ants[i].getPath();
			        		   break;
			        	   }
			           }
			         }
			        
			         //更新每只蚂蚁的信息素
			         if(ants[i].getLength()>0){
			         for (int j = 0; j < ants[i].getTabu().size()-1; j++) {
			           ants[i].getDelta()[ants[i].getTabu().get(j)][ants[i].getTabu().get(j+1)] = (float) ((ants[i].getVisted())/Math.pow(ants[i].getLength(),5));
			         
			         }
			         }
			         else{
			        	 for (int j = 0; j < ants[i].getTabu().size()-1; j++) {
					           ants[i].getDelta()[ants[i].getTabu().get(j)][ants[i].getTabu().get(j+1)] = 0;
					          
					         }
			         }
			       }
			       
			       //更新信息素
			       updatePheromone();
			       
			       //重新初始化蚂蚁
			           for(int i=0;i<antNum;i++){  
			              
			               ants[i].initfixAnt(distance, alpha, beta,neibor,index,start,end);  
			           }  
			     }
				     
		}
		   private void updatePheromone(){
		     //信息素挥发  
			 
		         for(int i:neibor.keySet())  
		             for(int j:neibor.keySet())  
		                pheromone[i][j]=pheromone[i][j]*(1-rho);  
		         //信息素更新  
		         for(int i:neibor.keySet()){  
		             for(int j:neibor.keySet()){  
		                 for (int k = 0; k < antNum; k++) {
		           pheromone[i][j] += ants[k].getDelta()[i][j];
		         } 
		             }  
		         }  
		   }
		public List<Integer> getBestPath() {
			return bestPath;
		}
		public void setBestPath(List<Integer> bestPath) {
			this.bestPath = bestPath;
		}
	}
	
	
	public class Ant implements Cloneable {
		private List<Integer> tabu;
		private List<Integer> allowedNodes;
		private List<Integer> path;
		private int currentNode;
		private float[][] delta;
		private int[][] distance;
		private int visted;
		private int[] vIndex;
		private int firstCity;
		private int start;
		private int end;
		private Map<Integer,Integer> pathMap;
		public int getFirstCity() {
			return firstCity;
		}

		public void setFirstCity(int firstCity) {
			this.firstCity = firstCity;
		}

		public int getCityNum() {
			return cityNum;
		}

		public void setCityNum(int cityNum) {
			this.cityNum = cityNum;
		}

		public Map<Integer, Head> getHeadMap() {
			return headMap;
		}

		public void setHeadMap(Map<Integer, Head> headMap) {
			this.headMap = headMap;
		}

		private int vistedCount;
		private float alpha;
		private float belta;
		private int length;
		private boolean success;
		private int cityNum;
		private Map<Integer, Head> headMap;

		public Ant(int cityNum) {
			// TODO Auto-generated constructor stub
			this.cityNum=cityNum;
		}

		public List<Integer> getTabu() {
			return tabu;
		}

		public void setTabu(Vector<Integer> tabu) {
			this.tabu = tabu;
		}

		public List<Integer> getAllowedNodes() {
			return allowedNodes;
		}

		public void setAllowedNodes(Vector<Integer> allowedNodes) {
			this.allowedNodes = allowedNodes;
		}

		public int getCurrentNode() {
			return currentNode;
		}

		public void setCurrentNode(int currentNode) {
			this.currentNode = currentNode;
		}

		public float[][] getDelta() {
			return delta;
		}

		public void setDelta(float[][] delta) {
			this.delta = delta;
		}

		public int[][] getDistance() {
			return distance;
		}

		public void setDistance(int[][] distance) {
			this.distance = distance;
		}

		public int getVistedCount() {
			return vistedCount;
		}

		public void setVistedCount(int vistedCount) {
			this.vistedCount = vistedCount;
		}

		public float getAlpha() {
			return alpha;
		}

		public void setAlpha(float alpha) {
			this.alpha = alpha;
		}

		public float getBelta() {
			return belta;
		}

		public void setBelta(float belta) {
			this.belta = belta;
		}

		public int getLength() {
			
			return calculateTourLength();
		}

		public void setLength(int length) {
			this.length = length;
		}

		public List<Integer> getPath() {
			return path;
		}

		public void setPath(Vector<Integer> path) {
			this.path = path;
		}

		public boolean isSuccess() {
			if(visted<vIndex.length){
				 return false;
			}
			if(tabu.get(tabu.size()-1)==end)return true;
			return false;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}
	public void initfixAnt(int[][] distance, float a, float b,
			Map<Integer, Head> headMap,int[] index,int start,int end){
		this.start=start;
		this.end=end;
		vIndex=index;
		visted=0;
		alpha = a;
		belta = b;
		allowedNodes = new ArrayList<Integer>();
		tabu = new ArrayList<Integer>();
		path=new ArrayList<Integer>();
		this.distance = distance;
		this.headMap = headMap;
		delta = new float[cityNum][cityNum];
		for (int i : headMap.keySet()) {
			for (int j : headMap.keySet()) {
				delta[i][j] = 0;
			}
		}
		
		firstCity=start;
		currentNode = firstCity;
		 Head head=headMap.get(currentNode);
		    allowedNodes=new ArrayList<Integer>();
		    ArcNode cur=head.getNext();
		    int pathId=0;
		    pathMap=new HashMap<Integer,Integer>();
		    while(cur!=null){
		    	pathId=cur.getId();
		    	allowedNodes.add(cur.getEnd());
		    	pathMap.put(cur.getEnd(), pathId);
		    	cur=cur.getNext();
		    }
		    for(Integer i :allowedNodes){
		    	if(tabu.contains(i)){
		    		allowedNodes.remove(i);
		    	}
		    }
		tabu.add(firstCity);
		
		
		
	}
		public void init(int[][] distance, float a, float b,
				Map<Integer, Head> headMap,int[]index,int start,int end) {
			this.start=start;
			this.end=end;
			vIndex=index;
			visted=0;
			alpha = a;
			belta = b;
			allowedNodes = new ArrayList<Integer>();
			tabu = new ArrayList<Integer>();
			path=new ArrayList<Integer>();
			this.distance = distance;
			this.headMap = headMap;
			delta = new float[cityNum][cityNum];
			for (int i : headMap.keySet()) {
			
				for (int j : headMap.keySet()) {
					delta[i][j] = 0;
				}
			}
			Random random = new Random(System.currentTimeMillis());
			
			Integer firstCity = random.nextInt(cityNum);
			
			if (tabu.contains(firstCity)) {
			   allowedNodes.remove(firstCity);
				}
				
			tabu.add(firstCity);
			currentNode = firstCity;
			Head head=headMap.get(currentNode);
			 ArcNode cur=head.getNext();
			 allowedNodes=new ArrayList<Integer>();
			    int pathId=0;
			    pathMap=new HashMap<Integer,Integer>();
			    while(cur!=null){
			    	pathId=cur.getId();
			    	allowedNodes.add(cur.getEnd());
			    	pathMap.put(cur.getEnd(), pathId);
			    	cur=cur.getNext();
			    }
			    for(Iterator<Integer> iter=allowedNodes.iterator();iter.hasNext();){
			    	Integer i=iter.next();
			    	if(tabu.contains(i)){
			    		iter.remove();
			    	}
			    }
			this.firstCity=firstCity;
		}

		private int calculateTourLength() {
			int len = 0;

			for (int i = 0; i < tabu.size()-1; i++) {
				len += distance[this.tabu.get(i)][this.tabu.get(i + 1)];
			}
			return len;
		}

		public void selectNextNode(float[][] pheromone) {
			float[] p = new float[601];
			float sum = 0.f;
			if(allowedNodes.size()==0)return;
			for (Integer i : allowedNodes) {
				
				sum += Math.pow(pheromone[currentNode][i], alpha)
						* Math.pow(1.0 / distance[currentNode][i], belta);
			}
			// 计算概率矩阵
			for (int i :headMap.keySet()) {
				boolean flag = false;
				for (Integer j : allowedNodes) {
					if (i == j.intValue()) {
						p[i] = (float) (Math.pow(pheromone[currentNode][i], alpha) * Math
								.pow(1.0 / distance[currentNode][i], belta)) / sum;
						flag = true;
						break;
					}
				}
				if (flag == false) {
					p[i] = 0.f;
				}
			}
			Random random=new Random(System.currentTimeMillis());
			float selectP=random.nextFloat();
			Integer selectNode=0;
			float sump=0.f;
			for(int i:allowedNodes){		
				sump+=p[i];
				if(sump>selectP){
					selectNode=i;
					break;
				}
			}
			System.out.println("selected "+selectNode);
			allowedNodes.remove(selectNode);
			tabu.add(selectNode);
			currentNode=selectNode;
			for(int i:vIndex){
			if(i==currentNode){
			visted++;	
			}
			}
			path.add(pathMap.get(currentNode));
			
		    Head head=headMap.get(currentNode);
		    allowedNodes=new Vector<Integer>();
		    pathMap=new HashMap<Integer,Integer>();
		    ArcNode cur=head.getNext();
		    int pathId=0;
		    while(cur!=null){
		    	pathId=cur.getId();
		    	allowedNodes.add(cur.getEnd());
		    	pathMap.put(cur.getEnd(), pathId);
		    	cur=cur.getNext();
		    	
		    }
		    for(Iterator<Integer> iter=allowedNodes.iterator();iter.hasNext();){
		    	Integer i=iter.next();
		    	if(tabu.contains(i)){
		    		iter.remove();
		    	}
		    }
			}

		public int getVisted() {
			return visted;
		}

		public void setVisted(int visted) {
			this.visted = visted;
		}

		
	}

}
	
	