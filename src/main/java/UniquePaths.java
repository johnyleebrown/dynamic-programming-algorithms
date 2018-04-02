/**
 * A robot is located at the top-left corner of a m x n grid.
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid.
 * How many possible unique paths are there?
 */
public class UniquePaths {
    /**
     * Time complexity: O(m*n)
     * Space complexity: O(m*n)
     */
    public static int solution(int m, int n) {
        int[][] grid = new int[m][n];
        for (int i = 0 ; i < m ; i++) grid[i][0] = 1;
        for (int i = 0 ; i < n ; i++) grid[0][i] = 1;
        for (int i = 1 ; i < m ; i++)
            for (int j = 1 ; j < n ; j++)
                grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
        return grid[m - 1][n - 1];
    }
}
