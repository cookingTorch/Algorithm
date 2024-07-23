#include <stdio.h>
#include <stdlib.h>

#define ROBOT_WALL	'1'
#define ROBOT_EMPTY	'0'

int
robot_clean(int m, int pos, int d, char *map)
{
	const int delta[] = {-m, 2, m, -2};

	int i;
	int rc;

	map[pos]--;
	for (rc = 1;;) {
		for (i = 4; i > 0 && map[pos + delta[d = (d + 3) % 4]] != ROBOT_EMPTY; i--);
		if (i) {
			map[pos += delta[d]]--;
			rc++;
		}
		else if (map[pos += delta[(d + 2) % 4]] == ROBOT_WALL)
			break;
	}
	return rc;
}

int
main()
{
	int n;
	int m;
	int r;
	int c;
	int d;
	char *map;

	scanf("%d %d\n%d %d %d", &n, &m, &r, &c, &d);
	map = (char *) malloc((n * (m <<= 1) + 1) * sizeof(char));
	scanf(" %[^2]", map);
	printf("%d", robot_clean(m, r * m + (c << 1), d, map));
	free(map);
	return 0;
}