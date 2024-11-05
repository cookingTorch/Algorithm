import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final char SPACE = ' ';

    public static void main(String[] args) throws IOException {
        int n;
        int max;
        int lenLeft;
        int lenRight;
        int prevLenLeft;
        int prevLenRight;
        char grade;
        char left;
        char right;
        char prevLeft;
        char prevRight;
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        prevLeft = prevRight = grade = SPACE;
        prevLenLeft = prevLenRight = max = 0;
        while (n-- > 0) {
            left = (char) br.read();
            br.read();
            right = (char) br.read();
            br.read();
            if (left == prevLeft) {
                lenLeft = prevLenLeft + 1;
            } else if (left == prevRight) {
                lenLeft = prevLenRight + 1;
            } else {
                lenLeft = 1;
            }
            if (right == prevRight) {
                lenRight = prevLenRight + 1;
            } else if (right == prevLeft) {
                lenRight = prevLenLeft + 1;
            } else {
                lenRight = 1;
            }
            if (lenLeft > max) {
                grade = left;
                max = lenLeft;
            } else if (lenLeft == max) {
                if (left < grade) {
                    grade = left;
                }
            }
            if (lenRight > max) {
                grade = right;
                max = lenRight;
            } else if (lenRight == max) {
                if (right < grade) {
                    grade = right;
                }
            }
            prevLeft = left;
            prevRight = right;
            prevLenLeft = lenLeft;
            prevLenRight = lenRight;
        }
        System.out.print(new StringBuilder().append(max).append(SPACE).append(grade).toString());
    }
}
