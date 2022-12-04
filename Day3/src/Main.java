import java.io.FileNotFoundException;

public class Main {
    private static final String INPUT_FILE = "/Users/Jack/Documents/Hobby Programming/advent-of-code-2022/Day3/src/input.txt";

    public static void main(String[] args) throws FileNotFoundException {
        Rearrangement rearrangement = new Rearrangement(INPUT_FILE);

        // part one
        int prioritySum = rearrangement.getPrioritySum();
        System.out.println(prioritySum);

        // part two
        int prioritySumPerGroup = rearrangement.getPrioritySumPerGroup();
        System.out.println(prioritySumPerGroup);
    }
}
