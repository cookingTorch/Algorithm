import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	private static final class Edge {
		int to;
		Edge next;
		
		Edge(int to, Edge next) {
			this.to = to;
			this.next = next;
		}
	}
	
	private static final class Tree implements Comparable<Tree> {
		int size;
		long hashCode;
		
		Tree(long hashCode, int size) {
			this.hashCode = hashCode;
			this.size = size;
		}

		@Override
		public int compareTo(Tree o) {
			if (this.size == o.size) {
				return Long.compare(this.hashCode, o.hashCode);
			}
			return Long.compare(this.size, o.size);
		}
	}
	
	private static int s;
	private static int[] subSize;
	private static Edge[] adj;
	private static HashSet<Long> results = new HashSet<>();
	private static HashSet<Integer> centroids = new HashSet<>();
	
	private static final Tree max(Tree tree1, Tree tree2) {
		if (tree1.compareTo(tree2) > 0) {
			return tree1;
		}
		return tree2;
	}

	private static final int getSize(int curr, int parent) {
		Edge edge;
		
		subSize[curr] = 1;
		for (edge = adj[curr]; edge != null; edge = edge.next) {
			if (edge.to == parent) {
				continue;
			}
			subSize[curr] += getSize(edge.to, curr);
		}
		return subSize[curr];
	}

	private static final int getCentroid(int curr, int parent, int thr) {
		Edge edge;
		
		for (edge = adj[curr]; edge != null; edge = edge.next) {
			if (edge.to == parent) continue;
			if (subSize[edge.to] > thr) {
				return getCentroid(edge.to, curr, thr);
			}
		}
		return curr;
	}

	private static final void addSecondCentroid(int curr, int parent, int thr) {
		int subSizeSum;
		boolean isValid;
		Edge edge;
		Edge subEdge;
		
		if ((s & 1) == 1) {
			return;
		}
		for (edge = adj[curr]; edge != null; edge = edge.next) {
			if (edge.to == parent) {
				continue;
			}
			subSizeSum = 1;
			isValid = true;
			if (subSize[edge.to] - 1 > thr) {
				continue;
			}
			for (subEdge = adj[edge.to]; subEdge != null; subEdge = subEdge.next) {
				if (subEdge.to == curr) {
					continue;
				}
				subSizeSum += subSize[subEdge.to];
				if (subSize[subEdge.to] > thr) {
					isValid = false;
				}
			}
			subSizeSum = s - subSizeSum;
			if (isValid && subSizeSum <= thr) {
				centroids.add(edge.to);
			}
		}
	}

	private static final Tree dnc(int curr, int parent) {
		Edge edge;
		Tree result;
		
		result = new Tree(1L, 1);
		ArrayList<Tree> subTrees = new ArrayList<>();
		for (edge = adj[curr]; edge != null; edge = edge.next) {
			if (edge.to == parent) {
				continue;
			}
			subTrees.add(dnc(edge.to, curr));
		}
		Collections.sort(subTrees);
		for (Tree child : subTrees) {
			result.hashCode <<= child.size;
			result.hashCode |= child.hashCode;
			result.size += child.size;
		}
		result.hashCode <<= 1;
		result.size++;
		return result;
	}

	public static void main(String[] args) throws IOException {
		int n;
		int u;
		int v;
		int thr;
		int centroid;
		BufferedReader br;
		StringTokenizer st;
		
		centroids = new HashSet<>();
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		while (n-- > 0) {
			s = Integer.parseInt(br.readLine());
			adj = new Edge[s];
			for (int i = 1; i < s; i++) {
				st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				adj[u] = new Edge(v, adj[u]);
				adj[v] = new Edge(u, adj[v]);
			}
			subSize = new int[s];
			thr = getSize(0, -1) >> 1;
			centroids.clear();
			centroid = getCentroid(0, -1, thr);
			centroids.add(centroid);
			addSecondCentroid(centroid, -1, thr);
			Tree tree = new Tree(-1, -1);
			for (int centroidNode : centroids) {
				tree = max(tree, dnc(centroidNode, -1));
			}
			results.add(tree.hashCode);
		}
		System.out.print(results.size());
	}
	
}
