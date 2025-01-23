import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        List<Integer> indices = new ArrayList<>();
    }

    static class Trie {
        TrieNode root = new TrieNode();

        // 단어를 트라이에 삽입하고 해당 인덱스를 저장
        void insert(String word, int index) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }
                node = node.children[idx];
                node.indices.add(index);
            }
        }

        // 가장 긴 공통 접두사를 가진 두 단어의 인덱스를 찾음
        void findLongestCommonPrefix(String[] words) {
            int maxLength = 0;
            int firstIndex = 0;
            int secondIndex = 1;

            for (int i = 0; i < words.length; i++) {
                TrieNode node = root;
                int commonLength = 0;
                for (char c : words[i].toCharArray()) {
                    int idx = c - 'a';
                    if (node.children[idx] == null) {
                        break;
                    }
                    node = node.children[idx];
                    commonLength++;
                    if (node.indices.size() > 1) {
                        for (int j : node.indices) {
                            if (j != i) {
                                if (commonLength > maxLength) {
                                    maxLength = commonLength;
                                    firstIndex = i;
                                    secondIndex = j;
                                }
                            }
                        }
                    }
                }
            }

            System.out.println(words[firstIndex]);
            System.out.println(words[secondIndex]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] words = new String[n];
        Trie trie = new Trie();

        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
            trie.insert(words[i], i);
        }

        trie.findLongestCommonPrefix(words);
    }
}
