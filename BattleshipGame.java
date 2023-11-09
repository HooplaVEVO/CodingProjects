import java.util.*;

public class BattleshipGame {
    public static void main(String[] args) {
        Board playerBoard = new Board();
        Board cpuBoard = new Board();
        ComputerPlayer cpu = new ComputerPlayer();
        List<Ship> pList = new LinkedList<>();
        pList = gameSetup(playerBoard);
        List<Ship> cList = new LinkedList<>();
        cList = computerPlaceShips(cpuBoard);
        gameLoop(playerBoard, cpuBoard, pList, cList, cpu);
    }

    public static void gameLoop(Board pBoard, Board cBoard, List<Ship> pList, List<Ship> cList, ComputerPlayer c) {
        while (true) {
            int guess = playerGuess();
            cBoard.setTile(guess, checkPlayerGuess(guess, cBoard));
            System.out.println(cBoard.toString());
            if (checkDestroyed(guess, cList, cBoard, c)) {
                System.out.println("You sank the enemy's " + getDestroyedShip(guess, cList, cBoard, c).getName() + "!");
            }
            if (checkWin(cBoard, cList)) {
                System.out.println("Player wins!");
                break;
            }
            int computerGuess = c.computerGuess(pBoard);
            pBoard.setTile(computerGuess, c.checkGuess(computerGuess, pBoard));
            System.out.println(pBoard.toString());
            if (checkDestroyed(computerGuess, pList, pBoard, c)) {
                System.out.println("The enemy sank your " + getDestroyedShip(computerGuess, pList, pBoard, c).getName() + "!");
            }
            if (checkWin(pBoard, pList)) {
                System.out.println("CPU wins!");
                break;
            }
        }
    }

