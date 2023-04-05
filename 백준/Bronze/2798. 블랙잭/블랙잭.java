import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	private static void combi(ArrayList<Integer> list, int r, ArrayList<ArrayList<Integer>> result, int s, ArrayList<Integer> combination) {
		if (combination.size() == r) {
			result.add(new ArrayList<>(combination));
		}
		else {
			for (int i = s; i < list.size(); i++) {
				combination.add(list.get(i));
				combi(list, r, result, i + 1, combination);
				combination.remove(combination.size() - 1);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		StringTokenizer st;
		
		int n, m, i, j, sum, ans = 0;
		ArrayList<Integer> cards = new ArrayList<>(), combination = new ArrayList<>();
		ArrayList<ArrayList<Integer>> combiCards = new ArrayList<>();
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		for (i = 0; i < n; i++) {
			cards.add(Integer.parseInt(st.nextToken()));
		}
		
		combi(cards, 3, combiCards, 0, combination);
		
		for (i = 0; i < combiCards.size(); i++) {
			sum = 0;
			for (j = 0; j < 3; j++) {
				sum += combiCards.get(i).get(j);
			}
			if (sum > ans && sum <= m) {
				ans = sum;
			}
		}
		
		bw.write(Integer.toString(ans));
		
		bw.flush();
		bw.close();

	}

}