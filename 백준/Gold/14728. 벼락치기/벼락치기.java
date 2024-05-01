import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static final class Item implements Comparable<Item> {
		int weight;
		int value;
		double ratio;
		
		Item(int weight, int value) {
			this.weight = weight;
			this.value = value;
			this.ratio = (double) value / weight;
		}
		
		@Override
		public int compareTo(Item o) {
			return Double.compare(o.ratio, this.ratio);
		}
	}
	
	private static int n;
	private static int t;
	private static int maxProfit;
	private static Item[] items;
	
	private static boolean promising(int depth, int weight, int profit) {
		int i;
		int bound;
		int totalWeight;
		
		if (weight >= t) {
			return false;
		}
		bound = profit;
		totalWeight = weight;
		for (i = depth + 1; i < n && totalWeight + items[i].weight <= t; i++) {
			bound += items[i].value;
			totalWeight += items[i].weight;
		}
		if (i < n) {
			bound += (t - totalWeight) * items[i].ratio;
		}
		return bound > maxProfit;
	}
	
	private static void knapsack(int depth, int weight, int profit) {
		if (weight <= t && profit > maxProfit) {
			maxProfit = profit;
		}
		if (promising(depth, weight, profit)) {
			knapsack(depth + 1, weight + items[depth + 1].weight, profit + items[depth + 1].value);
			knapsack(depth + 1, weight, profit);
		}
	}
	
	public static void main(String[] args) throws IOException {
		int i;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		items = new Item[n];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			items[i] = new Item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(items);
		maxProfit = 0;
		knapsack(-1, 0, 0);
		System.out.print(maxProfit);
	}
}
