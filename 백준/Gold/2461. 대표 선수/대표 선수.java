import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	
	private static final class Student implements Comparable<Student> {
		int idx;
		int val;
		
		Student(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}
		
		@Override
		public int compareTo(Student o) {
			return Integer.compare(this.val, o.val);
		}
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int i;
		int j;
		int idx;
		int min;
		int size;
		int left;
		int right;
		int unique;
		int[] cnt;
		Student[] arr;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		idx = 0;
		size = n * m;
		arr = new Student[size + 1];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < m; j++) {
				arr[idx++] = new Student(i, Integer.parseInt(st.nextToken()));
			}
		}
		arr[size] = new Student(n, INF);
		Arrays.sort(arr);
		right = -1;
		cnt = new int[n + 1];
		unique = 0;
		while (unique != n) {
			if (cnt[arr[++right].idx]++ == 0) {
				unique++;
			}
		}
		left = -1;
		min = INF;
		for (; right != size;) {
			while (--cnt[arr[++left].idx] != 0);
			min = Math.min(min, arr[right].val - arr[left].val);
			while (cnt[arr[++right].idx]++ != 0);
		}
		System.out.print(min);
	}
}
