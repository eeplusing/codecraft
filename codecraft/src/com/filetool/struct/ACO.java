package com.filetool.struct;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
