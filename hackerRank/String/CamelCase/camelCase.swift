import Foundation

let str = readLine()!

let result = str.reduce(0) { (acc, cur) in
    let temp = String(cur)
    return temp == String(cur).lowercased() ? acc : acc + 1
}

print(result + 1)
