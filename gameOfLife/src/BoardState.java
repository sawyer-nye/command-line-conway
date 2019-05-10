import java.util.Arrays;
import java.util.Random;

public class BoardState {
    private int board[][];
    private int width;
    private int height;

    public BoardState(int width, int height) {
        this.width = width;
        this.height = height;
        setBoardState(createRandomState(width, height));
    }

    public int[][] getBoardState() {
        return this.board;
    }

    public void setBoardState(int[][] newState) {
        this.board = newState;
    }

    public void printBoard(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            System.out.println(Arrays.toString(board[row]));
        }
    }

    public void renderBoard() {
        // draw a horizontal line
        char[] horizontalLine = new char[width + 2];
        for (int i = 0; i < horizontalLine.length; i++) {
            horizontalLine[i] = '-';
        }
        System.out.println(horizontalLine);

        for (int row = 0; row < board.length; row++) {
            System.out.print("|");  // left vertical bar

            for (int cell = 0; cell < board[row].length; cell++) {
                // cell is dead
                if (board[row][cell] == 0) {
                    System.out.print(" ");
                }
                // cell is alive
                else if (board[row][cell] == 1) {
                    System.out.print("@");
                }
            }
            System.out.printf("|%n");    // right vertical bar
        }
        System.out.println(horizontalLine); // draw another horizontal line
    }

    private int[][] createDeadState(int width, int height) {
        return (new int[height][width]);
    }

    private int[][] createRandomState(int width, int height) {
        int[][] newState = createDeadState(width, height);

        Random rand = new Random();

        for (int row = 0; row < newState.length; row++) {
            for (int column = 0; column < newState[row].length; column++) {
                newState[row][column] = rand.nextInt(2);
            }
        }

        return (newState);
    }
    // make public if used for unit testing purposes
    private int calculateNeighbors(int[][] board, int row, int column) {
        int neighbors = 0;
        final int[][] DIRECTIONS = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1},
                                    {0,1}, {1, -1}, {1, 0}, {1, 1}};

        for (int[] offset : DIRECTIONS) {
            int neighborRowIndex = offset[0] + row;
            int neighborColumnIndex = offset[1] + column;

            try {
                if (board[neighborRowIndex][neighborColumnIndex] == 1) {
                    neighbors++;
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                // ignored exception
            }
        }

        return neighbors;
    }

    public int[][] getNextState(int[][] state) {
        int[][] nextState = state;

        for (int row = 0; row < nextState.length; row++) {
            for (int column = 0; column < nextState[row].length; column++) {
                int neighbors = calculateNeighbors(nextState, row, column);

                // cell is alive
                if (state[row][column] == 1) {
                    if (neighbors == 0 | neighbors == 1) {      // check for underpopulation
                        nextState[row][column] = 0;
                    }
                    else if (neighbors == 2 | neighbors == 3) { // check for just right
                        nextState[row][column] = 1;
                    }
                    else if (neighbors > 3) {                   // check for overpopulation
                        nextState[row][column] = 0;
                    }
                }
                // cell is dead -> check for reproduction
                else if (state[row][column] == 0 & neighbors == 3) {
                    nextState[row][column] = 1;
                }
            }
        }

        return nextState;
    }


}
