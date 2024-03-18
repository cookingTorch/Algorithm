import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
	private static final int SIZE = 4;
	private static final String SONGDO = "SONGDO";
	private static final String CODE = "CODE";
	private static final String YEAR = "2023";
	private static final String ALGORITHM = "ALGORITHM";
	private static final String HIGHSCHOOL = "HIGHSCHOOL";
	private static final String MASTER = "MASTER";
	private static final String DATE = "0611";
	private static final String CONTEST = "CONTEST";
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int i;
		HashMap<String, String> map;
		
		map = new HashMap<>();
		for (i = 0; i < SIZE; i++) {
			map.put(SONGDO, HIGHSCHOOL);
			map.put(CODE, MASTER);
			map.put(YEAR, DATE);
			map.put(ALGORITHM, CONTEST);
		}
		System.out.print(map.get(br.readLine()));
	}
}
