/**
 * 实现代码文件
 * 
 * @author HUANGWEI
 * @since 2016-3-4
 * @version V1.0
 */
package com.routesearch.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class Route_hw
{
	
    /**
     * 你需要完成功能的入口
     * 
     * @author HUANGWEI
     * @since 2016-3-4
     * @version V1
     */
	
    public static String searchRoute(String graphContent, String condition)
    {
    	
    	Date dt= new Date();
    	Long startcalendar= dt.getTime();//这就是距离1970年1月1日0点0分0秒的毫秒数
    	
    	List<String[]> ShortestWays = new ArrayList<String[]>();
    	List<String[]> theShortestWays = new ArrayList<String[]>();
    	String[] theShortestWay = new String[5]; //最短路径
    	String flag = "pass"; //判断存不存在这样的路径 pass存在，stop不存在
    	List<String> passMiddleNecessaryPoints = new ArrayList<String>(); //原点第一次经过的的必须点（不包含终点）
    	Map<String,List<String> > oldChuDu = new HashMap<String,List<String>>();//原出度
    	Map<String,List<String> > chuDu = new HashMap<String,List<String>>();//出度
    	Map<String,List<String> > ruDu = new HashMap<String,List<String>>();//入度
    	String[] graphContents = graphContent.split("\n");
    	List<String> middlePoints = new ArrayList<String>();//所有未确定经过的点
    	List<String> passIds = new ArrayList<String>();//规定经过的中间节点
    	int checkPassId = 0 ;
    	
    	for(String passId : (condition.split("\n")[0].split(",")[2].split("\\|")))
    	{
    		passIds.add(passId);
    	}
    	
    	for(String graphColumn : graphContents)
    	{
    		if(!graphColumn.split(",")[2].equals(condition.split(",")[0])
    				&&!graphColumn.split(",")[2].equals(condition.split(",")[1])
   						&&!middlePoints.contains(graphColumn.split(",")[2]))//开始添加默认的为指定点
    		{
    			middlePoints.add(graphColumn.split(",")[2]);
    			if(passIds.contains(graphColumn.split(",")[2]))
    			{
    				checkPassId = checkPassId+1;
    			}
    		}

    		String sourceID = graphColumn.split(",")[1];
    		String endID = graphColumn.split(",")[2];
    		
    		if(oldChuDu.get(sourceID) == null)
    		{
    			List<String> list = new ArrayList<String> (); 
    			list.add(graphColumn);
    			oldChuDu.put(sourceID, list);
    		}
    		else
    		{
    			List<String> currentList = oldChuDu.get(sourceID);
    			currentList.add(graphColumn);
    			oldChuDu.put(sourceID, currentList);
    		}
    		if(ruDu.get(endID) == null)
    		{
    			List<String> list = new ArrayList<String> (); 
    			list.add(graphColumn);
    			ruDu.put(endID, list);
    		}
    		else
    		{
    			List<String> currentList = ruDu.get(endID);
    			currentList.add(graphColumn);
    			ruDu.put(endID, currentList);
    		}
    	}

    	String sourceId = condition.split(",")[0];
    	String destinationId = condition.split(",")[1];
    	
    	List<String> excludePoints = new ArrayList<String>();
    	excludePoints.add(sourceId);
    	excludePoints.add(destinationId);
    	
    	chuDu = delete(excludePoints, oldChuDu, ruDu);
    	
    	if(checkPassId == passIds.size())
    	{
    		
    		if(!passIds.contains(sourceId)&&!passIds.contains(destinationId))
    		{
    			String sourceFlag = "pass";
    			Map<String,List<String[]>> pointToWay = new HashMap<String,List<String[]>>();//每个点的链路状态
    			
    			List<String> V = new ArrayList<String>();//已确定的店
    			List<String[]> Vways = new ArrayList<String[]>();//已确定的最短路径
    			Map<String,String[]> Uways = new HashMap<String,String[]> ();//中间经过的暂时最短路径
    			
    			//初始路径
    			V.add(sourceId);
    			String[] startPrimaryWay = {sourceId,"",sourceId,"",0+""};
    			Vways.add(startPrimaryWay);
    			if(chuDu.get(V.get(V.size()-1)) != null)
    			{
    				while(sourceFlag.equals("pass"))
    				{
    					//U集合中存在的起始最短路径
    					for(String chuDuWay : chuDu.get(V.get(V.size()-1)))
    					{
    						if((Uways.get(chuDuWay.split(",")[2]) == null
    								||Integer.parseInt(Uways.get(chuDuWay.split(",")[2])[4]) 
    									>= Integer.parseInt(Vways.get(Vways.size()-1)[4])
    										+ Integer.parseInt(chuDuWay.split(",")[3]))
    											&& !chuDuWay.split(",")[2].equals(destinationId)
    												&& !chuDuWay.split(",")[2].equals(sourceId)
    													&& !V.contains(chuDuWay.split(",")[2]))
    						{
    							String[] way = new String[5];
    							if(V.get(V.size()-1).equals(sourceId))
    							{
    								way[0] = sourceId;
    								way[1] = "";
    								way[2] = chuDuWay.split(",")[2];
    								way[3] = chuDuWay.split(",")[0];
    								way[4] = chuDuWay.split(",")[3];
    							}
    							else
    							{
    								if(Uways.get(chuDuWay.split(",")[2]) != null)
    								{
    									way = Uways.get(chuDuWay.split(",")[2]);
    								}
    								
    								way[0] = sourceId;
    								if((Uways.get(chuDuWay.split(",")[2]) == null)
    										||(!way[1].split("\\|")[way[1].split("\\|").length-1].equals(V.get(V.size()-1))))//新增与添加
    								{
    									if(Vways.get(Vways.size()-1)[1].trim().length() <= 0)
    									{
    										way[1] = V.get(V.size()-1);
    									}
    									else
    									{
    										way[1] = Vways.get(Vways.size()-1)[1]+"|"+V.get(V.size()-1);
    									}
    								}
    								else if(way[1].split("\\|")[way[1].split("\\|").length-1].equals(V.get(V.size()-1)))
    								{
    									way[1] = way[1];
    								}
    								way[2] = chuDuWay.split(",")[2];
    								way[3] = Vways.get(Vways.size()-1)[3]+"|"+chuDuWay.split(",")[0];
    								way[4] = Integer.parseInt(Vways.get(Vways.size()-1)[4])+Integer.parseInt(chuDuWay.split(",")[3])+"";
    							}
    							
    							Uways.put(chuDuWay.split(",")[2],way);
    						}
    					}
    					
    					//转移操作
    					int i = 12001;
    					String[] shortWay = new String[5];
    					String shortestPoint = null ;
    					int includePassPoints = 0; //包含的必经点
    					int notIncludePassPoints = 0; //包含的非必经点
    					
    					for(String key : Uways.keySet())
    					{
    						if(i > Integer.parseInt(Uways.get(key)[4]) && !passIds.contains(key))
    						{
    							i = Integer.parseInt(Uways.get(key)[4]);
    							shortWay[0] = Uways.get(key)[0];
    							shortWay[1] = Uways.get(key)[1];
    							shortWay[2] = Uways.get(key)[2];
    							shortWay[3] = Uways.get(key)[3];
    							shortWay[4] = Uways.get(key)[4];
    							shortestPoint = key;
    						}
    						
    						for(String passId : passIds)
    						{
    							if( key.equals(passId))
    							{
    								includePassPoints = includePassPoints + 1;
    							}
    						}
    					}
    					
    					notIncludePassPoints = Uways.keySet().size() - includePassPoints;
    					if(notIncludePassPoints == 0)
    					{
    						List<String> keys = new ArrayList<String>();
    						for(String key :  Uways.keySet())
    						{
    							keys.add(key);
    						}
    						for(int iii = 0 ; iii <keys.size();iii++)
    						{
    							String passId = keys.get(iii);
    							String[] shortWayInFor = new String[5]; 
    							
    							shortWayInFor[0] = Uways.get(passId)[0];
    							shortWayInFor[1] = Uways.get(passId)[1];
    							shortWayInFor[2] = Uways.get(passId)[2];
    							shortWayInFor[3] = Uways.get(passId)[3];
    							shortWayInFor[4] = Uways.get(passId)[4];
    							shortestPoint = passId;
    							
    							V.add(shortestPoint);
    							Vways.add(shortWayInFor);
    							Uways.remove(shortestPoint);
    							
    							passMiddleNecessaryPoints.add(shortWayInFor[2]);
    						}
    						
    						if(passMiddleNecessaryPoints.size() > 0)
    						{
    							sourceFlag = "pass";
    						}
    						else
    						{
    							sourceFlag = "stop";
    						}
    						break;
    					}
    					if(i < 12001)
    					{
    						V.add(shortestPoint);
    						Vways.add(shortWay);
    						Uways.remove(shortestPoint);
    					}
    					
    				}// while结束
    			}
    			else
    			{
    				sourceFlag = "stop";
    			}
    			if(sourceFlag.equals("stop"))
    			{
    				return "NA";
    			}
    			if(sourceFlag.equals("pass"))
    			{
    				pointToWay.put(sourceId, Vways);
    				System.out.println("原点的出度到其他点的最短路径(不含终点)");
    				for(String[] ok : Vways)
    				{
    					System.out.println(ok[0]+","+ok[1]+","+ok[2]+","+ok[3]+","+ok[4]);
    				}
    				System.out.println("原点的最短路径结束");
    				
    				for(String[] way : Vways)
    				{
    					if(passIds.contains(way[2]))
    					{
    						theShortestWays.addAll(find(way, passIds, destinationId, chuDu, passIds, ShortestWays , startcalendar)) ;
    					}
    				}
    				
    				if(flag.equals("pass"))
    				{
    					if(theShortestWays.size() > 0)
    					{
    						System.out.println("原点到达终点的全部最短路径：");
    						
    						for(String[] infos : theShortestWays)
    						{
    							System.out.println(infos[0]+","+infos[1]+","+infos[2]+","+infos[3]+","+infos[4]);
    						}
    						
    						System.out.println("路径演示结束");
    						
    						//找最短路径
    						int cost = 12001;
    						for(String[] checkWay : theShortestWays)
    						{
    							if(cost > Integer.parseInt(checkWay[4]))
    							{
    								cost = Integer.parseInt(checkWay[4]);
    								theShortestWay[0] = checkWay[0];
    								theShortestWay[1] = checkWay[1];
    								theShortestWay[2] = checkWay[2];
    								theShortestWay[3] = checkWay[3];
    								theShortestWay[4] = checkWay[4];
    							}
    						}
    						System.out.println("最短路径为:"+theShortestWay[0]+","+theShortestWay[1]+","+theShortestWay[2]+","+theShortestWay[3]+","+theShortestWay[4]);
    					}
    					else
    					{
    						flag = "stop";
    					}
    				}
    				
    				if(flag.equals("stop"))
    				{
    					return "NA";
    				}
    			}
    		}
    		else
    		{
    			return "NA";
    		}
    	}//最大的 If循环
    	else
    	{
    		return "NA";
    	}
    	if(flag.equals("pass"))
    	{
    		return theShortestWay[3];
    	}
    	else
    	{
    		return "NA";
    	}
    }
    
    /**
     *  通过递归查询路径
     *   toThisPointWay 到本点的最短路径
     *   middlePoints 剩余中间点的集合(包括本点) 以passIds为起始点集
     *   destinationId 目的点 以最终目的点为起始值
     *   shortWayListArray由原点到终点的最短路径集合 
     *   chuDu 每个点的出度集
     */
    private static List<String[]> find(String[] toThisPointWay 
    									, List<String> middlePoints 
    										, String destinationId
    											, Map<String,List<String> > chuDu  
    												, List<String> passIds
    													, List<String[]> shortWayListArray
    														,Long startcalendar)
    {
    	Date dt= new Date();
    	Long endcalendar = dt.getTime();//这就是距离1970年1月1日0点0分0秒的毫秒数
    	
    	String sourceFlag = "pass";
    	String sourceId = toThisPointWay[2];//以本点为起始点
    	
    	List<String> passPoints = new ArrayList<String>(); //排除点(原点，本点，中间经过点，终点)
    	for(String passPoint : toThisPointWay[1].split("\\|"))
    	{
    		passPoints.add(passPoint);
    	}
    	middlePoints.remove(toThisPointWay[2]);
    	passPoints.add(toThisPointWay[0]);//添加路径原点
    	passPoints.add(sourceId);//添加本点
   		List<String> V = new ArrayList<String>();//已确定的店
   		List<String[]> Vways = new ArrayList<String[]>();//已确定的最短路径
   		Map<String,String[]> Uways = new HashMap<String,String[]> ();//中间经过的暂时最短路径
   		
   		//初始路径
   		V.add(sourceId);
   		String[] startPrimaryWay = {toThisPointWay[0],toThisPointWay[1],sourceId,toThisPointWay[3],toThisPointWay[4]};
   		Vways.add(startPrimaryWay);
  		if(middlePoints.size() > 0)
   		{
  			passPoints.add(destinationId);//添加终点
    		while(sourceFlag.equals("pass"))
    		{
    			//U集合中存在的起始最短路径
    			for(String chuDuWay : chuDu.get(V.get(V.size()-1)))
    			{
    				if((Uways.get(chuDuWay.split(",")[2]) == null
    						||Integer.parseInt(Uways.get(chuDuWay.split(",")[2])[4]) 
    							>= Integer.parseInt(Vways.get(Vways.size()-1)[4])
    								+ Integer.parseInt(chuDuWay.split(",")[3]))
    									&& !V.contains(chuDuWay.split(",")[2])
    										&& !passPoints.contains(chuDuWay.split(",")[2]))
    				{
    					String[] way = new String[5];
    					if(V.get(V.size()-1).equals(sourceId))
    					{
    						way[0] = toThisPointWay[0];
    						if(toThisPointWay[1].trim().length() > 0)
    						{
    							way[1] = toThisPointWay[1] + "|"+ chuDuWay.split(",")[1];
    						}
    						else
    						{
    							way[1] = chuDuWay.split(",")[1];
    						}
    						
    						way[2] = chuDuWay.split(",")[2];
    						way[3] = toThisPointWay[3]+"|"+chuDuWay.split(",")[0];
    						way[4] = (Integer.parseInt(chuDuWay.split(",")[3])+Integer.parseInt(toThisPointWay[4]))+"";
    					}
    					else
    					{
    						if(Uways.get(chuDuWay.split(",")[2]) != null)
    						{
    							way = Uways.get(chuDuWay.split(",")[2]);
    						}
    						way[0] = toThisPointWay[0];
    						if((Uways.get(chuDuWay.split(",")[2]) == null)
    								||(!way[1].split("\\|")[way[1].split("\\|").length-1].equals(
    										V.get(V.size()-1))))//新增与添加
    						{
    							way[1] = Vways.get(Vways.size()-1)[1]+"|"+V.get(V.size()-1);
    						}
    						else if(way[1].split("\\|")[way[1].split("\\|").length-1].equals(V.get(V.size()-1)))
    						{
    							way[1] = way[1];
    						}
    						way[2] = chuDuWay.split(",")[2];
    						way[3] = Vways.get(Vways.size()-1)[3]+"|"+chuDuWay.split(",")[0];
    						way[4] = Integer.parseInt(Vways.get(Vways.size()-1)[4])+Integer.parseInt(chuDuWay.split(",")[3])+"";
    					}
    					Uways.put(chuDuWay.split(",")[2],way);
    				}
    			}
    			
    			//转移操作
    			int i = 12001;
    			String[] shortWay = new String[5];
    			String shortestPoint = null ;
    			int includePassPoints = 0; //包含的必经点
    			int notIncludePassPoints = 0; //包含的非必经点
    			
    			for(String key : Uways.keySet())
    			{
    				if(i > Integer.parseInt(Uways.get(key)[4]) && !middlePoints.contains(key))
    				{
    					i = Integer.parseInt(Uways.get(key)[4]);
    					shortWay[0] = Uways.get(key)[0];
    					shortWay[1] = Uways.get(key)[1];
    					shortWay[2] = Uways.get(key)[2];
    					shortWay[3] = Uways.get(key)[3];
    					shortWay[4] = Uways.get(key)[4];
    					shortestPoint = key;
    				}
    				
    				for(String passId : passIds)
    				{
    					if( key.equals(passId))
    					{
    						includePassPoints = includePassPoints + 1;
    					}
    				}
    			}
    			
    			notIncludePassPoints = Uways.keySet().size() - includePassPoints;
    			if(notIncludePassPoints == 0 )
    			{
    				if(Uways.keySet().size() == 0 || (endcalendar - startcalendar) > 18000)
    				{
    					return shortWayListArray;
    				}
    				else
    				{
    					List<String> keys = new ArrayList<String>();
    					for(String passId : Uways.keySet())
    					{
    						keys.add(passId);
    					}
    					for(int ii = 0 ; ii < keys.size(); ii++)
    					{
    						String passId = keys.get(ii);
    						String[] shortWayInFor = new String[5]; 
    						
    						shortWayInFor[0] = Uways.get(passId)[0];
    						shortWayInFor[1] = Uways.get(passId)[1];
    						shortWayInFor[2] = Uways.get(passId)[2];
    						shortWayInFor[3] = Uways.get(passId)[3];
    						shortWayInFor[4] = Uways.get(passId)[4];

    						shortestPoint = passId;
    						V.add(shortestPoint);
    						Vways.add(shortWayInFor);
    						Uways.remove(shortestPoint);
    						
    						List<String> middlePointsCopy = new ArrayList<String>();
    						for(String middlePoint : middlePoints)
    						{
    							middlePointsCopy.add(middlePoint);
    						}
    						
    						shortWayListArray = find(shortWayInFor ,middlePointsCopy ,destinationId, chuDu  , passIds, shortWayListArray , startcalendar);
    					}
    				}
    				break;
    			}
    			if(i < 12001)
				{
					V.add(shortestPoint);
					Vways.add(shortWay);
					Uways.remove(shortestPoint);
				}
    		}
    	}
    	else
    	{
    		while(sourceFlag.equals("pass"))
    		{
    			//U集合中存在的起始最短路径
    			for(String chuDuWay : chuDu.get(V.get(V.size()-1)))
    			{
    				if((Uways.get(chuDuWay.split(",")[2]) == null
    						||Integer.parseInt(Uways.get(chuDuWay.split(",")[2])[4]) 
    							>= Integer.parseInt(Vways.get(Vways.size()-1)[4])
    								+ Integer.parseInt(chuDuWay.split(",")[3]))
    									&& !V.contains(chuDuWay.split(",")[2])
    										&& !passPoints.contains(chuDuWay.split(",")[2]))
    				{
    					String[] way = new String[5];
    					if(V.get(V.size()-1).equals(sourceId))
    					{
    						way[0] = toThisPointWay[0];
    						if(toThisPointWay[1].trim().length() > 0)
    						{
    							way[1] = toThisPointWay[1] + "|"+ chuDuWay.split(",")[1];
    						}
    						else
    						{
    							way[1] = chuDuWay.split(",")[1];
    						}
    						
    						way[2] = chuDuWay.split(",")[2];
    						way[3] = toThisPointWay[3]+"|"+chuDuWay.split(",")[0];
    						way[4] = (Integer.parseInt(chuDuWay.split(",")[3])+Integer.parseInt(toThisPointWay[4]))+"";
    					}
    					else
    					{
    						if(Uways.get(chuDuWay.split(",")[2]) != null)
    						{
    							way = Uways.get(chuDuWay.split(",")[2]);
    						}
    						way[0] = toThisPointWay[0];
    						if((Uways.get(chuDuWay.split(",")[2]) == null)
    								||(!way[1].split("\\|")[way[1].split("\\|").length-1].equals(
    										V.get(V.size()-1))))//新增与添加
    						{
    							way[1] = Vways.get(Vways.size()-1)[1]+"|"+V.get(V.size()-1);
    						}
    						else if(way[1].split("\\|")[way[1].split("\\|").length-1].equals(V.get(V.size()-1)))
    						{
    							way[1] = way[1];
    						}
    						way[2] = chuDuWay.split(",")[2];
    						way[3] = Vways.get(Vways.size()-1)[3]+"|"+chuDuWay.split(",")[0];
    						way[4] = Integer.parseInt(Vways.get(Vways.size()-1)[4])+Integer.parseInt(chuDuWay.split(",")[3])+"";
    					}
    					Uways.put(chuDuWay.split(",")[2],way);
    				}
    			}
    			
    			//转移操作
    			int i = 12001;
    			String[] shortWay = new String[5];
    			String shortestPoint = null ;
    			
    			for(String key : Uways.keySet())
    			{
    				if(i > Integer.parseInt(Uways.get(key)[4]) && !destinationId.equals(key))
    				{
    					i = Integer.parseInt(Uways.get(key)[4]);
    					shortWay[0] = Uways.get(key)[0];
    					shortWay[1] = Uways.get(key)[1];
    					shortWay[2] = Uways.get(key)[2];
    					shortWay[3] = Uways.get(key)[3];
    					shortWay[4] = Uways.get(key)[4];
    					shortestPoint = key;
    				}
    			}
    			
    			if((Uways.keySet().size() == 1)&&(Uways.containsKey(destinationId)))
    			{
					shortWay[0] = Uways.get(destinationId)[0];
					shortWay[1] = Uways.get(destinationId)[1];
					shortWay[2] = Uways.get(destinationId)[2];
					shortWay[3] = Uways.get(destinationId)[3];
					shortWay[4] = Uways.get(destinationId)[4];
					shortestPoint = destinationId;
					V.add(destinationId);
					Vways.add(shortWay);
					Uways.remove(shortestPoint);
					if(!shortWayListArray.contains(shortWay))
					{
						shortWayListArray.add(shortWay);
					}
					
					return shortWayListArray;
    			}
    			
    			if(Uways.keySet().size() == 0 || (endcalendar - startcalendar) > 18000)
    			{
    				return shortWayListArray;
    			}
    			
    			if(i < 12001)
				{
					V.add(shortestPoint);
					Vways.add(shortWay);
					Uways.remove(shortestPoint);
				}
    		}
    	}
    	return shortWayListArray;
    }
    
    /**
     * 减除边缘点
     */
    private static Map<String,List<String>> delete(List<String> excludePoints , Map<String,List<String>> chuDu , Map<String,List<String>> ruDu)
	{
    	List<String> chukeys = new ArrayList<String>(); //起点
    	List<String> rukeys = new ArrayList<String>(); //终点
    	List<String> removeWays = new ArrayList<String>();//删除的路径
    	for(String key : chuDu.keySet())
    	{
    		chukeys.add(key);
    	}
    	
    	for(String key : ruDu.keySet())
    	{
    		rukeys.add(key);
    	}
    	
    	for(int i = 0 ; i < rukeys.size() ; i++)
    	{
    		String key = rukeys.get(i);
    		
    		if((!chukeys.contains(key))&&(!excludePoints.contains(key)))
    		{
    			for(String removeWay : ruDu.get(key))
    			{
    				removeWays.add(removeWay);
    			}
    			ruDu.remove(key);
    		}
    	}
    	if( removeWays.size() > 0)
    	{
    		for(String removeWay : removeWays)
    		{
    			String removeWayStartPoint = removeWay.split(",")[1];
    			for(int i = 0 ; i < chukeys.size() ; i++)
    			{
    				if(chukeys.get(i).equals(removeWayStartPoint))
    				{
    					List<String> removeWayStartPointWays = chuDu.get(chukeys.get(i));
    					removeWayStartPointWays.remove(removeWay);
    					chuDu.put(chukeys.get(i) , removeWayStartPointWays);
    					if(removeWayStartPointWays.size() == 0)
    					{
    						chuDu.remove(chukeys.get(i));
    					}
    				}
    			}
    		}
    		chuDu = delete(excludePoints, chuDu , ruDu);
    	}
    	return chuDu;
	}
    
    
    
}