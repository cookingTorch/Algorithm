import java.util.Arrays;
import java.util.Stack;

class Solution {
	private static final class 현우 {
		private static int thr;private static int[] tree;
		static final int init(int node,int start,int end){
		int mid;if(start==end){return tree[node]=1;}mid
		=start+end>>1;return tree[node]=init(node<<1,start,
		mid)+init(node<<1|1,mid+1,end);}static final void
		init(int size){thr=size-1;tree=new int[size<<2];init
		(1, 0, thr);}static final int delete(int node,int
		start,int end,int prefix){int mid;tree[node]--;if
		(start==end){return start;}mid=start+end>>1;if(prefix
		<=tree[node<<1]){return delete(node<<1,start,mid,
		prefix);}return delete(node<<1|1,mid+1,end,prefix
		-tree[node<<1]);}static final int delete(int prefix)
		{return delete(1,0,thr,prefix);}static final int
		undo(int node,int start,int end,int idx){int mid;
		tree[node]++;if(start==end){return 1;}mid=start+
		end>>1;if(idx<=mid){return undo(node<<1,start,mid,
		idx);}return tree[node<<1]+undo(node<<1|1,mid+1,end,
		idx);}static final int undo(int idx){return undo(1,
		0, thr, idx);}
	}
	
	public String solution(int n, int k, String[] cmd) {
		현우.init(n);
		k++;
        StringBuilder answer = new StringBuilder();
        boolean[] isUse = new boolean[n];
        Arrays.fill(isUse, true);
        Stack<int[]> stack = new Stack<>();
        int size = n;
        for (int i = 0; i < cmd.length; i++) {
            String[] command = cmd[i].split(" ");
            if (command[0].equals("D")) {
                k += Integer.parseInt(command[1]);
            } else if (command[0].equals("C")) {
                stack.push(new int[] {n, 현우.delete(k)});
                n -= 1;
                if (k > n) k -= 1;
            } else if (command[0].equals("U")) {
                k -= Integer.parseInt(command[1]);
            } else if (command[0].equals("Z")) {
                int[] d = stack.pop();
                n += 1;
                if (현우.undo(d[1]) <= k) k += 1;
            }
        }

        while (!stack.isEmpty()) {
            int[] now = stack.pop();
            isUse[now[1]] = false;
        }
        for (int i = 0; i < size; i++) {
            if (isUse[i]) answer.append("O");
            else answer.append("X");
        }

        return answer.toString();
    }
}
