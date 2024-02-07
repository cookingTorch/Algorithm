import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static class AbsPQ {
		private int size;
		private int[] arr;
		
		AbsPQ() {
			size = 0;
			arr = new int[100001];
		}
		
		void add(int val) {
			int idx, parentIdx, parentVal;
			
			idx = ++size;
			arr[idx] = val;
			while (idx > 1) {
				parentIdx = idx / 2;
				parentVal = arr[parentIdx];
				if (compare(val, parentVal) >= 0) {
					break;
				}
				arr[idx] = parentVal;
				idx = parentIdx;
			}
			arr[idx] = val;
		}
		
		int poll() {
			int val, last, parent, child, right;
			
			if (size == 0) {
				return 0;
			}
			val = arr[1];
			last = arr[size];
			arr[size--] = 0;
			parent = 1;
			while ((child = parent * 2) <= size) {
				right = parent * 2 + 1;
				if (right <= size && compare(arr[child], arr[right]) > 0) {
					child = right;
				}
				if (compare(last, arr[child]) <= 0) {
					break;
				}
				arr[parent] = arr[child];
				parent = child;
			}
			arr[parent] = last;
			return val;
		}
		
		private int compare(int a, int b) {
			if (Math.abs(a) == Math.abs(b)) {
				return a - b;
			}
			if (Math.abs(a) < Math.abs(b)) {
				return -1;
			}
			return 1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n, num, i;
		AbsPQ pq = new AbsPQ();
		
		n = Integer.parseInt(br.readLine());
		for (i = 0; i < n; i++) {
			if ((num = Integer.parseInt(br.readLine())) == 0) {
				sb.append(pq.poll()).append('\n');
			} else {
				pq.add(num);
			}
		}
		System.out.print(sb);
	}
}
