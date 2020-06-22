package dp.subsequences.lis;

/**
 * 801
 */
public class MinimumSwapsToMakeSequencesIncreasing {
	public static class Solution {
		public static int minSwap(int[] A, int[] B) {
			int[] memo1 = new int[A.length];
			int[] memo2 = new int[A.length];
			memo2[0] = 1;

			for (int i = 1; i < A.length; i++) {
				memo1[i] = Integer.MAX_VALUE;
				memo2[i] = Integer.MAX_VALUE;

				if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
					memo1[i] = Math.min(memo1[i - 1], memo1[i]);
					memo2[i] = Math.min(memo2[i - 1] + 1, memo2[i]);
				}

				if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
					memo1[i] = Math.min(memo2[i - 1], memo1[i]);
					memo2[i] = Math.min(memo1[i - 1] + 1, memo2[i]);
				}
			}

			return Math.min(memo1[A.length - 1], memo2[A.length - 1]);
		}
	}
}
