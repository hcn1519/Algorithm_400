import Foundation

let n = Int(readLine()!)!

func prisonBreak(n: Int) -> Int {
    var arr = Array(repeating: true, count: n+1)

    for i in 2...n {
        for (j, _) in arr.enumerated() {
            if j != 0 && j % i == 0 {
                arr[j] = !arr[j]
            }
        }
    }

    return arr.reduce(-1) { (acc, cur) in
        return cur ? acc + 1 : acc
    }
}

for _ in 1...n {
    let x = Int(readLine()!)!
    print(prisonBreak(n: x))
}

