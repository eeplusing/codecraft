package com.eplusing.codecraft.ref3;

public class JavaDFS {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] graph = new int[7][7];
		// ��ֵ
		// graph[0][0]=0;
		graph = init(graph);
		// ���ñ�־����
		int[] mark = new int[graph.length];
		for (int k = 0; k < mark.length; k++) {
			mark[k] = 0;
		}
		// ���ö�ջ
		JavaStack stack = new JavaStack();
		// ��ʼ����ͼ�Ľ�㣬��0��ʼ�����ݹ�ʵ��
		for (int m = 0; m < graph.length; m++) {
			stackloop(m, graph, mark, stack);
		}
	}

	// �ݹ鴦��
	public static void stackloop(int m, int[][] graph, int[] mark,
			JavaStack stack) {
		// ����ÿһ�����Ԫ�أ��ж��Ƿ��Ѿ���ջ��û������ջ
		// ��ջ�������ѷ��ʹ�
		if (mark[m] == -1) {
		} else {
			if (mark[m] == 0) {
				mark[m] = 1;
				stack.push(m);
				System.out.println(stack.top());
			}
			int n = 0;
			// ѭ��Ѱ���ǲ��Ǵ������ӵ�,�����ж�������״̬����������������ջ�������ջ
			for (n = 0; n < graph.length; n++) {
				// ��ʱ˵��n��δ��ջ,��nѹ���ջ���������������Ѿ����ʹ�
				if (graph[m][n] == 1) {
					graph[m][n] = 0;
					graph[n][m] = 0;
					stackloop(n, graph, mark, stack);
				}
			}
			if (stack.size() != 0) {
				int j = (Integer) stack.top();
				mark[j] = -1;
				// ����������Ϊ-1�����Ѿ����ʹ��Ҳ��ڶ�ջ�У������ٷ���
				stack.pop();
				// �����׳���һ����ջֵ
			}
		}
	}

	// �ڽӾ��󸳳�ֵ
	public static int[][] init(int[][] graph) {
		for (int k = 0; k < graph.length; k++) {
			graph[k][k] = 0;
		}
		graph[0][1] = 0;
		graph[0][2] = 0;
		graph[0][3] = 1;
		graph[0][4] = 0;
		graph[0][5] = 1;
		graph[0][6] = 0;

		graph[1][0] = 0;
		graph[1][2] = 1;
		graph[1][3] = 0;
		graph[1][4] = 0;
		graph[1][5] = 0;
		graph[1][6] = 0;

		graph[2][0] = 0;
		graph[2][1] = 1;
		graph[2][3] = 1;
		graph[2][4] = 0;
		graph[2][5] = 0;
		graph[2][6] = 0;

		graph[3][0] = 1;
		graph[3][1] = 0;
		graph[3][2] = 1;
		graph[3][4] = 1;
		graph[3][5] = 0;
		graph[3][6] = 0;

		graph[4][0] = 0;
		graph[4][1] = 0;
		graph[4][2] = 0;
		graph[4][3] = 1;
		graph[4][5] = 0;
		graph[4][6] = 0;

		graph[5][0] = 1;
		graph[5][1] = 0;
		graph[5][2] = 0;
		graph[5][3] = 0;
		graph[5][4] = 0;
		graph[5][6] = 1;

		graph[6][0] = 0;
		graph[6][1] = 0;
		graph[6][2] = 0;
		graph[6][3] = 0;
		graph[6][4] = 0;
		graph[6][5] = 1;
		return graph;
	}
}
