import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final String SCI_COM_LOVE = "SciComLove\n";
	
	public static void main(String[] args) throws IOException {
		int n;
		int i;
		StringBuilder sb;
		BufferedReader br;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		for (i = 0; i < n; i++) {
			sb.append(SCI_COM_LOVE);
		}
		System.out.print(sb);
	}
}
