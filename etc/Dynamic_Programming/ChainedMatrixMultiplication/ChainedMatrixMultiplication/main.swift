//
//  main.swift
//  ChainedMatrixMultiplication
//
//  Created by 홍창남 on 2017. 12. 2..
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

/*
인풋 형태
4
20 2
2 30
30 12
12 8
*/
var ri = ReadInput()
let n = ri.readInt()

// d: 행렬의 XY 값
var d: [Int] = []

for i in 1...n {
    let input = ri.readLineToArray().map { Int($0)! }
    let row = input[0]

    if i == n {
        let col = input[1]
        d.append(row)
        d.append(col)
    } else {
        d.append(row)
    }
}

// m(i, j) 매트릭스 초기화, m[n][n] 사이즈
var m = Array(repeatElement(Array(repeatElement(0, count: n+1)), count: n+1))
var x = Array(repeatElement(Array(repeatElement(0, count: n+1)), count: n+1))


let BIGNUM = 100000000000
var i = 1
var g = 1
while g <= n-1 {

    i = 1
    while i <= n - g {
        let j = i + g
        print("(\(i), \(j))")

        m[i][j] = BIGNUM

        var k = i
        while k <= j-1 {
            let tmp = m[i][k] + m[k+1][j] + d[i-1]*d[k]*d[j]

            if tmp < m[i][j] {
                m[i][j] = tmp
                x[i][j] = k
            }
            k += 1
        }
        i += 1
    }
    g += 1
}

print(m[1][n])

