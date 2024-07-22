#include <stdio.h>

int _cnt;
int _full;

void
n_queen_dfs(int visited, int left, int right)
{
	int bit;
	int pos;

	if (visited == _full) {
		_cnt++;
		return;
	}
	for (bit = ~(visited | left | right) & _full; bit != 0; bit ^= pos) {
		pos = bit & -bit;
		n_queen_dfs(visited | pos, (left | pos) << 1, (right | pos) >> 1);
	}
}

int
main()
{
	int n;
	int pos;
	int mid;

	scanf("%d", &n);
	_full = (1 << n) - 1;
	mid = 1 << (n >> 1);
	for (pos = 1; pos < mid; pos <<= 1) {
		n_queen_dfs(pos, pos << 1, pos >> 1);
	}
	_cnt <<= 1;
	if ((n & 1) == 1)
		n_queen_dfs(mid, mid << 1, mid >> 1);
	printf("%d", _cnt);
	return 0;
}
