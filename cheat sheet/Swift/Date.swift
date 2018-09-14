struct MyDate {
    let year: Int
    let month: Int
    let day: Int
}

class DateCalculator {

    static let dayCountOfMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

    class func compare(date1: MyDate, date2: MyDate) -> Bool {
        if date1.year != date2.year {
            return date1.year > date2.year
        }
        return numberOfDay(date: date1) > numberOfDay(date: date2)
    }

    class func numberOfDay(date: MyDate) -> Int {
        if date.month == 1 {
            return date.day
        }

        let beforeDate = dayCountOfMonth.enumerated().reduce(0) { acc, cur in
            if cur.offset < date.month {
                return acc + cur.element
            }
            return acc
        }

        return beforeDate + date.day
    }

    class func sort(date: [MyDate]) -> [MyDate] {
        return date.sorted {
            compare(date1: $1, date2: $0)
        }
    }

    class func dayToDate(day: Int) -> MyDate {

        let newYear = day / 365 > 0 ? day / 365 : 0

        var leftDay = day % 365

        var month = 0

        while dayCountOfMonth[month] <= leftDay {
            leftDay -= dayCountOfMonth[month]
            month += 1
        }
        return MyDate(year: newYear, month: month, day: leftDay)
    }

    class func add(day: Int, from date: MyDate) -> MyDate {
        let total = numberOfDay(date: date) + day
        return dayToDate(day: total)
    }
}

let a = MyDate(year: 2015, month: 2, day: 29)
let b = MyDate(year: 2015, month: 5, day: 29)
let c = MyDate(year: 2015, month: 1, day: 19)
let d = MyDate(year: 2016, month: 5, day: 25)
let e = MyDate(year: 2015, month: 2, day: 19)
let f = MyDate(year: 2015, month: 5, day: 29)

let da = [a, b, c, d, e, f]

print(DateCalculator.compare(date1: a, date2: b))
print(DateCalculator.sort(date: da))

print(DateCalculator.dayToDate(day: 445))

print(DateCalculator.add(day: 130, from: f))
