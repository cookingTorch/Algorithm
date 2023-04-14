import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static String[][][] cube = new String[6][3][3];
	static String[][][] copiedCube = new String[6][3][3];
	static int U = 0, D = 1, F = 2, B = 3, L = 4, R = 5;
	static int[][] clockwise = new int[][] {{B, R, F, L, B}, {F, R, B, L, F}, {U, R, D, L, U}, {U, L, D, R, U}, {U, F, D, B, U}, {U, B, D, F, U}};
	
	private static void rotate(int face, char direction) {
		
		int i, j, k;
		
		for (i = 0; i < 6; i++) {
			for (j = 0; j < 3; j++) {
				for (k = 0; k < 3; k++) {
					copiedCube[i][j][k] = cube[i][j][k];
				}
			}
		}
		
		if (direction == '+') {
			
			for (i = 0; i < 3; i++) {
				for (j = 0; j < 3; j++) {
					cube[face][i][j] = copiedCube[face][2 - j][i];
				}
			}
			
			if (face == U) {
				for (i = 0; i < 3; i++) {
					cube[B][0][2 - i] = copiedCube[L][0][2 - i];
					cube[R][0][2 - i] = copiedCube[B][0][2 - i];
					cube[F][0][2 - i] = copiedCube[R][0][2 - i];
					cube[L][0][2 - i] = copiedCube[F][0][2 - i];
				}
			}
			else if (face == D) {
				for (i = 0; i < 3; i++) {
					cube[F][2][i] = copiedCube[L][2][i];
					cube[R][2][i] = copiedCube[F][2][i];
					cube[B][2][i] = copiedCube[R][2][i];
					cube[L][2][i] = copiedCube[B][2][i];
				}
			}
			else if (face == F) {
				for (i = 0; i < 3; i++) {
					cube[U][2][i] = copiedCube[L][2 - i][2];
					cube[R][i][0] = copiedCube[U][2][i];
					cube[D][0][2 - i] = copiedCube[R][i][0];
					cube[L][2 - i][2] = copiedCube[D][0][2 - i];
				}
			}
			else if (face == B) {
				for (i = 0; i < 3; i++) {
					cube[U][0][2 - i] = copiedCube[R][2 - i][2];
					cube[L][i][0] = copiedCube[U][0][2 - i];
					cube[D][2][i] = copiedCube[L][i][0];
					cube[R][2 - i][2] = copiedCube[D][2][i];
				}
			}
			else if (face == L) {
				for (i = 0; i < 3; i++) {
					cube[U][i][0] = copiedCube[B][2 - i][2];
					cube[F][i][0] = copiedCube[U][i][0];
					cube[D][i][0] = copiedCube[F][i][0];
					cube[B][2 - i][2] = copiedCube[D][i][0];
				}
			}
			else if (face == R) {
				for (i = 0; i < 3; i++) {
					cube[U][2 - i][2] = copiedCube[F][2 - i][2];
					cube[B][i][0] = copiedCube[U][2 - i][2];
					cube[D][2 - i][2] = copiedCube[B][i][0];
					cube[F][2 - i][2] = copiedCube[D][2 - i][2];
				}
			}
			
		}
		else if (direction == '-') {
			
			for (i = 0; i < 3; i++) {
				for (j = 0; j < 3; j++) {
					cube[face][i][j] = copiedCube[face][j][2 - i];
				}
			}
			
			if (face == U) {
				for (i = 0; i < 3; i++) {
					cube[L][0][2 - i] = copiedCube[B][0][2 - i];
					cube[B][0][2 - i] = copiedCube[R][0][2 - i];
					cube[R][0][2 - i] = copiedCube[F][0][2 - i];
					cube[F][0][2 - i] = copiedCube[L][0][2 - i];
				}
			}
			else if (face == D) {
				for (i = 0; i < 3; i++) {
					cube[L][2][i] = copiedCube[F][2][i];
					cube[F][2][i] = copiedCube[R][2][i];
					cube[R][2][i] = copiedCube[B][2][i];
					cube[B][2][i] = copiedCube[L][2][i];
				}
			}
			else if (face == F) {
				for (i = 0; i < 3; i++) {
					cube[L][2 - i][2] = copiedCube[U][2][i];
					cube[U][2][i] = copiedCube[R][i][0];
					cube[R][i][0] = copiedCube[D][0][2 - i];
					cube[D][0][2 - i] = copiedCube[L][2 - i][2];
				}
			}
			else if (face == B) {
				for (i = 0; i < 3; i++) {
					cube[R][2 - i][2] = copiedCube[U][0][2 - i];
					cube[U][0][2 - i] = copiedCube[L][i][0];
					cube[L][i][0] = copiedCube[D][2][i];
					cube[D][2][i] = copiedCube[R][2 - i][2];
				}
			}
			else if (face == L) {
				for (i = 0; i < 3; i++) {
					cube[B][2 - i][2] = copiedCube[U][i][0];
					cube[U][i][0] = copiedCube[F][i][0];
					cube[F][i][0] = copiedCube[D][i][0];
					cube[D][i][0] = copiedCube[B][2 - i][2];
				}
			}
			else if (face == R) {
				for (i = 0; i < 3; i++) {
					cube[F][2 - i][2] = copiedCube[U][2 - i][2];
					cube[U][2 - i][2] = copiedCube[B][i][0];
					cube[B][i][0] = copiedCube[D][2 - i][2];
					cube[D][2 - i][2] = copiedCube[F][2 - i][2];
				}
			}
			
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		StringTokenizer st;
		
		int t, n, i, j, k, l, face = -1;
		char direction;
		String command;
		String[] color = new String[] {"w", "y", "r", "o", "g", "b"};
		String[][] uCube = new String[3][3];
		
		str = br.readLine();
		t = Integer.parseInt(str);
		
		for (i = 0; i < t; i++) {
			
			for (j = 0; j < 6; j++) {
				for (k = 0; k < 3; k++) {
					for (l = 0; l < 3; l++) {
						cube[j][k][l] = color[j].substring(0);
					}
				}
			}
			
			str = br.readLine();
			n = Integer.parseInt(str);
			
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			
			for (j = 0; j < n; j++) {
				
				command = st.nextToken();
				
				if (command.charAt(0) == 'U') {
					face = U;
				}
				else if (command.charAt(0) == 'D') {
					face = D;
				}
				else if (command.charAt(0) == 'F') {
					face = F;
				}
				else if (command.charAt(0) == 'B') {
					face = B;
				}
				else if (command.charAt(0) == 'L') {
					face = L;
				}
				else if (command.charAt(0) == 'R') {
					face = R;
				}
				
				direction = command.charAt(1);
				
				rotate(face, direction);
				
			}
			
			for (j = 0; j < 3; j++) {
				for (k = 0; k < 3; k++) {
					bw.write(cube[U][j][k]);
				}
				bw.newLine();
			}
			
		}

		bw.flush();
		bw.close();
		
	}

}