import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	
	private static final class Student implements Comparable<Student> {
		int rank1, rank2, rank3;
		
		@Override
		public int compareTo(Student o) {
			return Integer.compare(this.rank1, o.rank1);
		}
	}
	
	private static int[] tree;
	private static Student[] students;
	
	private static final void init(int node, int start, int end) {
		int mid;
		
		tree[node] = INF;
		if (start == end) {
			return;
		}
		mid = start + end >> 1;
		init(node << 1, start, mid);
		init(node << 1 | 1, mid + 1, end);
	}
	
	private static final int query(int node, int start, int end, int rank) {
		int mid;
		
		if (rank < start) {
			return INF;
		}
		if (end <= rank) {
			return tree[node];
		}
		mid = start + end >> 1;
		return Math.min(query(node << 1, start, mid, rank), query(node << 1 | 1, mid + 1, end, rank));
	}
	
	private static final void update(int node, int start, int end, int idx, int val) {
		int mid;
		
		if (idx < start || end < idx) {
			return;
		}
		tree[node] = Math.min(tree[node], val);
		if (start == end) {
			return;
		}
		mid = start + end >> 1;
		update(node << 1, start, mid, idx, val);
		update(node << 1 | 1, mid + 1, end, idx, val);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1, st2, st3;
		
		int n, cnt, i;
		
		n = Integer.parseInt(br.readLine());
		students = new Student[n];
		for (i = 0; i < n; i++) {
			students[i] = new Student();
		}
		st1 = new StringTokenizer(br.readLine());
		st2 = new StringTokenizer(br.readLine());
		st3 = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			students[Integer.parseInt(st1.nextToken()) - 1].rank1 = i;
			students[Integer.parseInt(st2.nextToken()) - 1].rank2 = i;
			students[Integer.parseInt(st3.nextToken()) - 1].rank3 = i;
		}
		Arrays.sort(students);
		tree = new int[4 * n];
		init(1, 0, n - 1);
		cnt = 0;
		for (i = 0; i < n; i++) {
			if (query(1, 0, n - 1, students[i].rank2) > students[i].rank3) {
				cnt++;
			}
			update(1, 0, n - 1, students[i].rank2, students[i].rank3);
		}
		System.out.print(cnt);
	}
}
