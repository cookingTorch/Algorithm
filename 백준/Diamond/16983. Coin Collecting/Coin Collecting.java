import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int n;
	private static int[] upCnt;
	private static int[] downCnt;
	
	private static final long abs(long x) {
		return x < 0 ? -x : x;
	}
	
	private static final int clampX(long x) {
		if (x < 1) {
			return 1;
		}
		if (x > n) {
			return n;
		}
		return (int) x;
	}
	
	private static final int clampY(long y) {
		if (y < 1) {
			return 1;
		}
		if (y > 2) {
			return 2;
		}
		return (int) y;
	}
	
	private static final long solve(long ret) {
		int i;
		long total;
		long up;
		long down;
		long move;
		
		total = 0;
		for (i = 1; i < n; i++) {
			total += upCnt[i] + downCnt[i];
			ret += abs(total - ((long) i << 1));
		}
		up = 0;
		down = 0;
		for (i = 1; i <= n; i++) {
			up += upCnt[i] - 1;
			down += downCnt[i] - 1;
			if (up > 0 && down < 0) {
				move = Math.min(up, -down);
				ret += move;
				up -= move;
				down += move;
			} else if (up < 0 && down > 0) {
				move = Math.min(-up, down);
				ret += move;
				up += move;
				down -= move;
			}
		}
		return ret;
	}
	
	public static void main(String[] args) throws IOException {
		int i;
		int x;
		int y;
		long ox;
		long oy;
		long ret;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		upCnt = new int[n + 1];
		downCnt = new int[n + 1];
		ret = 0;
		for (i = 0; i < n << 1; i++) {
			st = new StringTokenizer(br.readLine());
			ox = Long.parseLong(st.nextToken());
			oy = Long.parseLong(st.nextToken());
			x = clampX(ox);
			y = clampY(oy);
			ret += abs(ox - x) + abs(oy - y);
			if (y == 1) {
				upCnt[x]++;
			} else {
				downCnt[x]++;
			}
		}
		System.out.print(solve(ret));
	}
}