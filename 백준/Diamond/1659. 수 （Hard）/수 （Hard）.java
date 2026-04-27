import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int S = 0;
	private static final long INF = Long.MAX_VALUE >> 2;
	
	private static int n;
	private static int m;
	private static int si;
	private static int ti;
	private static int aLen;
	private static int bLen;
	private static int[] s;
	private static int[] t;
	private static int[] a;
	private static int[] b;
	private static long[] oldDp;
	private static long[] newDp;
	private static long[] pa;
	private static long[] pb;
	private static long[] pref;
	private static long[] suf;
	
	private static final int getColor() {
		if (ti > m) {
			return S;
		}
		if (si > n) {
			return 1;
		}
		return s[si] <= t[ti] ? S : 1;
	}
	
	private static final int readRun(int[] arr) {
		int len;
		int color;
		
		if (si > n && ti > m) {
			return 0;
		}
		len = 0;
		color = getColor();
		while (si <= n || ti <= m) {
			if (getColor() != color) {
				break;
			}
			if (color == S) {
				arr[++len] = s[si++];
			} else {
				arr[++len] = t[ti++];
			}
		}
		return len;
	}
	
	private static final void transition() {
		int i;
		int k;
		int idx;
		int ap;
		int b1;
		long val;
		long min;
		
		pa[0] = 0;
		for (i = 1; i <= aLen; i++) {
			pa[i] = pa[i - 1] + a[i];
		}
		pb[0] = 0;
		for (i = 1; i <= bLen; i++) {
			pb[i] = pb[i - 1] + b[i];
		}
		ap = a[aLen];
		b1 = b[1];
		min = INF;
		for (i = aLen; i >= 0; i--) {
			val = oldDp[i] + pa[i] - (long) i * ap;
			if (val < min) {
				min = val;
			}
			suf[i] = min;
		}
		min = INF;
		for (i = 0; i <= aLen; i++) {
			val = oldDp[i] + pa[i] - (long) i * b1;
			if (val < min) {
				min = val;
			}
			pref[i] = min;
		}
		for (k = 0; k <= bLen; k++) {
			idx = aLen - k;
			if (idx < 0) {
				idx = 0;
			}
			newDp[k] = pb[k] + (long) (aLen - k) * ap - pa[aLen] + suf[idx];
			if (aLen - k - 1 >= 0) {
				val = pb[k] + (long) (aLen - k) * b1 - pa[aLen] + pref[aLen - k - 1];
				if (val < newDp[k]) {
					newDp[k] = val;
				}
			}
		}
	}
	
	private static final long solve() {
		int i;
		int[] tempArr;
		long[] tempDp;
		
		si = 1;
		ti = 1;
		aLen = readRun(a);
		oldDp[0] = 0;
		for (i = 1; i <= aLen; i++) {
			oldDp[i] = INF;
		}
		while ((bLen = readRun(b)) != 0) {
			transition();
			tempArr = a;
			a = b;
			b = tempArr;
			aLen = bLen;
			tempDp = oldDp;
			oldDp = newDp;
			newDp = tempDp;
		}
		return oldDp[aLen];
	}
	
	public static void main(String[] args) throws IOException {
		int i;
		int max;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		s = new int[n + 1];
		t = new int[m + 1];
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			s[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= m; i++) {
			t[i] = Integer.parseInt(st.nextToken());
		}
		max = Math.max(n, m) + 2;
		a = new int[max];
		b = new int[max];
		oldDp = new long[max];
		newDp = new long[max];
		pa = new long[max];
		pb = new long[max];
		pref = new long[max];
		suf = new long[max];
		System.out.print(solve());
	}
}