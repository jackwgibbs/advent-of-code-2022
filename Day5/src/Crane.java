import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Crane {
    ArrayList<Stack> stacks = new ArrayList<>();

    /**
     * Constructor reads input file and sets up stacks
     * @param filename - name of the input file
     * @param numOfStacks - number of stacks in the input file
     */
    public Crane(String filename, int numOfStacks) throws FileNotFoundException {
        // create the number of stacks and add to an ArrayList. Index will uniquely identify them.
        for (int i = 0; i < numOfStacks; i++){
            stacks.add(new Stack());
        }

        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        // check if line in file contains '[', i.e. describes the setup of the stacks
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.contains("[")){
                extractSetupLine(line, numOfStacks);
            }else{
                // if line does not contain '[', we have finished processing the stack setup
                break;
            }
        }
    }

    /**
     * Method to extract a stack setup line of the input file. Character appears at locations 1, 4, 9, etc...
     * (depicted by j)
     * Extract that character and add to the relevant stack (depicted by i)
     * @param line - the line of the file being processed
     * @param numOfStacks - number of stacks in the input file
     */
    public void extractSetupLine(String line, int numOfStacks){
        for (int i = 0, j = 1; i <= (numOfStacks * 4); i++, j += 4) {
            try {
                if (!(Character.valueOf(' ').equals(line.charAt(j)))) {
                    // add crate to the bottom of the stack (as processing in reverse order
                    stacks.get(i).addCrateToBottom(line.charAt(j));
                }
            }catch (StringIndexOutOfBoundsException e) {
                // if end of line reached, stop processing line
                break;
            }
        }
    }

    /**
     * Method to run through input file and move the crates between the stacks
     * @param filename - the name of the input file
     */
    public void run(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        // iterate over lines in file and process ones that contain command 'move'
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if (line.contains("move")){
                // remove all non-digit characters from line and split numbers into list
                line = line.replaceAll("[^0-9]+", "-");
                String[] movement = line.split("-");

                // add meaning to the numbers for readability
                int numberOfCrates = Integer.parseInt(movement[1]);
                int fromCrate = Integer.parseInt(movement[2]) -1 ;
                int toCrate = Integer.parseInt(movement[3]) - 1;

                // for the number of crates to move, move crate from fromCrate to toCrate
                for (int i = 0; i < numberOfCrates; i++){
                    stacks.get(toCrate).addCrate(stacks.get(fromCrate).removeCrate());
                }
            }
        }
    }
}
