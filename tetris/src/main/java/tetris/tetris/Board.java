package tetris.tetris;

public class Board {

    private int height;
    private int width;
    private int[][] board;

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        this.board = new int[this.height][this.width];
        emptyBoard();

    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int[][] getBoard() {
        return board;
    }

    public void emptyBoard() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.board[i][j] = 0;
            }
        }
    }

    public boolean isPointEmpty(int x, int y) {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            return x >= 0 && x < this.width && y < 0;    //true jos laudan yläpuolella
        } else {
            return this.board[y][x] == 0;
        }
    }

    public void removeFullRows() {
        for (int y = height - 1; y >= 0; y--) {
            if (isRowFull(y)) {
                removeRow(y);
                y++;
            }
        }
    }

    public boolean isRowFull(int y) {
//        if (y < 0 || y >= height) {   //laudan ulkopuolella true/false?
//            return true;
//        }
        for (int x = 0; x < this.width; x++) {
            if (this.board[y][x] == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isRowEmpty(int y) {
//        if (y < 0 || y >= height) {   //laudan ulkopuolella true/false?
//            return false;
//        }
        for (int x = 0; x < this.width; x++) {
            if (this.board[y][x] != 0) {
                return false;
            }
        }
        return true;
    }

    public void removeRow(int y) {
        if (y < 0 || y >= this.height) {
            return;
        }
        for (int z = y; z > 0; z--) {
            for (int x = 0; x < this.width; x++) {
                this.board[z][x] = this.board[z - 1][x];
            }
        }
        for (int x = 0; x < width; x++) {     //uusi rivi ylös?
            this.board[0][x] = 0;
        }
    }

    public int getPoint(int x, int y) {  //eri väriset/muotoiset palikat eri int?
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            return 0;
        } else {
            return this.board[y][x];
        }
    }
}
