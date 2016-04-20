package com.filetool.struct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

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
