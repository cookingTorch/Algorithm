class Solution {
    private static int getGcd(int a, int b) {
        int r;
        
        while (b != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
    
    public long solution(int w, int h) {
        return (long) w * h - (w + h - getGcd(w, h));
    }
}
