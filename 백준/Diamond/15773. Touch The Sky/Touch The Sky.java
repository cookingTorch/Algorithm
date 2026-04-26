import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static final String DELIM = " ";

	private static int heapSize;
	private static int[] heap;
	private static int[] capacity;
	private static int[] tempCapacity;
	private static long[] deadline;
	private static long[] tempDeadline;

	private static final void mergeSort(int left, int right) {
		int mid;

		if (left >= right) {
			return;
		}
		mid = (left + right) >> 1;
		mergeSort(left, mid);
		mergeSort(mid + 1, right);
		merge(left, mid, right);
	}

	private static final void merge(int left, int mid, int right) {
		int i;
		int j;
		int k;

		i = left;
		j = mid + 1;
		k = left;
		while (i <= mid && j <= right) {
			if (deadline[i] <= deadline[j]) {
				tempDeadline[k] = deadline[i];
				tempCapacity[k++] = capacity[i++];
			} else {
				tempDeadline[k] = deadline[j];
				tempCapacity[k++] = capacity[j++];
			}
		}
		while (i <= mid) {
			tempDeadline[k] = deadline[i];
			tempCapacity[k++] = capacity[i++];
		}
		while (j <= right) {
			tempDeadline[k] = deadline[j];
			tempCapacity[k++] = capacity[j++];
		}
		for (i = left; i <= right; i++) {
			deadline[i] = tempDeadline[i];
			capacity[i] = tempCapacity[i];
		}
	}

	private static final void push(int value) {
		int idx;
		int parent;
		int temp;

		heap[++heapSize] = value;
		idx = heapSize;
		while (idx > 1) {
			parent = idx >> 1;
			if (heap[parent] >= heap[idx]) {
				break;
			}
			temp = heap[parent];
			heap[parent] = heap[idx];
			heap[idx] = temp;
			idx = parent;
		}
	}

	private static final int poll() {
		int ret;
		int idx;
		int child;
		int temp;

		ret = heap[1];
		heap[1] = heap[heapSize--];
		idx = 1;
		while ((idx << 1) <= heapSize) {
			child = idx << 1;
			if (child + 1 <= heapSize && heap[child] < heap[child + 1]) {
				child++;
			}
			if (heap[idx] >= heap[child]) {
				break;
			}
			temp = heap[idx];
			heap[idx] = heap[child];
			heap[child] = temp;
			idx = child;
		}
		return ret;
	}

	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int d;
		long l;
		long total;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		heap = new int[n + 1];
		capacity = new int[n];
		tempCapacity = new int[n];
		deadline = new long[n];
		tempDeadline = new long[n];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), DELIM, false);
			l = Long.parseLong(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			capacity[i] = d;
			deadline[i] = l + d;
		}
		mergeSort(0, n - 1);
		total = 0L;
		heapSize = 0;
		for (i = 0; i < n; i++) {
			push(capacity[i]);
			total += capacity[i];
			if (total > deadline[i]) {
				total -= poll();
			}
		}
		System.out.println(heapSize);
	}
}