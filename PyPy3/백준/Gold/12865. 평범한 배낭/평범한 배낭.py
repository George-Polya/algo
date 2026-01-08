if __name__ == "__main__":
    N, K = map(int,input().split())
    
    weights = [0]
    values = [0]
    
    for i in range(1,N+1):
        w,v = map(int,input().split())
        weights.append(w)
        values.append(v)
        
    INT_MIN = -float('inf')
    dp = [INT_MIN for _ in range(K+1)]
    
    dp[0] = 0
    
    for i in range(1,N+1):
        for j in range(K,-1,-1):
            if j - weights[i] >=0:
                if dp[j-weights[i]] == INT_MIN:
                    continue
                    
                dp[j] = max(dp[j], dp[j-weights[i]] + values[i])
                
    ans = -1
    for i in range(K+1):
        ans = max(ans, dp[i])
    print(ans)
    