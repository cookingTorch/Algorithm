import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		double w;
		double h;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		w = Double.parseDouble(st.nextToken());
		h = Double.parseDouble(st.nextToken());
		System.out.printf("%.1f", w * h / 2.0);
	}
}
