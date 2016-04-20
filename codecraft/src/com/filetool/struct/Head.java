package com.filetool.struct;

public class Head {
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
