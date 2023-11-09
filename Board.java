import java.util.List;

public class Board {
    private int[] guessArray;

    public Board() {
        int[] board = new int[64];
        for (int i = 0; i < 64; i++) {
            board[i] = 0;
        }
        guessArray = board;
    }

    public int getTile(int i) {
        return guessArray[i];
    }

    public void setTile(int index, int i) {
        guessArray[index] = i;
    }

    public String toString() {
        String result = "";
        result += ("    1   2   3   4   5   6   7   8\n");
        List<String> charList = List.of("A", "B", "C", "D", "E", "F", "G", "H");
        for (int i = 0; i < 8; i++) {
            result += drawBody(charList.get(i), i * 8);
        }
        return result;
    }

    public String drawBody(String s, int index) {
        String result = s;
        for (int i = 0; i < 8; i++) {
            if (this.getTile(index) == 3) {
                result += "|[ ]";
            } else if (this.getTile(index) == 1) {
                result += "| O ";
            } else if (this.getTile(index) == 2) {
                result += "| X ";
            } else {
                result += "|   ";
            }
            index++;
        }
        result += "|\n";
        return result;
    }
}
