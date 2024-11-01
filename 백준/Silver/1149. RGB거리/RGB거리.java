import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int n;
		int red;
		int green;
		int blue;
		int prevRed;
		int prevGreen;
		int prevBlue;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		for (prevRed = 0, prevGreen = 0, prevBlue = 0; n-- > 0; prevRed = red, prevGreen = green, prevBlue = blue) {
			st = new StringTokenizer(br.readLine(), " ", false);
			red = Math.min(prevGreen, prevBlue) + Integer.parseInt(st.nextToken());
			green = Math.min(prevBlue, prevRed) + Integer.parseInt(st.nextToken());
			blue = Math.min(prevRed, prevGreen) + Integer.parseInt(st.nextToken());
		}
		System.out.print(Math.min(Math.min(prevRed, prevGreen), prevBlue));
	}
}
