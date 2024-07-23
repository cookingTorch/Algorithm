#include <stdio.h>
#include <stdlib.h>

#define MOD		1000000000
#define DIGIT	10
#define EMPTY	-1

int _n;
long long **_stair;
long long ****_dp;

int
get_min(int val1, int val2)
{
	return val1 < val2 ? val1 : val2;
}

int
get_max(int val1, int val2)
{
	return val1 > val2 ? val1 : val2;
}

long long
get_stair(int idx, int num)
{
	long long sum;

	if (_stair[idx][num] != EMPTY)
		return _stair[idx][num];
	if (idx == _n - 1)
		return _stair[idx][num] = 1;
	sum = 0;
	if (num > 0)
		sum = (sum + get_stair(idx + 1, num - 1)) % MOD;
	if (num < 9)
		sum = (sum + get_stair(idx + 1, num + 1)) % MOD;
	return _stair[idx][num] = sum;
}

long long
get_dp(int idx, int max, int min, int num)
{
	long long sum;

	if (_dp[idx][max][min][num] != EMPTY)
		return _dp[idx][max][min][num];
	if (max == 9 && min == 0)
		return _dp[idx][max][min][num] = 0;
	if (idx == _n - 1)
		return _dp[idx][max][min][num] = 1;
	sum = 0;
	if (num > 0)
		sum = (sum + get_dp(idx + 1, max, get_min(min, num - 1), num - 1)) % MOD;
	if (num < 9)
		sum = (sum + get_dp(idx + 1, get_max(max, num + 1), min, num + 1)) % MOD;
	return _dp[idx][max][min][num] = sum;
}

int
main()
{
	int i;
	int j;
	int k;
	int l;
	long long sum;

	scanf("%d", &_n);
	_dp = (long long ****) malloc(_n * sizeof(long long ***));
	for (i = 0; i < _n; i++) {
		_dp[i] = (long long ***) malloc(DIGIT * sizeof(long long **));
		for (j = 0; j < DIGIT; j++) {
			_dp[i][j] = (long long **) malloc((j + 1) * sizeof(long long *));
			for (k = 0; k <= j; k++) {
				_dp[i][j][k] = (long long *) malloc(DIGIT * sizeof(long long));
				for (l = 0; l < DIGIT; l++)
					_dp[i][j][k][l] = EMPTY;
			}
		}
	}
	_stair = (long long **) malloc(_n * sizeof(long long *));
	for (i = 0; i < _n; i++) {
		_stair[i] = (long long *) malloc(DIGIT * sizeof(long long));
		for (j = 0; j < DIGIT; j++)
			_stair[i][j] = EMPTY;
	}
	sum = 0;
	for (i = 1; i < DIGIT; i++)
		sum = (sum + get_stair(0, i)) % MOD;
	for (i = 1; i < DIGIT; i++)
		sum = (sum - get_dp(0, i, i, i)) % MOD;
	if (sum < 0)
		sum += MOD;
	printf("%d", (int) sum);
	return 0;
}