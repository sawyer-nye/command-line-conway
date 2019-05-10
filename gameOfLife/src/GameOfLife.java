import java.util.Arrays;

public class GameOfLife {

    public static void main(String[] args) {
        BoardState gameBoard = new BoardState(100, 10);
        for (int i = 0; i < 12000; i++) {
            gameBoard.renderBoard();
            gameBoard.setBoardState(gameBoard.getNextState(
                                    gameBoard.getBoardState()));
        }
        //gameBoard.printBoard();
        //gameBoard.renderBoard();

        /* UNIT TESTS
        // 1: all dead with no neighbors should stay dead
        int[][] initState1 = {
            {0,0,0},
            {0,0,0},
            {0,0,0}
        };
        int[][] expectedState1 = {
            {0,0,0},
            {0,0,0},
            {0,0,0}
        };
        int[][] actualNextState1 = gameBoard.getNextState(initState1);

        if (Arrays.deepEquals(actualNextState1, expectedState1)) {
            System.out.println("PASSED 1");
        }
        else {
            System.out.println("FAILED 1");
            System.out.println("Actual:");
            gameBoard.printBoard(actualNextState1);
            System.out.println("Expected:");
            gameBoard.printBoard(expectedState1);
        }

        // 2: dead cells with 3 neighbors should reanimate
        int[][] initState2 = {
                {1,1,0},
                {1,0,0},
                {0,0,0}
        };
        int[][] expectedState2 = {
                {1,1,0},
                {1,1,0},
                {0,0,0}
        };
        int[][] actualNextState2 = gameBoard.getNextState(initState2);

        if (Arrays.deepEquals(actualNextState2, expectedState2)) {
            System.out.println("PASSED 2");
        }
        else {
            System.out.println("FAILED 2");
            System.out.println("Actual:");
            gameBoard.printBoard(actualNextState2);
            System.out.println("Expected:");
            gameBoard.printBoard(expectedState2);
        }
        System.out.println("Neighbors: " + gameBoard.calculateNeighbors(initState2, 1, 1));
        */
    }
}