    public static boolean checkDestroyed(int index, List<Ship> list, Board b, ComputerPlayer c) {
        boolean result = false;
        for (Ship ship : list) {
            for (int pos : ship.getPosition()) {
                if (index == pos) {
                    if (ship.isDestroyed(b, c)) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    public static Ship getDestroyedShip(int index, List<Ship> list, Board b, ComputerPlayer c) {
        Ship result = null;
        for (Ship ship : list) {
            for (int pos : ship.getPosition()) {
                if (index == pos) {
                    if (ship.isDestroyed(b, c)) {
                        result = ship;
                    }
                }
            }
        }
        return result;
    }

    public static boolean checkWin(Board b, List<Ship> l) {
        boolean result = true;
        for (Ship ship : l) {
            for (int pos : ship.getPosition()) {
                if (b.getTile(pos) != 2) {
                    result = false;
                }
            }
        }
        return result;
    }

    public static List<Ship> gameSetup(Board board) {
        List<Ship> result = new LinkedList<>();
        System.out.println(board.toString());
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("[O]\n[ ]\n[ ]\n[ ]\n[ ]");
            System.out.println("Enter a tile to place your Carrier:");
            String input = s.nextLine().toUpperCase();
            if (correctFormat(input) && checkPositions(result, (checkCoordinate(true, 5, toIndex(input))))) {
                Ship s1 = new Ship(true, true, 5, "Carrier", toIndex(input));
                result.add(s1);
                for (int index : s1.getPosition()) {
                    board.setTile(index, 3);
                }
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
        System.out.println(board.toString());
        while (true) {
            System.out.println("[O] [ ] [ ] [ ]");
            System.out.println("Enter a tile to place your Battleship:");
            String input = s.nextLine().toUpperCase();
            if (correctFormat(input) && checkPositions(result, (checkCoordinate(false, 4, toIndex(input))))) {
                Ship s2 = new Ship(true, false, 4, "Battleship", toIndex(input));
                result.add(s2);
                for (int index : s2.getPosition()) {
                    board.setTile(index, 3);
                }
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
        System.out.println(board.toString());
        while (true) {
            System.out.println("[O]\n[ ]\n[ ]\n[ ]");
            System.out.println("Enter a tile to place your Battleship:");
            String input = s.nextLine().toUpperCase();
            if (correctFormat(input) && checkPositions(result, (checkCoordinate(true, 4, toIndex(input))))) {
                Ship s3 = new Ship(true, true, 4, "Battleship", toIndex(input));
                result.add(s3);
                for (int index : s3.getPosition()) {
                    board.setTile(index, 3);
                }
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
        System.out.println(board.toString());
        while (true) {
            System.out.println("[O] [ ] [ ]");
            System.out.println("Enter a tile to place your Cruiser:");
            String input = s.nextLine().toUpperCase();
            if (correctFormat(input) && checkPositions(result, (checkCoordinate(false, 3, toIndex(input))))) {
                Ship s4 = new Ship(true, false, 3, "Cruiser", toIndex(input));
                result.add(s4);
                for (int index : s4.getPosition()) {
                    board.setTile(index, 3);
                }
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
        System.out.println(board.toString());
        while (true) {
            System.out.println("[O] [ ] [ ]");
            System.out.println("Enter a tile to place your Cruiser:");
            String input = s.nextLine().toUpperCase();
            if (correctFormat(input) && checkPositions(result, (checkCoordinate(false, 3, toIndex(input))))) {
                Ship s5 = new Ship(true, false, 3, "Cruiser", toIndex(input));
                result.add(s5);
                for (int index : s5.getPosition()) {
                    board.setTile(index, 3);
                }
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
        System.out.println(board.toString());
        while (true) {
            System.out.println("[O]\n[ ]\n[ ]");
            System.out.println("Enter a tile to place your Cruiser:");
            String input = s.nextLine().toUpperCase();
            if (correctFormat(input) && checkPositions(result, (checkCoordinate(true, 3, toIndex(input))))) {
                Ship s6 = new Ship(true, true, 3, "Cruiser", toIndex(input));
                result.add(s6);
                for (int index : s6.getPosition()) {
                    board.setTile(index, 3);
                }
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
        System.out.println(board.toString());
        return result;
    }

    public static int checkPlayerGuess(int index, Board board) {
        int result = 1;
        if (board.getTile(index) % 2 == 0 && board.getTile(index) != 0) {
            result = 2;
        }
        return result;
    }

    public static int playerGuess() {
        String guess;
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a tile to fire on:");
            guess = s.nextLine();
            if (correctFormat(guess)) {
                break;
            } else {
                System.out.println("Invalid input.");
            }
        }
        return toIndex(guess);
    }

    public static boolean checkPositions(List<Ship> shipList, List<Integer> posList) {
        boolean result = true;
        for (Integer j : posList) {
            if (j > 63) {
                return false;
            }
        }
        for (Ship ship : shipList) {
            List<Integer> tempList = ship.getPosition();
            for (Integer i : tempList) {
                for (Integer j : posList) {
                    if (j.equals(i)) {
                        return false;
                    }
                }
            }
        }
        return result;
    }

    public static List<Integer> checkCoordinate(boolean b, int l, int index) {//Calculates a list of indexes where the ship is.
        LinkedList<Integer> shipPos = new LinkedList<>();
        if (b) {
            int j = 0;
            while (j < l) {
                shipPos.add(index + (j * 8));
                j++;
            }
        } else {
            int j = 0;
            while (j < l) {
                shipPos.add(index + j);
                j++;
            }
        }
        return shipPos;
    }

    public static boolean correctFormat(String s) {
        boolean result = false;
        if (s.length() == 2) {
            if (s.startsWith("A") || s.startsWith("B") || s.startsWith("C") || s.startsWith("D") || s.startsWith("E") || s.startsWith("F") || s.startsWith("G") || s.startsWith("H")) {
                if (s.endsWith("1") || s.endsWith("2") || s.endsWith("3") || s.endsWith("4") || s.endsWith("5") || s.endsWith("6") || s.endsWith("7") || s.endsWith("8")) {
                    result = true;
                }
            }
        }
        return result;
    }

    public static int toIndex(String s) {
        int result = 0;
        s = s.toUpperCase();
        if (s.startsWith("B")) {
            result += 8;
        } else if (s.startsWith("C")) {
            result += 16;
        } else if (s.startsWith("D")) {
            result += 24;
        } else if (s.startsWith("E")) {
            result += 32;
        } else if (s.startsWith("F")) {
            result += 40;
        } else if (s.startsWith("G")) {
            result += 48;
        } else if (s.startsWith("H")) {
            result += 56;
        }
        if (s.contains("2")) {
            result += 1;
        } else if (s.contains("3")) {
            result += 2;
        } else if (s.contains("4")) {
            result += 3;
        } else if (s.contains("5")) {
            result += 4;
        } else if (s.contains("6")) {
            result += 5;
        } else if (s.contains("7")) {
            result += 6;
        } else if (s.contains("8")) {
            result += 7;
        }
        return result;
    }

    public static List<Ship> computerPlaceShips(Board board) {
        List<Ship> result = new LinkedList<>();
        Random rand = new Random();
        while (true) {
            int input = rand.nextInt(64);
            if (checkPositions(result, (checkCoordinate(true, 5, input)))) {
                Ship es1 = new Ship(false, true, 5, "Carrier", input);
                result.add(es1);
                for (int index : es1.getPosition()) {
                    board.setTile(index, 4);
                }
                break;
            }
        }
        while (true) {
            int input = rand.nextInt(64);
            if (checkPositions(result, (checkCoordinate(false, 4, input)))) {
                Ship es2 = new Ship(false, false, 4, "Battleship", input);
                result.add(es2);
                for (int index : es2.getPosition()) {
                    board.setTile(index, 4);
                }
                break;
            }
        }
        while (true) {
            int input = rand.nextInt(64);
            if (checkPositions(result, (checkCoordinate(true, 4, input)))) {
                Ship es3 = new Ship(false, true, 4, "Battleship", input);
                result.add(es3);
                for (int index : es3.getPosition()) {
                    board.setTile(index, 4);
                }
                break;
            }
        }
        while (true) {
            int input = rand.nextInt(64);
            if (checkPositions(result, (checkCoordinate(false, 3, input)))) {
                Ship es4 = new Ship(false, false, 3, "Cruiser", input);
                result.add(es4);
                for (int index : es4.getPosition()) {
                    board.setTile(index, 4);
                }
                break;
            }
        }
        while (true) {
            int input = rand.nextInt(64);
            if (checkPositions(result, (checkCoordinate(false, 3, input)))) {
                Ship es5 = new Ship(false, false, 3, "Cruiser", input);
                result.add(es5);
                for (int index : es5.getPosition()) {
                    board.setTile(index, 4);
                }
                break;
            }
        }
        while (true) {
            int input = rand.nextInt(64);
            if (checkPositions(result, (checkCoordinate(true, 3, input)))) {
                Ship es6 = new Ship(false, true, 3, "Cruiser", input);
                result.add(es6);
                for (int index : es6.getPosition()) {
                    board.setTile(index, 4);
                }
                break;
            }
        }
        return result;
    }
}
