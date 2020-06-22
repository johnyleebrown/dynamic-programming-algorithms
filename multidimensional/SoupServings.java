package dp.multidimensional;

/**
 * 808
 */
public class SoupServings {
	public static double Solution(int N) {
		double[][] dp = new double[1000][1000];
		int n = (N + 25 - 1) / 25;
		// (5600, 0.9999990943903253)
		if (n > 800) {
			return 1.0;
		}
		dp[0][0] = 0.5; // 50/50
		for (int i = 1; i < 1000; i++) {
			dp[0][i] = 1.0;
		}

		for (int i = 1; i < 1000; i++) {
			for (int j = 1; j < 1000; j++) {
				dp[i][j] = 0.0;
				dp[i][j] += dp[Math.max(0, i - 4)][Math.max(0, j - 0)] / 4; // Serve 100 ml of soup A and 0 ml of soup B
				dp[i][j] += dp[Math.max(0, i - 3)][Math.max(0, j - 1)] / 4; // Serve 75 ml of soup A and 25 ml of soup B
				dp[i][j] += dp[Math.max(0, i - 2)][Math.max(0, j - 2)] / 4; // Serve 50 ml of soup A and 50 ml of soup B
				dp[i][j] += dp[Math.max(0, i - 1)][Math.max(0, j - 3)] / 4; // Serve 25 ml of soup A and 75 ml of soup B
			}
		}

		return dp[n][n];
	}
}
