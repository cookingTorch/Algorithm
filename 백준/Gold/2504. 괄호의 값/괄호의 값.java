import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
	private static final int LEFT_2 = '(';
	private static final int RIGHT_2 = ')';
	private static final int LEFT_3 = '[';
	private static final int RIGHT_3 = ']';
	private static final int OPEN_2 = -1;
	private static final int OPEN_3 = -2;
	
	public static void main(String[] args) throws IOException {
		int ch;
		int num;
		int ans;
		int temp;
		BufferedReader br;
		ArrayDeque<Integer> dq;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		temp = 0;
		dq = new ArrayDeque<>();
		loop:
		while ((ch = br.read()) != -1) {
			switch (ch) {
			case LEFT_2:
				dq.addFirst(OPEN_2);
				break;
			case LEFT_3:
				dq.addFirst(OPEN_3);
				break;
			case RIGHT_2:
				while (!dq.isEmpty()) {
					num = dq.pollFirst();
					if (num == OPEN_2) {
						if (temp == 0) {
							dq.addFirst(2);
						} else {
							dq.addFirst(temp << 1);
							temp = 0;
						}
						continue loop;
					} else if (num == OPEN_3) {
						break;
					}
					temp += num;
				}
				System.out.print('0');
				return;
			case RIGHT_3:
				while (!dq.isEmpty()) {
					num = dq.pollFirst();
					if (num == OPEN_3) {
						if (temp == 0) {
							dq.addFirst(3);
						} else {
							dq.addFirst(temp * 3);
							temp = 0;
						}
						continue loop;
					} else if (num == OPEN_2) {
						break;
					}
					temp += num;
				}
				System.out.print('0');
				return;
			}
		}
		ans = 0;
		while (!dq.isEmpty()) {
			num = dq.pollFirst();
			if (num < 0) {
				System.out.print('0');
				return;
			}
			ans += num;
		}
		System.out.print(ans);
	}
}
