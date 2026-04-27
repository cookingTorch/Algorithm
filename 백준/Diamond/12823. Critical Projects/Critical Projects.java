import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	
	private static int n;
	private static int m;
	private static int edgeCnt;
	private static int maxLayer;
	private static int[] head;
	private static int[] next;
	private static int[] to;
	private static int[] indeg;
	private static int[] layer;
	private static int[] temp;
	
	private static final class FastScanner {
		private final byte[] buffer = new byte[1 << 16];
		private int idx;
		private int size;
		
		private final int read() throws IOException {
			if (idx == size) {
				size = System.in.read(buffer);
				idx = 0;
			}
			return size == -1 ? -1 : buffer[idx++];
		}
		
		private final int nextInt() throws IOException {
			int c;
			int ret;
			
			c = read();
			while (c <= 32) {
				c = read();
			}
			ret = 0;
			while (c > 32) {
				ret = ret * 10 + c - '0';
				c = read();
			}
			return ret;
		}
	}
	
	private static final void addEdge(int u, int v) {
		to[++edgeCnt] = v;
		next[edgeCnt] = head[u];
		head[u] = edgeCnt;
		indeg[v]++;
	}
	
	private static final void topology() {
		int i;
		int curr;
		int child;
		int headIdx;
		int tailIdx;
		int edge;
		
		headIdx = 0;
		tailIdx = 0;
		for (i = 1; i <= n; i++) {
			if (indeg[i] == 0) {
				layer[i] = 1;
				temp[tailIdx++] = i;
			}
		}
		while (headIdx < tailIdx) {
			curr = temp[headIdx++];
			if (maxLayer < layer[curr]) {
				maxLayer = layer[curr];
			}
			for (edge = head[curr]; edge != 0; edge = next[edge]) {
				child = to[edge];
				if (layer[child] < layer[curr] + 1) {
					layer[child] = layer[curr] + 1;
				}
				if (--indeg[child] == 0) {
					temp[tailIdx++] = child;
				}
			}
		}
	}
	
	private static final void calc() {
		int i;
		int edge;
		int minLayer;
		
		temp[0] = 0;
		for (i = 1; i <= maxLayer; i++) {
			temp[i] = 0;
		}
		for (i = 1; i <= n; i++) {
			indeg[layer[i]]++;
			minLayer = INF;
			for (edge = head[i]; edge != 0; edge = next[edge]) {
				if (layer[to[edge]] < minLayer) {
					minLayer = layer[to[edge]];
				}
			}
			if (temp[layer[i]] < minLayer) {
				temp[layer[i]] = minLayer;
			}
		}
		for (i = 1; i <= maxLayer; i++) {
			if (temp[i] < temp[i - 1]) {
				temp[i] = temp[i - 1];
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		int i;
		int u;
		int v;
		int cnt;
		FastScanner fs;
		BufferedWriter bw;
		
		fs = new FastScanner();
		n = fs.nextInt();
		m = fs.nextInt();
		head = new int[n + 1];
		next = new int[m + 1];
		to = new int[m + 1];
		indeg = new int[n + 1];
		layer = new int[n + 1];
		temp = new int[n + 1];
		for (i = 0; i < m; i++) {
			u = fs.nextInt();
			v = fs.nextInt();
			addEdge(u, v);
		}
		topology();
		calc();
		cnt = 0;
		for (i = 1; i <= n; i++) {
			if (indeg[layer[i]] == 1 && temp[layer[i] - 1] <= layer[i]) {
				cnt++;
			}
		}
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(cnt));
		if (cnt != 0) {
			bw.newLine();
			for (i = 1; i <= n; i++) {
				if (indeg[layer[i]] == 1 && temp[layer[i] - 1] <= layer[i]) {
					bw.write(String.valueOf(i));
					bw.write(' ');
				}
			}
		}
		bw.flush();
	}
}