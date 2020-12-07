package factorial;

import java.math.BigInteger;
import java.util.Arrays;

public class DigitsService {
    private static final String EMPTY_DELIMITER = "";

    /**
     * Counting the sum of all digits
     *
     * @param num This value will be turned to factorial, split to strings.
     *            String will be counted respectively
     * @return Returns the value of counted string
     */
    public static int countDigitsSum(int num) {
        BigInteger factorial = factorial(num);
        String[] arr = factorial.toString().split(EMPTY_DELIMITER);
        return Arrays.stream(arr)
                .map(Integer::valueOf)
                .reduce(0, Integer::sum);
    }

    /**
     * Getting factorial of an entered number
     *
     * @param num Value passed to count its factorial number
     * @return Returns BigInteger as it doesn't fit
     */
    public static BigInteger factorial(int num) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= num; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
