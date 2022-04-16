class Solution:    
    def updateMatrix(self, mat: List[List[int]]) -> List[List[int]]:
        queue = []
        for (row, matRow) in enumerate(mat):
            for (col, matVal) in enumerate(matRow):
                # 시작 지점이 0이다.
                # 순차적으로 탐색하는 것이 아니라, 조건에 맞는 것을 기준으로 탐색 범위를 확대해 나가는 것이 핵심
                if matVal == 0:
                    queue.append((row, col))
                else:
                    # 나머지 위치는 max값 설정
                    mat[row][col] = float('inf')

        while len(queue) != 0:
            target = queue.pop(0)
            
            sr = target[0]
            sc = target[1]
            
            candidates = [
                (sr + 1, sc),
                (sr - 1, sc),
                (sr, sc + 1),
                (sr, sc - 1),
            ]
            
            # 시작 지점의 인근 영역을 중심으로 값을 갱신한다.
            for candidate in candidates:
                newSr = candidate[0]
                newSc = candidate[1]

                if 0 <= newSr < len(mat) and 0 <= newSc < len(mat[0]):
                    # max인 지점(미방문)을 찾았거나, 더 가까운 위치의 0이 있음을 알았을 때 값을 갱신한다.
                    if mat[newSr][newSc] > mat[sr][sc] + 1:
                        mat[newSr][newSc] = mat[sr][sc] + 1
                        queue.append((newSr, newSc))
        return mat