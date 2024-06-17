// https://www.acmicpc.net/problem/1197

#include <bits/stdc++.h>

using namespace std;

int v, e;
int a, b, c;
tuple<int, int, int> edges[100003];

vector<int> p(10005, -1);

int find(int x)
{
	if (p[x] < 0)
		return x;
	return p[x] = find(p[x]);
}

bool is_diff_group(int u, int v)
{
	u = find(u);
	v = find(v);

	if (u == v)
		return false;
	if (p[u] > p[v])
		swap(u, v);

	p[u] += p[v];
	p[v] = u;
	return true;
}

int main()
{

	ios::sync_with_stdio(0);
	cin.tie(0);


	cin >> v >> e;

	for (int i = 0; i < e; i++)
	{
		cin >> a >> b >> c;
		edges[i] = {c,a,b};
	}

	sort(edges, edges + e);
	int cnt = 0;
	int answer = 0;

	for (int i = 0; i < e; i++)
	{
		int a, b, cost;
		tie(cost, a, b) = edges[i];
		if (!is_diff_group(a, b))
			continue;
		answer += cost;
		cnt++;
		if (cnt == v - 1)
			break;
	}

	cout << answer;
}