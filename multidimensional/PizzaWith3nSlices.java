package dp.multidimensional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 1388
 *
 * ======
 *
 * Task.
 *
 * There is a pizza with 3n slices of varying size, you and your friends will
 * take slices of pizza as follows:
 *
 * You will pick any pizza slice. Your friend Alice will pick next slice in anti
 * clockwise direction of your pick. Your friend Bob will pick next slice in
 * clockwise direction of your pick. Repeat until there are no more slices of
 * pizzas.
 *
 * Sizes of Pizza slices is represented by circular array slices in clockwise
 * direction.
 *
 * Return the maximum possible sum of slice sizes which you can have.
 *
 * ======
 *
 * Similar: HRII.
 *
 * ======
 *
 * Source: Leetcode
 */
public class PizzaWith3nSlices {
    /**
     * Backtracking classic.
     */
    public static class Solution0 {
        int n;
        int max;
        boolean[] seen;

        public int maxSizeSlices(int[] a) {
            n = a.length;
            max = 0;
            seen = new boolean[n];
            dfs(0, 0, a);
            return max;
        }

        private void dfs(int step, int curSum, int[] a) {
            System.out.println(step);
            if (step == n / 3) {
                max = Math.max(max, curSum);
            } else {
                for (int i = 0; i < n; i++) {
                    if (seen[i]) {
                        continue;
                    }
                    seen[i] = true;
                    int l = findLeft(i, a);
                    seen[l] = true;
                    int r = findRight(i, a);
                    seen[r] = true;
                    dfs(step + 1, curSum + a[i], a);
                    seen[i] = seen[l] = seen[r] = false;
                }
            }
        }

        private int findLeft(int i, int[] a) {
            for (int j = i - 1; ; j--) {
                if (j == -1) {
                    j = n - 1;
                }
                if (!seen[j]) {
                    return j;
                }
            }
        }

        private int findRight(int i, int[] a) {
            for (int j = i + 1; ; j++) {
                if (j == n) {
                    j = 0;
                }
                if (!seen[j]) {
                    return j;
                }
            }
        }
    }

    class Solution1 {
        private int n;
        private boolean[] seen;

        public int maxSizeSlices(int[] a) {
            n = a.length;
            seen = new boolean[n];
            int ans = dfs(0, 0, a);
            return ans;
        }

        private int dfs(int step, int curSum, int[] a) {
            // System.out.println(step);
            if (step == n / 3) {
                return curSum;
            }
            int ret = 0;
            for (int i = 0; i < n; i++) {
                if (seen[i]) {
                    continue;
                }
                seen[i] = true;
                int l = findLeft(i, a);
                seen[l] = true;
                int r = findRight(i, a);
                seen[r] = true;
                int ans = dfs(step + 1, curSum + a[i], a);
                ret = Math.max(ret, ans);
                seen[i] = seen[l] = seen[r] = false;
            }
            return ret;
        }

        private int findLeft(int i, int[] a) {
            for (int j = i - 1; ; j--) {
                if (j == -1) {
                    j = n - 1;
                }
                if (!seen[j]) {
                    return j;
                }
            }
        }

        private int findRight(int i, int[] a) {
            for (int j = i + 1; ; j++) {
                if (j == n) {
                    j = 0;
                }
                if (!seen[j]) {
                    return j;
                }
            }
        }
    }

    class Solution {
        int n;
        // int max;
        boolean[] seen;

        // Integer[][][] memo;
        // int c=0;
        public int maxSizeSlices(int[] a) {
            n = a.length;
            // max = 0;
            seen = new boolean[n];
            int sum = 0;
            for (int i : a) sum += i;
            // memo=new Integer[n/3][sum + 1][sum+1];
            // System.out.println(s.size());System.out.println(c);
            return Math.max(dfs(0, 0, 0, a, 0, n - 1, new Integer[n / 3][n][sum + 1], 0),
                    dfs(0, 0, 0, a, 1, n, new Integer[n / 3][n][sum + 1], 1));
        }

        private int dfs(int step, int curSum, int wholeSum, int[] a,
                        int lo, int hi, Integer[][][] memo, int maxind) {
            if (step == n / 3) {
                return curSum;
            }
            if (memo[step][maxind][wholeSum] != null) {
                return memo[step][maxind][wholeSum];
            } else {
                int ret = 0;
                for (int i = lo; i < hi; i++) {
                    if (seen[i]) {
                        continue;
                    }
                    int l = findLeft(i);
                    int r = findRight(i);
                    seen[i] = seen[l] = seen[r] = true;

                    int ans = dfs(step + 1, curSum + a[i], curSum + a[i] + a[l] + a[r], a, lo, hi, memo, i);
                    ret = Math.max(ret, ans);

                    seen[i] = seen[l] = seen[r] = false;
                }
                memo[step][maxind][wholeSum] = ret;
                return ret;
            }
        }

        private int findLeft(int i) {
            for (int j = i - 1; ; j--) {
                if (j == -1) {
                    j = n - 1;
                }
                if (!seen[j]) {
                    return j;
                }
            }
        }

        private int findRight(int i) {
            for (int j = i + 1; ; j++) {
                if (j == n) {
                    j = 0;
                }
                if (!seen[j]) {
                    return j;
                }
            }
        }
    }

    class Solution3 {
        private int n;
        private boolean[] seen;
        private Set<Integer> s = new HashSet<>();

        public int maxSizeSlices(int[] a) {
            n = a.length;
            seen = new boolean[n];
            // cur=new int[n/3];

            int ans = dfs(0, 0, a, new HashMap<>(), 0, n);
            System.out.println(ans);
            int ans1 = dfs(0, 0, a, new HashMap<>(), 1, n);
            System.out.println(ans1);
            return Math.max(ans, ans1);
        }

        private int dfs(int step, int curSum, int[] a, Map<Integer, Integer> m, int lo, int hi) {
            // System.out.println(step);
            if (step == n / 3) {
                return curSum;
            }
            int h = s.hashCode();
            if (m.containsKey(h)) {
                return m.get(h);
            }
            int ret = 0;
            for (int i = lo; i < hi; i++) {
                if (seen[i]) {
                    continue;
                }
                seen[i] = true;
                int l = findLeft(i);
                seen[l] = true;
                int r = findRight(i);
                seen[r] = true;
                s.add(i);
                s.add(l);
                s.add(r);
                int ans = dfs(step + 1, curSum + a[i], a, m, lo, hi);
                s.remove(i);
                s.remove(l);
                s.remove(r);
                ret = Math.max(ret, ans);
                seen[i] = seen[l] = seen[r] = false;
            }
            m.put(h, ret);
            return ret;
        }

        private int findLeft(int i) {
            for (int j = i - 1; ; j--) {
                if (j == -1) {
                    j = n - 1;
                }
                if (!seen[j]) {
                    return j;
                }
            }
        }

        private int findRight(int i) {
            for (int j = i + 1; ; j++) {
                if (j == n) {
                    j = 0;
                }
                if (!seen[j]) {
                    return j;
                }
            }
        }
    }
}
