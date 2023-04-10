import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

   public static void main(String[] args) throws IOException {
      
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      String str;
      StringTokenizer st;
      
      long a, b, ans;
      
      str = br.readLine();
      st = new StringTokenizer(str, " ");
      
      a = Long.parseLong(st.nextToken());
      b = Long.parseLong(st.nextToken());
      
      ans = (a + b) * (a - b);
      
      bw.write(Long.toString(ans));

      bw.flush();
      bw.close();
      
   }

}