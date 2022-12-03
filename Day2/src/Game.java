import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
    private static final HashMap<Character, Integer> choiceScores = new HashMap<>();
    private static final HashMap<Character, Character> gameRules = new HashMap<>();
    private static final int WIN = 6;
    private static final int DRAW = 3;
    private static final int LOSS = 0;
    private final ArrayList<Character> playerChoice = new ArrayList<>();
    private final ArrayList<Character> opponentChoice = new ArrayList<>();

    /**
     * Constructor to set up the game. Read in input file and populate
     * the opponent and player choices. Also construct score map and game rules
     * @param filename - the name of the input file
     */
    public Game(String filename) throws FileNotFoundException {
        // set up game by reading in file
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            opponentChoice.add(line.charAt(0));
            playerChoice.add(line.charAt(2));
        }

        // initialise score map
        choiceScores.put('X', 1);
        choiceScores.put('Y', 2);
        choiceScores.put('Z', 3);

        // set up game rules
        /// X, Y, Z = A, B, C = Rock, Paper, Scissors
        gameRules.put('X', 'B'); // rock beaten by paper
        gameRules.put('Y', 'C'); // paper beaten by rock, scissors
        gameRules.put('Z', 'A'); // scissors beaten by rock
    }

    /**
     * Main method to calculate the players total score.
     * @return the total score of the player for all rounds of the game
     */
    public int getGameScoreForPlayer(){
        int numOfRounds = playerChoice.size();
        int score = 0;
        for (int i = 0; i < numOfRounds; i++){
            score += getChoiceScore(playerChoice.get(i));
            score += hasPlayerWon(playerChoice.get(i), opponentChoice.get(i));
        }
        return score;
    }

    /**
     * Method to return the score of the choice player selected
     * X = Rock = 1
     * Y = Paper = 2
     * Z = Scissors = 3
     * @param choice - choice of the player
     * @return the score of the users choice
     */
    public int getChoiceScore(char choice){
        return choiceScores.get(choice);
    }

    /**
     * Method to check whether the player has won, lost or drew
     * @param playerChoice - the choice of the player (X-Z)
     * @param opponentChoice - the choice of the opponent (A-C)
     * @return the score, 0 for a loss, 3 for a draw, 6 for a win
     */
    public int hasPlayerWon(char playerChoice, char opponentChoice) {
        // check draw - convert to ascii and take 23 from player choice
        // player choice -> X-Z, opponent choice -> A-Z
        if ((int) playerChoice - 23 == (int) opponentChoice){
            return DRAW;
        }

        return gameRules.get(playerChoice).equals(opponentChoice) ? LOSS : WIN;

    }
}
