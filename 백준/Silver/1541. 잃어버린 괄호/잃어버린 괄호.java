import java.io.*;
import java.util.ArrayList;

public class Main {
    private static int sum(String str) {
        int len, temp, size, ans, i;
        ArrayList<String> arr = new ArrayList<>();

        len = str.length();
        temp = 0;
        for (i = 0; i < len; i++) {
            if (i == len - 1)
                arr.add(str.substring(temp, len));
            else if (str.charAt(i) == '+') {
                arr.add(str.substring(temp, i));
                temp = i + 1;
            }
        }
        ans = 0;
        size = arr.size();
        for (i = 0; i < size; i++)
            ans += Integer.parseInt(arr.get(i));
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int len, temp, size, ans, i;
        String str;
        ArrayList<String> arr = new ArrayList<>();

        str = br.readLine();
        len = str.length();
        temp = 0;
        for (i = 0; i < len; i++) {
            if (i == len - 1)
                arr.add(str.substring(temp, len));
            else if (str.charAt(i) == '-') {
                arr.add(str.substring(temp, i));
                temp = i + 1;
            }
        }
        ans = sum(arr.get(0));
        size = arr.size();
        for (i = 1; i < size; i++)
            ans -= sum(arr.get(i));
        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}