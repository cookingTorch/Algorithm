import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		StringTokenizer st;
		
		int t, n, m, i, j, num, sizePrinter, sizeTemp, cnt;
		Deque<Integer> printer = new LinkedList<>();
		Deque<Integer> temp = new LinkedList<>();
		
		str = br.readLine();
		t = Integer.parseInt(str);
		
		for (j = 0; j < t; j++) {
			
			printer.clear();
			
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			for (i = 0; i < n; i++) {
				printer.addLast(Integer.parseInt(st.nextToken()));
			}
			
			while (m >= 0) {
				sizePrinter = printer.size();
				num = printer.getFirst();
				
				for (i = 0; i < sizePrinter; i++) {
					if (printer.getFirst() > num) {
						break;
					}
					temp.addLast(printer.pollFirst());
				}
				cnt = i;
				
				sizeTemp = temp.size();
				for (i = 0; i < sizeTemp; i++) {
					printer.addFirst(temp.pollLast());
				}
				
				if (cnt < sizePrinter) {
					printer.removeFirst();
					printer.addLast(num);
					if (m > 0) {
						m--;
					}
					else {
						m = sizePrinter - 1;
					}
				}
				else {
					printer.removeFirst();
					m--;
				}
			}
			
			bw.write(Integer.toString(n - printer.size()) + "\n");
			
		}
		
		bw.flush();
		bw.close();
	}

}