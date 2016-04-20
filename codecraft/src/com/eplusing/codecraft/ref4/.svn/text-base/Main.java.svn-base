package com.eplusing.codecraft.ref4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		GraphNode graphnode = new GraphNode();
		System.out.println("---Please input the graph informatiion!!!---");
		String s = bufferedReader.readLine();
        String [] ss1 = s.split(" ");
        
        int [] num = new int [3];
        
        for (int i = 0; i < ss1.length; i++) {
			num[i] = Integer.parseInt(ss1[i]);
		}
        //graph���ý����ͱ���
        graphnode.setS(num[0]);
        graphnode.setD(num[1]);
        
        int [][] M = new int [graphnode.getS()][graphnode.getS()];
        for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M.length; j++) {
				M[i][j] = -1;
			}
		}
        for (int i = 0; i < M.length; i++) {
				M[i][i]=0;		
		}//�Խ�������Ϊ0;
             
        //print(M);
        int counter = 0;
        while(counter < graphnode.getD()){
        	s=bufferedReader.readLine();
        	String [] ss2=s.split(" ");
            for (int i = 0; i < ss2.length; i++) {
    			num[i] = Integer.parseInt(ss2[i]);
    		}
            M[num[0]-1][num[1]-1] = num[2];
            M[num[1]-1][num[0]-1] = num[2];//����ͼ����Ҫ��������Ȩֵ��
            counter++;
            //System.out.println(counter);
        }       
        //print(M);
        
        s=bufferedReader.readLine();
        String [] ss3=s.split(" ");
        for (int i = 0; i < ss3.length; i++) {
			num[i] = Integer.parseInt(ss3[i]);
		}
        
        graphnode.setR(num[0]-1);
        graphnode.setT(num[1]-1);
        
        //��ȡ�����ϣ�����ȴ���㡣��ֱ�������
        DijkstraForth.dijkstra(M,graphnode.getR(),graphnode.getT());

	}
	public static void print(int [][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
	}
}
