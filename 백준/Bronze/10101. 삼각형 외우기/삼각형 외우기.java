import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int a, b, c;
        
        a = Integer.parseInt(br.readLine());
        b = Integer.parseInt(br.readLine());
        c = Integer.parseInt(br.readLine());
        
        if (a + b + c != 180) {
            sb.append("Error");
        } else if (a == b && b == c) {
            sb.append("Equilateral");
        } else if (a == b || b == c || c == a) {
            sb.append("Isosceles");
        } else {
            sb.append("Scalene");
        }
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}