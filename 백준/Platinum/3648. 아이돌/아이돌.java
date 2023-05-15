import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	private static int n, m, sccCnt, vertexCnt;
    private static int[] sccNum, order;
    private static boolean[] finished;
    private static Stack<Integer> stack;
    private static ArrayList<ArrayList<Integer>> adj;
    
    // 찬반 전환
    private static int opp(int num) {
        return num > n ? num - n : num + n;
    }
    
    // DFS
    private static int dfs(int now) {
    	int minOrder, t;
        order[now] = ++vertexCnt;
        stack.push(now);
        minOrder = order[now];
        for (int next : adj.get(now)) {
            if (order[next] == 0) {
            	minOrder = Math.min(minOrder, dfs(next));
            }
            else if (!finished[next]) {
            	minOrder = Math.min(minOrder, order[next]);
            }
        }
        if (minOrder == order[now]) {
            while (true) {
                t = stack.pop();
                finished[t] = true;
                sccNum[t] = sccCnt;
                if (t == now) {
                	break;
                }
            }
            sccCnt++;
        }
        return minOrder;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String str;

        int i;
        
        // 테스트 케이스
        while (true) {
            str = br.readLine();
            if (str == null) {
            	break;
            }
            StringTokenizer st = new StringTokenizer(str);
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            // 초기화
            sccCnt = 0;
            vertexCnt = 0;
            sccNum = new int[2 * n + 1];
            order = new int[2 * n + 1];
            finished = new boolean[2 * n + 1];
            stack = new Stack<>();
            adj = new ArrayList<>();
            for (i = 0; i < 2 * n + 1; i++) {
            	adj.add(new ArrayList<>());
            }

            // 간선 그리기
            adj.get(opp(1)).add(1);
            for (i = 0; i < m; i++) {
            	str = br.readLine();
                st = new StringTokenizer(str);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (a < 0) a = n - a;
                if (b < 0) b = n - b;
                adj.get(opp(a)).add(b);
                adj.get(opp(b)).add(a);
            }

            // SCC 탐색
            for (i = 1; i <= 2 * n; i++)
                if (order[i] == 0)
                    dfs(i);

            // 같은 SCC인지 확인
            boolean valid = true;
            for (i = 1; i <= n; i++) {
                if (sccNum[i] == sccNum[opp(i)]) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
            	sb.append("yes\n");
            }
            else {
            	sb.append("no\n");
            }
        }
        
        // 출력
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}