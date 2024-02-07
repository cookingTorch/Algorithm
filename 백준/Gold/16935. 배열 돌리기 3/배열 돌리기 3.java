import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[][] arr0, arr1, arr2, arr3, temp0, temp1, temp2, temp3;
	
	private static void upDown() {
		int nn, nm, i, j;
		int[][] nArr0, nArr1, nArr2, nArr3;
		
		nn = arr0.length;
		nm = arr0[0].length;
		nArr0 = temp3;
		nArr1 = temp2;
		nArr2 = temp1;
		nArr3 = temp0;
		for (i = 0; i < nn; i++) {
			for (j = 0; j < nm; j++) {
				nArr0[nn - 1 - i][j] = arr0[i][j];
				nArr1[nn - 1 - i][j] = arr1[i][j];
				nArr2[nn - 1 - i][j] = arr2[i][j];
				nArr3[nn - 1 - i][j] = arr3[i][j];
			}
		}
		temp0 = arr0;
		temp1 = arr1;
		temp2 = arr2;
		temp3 = arr3;
		arr0 = nArr3;
		arr1 = nArr2;
		arr2 = nArr1;
		arr3 = nArr0;
	}
	
	private static void leftRight() {
		int nn, nm, i, j;
		int[][] nArr0, nArr1, nArr2, nArr3;
		
		nn = arr0.length;
		nm = arr0[0].length;
		nArr0 = temp1;
		nArr1 = temp0;
		nArr2 = temp3;
		nArr3 = temp2;
		for (i = 0; i < nn; i++) {
			for (j = 0; j < nm; j++) {
				nArr0[i][nm - 1 - j] = arr0[i][j];
				nArr1[i][nm - 1 - j] = arr1[i][j];
				nArr2[i][nm - 1 - j] = arr2[i][j];
				nArr3[i][nm - 1 - j] = arr3[i][j];
			}
		}
		temp0 = arr0;
		temp1 = arr1;
		temp2 = arr2;
		temp3 = arr3;
		arr0 = nArr1;
		arr1 = nArr0;
		arr2 = nArr3;
		arr3 = nArr2;
	}
	
	private static void clock() {
		int nn, nm, i, j;
		
		nn = arr0.length;
		nm = arr0[0].length;
		temp0 = new int[nm][nn];
		temp1 = new int[nm][nn];
		temp2 = new int[nm][nn];
		temp3 = new int[nm][nn];
		for (i = 0; i < nn; i++) {
			for (j = 0; j < nm; j++) {
				temp1[j][nn - 1 - i] = arr0[i][j];
				temp2[j][nn - 1 - i] = arr1[i][j];
				temp3[j][nn - 1 - i] = arr2[i][j];
				temp0[j][nn - 1 - i] = arr3[i][j];
			}
		}
		arr0 = temp0;
		arr1 = temp1;
		arr2 = temp2;
		arr3 = temp3;
		temp0 = new int[nm][nn];
		temp1 = new int[nm][nn];
		temp2 = new int[nm][nn];
		temp3 = new int[nm][nn];
	}
	
	private static void counterClock() {
		int nn, nm, i, j;
		
		nn = arr0.length;
		nm = arr0[0].length;
		temp0 = new int[nm][nn];
		temp1 = new int[nm][nn];
		temp2 = new int[nm][nn];
		temp3 = new int[nm][nn];
		for (i = 0; i < nn; i++) {
			for (j = 0; j < nm; j++) {
				temp3[nm - 1 - j][i] = arr0[i][j];
				temp0[nm - 1 - j][i] = arr1[i][j];
				temp1[nm - 1 - j][i] = arr2[i][j];
				temp2[nm - 1 - j][i] = arr3[i][j];
			}
		}
		arr0 = temp0;
		arr1 = temp1;
		arr2 = temp2;
		arr3 = temp3;
		temp0 = new int[nm][nn];
		temp1 = new int[nm][nn];
		temp2 = new int[nm][nn];
		temp3 = new int[nm][nn];
	}
	
	private static void rotateClock() {
		int[][] nArr0, nArr1, nArr2, nArr3;
		
		nArr0 = arr0;
		nArr1 = arr1;
		nArr2 = arr2;
		nArr3 = arr3;
		arr0 = nArr3;
		arr1 = nArr0;
		arr2 = nArr1;
		arr3 = nArr2;
	}
	
	private static void rotateCounterClock() {
		int[][] nArr0, nArr1, nArr2, nArr3;
		
		nArr0 = arr0;
		nArr1 = arr1;
		nArr2 = arr2;
		nArr3 = arr3;
		arr0 = nArr1;
		arr1 = nArr2;
		arr2 = nArr3;
		arr3 = nArr0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, hn, hm, r, i, j;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		hn = n / 2;
		hm = m / 2;
		arr0 = new int[hn][hm];
		arr1 = new int[hn][hm];
		arr2 = new int[hn][hm];
		arr3 = new int[hn][hm];
		temp0 = new int[hn][hm];
		temp1 = new int[hn][hm];
		temp2 = new int[hn][hm];
		temp3 = new int[hn][hm];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < m; j++) {
				if (i < hn && j < hm) {
					arr0[i][j] = Integer.parseInt(st.nextToken());
				} else if (i < hn) {
					arr1[i][j - hm] = Integer.parseInt(st.nextToken());
				} else if (j >= hm) {
					arr2[i - hn][j - hm] = Integer.parseInt(st.nextToken());
				} else {
					arr3[i - hn][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < r; i++) {
			switch (Integer.parseInt(st.nextToken())) {
			case 1:
				upDown();
				break;
			case 2:
				leftRight();
				break;
			case 3:
				clock();
				break;
			case 4:
				counterClock();
				break;
			case 5:
				rotateClock();
				break;
			case 6:
				rotateCounterClock();
			}
		}
		n = arr0.length * 2;
		m = arr0[0].length * 2;
		hn = n / 2;
		hm = m / 2;
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				if (i < hn && j < hm) {
					sb.append(arr0[i][j]).append(' ');
				} else if (i < hn) {
					sb.append(arr1[i][j - hm]).append(' ');
				} else if (j >= hm) {
					sb.append(arr2[i - hn][j - hm]).append(' ');
				} else {
					sb.append(arr3[i - hn][j]).append(' ');
				}
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}
