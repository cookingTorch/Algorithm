import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static ArrayList<ArrayList<Integer>> fishTank = new ArrayList<>();
	
	private static void addFish(int fishTankLength, int minFish) {
		
		int i;
		
		for (i = 0; i < fishTankLength; i++) {
			if (fishTank.get(0).get(i) == minFish) {
				fishTank.get(0).set(i, minFish + 1);
			}
		}
		
	}
	
	private static void firstLevitation(int fishTankLength) {
		
		int n, m, remainderSize, i, j, k;
		ArrayList<ArrayList<Integer>> levitatedTank = new ArrayList<>();
		ArrayList<Integer> remainder = new ArrayList<>();
		remainderSize = fishTankLength;
		i = 0;
		
		while (remainderSize > 0) {
			
			n = (i + 3) / 2;
			m = (i + 2) / 2;
			remainderSize -= m;
			
			if (remainderSize < n) {
				break;
			}
			
			for (j = 0; j < n; j++) {
				levitatedTank.add(new ArrayList<>());
				for (k = 0; k < m; k++) {
					levitatedTank.get(j).add(fishTank.get(j).get(k));
				}
			}
			
			for (j = 0; j < remainderSize; j++) {
				remainder.add(fishTank.get(0).get(m + j));
			}
			
			fishTank.clear();
			
			for (j = 0; j < m + 1; j++) {
				fishTank.add(new ArrayList<>());
			}
			
			for (j = 1; j < m + 1; j++) {
				for (k = 0; k < n; k++) {
					fishTank.get(j).add(levitatedTank.get(k).get(m - j));
				}
			}
			
			for (j = 0; j < remainderSize; j++) {
				fishTank.get(0).add(remainder.get(j));
			}
			
			levitatedTank.clear();
			remainder.clear();
			
			i++;
			
		}
		
	}
	
	private static void populationControl() {
		
		int i, j, fish, movingFish;
		ArrayList<ArrayList<Integer>> copiedFishTank = new ArrayList<>();
		
		for (i = 0; i < fishTank.size(); i++) {
			copiedFishTank.add(new ArrayList<>());
			for (j = 0; j < fishTank.get(i).size(); j++) {
				copiedFishTank.get(i).add(fishTank.get(i).get(j));
			}
		}
		
		for (i = 0; i < copiedFishTank.size(); i++) {
			for (j = 0; j < copiedFishTank.get(i).size(); j++) {
				
				fish = copiedFishTank.get(i).get(j);
				movingFish = 0;
				
				if (i + 1 < copiedFishTank.size() && j < copiedFishTank.get(i + 1).size()) {
					movingFish += (copiedFishTank.get(i + 1).get(j) - fish) / 5;
				}
				if (i - 1 >= 0) {
					movingFish += (copiedFishTank.get(i - 1).get(j) - fish) / 5;
				}
				if (j + 1 < fishTank.get(i).size()) {
					movingFish += (copiedFishTank.get(i).get(j + 1) - fish) / 5;
				}
				if (j - 1 >= 0) {
					movingFish += (copiedFishTank.get(i).get(j - 1) - fish) / 5;
				}
				
				fishTank.get(i).set(j, fish + movingFish);
				
			}
		}
		
	}
	
	private static void arrange(int fishTankLength) {
		
		int i, j;
		ArrayList<ArrayList<Integer>> copiedFishTank = new ArrayList<>();
		
		for (i = 0; i < fishTank.size(); i++) {
			copiedFishTank.add(new ArrayList<>());
			for (j = 0; j < fishTank.get(i).size(); j++) {
				copiedFishTank.get(i).add(fishTank.get(i).get(j));
			}
		}
		
		fishTank.clear();
		fishTank.add(new ArrayList<>());
		
		for (i = 0; i < copiedFishTank.get(0).size(); i++) {
			for (j = 0; j < copiedFishTank.size(); j++) {
				if (i < copiedFishTank.get(j).size()) {
					fishTank.get(0).add(copiedFishTank.get(j).get(i));
				}
			}
		}
		
	}
	
	private static void secondLevitation(int fishTankLength) {
		
		int i;
		
		for (i = 0; i < 3; i++) {
			fishTank.add(new ArrayList<>());
		}
		
		for (i = 0; i < fishTankLength / 2; i++) {
			fishTank.get(1).add(fishTank.get(0).remove((fishTankLength / 2) - 1 - i));
		}
		
		for (i = 0; i < fishTankLength / 4; i++) {
			fishTank.get(2).add(fishTank.get(1).remove((fishTankLength / 4) - 1 - i));
			fishTank.get(3).add(fishTank.get(0).remove((fishTankLength / 4) - 1 - i));
		}
		
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		StringTokenizer st;
		
		int n, k, i, min, max, cnt = 0;
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		fishTank.add(new ArrayList<>());
		for (i = 0; i < n; i++) {
			fishTank.get(0).add(Integer.parseInt(st.nextToken()));
		}
		
		while (cnt >= 0) {
			
			min = 10001;
			max = 0;
			
			for (i = 0; i < n; i++) {
				if (fishTank.get(0).get(i) < min) {
					min = fishTank.get(0).get(i);
				}
				if (fishTank.get(0).get(i) > max) {
					max = fishTank.get(0).get(i);
				}
			}
			
			if (max - min <= k) {
				bw.write(Integer.toString(cnt));
				break;
			}
			
			addFish(n, min);
			firstLevitation(n);
			populationControl();
			arrange(n);
			secondLevitation(n);
			populationControl();
			arrange(n);
			
			cnt++;
			
		}
		
		bw.flush();
		bw.close();

	}

}