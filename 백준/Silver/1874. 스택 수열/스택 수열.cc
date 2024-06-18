// https://www.acmicpc.net/problem/1874
#include <bits/stdc++.h>

using namespace std;

int main()
{
	int n;
	cin >> n;
	stack<int> stk = {};
	int cnt = 1;
	string ans;
	while (n-- > 0)
	{
		int t;
		cin >> t;
		while (cnt <= t)
		{
			stk.push(cnt++);
			ans += "+\n";

		}

		if (stk.top() != t)
		{
			cout << "NO\n";
			return 0;
		}
		stk.pop();
		ans += "-\n";
	}

	cout << ans;

	
}