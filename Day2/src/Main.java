import java.io.FileNotFoundException;

public class Main {
    private static final String FILENAME = "/Users/Jack/Documents/Hobby Programming/advent-of-code-2022/Day2/src/input1.txt";
    public static void main(String[] args) throws FileNotFoundException {
        Game game = new Game(FILENAME);
        int score = game.getGameScoreForPlayer();
        System.out.println(score);
    }
}