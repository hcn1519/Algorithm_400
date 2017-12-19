//
//  main.swift
//  LongestPalindromicSubsequence
//
//  Created by 홍창남 on 2017. 12. 17..
//  Copyright © 2017년 홍창남. All rights reserved.
//

import Foundation

let str = readLine()!


func print2DArr<T>(array: [[T]]) {
    array.forEach { print($0) }
}

func checkIsPalindrome(str: String) -> Bool {
    if str.count == 1 {
        return true
    }

    var result = true
    str.enumerated().forEach {
        let offset = ($0 + 1) * -1
        let index = str.index(str.endIndex, offsetBy: offset)
        if $1 != str[index] {
            result = false
        }
    }

    return result
}

// BABAB
// BBABCBCAB
var p = Array(repeatElement(Array(repeatElement(0, count: str.count+1)), count: str.count+1))

func palindromeLength(str: String) {

    var reversedStr = ""
    for i in str.reversed() {
        reversedStr += String(i)
    }

    var i = 0
    var j = 0
    while i <= str.count {
        j = 0
        while j <= reversedStr.count {
            if i == 0 || j == 0 {
                p[i][j] = 0
                j += 1
                continue
            }

            let indexI = str.index(str.startIndex, offsetBy: i-1)
            let indexJ = reversedStr.index(reversedStr.startIndex, offsetBy: j-1)

            if str[indexI] == reversedStr[indexJ] {
                p[i][j] = p[i-1][j-1] + 1
            } else {
                p[i][j] = max(p[i-1][j], p[i][j-1])
            }
            j += 1
        }
        i += 1
    }
}

func palindromeLength2(str: String) {

     var j = 0
     var k = 2
     while k <= str.count {
         var i = 0
         while i < str.count - k + 1 {
             j = i + k - 1
             let indexI = str.index(str.startIndex, offsetBy: i)
             let indexJ = str.index(str.startIndex, offsetBy: j)

             if str[indexI] == str[indexJ] && k == 2 {
                 p[i][j] = 2
             } else if str[indexI] == str[indexJ] {
                 p[i][j] = p[i+1][j-1] + 2
             } else {
                 if p[i+1][j] >= p[i][j-1] {
                     p[i][j] = p[i+1][j]
                 } else {
                     p[i][j] = p[i][j-1]
                 }
             }
             i += 1
         }
         k += 1
     }

}
func LPSString(str: String) -> String {

    var reversedStr = ""
    for i in str.reversed() {
        reversedStr += String(i)
    }

    var i = str.count
    var j = str.count

    var result = ""
    while i > 0 && j > 0 {

        let index1 = str.index(str.startIndex, offsetBy: i-1)
        let index2 = str.index(reversedStr.startIndex, offsetBy: j-1)

        if str[index1] == reversedStr[index2] {
            result += String(reversedStr[index2])
            i -= 1
            j -= 1
        } else if p[i-1][j] > p[i][j-1] {
            i -= 1
        } else {
            j -= 1
        }
    }
    return result
}

palindromeLength(str: str)

print(p[str.count][str.count])

print(LPSString(str: str))
