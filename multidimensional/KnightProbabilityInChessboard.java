package dp.multidimensional;

/**
 * 688
 *
 * ======
 *
 * Task.
 *
 * On an NxN chessboard, a knight starts at the r-th row and c-th column and
 * attempts to make exactly K moves. The rows and columns are 0 indexed, so the
 * top-left square is (0, 0), and the bottom-right square is (N-1, N-1).
 *
 * A chess knight has 8 possible moves it can make, as illustrated below. Each
 * move is two squares in a cardinal direction, then one square in an orthogonal
 * direction.
 *
 * Each time the knight is to move, it chooses one of eight possible moves
 * uniformly at random (even if the piece would go off the chessboard) and moves
 * there.
 *
 * The knight continues moving until it has made exactly K moves or has moved
 * off the chessboard. Return the probability that the knight remains on the
 * board after it has stopped moving.
 *
 * ======
 *
 * Source: Leetcode
 */
public class KnightProbabilityInChessboard {
		/**
		 * SF except the part of ans/=8, this is because "even if the piece would go
		 * off the chessboard".
		 */
		public static class Solution {
				private static final int[][] dirs = new int[][]{{1, 2}, {-1, 2}, {1, -2}, {-1, -2}, {2, 1}, {-2, -1}, {-2, 1}, {2, -1}};

				public double knightProbability(int N, int K, int r, int c) {
						return dfs(r, c, K, N, new Double[N + 1][N + 1][K + 1]);
				}

				private double dfs(int i, int j, int k, int n, Double[][][] dp) {
						if (k == 0) {
								return 1;
						}
						if (dp[i][j][k] != null) {
								return dp[i][j][k];
						}
						double ans = 0;
						for (int[] d : dirs) {
								int ni = i + d[0];
								int nj = j + d[1];
								if (ni < 0 || nj < 0 || ni >= n || nj >= n) {
										continue;
								}
								ans += dfs(ni, nj, k - 1, n, dp);
						}
						ans /= 8;
						dp[i][j][k] = ans;
						return dp[i][j][k];
				}
		}
}