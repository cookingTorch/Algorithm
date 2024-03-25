import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		char[] str;
		Deque<Character> stack;
		
		str = br.readLine().toCharArray();
		stack = new ArrayDeque<>();
		for (char ch : str) {
			if ('A' <= ch && ch <= 'Z') {
				sb.append(ch);
			} else if (ch == '(') {
				stack.push(ch);
			} else if (ch == ')') {
				for (; (ch = stack.pop()) != '('; sb.append(ch));
			} else if (ch == '*' || ch == '/') {
				for (; !stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/'); sb.append(stack.pop()));
				stack.push(ch);
			} else {
				for (; !stack.isEmpty() && stack.peek() != '('; sb.append(stack.pop()));
				stack.push(ch);
			}
		}
		for (; !stack.isEmpty(); sb.append(stack.poll()));
		System.out.print(sb);
	}
}
