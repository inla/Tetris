package tetris.logic;

public class Board {

    private final static int HEIGHT = 20;
    private final static int WIDTH = 10;
    private final int[][] board;

    public Board() {
        this.board = new int[HEIGHT][WIDTH];
        //emptyBoard();
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int[][] getBoard() {
        return board;
    }

    public void emptyBoard() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                this.board[i][j] = 0;
            }
        }
    }

    public void addTetrimino(Tetrimino tetrimino) {
        for (int i = 0; i < tetrimino.getCurrentRotation().length; i++) {
            for (int j = 0; j < tetrimino.getCurrentRotation()[i].length; j++) {
                if (tetrimino.getCurrentRotation()[i][j] == 0) {
                    continue;                 // --|
                }                             //   |
                int x = j + tetrimino.getX(); //   v
                int y = i + tetrimino.getY();
                setPoint(x, y, 1);  //eri muodot eri int??
            }
        }
    }

    public boolean isPointEmpty(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return x >= 0 && x < WIDTH && y < 0;    //true jos laudan yläpuolella
        } else {
            return this.board[y][x] == 0;
        }
    }

    public void removeFullRows() {
        for (int y = HEIGHT - 1; y >= 0; y--) {
            if (isRowFull(y)) {
                removeRow(y);
                y++;
            }
        }
    }

    public boolean isRowFull(int y) {
//        if (y < 0 || y >= HEIGHT) {   //laudan ulkopuolella true/false?
//            return true;
//        }
        for (int x = 0; x < WIDTH; x++) {
            if (this.board[y][x] == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isRowEmpty(int y) {
//        if (y < 0 || y >= HEIGHT) {   //laudan ulkopuolella true/false?
//            return false;
//        }
        for (int x = 0; x < WIDTH; x++) {
            if (this.board[y][x] != 0) {
                return false;
            }
        }
        return true;
    }

    public void removeRow(int y) {
        if (y < 0 || y >= HEIGHT) {
            return;
        }
        for (int z = y; z > 0; z--) {       //ylemmät rivit yksi alemmas
            for (int x = 0; x < WIDTH; x++) {
                this.board[z][x] = this.board[z - 1][x];
            }
        }
        for (int x = 0; x < WIDTH; x++) {     //uusi rivi ylös?
            this.board[0][x] = 0;
        }
    }

    public void setPoint(int x, int y, int type) {
        this.board[y][x] = type;
    }

    public int getPoint(int x, int y) {  //eri väriset/muotoiset palikat eri int?
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return 0;
        } else {
            return this.board[y][x];
        }
    }
}
