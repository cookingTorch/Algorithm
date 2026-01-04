class Solution {
	public int solution(int bridge_length, int weight, int[] truck_weights) {
		int l;
		int r;
		int len;
		int time;
		int truck;
		int[] end;
		
		len = truck_weights.length;
		end = new int[len];
		l = 0;
		time = 1;
		end[l] = time + bridge_length;
		weight -= truck_weights[l];
		for (r = 1; r < len; r++) {
			if (++time == end[l]) {
				weight += truck_weights[l++];
			}
			truck = truck_weights[r];
			while (weight < truck) {
				time = end[l];
				weight += truck_weights[l++];
			}
			end[r] = time + bridge_length;
			weight -= truck_weights[r];
		}
		return end[len - 1];
	}
}
