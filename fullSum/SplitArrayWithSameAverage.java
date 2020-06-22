package dp.fullSum;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 805
 */
public class SplitArrayWithSameAverage {
	/**
	 * Math: sa = sb + sc, na = nb + nc, sb/nb = sc/nc => sa/na = sb/nb Time
	 * complexity: O(n^3) Space complexity: O(n^2)
	 */
	public static class Solution {
		public boolean splitArraySameAverage(int[] a) {
			int sum = 0;
			for (int i : a) sum += i;

			// sum, nMoves -> can achieve
			boolean dp[][] = new boolean[sum + 1][a.length + 1];
			dp[0][0] = true;

			for (int i = 1; i < a.length; i++)
				for (int j = sum - a[i] - 1; j >= 0; j--)
					for (int k = 1; k < a.length - 1; k++)
						if (dp[j][k]) {
							dp[j + a[i]][k + 1] = true;

							if ((j + a[i]) * (a.length - k - 1) == (sum - j - a[i]) * (k + 1))
								return true;
						}

			return false;
		}
	}

	/**
	 * Time complexity: O(2^n/2) Space complexity: O(2^n/2)
	 */
	class Solution2 {
		public boolean splitArraySameAverage(int[] A) {
			int N = A.length;
			int S = 0;
			for (int x : A) S += x;
			if (N == 1) return false;

			int g = gcd(S, N);
			Point mu = new Point(-(S / g), N / g);
			// A[i] -> fracAdd(A[i], mu)
			List<Point> A2 = new ArrayList();
			for (int x : A)
				A2.add(fracAdd(new Point(x, 1), mu));

			Set<Point> left = new HashSet();
			left.add(A2.get(0));
			for (int i = 1; i < N / 2; ++i) {
				Set<Point> left2 = new HashSet();
				Point z = A2.get(i);
				left2.add(z);
				for (Point p : left) {
					left2.add(p);
					left2.add(fracAdd(p, z));
				}
				left = left2;
			}

			if (left.contains(new Point(0, 1)))
				return true;

			Set<Point> right = new HashSet();
			right.add(A2.get(N - 1));
			for (int i = N / 2; i < N - 1; ++i) {
				Set<Point> right2 = new HashSet();
				Point z = A2.get(i);
				right2.add(z);
				for (Point p : right) {
					right2.add(p);
					right2.add(fracAdd(p, z));
				}
				right = right2;
			}

			if (right.contains(new Point(0, 1))) return true;

			Point sleft = new Point(0, 1);
			for (int i = 0; i < N / 2; ++i)
				sleft = fracAdd(sleft, A2.get(i));

			Point sright = new Point(0, 1);
			for (int i = N / 2; i < N; ++i)
				sright = fracAdd(sright, A2.get(i));

			for (Point ha : left) {
				Point ha2 = new Point(-ha.x, ha.y);
				if (right.contains(ha2) && (!ha.equals(sleft) || !ha2.equals(sright)))
					return true;
			}

			return false;
		}

		public Point fracAdd(Point A, Point B) {
			int numer = A.x * B.y + B.x * A.y;
			int denom = A.y * B.y;
			int g = gcd(numer, denom);
			numer /= g;
			denom /= g;

			if (denom < 0) {
				numer *= -1;
				denom *= -1;
			}

			return new Point(numer, denom);
		}

		public int gcd(int a, int b) {
			if (b == 0) return a;
			return gcd(b, a % b);
		}
	}
}
