class Solution {
	private static final int SIZE = 26;
	private static final int DIFF = 'A';

	public int solution(String skill, String[] skill_trees) {
		int i;
		int j;
		int len;
		int idx;
		int cur;
		int ans;
		int size;
		int[] idxs;
		String tree;

		idx = 1;
		idxs = new int[SIZE];
		len = skill.length();
		for (i = 0; i < len; i++) {
			idxs[skill.charAt(i) - DIFF] = idx++;
		}
		ans = 0;
		size = skill_trees.length;
		for (i = 0; i < size; i++) {
			cur = 1;
			tree = skill_trees[i];
			len = tree.length();
			for (j = 0; j < len; j++) {
				idx = idxs[tree.charAt(j) - DIFF];
				if (idx > cur) {
					break;
				}
				if (idx == cur) {
					cur++;
				}
			}
			if (j == len) {
				ans++;
			}
		}
		return ans;
	}
}
