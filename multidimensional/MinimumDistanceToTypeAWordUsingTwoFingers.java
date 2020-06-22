package dp.multidimensional;

/**
 * 1320
 *
 * ======
 *
 * Task.
 *
 * You have a keyboard layout as shown above in the XY plane, where each English
 * uppercase letter is located at some coordinate, for example, the letter A is
 * located at coordinate (0,0), the letter B is located at coordinate (0,1), the
 * letter P is located at coordinate (2,3) and the letter Z is located at
 * coordinate (4,1).
 *
 * Given the string word, return the minimum total distance to type such string
 * using only two fingers. The distance between coordinates (x1,y1) and (x2,y2)
 * is |x1 - x2| + |y1 - y2|.
 *
 * Note that the initial positions of your two fingers are considered free so
 * don't count towards your total distance, also your two fingers do not have to
 * start at the first letter or the first two letters.
 *
 * ======
 *
 * Test cases: "CAKE" - 3, "HAPPY" - 6, "NEW" - 3, "YEAR" - 7.
 */
public class MinimumDistanceToTypeAWordUsingTwoFingers {
	static class Solution {
		public int minimumDistance(String word) {
			int[] dp = new int[26];
			int result = 0;
			int save = 0;

			for (int i = 0; i < word.length() - 1; i++) {
				int b = word.charAt(i) - 'A';
				int c = word.charAt(i + 1) - 'A';

				for (int a = 0; a < 26; a++) {
					dp[b] = Math.max(dp[b], dp[a] + helper(b, c) - helper(a, c));
				}

				save = Math.max(save, dp[b]);
				result += helper(b, c);
			}

			return result - save;
		}

		private int helper(int a, int b) {
			return Math.abs(a / 6 - b / 6) + Math.abs(a % 6 - b % 6);
		}
	}
}
