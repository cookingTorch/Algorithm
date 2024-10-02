#include <stdio.h>
#include <stdlib.h>

#define MAX_M		10001
#define MAX_P		100001
#define MAX_LEN		10
#define RECOMMEND	'r'
#define ADD			'a'
#define SOLVED		's'

const int INF = 2147483647 >> 1;
const int SHIFT = 17;
const int MOD = (1 << SHIFT) - 1;
const char MAX = '1';

int maxLazy[MAX_M << 2];
int minLazy[MAX_M << 2];
char times[MAX_M];

typedef struct range_s range_t;
struct range_s {
	int left;
	int right;
	int val;
	range_t *next;
};

range_t *
new_range(int left, int val, range_t *next)
{
	range_t *range;

	range = (range_t *) malloc(sizeof(range_t));
	range->left = left;
	range->right = INF;
	range->val = val;
	range->next = next;
	return range;
}

int
max(int val1, int val2)
{
	return val1 > val2 ? val1 : val2;
}

int
min(int val1, int val2)
{
	return val1 < val2 ? val1 : val2;
}

void
update(int node, int start, int end, int left, int right, int val)
{
	int mid;

	if (right < start || end < left) {
		return;
	}
	if (left <= start && end <= right) {
		if (val > maxLazy[node]) {
			maxLazy[node] = val;
		}
		if (val < minLazy[node]) {
			minLazy[node] = val;
		}
		return;
	}
	mid = start + end >> 1;
	update(node << 1, start, mid, left, right, val);
	update(node << 1 | 1, mid + 1, end, left, right, val);
}

void
prop(int node, int start, int end)
{
	int mid;

	if (start == end) {
		printf("%d\n", (times[start] == MAX ? maxLazy[node] : minLazy[node]) & MOD);
		return;
	}
	maxLazy[node << 1] = max(maxLazy[node], maxLazy[node << 1]);
	maxLazy[node << 1 | 1] = max(maxLazy[node], maxLazy[node << 1 | 1]);
	minLazy[node << 1] = min(minLazy[node], minLazy[node << 1]);
	minLazy[node << 1 | 1] = min(minLazy[node], minLazy[node << 1 | 1]);
	mid = start + end >> 1;
	prop(node << 1, start, mid);
	prop(node << 1 | 1, mid + 1, end);
}

int
main()
{
	int n;
	int m;
	int p;
	int l;
	int i;
	int time;
	int size;
	char query[MAX_LEN];
	range_t *range;
	range_t *ranges[MAX_P];

	scanf("%d", &n);
	for (i = 1; i < MAX_P; i++) {
		ranges[i] = NULL;
	}
	while (n-- > 0) {
		scanf("%d %d", &p, &l);
		ranges[p] = new_range(0, l << SHIFT | p, NULL);
	}
	scanf("%d", &m);
	time = 1;
	for (i = 0; i < m; i++) {
		scanf("%s", query);
		switch (query[0]) {
			case RECOMMEND:
				scanf("%s", query);
				times[time++] = query[0];
				break;
			case ADD:
				scanf("%d %d", &p, &l);
				ranges[p] = new_range(time, l << SHIFT | p, ranges[p]);
				break;
			case SOLVED:
				scanf("%d", &p);
				if (ranges[p]->left < time) {
					ranges[p]->right = time - 1;
				} else {
					ranges[p] = ranges[p]->next;
				}
				break;
		}
	}
	size = time << 2;
	for (i = 0; i < size; i++) {
		maxLazy[i] = 0;
		minLazy[i] = INF;
	}
	time--;
	for (i = 1; i < MAX_P; i++) {
		for (range = ranges[i]; range != NULL; range = range->next) {
			update(1, 1, time, range->left, range->right, range->val);
		}
	}
	prop(1, 1, time);
}
