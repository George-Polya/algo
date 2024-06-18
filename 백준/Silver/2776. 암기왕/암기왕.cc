// https://www.acmicpc.net/problem/2776
#include <bits/stdc++.h>
using namespace std;

int tc, n, m;



bool inV1(int target,vector<int> &v)
{
    int left = 0;
    int right = n-1;
    while(left <= right)
    {
        int mid = (left + right)/ 2;

        if(target == v[mid])
            return true;
        else if(target < v[mid])
            right = mid-1;
        else if(target > v[mid])
            left = mid + 1;

    }

    return false;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin>>tc;
    for(int t = 0; t< tc; t++)
    {
        cin >> n;
        vector<int> v1(n);
        for(int i = 0; i<n;i++)
            cin>>v1[i];
        
        sort(v1.begin(), v1.end());

        cin >> m;
        for(int i = 0; i < m;i++)
        {
            int num;
            cin >> num;
            cout << inV1(num, v1)<<"\n";
        }
    }
}