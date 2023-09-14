import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static class Edge {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private static class Node implements Comparable<Node> {
        int index, distance;

        Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int v, e, k, i, curr;
        int[] distance;
        Node node;
        ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());
        distance = new int[v + 1];
        for (i = 0; i <= v; i++)
            adj.add(new ArrayList<>());
        for (i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            adj.get(Integer.parseInt(st.nextToken())).add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        pq.add(new Node(k, 0));
        while (!pq.isEmpty()) {
            node = pq.poll();
            curr = node.index;
            for (Edge edge : adj.get(curr)) {
                if ((edge.to != k && distance[edge.to] == 0) || distance[curr] + edge.weight < distance[edge.to]) {
                    distance[edge.to] = distance[curr] + edge.weight;
                    pq.add(new Node(edge.to, distance[edge.to]));
                }
            }
        }
        for (i = 1; i <= v; i++) {
            if (i != k && distance[i] == 0)
                sb.append("INF").append("\n");
            else
                sb.append(distance[i]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}