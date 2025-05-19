import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Solution {
    private static final class Node implements Comparable<Node> {
        private int idx;
        private int work;
        private int degree;
        private ArrayList<Node> children;

        Node(int idx) {
            this.idx = idx;
            children = new ArrayList<>();
        }

        void add(Node child) {
            degree++;
            children.add(child);
        }

        void sort() {
            Collections.sort(children);
        }

        int drop() {
            if (degree == 0) {
                return idx;
            }
            if (work == degree) {
                work = 0;
            }
            return children.get(work++).drop();
        }

        @Override
        public int compareTo(Node o) {
            return idx - o.idx;
        }
    }

    public int[] solution(int[][] edges, int[] target) {
        int i;
        int len;
        int leaf;
        int leafCnt;
        int[] cnt;
        int[] ans;
        Node root;
        Node[] nodes;
        ArrayList<Integer> list;

        len = edges.length;
        nodes = new Node[len + 1];
        leafCnt = 0;
        for (i = 0; i <= len; i++) {
            nodes[i] = new Node(i);
            if (target[i] > 0) {
                leafCnt++;
            }
        }
        for (i = 0; i < len; i++) {
            nodes[edges[i][0] - 1].add(nodes[edges[i][1] - 1]);
        }
        for (i = 0; i <= len; i++) {
            nodes[i].sort();
        }
        root = nodes[0];
        cnt = new int[len + 1];
        list = new ArrayList<>();
        for (;;) {
            leaf = root.drop();
            if (++cnt[leaf] > target[leaf]) {
                return new int[] {-1};
            }
            list.add(leaf);
            if (cnt[leaf] == (target[leaf] - 1) / 3 + 1) {
                if (--leafCnt == 0) {
                    break;
                }
            }
        }
        len = list.size();
        ans = new int[len];
        for (i = len - 1; i >= 0; i--) {
            leaf = list.get(i);
            if (target[leaf] == cnt[leaf]) {
                ans[i] = 1;
            } else if (target[leaf] - cnt[leaf] == 1) {
                ans[i] = 2;
            } else {
                ans[i] = 3;
            }
            target[leaf] -= ans[i];
            cnt[leaf]--;
        }
        return ans;
    }
}
