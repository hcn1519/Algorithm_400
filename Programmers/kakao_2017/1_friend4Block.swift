//
//  main.swift
//  1_friend4Block
//
//  Created by 홍창남 on 2018. 9. 9..
//  Copyright © 2018년 홍창남. All rights reserved.
//

import Foundation

func mapInitialBoard(m: Int, n: Int, board: [String]) -> [[String]] {

    var newBoard: [[String]] = Array.init(repeating: [], count: m)
    var count = 0

    zip(newBoard, board).forEach { value in
        let newV = value.1.reduce([]) { acc2, cur2 in
            return acc2 + [String(cur2)]
        }
        newBoard[count] = newV
        count += 1
    }

    return newBoard
}
func shouldRemoveBlock(r: Int, c: Int, board: [[String]]) -> Bool {
    if r == board.count - 1 || c == board[r].count - 1 {
        return false
    }

    let val1 = board[r][c]
    let val2 = board[r + 1][c]
    let val3 = board[r][c + 1]
    let val4 = board[r + 1][c + 1]

    return val1 != "" && val1 == val2 && val1 == val3 && val1 == val4
}

func canSqueeze(r: Int, c: Int, board: [[String]]) -> Bool {
    if r == board.count - 1 || c == board[r].count - 1 {
        return false
    }

    let val1 = board[r][c]
    let val2 = board[r + 1][c]

    return val1 != "" && val2 == ""
}

func removeBlock(board: [[String]]) -> ([[String]], Int) {

    var boardCopy = board
    var count = 0

    for (r, row) in board.enumerated() {
        for (c, _) in row.enumerated() {
            if shouldRemoveBlock(r: r, c: c, board: board) {
                boardCopy[r][c] = ""
                boardCopy[r + 1][c] = ""
                boardCopy[r][c + 1] = ""
                boardCopy[r + 1][c + 1] = ""
                count += 1
                //                print(boardCopy)
            }
        }
    }
    return (boardCopy, count)
}


func squeezeBlock(board: [[String]]) -> [[String]] {

    var boardCopy = board

    let rCount = board.count
    let cCount = board[rCount - 1].count

    (0...(rCount - 1)).reversed().forEach { row in
        (0...(cCount - 1)).reversed().forEach { col in
            if board[row][col] == "" {
                var saveRow = row
                var curRow = row - 1

                while curRow != -1 {
                    if boardCopy[curRow][col] != "" && boardCopy[saveRow][col] == "" {
                        boardCopy[saveRow][col] = boardCopy[curRow][col]
                        boardCopy[curRow][col] = ""
                        saveRow -= 1
                    }
                    curRow -= 1
                }
            }
        }
    }
    return boardCopy
}

func solution(_ m:Int, _ n:Int, _ board:[String]) -> Int {
    let splitBoard = mapInitialBoard(m: m, n: n, board: board)

    var resultBoard = removeBlock(board: splitBoard)
    while resultBoard.1 != 0 {

        let temp = (squeezeBlock(board: resultBoard.0), 0)
        resultBoard = removeBlock(board: temp.0)

    }

    let removeCount = resultBoard.0.reduce(0) { acc, cur in
        let rowCount = cur.reduce(0) { acc2, cur2 in
            return cur2 == "" ? acc2 + 1 : acc2
        }
        return acc + rowCount
    }
    return removeCount
}

print(solution(2, 2, ["AB", "AA"])) // 0
print(solution(3, 3, ["AAA", "AAA", "AAA"])) // 9
print(solution(2, 2, ["AA", "AA"])) // 4
print(solution(3, 2, ["AA", "AA", "AA"])) // 6
print(solution(4, 3, ["BBC", "AAA", "AAB", "BBC"])) // 8
print(solution(5, 5, ["BBCDE", "AABDE", "AAEED", "BBEED", "BCBAB"])) // 16
print(solution(4, 5, ["CCBDE", "AAADE", "AAABF", "CCBBF"])) // 14
print(solution(6, 6, ["TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"])) // 15
print(solution(6, 5, ["TTTAN", "RRFAC", "RRRFC", "TRRRA", "TTMMM", "TMMTT"])) // 11

print(solution(6, 5, ["ABCDE", "ABCDE", "ABCDE", "ABCDE", "AACDE", "AACDE"])) // 4
