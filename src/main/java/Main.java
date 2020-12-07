import static factorial.DigitsService.countDigitsSum;

import brackets.BracketsService;
import fight.connections.FlightService;
import java.io.IOException;

public class Main {
    private static final int FACTORIAL_NUMBER = 100;
    private static final String FILE_PATH = "src/main/resources/flights.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("The sum of all digits is: " + countDigitsSum(FACTORIAL_NUMBER) + "\n");

        new BracketsService().getValidBrackets();

        new FlightService(FILE_PATH).getCheapestWay();
    }
}
