package lc.weekly.contests.w289.calculateDigitSumOfAString;

/**
 * @author sherlockyb
 */
public class Solution {
    public String digitSum(String s, int k) {
        if (s == null) {
            return null;
        }

        final StringBuilder sb = new StringBuilder(s.length());
        while (s.length() > k) {
            int sum = 0;
            for (int i = 0; i < s.length(); i++) {
                sum += s.charAt(i) - '0';
                if ((i + 1) % k == 0) {
                    sb.append(sum);
                    sum = 0;
                }
            }

            // last items less then k
            if (s.length() % k != 0) {
                sb.append(sum);
            }

            s = sb.toString();
            sb.delete(0, sb.length());
        }

        return s;
    }
}
