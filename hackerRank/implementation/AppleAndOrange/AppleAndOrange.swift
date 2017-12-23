
import Foundation

struct ReadInput {

    private var currentIndex: Int = 0
    private var inputArray: [String] = []

    // 데이터를 배열로 변환
    public mutating func readLineToArray() -> [String] {

        guard let result = readLine() else {
            return []
        }

        return result.components(separatedBy: " ")
    }

    // 띄어쓰기 단위로 String 읽기
    public mutating func read() -> String {
        if inputArray.count == 0 {
            inputArray = self.readLineToArray()
        }
        let result = inputArray[inputArray.index(after: currentIndex-1)]
        currentIndex += 1

        if currentIndex == inputArray.count {
            self.inputArray.removeAll()
            self.currentIndex = 0
        }

        return result
    }

    // Int 데이터 읽기
    public mutating func readInt() -> Int {
        guard let result = Int(self.read()) else {
            fatalError("Int형 데이터가 아닙니다.")
        }

        return result
    }
}

var ri = ReadInput()

let s = ri.readInt()
let t = ri.readInt()

let appleTree = ri.readInt()
let orangeTree = ri.readInt()

let countOfApple = ri.readInt()
let countOfOrange = ri.readInt()

let applesDistance = ri.readLineToArray().map { Int($0)! }
let orangesDistance = ri.readLineToArray().map { Int($0)! }

let apples = applesDistance.map { return appleTree + $0 }
let oranges = orangesDistance.map { return orangeTree + $0 }

let appleInBound = apples.filter {
    return ($0 >= s && $0 <= t)
}

let orangeInBound = oranges.filter {
    return ($0 >= s && $0 <= t)
}

print(appleInBound.count)
print(orangeInBound.count)

