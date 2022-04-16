class Solution:
    # currentDigits에서 숫자를 하나 바꾸어 넘어갈 수 있는 digits 후보 리턴
    def generateCandidates(self, currentDigits: str) -> List[str]:
        around = []
        for digit in currentDigits:
            if digit == '0':
                around.append(('9', '1'))
            elif digit == '9':
                around.append(('0', '8'))
            else:
                intDigit = int(digit)
                around.append((str(intDigit + 1), str(intDigit - 1)))
        
        candidates = []
        for index, item in enumerate(around):
            left = ''
            right = ''
            if index == 0:
                right = currentDigits[1:]
            elif index == 1:
                left = currentDigits[0]
                right = currentDigits[2:]
            elif index == 2:
                left = currentDigits[:2]
                right = currentDigits[-1]
            else:
                left = currentDigits[:3]

            result1 = left + item[0] + right
            result2 = left + item[1] + right
            candidates.append(result1)
            candidates.append(result2)

        return candidates
    
    # deadends: ["0201","0101","0102","1212","2002"]
    # start: "0000"(고정)
    # target: "0202"
    # 최소 돌려야 하는 횟수 리턴
    def openLock(self, deadends: List[str], target: str) -> int:
        if "0000" in deadends:
            return -1

        queue = []
        queue.append(("0000", 0))
        visited = set(deadends)
        result = -1

        # BFS를 통해 먼저 도착한 round인 경우에는 항상 최단 round임을 보장
        while len(queue) != 0:
            current = queue.pop(0)
            
            currentDigits = current[0]
            currentRound = current[1]

            # 목적지에 도달하면, 현재 round 리턴
            if currentDigits == target:
                return currentRound
        
            candidates = self.generateCandidates(currentDigits)
    
            for candidate in candidates:
                # 중복 처리, 간 곳은 다시 안 가기
                if candidate not in visited:
                    # 휠을 하나 돌린 상태에서 도달한 숫자이므로 round 하나 추가
                    queue.append((candidate, currentRound + 1))    
                    visited.add(candidate)
                
        return result
        