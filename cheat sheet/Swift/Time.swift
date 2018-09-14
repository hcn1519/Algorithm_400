
import Foundation

struct MyTime {
    let hour: Int
    let minute: Int
    let second: Int
}

extension MyTime {
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
    func setTime(separator: Character, hasSecond: Bool) -> MyTime {

        let timeStr = self.split(separator: separator)
        let hour = Int(String(timeStr.first!))!

        if hasSecond {
            let minute = Int(String(timeStr[1]))!
            let second = Int(String(timeStr.last!))!
            return MyTime(hour: hour, minute: minute, second: second)
        } else {
            let minute = Int(String(timeStr.last!))!
            return MyTime(hour: hour, minute: minute, second: 0)
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

    class func sum(time1: MyTime, time2: MyTime) -> MyTime {

        let second = calculate60(isAdd: true, time1: time1.second, time2: time2.second, extra: 0)
        let minute = calculate60(isAdd: true, time1: time1.minute, time2: time2.minute, extra: second.0)
        let hour = time1.hour + time2.hour + minute.0

        return MyTime(hour: hour, minute: minute.1, second: second.1)
    }

    class func subtract(hour: Int, from time: MyTime) -> MyTime {
        return MyTime(hour: time.hour - hour, minute: time.minute, second: time.second)
    }

    class func subtract(minute: Int, from time: MyTime) -> MyTime {
        if time.minute >= minute {
            return MyTime(hour: time.hour, minute: time.minute - minute, second: time.second)
        }

        return MyTime(hour: time.hour - 1, minute: time.minute + 60 - minute, second: time.second)
    }

    class func subtract(time1: MyTime, time2: MyTime) -> MyTime {

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

        return MyTime(hour: hr, minute: min, second: sec)
    }

    class func range(time1: MyTime, time2: MyTime) -> MyTime {
        if compare(time1: time1, time2: time2) {
            return subtract(time1: time1, time2: time2)
        }
        return subtract(time1: time2, time2: time1)
    }

    private class func compare(time1: MyTime, time2: MyTime) -> Bool {
        let total1 = time1.totalTime(criteria: 2)
        let total2 = time2.totalTime(criteria: 2)
        return total1 > total2
    }

    class func sort(times: [MyTime]) -> [MyTime] {
        return times.sorted { (time1, time2) -> Bool in
            return time1.totalTime(criteria: 2) < time2.totalTime(criteria: 2)
        }
    }
}
