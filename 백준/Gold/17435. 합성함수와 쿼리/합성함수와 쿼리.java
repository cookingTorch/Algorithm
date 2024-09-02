import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    private static final char LINE_BREAK = '\n';

    private static final class Edge {
        int to;
        Edge next;

        Edge(int to, Edge next) {
            this.to = to;
            this.next = next;
        }
    }

    private static abstract class Component {
        int size;
        int[] arr;
        HashMap<Integer, Integer> map;

        abstract int find(int start, int num);
    }

    private static class Chain extends Component {
        Chain(ArrayDeque<Integer> stack) {
            int i;

            size = stack.size() - 1;
            arr = new int[size + 1];
            map = new HashMap<>();
            for (i = 0; i <= size; i++) {
                arr[i] = stack.pollFirst();
                map.put(arr[i], i);
            }
        }

        @Override
        int find(int start, int num) {
            int idx;

            idx = map.get(start) + num;
            if (idx < size) {
                return arr[idx];
            }
            return components[arr[size]].find(arr[size], idx - size);
        }
    }

    private static class Cycle extends Component {
        Cycle(ArrayDeque<Integer> q) {
            int i;

            size = q.size();
            arr = new int[size];
            map = new HashMap<>();
            for (i = 0; i < size; i++) {
                arr[i] = q.pollFirst();
                map.put(arr[i], i);
            }
        }

        @Override
        int find(int start, int num) {
            return arr[(map.get(start) + num) % size];
        }
    }

    private static int[] children;
    private static boolean[] visited;
    private static Edge[] parents;
    private static Component[] components;

    private static Component buildChain(int node, ArrayDeque<Integer> stack) {
        Edge edge;

        visited[node] = true;
        stack.addFirst(node);
        edge = parents[node];
        if (edge == null) {
            return components[node] = new Chain(stack);
        }
        components[node] = buildChain(edge.to, stack);
        for (edge = edge.next; edge != null; edge = edge.next) {
            buildChain(edge.to);
        }
        return components[node];
    }

    private static void buildChain(int node) {
        ArrayDeque<Integer> stack;

        stack = new ArrayDeque<>();
        stack.addFirst(children[node]);
        buildChain(node, stack);
    }

    private static Component buildCycle(int node, ArrayDeque<Integer> q) {
        if (node == q.peekFirst()) {
            return components[node] = new Cycle(q);
        }
        q.addLast(node);
        return components[node] = buildCycle(children[node], q);
    }

    private static void buildCycle(int node) {
        ArrayDeque<Integer> q;

        q = new ArrayDeque<>();
        q.addLast(node);
        buildCycle(children[node], q);
    }

    private static void build(int node) {
        Edge edge;

        if (visited[node]) {
            if (components[node] == null) {
                buildCycle(node);
            }
            for (edge = parents[node]; edge != null; edge = edge.next) {
                if (components[edge.to] == null) {
                    buildChain(edge.to);
                }
            }
            return;
        }
        visited[node] = true;
        build(children[node]);
    }

    public static void main(String[] args) throws IOException {
        int m;
        int q;
        int n;
        int x;
        int child;
        int i;
        StringBuilder sb;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        m = Integer.parseInt(br.readLine());
        children = new int[m + 1];
        parents = new Edge[m + 1];
        st = new StringTokenizer(br.readLine());
        for (i = 1; i <= m; i++) {
            child = Integer.parseInt(st.nextToken());
            children[i] = child;
            parents[child] = new Edge(i, parents[child]);
        }
        components = new Component[m + 1];
        visited = new boolean[m + 1];
        for (i = 1; i <= m; i++) {
            if (!visited[i]) {
                build(i);
            }
        }
        sb = new StringBuilder();
        q = Integer.parseInt(br.readLine());
        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            sb.append(components[x].find(x, n)).append(LINE_BREAK);
        }
        System.out.print(sb.toString());
    }
}
