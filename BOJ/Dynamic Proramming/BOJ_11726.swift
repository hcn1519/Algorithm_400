import Foundation

let n = Int(readLine()!)!

func tile(n: Int) -> Int {
    var dp = Array(repeating: 0, count: n + 1)

    dp[1] = 1
    dp[2] = 2

    for i in dp.indices {
        if i == 0 || i == 1 || i == 2 {
            continue
        }

        dp[i] = dp[i-1] % 10007 + dp[i-2] % 10007
    }

    return dp[n]
}

print(tile(n: n))
