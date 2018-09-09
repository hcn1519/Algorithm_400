//
//  main.swift
//  1_NewsClustering
//
//  Created by 홍창남 on 2018. 9. 9..
//  Copyright © 2018년 홍창남. All rights reserved.
//

import Foundation

extension NSRegularExpression {
    convenience init(_ pattern: String) {
        do {
            try self.init(pattern: pattern)
        } catch {
            preconditionFailure("Illegal regular expression: \(pattern).")
        }
    }
}
extension NSRegularExpression {
    func matches(_ string: String) -> Bool {
        let range = NSRange(location: 0, length: string.utf16.count)
        return firstMatch(in: string, options: [], range: range) != nil
    }
}

func splitStr(str: String) -> [String] {

    return str.enumerated().compactMap { (idx, char) in
        if idx != str.count - 1 {
            let nextStrIdx = str.index(after: str.index(str.startIndex, offsetBy: idx))
            let nextChar = str[nextStrIdx]

            return String(char).lowercased() + String(nextChar).lowercased()
        }
        return nil
    }
}

func trimArray(strArr: [String]) -> [String] {
    let regex = NSRegularExpression("[A-Za-z][A-Za-z]")

    return strArr.filter {
        return regex.matches($0)
    }
}
func numberOfElement(pair: [String]) -> [String: Int] {
    if pair.count == 0 {
        return [:]
    }
    let sortedPair = pair.sorted()

    var count = 0
    var newPair = sortedPair.first!
    var resultDict: [String: Int] = [:]

    sortedPair.enumerated().forEach {
        if $0.element == newPair {
            count += 1
        } else {
            resultDict[newPair] = count
            newPair = $0.element
            count = 1
        }
        if $0.offset == sortedPair.count - 1 {
            resultDict[$0.element] = count
        }
    }
    return resultDict
}

func 교집합(pair1: [String], pair2: [String]) -> [(String, Int)] {

    let set1 = Set(pair1)
    let set2 = Set(pair2)

    let intersection = set1.intersection(set2)

    let number1Pair = numberOfElement(pair: pair1)
    let number2Pair = numberOfElement(pair: pair2)

    return intersection.compactMap {
        if let value1 = number1Pair[$0], let value2 = number2Pair[$0] {
            return ($0, min(value1, value2))
        }
        return nil
    }
}

func 합집합(pair1: [String], pair2: [String]) -> [(String, Int)] {

    let set1 = Set(pair1)
    let set2 = Set(pair2)

    let union = set1.union(set2)

    let number1Pair = numberOfElement(pair: pair1)
    let number2Pair = numberOfElement(pair: pair2)

    return union.compactMap {
        var num = -1
        if let value1 = number1Pair[$0] {
            num = value1
        }
        if let value2 = number2Pair[$0] {
            if num < value2 {
                num = value2
            }
        }
        return ($0, num)
    }
}
func jakad(intersection: [(String, Int)], union: [(String, Int)]) -> Double {
    if intersection.count == 0 && union.count == 0 {
        return 1.0
    }
    let 분자 = intersection.reduce(0) { acc, cur in
        return acc + cur.1
    }
    let 분모 = union.reduce(0) { acc, cur in
        return acc + cur.1
    }
    return Double(분자)/Double(분모)
}

func solution(_ str1:String, _ str2:String) -> Int {
    let pair1 = splitStr(str: str1)
    let pair2 = splitStr(str: str2)
    let trimmedPair1 = trimArray(strArr: pair1)
    let trimmedPair2 = trimArray(strArr: pair2)

    let intersection = 교집합(pair1: trimmedPair1, pair2: trimmedPair2)
    let union = 합집합(pair1: trimmedPair1, pair2: trimmedPair2)

    let result = jakad(intersection: intersection, union: union)
    return Int(result * 65536)
}

print(solution("FRANCE", "french"))
print(solution("handshake", "shake hands"))
print(solution("aa1+aa2", "AAAA12"))
print(solution("E=M*C^2", "e=m*c^2"))
