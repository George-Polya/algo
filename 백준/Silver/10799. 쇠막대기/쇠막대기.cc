// https://www.acmicpc.net/problem/10799

#include <bits/stdc++.h>
using namespace std;

int main()
{
	ios::sync_with_stdio(0);
	cin.tie(0);

	string str;
	cin >> str;
	stack<char> stk;
	long long ans = 0;

	for (int i = 0; i < str.length(); i++)
	{
		if (str[i] == '(')
			stk.push(str[i]);
		else
		{
			if (str[i - 1] == '(')
			{
				stk.pop();
				ans += stk.size();
			}
			else
			{
				stk.pop();
				ans++;
			}
		}
	}

	cout << ans << '\n';
	
}