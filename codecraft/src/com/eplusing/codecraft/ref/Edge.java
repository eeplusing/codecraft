package com.eplusing.codecraft.ref;

public class Edge {
	public int srcVert;
	public int endVert;
	public int distance;
	
	public Edge(int srcVert, int endVert, int distance) {
		this.srcVert = srcVert;
		this.endVert = endVert;
		this.distance = distance;
	}
}
