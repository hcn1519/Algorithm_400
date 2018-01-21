import Foundation

let input = readLine()!


func getResult(input: String) {
    var count: [String: Int] = [:]

    input.forEach {
        let k = String($0).capitalized

        if let value = count[k] {
            count.updateValue(value + 1, forKey: k)
        } else {
            count[k] = 1
        }
    }

    var maxChar = ""
    let max = count.reduce(0) { (acc: Int, cur: (String, Int)) in
        if acc < cur.1 {
            maxChar = cur.0
            return cur.1
        }
        return acc
    }

    let maxTimes = count.reduce(0) { (acc: Int, cur: (String, Int)) in
        return max == cur.1 ? acc + 1 : acc
    }

    if maxTimes == 1 {
        print(maxChar)
    } else {
        print("?")
    }
}


getResult(input: input)

