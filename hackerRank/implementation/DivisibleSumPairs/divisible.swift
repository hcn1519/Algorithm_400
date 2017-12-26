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

let n = ri.readInt()
let k = ri.readInt()

var arr = [Int]()
for _ in 1...n {
    let input = ri.readInt()
    arr.append(input)
}

var result = 0
for (i,v) in arr.enumerated() {
    var j = i + 1
    while j < arr.count {

        let sum = v + arr[j]

        if sum % k == 0 {
            result += 1
        }
        j+=1
    }
}

print(result)
