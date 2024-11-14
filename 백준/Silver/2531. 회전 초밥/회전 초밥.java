import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final class ArrayDeque {
		private int head;
		private int tail;
		private int size;
		private int[] dq;

		ArrayDeque(int size) {
			head = 0;
			tail = -1;
			this.size = size;
			dq = new int[size];
		}

		void addLast(int val) {
			dq[tail = (tail + 1) % size] = val;
		}

		int pollFirst() {
			int val;

			val = dq[head];
			head = (head + 1) % size;
			return val;
		}
	}

	public static void main(String[] args) throws IOException {
		int n;
		int d;
		int k;
		int i;
		int max;
		int num;
		int sushi;
		int[] cnt;
		int[] prefix;
		ArrayDeque dq;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		cnt = new int[d + 1];
		cnt[Integer.parseInt(st.nextToken())]++;
		num = 1;
		dq = new ArrayDeque(k);
		prefix = new int[--k];
		for (i = 0; i < k; i++) {
			prefix[i] = Integer.parseInt(br.readLine());
			dq.addLast(prefix[i]);
			if (cnt[prefix[i]]++ == 0) {
				num++;
			}
		}
		max = num;
		for (; i < n; i++) {
			dq.addLast(sushi = Integer.parseInt(br.readLine()));
			if (cnt[sushi]++ == 0) {
				max = Math.max(max, ++num);
			}
			if (--cnt[dq.pollFirst()] == 0) {
				num--;
			}
		}
		for (i = 0; i < k; i++) {
			if (cnt[prefix[i]]++ == 0) {
				max = Math.max(max, ++num);
			}
			if (--cnt[dq.pollFirst()] == 0) {
				num--;
			}
		}
		System.out.print(max);
	}
}
