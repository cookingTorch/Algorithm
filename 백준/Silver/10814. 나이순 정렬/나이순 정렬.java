import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		StringTokenizer st;
		
		int n, i;
		ArrayList<Object[]> users = new ArrayList<>();
		Comparator<Object[]> comparator = new Comparator<Object[]>() {
			public int compare(Object[] o1, Object[] o2) {
				if (((Integer) o1[0]).compareTo((Integer) o2[0]) == 0) {
					return ((Integer) o1[1]).compareTo((Integer) o2[1]);
				}
				else {
					return ((Integer) o1[0]).compareTo((Integer) o2[0]);
				}
			}
		};
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		for (i = 0; i < n; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			users.add(new Object[] {Integer.parseInt(st.nextToken()), i, st.nextToken()});
		}
		
		Collections.sort(users, comparator);
		
		for (i = 0; i < n; i++) {
			bw.write(users.get(i)[0].toString() + " " + users.get(i)[2].toString() + "\n");
		}
		
		bw.flush();
		bw.close();
		
	}

}