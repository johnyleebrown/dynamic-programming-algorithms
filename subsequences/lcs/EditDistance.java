package dp.subsequences.lcs;

import java.util.Arrays;

/**
 * 72
 *
 * ======
 *
 * Task.
 *
 * Given two words word1 and word2, find the minimum number of operations
 * required to convert word1 to word2.
 *
 * You have the following 3 operations permitted on a word:
 *
 * Insert a character Delete a character Replace a character
 *
 * ======
 *
 * Source: Leetcode
 */
public class EditDistance {
    /**
     * 1. subproblem: dp[i1][i2] is min num of ops to change w1(0,i1) to
     * w2(0,i2).
     *
     * 2. how it ends: 2 cases, same letters at i1,i2 or different. If same, we
     * take prev calculated result (i-1,j-1), otherwise
     */
    public static class Solution {
        public int minDistance(String w1, String w2) {
            int nw1 = w1.length();
            int nw2 = w2.length();
            int[][] dp = new int[nw1 + 1][nw2 + 1];
            for (int i = 0; i <= nw1; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            for (int i = 0; i <= nw1; i++) {
                dp[i][0] = i;
            }
            for (int i = 0; i <= nw2; i++) {
                dp[0][i] = i;
            }
            return dfs(nw1, nw2, w1, w2, dp);
        }

        private int dfs(int i1, int i2, String w1, String w2, int[][] dp) {
            if (i1 > w1.length()) {
                return w2.length() - i2;
            }
            if (i2 > w2.length()) {
                return w1.length() - i1;
            }
            if (dp[i1][i2] != Integer.MAX_VALUE) {
                return dp[i1][i2];
            }

            if (w1.charAt(i1 - 1) == w2.charAt(i2 - 1)) {
                dp[i1][i2] = dfs(i1 - 1, i2 - 1, w1, w2, dp);
            } else {
                dp[i1][i2] = Math.min(Math.min(dfs(i1, i2 - 1, w1, w2, dp), dfs(i1 - 1, i2, w1, w2, dp)),
                        dfs(i1 - 1, i2 - 1, w1, w2, dp)) + 1;
            }

            return dp[i1][i2];
        }
    }
}
