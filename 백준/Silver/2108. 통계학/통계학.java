import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		
		int n, i, avg, mid, cnt = 1, max = 1, most = 0, range, sum = 0;
		int[] nums;
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		nums = new int[n];
		
		for (i = 0; i < n; i++) {
			str = br.readLine();
			nums[i] = Integer.parseInt(str);
			sum += Integer.parseInt(str);
		}
		
		Arrays.sort(nums);
		
		avg = (int) Math.round(((double) sum) / n);
		mid = nums[n / 2];
		
		most = nums[0];
		for (i = 1; i < n; i++) {
			if (nums[i] == nums[i - 1]) {
				cnt++;
			}
			else {
				cnt = 1;
			}
			if (max < cnt) {
				max = cnt;
				most = nums[i];
			}
		}
		cnt = 1;
		for (i = 1; i < n; i++) {
			if (nums[i] == nums[i - 1]) {
				cnt++;
			}
			else {
				cnt = 1;
			}
			if (max == cnt && nums[i] != most) {
				most = nums[i];
				break;
			}
		}
		
		range = nums[n - 1] - nums[0];
		
		bw.write(Integer.toString(avg) + "\n");
		bw.write(Integer.toString(mid) + "\n");
		bw.write(Integer.toString(most) + "\n");
		bw.write(Integer.toString(range) + "\n");
		
		bw.flush();
		bw.close();
		
	}

}