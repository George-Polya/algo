// https://www.acmicpc.net/problem/1918

#include <bits/stdc++.h>
using namespace std;

string str;

int main()
{
	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> str;
	stack<char> op;

	for (auto c : str)
	{
		if (c >= 'A' && c <= 'Z')
			cout << c;
		else
		{
			if (c == '(')
				op.push(c);
			else if (c == '*' || c == '/')
			{
				while (!op.empty() && (op.top() == '*' || op.top() == '/'))
				{
					cout << op.top();
					op.pop();
				}

				op.push(c);
			}
			else if (c == '+' || c == '-')
			{
				while (!op.empty() && op.top() != '(')
				{
					cout << op.top();
					op.pop();
				}

				op.push(c);
			}
			else if (c == ')')
			{
				while (!op.empty() && op.top() != '(')
				{
					cout << op.top();
					op.pop();
				}
				op.pop();
			}
		}
	}

	while (!op.empty())
	{
		cout << op.top();
		op.pop();
	}
}