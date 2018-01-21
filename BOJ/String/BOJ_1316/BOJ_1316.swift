import Foundation

let n = Int(readLine()!)!

func getGroupWords(word: String) -> Bool {

    var count: [String: Int] = [:]

    for (index, value) in word.enumerated() {
        let v = String(value)

        if let _ = count[v] {

            if word[word.index(word.startIndex, offsetBy: index-1)] != value {
                return false
            }
        } else {
            count[v] = 1
        }
    }

    return true
}

var result = 0
for _ in 1...n {
  let word = readLine()!
  if getGroupWords(word: word) {
    result += 1
  }
}
print(result)
