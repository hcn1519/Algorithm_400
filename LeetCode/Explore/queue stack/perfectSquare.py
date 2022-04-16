# https://leetcode.com/explore/learn/card/queue-stack/231/practical-application-queue/1371/
class Solution:
    def __init__(self):
        # 조건이 1 <= n <= 10000이므로, 100의 제곱까지만 pool 구성
        self.pool = [start ** 2 for start in range(1, 101)]
    
    # 고려해야 하는 최대 index 확인
    def findMaxIndex(self, n: int) -> int:
        firstIndex = 100
        for index, start in enumerate(self.pool):
            if n < start:
                firstIndex = index
                break
        return firstIndex
    
    def numSquares(self, n: int) -> int:
        maxIndex = self.findMaxIndex(n = n)
        dp = [10001 for i in range(n + 1)]
        dp[0] = 0
        
        # 1부터 squareNum의 최솟값을 계산해나간다.
        # 작은 수에서 사용한 값을 큰 수의 계산에 활용한다.
        for index in range(1, n + 1):
            for squareNum in self.pool:
                if index < squareNum:
                    break
                # index = 10, squareNum = 1, dp[10] = min(dp[10], dp[10 - 1] + 1) -> 2
                # index = 10, squareNum = 4, dp[10] = min(dp[10], dp[10 - 4] + 1) -> 3
                # index = 10, squareNum = 9, dp[10] = min(dp[10], dp[10 - 9] + 1) -> 2
                dp[index] = min(dp[index], dp[index - squareNum] + 1)
        
        # n = 12, dp = [0, 1, 2, 3, 1, 2, 3, 4, 2, 1, 2, 3, 3]
        return dp[-1]
        