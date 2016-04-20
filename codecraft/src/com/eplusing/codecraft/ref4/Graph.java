package com.eplusing.codecraft.ref4;

import java.util.ArrayList;

public class Graph {
    //�����Ȩͼ��-1��ʾ��·��ͨ���Լ����Լ�Ҳ��-1�������ʾȨֵ��
    public static int[][] graph = new int [DijkstraForth.lengthd][DijkstraForth.lengthd];
    public static boolean[] hasFlag=new boolean[graph.length];
    //true-��ʾ�ý���ѷ��ʹ�false-��ʾ��û�з��ʹ�
    
    public static ArrayList<String> res=new ArrayList<String>();
    //�������е�·���Ľ��ÿһ��·���ĸ�ʽ���磺0->2->1->3:7
    
    //����ͼgraph��Դ��s��Ŀ���d֮�����еļ�·���������·���ĺ͡�    
    public static void getPaths(int s,int d,String path)
    {
        hasFlag[s]=true;//Դ���ѷ��ʹ�. 
     for(int i=0;i<graph.length;i++)
     {
        if (graph[s][i]==-1 || hasFlag[i])
        {
        	continue;
        }
        //����·��ͨ���ѷ��ʹ�������һ����㡣

        if(i==d)//�����ҵ�һ��·��
        { 
            res.add(path+"->"+d);//������
            continue;
        }
        getPaths(i, d, path+"->"+i);//������
        hasFlag[i]=false;        
     }
    }
}