//
//  main.swift
//  LongestCommonSubsequence
//
//  Created by 홍창남 on 2017. 12. 16..
//  Copyright © 2017년 홍창남. All rights reserved.
//

/*
ABCBDAB
BDCABA
 */
import Foundation

enum Direction {
    case left, top, topLeft, unknown
}

let string1 = readLine()!
let string2 = readLine()!

let m = string1.count
let n = string2.count

var c = Array(repeatElement(Array(repeatElement(0, count: n+1)), count: m+1))
var d = Array(repeatElement(Array(repeatElement(Direction.unknown, count: n+1)), count: m+1))

func print2DArr<T>(array: [[T]]) {
    array.forEach { print($0) }
}

func LCSLength(string1: String, string2: String) -> Int {

    let s1 = " " + string1
    let s2 = " " + string2

    for (i, xi) in s1.enumerated() {
        for (j, yj) in s2.enumerated() {
            if i == 0 || j == 0 {
                continue
            }

            if xi == yj {
                c[i][j] = c[i-1][j-1] + 1
                d[i][j] = Direction.topLeft
            } else {
                if c[i-1][j] >= c[i][j-1] {
                    c[i][j] = c[i-1][j]
                    d[i][j] = Direction.top
                } else {
                    c[i][j] = c[i][j-1]
                    d[i][j] = Direction.left
                }
            }
        }
    }
    return c[m][n]
}

func LCSString(d: [[Direction]], string1: String, i: Int, j: Int) {
    if i == 0 || j == 0 {
        return
    }

    if d[i][j] == Direction.topLeft {
        LCSString(d: d, string1: string1, i: i-1, j: j-1)
        let index = string1.index(string1.startIndex, offsetBy: i)
        print(string1[index], terminator: "")
    } else if d[i][j] == Direction.left {
        LCSString(d: d, string1: string1, i: i, j: j-1)
    } else if d[i][j] == Direction.top {
        LCSString(d: d, string1: string1, i: i-1, j: j)
    }
}

print(LCSLength(string1: string1, string2: string2))

LCSString(d: d, string1: " " + string1, i: m, j: n)
print("")

