package com.eplusing.codecraft.ref4;

public class GraphNode {
	
	private int S;//������
	private int D;//�ߵĸ���	
	private int r; //���
	private int t; //�յ�
	
	
	public int getS() {
		return S;
	}
	
	public void setS(int s) {
		if (s >= 1&&s < 10) {
			S = s;
			//System.out.println("--�ڵ���Ŀ���óɹ���     ");
		}else {
			//System.out.println("--�ڵ����������1<=S<10��");
		}

	}
	public int getD() {
		return D;
	}
	public void setD(int d) {
		if (d >=1 && d <= S*(S-1)/2) {
			D = d;
			//System.out.println("�������óɹ�����");
		}else {
			//System.out.println("�߸���Ӧ����������");
		}
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	public int getT() {
		return t;
	}
	public void setT(int t) {
		this.t = t;
	}
}
