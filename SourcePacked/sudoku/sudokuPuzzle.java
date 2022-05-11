package sudoku;

public class sudokuPuzzle {

    protected String[][] board;
    private  final int ROWS;
    private  final int COLUMNS;
    private  final int BOX_WIDTH;
    private  final int BOX_HEIGHT;
    private  final String[] VALIDVALUES;

    public sudokuPuzzle(int rows, int cols, int boxWidth, int boxHeight, String[] validValues) {
        this.ROWS = rows;
        this.COLUMNS = cols;
        this.BOX_WIDTH = boxWidth;
        this.BOX_HEIGHT = boxHeight;
        this.VALIDVALUES = validValues;
        this.board = new String[ROWS][COLUMNS];
        initializeBoard();
    }

    public int getNumRows() {
        return ROWS;
    }

    public int getNumColumns() {
        return COLUMNS;
    }

    public int getBOXWIDTH() {  
        return BOX_WIDTH;
    }

    public int getBOXHEIGHT() {
        return BOX_HEIGHT;
    }

    public String[] getVALIDVALUES() {
        return VALIDVALUES;
    }

    public String toString() {
        String str = "Game Board:\n";
        for(int row = 0; row < this.ROWS; row++) {
            for(int col = 0; col < this.COLUMNS; col++) {
                str += this.board[row][col] + " ";
            }
            str += "\n";
        } 

        return str + "\n";
    }

    private void initializeBoard() {
        for(int row = 0; row < this.ROWS; row++) {
            for(int col = 0; col < this.COLUMNS; col++) {
                this.board[row][col] = "";
            }
        } 
    }

    public void makeMove(int row, int col, String value) {
        this.board[row][col] = value;
    }

}
