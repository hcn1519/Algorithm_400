# https://leetcode.com/explore/learn/card/data-structure-tree/17/solve-problems-recursively/536/
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
        
class Solution:
    def __init__(self):
        self.left = []
        self.right = []

    # 왼쪽 노드의 left/right 정보를 모두 수집한다.
    # 재귀를 통해 전체 노드 정보를 구성한다.
    def leftFirst(self, root: Optional[TreeNode]):

        lhs = root.left != None
        rhs = root.right != None
        
        self.left.append((lhs, rhs, root.val))
        
        if lhs:
            self.leftFirst(root = root.left)
        
        if rhs:
            self.leftFirst(root = root.right)

    # 오른쪽 노드의 left/right 정보를 모두 수집한다.        
    def rightFirst(self, root: Optional[TreeNode]):

        lhs = root.left != None
        rhs = root.right != None
        
        self.right.append((lhs, rhs, root.val))
        
        # Symmetric Tree는 중심을 기준으로 접었을 때(mirror of itself) 값이 같아야 한다.
        # 따라서, 추후 연산을 용이하게 하기 위해 leftFirst에서 왼쪽 노드를 먼저 추가한 것과 다르게 오른쪽 노드를 먼저 추가한다.
        if rhs:
            self.rightFirst(root = root.right)
            
        if lhs:
            self.rightFirst(root = root.left)
        
    def isSymmetric(self, root: Optional[TreeNode]) -> bool:
        if root == None:
            return True
        
        if root.left != None:
            self.leftFirst(root = root.left)
        
        if root.right != None:
            self.rightFirst(root = root.right)

        if len(self.left) != len(self.right):
            return False
        
        result = True
        for lhs, rhs in zip(self.left, self.right):
            # lhs의 모든 sub node가 있으면
            if lhs[0] != lhs[1]:
                # lhs와 rhs의 반대 방향 비교
                result = lhs[1] == rhs[0] and lhs[0] == rhs[1]
            else:
                # lhs와 rhs는 정방향 비교
                result = lhs[0] == rhs[0] and lhs[1] == rhs[1]
            
            # 최종 value 확인
            if lhs[2] != rhs[2]:
                result = False
            
            if result == False:
                break
            
        return result