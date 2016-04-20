package com.routesearch.test;

/************************************************************************************
 * @Title        : Route.java
 * @Todo         : 路径
 * @Author       : CaoPeng
 * @DateTime     : 2016年3月17日 下午9:06:02
 * @Copyright    : 2016 LLC All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public final class Route
{
    /*功能的入口
     * 功能要求：1.有向边的编号连续或不连续
     *		  2.路径经过所有子集V'
     *		  3.路径不过重复点
     */
    public static String searchRoute(String graphContent, String condition)
    {
    	/*1.解析图数据；填充邻接矩阵；
    	 * 注意：
    	 * 	边的编号不连续；
    	 * 	sourceId与destinationId相同者只取权值小的；
    	 * 	
    	 */
    	
    	/*2.用dijstra查找最小路径，若存在最小路径，找出最小路径*/
    	
    	
    	/*3.解析必须经过的子集，检验是否包含子集*/
    	
    	
    	
    	
    	
    	/*检验是否包含子集*/
    	
    	
    	/*4.检验路径中是否存在环路*/
    	
    	
    	/*5.解析要求条件*/
    	
    	
        return "result";
    }
}