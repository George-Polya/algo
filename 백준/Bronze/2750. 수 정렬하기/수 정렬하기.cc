// https://www.acmicpc.net/problem/2750

#include <bits/stdc++.h>
using namespace std;

int n;
int arr[1005];

int main()
{
	cin >> n;
	for (int i = 0; i < n; i++)
		cin >> arr[i];

	sort(arr, arr + n);
	for (int i = 0; i < n; i++)
		cout << arr[i] << '\n';
}