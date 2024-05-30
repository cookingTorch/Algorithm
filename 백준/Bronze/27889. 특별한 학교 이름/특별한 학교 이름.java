import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(System.in));
        switch(br.readLine()) {
            case "NLCS" :
                System.out.print("North London Collegiate School");
                return;
            case "BHA" :
                System.out.print("Branksome Hall Asia");
                return;
            case "KIS" :
                System.out.print("Korea International School");
                return;
            case "SJA" :
                System.out.print("St. Johnsbury Academy");
        }
    }
}