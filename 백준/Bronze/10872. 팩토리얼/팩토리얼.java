import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

   public static void main(String[] args) throws IOException {
      
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      String str;
      
      long n, i, ans = 1;
      
      str = br.readLine();
      n = Long.parseLong(str);
      
      for (i = 2; i <= n; i++) {
         ans = ans * i;
      }
      
      bw.write(Long.toString(ans));
      
      bw.flush();
      bw.close();
      
   }

}