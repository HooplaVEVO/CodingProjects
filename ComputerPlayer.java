import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer {
    private static boolean hitLastGuess;
    private int lastHitPos;
    private int firstHitPos;
    private List<Integer> sideList;

    public ComputerPlayer() {
        boolean hitLastGuess = false;
        int firstHitPos = 0;
        int lasHitPos = 0;
        List<Integer> sideList = new LinkedList<>();
    }

    public static void setHitLastGuess(boolean b) {
        hitLastGuess = b;
    }
    
    public int checkGuess(int i, Board b) {
        int result = 1;
        if (b.getTile(i) == 3) {
            result = 2;
        }
        return result;
    }

    public int computerGuess(Board b) {
        int index = 0;
        if (!hitLastGuess) {
            while (true) {
                Random rand = new Random();
                index = rand.nextInt(64);
                if (checkTile(b, index) == 0 || checkTile(b, index) == 3) {
                    if (this.checkGuess(index, b) == 2) {
                        hitLastGuess = true;
                        firstHitPos = index;
                        lastHitPos = index;
                        sideList = updateSideList(b);
                    }
                    break;
                }
            }
        } else {
            index = this.toIndex(sideList, b);
            if (checkGuess(index, b) == 2) {
                lastHitPos = index;
                sideList = updateSideList(b);
            }
        }
        return index;
    }

    public int checkTile(Board b, int i) {
        return b.getTile(i);
    }

    public int toIndex(List<Integer> list, Board b) {
        if (sideList.isEmpty()) {
            if (lastHitPos == firstHitPos) {
                hitLastGuess = false;
                return computerGuess(b);
            }
            lastHitPos = firstHitPos;
            sideList = updateSideList(b);
        }
        int result = 0;
        Random rand = new Random();
        int index = rand.nextInt(list.size() + 1) - 1;
        if (index < 0) {
            index += 1;
        }
        int tile = sideList.get(index);
        sideList.remove(sideList.indexOf(tile));
        if (tile == 1) {
            result = lastHitPos - 8;
            if (b.getTile(result) != 0) {
                this.toIndex(list, b);
            }
        } else if (tile == 2) {
            result = lastHitPos + 1;
            if (b.getTile(result) != 0) {
                this.toIndex(list, b);
            }
        } else if (tile == 3) {
            result = lastHitPos + 8;
            if (b.getTile(result) != 0) {
                this.toIndex(list, b);
            }
        } else {
            result = lastHitPos - 1;
            if (b.getTile(result) != 0) {
                this.toIndex(list, b);
            }
        }
        return result;
    }

    public List<Integer> updateSideList(Board b) {
        List<Integer> posList = new LinkedList<>();
        if (lastHitPos == 0) {
            posList.add(2);
            posList.add(3);
        } else if (lastHitPos == 7) {
            posList.add(3);
            posList.add(4);
        } else if (lastHitPos == 63) {
            posList.add(4);
            posList.add(1);
        } else if (lastHitPos == 56) {
            posList.add(1);
            posList.add(2);
        } else if (lastHitPos % 8 == 0) {
            posList.add(1);
            posList.add(2);
            posList.add(3);
        } else if (lastHitPos < 8) {
            posList.add(2);
            posList.add(3);
            posList.add(4);
        } else if (lastHitPos > 55) {
            posList.add(1);
            posList.add(2);
            posList.add(4);
        } else if (lastHitPos % 8 == 7) {
            posList.add(1);
            posList.add(3);
            posList.add(4);
        } else {
            posList.add(1);
            posList.add(2);
            posList.add(3);
            posList.add(4);
        }
        if (lastHitPos > 7 && posList.contains(1)) {
            if (b.getTile(lastHitPos - 8) == 2 || b.getTile(lastHitPos - 8) == 1) {
                posList.remove(posList.indexOf(1));
            }
        }
        if (lastHitPos < 63 && posList.contains(2)) {
            if (b.getTile(lastHitPos + 1) == 2 || b.getTile(lastHitPos + 1) == 1) {
                posList.remove(posList.indexOf(2));
            }
        }
        if (lastHitPos < 55 && posList.contains(3)) {
            if (b.getTile(lastHitPos + 8) == 2 || b.getTile(lastHitPos + 8) == 1) {
                posList.remove(posList.indexOf(3));
            }
        }
        if (lastHitPos > 0 && posList.contains(4)) {
            if (b.getTile(lastHitPos - 1) == 2 || b.getTile(lastHitPos - 1) == 1) {
                posList.remove(posList.indexOf(4));
            }
        }
        return posList;
    }
}
