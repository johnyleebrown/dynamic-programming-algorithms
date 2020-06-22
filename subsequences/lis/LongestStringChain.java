package dp.subsequences.lis;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1048
 *
 * ======
 *
 * Task.
 *
 * Given a list of words, each word consists of English lowercase letters.
 *
 * Let's say word1 is a predecessor of word2 if and only if we can add exactly
 * one letter anywhere in word1 to make it equal to word2.  For example, "abc"
 * is a predecessor of "abac".
 *
 * A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >=
 * 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of
 * word_3, and so on.
 *
 * Return the longest possible length of a word chain with words chosen from the
 * given list of words.
 *
 * ======
 *
 * Source: Leetcode
 */
public class LongestStringChain {
    /**
     * Same as LIS.
     */
    public static class Solution1 {
        public int longestStrChain(String[] ar) {
            Map<String, int[]> m = new HashMap<>();
            Arrays.sort(ar, (a, b) -> a.length() - b.length());
            int[] dp = new int[ar.length];
            Arrays.fill(dp, 1);
            int ans = 1;
            for (int i = 1; i < ar.length; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    if (compare(ar[i], ar[j], m)) {
                        dp[i] = Math.max(dp[j] + 1, dp[i]);
                        ans = Math.max(dp[i], ans);
                    }
                }
            }
            return ans;
        }

        private boolean compare(String a, String b, Map<String, int[]> cache) {
            if (a.length() <= b.length()) return false;
            if (!cache.containsKey(a)) cache.put(a, g(a));
            if (!cache.containsKey(b)) cache.put(b, g(b));
            int[] aa = cache.get(a);
            int[] ba = cache.get(b);
            int diff = 0;
            for (int i = 0; i < 26; i++) {
                diff += Math.abs(aa[i] - ba[i]);
                if (diff > 1) return false;
            }
            return true;
        }

        private int[] g(String s) {
            int[] count = new int[26];
            for (char c : s.toCharArray()) count[c - 'a']++;
            return count;
        }
    }

    /**
     * Same idea as LIS, use substrings of string to check previous possible
     * variants.
     * For efficiency use bucket sort.
     */
    public static class Solution2 {
        public int longestStrChain(String[] words) {
            Arrays.sort(words, (a, b) -> a.length() - b.length());

            Map<String, Integer> dp = new HashMap<>();
            for (String word : words) {
                dp.put(word, 1);
            }

            int max = 0;
            for (String word : words) {
                for (int i = 0; i < word.length(); i++) {
                    String prev = word.substring(0, i) + word.substring(i + 1);
                    if (dp.containsKey(prev)) {
                        dp.put(word, Math.max(dp.get(word), dp.get(prev) + 1));
                    }
                }
                max = Math.max(max, dp.get(word));
            }
            return max;
        }
    }

}