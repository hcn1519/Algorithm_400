extension Substring.SubSequence {
    var toInt: Int {
        return Int(String(self))!
    }
    var toDouble: Double {
        return Double(String(self))!
    }
}

extension String {
    subscript(_ idx: Int) -> Character {
        let indice = self.index(self.startIndex, offsetBy: idx)
        return self[indice]
    }

    subscript(_ range: CountableClosedRange<Int>) -> String {
        if range.upperBound >= self.count || range.upperBound < 0 {
            fatalError("Index Out of Range")
        }

        let low = self.index(self.startIndex, offsetBy: range.lowerBound)
        let high = self.index(self.startIndex, offsetBy: range.upperBound)

        return String(self[low...high])
    }
}

let abc = "abc"

print(abc[1])

print(abc[1...3])
