package dp.multidimensional;

import java.util.Arrays;

/**
 * 1223
 *
 * ======
 *
 * Task.
 *
 * A die simulator generates a random number from 1 to 6 for each roll. You
 * introduced a constraint to the generator such that it cannot roll the number
 * i more than rollMax[i] (1-indexed) consecutive times.
 *
 * Given an array of integers rollMax and an integer n, return the number of
 * distinct sequences that can be obtained with exact n rolls.
 *
 * Two sequences are considered different if at least one element differs from
 * each other. Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * ======
 *
 * Source: Leetcode
 */
public class DiceRollSimulation {
    /**
     * The reasoning here is to check how can the ith element end. I wrote down
     * the states for i=2 and i=3.
     *
     * We use dp[n-1][a[newj]][newJ] because the state is number of combinations
     * so for current one we call other indexes dp answers and * for our index
     * we go one by one k -1 so previously i was looping through k from 1 to
     * a[ind] that way i was repeating what was counted in *.
     */
    public static class Solution {
        private static final int MOD = 1_000_000_007;

        public int dieSimulator(int n, int[] a) {
            int[][][] dp = new int[n + 1][6][15 + 1];
            for (int i = 0; i < 6; i++) {
                Arrays.fill(dp[1][i], 1);
            }
            int res = 0;
            for (int i = 0; i < 6; i++) {
                res = (res + dfs(n, i, a[i], dp, a)) % MOD;
            }
            return res;
        }

        int dfs(int n, int curI, int k, int[][][] dp, int[] a) {
            if (dp[n][curI][k] != 0) {
                return dp[n][curI][k];
            }
            int res = 0;
            for (int newI = 0; newI < 6; newI++) {
                if (newI != curI) {
                    res = (res + dfs(n - 1, newI, a[newI], dp, a)) % MOD;
                }
                // ith sequence can end with i only if k - 1 > 0
                else {
                    if (k - 1 > 0) {
                        res = (res + dfs(n - 1, newI, k - 1, dp, a)) % MOD;
                    }
                }
            }
            dp[n][curI][k] = res;
            return dp[n][curI][k];
        }
    }
}