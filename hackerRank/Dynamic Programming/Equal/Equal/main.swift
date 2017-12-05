//
//  main.swift
//  Equal
//
//  Created by 홍창남 on 2017. 12. 6..
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

func countTime(min end: Int, start: Int) -> Int {
    var count = 0
    var sub = start - end

    while sub > 0 {
        if sub >= 5 {
            count += 1
            sub -= 5
        } else if sub >= 2 {
            count += 1
            sub -= 2
        } else {
            count += 1
            sub -= 1
        }
    }
    return count
}

var ri = ReadInput()
let n = ri.readInt()

for _ in 1...n {
    let count = ri.readInt()
    var arr = [Int]()
    for _ in 1...count {
        let choco = ri.readInt()
        arr.append(choco)
    }

    let min = arr.reduce(arr[0]) { (acc, current) in
        return acc < current ? acc : current
    }

    var resultMin = INT_MAX
    for i in min-3...min {
        let result = arr.reduce(0) { (acc, current) in
            return acc + countTime(min: i, start: current)
        }
        if resultMin > result {
            resultMin = Int32(result)
        }
    }

    print(resultMin)
}
