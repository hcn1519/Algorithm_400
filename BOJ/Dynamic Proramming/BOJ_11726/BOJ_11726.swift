import Foundation

let n = Int(readLine()!)!

func tile(n: Int) -> Int {
    var dp = Array(repeating: 0, count: n + 1)

    dp[0] = 1
    dp[1] = 2


    for i in dp.indices {
        if i == 0 || i == 1 {
            continue
        }

        dp[i] = dp[i-1] + dp[i-2]
        dp[i] %= 10007
    }
    return dp[n-1]
}

print(tile(n: n))
