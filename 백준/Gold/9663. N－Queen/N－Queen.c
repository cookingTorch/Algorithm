#include <stdio.h>

int _n;
int _ans;
int _full;

void
n_queen_dfs(int visited, int left, int right)
{
	int bit;
	int pos;

	if (visited == _full) {
		_ans++;
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
	scanf("%d", &_n);
	_full = (1 << _n) - 1;
	n_queen_dfs(0, 0, 0);
	printf("%d", _ans);
	return 0;
}
