import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

class Solution {
    private static final int COL = 50;
    private static final int SIZE = COL * COL + 1;
    private static final char UPDATE = 'P';
    private static final char MERGE = 'E';
    private static final char UNMERGE = 'N';
    private static final char PRINT = 'R';
    private static final String EMPTY = "EMPTY";
    private static final String DELIM = " ";

    private static final class List {
        Node head;
        Node tail;

        List(int v) {
            head = new Node(v);
            tail = head;
        }
    }

    private static final class Node {
        int v;
        Node next;

        Node(int v) {
            this.v = v;
        }
    }

    private static int[] roots;
    private static List[] lists;
    private static String[] vals;
    private static HashMap<String, HashSet<Integer>> map;

    private static int convert(String r, String c) {
        return (Integer.parseInt(r) - 1) * COL + Integer.parseInt(c);
    }

    private static int find(int v) {
        if (roots[v] <= 0) {
            return v;
        }
        return roots[v] = find(roots[v]);
    }

    private static void union(int u, int v) {
        roots[v] = u;
        lists[u].tail.next = lists[v].head;
        lists[u].tail = lists[v].tail;
        lists[v] = null;
    }

    private static void update(int v, String val) {
        HashSet<Integer> set;

        v = find(v);
        if (vals[v] != null) {
            set = map.get(vals[v]);
            set.remove(v);
            if (set.isEmpty()) {
                map.remove(vals[v]);
            }
        }
        vals[v] = val;
        if (val != null) {
            if ((set = map.get(val)) == null) {
                map.put(val, set = new HashSet<>());
            }
            set.add(v);
        }
    }

    private static void update(String val1, String val2) {
        HashSet<Integer> set;

        if (!map.containsKey(val1) || val1.equals(val2)) {
            return;
        }
        if ((set = map.get(val2)) == null) {
            map.put(val2, set = new HashSet<>());
        }
        for (int idx : map.get(val1)) {
            vals[idx] = val2;
            set.add(idx);
        }
        map.remove(val1);
    }

    private static void merge(int u, int v) {
        if ((u = find(u)) == (v = find(v))) {
            return;
        }
        if (vals[u] == null) {
            union(v, u);
        } else {
            if (vals[v] != null) {
                update(v, null);
            }
            union(u, v);
        }
    }

    private static void unmerge(int v) {
        int root;
        Node node;

        root = find(v);
        for (node = lists[root].head; node != null; node = node.next) {
            roots[node.v] = 0;
            lists[node.v] = new List(node.v);
        }
        if (v != root) {
            update(v, vals[root]);
            update(root, null);
        }
    }

    private static String print(int v) {
        return vals[v = find(v)] == null ? EMPTY : vals[v];
    }

    public String[] solution(String[] commands) {
        int i;
        String arg1;
        String arg2;
        ArrayList<String> ans;
        StringTokenizer st;

        ans = new ArrayList<>();
        roots = new int[SIZE];
        vals = new String[SIZE];
        lists = new List[SIZE];
        map = new HashMap<>();
        for (i = 1; i < SIZE; i++) {
            lists[i] = new List(i);
        }
        for (String command : commands) {
            st = new StringTokenizer(command, DELIM, false);
            switch (st.nextToken().charAt(1)) {
                case UPDATE:
                    arg1 = st.nextToken();
                    arg2 = st.nextToken();
                    if (st.hasMoreTokens()) {
                        update(convert(arg1, arg2), st.nextToken());
                    } else {
                        update(arg1, arg2);
                    }
                    break;
                case MERGE:
                    merge(convert(st.nextToken(), st.nextToken()), convert(st.nextToken(), st.nextToken()));
                    break;
                case UNMERGE:
                    unmerge(convert(st.nextToken(), st.nextToken()));
                    break;
                case PRINT:
                    ans.add(print(convert(st.nextToken(), st.nextToken())));
                    break;
            }
        }
        return ans.toArray(new String[ans.size()]);
    }
}
