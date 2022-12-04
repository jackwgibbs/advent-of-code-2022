public class Rucksack {
    private final String rucksackContents;
    private final String compartmentOne;
    private final String compartmentTwo;

    /**
     * Constructor populates the rucksack contents and each compartment's contents
     */
    public Rucksack(String firstHalf, String secondHalf){
        rucksackContents = firstHalf + secondHalf;
        compartmentOne = firstHalf;
        compartmentTwo = secondHalf;
    }

    /**
     * Method to determine matching item between components
     * @return the matching item
     */
    public char getMatchingItemsInComponents(){
        int length = compartmentOne.length();
        for (int i = 0; i < length; i++){
            char item = compartmentOne.charAt(i);
            if (compartmentTwo.contains(Character.toString(item)))
                return compartmentOne.charAt(i);
        }
        throw new AssertionError("No match found");
    }

    /**
     * Getter for rucksack contents
     * @return rucksack contents
     */
    public String getRucksackContents(){
        return rucksackContents;
    }
}
