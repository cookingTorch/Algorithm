import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

class Solution {
	private static final int IN = 2;
	private static final int DIFF = '0';

	private static final class Car implements Comparable<Car> {
		private static final int NIL = -1;
		private static final int MAX = 23 * 60 + 59;

		private static int time1;
		private static int fee1;
		private static int time2;
		private static int fee2;

		int in;
		int num;
		int time;

		Car(int num, int in) {
			this.time = 0;
			this.in = in;
			this.num = num;
		}

		void in(int in) {
			this.in = in;
		}

		void out(int out) {
			this.time += out - in;
			in = NIL;
		}

		int calc() {
			if (in != NIL) {
				time += MAX - in;
			}
			if (time <= time1) {
				return fee1;
			}
			return fee1 + ((time - time1 - 1) / time2 + 1) * fee2;
		}

		@Override
		public int compareTo(Car o) {
			return num - o.num;
		}
	}

	private static int toMin(String time) {
		return ((time.charAt(0) - DIFF) * 10 + time.charAt(1) - DIFF) * 60 + (time.charAt(3) - DIFF) * 10 + time.charAt(4) - DIFF;
	}

	public int[] solution(int[] fees, String[] records) {
		int i;
		int len;
		int num;
		int time;
		int size;
		int inOut;
		int[] ans;
		Car[] arr;
		HashMap<Integer, Car> map;
		StringTokenizer st;

		Car.time1 = fees[0];
		Car.fee1 = fees[1];
		Car.time2 = fees[2];
		Car.fee2 = fees[3];
		map = new HashMap<>();
		len = records.length;
		for (i = 0; i < len; i++) {
			st = new StringTokenizer(records[i]);
			time = toMin(st.nextToken());
			num = Integer.parseInt(st.nextToken());
			inOut = st.nextToken().length();
			if (inOut == IN) {
				if (map.containsKey(num)) {
					map.get(num).in(time);
				} else {
					map.put(num, new Car(num, time));
				}
			} else {
				map.get(num).out(time);
			}
		}
		size = map.size();
		arr = new Car[size];
		i = 0;
		for (Car car : map.values()) {
			arr[i++] = car;
		}
		Arrays.sort(arr);
		ans = new int[size];
		for (i = 0; i < size; i++) {
			ans[i] = arr[i].calc();
		}
		return ans;
	}
}