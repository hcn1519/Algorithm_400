# Fibonacci Modified

[문제 링크](https://www.hackerrank.com/challenges/fibonacci-modified/problem)

변형된 형태의 피보나치 수열의 계산을 하는 문제이다.

### Input1

```
0 1 5
```

### Output1

```
5
```

### Input2

```
0 1 10
```

### Output2

```
84266613096281243382112
```

---

## Solution

본 문제는 다이나믹 프로그래밍의 기초 문제인데, 그것보다 구현이 어려운 것은 `Int64` 이상의 숫자의 계산이다. Swift는 별도로 64비트 이상의 숫자에 대한 라이브러리가 내장되어 있지 않기 때문에 이 계산을 직접 구현해야 한다.

```
struct MyBigInt {
	static func sum(lhs: String, rhs: String) -> String
	static func multiple(lhs: String, rhs: String) -> String
}
```

그래서 `MyBigInt`에서는 `String`으로 인풋을 받아서 결과는 `[Int]`로 계산을 하고 이를 다시 `String`으로 변환하는 형태로 코드를 구현하였다.


```
func modifiedFibonacci(_ t1: String,_ t2: String) -> String {
    return MyBigInt.sum(lhs: t1, rhs: MyBigInt.multiple(lhs: t2, rhs: t2))
}

var fiboArr = [String]()

fiboArr.append(String(t1))
fiboArr.append(String(t2))

for i in 2..<n {
    fiboArr.append(modifiedFibonacci(fiboArr[i-2], fiboArr[i-1]))
}
```

다만 현재 구현된 방식은 일부 케이스`(1, 1, 20)`에서 시간이 초과된다.