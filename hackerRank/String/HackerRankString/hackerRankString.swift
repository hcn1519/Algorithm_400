import Foundation

let n = Int(readLine()!)!

var inputs = [String]()

for _ in 1...n {
    let input = readLine()!
    inputs.append(input)
}


func checkIsInThere(member: String, base: String) -> Bool {
    if member.count > base.count {
        return false
    }
    var idx = 0
    for item in base {
        let memberIdx = member.index(member.startIndex, offsetBy: idx)
        if member[memberIdx] == item {
            idx += 1
        }
    }
    return idx == member.count ? true : false
}

inputs.forEach {
    checkIsInThere(member: "hackerrank", base: $0) ? print("YES") : print("NO")
}

