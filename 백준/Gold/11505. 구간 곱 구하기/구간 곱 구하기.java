import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static long[] nums, segmentTree;

    private static long init(int start, int end, int node) {
        if (start == end) {
        	return segmentTree[node] = nums[start];
        }
        else {
        	int mid = (start + end) / 2;
            return segmentTree[node] = (init(start, mid, node * 2) * init(mid + 1, end, node * 2 + 1)) % 1000000007;
        }
    }

    private static long update(int start, int end, int node, int idx, long val) {
        if (idx < start || idx > end) {
        	return segmentTree[node];
        }
        if (start == end) {
        	return segmentTree[node] = val;
        }
        int mid = (start + end) / 2;
        return segmentTree[node] = (update(start, mid, node * 2, idx, val) * update(mid + 1, end, node * 2 + 1, idx, val)) % 1000000007;
    }

    private static long query(int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
        	return 1;
        }
        else if (left <= start && end <= right) {
        	return segmentTree[node];
        }
        else {
        	int mid = (start + end) / 2;
            return (query(start, mid, node * 2, left, right) * query(mid + 1, end, node * 2 + 1, left, right)) % 1000000007;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String str;
        StringTokenizer st;
        
        int n, m, k, i;
        
        str = br.readLine();
        st = new StringTokenizer(str);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        nums = new long[n + 1];
        segmentTree = new long[n * 4];
        for (i = 1; i <= n; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }
        init(1, n, 1);
        
        for (i = 0; i < m + k; i++) {
        	str = br.readLine();
            st = new StringTokenizer(str);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 1) {
                nums[b] = c;
                update(1, n, 1, b, c);
            }
            else {
            	sb.append(query(1, n, 1, b, c)).append("\n");
            }
        }
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}