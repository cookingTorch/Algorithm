#include <stdio.h>

int _n;
int _ans;
int _line;

void
n_queen_dfs(int depth, int full, int left, int right)
{
	int bit;
	int pos;

	if (depth == _n) {
		_ans++;
		return;
	}
	for (bit = ~(full | left | right) & _line; bit != 0; bit ^= pos) {
		pos = bit & -bit;
		n_queen_dfs(depth + 1, full | pos, (left | pos) << 1, (right | pos) >> 1);
	}
}

int
main()
{
	scanf("%d", &_n);
	_ans = 0;
	_line = (1 << _n) - 1;
	n_queen_dfs(0, 0, 0, 0);
	printf("%d", _ans);
}