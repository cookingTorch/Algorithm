import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	private static final class Mine {
		int x;
		int y;
		int idx;
		long num;
		Mine next;
		
		Mine(int x, int y, long num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
		
		final Mine add(Mine mine) {
			this.next = mine;
			return this;
		}
	}
	
	private static final class Node {
		long sum;
		long max;
		long lMax;
		long rMax;
		
		final void fill(long num) {
			sum = num;
			max = num;
			lMax = num;
			rMax = num;
		}
	}
	
	private static Node[] tree;
	
	private static void build(int node, int start, int end) {
		int mid;
		
		tree[node] = new Node();
		if (start == end) {
			return;
		}
		mid = start + end >> 1;
		build(node << 1, start, mid);
		build(node << 1 | 1, mid + 1, end);
	}
	
	private static void init(int node, int start, int end) {
		int mid;
		
		tree[node].fill(0L);
		if (start == end) {
			return;
		}
		mid = start + end >> 1;
		init(node << 1, start, mid);
		init(node << 1 | 1, mid + 1, end);
	}
	
	private static final void merge(Node left, Node right, Node dest) {
		dest.sum = left.sum + right.sum;
		dest.max = Math.max(Math.max(left.max, right.max), left.rMax + right.lMax);
		dest.lMax = Math.max(left.lMax, left.sum + right.lMax);
		dest.rMax = Math.max(right.rMax, right.sum + left.rMax);
	}
	
	private static final void update(int node, int start, int end, int idx, long num) {
		int mid;
		
		if (start == end) {
			tree[node].fill(tree[node].sum + num);
			return;
		}
		mid = start + end >> 1;
		if (idx <= mid) {
			update(node << 1, start, mid, idx, num);
		} else {
			update(node << 1 | 1, mid + 1, end, idx, num);
		}
		merge(tree[node << 1], tree[node << 1 | 1], tree[node]);
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int j;
		int yIdx;
		int xLen;
		int yLen;
		int[] xArr;
		int[] yArr;
		int[] xUnique;
		int[] yUnique;
		long max;
		Mine curr;
		Mine[] mines;
		Mine[] mineList;
		BufferedReader br;
		StringTokenizer st;
		HashMap<Integer, Integer> xMap;
		HashMap<Integer, Integer> yMap;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		xArr = new int[n];
		yArr = new int[n];
		mines = new Mine[n];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			xArr[i] = Integer.parseInt(st.nextToken());
			yArr[i] = Integer.parseInt(st.nextToken());
			mines[i] = new Mine(xArr[i], yArr[i], Long.parseLong(st.nextToken()));
		}
		xUnique = Arrays.stream(xArr).distinct().sorted().toArray();
		yUnique = Arrays.stream(yArr).distinct().sorted().toArray();
		xMap = new HashMap<>();
		yMap = new HashMap<>();
		xLen = xUnique.length;
		yLen = yUnique.length;
		for (i = 0; i < xLen; i++) {
			xMap.put(xUnique[i], i);
		}
		for (i = 0; i < yLen; i++) {
			yMap.put(yUnique[i], i);
		}
		mineList = new Mine[yLen];
		for (Mine mine : mines) {
			mine.idx = xMap.get(mine.x);
			yIdx = yMap.get(mine.y);
			mineList[yIdx] = mine.add(mineList[yIdx]);
		}
		max = 0;
		tree = new Node[4 * xLen];
		build(1, 0, xLen - 1);
		for (i = 0; i < yLen; i++) {
			init(1, 0, xLen - 1);
			for (j = i; j < yLen; j++) {
				for (curr = mineList[j]; curr != null; curr = curr.next) {
					update(1, 0, xLen - 1, curr.idx, curr.num);
				}
				max = Math.max(max, tree[1].max);
			}
		}
		System.out.print(max);
	}
}
