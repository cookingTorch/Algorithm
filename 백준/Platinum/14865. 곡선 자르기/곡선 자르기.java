import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, start, temp, size, ans0, ans1, i;
		int[] first, point, prev;
		boolean flag;
		ArrayList<int[]> points;
		Deque<Integer> dq0, dq1;
		
		n = Integer.parseInt(br.readLine());
		points = new ArrayList<>();
		flag = false;
		temp = 0;
		start = 0;
		st = new StringTokenizer(br.readLine());
		first = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		prev = first;
		for (i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			point = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			if (prev[1] < 0 && point[1] > 0) {
				temp = point[0];
				flag = true;
			} else if (prev[1] > 0 && point[1] < 0) {
				if (flag) {
					points.add(new int[] {Math.min(temp, point[0]), Math.max(temp, point[0])});
					flag = false;
				} else {
					start = point[0];
				}
			}
			prev = point;
		}
		point = first;
		if (prev[1] < 0 && point[1] > 0) {
			temp = point[0];
			flag = true;
		} else if (prev[1] > 0 && point[1] < 0) {
			if (flag) {
				points.add(new int[] {Math.min(temp, point[0]), Math.max(temp, point[0])});
				flag = false;
			} else {
				start = point[0];
			}
		}
		if (flag) {
			points.add(new int[] {Math.min(temp, start), Math.max(temp, start)});
		}
		Collections.sort(points, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}
		});
		ans0 = 0;
		ans1 = 0;
		dq0 = new ArrayDeque<>();
		dq1 = new ArrayDeque<>();
		size = points.size();
		for (i = 0; i < size; i++) {
			point = points.get(i);
			if (dq0.isEmpty() || point[0] < dq1.peekFirst()) {
				dq0.addLast(point[0]);
				dq1.addFirst(point[1]);
			} else {
				ans1++;
				dq0.pollLast();
				dq1.pollFirst();
				while (!dq0.isEmpty() && point[0] > dq1.peekFirst()) {
					dq0.pollLast();
					dq1.pollFirst();
				}
				if (dq0.isEmpty()) {
					ans0++;
				}
				dq0.addLast(point[0]);
				dq1.addFirst(point[1]);
			}
		}
		if (!dq0.isEmpty()) {
			ans1++;
			ans0++;
		}
		sb.append(ans0).append(' ').append(ans1);
		System.out.print(sb);
	}
}
