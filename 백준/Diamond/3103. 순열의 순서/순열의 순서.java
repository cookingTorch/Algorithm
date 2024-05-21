import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	private static final int MOD = 1_000_000_007;
	private static final int RADIX = 10;
	private static final int DIFF = '0';
	private static final long MOD_L = 1_000_000_007L;
	private static final char SPACE = ' ';
	private static final char LINE_BREAK = '\n';
	
	private static int k;
	private static int len;
	private static int offset;
	private static int endIdx;
	private static int[] sum;
	private static int[] fact;
	private static int[] left;
	private static int[] right;
	private static int[] facts;
	private static int[] trees;
	private static String str;
	
	private static final int nextInt() {
		int val;
		char ch;
		
		val = str.charAt(offset) - DIFF;
		for (++offset; offset < len && (ch = str.charAt(offset)) != SPACE; offset++) {
			val = val * RADIX + ch - DIFF;
		}
		offset++;
		return val;
	}
	
	private static final void generateFacts() {
		int i;
		long j;
		
		facts = new int[k + 1];
		facts[k] = 0;
		facts[k - 1] = 1;
		for (i = k - 2, j = 2L; i > 0; i--, j++) {
			facts[i] = (int) ((long) facts[i + 1] * j % MOD_L);
		}
	}
	
	private static final void init() {
		int size;
		int power;
		int height;
		
		for (power = 1, height = 1; power < k; power <<= 1, height++);
		size = (power << 1) - 1 + k * height;
		sum = new int[size];
		fact = new int[size];
		left = new int[size];
		right = new int[size];
		endIdx = 0;
		trees = new int[k + 1];
		trees[0] = endIdx++;
		init(trees[0], 1, k);
	}
	
	private static final void init(int node, int start, int end) {
		int mid;
		
		if (start == end) {
			return;
		}
		mid = start + end >> 1;
		left[node] = endIdx++;
		right[node] = endIdx++;
		init(left[node], start, mid);
		init(right[node], mid + 1, end);
	}
	
	private static final int insert(int node, int start, int end, int idx, int num) {
		int mid;
		int newNode;
		
		newNode = endIdx++;
		if (start == end) {
			sum[newNode] = 1;
			fact[newNode] = facts[idx];
			return newNode;
		}
		mid = start + end >> 1;
		if (num <= mid) {
			left[newNode] = insert(left[node], start, mid, idx, num);
			right[newNode] = right[node];
		} else {
			left[newNode] = left[node];
			right[newNode] = insert(right[node], mid + 1, end, idx, num);
		}
		sum[newNode] = (sum[left[newNode]] + sum[right[newNode]]) % MOD;
		fact[newNode] = (fact[left[newNode]] + fact[right[newNode]]) % MOD;
		return newNode;
	}
	
	private static final int sumQuery(int l, int r, int start, int end, int min, int max) {
		int mid;
		
		if (end < min || max < start) {
			return 0;
		}
		if (min <= start && end <= max) {
			return sum[r] - sum[l];
		}
		mid = start + end >> 1;
		return (sumQuery(left[l], left[r], start, mid, min, max) + sumQuery(right[l], right[r], mid + 1, end, min, max)) % MOD;
	}
	
	private static final int factQuery(int l, int r, int start, int end, int min, int max) {
		int mid;
		
		if (end < min || max < start) {
			return 0;
		}
		if (min <= start && end <= max) {
			return fact[r] - fact[l];
		}
		mid = start + end >> 1;
		return (factQuery(left[l], left[r], start, mid, min, max) + factQuery(right[l], right[r], mid + 1, end, min, max)) % MOD;
	}
	
	private static final char[] intToCharArray(int num) {
		int i;
		int tens;
		int length;
		char[] ret;
		
		for (tens = 10, length = 1; tens <= num; tens *= RADIX, length++);
		ret = new char[length + 1];
		for (i = length - 1; i >= 0; i--) {
			ret[i] = (char) (num % RADIX + DIFF);
			num /= RADIX;
		}
		ret[length] = LINE_BREAK;
		return ret;
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int a;
		int b;
		int i;
		int val;
		int ans;
		int[] arr;
		BufferedReader br;
		BufferedWriter bw;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		len = str.length();
		offset = 0;
		k = nextInt();
		n = nextInt();
		generateFacts();
		init();
		arr = new int[k + 1];
		str = br.readLine();
		len = str.length();
		offset = 0;
		for (i = 1; i <= k; i++) {
			arr[i] = nextInt();
			trees[i] = insert(trees[i - 1], 1, k, i, arr[i]);
		}
		val = 1;
		for (i = 1; i <= k; i++) {
			val = (val + (int) ((long) sumQuery(trees[i], trees[k], 1, k, 1, arr[i] - 1) * facts[i] % MOD_L)) % MOD;
		}
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (i = 0; i < n; i++) {
			str = br.readLine();
			len = str.length();
			offset = 0;
			a = nextInt();
			b = nextInt();
			ans = val;
			if (arr[a] > arr[b]) {
				ans = (ans - (int) ((long) sumQuery(trees[a], trees[k], 1, k, arr[b], arr[a] - 1) * facts[a] % MOD_L)) % MOD;
				ans = (ans - factQuery(trees[a], trees[b - 1], 1, k, arr[b], arr[a])) % MOD;
				ans = (ans + (int) ((long) sumQuery(trees[b], trees[k], 1, k, arr[b], arr[a]) * facts[b] % MOD_L)) % MOD;
			} else {
				ans = (ans + (int) ((long) sumQuery(trees[a], trees[k], 1, k, arr[a] + 1, arr[b]) * facts[a] % MOD_L)) % MOD;
				ans = (ans + factQuery(trees[a], trees[b - 1], 1, k, arr[a], arr[b])) % MOD;
				ans = (ans - (int) ((long) sumQuery(trees[b], trees[k], 1, k, arr[a], arr[b]) * facts[b] % MOD_L)) % MOD;
			}
			bw.write(intToCharArray((ans + MOD) % MOD));
		}
		bw.flush();
		bw.close();
	}
}
