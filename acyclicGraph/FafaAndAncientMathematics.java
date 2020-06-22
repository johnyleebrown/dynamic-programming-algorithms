package dp.trees;

import tree._ds.ExpressionTree;
import util.ds.TreeNode;
import util.utils.reader.InputReader;

import java.io.PrintWriter;

import static util.utils.Out.print;

/**
 * 465Div2E
 *
 * ======
 *
 * Task.
 *
 * Ancient Egyptians are known to have understood difficult concepts in
 * mathematics. The ancient Egyptian mathematician Ahmes liked to write a kind
 * of arithmetic expressions on papyrus paper which he called as Ahmes
 * arithmetic expression.
 *
 * An Ahmes arithmetic expression can be defined as:
 *
 * "d" is an Ahmes arithmetic expression, where d is a one-digit positive
 * integer; "(E1 op E2)" is an Ahmes arithmetic expression, where E1 and E2 are
 * valid Ahmes arithmetic expressions (without spaces) and op is either plus
 * ( + ) or minus ( - ).
 *
 * For example 5, (1-1) and ((1+(2-3))-5) are valid Ahmes arithmetic
 * expressions.
 *
 * On his trip to Egypt, Fafa found a piece of papyrus paper having one of these
 * Ahmes arithmetic expressions written on it. Being very ancient, the papyrus
 * piece was very worn out. As a result, all the operators were erased, keeping
 * only the numbers and the brackets. Since Fafa loves mathematics, he decided
 * to challenge himself with the following task:
 *
 * Given the number of plus and minus operators in the original expression, find
 * out the maximum possible value for the expression on the papyrus paper after
 * putting the plus and minus operators in the place of the original erased
 * operators.
 *
 * ======
 *
 * Source: Codeforces
 */
public class FafaAndAncientMathematics {
    /**
     * TODO
     */
    public static class Solution {
        private static void s(InputReader in, PrintWriter out) {
            String exp = in.nextLine();
            int p = in.nextInt();
            int m = in.nextInt();
            ExpressionTree e = new ExpressionTree(exp);
            print(out, dfs(e, e.getRoot(), p, m, true));
        }

        private static int dfs(ExpressionTree e, TreeNode cur, int p, int m, boolean max) {
            if (cur == null)
                return 0;
            if (max) {
                int res = 0;
                if (p > 0) {
                    res = dfs(e, cur.left, p - 1, m, true) + dfs(e, cur.right, p - 1, m, true);
                }
                if (m > 0) {
                    res = Math.max(res, dfs(e, cur.left, p, m - 1, true) - dfs(e, cur.right, p, m - 1, false));
                }
                return res;
            } else // if min
            {
                int res = 0;
                if (p > 0) {
                    res = dfs(e, cur.left, p - 1, m, false) - dfs(e, cur.right, p - 1, m, true);
                }
                if (m > 0) {
                    res = Math.min(res, dfs(e, cur.left, p, m - 1, false) + dfs(e, cur.right, p, m - 1, false));
                }
                return res;
            }
        }
    }
}
