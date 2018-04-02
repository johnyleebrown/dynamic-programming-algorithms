/**
 * 63
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 */
public class UniquePathsII {
    /**
     * Time complexity: O(n*m)
     * Space complexity: O(1)
     */
    public static int solution(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1)
                    obstacleGrid[i][j] = 0;
                else if (i == 0 && j > 0)
                    obstacleGrid[i][j] = obstacleGrid[i][j - 1];
                else if (j == 0 && i > 0)
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j];
                else if (i > 0 && j > 0)
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                else
                    obstacleGrid[i][j] = 1;
            }
        }
        return obstacleGrid[m - 1][n - 1];
    }
}
