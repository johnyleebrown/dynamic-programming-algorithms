package dp.subsequences.lcs;

import util.tester.Tester;

import java.util.Arrays;

import static util.utils.StringUtils.getMaxLen3Strings;

/**
 * 1092
 *
 * ======
 *
 * Task.
 *
 * Given two strings str1 and str2, return the shortest string that has both
 * str1 and str2 as subsequences.  If multiple answers exist, you may return any
 * of them.
 *
 * (A string S is a subsequence of string T if deleting some number of
 * characters from T (possibly 0, and the characters are chosen anywhere from T)
 * results in the string S.)
 *
 * ======
 *
 * Source: Leetcode
 */
public class ShortestCommonSupersequence {
	public static void main(String[] args) {
		new Tester(new Solution())
				.add("abac", "cab").expect("cabac").orExpect(null)
				.add("cbcbacaab", "ababaabbbb").expect("cabacbacaabbbb").orExpect("cabcabacaabbbb")
				.run();
	}

	/**
	 * Find a lcs and then fill the result with leftovers from both strings.
	 */
	public static class Solution {
		public String shortestCommonSupersequence(String s1, String s2) {
			StringBuilder sb = new StringBuilder();

			String lcs = longestCommonSubsequenceString(s1, s2);

			int lcsInd = 0;
			int s1Ind = 0;
			int s2Ind = 0;

			while (lcsInd < lcs.length()) {
				while (s1.charAt(s1Ind) != lcs.charAt(lcsInd)) {
					sb.append(s1.charAt(s1Ind));
					s1Ind++;
				}
				s1Ind++;

				while (s2.charAt(s2Ind) != lcs.charAt(lcsInd)) {
					sb.append(s2.charAt(s2Ind));
					s2Ind++;
				}
				s2Ind++;

				sb.append(lcs.charAt(lcsInd));
				lcsInd++;
			}

			return sb.toString() + s1.substring(s1Ind, s1.length()) + s2.substring(s2Ind, s2.length());
		}

		public String longestCommonSubsequenceString(String s1, String s2) {
			int n = s1.length();
			int m = s2.length();
			String[][] ans = new String[n + 1][m + 1];

			// base
			for (int i = 0; i <= n; i++)
				Arrays.fill(ans[i], "");

			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					if (s1.charAt(i - 1) == s2.charAt(j - 1))
						ans[i][j] = ans[i - 1][j - 1] + s1.charAt(i - 1);
					else
						ans[i][j] = getMaxLen3Strings(ans[i - 1][j - 1], ans[i][j - 1], ans[i - 1][j]);
				}
			}

			return ans[n][m];
		}
	}
}
