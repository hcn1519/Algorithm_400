
import Foundation

func getBinaryArray(num: Int, length: Int) -> [Int] {
    var mod = num
    var arr = [Int]()

    while mod > 1 {
        let value = mod % 2
        arr.append(value)

        mod /= 2
    }
    if mod == 1 {
        arr.append(1)
    }

    while length != arr.count {
        arr.append(0)
    }
    return arr.reversed()
}

func merge(arr1: [[Int]], arr2: [[Int]]) -> [[Int]] {

    return zip(arr1, arr2).map { (arg) -> [Int] in
        let (a1, a2) = arg

        return zip(a1, a2).map { a, b in
            return a == 1 || b == 1 ? 1 : 0
        }
    }
}

func getResult(arr: [[Int]]) -> [String] {
    let result: [String] = arr.map { numArr in
        return numArr.reduce("") { acc, cur in
            return cur == 1 ? acc + "#" : acc + " "
        }
    }
    return result
}

func solution(_ n:Int, _ arr1:[Int], _ arr2:[Int]) -> [String] {

    let numArr1: [[Int]] = arr1.map {
        return getBinaryArray(num: $0, length: n)
    }
    let numArr2: [[Int]] = arr2.map {
        return getBinaryArray(num: $0, length: n)
    }

    let decodedArr = merge(arr1: numArr1, arr2: numArr2)
    return getResult(arr: decodedArr)
}
print(solution(5, [9, 20, 28, 18, 11], [30, 1, 21, 17, 28]))
print(solution(6, [46, 33, 33, 22, 31, 50], [27, 56, 19, 14, 14, 10]))
