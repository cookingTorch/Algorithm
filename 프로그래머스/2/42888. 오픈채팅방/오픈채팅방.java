import java.util.HashMap;
import java.util.StringTokenizer;

class Solution {
	private static final int ENTER = 'E';
	private static final int LEAVE = 'L';
	private static final int CHANGE = 'C';
	private static final String E = "님이 들어왔습니다.";
	private static final String L = "님이 나갔습니다.";
	private static final String DELIM = " ";

	private static final class Log {
		int query;
		String id;
		String name;

		Log(String query) {
			StringTokenizer st;

			st = new StringTokenizer(query, DELIM, false);
			this.query = st.nextToken().charAt(0);
			this.id = st.nextToken();
			if (this.query != LEAVE) {
				this.name = st.nextToken();
			}
		}
	}

	public String[] solution(String[] record) {
		int i;
		int idx;
		int len;
		int size;
		int query;
		Log log;
		Log[] logs;
		String[] ans;
		HashMap<String, String> map;

		map = new HashMap<>();
		len = record.length;
		size = len;
		logs = new Log[len];
		for (i = 0; i < len; i++) {
			logs[i] = new Log(record[i]);
			query = logs[i].query;
			if (query == ENTER) {
				map.put(logs[i].id, logs[i].name);
			} else if (query == CHANGE) {
				map.put(logs[i].id, logs[i].name);
				size--;
			}
		}
		ans = new String[size];
		idx = 0;
		for (i = 0; i < len; i++) {
			log = logs[i];
			query = log.query;
			if (query == ENTER) {
				ans[idx++] = map.get(log.id) + E;
			} else if (query == LEAVE) {
				ans[idx++] = map.get(log.id) + L;
			}
		}
		return ans;
	}
}
