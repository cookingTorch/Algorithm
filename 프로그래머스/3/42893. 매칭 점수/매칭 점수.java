import java.util.ArrayList;
import java.util.HashMap;

class Solution {
	private static final class Page {
		private static final char A = 'A';
		private static final char Z = 'Z';
		private static final char a = 'a';
		private static final char z = 'z';
		private static final char BRACE = '>';
		private static final char QUOTE = '"';
		private static final char[] META = "<meta ".toCharArray();
		private static final char[] HREF = "<a href=\"".toCharArray();
		private static final char[] CONTENT = "content=\"".toCharArray();
		private static final int NULL = -1;
		private static final int DIFF = a - A;
		private static final int META_LEN = META.length;
		private static final int HREF_LEN = HREF.length;
		private static final int CONTENT_LEN = CONTENT.length;

		private static int len;
		private static char[] word;
		private static HashMap<String, Page> map;

		int cnt;
		int size;
		int letter;
		char[] txt;
		double score;
		ArrayList<String> links;

		Page(String page) {
			int i;
			int j;

			txt = page.toCharArray();
			size = txt.length;
			links = new ArrayList<>();
			letter = NULL;
			for (i = 0; i < size; i++) {
				isWord(i);
				if (isPattern(i, META, META_LEN)) {
					for (j = i + META_LEN; j < size; j++) {
						if (txt[j] == BRACE) {
							break;
						}
						if (isPattern(j, CONTENT, CONTENT_LEN)) {
							map.put(getUrl(j + CONTENT_LEN), this);
							break;
						}
					}
				}
				if (isPattern(i, HREF, HREF_LEN)) {
					links.add(getUrl(i + HREF_LEN));
				}
			}
			if (letter != NULL && size - letter == len) {
				for (i = 0; i < len; i++) {
					if (lower(txt[letter + i]) != word[i]) {
						return;
					}
				}
				cnt++;
			}
		}

		private static char lower(char ch) {
			if (ch < a) {
				return (char) (ch + DIFF);
			}
			return ch;
		}

		static void init(String str) {
			int i;

			word = str.toCharArray();
			len = word.length;
			for (i = 0; i < len; i++) {
				word[i] = lower(word[i]);
			}
			map = new HashMap<>();
		}

		private boolean isLetter(char ch) {
			return (A <= ch && ch <= Z) || (a <= ch && ch <= z);
		}

		private void isWord(int i) {
			int j;

			if (letter == NULL) {
				if (isLetter(txt[i])) {
					letter = i;
				}
			} else if (!isLetter(txt[i])) {
				if (i - letter != len) {
					letter = NULL;
					return;
				}
				i = letter;
				letter = NULL;
				for (j = 0; j < len; j++) {
					if (lower(txt[i + j]) != word[j]) {
						return;
					}
				}
				cnt++;
			}
		}

		private boolean isPattern(int i, char[] str, int len) {
			int j;

			if (i + len > size) {
				return false;
			}
			for (j = 0; j < len; j++) {
				if (txt[i + j] != str[j]) {
					return false;
				}
			}
			return true;
		}

		private String getUrl(int i) {
			StringBuilder sb;

			sb = new StringBuilder();
			for (; i < size; i++) {
				if (txt[i] == QUOTE) {
					break;
				}
				sb.append(txt[i]);
			}
			return sb.toString();
		}

		void calc() {
			int len;
			double linkScore;
			Page page;

			score += cnt;
			len = links.size();
			linkScore = (double) cnt / len;
			for (String link : links) {
				if ((page = map.get(link)) != null) {
					page.score += linkScore;
				}
			}
		}
	}

	public int solution(String word, String[] pages) {
		int i;
		int len;
		int ans;
		double max;
		Page[] nodes;

		Page.init(word);
		len = pages.length;
		nodes = new Page[len];
		for (i = 0; i < len; i++) {
			nodes[i] = new Page(pages[i]);
		}
		for (i = 0; i < len; i++) {
			nodes[i].calc();
		}
		ans = 0;
		max = 0.0;
		for (i = 0; i < len; i++) {
			if (nodes[i].score > max) {
				max = nodes[i].score;
				ans = i;
			}
		}
		return ans;
	}
}
