import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static void postOrder(int[] pre, int[] in, int n) throws IOException {
		
		if (n == 1) {
			bw.write(Integer.toString(in[0]));
			bw.write(" ");
		}
		else {
			int i, n1 = 0, n2;
			int[] pre1, in1, pre2, in2;
			
			for (i = 0; i < n; i++) {
				if (in[i] == pre[0]) {
					n1 = i;
					break;
				}
			}
			n2 = n - 1 - n1;
			
			if (n1 > 0) {
				pre1 = new int[n1];
				in1 = new int[n1];
				for (i = 0; i < n1; i++) {
					pre1[i] = pre[i + 1];
					in1[i] = in[i]; 
				}
				postOrder(pre1, in1, n1);
			}
			
			if (n2 > 0) {
				pre2 = new int[n2];
				in2 = new int[n2];
				for (i = n1 + 1; i < n; i++) {
					pre2[i - n1 - 1] = pre[i];
					in2[i - n1 - 1] = in[i];
				}
				postOrder(pre2, in2, n2);
			}
			
			bw.write(Integer.toString(pre[0]));
			bw.write(" ");
		}
		
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		StringTokenizer st;
		
		int t, n, i, j;
		int[] preOrder, inOrder;
		
		str = br.readLine();
		t = Integer.parseInt(str);
		
		for (i = 0; i < t; i++) {
			
			str = br.readLine();
			n = Integer.parseInt(str);
			preOrder = new int[n];
			inOrder = new int[n];
			
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			for (j = 0; j < n; j++) {
				preOrder[j] = Integer.parseInt(st.nextToken());
			}
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			for (j = 0; j < n; j++) {
				inOrder[j] = Integer.parseInt(st.nextToken());
			}
			
			postOrder(preOrder, inOrder, n);
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
	}

}