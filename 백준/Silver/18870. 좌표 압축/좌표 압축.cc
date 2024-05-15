// https://www.acmicpc.net/problem/18870
#include <bits/stdc++.h>
using namespace std;


int n;
int x[1000005];
vector<int> tmp, uni;
int main()
{
	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> n;
	for (int i = 0; i < n; i++)
	{
		cin >> x[i];
		tmp.push_back(x[i]);
	}

	sort(tmp.begin(), tmp.end());

	//for (int i = 0; i < n; i++)
	//{
	//	if (i == 0 || tmp[i] == tmp[i - 1])
	//		uni.push_back(tmp[i]);
	//}

	tmp.erase(unique(tmp.begin(), tmp.end()), tmp.end());

	for (int i = 0; i < n; i++)
		cout << lower_bound(tmp.begin(), tmp.end(), x[i]) - tmp.begin() << ' ';
}