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

    // Double 데이터 읽기
    public mutating func readDouble() -> Double {
        guard let result = Double(self.read()) else {
            fatalError("Double형 데이터가 아닙니다.")
        }

        return result
    }
}

var ri = ReadInput()

let n = ri.readInt()

var grade = [Int]()

for _ in 1...n {
    let temp = ri.readInt()
    grade.append(temp)
}

func roundGrade(score: Int) -> Int {
    if score < 38 {
        return score
    }

    let val = score / 5
    let rounded = (val + 1) * 5

    if rounded - score < 3 {
        return rounded
    } else {
        return score
    }
}

let roundedGrade = grade.map {
    return roundGrade(score: $0)
}

roundedGrade.forEach {
    print($0)
}

