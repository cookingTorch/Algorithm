import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
	
	private static int len;
	private static int[] rank, nextRank;
	private static String str;
	
	// 정렬
	private static Comparator<Integer> cmp = new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			if (rank[o1] == rank[o2]) {
				return nextRank[o1] - nextRank[o2];
			}
			return rank[o1] - rank[o2];
		}
	};
	
	// LCP 계산
	private static int commonPrefix(int index1, int index2, int prev) {
		int size, lcp, i;
		size = Math.min(len - index1, len - index2);
		lcp = ((prev == 0) ? 0 : (prev - 1));
		for (i = lcp; i < size; i++) {
			if (str.charAt(index1 + i) == str.charAt(index2 + i)) {
				lcp++;
			} else {
				break;
			}
		}
		return lcp;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int next, temp, i;
		int[] tRank, index, lcp;
		Integer[] sa;
		
		str = br.readLine();
		len = str.length();
		sa = new Integer[len];
		rank = new int[len];
		nextRank = new int[len];
		
		// 첫 번째 문자 기준 정렬
		for (i = 0; i < len; i++) {
			sa[i] = i;
			rank[i] = str.charAt(i) - 'a';
		}
		Arrays.sort(sa, cmp);
		
		// 2, 4, 8... 개 문자 기준 정렬
		tRank = new int[len];
		for (next = 1; next <= len; next *= 2) {
			tRank[sa[0]] = 0;
			for (i = 1; i < len; i++) {
				tRank[sa[i]] = tRank[sa[i - 1]];
				if (rank[sa[i]] != rank[sa[i - 1]] || nextRank[sa[i]] != nextRank[sa[i - 1]]) {
					tRank[sa[i]]++;
				}
			}
			rank = Arrays.copyOf(tRank, len);
			for (i = 0; i < len; i++) {
				if (sa[i] + next >= len) {
					nextRank[sa[i]] = -1;
				}
				else {
					nextRank[sa[i]] = rank[sa[i] + next];
				}
			}
			Arrays.sort(sa, cmp);
		}
		
		// Kasai's 알고리즘
		index = new int[len];
		for (i = 0; i < len; i++) {
			index[sa[i]] = i;
		}
		lcp = new int[len];
		temp = 0;
		for (i = 0; i < len; i++) {
			if (index[i] != 0) {
				temp = commonPrefix(i, sa[index[i] - 1], temp);
				lcp[index[i]] = temp;
			}
		}
		
		// 출력
		for (i = 0; i < len; i++) {
			sb.append(sa[i] + 1).append(" ");
		}
		sb.append("\nx ");
		for (i = 1; i < len; i++) {
			sb.append(lcp[i]).append(" ");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}