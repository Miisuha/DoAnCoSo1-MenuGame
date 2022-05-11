package sudoku;

import java.util.*;

public class sudokuGenerator {

    public static sudokuPuzzle generateRandomSudoku(sudokuPuzzleType puzzleType) {

        sudokuPuzzle puzzle = new sudokuPuzzle(puzzleType.getRow(), puzzleType.getColumn(), puzzleType.getBoxWidth(), puzzleType.getBoxHeight(), puzzleType.getValidValues());

        Random randomGenerator = new Random();
        List<String> notUsedValidValues = new ArrayList<>(Arrays.asList(puzzle.getVALIDVALUES()));
        for(int r = 0; r < puzzle.getNumRows(); r++) {
            int RandomValue = randomGenerator.nextInt(notUsedValidValues.size());
            puzzle.makeMove(r, 0, notUsedValidValues.get(RandomValue));
            notUsedValidValues.remove(RandomValue);
        }


        return puzzle;

       
    }

}