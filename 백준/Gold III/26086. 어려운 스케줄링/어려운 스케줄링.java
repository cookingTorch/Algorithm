import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, q, k, lastSort, sort, reverse, command, i;
		int[] query, cnt;
		boolean flag;
		LinkedList<Integer> list;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		lastSort = -1;
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
		flag = true;
		cnt = new int[n + 1];
		for (i = 0; i < lastSort; i++) {
			if (query[i] == reverse) {
				flag ^= true;
			} else if (query[i] != sort) {
				cnt[query[i]]++;
			}
		}
		list = new LinkedList<>();
		if (lastSort != -1) {
			if (flag) {
				for (i = 1; i <= n; i++) {
					if (cnt[i] == 1) {
						list.add(i);
					}
				}
			} else {
				for (i = n; i > 0; i--) {
					if (cnt[i] == 1) {
						list.add(i);
					}
				}
			}
		}
		for (i = lastSort + 1; i < q; i++) {
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
