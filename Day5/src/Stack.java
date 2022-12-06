import java.util.ArrayList;

public class Stack {
    ArrayList<Character> stack = new ArrayList<>();

    /**
     * Method to take a crate and add it to the bottom of the stack (unconventional!) This is needed since the
     * input file is read from top to bottom, from top crate to bottom crate. Only used in set-up when reading input
     * file.
     * @param crate - the crate to add to bottom of stack
     */
    public void addCrateToBottom(char crate){
        stack.add(0, crate);
    }

    /**
     * Method to take a crate and add it to the top of the stack (LIFO) structure.
     * @param crate - crate to add to top of stack
     */
    public void addCrate(char crate){
        stack.add(crate);
    }

    /**
     * Method to add a list of crates to the stack
     */
    public void addMultipleCrates(char[] multipleCrates){
        for (int i = multipleCrates.length; i-- > 0; ) {
            this.addCrate(multipleCrates[i]);
        }
    }

    /**
     * Method to pop top crate from the stack
     * @return top crate from stack
     */
    public char removeCrate(){
        return stack.remove(stack.size()-1);
    }
}
