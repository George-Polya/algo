// https://www.acmicpc.net/problem/9655
#include <bits/stdc++.h>
using namespace std;

int n;
int dp[1003];
int main()
{
	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> n;

	cout << (n % 2 ? "SK" : "CY");
}