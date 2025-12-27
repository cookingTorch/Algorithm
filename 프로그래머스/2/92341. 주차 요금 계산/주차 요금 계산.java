class Solution {
	private static final int IN = 13;
	private static final int SIZE = 10_000;
	private static final int NUM = '0' * 1_111;
	private static final int TIME = '0' * 671;
	private static final int INIT = getTime("23:59");

	private static int getNum(String record) {
		return record.charAt(6) * 1_000 + record.charAt(7) * 100 + record.charAt(8) * 10 + record.charAt(9) - NUM;
	}

	private static int getTime(String record) {
		return record.charAt(0) * 600 + record.charAt(1) * 60 + record.charAt(3) * 10 + record.charAt(4) - TIME;
	}

	public int[] solution(int[] fees, String[] records) {
		int i;
		int len;
		int num;
		int cnt;
		int idx;
		int base;
		int unit;
		int baseFee;
		int unitFee;
		int[] ans;
		int[] sum;
		int[] last;
		String record;

		cnt = 0;
		sum = new int[SIZE];
		last = new int[SIZE];
		len = records.length;
		for (i = len - 1; i >= 0; i--) {
			record = records[i];
			num = getNum(record);
			if (record.length() == IN) {
				if (last[num] == 0) {
					cnt++;
					sum[num] += INIT - getTime(record);
				} else {
					sum[num] += last[num] - getTime(record);
				}
			} else {
				if (sum[num] == 0) {
					cnt++;
				}
				last[num] = getTime(record);
			}
		}
		base = fees[0];
		baseFee = fees[1];
		unit = fees[2];
		unitFee = fees[3];
		ans = new int[cnt];
		idx = 0;
		for (i = 0; i < SIZE; i++) {
			if (sum[i] != 0) {
				ans[idx++] = baseFee + (Math.max(0, sum[i] - base) + unit - 1) / unit * unitFee;
			}
		}
		return ans;
	}
}
