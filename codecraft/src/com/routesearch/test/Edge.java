package com.routesearch.test;

/************************************************************************************
 * @Title        : Edge.java
 * @Todo         : 图中的边
 * @Author       : CaoPeng
 * @DateTime     : 2016年3月16日 下午9:10:00
 * @Copyright    : 2016 LLC All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class Edge 
{
	private int linkID; 
	private int sourceID;
	private int destinationID;
	private int cost;
	
	public Edge(int linkID, int sourceID, int destinationID, int cost) 
	{
		super();
		this.linkID = linkID;
		this.sourceID = sourceID;
		this.destinationID = destinationID;
		this.cost = cost;
	}
	
	public int getLinkID() 
	{
		return linkID;
	}
	public void setLinkID(int linkID) 
	{
		this.linkID = linkID;
	}
	public int getSourceID() 
	{
		return sourceID;
	}
	public void setSourceID(int sourceID) 
	{
		this.sourceID = sourceID;
	}
	public int getDestinationID() 
	{
		return destinationID;
	}
	public void setDestinationID(int destinationID) 
	{
		this.destinationID = destinationID;
	}
	public int getCost() 
	{
		return cost;
	}
	public void setCost(int cost) 
	{
		this.cost = cost;
	}
}
