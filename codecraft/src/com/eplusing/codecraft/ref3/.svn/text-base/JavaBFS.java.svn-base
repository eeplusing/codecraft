package com.eplusing.codecraft.ref3;

public class JavaBFS {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// �趨��ʼֵ����ֵ�����ڽӾ����ʾ��
		int[][] graph = new int[7][7];
		// ��ֵ
		// graph[0][0]=0;
		for (int k = 0; k < graph.length; k++) {
			graph[k][k] = 0;
		}
		graph[0][1] = 1;
		graph[0][2] = 0;
		graph[0][3] = 0;
		graph[0][4] = 0;
		graph[0][5] = 1;
		graph[0][6] = 0;

		graph[1][0] = 1;
		graph[1][2] = 0;
		graph[1][3] = 0;
		graph[1][4] = 0;
		graph[1][5] = 1;
		graph[1][6] = 0;

		graph[2][0] = 0;
		graph[2][1] = 0;
		graph[2][3] = 1;
		graph[2][4] = 0;
		graph[2][5] = 0;
		graph[2][6] = 0;

		graph[3][0] = 0;
		graph[3][1] = 0;
		graph[3][2] = 1;
		graph[3][4] = 0;
		graph[3][5] = 1;
		graph[3][6] = 0;

		graph[4][0] = 0;
		graph[4][1] = 0;
		graph[4][2] = 0;
		graph[4][3] = 0;
		graph[4][5] = 1;
		graph[4][6] = 0;

		graph[5][0] = 1;
		graph[5][1] = 1;
		graph[5][2] = 0;
		graph[5][3] = 1;
		graph[5][4] = 1;
		graph[5][6] = 0;

		graph[6][0] = 0;
		graph[6][1] = 0;
		graph[6][2] = 0;
		graph[6][3] = 0;
		graph[6][4] = 0;
		graph[6][5] = 0;

		// ���ñ�־����
		int[] mark = new int[graph.length];
		for (int k = 0; k < mark.length; k++) {
			mark[k] = 0;
		}
		// ���ö���
		JavaQueue queue = new JavaQueue();
		// ��ʼ����ͼ�Ľ�㣬��0��ʼ
		for (int m = 0; m < graph.length; m++) {
			// ��Ԫ������ѹ�����,Ȼ��Ѱ�����ӵĵ㣬�ж��Ƿ��Ѿ������
			if (mark[m] == 0)// ���Ϊ��0��ʾ��δ�����,���μ�����ӵ�
			{
				mark[m] = 1;
				queue.push(m);
				while (!queue.isEmpty()) {
					// ȡ������ֵ�����
					int l = (Integer) queue.get();
					System.out.println(l);
					for (int n = 0; n < graph.length; n++) {
						// ���Ϊ1������ʾ�����ӣ��һ�δ����У�ѹ����У����õ�ǰֵΪ-1����ʾ�Ѿ�����
						if (graph[l][n] == 1) {
							if (mark[n] != 1) {// ���n��ռ��δ��ջ������ջ�����򲻴���
								queue.push(n);
								mark[n] = 1;// �����Ѿ�Ϊ��ջ״̬
								graph[l][n] = -1;// �����ô������Ѿ����ʹ�
								graph[n][l] = -1;
							}
						}
					}
				}
			}
			// System.out.println(queue.get());
		}
	}
}
