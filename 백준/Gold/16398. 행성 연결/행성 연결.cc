//https://www.acmicpc.net/problem/16398
#include <bits/stdc++.h>
using namespace std;

int n;
using ll = long long;

vector<int> p(1001, -1);



int find(int v)
{
	if (p[v] <0)
		return v;

	return p[v] = find(p[v]);
}

bool is_diff_group(int u, int v) {
	u = find(u); v = find(v);
	if (u == v) return 0;
	if (p[u] == p[v]) p[u]--;
	if (p[u] < p[v]) p[v] = u;
	else p[u] = v;
	return 1;
}

vector<tuple<int, int, int>> edges;

int main()
{
	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> n;

	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++)
		{
			int cost;
			cin >> cost;
			if (i > j)
				edges.push_back({cost, i, j });
		}

	sort(edges.begin(), edges.end());

	int cnt = 0;
	ll ans = 0;

	
	for (auto edge : edges)
	{
		int a, b, cost;
		tie(cost, a, b) = edge;

		if (!is_diff_group(a, b))
			continue;
		ans += cost;
		cnt++;
		if (cnt == n - 1)
			break;
	}

	cout << ans;


}