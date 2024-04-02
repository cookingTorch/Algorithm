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
		
		Item(int weight) {
			this.weight = weight;
		}
		
		void setValue(int value) {
			this.value = value;
			this.ratio = (double) value / weight;
		}
		
		@Override
		public int compareTo(Item o) {
			return Double.compare(o.ratio, this.ratio);
		}
	}
	
	private static int n, m, maxProfit;
	private static Item[] items;
	
	private static boolean promising(int depth, int weight, int profit) {
		int totalWeight, bound, i;
		
		if (weight >= m) {
			return false;
		}
		bound = profit;
		totalWeight = weight;
		for (i = depth + 1; i < n && totalWeight + items[i].weight <= m; i++) {
			bound += items[i].value;
			totalWeight += items[i].weight;
		}
		if (i < n) {
			bound += (m - totalWeight) * items[i].ratio;
		}
		return bound > maxProfit;
	}
	
	private static void knapsack(int depth, int weight, int profit) {
		if (weight <= m && profit > maxProfit) {
			maxProfit = profit;
		}
		if (promising(depth, weight, profit)) {
			knapsack(depth + 1, weight + items[depth + 1].weight, profit + items[depth + 1].value);
			knapsack(depth + 1, weight, profit);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int weight, value, weightSum, valueSum, i;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		items = new Item[n];
		weightSum = 0;
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			weight = Integer.parseInt(st.nextToken());
			items[i] = new Item(weight);
			weightSum += weight;
		}
		valueSum = 0;
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			value = Integer.parseInt(st.nextToken());
			items[i].setValue(value);
			valueSum += value;
		}
		m = weightSum - m;
		Arrays.sort(items);
		maxProfit = 0;
		knapsack(-1, 0, 0);
		System.out.print(valueSum - maxProfit);
	}
}
