import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	private static final String FRULA = "FRULA";
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n, m, i, j;
		char letter;
		char[] str, part;
		String ans;
		Deque<Character> left, right, temp;
		
		str = br.readLine().toCharArray();
		n = str.length;
		left = new ArrayDeque<>();
		right = new ArrayDeque<>();
		for (i = 0; i < n; i++) {
			right.addLast(str[i]);
		}
		part = br.readLine().toCharArray();
		m = part.length;
		temp = new ArrayDeque<>();
		loop:
		while (!right.isEmpty()) {
			for (j = 0; j < m; j++) {
				if (right.isEmpty()) {
					while (!temp.isEmpty()) {
						left.addLast(temp.poll());
					}
					break loop;
				}
				letter = right.pollFirst();
				temp.addLast(letter);
				if (letter != part[j]) {
					if (!temp.isEmpty()) {
						left.addLast(temp.pollFirst());
					}
					while (!temp.isEmpty()) {
						right.addFirst(temp.pollLast());
					}
					continue loop;
				}
			}
			temp.clear();
			for (j = 0; j < m && !left.isEmpty(); j++) {
				right.addFirst(left.pollLast());
			}
		}
		while (!left.isEmpty()) {
			sb.append(left.pollFirst());
		}
		ans = sb.toString();
		if (ans.length() == 0) {
			System.out.print(FRULA);
			return;
		}
		System.out.print(ans);
	}
}
