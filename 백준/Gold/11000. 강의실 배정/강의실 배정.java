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
		int[] tempRooms = new int[2], tempTime = new int[2];
		PriorityQueue<int[]> time = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        });
		PriorityQueue<int[]> rooms = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[1], b[1]);
            }
        });
				
		str = br.readLine();
		n = Integer.parseInt(str);
		
		for (i = 0; i < n; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			time.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
		
		for (i = 0; i < n; i++) {
			tempTime = Arrays.copyOf(time.poll(), 2);
			if (i > 0) {
				tempRooms = Arrays.copyOf(rooms.peek(), 2);
				if (tempRooms[1] <= tempTime[0]) {
					rooms.remove();
				}
			}
			rooms.add(tempTime);
		}
		
		bw.write(Integer.toString(rooms.size()));

		bw.flush();
		bw.close();
		
	}

}