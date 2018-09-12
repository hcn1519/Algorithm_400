
//
//  main.swift
//  3_nCountGame
//
//  Created by 홍창남 on 2018. 9. 12..
//  Copyright © 2018년 홍창남. All rights reserved.
//

import Foundation

func getAbove10(tmp: Int, n: Int) -> String {

    if tmp%n == 10 {
        return "A"
    } else if tmp%n == 11 {
        return "B"
    } else if tmp%n == 12 {
        return "C"
    } else if tmp%n == 13 {
        return "D"
    } else if tmp%n == 14 {
        return "E"
    } else if tmp%n == 15 {
        return "F"
    }
    return String(tmp%n)
}

func convert(n: Int, num: Int) -> String {
    if n == 10 || num == 0 {
        return String(num)
    }

    var result: String = ""
    var tmp = num
    if n > 10 {
        while tmp >= n {
            let value = getAbove10(tmp: tmp, n: n)
            result += value
            tmp /= n
        }
        if tmp != 0 {
            let value = getAbove10(tmp: tmp, n: n)
            result += value
        }
        return String(result.reversed())
    } else {
        while tmp >= n {
            result += String(tmp%n)
            tmp /= n
        }
        if tmp != 0 {
            result += String(tmp)
        }
        return String(result.reversed())
    }
}

func nCountStr(n: Int, t: Int, m: Int, turn: Int) -> String {

    let strNum = (0..<t*m).reduce("") { acc, cur in
        return acc + convert(n: n, num: cur)
    }

    var tmp = turn
    var result: String = ""

    var count = 0
    for (idx, char) in strNum.enumerated() {
        if idx == tmp - 1 {
            result.append(String(char))
            tmp += m
            count += 1
        }

        if count == t {
            break
        }
    }

    return result
}

func solution(_ n:Int, _ t:Int, _ m:Int, _ p:Int) -> String {
    return nCountStr(n: n, t: t, m: m, turn: p)
}

//print(convert(n: 2, num: 100))
//print(convert(n: 3, num: 100))
//print(convert(n: 10, num: 100))
//print(convert(n: 16, num: 20523490))

print(solution(2, 4, 2, 1))
print(solution(16, 16, 2, 1))
print(solution(16, 16, 2, 2))
