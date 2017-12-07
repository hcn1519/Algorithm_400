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


struct MyBigInt {

    static func sumDigit(resultLength: Int, baseArr: [Int], subArr: [Int]) -> [Int] {
        var result = Array(repeatElement(0, count: resultLength))

        var above = 0
        for (i, digit) in baseArr.enumerated() {
            if i <= subArr.count - 1 {
                let digitSum = digit + subArr[i] + above

                result[i] = (digitSum % 10)

                if digitSum >= 10 {
                    above = 1
                } else {
                    above = 0
                }
            } else {
                result[i] = digit + above

                above = 0
            }

        }
        return result
    }

    static func removeZero(arr: [Int]) -> [Int] {
        var result = arr

        for item in result {
            if item == 0 {
                result.removeFirst()
            } else {
                break
            }
        }
        return result
    }

    static func sum(lhs: String, rhs: String) -> String {
        let leftArr = lhs.reversed().map { Int(String($0))! }
        let rightArr = rhs.reversed().map { Int(String($0))! }

        let leftLength = leftArr.count
        let rightLength = rightArr.count

        var length = 0
        var result = [Int]()
        if leftLength < rightLength {
            length = rightLength + 1

            result = sumDigit(resultLength: length, baseArr: rightArr, subArr: leftArr)
        } else {
            length = leftLength + 1

            result = sumDigit(resultLength: length, baseArr: leftArr, subArr: rightArr)

        }

        let finalResult = removeZero(arr: result.reversed()).reduce("") { (acc, current) in
            return acc + String(current)
        }

        return finalResult == "" ? "0" : finalResult
    }

    static func multiple(lhs: String, rhs: String) -> String {
        let leftArr = lhs.reversed().map { Int(String($0))! }
        let rightArr = rhs.reversed().map { Int(String($0))! }

        var result = Array(repeating: 0, count: leftArr.count + rightArr.count)

        for leftIndex in 0..<leftArr.count {
            for rightIndex in 0..<rightArr.count {

                let resultIndex = leftIndex + rightIndex

                result[resultIndex] = leftArr[leftIndex] * rightArr[rightIndex] + (resultIndex >= result.count ? 0 : result[resultIndex])
                if result[resultIndex] > 9 {
                    result[resultIndex + 1] = (result[resultIndex] / 10) + (resultIndex+1 >= result.count ? 0 : result[resultIndex + 1])
                    result[resultIndex] -= (result[resultIndex] / 10) * 10
                }
            }
        }

        let finalResult = removeZero(arr: result.reversed()).reduce("") { (acc, current) in
            return acc + String(current)
        }

        return finalResult == "" ? "0" : finalResult

    }
}

func modifiedFibonacci(_ t1: String,_ t2: String) -> String {
    return MyBigInt.sum(lhs: t1, rhs: MyBigInt.multiple(lhs: t2, rhs: t2))
}

var ri = ReadInput()

let t1 = ri.readInt()
let t2 = ri.readInt()
let n = ri.readInt()

var fiboArr = [String]()

fiboArr.append(String(t1))
fiboArr.append(String(t2))

for i in 2..<n {
    fiboArr.append(modifiedFibonacci(fiboArr[i-2], fiboArr[i-1]))
}

print(fiboArr.last!)

