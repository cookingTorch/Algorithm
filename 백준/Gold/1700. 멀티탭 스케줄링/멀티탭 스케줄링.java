import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static final Tool EMPTY = new Tool(INF);
	
	private static final class Tool {
		int num, next;
		
		Tool() {
		};
		
		Tool(int next) {
			this.next = next;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, k, max, idx, cnt, i, j;
		boolean flag;
		Tool tool;
		Tool[] arr, plug;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new Tool[k];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < k; i++) {
			arr[i] = new Tool();
			arr[i].num = Integer.parseInt(st.nextToken());
		}
		for (i = 0; i < k; i++) {
			tool = arr[i];
			for (tool.next = i + 1; tool.next < k; tool.next++) {
				if (arr[tool.next].num == tool.num) {
					break;
				}
			}
		}
		flag = false;
		cnt = 0;
		idx = 0;
		plug = new Tool[n];
		Arrays.fill(plug, EMPTY);
		loop:
		for (i = 0; i < k; i++) {
			tool = arr[i];
			max = 0;
			for (j = 0; j < n; j++) {
				if (tool.num == plug[j].num) {
					plug[j].next = tool.next;
					continue loop;
				}
				if (max < plug[j].next) {
					max = plug[j].next;
					idx = j;
				}
			}
			plug[idx] = tool;
			if (flag) {
				cnt++;
			} else {
				for (j = 0; j < n; j++) {
					if (plug[j] == EMPTY) {
						continue loop;
					}
				}
				flag = true;
			}
		}
		System.out.print(cnt);
	}
}
