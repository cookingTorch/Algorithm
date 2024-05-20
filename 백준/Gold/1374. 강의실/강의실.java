import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final Comparator<Lecture> cmp = new Comparator<Lecture>() {
		@Override
		public int compare(Lecture o1, Lecture o2) {
			return Integer.compare(o1.end, o2.end);
		}
	};
	
	private static final class Lecture implements Comparable<Lecture> {
		int start;
		int end;
		
		Lecture(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Lecture o) {
			return Integer.compare(this.start, o.start);
		}
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int cnt;
		Lecture lecture;
		BufferedReader br;
		StringTokenizer st;
		PriorityQueue<Lecture> pqEnd;
		PriorityQueue<Lecture> pqStart;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		pqStart = new PriorityQueue<>();
		n = Integer.parseInt(br.readLine());
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			pqStart.offer(new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		pqEnd = new PriorityQueue<>(cmp);
		pqEnd.offer(pqStart.poll());
		cnt = 1;
		while (!pqStart.isEmpty()) {
			lecture = pqStart.poll();
			if (lecture.start < pqEnd.peek().end) {
				cnt++;
			} else {
				pqEnd.poll();
			}
			pqEnd.offer(lecture);
		}
		System.out.print(cnt);
	}
}
