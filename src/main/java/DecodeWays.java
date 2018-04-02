/**
 * 91
 * Given an encoded message containing digits,
 * determine the total number of ways to decode it.
 */
public class DecodeWays  {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int solution(String s) {
        int n = s.length();
        if (n == 0) return 0;
        if (n == 1 && s.charAt(0) != '0') return 1;
        if (s.charAt(0) == '0') return 0;
        int[] a = new int[n + 1];
        a[0] = 1;
        a[1] = s.charAt(1) == '0' ? 0 : 1;
        for (int i = 2 ; i <= n ; i++) {
            if (i < n && s.charAt(i) == '0') continue;
            int x = Integer.parseInt(s.substring(i - 2, i)) < 27 ? a[i - 2] : 0;
            // either we add nothing, or we add a[i-2] if the last two digits form a letter
            a[i] = a[i - 1] + x;
        }
        return a[n];
    }
}
