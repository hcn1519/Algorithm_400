# Kangaroo

[Problem Link](https://www.hackerrank.com/challenges/kangaroo/problem)

## Solution

if v2 > v1, kangaroos cannot meet each other because x2 > x1.

So, 

```
if v2 >= v1 {
	print("NO")
} else {
	(x1 - x2) % (v2 - v1) == 0 ? print("YES") : print("NO")
}
```