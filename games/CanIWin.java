package dp.games;

import java.util.HashMap;

/**
 * 464
 *
 * =====
 *
 * Task.
 *
 * Same as stones game(2 players, stones 1..m, total n).
 *
 * In the "100 game," two players take turns adding, to a running total, any
 * integer from 1..10. The player who first causes the running total to reach or
 * exceed 100 wins.
 *
 * What if we change the game so that players cannot re-use integers?
 *
 * For example, two players might take turns drawing from a common pool of
 * numbers of 1..15 without replacement until they reach a total >= 100.
 *
 * Given an integer maxChoosableInteger and another integer desiredTotal,
 * determine if the first player to move can force a win, assuming both players
 * play optimally.
 *
 * You can always assume that maxChoosableInteger will not be larger than 20 and
 * desiredTotal will not be larger than 300.
 */
public class CanIWin {
	public static class Solution {
		public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
			if (((1 + maxChoosableInteger) * maxChoosableInteger) / 2 < desiredTotal)
				return false;
			if (desiredTotal <= 0) return true;
			HashMap<Integer, Boolean> map = new HashMap<>();
			return helper(maxChoosableInteger, 0, desiredTotal, map);
		}

		private boolean helper(int max, int state, int total, HashMap<Integer, Boolean> map) {
			if (total <= 0) return false;
			if (map.containsKey(state)) return map.get(state);

			for (int i = 0; i < max; i++) {
				if ((state & (1 << i)) == 0) { // check which number is not used
					int t = state | (1 << i); // next state
					if (!helper(max, t, total - (max - i), map)) { // if opponent loses, we win
						map.put(state, true);
						return true;
					}
				}
			}

			map.put(state, false); // if we can't win
			return false;
		}
	}
}
