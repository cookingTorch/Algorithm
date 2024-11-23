public class Solution {
	private static final int FAIL = 0;
	private static final int DIFF = 'a';
	private static final int MAX_WORD_LEN = 10_001;
	private static final int ALPHABET_SIZE = 26;
	private static final char WILD_CARD = '?';

	private static final class Trie {
		int cnt;
		Trie[] next;

		Trie() {
			next = new Trie[ALPHABET_SIZE];
		}

		final void insertForward(char[] word, int len) {
			int i;
			int idx;
			Trie curr;

			curr = this;
			for (i = 0; i < len; i++) {
				curr.cnt++;
				idx = word[i] - DIFF;
				if (curr.next[idx] == null) {
					curr.next[idx] = new Trie();
				}
				curr = curr.next[idx];
			}
			curr.cnt++;
		}

		final void insertBackward(char[] word, int len) {
			int i;
			int idx;
			Trie curr;

			curr = this;
			for (i = len - 1; i >= 0; i--) {
				curr.cnt++;
				idx = word[i] - DIFF;
				if (curr.next[idx] == null) {
					curr.next[idx] = new Trie();
				}
				curr = curr.next[idx];
			}
			curr.cnt++;
		}

		final int searchForward(char[] word, int len) {
			int i;
			int idx;
			Trie curr;

			curr = this;
			for (i = 0; i < len; i++) {
				if (word[i] == WILD_CARD) {
					break;
				}
				idx = word[i] - DIFF;
				if (curr.next[idx] == null) {
					return FAIL;
				}
				curr = curr.next[idx];
			}
			return curr.cnt;
		}

		final int searchBackward(char[] word, int len) {
			int i;
			int idx;
			Trie curr;

			curr = this;
			for (i = len - 1; i >= 0; i--) {
				if (word[i] == WILD_CARD) {
					break;
				}
				idx = word[i] - DIFF;
				if (curr.next[idx] == null) {
					return FAIL;
				}
				curr = curr.next[idx];
			}
			return curr.cnt;
		}
	}

	public int[] solution(String[] words, String[] queries) {
		int i;
		int len;
		int size;
		int[] ans;
		char[] arr;
		Trie[] forward;
		Trie[] backward;

		forward = new Trie[MAX_WORD_LEN];
		backward = new Trie[MAX_WORD_LEN];
		for (i = 1; i < MAX_WORD_LEN; i++) {
			forward[i] = new Trie();
			backward[i] = new Trie();
		}
		for (String word : words) {
			arr = word.toCharArray();
			len = arr.length;
			if (len >= MAX_WORD_LEN) {
				continue;
			}
			forward[len].insertForward(arr, len);
			backward[len].insertBackward(arr, len);
		}
		size = queries.length;
		ans = new int[size];
		for (i = 0; i < size; i++) {
			arr = queries[i].toCharArray();
			len = arr.length;
			if (arr[0] == WILD_CARD) {
				ans[i] = backward[len].searchBackward(arr, len);
			} else {
				ans[i] = forward[len].searchForward(arr, len);
			}
		}
		return ans;
	}
}
