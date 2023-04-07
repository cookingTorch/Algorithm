import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		StringTokenizer st;
		
		int n, i;
		int[] temp = new int[2];
		PriorityQueue<int[]> coordinate = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
            public int compare(int[] o1, int[] o2) {
				if (Integer.compare(o1[0], o2[0]) == 0) {
					return Integer.compare(o1[1], o2[1]);
				}
				else {
					return Integer.compare(o1[0], o2[0]);
				}
            }
        });
		
		str = br.readLine();
		n = Integer.parseInt(str);
		for (i = 0; i < n; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			coordinate.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
		for (i = 0; i < n; i++) {
			temp = Arrays.copyOf(coordinate.poll(), 2);
			bw.write(Integer.toString(temp[0]) + " " + Integer.toString(temp[1]));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();

	}

}