class Solution {
    public int solution(int storey) {
        int ans;
        int num;
        
        ans = 0;
        for (; storey != 0; storey /= 10) {
            if ((num = storey % 10) > 5 || (num == 5 && storey % 100 > 50)) {
                ans += 10 - num;
                storey += 10;
                continue;
            }
            ans += num;
        }
        return ans;
    }
}