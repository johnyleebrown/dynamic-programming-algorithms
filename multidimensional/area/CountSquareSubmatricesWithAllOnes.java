package dp.multidimensional.area;

/**
 * 1277
 *
 * ======
 *
 * Task.
 *
 * Given a m * n matrix of ones and zeros, return how many square submatrices
 * have all ones.
 *
 * ======
 *
 * Source: Leetcode
 */
public class CountSquareSubmatricesWithAllOnes {
    /**
     * Number of subarrays when you add a number to array is [1,2,3] + [4] = 4
     * => num+=r-l. Same with squares, num of squares if bottom right corner is
     * 1 length of previous square + 1. Num of previous squares is
     * min((i-1,j-1),(i-1,j),(i,j-1)).
     */
    public static class Solution {
        public int countSquares(int[][] ar) {
            int n = ar.length;
            int m = ar[0].length;
            int[][] dp = new int[n][m];
            int ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (i > 0 && j > 0 && ar[i][j] == 1) {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    } else {
                        dp[i][j] = ar[i][j];
                    }
                    ans += dp[i][j];
                }
            }
            return ans;
        }
    }
}