/**
 * Given a “2 x n” board and tiles of size “2 x 1”,
 * count the number of ways to tile the given board using
 * the 2 x 1 tiles. A tile can either be placed horizontally
 * i.e., as a 1 x 2 tile or vertically i.e., as 2 x 1 tile.
 */
public class TilingProblem {
    /**
     * Fib numbers
     * Either we put XX vertically at pos 0
     * Or we put 2 XX tiles horizontally at pos(0..1)
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int solution(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++)
            dp[i] = dp[i - 2] + dp[i - 1];
        return dp[n];
    }
}
