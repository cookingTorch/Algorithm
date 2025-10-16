/**
 * knapsack
 * 가치: 맞출 점수, 어피치가 이미 획득했으면 두 배
 * 무게: 어피치보다 한 발 더
 * 용량: n
 * dp[i]: i 발로 얻게되는 라이언의 최대 이익
 * 각 dp값에 대한 화살 결과 배열들을 같이 저장
 * 길이 11의 배열 대신 44비트 숫자에 점수별로 4비트씩 나눠 저장
 * 가장 큰 비트에 가장 낮은 점수를 저장
 * dp값 동일할 시 큰 값으로만 업데이트
 */
class Solution {
    private static final int MAX = 10;
    private static final int SHIFT = 2;
    private static final int SIZE = 1 << SHIFT;
    private static final int FAIL = -1;
    private static final long UNIT = (1L << SIZE) - 1L;
    
    public int[] solution(int n, int[] info) {
        int i;
        int j;
        int sum;
        int idx;
        int score;
        int value;
        int weight;
        int[] dp;
        int[] ans;
        long[] res;
        
        dp = new int[n + 1]; // dp[i]: i 발로 얻게되는 라이언의 최대 이익
        res = new long[n + 1]; // 각 dp 값에 대한 결과 배열을 44비트에 점수별로 4비트씩 저장
        res[1] = 1L << (MAX << SHIFT); // 0점에 한 발 맞춤
        for (i = 2; i <= n; i++) { // 0점만 맞춘 상태들로 초기화
            res[i] = res[i - 1] + res[1]; // 0점에 i 발 맞춤
        }
        sum = 0; // 어피치 점수 합
        for (i = 0; i < MAX; i++) { // 10점부터 1점까지 순회
            idx = i << SHIFT; // 해당 점수의 44비트 안에서의 위치
            value = MAX - i; // 현재 점수의 가치
            if (info[i] > 0) { // 어피치가 이미 획득한 점수
                sum += value; // 어피치 점수 합
                value <<= 1; // 점수 획득했을 때 어피치의 점수가 사라지므로 가치 두 배
            }
            weight = info[i] + 1; // 어피치보다 한 발 더 소모해야 점수 획득
            for (j = n; j >= weight; j--) { // n 발 상태부터 역으로 순회
                score = dp[j - weight] + value; // 현재 점수 획득 시 dp[j]
                if (dp[j] < score) { // 기존 dp[j] 보다 크면
                    dp[j] = score; // dp[j] 업데이트
                    res[j] = res[j - weight] | (long) weight << idx; // 결과 배열 res[j] 업데이트
                } else if (dp[j] == score) { // 기존 dp[j] 값과 같으면
                    res[j] = Math.max(res[j], res[j - weight] | (long) weight << idx); // res[j] 기존 값과 비교하여 큰 쪽(낮은 점수가 많은 쪽)으로 업데이트
                }
            }
        }
        if (dp[n] <= sum) { // n발로 획득한 최대 이익이 어피치 점수 합보다 크지 않으면
            return new int[] {FAIL}; // 승리 불가
        }
        ans = new int[MAX + 1]; // 결과 배열
        for (i = 0; i <= MAX; i++) {
            ans[i] = (int) (res[n] & UNIT); // 가장 아래 4비트를 결과 배열에 저장
            res[n] >>= SIZE; // 4비트씩 줄이면서 순회
        }
        return ans; // 결과 배열 반환
    }
}