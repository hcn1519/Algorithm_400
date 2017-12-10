//
//  main.swift
//  QuickSort
//
//  Created by 홍창남 on 2017. 12. 10..
//  Copyright © 2017년 홍창남. All rights reserved.
//

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

var input = Array(repeatElement(0, count: n))

for i in input.indices {
    input[i] = ri.readInt()
}

let pivot = input[0]
var pivotIndex = 1

var left = [Int]()
var right = [Int]()

for i in 1..<input.count {
    if input[i] < pivot {
        left.append(input[i])
    }
    if input[i] >= pivot {
        right.append(input[i])
    }
}

for i in left {
    print(i, terminator: " ")
}

print(pivot, terminator: " ")

for i in right {
    print(i, terminator: " ")
}

print("")

