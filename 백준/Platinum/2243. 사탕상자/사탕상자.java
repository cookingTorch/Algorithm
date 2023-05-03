import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int[] segmentTree = new int[4000001];
	
	// 트리 업데이트
	private static void updateTree(int start, int end, int idx, int tastiness, int num) {
		if (tastiness >= start && tastiness <= end) {
			segmentTree[idx] += num;
			if (end > start) {
				int mid = (start + end) / 2;
				updateTree(start, mid, idx * 2, tastiness, num);
				updateTree(mid + 1, end, idx * 2 + 1, tastiness, num);
			}
		}
	}
	
	// 1 ~ temp 개수 합
	private static int sum(int start, int end, int idx, int temp) {
		if (end <= temp) {
			return segmentTree[idx];
		}
		else if (start <= temp) {
			int mid = (start + end) / 2;
			return sum(start, mid, idx * 2, temp) + sum(mid + 1, end, idx * 2 + 1, temp);
		}
		else {
			return 0;
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int n, i, tastiness, num, rank, left, right, mid;
		
		// n 입력
		str = br.readLine();
		n = Integer.parseInt(str);
		
		// 쿼리 입력
		for (i = 0; i < n; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			
			// 사탕 넣기
			if (st.nextToken().equals("2")) {
				tastiness = Integer.parseInt(st.nextToken());
				num = Integer.parseInt(st.nextToken());
				updateTree(1, 1000000, 1, tastiness, num);
			}
			
			// 사탕 꺼내기
			else {
				rank = Integer.parseInt(st.nextToken());
				left = 0;
				right = 1000001;
				while (left < right) {
					mid = (left + right) / 2;
					if (sum(1, 1000000, 1, mid) < rank) {
						left = mid + 1;
					}
					else {
						right = mid;
					}
				}
				sb.append(right).append("\n");
				updateTree(1, 1000000, 1, right, -1);
			}
		}
		
		// 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

}