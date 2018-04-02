/**
 * 256
 * There are a row of n houses, each house can be painted
 * with one of the three colors: red, blue or green. The
 * cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent
 * houses have the same color. The cost of painting each house
 * with a certain color is represented by a n x 3 cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with
 * color red; costs[1][2] is the cost of painting house 1 with
 * color green, and so on... Find the minimum cost to paint all houses
 */
public class PaintHouse {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int solution1(int[][] costs) {
        int prev = -1;
        int n = costs.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int cur = 0;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < 3; j++) {
                if (j == prev) continue;
                if (min > costs[i - 1][j]) {
                    min = costs[i - 1][j];
                    cur = j;
                }
            }
            prev = cur;
            dp[i] = min + dp[i - 1];
        }
        return dp[n];
    }
}
