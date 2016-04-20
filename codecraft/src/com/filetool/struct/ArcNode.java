package com.filetool.struct;

public class ArcNode {
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
