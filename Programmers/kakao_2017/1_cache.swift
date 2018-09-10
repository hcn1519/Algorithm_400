//
//  main.swift
//  1_cache
//
//  Created by 홍창남 on 2018. 9. 10..
//  Copyright © 2018년 홍창남. All rights reserved.
//

import Foundation

class LRUCache {

    var cacheSize: Int
    var items: [String] = []

    init(cacheSize: Int) {
        self.cacheSize = cacheSize
    }

    var isFull: Bool {
        return items.count >= cacheSize
    }
}

func calculateTime(cache: LRUCache, cities: [String]) -> Int {
    if cache.cacheSize == 0 {
        return cities.count * 5
    }

    var totalTime: Int = 0

    cities.forEach { cityU in
        let city = cityU.lowercased()
        if !cache.isFull {
            if cache.items.contains(city) {
                totalTime += 1
                var removeIdx = 0
                for (idx, item) in cache.items.enumerated().reversed() {
                    if item == city {
                        removeIdx = idx
                        break
                    }
                }
                cache.items.remove(at: removeIdx)
                cache.items.insert(city, at: 0)
            } else {
                totalTime += 5
                cache.items.insert(city, at: 0)
            }
        } else {
            // 캐시 full
            if cache.items.contains(city) {
                // 앞으로 보내기
                totalTime += 1
                var removeIdx = 0
                for (idx, item) in cache.items.enumerated().reversed() {
                    if item == city {
                        removeIdx = idx
                        break
                    }
                }
                cache.items.remove(at: removeIdx)
                cache.items.insert(city, at: 0)
            } else {
                // 오래된 데이터 삭제
                totalTime += 5
                cache.items.removeLast()
                cache.items.insert(city, at: 0)
            }
        }
    }
    return totalTime
}
func solution(_ cacheSize:Int, _ cities:[String]) -> Int {

    let cache = LRUCache(cacheSize: cacheSize)

    return calculateTime(cache: cache, cities: cities)
}

let a = solution(3, ["Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"])
let b = solution(3, ["Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"])
let c = solution(2, ["Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"])
let d = solution(5, ["Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"])
let e = solution(2, ["Jeju", "Pangyo", "NewYork", "newyork"])
let f = solution(0, ["Jeju", "Pangyo", "Seoul", "NewYork", "LA"])

let g = solution(4, ["a", "b", "c", "d", "d", "a", "b", "f"])

let h = solution(3, ["a", "b", "c", "d", "d", "d", "d", "f"])
let i = solution(3, ["a", "b", "c", "d", "d", "a", "b", "f"])
let j = solution(3, ["a", "s", "a", "d", "D", "c", "b", "a"])

print(a)
print(b)
print(c)
print(d)
print(e)
print(f)
print(g)
print(h)
print(i)
print(j)
