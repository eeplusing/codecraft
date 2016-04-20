package com.eplusing.codecraft.ref1;

import com.eplusing.codecraft.ref.Edge;

/**
 * ���ȶ���
 * @author lmning
 *Feb 15, 2009 8:43:12 AM
 */
public class PriorityQ {
	private final int SIZE = 20;
	private Edge[] queArray;
	private int size;
	
	//constructor;
	public PriorityQ(){
		queArray = new Edge[SIZE];
		size = 0;
	}
	//����ߣ�����Ȩ���ش�С����
	public void insert(Edge item){
		int j ;
		for(j =0;j<size;j++){
			if(item.distance>queArray[j].distance)break;
		}
		for(int k=size-1;k>=j;k--){
			queArray[k+1]=queArray[k];
		}
		queArray[j]=item;
		size++;
	}
	//�����ȶ�����ɾ����С�ı�
	public Edge removeMin(){
		return queArray[--size];
	}
	//remove item at n
	public void removeN(int n){
		for(int i=n;i<size-1;i++){
			queArray[i]=queArray[i+1];
		}
		size--;
	}
	//peek min item
	public Edge peekMin(){
		return queArray[size-1];
	}
	//peek item at n
	public Edge peekN(int n){
		return queArray[n];
	}
	//return size of queArray
	public int size(){
		return size;
	}
	//true if queue is empty
	public boolean isEmpty(){
		return size==0;
	}
	//find item with specified end vertex;
	public int find(int v){
		for(int i =0;i<size;i++){
			if(queArray[i].endVert==v){
				return i;
			}
		}
		return -1;
	}
	
}
