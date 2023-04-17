import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	private static ArrayList<Integer> subList(ArrayList<Integer> copiedNums, int start, int end) {
		
		List<Integer> subList = copiedNums.subList(start, end);
		ArrayList<Integer> subArrayList = new ArrayList<>(subList);
		
		return subArrayList;
		
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		StringTokenizer st;
		
		int n, i, j, k, start = 0, end = 0;
		int[][] ans = new int[2][2];
		ArrayList<Integer> nums = new ArrayList<>();
		ArrayList<Integer> copiedNums = new ArrayList<>();
		ArrayList<Integer> subNums = new ArrayList<>();
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		for (i = 0; i < n; i++) {
			nums.add(Integer.parseInt(st.nextToken()));
		}
		
		for (i = 0; i < n; i++) {
			copiedNums.add(nums.get(i));
		}
		
		for (i = 0; i < n; i++) {
			if (copiedNums.get(i) != i + 1) {
				start = i;
				end = copiedNums.get(i);
				break;
			}
		}
		
		if (i == n) {
			ans = new int[][] {{0, 0}, {0, 0}};
		}
		else {
			
			subNums.clear();
			subNums.addAll(subList(copiedNums, start, end));
			Collections.reverse(subNums);
			
			for (i = start; i < end; i++) {
				copiedNums.set(i, subNums.get(i - start));
			}
			
			for (i = 0; i < n; i++) {
				if (copiedNums.get(i) != i + 1) {
					break;
				}
			}
			
			if (i == n) {
				ans = new int[][] {{start, end - 1}, {0, 0}};
			}
			else {
				
				for (i = 0; i < 4; i++) {
					
					copiedNums.clear();
					for (j = 0; j < n; j++) {
						copiedNums.add(nums.get(j));
					}
					
					if (i == 0) {
						
						for (j = 0; j < n; j++) {
							if (copiedNums.get(j) != j + 1) {
								start = j;
								for (k = j + 1; k < n; k++) {
									if (copiedNums.get(k) == j + 1) {
										end = k + 1;
										break;
									}
								}
								break;
							}
						}
						
						subNums.clear();
						subNums.addAll(subList(copiedNums, start, end));
						Collections.reverse(subNums);
						
						for (j = start; j < end; j++) {
							copiedNums.set(j, subNums.get(j - start));
						}
						
						ans[0] = new int[] {start, end - 1};
						
						for (j = 0; j < n; j++) {
							if (copiedNums.get(j) != j + 1) {
								start = j;
								for (k = j + 1; k < n; k++) {
									if (copiedNums.get(k) == j + 1) {
										end = k + 1;
										break;
									}
								}
								break;
							}
						}
						
						subNums.clear();
						subNums.addAll(subList(copiedNums, start, end));
						Collections.reverse(subNums);
						
						for (j = start; j < end; j++) {
							copiedNums.set(j, subNums.get(j - start));
						}
						
						ans[1] = new int[] {start, end - 1};
						
						for (j = 0; j < n; j++) {
							if (copiedNums.get(j) != j + 1) {
								break;
							}
						}
						
						if (j == n) {
							break;
						}
						
					}
					else if (i == 1) {
						
						for (j = 0; j < n; j++) {
							if (copiedNums.get(j) != j + 1) {
								start = j;
								for (k = j + 1; k < n; k++) {
									if (copiedNums.get(k) == j + 1) {
										end = k + 1;
										break;
									}
								}
								break;
							}
						}
						
						subNums.clear();
						subNums.addAll(subList(copiedNums, start, end));
						Collections.reverse(subNums);
						
						for (j = start; j < end; j++) {
							copiedNums.set(j, subNums.get(j - start));
						}
						
						ans[0] = new int[] {start, end - 1};
						
						for (j = n - 1; j >= 0; j--) {
							if (copiedNums.get(j) != j + 1) {
								for (k = j - 1; k >= 0; k--) {
									if (copiedNums.get(k) == j + 1) {
										start = k;
										break;
									}
								}
								end = j + 1;
								break;
							}
						}
						
						subNums.clear();
						subNums.addAll(subList(copiedNums, start, end));
						Collections.reverse(subNums);
						
						for (j = start; j < end; j++) {
							copiedNums.set(j, subNums.get(j - start));
						}
						
						ans[1] = new int[] {start, end - 1};
						
						for (j = 0; j < n; j++) {
							if (copiedNums.get(j) != j + 1) {
								break;
							}
						}
						
						if (j == n) {
							break;
						}
						
					}
					else if (i == 2) {
						
						for (j = n - 1; j >= 0; j--) {
							if (copiedNums.get(j) != j + 1) {
								for (k = j - 1; k >= 0; k--) {
									if (copiedNums.get(k) == j + 1) {
										start = k;
										break;
									}
								}
								end = j + 1;
								break;
							}
						}
						
						subNums.clear();
						subNums.addAll(subList(copiedNums, start, end));
						Collections.reverse(subNums);
						
						for (j = start; j < end; j++) {
							copiedNums.set(j, subNums.get(j - start));
						}
						
						ans[0] = new int[] {start, end - 1};
						
						for (j = 0; j < n; j++) {
							if (copiedNums.get(j) != j + 1) {
								start = j;
								for (k = j + 1; k < n; k++) {
									if (copiedNums.get(k) == j + 1) {
										end = k + 1;
										break;
									}
								}
								break;
							}
						}
						
						subNums.clear();
						subNums.addAll(subList(copiedNums, start, end));
						Collections.reverse(subNums);
						
						for (j = start; j < end; j++) {
							copiedNums.set(j, subNums.get(j - start));
						}
						
						ans[1] = new int[] {start, end - 1};
						
						for (j = 0; j < n; j++) {
							if (copiedNums.get(j) != j + 1) {
								break;
							}
						}
						
						if (j == n) {
							break;
						}
						
					}
					else {
						
						for (j = n - 1; j >= 0; j--) {
							if (copiedNums.get(j) != j + 1) {
								for (k = j - 1; k >= 0; k--) {
									if (copiedNums.get(k) == j + 1) {
										start = k;
										break;
									}
								}
								end = j + 1;
								break;
							}
						}
						
						subNums.clear();
						subNums.addAll(subList(copiedNums, start, end));
						Collections.reverse(subNums);
						
						for (j = start; j < end; j++) {
							copiedNums.set(j, subNums.get(j - start));
						}
						
						ans[0] = new int[] {start, end - 1};
						
						for (j = n - 1; j >= 0; j--) {
							if (copiedNums.get(j) != j + 1) {
								for (k = j - 1; k >= 0; k--) {
									if (copiedNums.get(k) == j + 1) {
										start = k;
										break;
									}
								}
								end = j + 1;
								break;
							}
						}
						
						subNums.clear();
						subNums.addAll(subList(copiedNums, start, end));
						Collections.reverse(subNums);
						
						for (j = start; j < end; j++) {
							copiedNums.set(j, subNums.get(j - start));
						}
						
						ans[1] = new int[] {start, end - 1};
						
						for (j = 0; j < n; j++) {
							if (copiedNums.get(j) != j + 1) {
								break;
							}
						}
						
						if (j == n) {
							break;
						}
						
					}
					
				}
				
			}
			
		}
		
		for (i = 0; i < 2; i++) {
			for (j = 0; j < 2; j++) {
				bw.write(Integer.toString(ans[i][j] + 1) + " ");
			}
			bw.newLine();
		}
		
		bw.flush();
		bw.close();

	}

}