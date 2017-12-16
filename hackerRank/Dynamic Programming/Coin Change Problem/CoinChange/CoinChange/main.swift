//
//  main.swift
//  CoinChange
//
//  Created by 홍창남 on 2017. 12. 15..
//  Copyright © 2017년 홍창남. All rights reserved.
//

// Swift 3.1
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
let m = ri.readInt()

let coinType = ri.readLineToArray().map { Int($0)! }
let coinSorted = coinType.sorted()

var result = Array(repeatElement(0, count: n+1))

result[0] = 1

func coinChange(coinType: [Int], array: [Int], n: Int) -> [Int] {
    var result = array

    for i in coinType.indices {
        var j = coinType[i]
        while j <= n {
            result[j] += result[j - coinType[i]]
            j += 1
        }
    }
    return result
}

print(coinChange(coinType: coinType, array: result, n: n).last!)

