import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		StringTokenizer st;
		
		int n, k, l, i, appleX, appleY, changeNumber, live, time, size, flag;
		int[] head = new int[2], temp = new int[2];
		String way, turn;
		Deque<int[]> snake = new LinkedList<>();
		Deque<int[]> apple = new LinkedList<>();
		Deque<Integer> changeTime = new LinkedList<>();
		Deque<String> changeWay = new LinkedList<>();
		
		str = br.readLine();
		n = Integer.parseInt(str);
		str = br.readLine();
		k = Integer.parseInt(str);
		
		for (i = 0; i < k; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			appleX = Integer.parseInt(st.nextToken());
			appleY = Integer.parseInt(st.nextToken());
			apple.add(new int[] {appleX, appleY});
		}
		
		str = br.readLine();
		l = Integer.parseInt(str);
		
		for (i = 0; i < l; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			changeNumber = Integer.parseInt(st.nextToken());
			way = st.nextToken();
			changeTime.addLast(changeNumber);
			changeWay.addLast(way);
		}
		
		way = "right";
		live = 1;
		time = 0;
		snake.add(new int[] {1, 1});
		while (live == 1) {
			
			time++;
			
			head = Arrays.copyOf(snake.getFirst(), 2);
			
			if (way.equals("right")) {
				head[1]++;
			}
			else if (way.equals("left")) {
				head[1]--;
			}
			else if (way.equals("up")) {
				head[0]--;
			}
			else if (way.equals("down")) {
				head[0]++;
			}
			
			if (head[0] < 1 || head[1] < 1 || head[0] > n || head[1] > n) {
				live = 0;
				break;
			}
			
			size = snake.size();
			flag = 0;
			for (i = 0; i < size; i++) {
				temp = snake.pollFirst();
				if (Arrays.equals(head, temp)) {
					flag = 1;
					break;
				}
				else {
					snake.addLast(temp);
				}
			}
			if (flag == 1) {
				live = 0;
				break;
			}
			
			snake.addFirst(head);
			size = apple.size();
			flag = 0;
			for (i = 0; i < size; i++) {
				temp = apple.pollFirst();
				if (Arrays.equals(head, temp)) {
					flag = 1;
				}
				else {
					apple.addLast(temp);
				}
			}
			if (flag == 0) {
				snake.removeLast();
			}
			
			if (changeTime.size() > 0 && time == changeTime.getFirst()) {
				turn = changeWay.pollFirst();
				if (way.equals("right")) {
					if (turn.equals("L")) {
						way = "up";
					}
					else {
						way = "down";
					}
				}
				else if (way.equals("left")) {
					if (turn.equals("L")) {
						way = "down";
					}
					else {
						way = "up";
					}
				}
				else if (way.equals("up")) {
					if (turn.equals("L")) {
						way = "left";
					}
					else {
						way = "right";
					}
				}
				else if (way.equals("down")) {
					if (turn.equals("L")) {
						way = "right";
					}
					else {
						way = "left";
					}
				}
				changeTime.removeFirst();
			}
			
		}
		
		bw.write(Integer.toString(time));
		
		bw.flush();
		bw.close();

	}

}