/**
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right which minimizes
 * the sum of all numbers along its path.
 */
public class MinimumPathSum {
    /**
     * Time complexity: O(m*n)
     * Space complexity: O(1)
     */
    public static int solution(int[][] grid) {
        for (int i = 0 ; i < grid.length ; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == 0 && j == 0) continue;
                if (i == 0) {
                    grid[i][j] += grid[i][j - 1];
                } else if (j == 0) {
                    grid[i][j] += grid[i - 1][j];
                } else {
                    grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
                }

            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }
}
