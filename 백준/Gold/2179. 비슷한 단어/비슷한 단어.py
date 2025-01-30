from collections import defaultdict, namedtuple

class TrieNode:
    def __init__(self):
        self.children = [ None for _ in range(26)]
        self.indices = []


def insert(word: str, idx: int):
    node = root
    for ch in word:
        i = ord(ch) - ord('a')
        if node.children[i] == None:
            node.children[i] = TrieNode()
        node = node.children[i]
        node.indices.append(idx)

if __name__ == '__main__':
    N = int(input())
    root = TrieNode()
    words = []
    for i in range(N):
        word = input()
        words.append(word)
        insert(word, i)

    max = 0
    first = 0
    second = 1

    for i in range(N):
        node = root

        common = 0

        for ch in words[i]:
            idx = ord(ch) - ord('a')

            if node.children[idx] == None:
                break
            node = node.children[idx]
            common += 1

            if len(node.indices) > 1:
                for j in node.indices:
                    if i != j and common > max:
                        max = common

                        first = i
                        second = j
    print(words[first],words[second],sep='\n')