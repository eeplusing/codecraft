package com.eplusing.codecraft.ref4;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;


public class DijkstraForth {  
	//ʣ�๤��Ϊ��Ϊ����Ŀ�����Ӧ�����������������Ҫ�󽫽�������
	    public static int lengthd;
	    public static void dijkstra(int[][] A, int start, int end) {  
	    	
	    	lengthd = A[0].length;
	        boolean[] isLabel = new boolean[A[0].length];// �Ƿ���  
	        int[] indexs = new int[A[0].length];// ���б�ŵĵ���±꼯�ϣ��Ա�ŵ��Ⱥ�˳����д洢��ʵ������һ���������ʾ��ջ  
	        int i_count = -1;//ջ�Ķ���  
	        int[] distance = A[start].clone();// v0���������̾���ĳ�ʼֵ  
	        int index = start;// �ӳ�ʼ�㿪ʼ  
	        int presentShortest = 0;//��ǰ��ʱ��̾���  
	        indexs[++i_count] = index;// ���Ѿ���ŵ��±�����±꼯�� ,��ʼ������
	        isLabel[index] = true;  //�Ƿ��Ѿ������˱�ţ���ʼ��������
	        
	        
	        //���һ������ά���ľ���ר��ά��·����
	        final int colume = A[0].length;
	        int rownum = colume;

	        
	        int p [][] = new int[colume][rownum];//����������P����
            
	        int q [][] = new int[colume][rownum];
	        int q1counter = 0;
	        int q2counter = 0;
	        
	        //���õ�ǰp��d�ĳ�ʼֵ������ʼ��·����¼���С�
	        for (int i = 0; i < p.length; i++) {
				for (int j = 0; j < p.length; j++) {
					p[i][j] = -1;
					q[i][j] = -1;
				}
			}	        //�����·����Ƕ��г�ʼ��Ϊ-1 

            //print(p);
	        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<ArrayList<Integer>>();
	        while (i_count < A[0].length) {  
	        	
	            // ��һ�������v0,��Ϊw[0][0]�ҵ�����v0���ĵ�  
	 
	            int min = Integer.MAX_VALUE;
	            
	            for (int i = 0; i < distance.length; i++) {  
	                if (!isLabel[i] && distance[i] != -1 && i != index) {  
	                    // ���������б�,����û�б���� ����������̵ġ�
	                    if (distance[i] < min) {  
	                        min = distance[i];
	                        //D[dcounter++] = min; 
	                        index = i;// �ѹ����±��Ϊ��ǰ��Ҫ��������±�  
	                    }  
	                }  
	            }  //index �ǵ�ǰ�����ĵ㡣
	            //�˴�����һ�����ơ�
	            if (index == end) {//�Ѿ��ҵ���ǰ���ˣ��ͽ������  
	                break;  
	                //��end���뵽��ǰ��¼���е����
	            }
	            
                
	            isLabel[index] = true;    //�Ե���б��  �����뼯�ϣ���ʾ�Ѿ����롣
	            indexs[++i_count] = index;// ���Ѿ���ŵ��±�����±꼯��  ,���Ѿ����뼯�ϵ��±�
	            

	            if (A[indexs[i_count - 1]][index] == -1 
	                    || presentShortest + A[indexs[i_count - 1]][index] > distance[index]) {  
	                // ���������û��ֱ������������������֮���·���������·��  ,���µ�index���·����Ϣ��
	                presentShortest = distance[index];
	            } else {  
	                presentShortest += A[indexs[i_count - 1]][index];  
	                //��֮��ѡ��ֱ�ӵ����·��Ϊ���·��
	            }  
	 
	            // �ڶ�������distance�еľ������vi  
	            for (int i = 0; i < distance.length; i++) {  
	                // ���vi���Ǹ����бߣ���v0�������ľ����  
	                if (distance[i] == -1 && A[index][i] != -1) {// �����ǰ���ɴ�����ڿɴ���  
	                    distance[i] = presentShortest + A[index][i];  
	                } else if (A[index][i] != -1 
	                        && presentShortest + A[index][i] < distance[i]) {  
	                    // �����ǰ�ɴ�����ڵ�·������ǰ��̣����ɸ�̵�·��  
	                    distance[i] = presentShortest + A[index][i];
	                }  
	 
	            }  
	        }  
	        //���ȫ���㶼�����꣬��distance�д洢���ǿ�ʼ�㵽����������·�����ȡ�
/*	        for (int i = 0; i < colume; i++) {
				for (int j = 0; j < colume; j++) {
                      System.out.print(distance[j]);
				}
				System.out.println();}*/
	        for (int i = 0; i < distance.length; i++) {
				for (int j = 0; j < distance.length; j++) {
					if (A[i][j] > 0 && distance[i] != -1) {
						p[i][j] = A[i][j]+distance[i];
					}else {
						p[i][j] = A[i][j];
					}
				}
			}
	        
	        for (int i = 0; i < distance.length; i++) {
				arrayList.add(success(distance, p, i));
			}
	        
	        for (Iterator<ArrayList<Integer>> i = arrayList.iterator();i.hasNext();q1counter++) {
	        	
				ArrayList<Integer> v = i.next();
				q2counter=0;
				for (Iterator<Integer> j = v.iterator(); j.hasNext();) {
					int num = j.next();
						q[q1counter][q2counter] = num;
						q2counter++;
						//System.out.print(num+" ");								
				}
				//System.out.println();
			}
	        for (int i = 0; i < p.length; i++) {
				for (int j = 0; j < p.length; j++) {
					p[i][j] = -1;
				}
			}	        //�����·����Ƕ��г�ʼ��Ϊ-1 
        
	        Changeq2p(q, p);
	        Graph.graph=p;
	        //System.out.println();
	        Graph.getPaths(start, end, ""+start);	        
	        //����д���������  
	    	System.out.println("---This is the output!!!---");
	        System.out.println(Graph.res.size()+" "+(distance[end] - distance[start]));
            //�˴����ý���?����б�׼���������
	        ///System.out.println();
	        FormatOutput(Graph.res);	        
	    }  
	    /*public static void main(String[] args) {          
	    	// ����һ��Ȩֵ����         
	    	int[][] W1 = { //�������1                  
	    			{ 0, 1, 4, -1, -1, -1 },                 
	    			{ 1, 0, 2, 7, 5, -1 },                  
	    			{ 4, 2, 0, -1, 1, -1 },                  
	    			{ -1, 7, -1, 0, 3, 2 },                  
	    			{ -1, 5, 1, 3, 0, 6 },                  
	    			{ -1, -1, -1, 2, 6, 0 } };         
	    	int[][] W = { //�������2                  
	    			{ 0, 1, 3, 4 },                  
	    			{ 1, 0, 2, -1 },                  
	    			{ 3, 2, 0, 5 },                  
	    			{ 4, -1, 5, 0 } }; 
	    	int[][] M = {
	    			{0,2,1,-1,-1},
	    			{2,0,1,1,2},
	    			{1,1,0,2,-1},
	    			{-1,1,2,0,1},
	    			{-1,2,-1,1,0},
	    	};

	    	dijkstra(M,0,4);
	    	}
*/
	    public static void print(int [][] a) {
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a.length; j++) {
					System.out.print(a[i][j]+" ");
				}
				System.out.println();
			}
		}
	    public static ArrayList<Integer> success(int D[],int P[][],int u) {
	    	ArrayList<Integer>a = new ArrayList<Integer>();
	    	for (int w = 0; w < P.length; w++) {
	    		if (D[w] == P[u][w] && w != u) {
					a.add(w);
				}
			}
	    	return a;
		}   
	    public static void Changeq2p(int [][]q,int [][]p){
	    	for (int i = 0; i < q.length; i++) {
				for (int j = 0; j < q.length; j++) {
					if (q[i][j]!=-1) {
						p[i][q[i][j]]=1;
					}  
				}
			}
	    }
	    private static void FormatOutput(ArrayList<String>ResultList){
	    	//�������������ǣ������е�������ȡ��+1
	    	Iterator<String> strs = ResultList.iterator();
	    	int counter = 0;
	    	int [][] numbers = new int [ResultList.size()][DijkstraForth.lengthd];//ע������ĳ�ʼ����
			int [][] tempNumbers = new int [ResultList.size()][DijkstraForth.lengthd];// ��������֣��Ѿ�����,��+1
			String flagString[]= new String [ResultList.size()];
			Stack<Integer>stack =new Stack<Integer>();
	    	while (strs.hasNext()) {
				String string = strs.next();
				String [] stringnumbers = string.split("->");
				
				//���е��˴���

				for (int i = 0; i < stringnumbers.length; i++) {
					numbers[counter][i] = Integer.parseInt(stringnumbers[i])+1;
					stack.push(numbers[counter][i]);
					//innerString += (numbers[counter][i]+" ");
					//System.out.print(numbers[counter][i]+" ");
				}
				String s =new String();
				for (int i = 0; i < stringnumbers.length; i++) {
					tempNumbers[counter][i]=stack.pop();
					s += tempNumbers[counter][i]+" ";
				}
				flagString[counter] = s;
				counter++;	
				//System.out.println();
			}
	    	//print(numbers);
	    	//System.out.println();
	    	//print(tempNumbers);
	    	//PrintStringArray(flagString);
	    	//��ÿһ��ͨ��ջ�����Ѿ�����������ʱ���������ΪtemNumbers[][]��
	    	//˳��Ҫ����T_i�ķ������е��ֵ���С��T_i+1�ķ������е��ֵ���
	    	//flagstring[]�д���Ǹ�����¼���ַ���
	    	
	    	//����������е�Ԫ�ؽ�������
	    	BubbleSort(flagString);
	    	//System.out.println("----");
	    	//PrintStringArray(flagString);   	
	    	//falgString�д�����Ѿ��ź�������顣��Ҫ��ÿһ������.
	    	upsidedownOutPut(flagString);
	    	PrintStringArray(flagString);
	    }
		private static void upsidedownOutPut(String[] flagString) {
			// TODO Auto-generated method stub
			//�˺���������ǽ�String�е�ÿһ��������
			Stack<String> s = new Stack<String>();
			
			for (int i = 0; i < flagString.length; i++) {
				String str = flagString[i];
				String[] numinner= str.split(" ");
				String strone =new String();
				for (int j = 0; j < numinner.length; j++) {
					s.push(numinner[j]);
				}
				for (int j = 0; j < numinner.length; j++) {
					strone += s.pop()+" ";
				}
				flagString[i]=strone;
			}
		}
		private static void PrintStringArray(String[] flagString) {
			for (int i = 0; i < flagString.length; i++) {
	    		System.out.println(flagString[i]);
			}
		}
	    
	    private static void BubbleSort(String [] x) {   
	    	for (int i = 0; i < x.length; i++) {   
	    		for (int j = i + 1; j < x.length; j++) {   
	    			if (x[i].compareTo(x[j])>0) {   
	    				String temp = x[i];   
	    				x[i] = x[j];   
	    				x[j] = temp;   }   
	    			}   
	    		}     
	    	}   
}
