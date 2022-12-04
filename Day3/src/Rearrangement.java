import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Rearrangement {
    public static ArrayList<Rucksack> rucksacks = new ArrayList<>();

    /**
     * Constructor to read in file and create rucksack objects. Store objects in arraylist
     * @param filename - name of the input file
     */
    public Rearrangement(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();

            // get midpoint of line, use it to split line, and create new rucksack object with it
            int midpoint = Math.floorDiv(line.length(), 2);
            String firstHalf = line.substring(0, midpoint);
            String secondHalf = line.substring(midpoint);

            rucksacks.add(new Rucksack(firstHalf, secondHalf));
        }
    }

    /**
     * Method to determine the matching item between compartments of rucksack and calculate its priority.
     * Repeat for all rucksacks and keep track of all priorities sum.
     * @return total priority of all matching rucksack compartment items
     */
    public int getPrioritySum(){
        int total = 0;
        for (Rucksack rucksack : rucksacks) {
            char matchingItem = rucksack.getMatchingItemsInComponents();
            total += getPriority(matchingItem);
        }
        return total;
    }

    /**
     * Method to determine the matching item between group of three rucksacks and calculate its priority.
     * Repeat for all groups of three rucksack and keep track of the sum of the priorities of the matching items.
     * @return priority of items
     */
    public int getPrioritySumPerGroup(){
        int total = 0;

        // iterate over each group of three rucksacks
        for (int i = 0; i < rucksacks.size(); i += 3){
            String rucksackOne = rucksacks.get(i).getRucksackContents();
            String rucksackTwo = rucksacks.get(i + 1).getRucksackContents();
            String rucksackThree = rucksacks.get(i + 2).getRucksackContents();

            total += getPriority(getMatchingItemInGroup(rucksackOne, rucksackTwo, rucksackThree));
        }

        return total;
    }

    /**
     * Method to determine the matching item between a group of three rucksacks
     * @param rucksackOne - contents of rucksack 1
     * @param rucksackTwo - contents of rucksack 2
     * @param rucksackThree - contents of rucksack 3
     * @return the matching item between rucksacks
     */
    public char getMatchingItemInGroup(String rucksackOne, String rucksackTwo, String rucksackThree){
        for (int i = 0; i < rucksackOne.length(); i++){
            String currentItem = Character.toString(rucksackOne.charAt(i));
            if (rucksackTwo.contains(currentItem) && (rucksackThree.contains(currentItem))){
                return rucksackOne.charAt(i);
            }
        }
        throw new AssertionError("No match found");
    }

    /**
     * Method to return priority of the item in rucksack
     * @param item - the item from rucksack
     * @return priority of item
     */
    public int getPriority(char item){
        // priority is 1-26 for a-z and 27-52 for A-Z
        // convert to ascii value and map to priority depending on case
        return Character.isUpperCase(item) ? (int) item - 38 : (int) item - 96;
    }
}
