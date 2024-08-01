import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final char LINE_BREAK = '\n';
	private static final String END = "END";

	public static void main(String[] args) throws IOException {
		int len;
		String str;
		StringBuilder sb;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		for (str = br.readLine(); !str.equals(END); str = br.readLine()) {
			len = str.length();
			while (len-- > 0) {
				sb.append(str.charAt(len));
			}
			sb.append(LINE_BREAK);
		}
		System.out.print(sb);
	}
}
