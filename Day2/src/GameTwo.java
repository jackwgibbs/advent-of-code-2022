import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GameTwo {
    private static final HashMap<Character, Integer> choiceScores = new HashMap<>();
    private static final HashMap<Character, Integer> outcomeScores = new HashMap<>();
    private static final HashMap<Character, Character> toWin = new HashMap<>();
    private static final HashMap<Character, Character> toLose = new HashMap<>();
    private static final int WIN = 6;
    private static final int DRAW = 3;
    private static final int LOSS = 0;
    private final ArrayList<Character> opponentChoice = new ArrayList<>();
    private final ArrayList<Character> gameOutcome = new ArrayList<>();

    /**
     * Constructor to set up the game. Read in input file and populate
     * the opponent choices and round outcomes. Also construct score map and game rules
     * @param filename - the name of the input f
     */
    public GameTwo(String filename) throws FileNotFoundException {
        // set up game by reading in file
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            opponentChoice.add(line.charAt(0));
            gameOutcome.add(line.charAt(2));
        }

        // initialise score map
        choiceScores.put('X', 1);
        choiceScores.put('Y', 2);
        choiceScores.put('Z', 3);
        choiceScores.put('A', 1);
        choiceScores.put('B', 2);
        choiceScores.put('C', 3);

        // set up game rules
        /// X, Y, Z = A, B, C = Rock, Paper, Scissors
        toWin.put('A', 'Y'); // opponent chooses rock, to win player to chose paper
        toWin.put('B', 'Z'); // opponent chooses paper, to win player to chose scissors
        toWin.put('C', 'X'); // opponent chooses scissors, to win player to chose rock

        toLose.put('A', 'Z'); // opponent chooses rock, to lose player to chose scissors
        toLose.put('B', 'X'); // opponent chooses paper, to lose player to chose rock
        toLose.put('C', 'Y'); // opponent chooses scissors, to lose player to chose paper

        // initialise mapping between X, Y, Z and outcome of round
        outcomeScores.put('X', LOSS);
        outcomeScores.put('Y', DRAW);
        outcomeScores.put('Z', WIN);
    }

    /**
     * Main method to calculate the players total score.
     * @return the total score of the player for all rounds of the game
     */
    public int getGameScoreForPlayer(){
        int numOfRounds = opponentChoice.size();
        int score = 0;
        for (int i = 0; i < numOfRounds; i++){
            score += outcomeScore(gameOutcome.get(i)); // score from round outcome
            score += playerChoice(gameOutcome.get(i), opponentChoice.get(i)); // score from choice
        }
        return score;
    }

    /**
     * Method to return the score of the outcome of the round
     * Loss = 0
     * Draw = 3
     * Win = 6
     * @return the outcome score
     */
    public int outcomeScore(char outcome){
        return outcomeScores.get(outcome);
    }

    /**
     * Method to calculate which choice player to make based on opponents choice
     * and intended outcome of the round. Returns score of that choice
     * @param outcome the intended outcome of the round
     * @param opponentsChoice the choice of the opponent
     * @return the score of the players calculated choice
     */
    public int playerChoice(char outcome, char opponentsChoice) {
        if (outcome == 'Y') {
            return choiceScores.get(opponentsChoice);
        }else if (outcome == 'Z') {
            return choiceScores.get(toWin.get(opponentsChoice));
        }else{
            return choiceScores.get(toLose.get(opponentsChoice));
        }
    }
}
