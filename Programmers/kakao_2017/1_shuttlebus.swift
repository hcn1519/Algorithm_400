//
//  main.swift
//  Shuttle
//
//  Created by 홍창남 on 2018. 9. 7..
//  Copyright © 2018년 홍창남. All rights reserved.
//

import Foundation

struct TimeFormatter {
    let hour: Int
    let minute: Int
    let second: Int
}
extension TimeFormatter {
    func totalTime(criteria: Int) -> Int {
        if criteria == 0 {
            return hour
        } else if criteria == 1 {
            return hour * 60 + minute
        } else {
            return (hour * 60 * 60) + (minute * 60) + second
        }
    }
    func to24Format(time: Int) -> String {
        return time >= 10 ? "\(time)" : "0\(time)"
    }
}

extension String {
    func setTime(separator: Character, hasSecond: Bool) -> TimeFormatter {

        let timeStr = self.split(separator: separator)
        let hour = Int(String(timeStr.first!))!

        if hasSecond {
            let minute = Int(String(timeStr[1]))!
            let second = Int(String(timeStr.last!))!
            return TimeFormatter(hour: hour, minute: minute, second: second)
        } else {
            let minute = Int(String(timeStr.last!))!
            return TimeFormatter(hour: hour, minute: minute, second: 0)
        }
    }
}

class TimeCalculator {

    class func calculate60(isAdd: Bool, time1: Int, time2: Int, extra: Int) -> (Int, Int) {
        if isAdd {
            let sum = time1 + time2 + extra
            return sum >= 60 ? (1, sum - 60) : (0, sum)
        }
        return (0,0)
    }

    class func sum(time1: TimeFormatter, time2: TimeFormatter) -> TimeFormatter {

        let second = calculate60(isAdd: true, time1: time1.second, time2: time2.second, extra: 0)
        let minute = calculate60(isAdd: true, time1: time1.minute, time2: time2.minute, extra: second.0)
        let hour = time1.hour + time2.hour + minute.0

        return TimeFormatter(hour: hour, minute: minute.1, second: second.1)
    }

    class func subtract(hour: Int, from time: TimeFormatter) -> TimeFormatter {
        return TimeFormatter(hour: time.hour - hour, minute: time.minute, second: time.second)
    }

    class func subtract(minute: Int, from time: TimeFormatter) -> TimeFormatter {
        if time.minute >= minute {
            return TimeFormatter(hour: time.hour, minute: time.minute - minute, second: time.second)
        }

        return TimeFormatter(hour: time.hour - 1, minute: time.minute + 60 - minute, second: time.second)
    }

    class func subtract(time1: TimeFormatter, time2: TimeFormatter) -> TimeFormatter {

        let subtractTimeSec = time1.totalTime(criteria: 2) - time2.totalTime(criteria: 2)

        let sec = subtractTimeSec % 60
        let hr = subtractTimeSec / 3600

        let min: Int = {
            if hr > 0 {
                return subtractTimeSec % 3600
            } else if hr == 0 {
                return subtractTimeSec / 60
            }
            fatalError("시간 계산이 잘못되었습니다.")
        }()

        return TimeFormatter(hour: hr, minute: min, second: sec)
    }

    class func range(time1: TimeFormatter, time2: TimeFormatter) -> TimeFormatter {
        if compare(time1: time1, time2: time2) {
            return subtract(time1: time1, time2: time2)
        }
        return subtract(time1: time2, time2: time1)
    }

    private class func compare(time1: TimeFormatter, time2: TimeFormatter) -> Bool {
        let total1 = time1.totalTime(criteria: 2)
        let total2 = time2.totalTime(criteria: 2)
        return total1 > total2
    }

    class func sort(times: [TimeFormatter]) -> [TimeFormatter] {
        return times.sorted { (time1, time2) -> Bool in
            return time1.totalTime(criteria: 2) < time2.totalTime(criteria: 2)
        }
    }
}

func setBusTime(n: Int, term: Int) -> [TimeFormatter] {
    let startTime = TimeFormatter(hour: 9, minute: 0, second: 0)
    if n == 1 {
        return [startTime]
    }

    return (1...n).map { num in
        let totalMin = (term * num) - term
        let hr = totalMin > 60 ? (totalMin / 60) : 0
        let min = totalMin > 60 ? totalMin % 60 : totalMin

        let newTime = TimeFormatter(hour: hr, minute: min, second: 0)
        return TimeCalculator.sum(time1: startTime, time2: newTime)
    }

}

