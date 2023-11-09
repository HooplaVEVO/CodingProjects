import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Ship extends BattleshipGame {
    private boolean friendly;
    private boolean isVertical;
    private int length;
    private List<Integer> position;
    private String name;

    public Ship(boolean friend, boolean vertical, int l, String s, int cordinate) {
        friendly = friend;
        isVertical = vertical;
        length = l;
        name = s;
        position = calculatePosition(cordinate);
    }

    public boolean getVertical() {
        return isVertical;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getPosition() {
        return position;
    }

    public List<Integer> calculatePosition(int i) {
        LinkedList<Integer> shipPos = new LinkedList<>();
        if (this.getVertical()) {
            int j = 0;
            while (j < this.getLength()) {
                shipPos.add(i + (j * 8));
                j++;
            }
        } else {
            int j = 0;
            while (j < this.getLength()) {
                shipPos.add(i + j);
                j++;
            }
        }
        return shipPos;
    }

    public boolean isDestroyed(Board board, ComputerPlayer c) {
        boolean result = true;
        Iterator i = position.iterator();
        while (i.hasNext()) {
            int element = (int) i.next();
            if (board.getTile(element) != 2) {
                result = false;
            }
        }
        if (result && friendly) {
            c.setHitLastGuess(false);
        }
        return result;
    }
}
