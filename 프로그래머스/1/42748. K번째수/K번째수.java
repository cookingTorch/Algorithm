class Solution {
    private static final int MAX = 100;

    private static class Node {
        int sum;
        Node left;
        Node right;
    }

    private Node init(int range) {
        Node node;

        node = new Node();
        if (range != 1) {
            node.left = init(range - (range >>> 1));
            node.right = init(range >>> 1);
        }
        return node;
    }

    private Node update(Node tree, int range, int val) {
        Node node;

        node = new Node();
        if (range == 1) {
            node.sum = tree.sum + 1;
        } else if (val <= range - (range >>> 1)) {
            node.left = update(tree.left, range - (range >>> 1), val);
            node.right = tree.right;
            node.sum = node.left.sum + node.right.sum;
        } else {
            node.left = tree.left;
            node.right = update(tree.right, range >>> 1, val - (range - (range >>> 1)));
            node.sum = node.left.sum + node.right.sum;
        }
        return node;
    }

    private int query(Node l, Node r, int range, int k) {
        if (range == 1) {
            return 1;
        }
        if (r.left.sum - l.left.sum >= k) {
            return query(l.left, r.left, range - (range >>> 1), k);
        }
        return range - (range >>> 1) + query(l.right, r.right, range >>> 1, k - (r.left.sum - l.left.sum));
    }

    public int[] solution(int[] array, int[][] commands) {
        int i;
        int len;
        int size;
        int[] ans;
        Node trees[];

        size = array.length;
        trees = new Node[size + 1];
        trees[0] = init(MAX);
        for (i = 1; i <= size; i++) {
            trees[i] = update(trees[i - 1], MAX, array[i - 1]);
        }
        len = commands.length;
        ans = new int[len];
        for (i = 0; i < len; i++) {
            ans[i] = query(trees[commands[i][0] - 1], trees[commands[i][1]], MAX, commands[i][2]);
        }
        return ans;
    }
}
