#  Longest Common Subsequence


### Subsequence

A subsequence of a given sequence is just the given sequence with some elements left out.

> Ex) If X = < A, **B**, **C**, **B**, D, A, **B**>, Z = <B, C, D, B>, Z is subsequence of X.

### Common Subsequence
We say that Z is a common subsequence of X and Y if Z is a subsequence of both X and Y.

> Ex) If X = < A, B, C, B, D, A, B>, Y = <B, D, C, A, B, A>,  <B, C, A>, <B, C, D, B>, <B, D, A, B> **common subsequence** of X and Y.

### Problem
Given two sequences `X = <x1, x2, x3, ... , xm>` and `Y = <y1, y2, y3, ..., yn>`, find length of **longest common subsequence** and **longest common subsequence** itself.

### input1

```
ABCBDAB
BDCABA
```

### output1

```
4
BCBA
```

### input2

```
ABCBDAB
ABCB
```

### output2

```
4
ABCB
```

## Solution
