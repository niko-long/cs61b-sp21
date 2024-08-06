package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author TODO: YOUR NAME HERE
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;

        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.
        for (int c = 0; c < board.size(); c +=1) {
            int m = 0;
            boolean mergeResult = false;
            for(int r = board.size()-1; r >= 0; r -= 1) {
                board.setViewingPerspective(side);
                Tile t = board.tile(c, r);
                int n = 0;

                if (board.tile(c,r) != null) {
                    for (int h = r + 1; h < board.size(); h += 1){
                        if (board.tile(c,h) == null) {
                        n += 1;
                        } else if (board.tile(c, r + n + 1) != null && tile(c, r).value() == tile(c, r+n+1).value() && mergeResult == false) {//else if (tile(c, r).value() == tile(c, h).value() && m == 0) { //这里一个问题卡了很久是想写 && 结果一开始写成了 || 符号 导致一test4一直过不去
                            n += 1;  //这里还是有一个 不太容易发现的bug 如果一列是满的 然后有两个tile的值隔着一个不为空的tile 就会移动
                            //else if (board.tile(c, r + n + 1) != null && tile(c, r).value() == tile(c, r+n+1).value() && m == 0)修改m == 0 这个麻烦的控制语句
                        }
                    }
                    mergeResult = board.move(c,r + n, t);

                    if (mergeResult == true) {
                        m += 1;
                        //System.out.println("m"); //用来debug
                        //System.out.println(m);
                        score += 2*t.value();
                    }

                }
                board.setViewingPerspective(Side.NORTH);
                if (n!=0) {
                    changed = true;
                }


            }
        }

        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        // TODO: Fill in this function.
        int n = 0;
        for (int i = 0; i < b.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                if (b.tile(i, j) == null) {
                    n += 1;
                } else {
                    n += 0;
                }
            }
        }
        return n != 0;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        // TODO: Fill in this function.

        int n = 0;
        int MAX_PIECE = 2048;
        for (int i = 0; i < b.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                Tile tile = b.tile(i, j);
                /* System.out.println(tile.value()); 加上这个本来是为了debug
                没想到成为了后边一直出bug的原因 :Cannot invoke "game2048.Tile.value()" because "tile" is null
                 */
                if (tile != null) {
                    if (tile.value() == MAX_PIECE){
                        n += 1;
                    } else{
                        n += 0;
                    }
                }
            }
        }
        return n != 0;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        // TODO: Fill in this function.
        int n = 0;
        if (emptySpaceExists(b)){
            n += 1;
        } else if(!emptySpaceExists(b)){
            for (int i = 1; i < b.size() -1 ; i++ ) {
                for (int j = 1; j < b.size() - 1; j++ ){
                    //Tile tile = b.tile(i, j);
                    //这里的判断语句虽然应该不是最简单的,但是一开始想的要更复杂
                    /*  if (b.tile(i, j).value() == b.tile(i-1, j).value() ||
                            b.tile(i, j).value() == b.tile(i+1, j).value() ||
                            b.tile(i, j).value() == b.tile(i, j-1).value() ||
                            b.tile(i, j).value() == b.tile(i, j+1).value())
                            这样存在一个问题 就是边上的tile 没有办法检验到,导致一个test过不去 才发现还有这个严重问题
                             第一反应是再补充if 语句 把在边上的行和列的特殊情况列出来 就会导致程序特别臃肿(虽然现在也不简洁)*/
                    if (b.tile(i-1, j-1).value() == b.tile(i-1, j).value() ||
                            b.tile(i+1, j).value() == b.tile(i+1, j+1).value() ||
                            b.tile(i, j-1).value() == b.tile(i+1, j-1).value() ||
                            b.tile(i, j+1).value() == b.tile(i+1, j+1).value()) {
                        n += 1;
                    }
                    //20230805 接着开始做 该考虑test里的testUpBasicMerge了

                }
            }
        }
        return n!=0;
    }


    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}
