import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final class Node {
		int idx;
		Node next;
		
		Node(int idx, Node next) {
			this.idx = idx;
			this.next = next;
		}
	}
	
	private static final class Stack {
		int size;
		int[] arr;
		
		Stack() {
			arr = new int[4];
		}
		
		boolean isEmpty() {
			return size == 0;
		}
		
		int peekLast() {
			return arr[size - 1];
		}
		
		void addLast(int val) {
			if (size == arr.length) {
				int i;
				int[] temp;
				
				temp = new int[arr.length << 1];
				for (i = 0; i < size; i++) {
					temp[i] = arr[i];
				}
				arr = temp;
			}
			arr[size++] = val;
		}
		
		void pollLast() {
			size--;
		}
	}
	
	private static final class Heap {
		int size;
		int[] arr;
		
		Heap() {
			arr = new int[4];
		}
		
		boolean isEmpty() {
			return size == 0;
		}
		
		int peek() {
			return arr[1];
		}
		
		void add(int val) {
			int idx;
			
			if (size + 1 == arr.length) {
				expand();
			}
			arr[++size] = val;
			for (idx = size; idx > 1 && compare(arr[idx], arr[idx >> 1]) < 0; idx >>= 1) {
				swap(idx, idx >> 1);
			}
		}
		
		int poll() {
			int ret;
			int idx;
			int child;
			
			ret = arr[1];
			arr[1] = arr[size--];
			idx = 1;
			while ((idx << 1) <= size) {
				child = idx << 1;
				if (child + 1 <= size && compare(arr[child + 1], arr[child]) < 0) {
					child++;
				}
				if (compare(arr[idx], arr[child]) <= 0) {
					break;
				}
				swap(idx, child);
				idx = child;
			}
			return ret;
		}
		
		private void expand() {
			int i;
			int[] temp;
			
			temp = new int[arr.length << 1];
			for (i = 1; i <= size; i++) {
				temp[i] = arr[i];
			}
			arr = temp;
		}
		
		private void swap(int i, int j) {
			int temp;
			
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}
	
	private static int n;
	private static int arcCnt;
	private static int nArc;
	private static int nHeap;
	private static long[] w;
	private static long[] cp;
	private static long[] base;
	private static long[] mul;
	private static long[] num;
	private static long[] den;
	private static int[] u;
	private static int[] v;
	private static int[] low;
	private static int[] hid;
	private static int[] sub;
	private static int[] arcU;
	private static int[] arcV;
	private static Node[] child;
	private static Stack[] con;
	private static Heap[] heap;
	
	private static final int compare(int a, int b) {
		long sa;
		long sb;
		
		sa = support(a);
		sb = support(b);
		if (sa < sb) {
			return 1;
		}
		if (sa > sb) {
			return -1;
		}
		return b - a;
	}
	
	private static final void init() {
		int i;
		int size;
		
		size = (n << 1) + 10;
		cp = new long[n + 2];
		base = new long[size];
		mul = new long[size];
		num = new long[size];
		den = new long[size];
		u = new int[size];
		v = new int[size];
		low = new int[size];
		hid = new int[size];
		sub = new int[size];
		arcU = new int[size];
		arcV = new int[size];
		child = new Node[size];
		con = new Stack[n + 2];
		heap = new Heap[size];
		for (i = 0; i <= n + 1; i++) {
			con[i] = new Stack();
		}
	}
	
	private static final void rotateByMin() {
		int i;
		int p;
		int idx;
		long[] nw;
		
		p = 1;
		for (i = 2; i <= n; i++) {
			if (w[i] < w[p]) {
				p = i;
			}
		}
		nw = new long[n + 2];
		idx = 1;
		for (i = p; i <= n; i++) {
			nw[idx++] = w[i];
		}
		for (i = 1; i < p; i++) {
			nw[idx++] = w[i];
		}
		w = nw;
		w[n + 1] = w[1];
	}
	
	private static final void collectHArcs() {
		int i;
		int top;
		int tmpCnt;
		int[] stack;
		int[] tmpU;
		int[] tmpV;
		
		stack = new int[n + 2];
		tmpU = new int[n + 2];
		tmpV = new int[n + 2];
		top = 0;
		tmpCnt = 0;
		for (i = 1; i <= n; i++) {
			while (top >= 2 && w[stack[top - 1]] > w[i]) {
				tmpU[tmpCnt] = stack[top - 2];
				tmpV[tmpCnt++] = i;
				top--;
			}
			stack[top++] = i;
		}
		while (top >= 4) {
			tmpU[tmpCnt] = 1;
			tmpV[tmpCnt++] = stack[top - 2];
			top--;
		}
		for (i = 0; i < tmpCnt; i++) {
			if (tmpU[i] == 1 || tmpV[i] == 1) {
				continue;
			}
			arcU[arcCnt] = tmpU[i];
			arcV[arcCnt++] = tmpV[i];
		}
	}
	
	private static final int newArc(int a, int b) {
		nArc++;
		u[nArc] = a;
		v[nArc] = b;
		low[nArc] = w[a] < w[b] ? a : b;
		mul[nArc] = w[a] * w[b];
		base[nArc] = cp[b] - cp[a] - mul[nArc];
		return nArc;
	}
	
	private static final boolean contains(int a, int b) {
		return u[a] <= u[b] && v[b] <= v[a];
	}
	
	private static final void buildTree() {
		int i;
		int top;
		int curr;
		int[] stack;
		
		stack = new int[arcCnt + 5];
		top = 0;
		newArc(1, n + 1);
		for (i = 0; i < arcCnt; i++) {
			curr = newArc(arcU[i], arcV[i]);
			while (top > 0 && contains(curr, stack[top - 1])) {
				child[curr] = new Node(stack[--top], child[curr]);
			}
			stack[top++] = curr;
		}
		while (top > 0) {
			child[1] = new Node(stack[--top], child[1]);
		}
	}
	
	private static final long support(int idx) {
		return num[idx] / den[idx];
	}
	
	private static final long getMul(int curr) {
		Stack stack;
		
		if (curr == 1) {
			return w[1] * w[2] + w[1] * w[n];
		}
		if (u[curr] == low[curr]) {
			stack = con[u[curr]];
			if (stack.isEmpty() || !contains(curr, stack.peekLast())) {
				return w[u[curr]] * w[u[curr] + 1];
			}
			return mul[stack.peekLast()];
		}
		stack = con[v[curr]];
		if (stack.isEmpty() || !contains(curr, stack.peekLast())) {
			return w[v[curr]] * w[v[curr] - 1];
		}
		return mul[stack.peekLast()];
	}
	
	private static final void addArc(int curr) {
		heap[hid[curr]].add(curr);
		con[u[curr]].addLast(curr);
		con[v[curr]].addLast(curr);
	}
	
	private static final void removeArc(int curr) {
		int idx;
		
		idx = heap[hid[curr]].poll();
		con[u[idx]].pollLast();
		con[v[idx]].pollLast();
	}
	
	private static final void mergeHeap(int curr) {
		int maxChild;
		Node node;
		Heap src;
		Heap dst;
		
		maxChild = -1;
		for (node = child[curr]; node != null; node = node.next) {
			if (maxChild == -1 || sub[maxChild] < sub[node.idx]) {
				maxChild = node.idx;
			}
		}
		hid[curr] = hid[maxChild];
		dst = heap[hid[curr]];
		for (node = child[curr]; node != null; node = node.next) {
			if (node.idx == maxChild) {
				continue;
			}
			src = heap[hid[node.idx]];
			while (!src.isEmpty()) {
				dst.add(src.poll());
			}
		}
	}
	
	private static final void process(int curr) {
		int idx;
		Node node;
		Heap h;
		
		sub[curr] = 1;
		den[curr] = base[curr];
		if (child[curr] == null) {
			hid[curr] = ++nHeap;
			heap[hid[curr]] = new Heap();
			num[curr] = w[low[curr]] * (den[curr] + mul[curr] - getMul(curr));
			addArc(curr);
			return;
		}
		for (node = child[curr]; node != null; node = node.next) {
			sub[curr] += sub[node.idx];
			den[curr] -= base[node.idx];
		}
		num[curr] = w[low[curr]] * (den[curr] + mul[curr] - getMul(curr));
		mergeHeap(curr);
		h = heap[hid[curr]];
		while (!h.isEmpty() && support(h.peek()) >= w[low[curr]]) {
			idx = h.peek();
			den[curr] += den[idx];
			removeArc(curr);
			num[curr] = w[low[curr]] * (den[curr] + mul[curr] - getMul(curr));
		}
		while (!h.isEmpty() && support(curr) <= support(h.peek())) {
			idx = h.peek();
			den[curr] += den[idx];
			removeArc(curr);
			num[curr] += num[idx];
		}
		addArc(curr);
	}
	
	private static final long solve() {
		int i;
		int top;
		int cnt;
		int curr;
		long ret;
		int[] stack;
		int[] order;
		Node node;
		Heap h;
		
		stack = new int[nArc + 5];
		order = new int[nArc + 5];
		top = 0;
		cnt = 0;
		stack[top++] = 1;
		while (top > 0) {
			curr = stack[--top];
			order[cnt++] = curr;
			for (node = child[curr]; node != null; node = node.next) {
				stack[top++] = node.idx;
			}
		}
		for (i = cnt - 1; i >= 0; i--) {
			process(order[i]);
		}
		ret = 0;
		h = heap[hid[1]];
		while (!h.isEmpty()) {
			ret += num[h.poll()];
		}
		return ret;
	}
	
	public static void main(String[] args) throws IOException {
		int m;
		int i;
		long r;
		long c;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		m = Integer.parseInt(br.readLine());
		n = m + 1;
		w = new long[n + 2];
		for (i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			r = Long.parseLong(st.nextToken());
			c = Long.parseLong(st.nextToken());
			if (i == 1) {
				w[1] = r;
			}
			w[i + 1] = c;
		}
		if (m == 1) {
			System.out.print(0);
			return;
		}
		init();
		rotateByMin();
		collectHArcs();
		for (i = 1; i <= n + 1; i++) {
			cp[i] = cp[i - 1] + w[i] * w[i - 1];
		}
		buildTree();
		System.out.print(solve());
	}
}