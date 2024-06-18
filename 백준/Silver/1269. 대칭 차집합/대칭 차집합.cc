// https://www.acmicpc.net/problem/1269
#include <bits/stdc++.h>

using namespace std;

int n,m;
unordered_set<int> a;
unordered_set<int> b;

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> m;
    for(int i =0;i<n;i++)
    {
        int tmp;
        cin >> tmp;
        a.insert(tmp);
    }

    for(int i =0;i<m;i++)
    {
        int tmp;
        cin >> tmp;
        b.insert(tmp);
    }
    int cnt = 0;
    for(auto e : a)
    {
        auto it = b.find(e);
        if(it == b.end())
            cnt++;
    }

    for(auto e : b)
    {
        auto it = a.find(e);
        if(it == a.end())
            cnt++;
    }

    cout << cnt;
    
}