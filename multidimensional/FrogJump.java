package dp.multidimensional;

import java.util.HashMap;
import java.util.Map;

/**
 * 403
 *
 * ======
 *
 * Task.
 *
 * A frog is crossing a river. The river is divided into x units and at each
 * unit there may or may not exist a stone. The frog can jump on a stone, but it
 * must not jump into the water.
 *
 * Given a list of stones' positions (in units) in sorted ascending order,
 * determine if the frog is able to cross the river by landing on the last
 * stone. Initially, the frog is on the first stone and assume the first jump
 * must be 1 unit.
 *
 * If the frog's last jump was k units, then its next jump must be either k - 1,
 * k, or k + 1 units. Note that the frog can only jump in the forward
 * direction.
 *
 * ======
 *
 * Source: Leetcode
 */
public class FrogJump {
	/**
	 * Using map(ind-map_of_k) to save space for cases with large indexes.
	 * Considering 3 cases.
	 */
	public static class Solution {
		private static Map<Integer, Map<Integer, Boolean>> m;
		private static int max;

		public boolean canCross(int[] a) {
			max = a[a.length - 1];

			m = new HashMap<>();
			for (int value : a)
				m.put(value, new HashMap<>());

			return dfs(1, 1);
		}

		private boolean dfs(int ind, int k) {
			if (!m.containsKey(ind)) {// there is no stone at ind
				return false;
			}
			if (ind == max) {//reached the end - always true
				m.get(ind).put(k, true);
				return true;
			}
			if (m.get(ind).containsKey(k)) {//if been here before
				return m.get(ind).get(k);
			}

			boolean ans = false;
			if (ind + k - 1 > ind)
				ans = dfs(ind + k - 1, k - 1);//not going back
			ans = ans || dfs(ind + k, k) || dfs(ind + k + 1, k + 1);

			m.get(ind).put(k, ans);
			return ans;
		}
	}
}
