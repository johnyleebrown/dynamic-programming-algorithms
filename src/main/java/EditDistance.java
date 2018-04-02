/**
 * GeeksForGeeks
 * Originally Levenshtein distance (1965)
 */
public class EditDistance {
    /**
     * Time complexity: O(n^2)
     * Space complexity: O(n^2)
     */
    public static int solution(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int temp[][] = new int[str1.length + 1][str2.length + 1];
        for (int i = 0; i < temp[0].length; i++) temp[0][i] = i;
        for (int i = 0; i < temp.length; i++) temp[i][0] = i;

        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if (str1[i - 1] == str2[j - 1])
                    temp[i][j] = temp[i - 1][j - 1];
                else
                    // insert, delete, replace
                    temp[i][j] = 1 +
                            Math.min(Math.min(temp[i - 1][j - 1], temp[i - 1][j]), temp[i][j - 1]);
            }
        }
        return temp[str1.length][str2.length];
    }
}
