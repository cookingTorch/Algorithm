import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, height, temp, idx, i;
		int[] tower;
		boolean flag;
		Stack<int[]> towers;
		
		n = Integer.parseInt(br.readLine());
		towers = new Stack<>();
		idx = 0;
		tower = new int[2];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			height = Integer.parseInt(st.nextToken());
			temp = -1;
			flag = true;
			while (temp < height) {
				if (towers.isEmpty()) {
					sb.append("0 ");
					flag = false;
					break;
				}
				tower = towers.pop();
				temp = tower[0];
			}
			if (flag) {
				sb.append(tower[1]).append(' ');
				towers.add(tower);
			}
			towers.add(new int[] {height, ++idx});
		}
		System.out.print(sb);
	}
}
