import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, i;
		String str;
		Map<String, String> pokemon = new HashMap<>();
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		for (i = 1; i <= n; i++) {
			str = br.readLine();
			pokemon.put(str, Integer.toString(i));
			pokemon.put(Integer.toString(i), str);
		}
		for (i = 0; i < m; i++) {
			sb.append(pokemon.get(br.readLine())).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}