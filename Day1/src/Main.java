import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static final String INPUT_FILE = "/Users/Jack/Documents/Hobby Programming/Advent of Code/Day1/src/input1.txt";
    private static final String SEPARATION_LINE = "";
    public static void main(String[] args) throws FileNotFoundException {
        int maxCalories = maxCalories();
        System.out.println(maxCalories);

        int topThreeCalories = topThreeCalories();
        System.out.println(topThreeCalories);
    }

    public static int maxCalories() throws FileNotFoundException {
        File file = new File(INPUT_FILE);
        Scanner scanner = new Scanner(file);

        int maxCount = 0;
        int count = 0;

        // iterate over file, keep count per elf, and store current greatest count of one elf in maxCount
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!Objects.equals(line, SEPARATION_LINE)){
                count += Integer.parseInt(line);
            }else{
                if (count > maxCount)
                    maxCount = count;
                count = 0;
            }
        }
        return maxCount;
    }

    public static int topThreeCalories() throws FileNotFoundException {
        File file = new File(INPUT_FILE);
        Scanner scanner = new Scanner(file);

        ArrayList<Integer> calories = new ArrayList<Integer>() ;
        int count = 0;

        // iterate over file, keep count per elf, and store in an ArrayList
        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();
            if (!Objects.equals(line, SEPARATION_LINE)){
                count += Integer.parseInt(line);
            }else{
                calories.add(count);
                count = 0;
            }

        }

        calories.sort(Collections.reverseOrder());
        return calories.get(0) + calories.get(1) + calories.get(2);
    }
}
