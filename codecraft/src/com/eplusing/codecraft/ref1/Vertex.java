package com.eplusing.codecraft.ref1;

public class Vertex {
	public char        label;        // label (e.g. 'A')
    public boolean    wasVisited;
    public boolean    isInTree;
    public Vertex( char lab ) // constructor
    {
        this.label = lab;
        this.wasVisited = false;
        this.isInTree = false;
    }
}
