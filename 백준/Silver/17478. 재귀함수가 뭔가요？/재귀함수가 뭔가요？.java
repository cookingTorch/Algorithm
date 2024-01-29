import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final String start = "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n";
	private static final String prefix = "\"재귀함수가 뭔가요?\"\n";
	private static final String story1 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n";
	private static final String story2 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n";
	private static final String story3 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n";
	private static final String answer = "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n";
	private static final String suffix = "라고 답변하였지.\n";
	
	private static void recursion(StringBuilder sb, int n, int depth) {
		int i;
		String underBar;
		StringBuilder builder;
		
		builder = new StringBuilder();
		for (i = 0; i < depth; i++) {
			builder.append("____");
		}
		underBar = builder.toString();
		sb.append(underBar).append(prefix);
		if (depth == n) {
			sb.append(underBar).append(answer);
		} else {
			sb.append(underBar).append(story1);
			sb.append(underBar).append(story2);
			sb.append(underBar).append(story3);
			recursion(sb, n, depth + 1);
		}
		sb.append(underBar).append(suffix);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n;
		
		n = Integer.parseInt(br.readLine());
		sb.append(start);
		recursion(sb, n, 0);
		System.out.print(sb);
	}

}