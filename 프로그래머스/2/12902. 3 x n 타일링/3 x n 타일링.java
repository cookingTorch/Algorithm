/**
 * 홀수 길이일 경우 경우의 수 0
 * dp[i]: i 칸까지의 경우의 수
 * 2 칸을 채우는 경우의 수가 3 가지
 * -> + 3 * dp[i - 2]
 * 2 칸으로 끊어지지 않는 모양이
 * 4 칸, 6 칸, 8 칸... 길이별로 두 가지씩 존재할 수 있음
 * -> + 2 * (dp[i - 4] + dp[i - 6] + ... + dp[0])
 * 점화식 전개
 *             dp[i] = 3 * dp[i - 2] + 2 * (dp[i - 4] + dp[i - 6] + ... + dp[0])
 *         dp[i - 2] = 3 * dp[i - 4] + 2 * (dp[i - 6] + dp[i - 8] + ... + dp[0])
 * dp[i] - dp[i - 2] = 3 * dp[i - 2] - 3 * dp[i - 4] + 2 * dp[i - 4]
 *             dp[i] = 4 * dp[i - 2] - dp[i - 4]
 * 짝수 길이만 사용하므로 반으로 줄이면 (i = i / 2)
 * dp[i] = 4 * dp[i - 1] - dp[i - 2]
 * 배열 대신 dp[i], dp[i - 1], dp[i - 2]를 세 변수에 저장하여 사용
 */
class Solution {
    private static final long MOD = 1_000_000_007L;

    public int solution(int n) {
        int i;
        int half;
        long cur;
        long prev1;
        long prev2;

        if ((n & 1) == 1) { // 홀수 길이 경우의 수 0
            return 0;
        }
        half = n >>> 1; // 짝수 길이만 사용
        prev2 = 1L; // dp[0]
        prev1 = 3L; // dp[1]
        for (i = 2; i <= half; i++) { // 각 길이별 경우의 수 계산
            cur = ((prev1 << 2) - prev2 + MOD) % MOD; // dp[i] = 4 * dp[i - 1] - dp[i - 2]
            prev2 = prev1; // dp[i - 2] 업데이트
            prev1 = cur; // dp[i - 1] 업데이트
        }
        return (int) prev1; // dp[half] 반환
    }
}
