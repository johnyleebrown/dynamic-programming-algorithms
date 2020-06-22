package dp.multidimensional;

/**
 * 562
 */
public class LongestLineOfConsecutiveOneInMatrix {
    /**
     * BU solution is much nicer. So here we count 4 different results cuz we
     * can count consecutive 1's 4 diff ways. Extra element (5) is to check if
     * we ve calculated cell before.
     */
    public static class Solution {
        int n, m;
        int max = 0;
        private int[][] dirs = new int[][]{{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};

        public int longestLine(int[][] a) {
            n = a.length;
            if (n == 0) return 0;
            m = a[0].length;
            if (m == 0) return 0;
            int[][][] dp = new int[n + 1][m + 1][5];
            dfs(n, m, dp, a);
            return max;
        }

        private int[] dfs(int i, int j, int[][][] dp, int[][] a) {
            if (i < 1 || j < 1 || i > n || j > this.m) {
                return new int[]{0, 0, 0, 0, 1};
            }
            if (dp[i][j][4] != 0) {
                return dp[i][j];
            }
            dp[i][j] = new int[]{0, 0, 0, 0, 1};
            for (int k = 0; k < 4; k++) {
                int[] res = dfs(i + dirs[k][0], j + dirs[k][1], dp, a);
                if (a[i - 1][j - 1] != 0) {
                    dp[i][j][k] = res[k] + 1;
                }
                max = Math.max(max, dp[i][j][k]);
            }
            return dp[i][j];
        }
    }
}