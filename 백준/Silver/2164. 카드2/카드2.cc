// https://www.acmicpc.net/problem/2164

#include <bits/stdc++.h>
using namespace std;

int main()
{
	int n;
	cin >> n;

	queue<int> q;
	for (int i = 1; i <= n; i++)
		q.push(i);

	while (true)
	{
		if (q.size() == 1)
			break;

		q.pop();

		int temp = q.front();
		q.pop();
		q.push(temp);
	}
	cout << q.front();
}