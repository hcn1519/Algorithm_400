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

var arr = [String]()
for _ in 1...n {
    let input = readLine()!
    arr.append(input)
}

let q = ri.readInt()

var arr2 = [String]()
for _ in 1...q {
    let input = readLine()!
    arr2.append(input)
}

let result = arr2.map { (query) in
    return arr.reduce(0) { (acc, cur) in
        return cur == query ? acc + 1 : acc
    }
}

result.forEach {
    print($0)
}
