import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		
		int n, i, tempNum, tempStack, flag;
		ArrayList<String> ans = new ArrayList<>();
		ArrayList<Integer> nums = new ArrayList<>();
		PriorityQueue<Integer> orderedNums = new PriorityQueue<>();
		Stack<Integer> box = new Stack<>();
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		for (i = 0; i < n; i++) {
			str = br.readLine();
			nums.add(Integer.parseInt(str));
		}
		
		for (i = 0; i < n; i++) {
			orderedNums.add(nums.get(i));
		}
		
		flag = 1;
		for (i = 0; i < n; i++) {
			tempNum = nums.remove(0);
			if (orderedNums.size() > 0 && orderedNums.peek() <= tempNum) {
				do {
					tempStack = orderedNums.poll();
					box.add(tempStack);
					ans.add("+");
				} while (tempStack < tempNum);
				box.pop();
				ans.add("-");
			}
			else if (tempNum == box.peek()) {
				box.pop();
				ans.add("-");
			}
			else {
				bw.write("NO");
				flag = 0;
				break;
			}
		}
		
		if (flag == 1) {
			for (i = 0; i < ans.size(); i++) {
				bw.write(ans.get(i));
				bw.newLine();
			}
		}
		
		bw.flush();
		bw.close();

	}

}