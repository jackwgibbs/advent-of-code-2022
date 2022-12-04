import java.io.FileNotFoundException;

public class Main {
    private static final String INPUT_FILE = "/Users/Jack/Documents/Hobby Programming/advent-of-code-2022/Day4/src/input.txt";
    public static void main(String[] args) throws FileNotFoundException {
        CleaningAssignments cleaningAssignments = new CleaningAssignments(INPUT_FILE);

        // part 1
        int overlaps = cleaningAssignments.getNumberOfOverlaps(0);
        System.out.println(overlaps);

        // part 2
        int rangeOverlaps = cleaningAssignments.getNumberOfOverlaps(1);
        System.out.println(rangeOverlaps);
    }
}
