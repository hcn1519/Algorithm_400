class Solution:
    def dailyTemperatures(self, temperatures: List[int]) -> List[int]:
        
        answers = [0 for i in range(0, len(temperatures))]
        stack = []
                                    
        for index, temperature in enumerate(temperatures):
            if index == 0:
                stack.append(index)
                continue
            
            # 이전에 저장한 값을 계산하는 과정
            while len(stack) != 0 and temperatures[stack[-1]] < temperature:
                answers[stack[-1]] = index - stack[-1]
                stack.pop()
            
            # 핵심은 stack에 index를 저장하는 것, length와 값 모두를 확인할 수 있도록 index를 저장한다.
            # 현재 기온을 저장
            stack.append(index)
            
        return answers