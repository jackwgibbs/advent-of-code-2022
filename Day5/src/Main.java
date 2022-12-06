import java.io.FileNotFoundException;

public class Main {
    private static final String INPUT_FILE = "/Users/Jack/Documents/Hobby Programming/advent-of-code-2022/Day5/src/input.txt";
    private static final int NUM_OF_CRATES = 9;

    public static void main(String[] args) throws FileNotFoundException {
        Crane crane = new Crane(INPUT_FILE, NUM_OF_CRATES);
        crane.run(INPUT_FILE, true);

        Crane craneTwo = new Crane(INPUT_FILE, NUM_OF_CRATES);
        craneTwo.run(INPUT_FILE, false);
    }
}
