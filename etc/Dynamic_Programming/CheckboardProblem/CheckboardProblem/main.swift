//
//  main.swift
//  CheckboardProblem
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
 조건 : nXn 행렬
 비용 : c[i][j] 테이블
 최소 비용이 드는 path 찾기
 */

// 1. 인풋값 받아오기 c[][]
// 인풋 유형
/*
5
5
7 3 6 5 1
5 9 10 1 3
8 10 8 9 3
15 14 9 4 7
20 16 8 11 12
 */
var ri = ReadInput()
let i = ri.readInt()
let j = ri.readInt()

var c: [[Int]] = Array(repeatElement(Array(repeatElement(0, count: j)), count: i))

for t in 0..<i {
    let row = ri.readLineToArray().map { Int($0)! }
    c[t] = row
}

// 2-1. 결과값을 저장할 공간 생성 q[][]
// 2-2. 방향 저장할 공간 생성 p[][]
var q: [[Int]] = Array(repeatElement(Array(repeatElement(0, count: j)), count: i))
var p: [[Int]] = Array(repeatElement(Array(repeatElement(0, count: j)), count: i))


// 3. 루프 돌기
// 점화식 적용
//
func minArrIndex(input: [Int]) -> Int {
    let result = input.enumerated().reduce((0, input[0])) { (accumulate, current) in
        return accumulate.1 > current.element ? current : accumulate
    }

    return result.0
}

for (idx, val) in q.enumerated() {
    for (k, v) in q[idx].enumerated() {
        if idx == 0 {
            q[idx][k] = c[idx][k]
            p[idx][k] = 0
        } else {
            var arr = [Int]()
            var t = 0
            var path = 0
            if k == 0 {
                arr = [q[idx-1][k], q[idx-1][k+1]]
                t = minArrIndex(input: arr)

                path = t == 0 ? 0 : 1
            } else if k == q[idx].count - 1 {
                arr = [q[idx-1][k-1], q[idx-1][k]]
                t = minArrIndex(input: arr)

                path = t == 0 ? -1 : 0
            } else {
                arr = [q[idx-1][k-1], q[idx-1][k], q[idx-1][k+1]]
                t = minArrIndex(input: arr)

                if t == 0 {
                    path = -1
                } else if t == 1 {
                    path = 0
                } else {
                    path = 1
                }
            }

            q[idx][k] = c[idx][k] + arr[t]
            p[idx][k] = path
        }
    }
}

// 경로 출력
func printPath(length: Int, q: [[Int]], p: [[Int]]) {
    let lastMinIndex = minArrIndex(input: q[length-1])

    var point: (Int, Int) = (length - 1, lastMinIndex)
    var j = lastMinIndex
    for idx in 0..<length {
        print("a(\(point.0 + 1), \(point.1 + 1))")

        switch p[length-idx-1][j] {
        case -1:
            j -= 1
        case 1:
            j += 1
        default:
            break
        }
        point = (length-idx-2, j)
    }
}
let resultIndex = minArrIndex(input: q[i-1])

print("최솟값 -", q[i-1][resultIndex])
print("이동 경로")
printPath(length: 5, q: q, p: p)
