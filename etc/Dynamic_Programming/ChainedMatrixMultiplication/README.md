#  Chained Matrix Multiplication(행렬 체인 곱셈)

n개의 행렬의 체인이 (A1, A2, ... , An)과 같이 주어질 때, 여기서 스칼라 곱의 개수를 최소화하도록 괄호를 묶는 방법과 그 연산 결과를 구하라.

### input1

```
4
20 2
2 30
30 12
12 8
```
### output1

```
1232
(A1((A2A3)A4))
```
### input2

```
3
10 30
30 5
5 60
```
### output2

```
4500
((A1A2)A3)
```


---

## Solution

### 연산

`A(i, j)`와 `A(j, k)`의 곱 사이에 필요한 연산의 횟수를 `d(i)d(j)d(k)`라 가정한다. 예를 들어,

```
A1(10, 30), A2(30, 5)인 경우,

연산 횟수는 d(10) * d(30) * d(5) 형태로 결과가 나오도록 d 배열을 설정해야 한다.

```

또한 `m(i, j)`를 `Ai`부터 `Aj`까지의 최소 곱이라고 하자. `m(i, j)`를 구하기 위해 재귀식을 정의할 때,  `m(i, j)`는  `k`(`i <= k < j`)를 기준으로 나누는 경우를 생각하면 다음과 같은 재귀식이 도출된다.

```
if i == j
    m(i, j) = 0
else if i < j
    m(i, j) = min{ m(i, k) + m(k+1, j) + d(i-1) * d(k) * d(j) }
```

이 때 메모이제이션을 활용하기 위해 다음과 같이 루프를 돌고 최종 결과는 `m(n, 1)`에 반환된다.

![alt text](https://dl.dropbox.com/s/5wgjd7jdhv5i4hr/mul.jpeg "Logo Title Text 1")


### 출력

```
func printOrder(arr: [[Int]], i: Int, j: Int) {
    if i == j {
        print(Ai)
    } else {
        let k = arr[i][j]
        print("(")
        printOrder(arr: arr, i: i, j: k)
        printOrder(arr: arr, i: k+1, j: j)
        print(")")
    }
}
```
