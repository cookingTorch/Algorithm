import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	private static int n, vertexCnt, sccCnt;
    private static int[] sccNum, order;
    private static boolean[] finished;
    private static Stack<Integer> stack;
    private static ArrayList<ArrayList<Integer>> adj;
    
    // 찬반 전환
    private static int opposite(int num) {
        return num > n ? num - n : num + n;
    }
    
    // DFS
    private static int findSCC(int node) {
    	int minOrder, stackNum;
        order[node] = ++vertexCnt;
        stack.push(node);
        minOrder = order[node];
        
        // 연결된 노드 탐색
        for (int next : adj.get(node)) {
            if (order[next] == 0) {
            	minOrder = Math.min(minOrder, findSCC(next));
            }
            else if (!finished[next]) {
            	minOrder = Math.min(minOrder, order[next]);
            }
        }
        
        // 루트 노드일 경우 새로운 SCC 형성
        if (minOrder == order[node]) {
            do {
                stackNum = stack.pop();
                finished[stackNum] = true;
                sccNum[stackNum] = sccCnt;
            } while (stackNum != node);
            sccCnt++;
        }
        return minOrder;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String str;

        int m, a, b, i;
        boolean valid;
        
        // 테스트 케이스
        while (true) {
            str = br.readLine();
            if (str == null) {
            	break;
            }
            StringTokenizer st = new StringTokenizer(str);
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            // 간선 그리기
            adj = new ArrayList<>();
            for (i = 0; i < 2 * n + 1; i++) {
            	adj.add(new ArrayList<>());
            }
            adj.get(opposite(1)).add(1);
            for (i = 0; i < m; i++) {
            	str = br.readLine();
                st = new StringTokenizer(str);
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                if (a < 0) a = n - a;
                if (b < 0) b = n - b;
                adj.get(opposite(a)).add(b);
                adj.get(opposite(b)).add(a);
            }
            
            // 초기화
            sccCnt = 0;
            vertexCnt = 0;
            sccNum = new int[2 * n + 1];
            order = new int[2 * n + 1];
            finished = new boolean[2 * n + 1];
            stack = new Stack<>();

            // SCC 탐색
            for (i = 1; i <= 2 * n; i++) {
            	if (order[i] == 0) {
            		findSCC(i);
            	}
            }

            // 같은 SCC인지 확인
            valid = true;
            for (i = 1; i <= n; i++) {
                if (sccNum[i] == sccNum[opposite(i)]) {
                    valid = false;
                    break;
                }
            }
            
            // 출력
            if (valid) {
            	sb.append("yes\n");
            }
            else {
            	sb.append("no\n");
            }
        }
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}