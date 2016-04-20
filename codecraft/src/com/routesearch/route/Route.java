package com.routesearch.route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.filetool.struct.ACO;
import com.filetool.struct.ArcNode;
import com.filetool.struct.Head;

public final class Route 
{
	/**
	 * 你需要完成功能的入口
	 * 
	 * @author XXX
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
		
		/*if(headMap.keySet().size()<=20){
			dfs(headMap.get(start),path,points);
		}
		else{
			antSearch();
		}*/
		
		dfs(headMap.get(start),path,points);
		//antSearch();
		
		StringBuilder sb=new StringBuilder();
		if(minpath==null||minpath.size()==0)
		{
			return "NA";
		}
		else
		{
			for(int r:minpath)
			{
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
			
			Head head = new Head();
			head.setId(begin);
			ArcNode node = new ArcNode();
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
					Head endHead=new Head();
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
		ACO aco=new ACO(cityNum,antNum,Max_Gen,a,b,r,searchCount,vIndex,start,end);
		aco.init(distanceMatrix,headMap);
		aco.solve();
		minpath=aco.getBestPath();
		
	}
}