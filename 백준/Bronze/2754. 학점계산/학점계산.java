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
      
      double score = 0;
      
      str = br.readLine();
      
      if (Character.toString(str.charAt(0)).equals("A")) {
         score = 4.0;
      }
      else if (Character.toString(str.charAt(0)).equals("B")) {
         score = 3.0;
      }
      else if (Character.toString(str.charAt(0)).equals("C")) {
         score = 2.0;
      }
      else if (Character.toString(str.charAt(0)).equals("D")) {
         score = 1.0;
      }
      else if (Character.toString(str.charAt(0)).equals("F")) {
         score = 0.0;
      }
      
      if (Character.toString(str.charAt(0)).equals("F") == false) {
         if (Character.toString(str.charAt(1)).equals("+")) {
            score += 0.3;
         }
         else if (Character.toString(str.charAt(1)).equals("-")) {
            score -= 0.3;
         }
      }
      
      bw.write(Double.toString(score));
      
      bw.flush();
      bw.close();

   }

}