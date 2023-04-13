import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	static ArrayList<ArrayList<Integer>> paper = new ArrayList<>();
	
	private static void rotate() {
		
		int i, j;
		ArrayList<ArrayList<Integer>> copied = new ArrayList<>();
		
		for (i = 0; i < paper.size(); i++) {
			copied.add(new ArrayList<Integer>());
			for (j = 0; j < paper.get(i).size(); j++) {
				copied.get(i).add(paper.get(i).get(j));
			}
		}
		
		paper.clear();
		
		for (i = 0; i < copied.get(0).size(); i++) {
			paper.add(new ArrayList<Integer>());
			for (j = 0; j < copied.size(); j++) {
				paper.get(i).add(copied.get(copied.size() - 1 - j).get(i));
			}
		}
		
		copied.clear();
		
	}
	
	private static void reverse() {
		
		int i;
		
		for (i = 0; i < paper.size(); i++) {
			Collections.reverse(paper.get(i));
		}
		
	}
	
	private static int getMax() {
		
		int i, j, n, m, sum = 0, max = 0;
		
		n = paper.size();
		m = paper.get(0).size();
		
		for (i = 0; i < n; i++) {
			for (j = 0; j < m - 3; j++) {
				sum = paper.get(i).get(j) + paper.get(i).get(j + 1) + paper.get(i).get(j + 2) + paper.get(i).get(j + 3);
				if (sum > max) {
					max = sum;
				}
			}
		}
		
		for (i = 0; i < n - 1; i++) {
			for (j = 0; j < m - 1; j++) {
				sum = paper.get(i).get(j) + paper.get(i).get(j + 1) + paper.get(i + 1).get(j) + paper.get(i + 1).get(j + 1);
				if (sum > max) {
					max = sum;
				}
			}
		}
		
		for (i = 0; i < n - 2; i++) {
			for (j = 0; j < m - 1; j++) {
				sum = paper.get(i).get(j) + paper.get(i + 1).get(j) + paper.get(i + 2).get(j) + paper.get(i + 2).get(j + 1);
				if (sum > max) {
					max = sum;
				}
			}
		}
		
		for (i = 0; i < n - 2; i++) {
			for (j = 0; j < m - 1; j++) {
				sum = paper.get(i).get(j) + paper.get(i + 1).get(j) + paper.get(i + 1).get(j + 1) + paper.get(i + 2).get(j + 1);
				if (sum > max) {
					max = sum;
				}
			}
		}
		
		for (i = 0; i < n - 1; i++) {
			for (j = 0; j < m - 2; j++) {
				sum = paper.get(i).get(j) + paper.get(i).get(j + 1) + paper.get(i).get(j + 2) + paper.get(i + 1).get(j + 1);
				if (sum > max) {
					max = sum;
				}
			}
		}
		
		return max;
		
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		StringTokenizer st;
		
		int n, m, i, j, temp, ans = 0;
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		for (i = 0; i < n; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			paper.add(new ArrayList<Integer>());
			for (j = 0; j < m; j++ ) {
				paper.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for (i = 0; i < 4; i++) {
			temp = getMax();
			ans = Math.max(ans, temp);
			reverse();
			temp = getMax();
			ans = Math.max(ans, temp);
			reverse();
			
			rotate();
		}
		
		bw.write(Integer.toString(ans));
		
		bw.flush();
		bw.close();

	}

}