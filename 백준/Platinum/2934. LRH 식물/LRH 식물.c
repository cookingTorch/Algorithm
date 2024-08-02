#include <stdio.h>

#define LRH_MAX_SIZE	100000
#define LRH_TREE_SIZE	400000

int lazy[LRH_TREE_SIZE];

void
lrh_prop(int node, int start, int end)
{
	lazy[node << 1] += lazy[node];
	lazy[node << 1 | 1] += lazy[node];
	lazy[node] = 0;
}

int
lrh_delete(int node, int start, int end, int idx)
{
	int rc;
	int mid;

	if (start == end) {
		rc = lazy[node];
		lazy[node] = 0;
		return rc;
	}
	lrh_prop(node, start, end);
	mid = (start + end) >> 1;
	if (idx <= mid)
		return lrh_delete(node << 1, start, mid, idx);
	return lrh_delete(node << 1 | 1, mid + 1, end, idx);;
}

void
lrh_insert(int node, int start, int end, int left, int right)
{
	int mid;

	if (right < start || end < left)
		return;
	if (left <= start && end <= right) {
		lazy[node]++;
		return;
	}
	mid = (start + end) >> 1;
	lrh_insert(node << 1, start, mid, left, right);
	lrh_insert(node << 1 | 1, mid + 1, end, left, right);
}

int
main()
{
	int n;
	int l;
	int r;
	int i;

	for (i = 0; i < LRH_TREE_SIZE; i++)
		lazy[i] = 0;
	scanf("%d", &n);
	while (n-- > 0) {
		scanf("%d %d", &l, &r);
		printf("%d\n", lrh_delete(1, 1, LRH_MAX_SIZE, l) + lrh_delete(1, 1, LRH_MAX_SIZE, r));
		if (r > l + 1)
			lrh_insert(1, 1, LRH_MAX_SIZE, l + 1, r - 1);
	}
	return 0;
}