func waitingLine(table: [TimeFormatter], busTime: [TimeFormatter], max: Int) -> [String: (Int, [TimeFormatter])] {
    var dict = [String: (Int, [TimeFormatter])]()
    var newTable = table

    busTime.forEach { busTime in
        var dropCount = 0
        for time in newTable {
            if time.totalTime(criteria: 2) <= busTime.totalTime(criteria: 2) {
                if let value = dict[toResult(time: busTime)] {
                    dict[toResult(time: busTime)] = (value.0 + 1, value.1 + [time])
                } else {
                    dict[toResult(time: busTime)] = (1, [time])
                }
                dropCount += 1
            }
        }
        if dropCount == 0 {
            dict[toResult(time: busTime)] = (0, [])
        } else {
            newTable.removeFirst(dropCount)
        }
    }

    var reserve = 0
    busTime.enumerated().forEach {
        if let count = dict[toResult(time: $0.element)], count.0 > max {
            reserve = count.0 - max
            if $0.offset < busTime.count - 1, let value = dict[toResult(time: busTime[$0.offset + 1])] {
                var tmp = count.1

                var cnt = 0
                var removed: [TimeFormatter] = []
                tmp.reversed().forEach {
                    if cnt < reserve {
                        removed.append($0)
                    }
                    cnt += 1
                }

                tmp.removeLast(reserve)

                let newTime = TimeCalculator.sort(times: value.1 + removed)

                dict[toResult(time: $0.element)] = (max, tmp)
                dict[toResult(time: busTime[$0.offset + 1])] = ((value.0 + reserve), newTime)
            }

            if $0.offset == busTime.count - 1 {
                var value = count.1
                value.removeLast(reserve)
                dict[toResult(time: $0.element)] = (max, value)
            }
        }
        reserve = 0
    }
    return dict
}

func toResult(time: TimeFormatter) -> String {
    return time.to24Format(time: time.hour) + ":" + time.to24Format(time: time.minute)
}

func solution(_ n:Int, _ t:Int, _ m:Int, _ timetable:[String]) -> String {

    let table: [TimeFormatter] = timetable.map { return $0.setTime(separator: ":", hasSecond: false) }
    let sortedTable = TimeCalculator.sort(times: table)

    let busTime = setBusTime(n: n, term: t)
    let lineDict = waitingLine(table: sortedTable, busTime: busTime, max: m)

    if lineDict.count == 0 {
        return toResult(time: busTime.last!)
    }

    for time in busTime.reversed() {
        let key = toResult(time: time)
        if let value = lineDict[key] {
            if value.0 > m {
                let firstMember = value.1.first!
                return toResult(time: TimeCalculator.subtract(minute: 1, from: firstMember))
            } else if value.0 < m {
                return toResult(time: time)
            }

            let lastMember = value.1.last!
            return toResult(time: TimeCalculator.subtract(minute: 1, from: lastMember))
        }
    }
    return ""
}

func 답확인(번호: Int, 예상: String, 실제: String) {
    print(예상 == 실제 ? "\(번호) - 맞음, 예상 - \(예상), 실제 - \(실제)" : "\(번호) - 틀림, 예상 - \(예상), 실제 - \(실제)")
}

let a = solution(1, 1, 5, ["08:00", "08:01", "08:02", "08:03"])
let b = solution(2, 10, 2, ["09:10", "09:09", "08:00"])
let c = solution(2, 1, 2, ["09:00", "09:00", "09:00", "09:00"])
let d = solution(1, 1, 5, ["00:01", "00:01", "00:01", "00:01", "00:01"])
let e = solution(1, 1, 1, ["23:59"])
let f = solution(10, 60, 45, ["23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"])
let g = solution(1, 10, 2, ["09:10", "08:02", "08:00"])
let h = solution(2, 10, 1, ["09:10", "08:02", "08:00"])
let i = solution(1, 10, 1, ["09:10", "08:02", "08:00"])


답확인(번호: 1, 예상: solution(1, 1, 5, ["08:00", "08:01", "08:02", "08:03", "08:58"]), 실제: "08:57")
답확인(번호: 2, 예상: solution(2, 60, 2, ["08:00", "08:13", "08:14", "10:00"]), 실제: "09:59")
답확인(번호: 3, 예상: solution(2, 1, 1, ["09:02", "00:01", "00:01", "00:01", "00:01", "00:01"]), 실제: "00:00")
답확인(번호: 4, 예상: solution(1, 1, 1, ["23:59"]), 실제: "09:00")
답확인(번호: 5, 예상: solution(1, 1, 1, ["09:01"]), 실제: "09:00")
답확인(번호: 6, 예상: solution(10, 60, 45, ["23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"]), 실제: "18:00")
답확인(번호: 7, 예상: solution(10, 60, 5, ["09:01","09:01", "09:01", "09:01", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"]), 실제: "18:00")
답확인(번호: 8, 예상: solution(2, 10, 2, ["09:10", "09:09", "08:00"]), 실제: "09:09")
답확인(번호: 9, 예상: a, 실제: "09:00")
답확인(번호: 10, 예상: b, 실제: "09:09")
답확인(번호: 11, 예상: c, 실제: "08:59")
답확인(번호: 12, 예상: d, 실제: "00:00")
답확인(번호: 13, 예상: e, 실제: "09:00")
답확인(번호: 14, 예상: f, 실제: "18:00")
답확인(번호: 15, 예상: h, 실제: "08:01")
답확인(번호: 16, 예상: i, 실제: "07:59")

let j = solution(7, 60, 1, ["09:10", "08:02", "08:00", "14:22", "23:59"])
답확인(번호: 17, 예상: j, 실제: "14:21")

let k = solution(1, 60, 1, ["00:01", "08:02", "08:00", "00:01"])
답확인(번호: 17, 예상: k, 실제: "00:00")

let l = solution(2, 60, 4, ["00:01", "00:01", "00:01", "00:01", "00:01", "00:01", "00:01",
                            "08:00", "08:40", "08:50"])

답확인(번호: 18, 예상: l, 실제: "07:59")
