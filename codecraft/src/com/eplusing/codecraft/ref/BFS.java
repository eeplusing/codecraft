package com.eplusing.codecraft.ref;

import java.util.Arrays;
import java.util.Scanner;

/************************************************************************************
 * @Title        : BFS.java
 * @Todo         :<----功能描述---->
 * @Author       : CaoPeng
 * @DateTime     : 2016年3月17日 下午9:32:23
 * @Copyright    : 2016 LLC All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class BFS
{
	 
    final static int MAXN = 100;
    static Scanner scan = new Scanner(System.in);
    public static class Queue    
    {
        public int Depth;
        public int Dot;
        Queue()    
        {
            Depth = -1;
            Dot = -1;
        }
        public void enterQueue(int dot,int dep)    
        {
            Dot = dot;
            Depth = dep;
            System.out.println(Dot+" "+"The Depth is:"+ Depth);
        }
        public int depDate()    
        {
            return Depth;
        }
        public int dotdate()    
        {
            return Dot;
        }
    };
    
    public static void main(String[] args) 
    {
        // TODO Auto-generated method stub
        int[][] Graph = new int[MAXN][MAXN];
        boolean[] vis = new boolean[MAXN];
        for(int i=0;i<MAXN;i++)    
        {
            Arrays.fill(Graph[i], 0);
        }
        Arrays.fill(vis, false);
        int base = 0;int top = 0;
        Queue[] queue = new Queue[MAXN];
        int n,m;//n为点数，m为边数
        n = scan.nextInt();
        m = scan.nextInt();
        for(int i=0;i<2*n;i++)    
        {
            queue[i]=new Queue();
        }
        for(int i=0;i<m;i++)    
        {
            int a,b;
            a = scan.nextInt();
            b = scan.nextInt();
            Graph[a][b] = Graph[b][a] = 1;
        }
        for(int i=0;i<n;i++)    
        {
            if(vis[i] == false)    
            {
                int dot = 0;int dep=0;
                queue[top].enterQueue(i, 1);
                top++;
                vis[i] = true;
                while(top != base)    
                {
                    dep=queue[base].depDate()+1;
                    dot=queue[base].dotdate();
                    for(int j=0;j<n;j++)    {
                        if(Graph[dot][j] == 1 && vis[j] == false)    
                        {
                            queue[top].enterQueue(j, dep);
                            top++;
                            vis[j] = true;
                        }
                    }
                    base++;
                }
            }
        }
    }
}
