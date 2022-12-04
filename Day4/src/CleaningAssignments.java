import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CleaningAssignments{
    private final ArrayList<String> pairs = new ArrayList<>();

    /**
     * Constructor to read in file and populate pairs arraylist
     * @param filename - name of the input file
     */
    public CleaningAssignments(final String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            pairs.add(scanner.nextLine());
        }
    }

    /**
     * Method to get the number of overlaps
     * @param typeOfOverlap - can be either 0 or 1. 0 calculates number of pairs where one elf's assignment is
     *                      completely contained by the other. 1 calculates number of pairs where there is any overlap
     *                      at all between cleaning assignments
     * @return the number of overlaps
     */
    public int getNumberOfOverlaps(int typeOfOverlap){
        int count = 0;
        for (String pair:pairs){
            // split based on , and - to get the min and max assignment per elf
            String[] splitPair = pair.split(",");
            String[] elfOne = splitPair[0].split("-");
            String[] elfTwo = splitPair[1].split("-");

            int elfOneMin = Integer.parseInt(elfOne[0]);
            int elfOneMax = Integer.parseInt(elfOne[1]);
            int elfTwoMin = Integer.parseInt(elfTwo[0]);
            int elfTwoMax = Integer.parseInt(elfTwo[1]);

            if (typeOfOverlap == 0){
                count = checkContainedOverlap(elfOneMin, elfOneMax, elfTwoMin, elfTwoMax) ? count + 1 : count;
            }else{
                count = checkRangeOverlap(elfOneMin, elfOneMax, elfTwoMin, elfTwoMax) ? count + 1 : count;
            }
        }
        return count;
    }

    /**
     * Method to determine whether there is a contained overlap between elves in pair
     * @param elfOneMin - Min assignment of elf one.
     * @param elfOneMax - Max assignment of elf one
     * @param elfTwoMin - Min assignment of elf two
     * @param elfTwoMax - Max assignment of elf two
     * @return whether one elf's assignments are fully contained within the others
     */
    public boolean checkContainedOverlap(int elfOneMin, int elfOneMax, int elfTwoMin, int elfTwoMax){

        if ((elfOneMin <= elfTwoMin) && (elfOneMax >= elfTwoMax)){
            return true; // check if elf two contained within elf one
        }else return (elfTwoMin <= elfOneMin) && (elfTwoMax >= elfOneMax); // check if elf one contained within elf two
    }

    /**
     * Method to determine whether there is any overlap between two elves
     * @param elfOneMin - Min assignment of elf one.
     * @param elfOneMax - Max assignment of elf one
     * @param elfTwoMin - Min assignment of elf two
     * @param elfTwoMax - Max assignment of elf two
     * @return whether one elf's assignments overlap the others at all
     */
    public boolean checkRangeOverlap(int elfOneMin, int elfOneMax, int elfTwoMin, int elfTwoMax){
        if ((elfTwoMin >= elfOneMin) && (elfTwoMin <= elfOneMax)){
            return true; // check if elf twos min is within elf ones range
        }else if((elfTwoMax >= elfOneMin) && (elfTwoMax <= elfOneMax)){
            return true; // check if elf twos max is within elf ones range
        }else if((elfOneMin >= elfTwoMin) && (elfOneMin <= elfTwoMax)){
            return true; // check if elf ones min is within elf twos range
        }else return (elfOneMax >= elfTwoMin) && (elfOneMax <= elfTwoMax); // check if elf ones max is within elf twos range
    }
}
