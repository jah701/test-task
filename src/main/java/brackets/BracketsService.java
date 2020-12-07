package brackets;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BracketsService {
    private static final String BRACKETS = "()";

    /**
     *
     */
    public void getValidBrackets() {
        Set<String> firstSet = new HashSet<>();
        Set<String> secondSet = new HashSet<>();
        String temp;
        int bracketsCount;

        // Input number of brackets
        while (true) {
            System.out.println("Please enter the number of brackets you want: ");
            Scanner in = new Scanner(System.in);
            if (in.hasNextInt()) {
                bracketsCount = in.nextInt();
                break;
            }
        }

        // Return '0' if input value = 0 as there would be no valid brackets therefore
        if (bracketsCount <= 0) {
            System.out.println("There are no valid brackets for 0 or negative value");
            return;
        }

        firstSet.add(BRACKETS);

        // Adding "(" and ")" before and after each symbol
        for (int i = 0; i < bracketsCount - 1; i++) {
            for (String str : firstSet) {
                for (int j = 0; j < str.length(); j++) {
                    temp = str.substring(0, j)
                            + BRACKETS
                            + str.substring(j);
                    secondSet.add(temp);
                }
            }

            firstSet = secondSet;
            secondSet = new HashSet<>();
        }
        System.out.println(firstSet + "\nThere are "
                + firstSet.size()
                + " valid"
                + (bracketsCount > 1 ? " combinations" : " combination")
                + " for number "
                + bracketsCount + "\n");
    }
}
