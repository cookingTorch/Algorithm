import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		StringTokenizer st;
		
		int wireNum, i, left, right, mid, cnt;
		int[] ansIndex;
		int[][] wire;
		ArrayList<int[]> remainder = new ArrayList<>();
		
		str = br.readLine();
		wireNum = Integer.parseInt(str);
		
		wire = new int[wireNum][2];
		ansIndex = new int[wireNum];
		
		for (i = 0; i < wireNum; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			wire[i][0] = Integer.parseInt(st.nextToken());
			wire[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(wire, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				int diff = o1[0] - o2[0];
				if (diff != 0) {
					return diff;
				}
				return o1[1] - o2[1];
			}
		} );;
		
		for (i = 0; i < wireNum; i++) {
			
			left = 0;
			right = remainder.size();
			
			while (left < right) {
				mid = (left + right) / 2;
				if (remainder.get(mid)[1] < wire[i][1]) {
					left = mid + 1;
				}
				else {
					right = mid;
				}
			}
			
			if (remainder.size() <= right) {
				remainder.add(new int[2]);
			}
			remainder.set(right, new int[] {wire[i][0], wire[i][1]});
			ansIndex[i] = right;
			
		}
		
		cnt = remainder.size() - 1;
		for (i = wireNum - 1; i >= 0; i--) {
			if (ansIndex[i] == cnt) {
				ansIndex[i] = -1;
				cnt--;
			}
		}
		
		bw.write(Integer.toString(wireNum - remainder.size()));
		for (i = 0; i < wireNum; i++) {
			if (ansIndex[i] >= 0) {
				bw.newLine();
				bw.write(Integer.toString(wire[i][0]));
			}
		}
		
		bw.flush();
		bw.close();

	}

}