
import Foundation

func arrangeClothes(clothes:[[String]]) -> [String: [String]] {

    var dict: [String: [String]] = [:]
    clothes.forEach { item in
        let value = item[0]
        let key = item[1]

        if let existClothes = dict[key] {
            if !existClothes.contains(value) {
                dict[key] = existClothes + [value]
            }
        } else {
            dict[key] = [value]
        }
    }
    return dict
}
func solution(_ clothes:[[String]]) -> Int {

    let arranged = arrangeClothes(clothes: clothes)

    var count = 1
    arranged.forEach {
        count *= ($0.value.count + 1)
    }
    return count - 1
}

print(solution([["yellow_hat", "headgear"], ["blue_sunglasses", "eyewear"], ["green_turban", "headgear"]]))
print(solution([["crow_mask", "face"], ["blue_sunglasses", "face"], ["smoky_makeup", "face"]]))
