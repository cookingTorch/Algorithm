import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int i;
		char[] list;
		String str;
		
		str = br.readLine();
		list = str.toCharArray();
		Arrays.sort(list);
		for (i = list.length - 1; i >= 0; i--) {
			sb.append(list[i]);
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}