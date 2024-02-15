import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, q, k, lastSort, sort, reverse, command, i;
		int[] query;
		boolean flag;
		LinkedList<Integer> list;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		lastSort = q;
		sort = n + 1;
		reverse = n + 2;
		query = new int[q];
		for (i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			command = Integer.parseInt(st.nextToken());
			switch (command) {
			case 0:
				query[i] = Integer.parseInt(st.nextToken());
				break;
			case 1:
				query[i] = sort;
				lastSort = i;
				break;
			case 2:
				query[i] = reverse;
			}
		}
		list = new LinkedList<>();
		flag = true;
		for (i = 0; i < lastSort; i++) {
			if (query[i] == reverse) {
				flag ^= true;
			} else if (query[i] != sort) {
				if (flag) {
					list.addFirst(query[i]);
				} else {
					list.addLast(query[i]);
				}
			}
		}
		if (lastSort != q) {
			if (flag) {
				Collections.sort(list);
			} else {
				Collections.sort(list, Collections.reverseOrder());
			}
		}
		for (; i < q; i++) {
			if (query[i] == reverse) {
				flag ^= true;
			} else if (query[i] != sort) {
				if (flag) {
					list.addFirst(query[i]);
				} else {
					list.addLast(query[i]);
				}
			}
		}
		if (flag) {
			System.out.print(list.get(k - 1));
		} else {
			System.out.print(list.get(list.size() - k));
		}
	}
}
