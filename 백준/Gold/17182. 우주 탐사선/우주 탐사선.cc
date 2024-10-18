//https://www.acmicpc.net/problem/17182
#include <bits/stdc++.h>
using namespace std;

int n, k;
int matrix[11][11];

bool visited[11];
int recur(int start, int depth)
{
	if (depth == 1)
		return 0;

	visited[start] = true;
	int result = 0x3f3f3f3f;
	
	for (int i = 0; i < n; i++)
	{
		if (!visited[i])
			result = min(result, recur(i, depth - 1) + matrix[start][i]);
	}

	visited[start] = false;
	return result;
}

int main()
{
	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> n >> k;
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			cin >> matrix[i][j];

	
	for (int k = 0; k < n; k++)
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				matrix[i][j] = min(matrix[i][j], matrix[i][k] + matrix[k][j]);


	/*for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
			cout << matrix[i][j] << ' ';
		cout << '\n';
	}*/

	int result = recur(k, n);
	cout << result;
}