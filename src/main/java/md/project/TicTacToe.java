package md.project;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[] strArray = new char[9];
        Arrays.fill(strArray, '_');

        printLayout(strArray);

        char player = 'X';

        while (true) {
            System.out.print("Enter the coordinates: ");

            String input = scanner.nextLine().trim(); // "3 3"
            String[] inputArr = input.split("\\s+"); // ["3", "3"]

            if (inputArr.length != 2 || inputArr[0].length() != 1 || inputArr[1].length() != 1 ||
                    !Character.isDigit(inputArr[0].charAt(0)) || !Character.isDigit(inputArr[1].charAt(0))) {
                System.out.println("You should enter numbers!");
                continue;
            }

            // transforms a string into an integer
            int coordX = Integer.parseInt(inputArr[0]); // 3 -> 2
            int coordY = Integer.parseInt(inputArr[1]); // 3 -> 2

            if ((coordX > 3) || (coordY > 3) || (coordX < 1) || (coordY < 1)) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue; // is used to start from the beginning of the while loop
            }

            int x = (coordX - 1) * 3 + (coordY - 1);

            if (strArray[x] == 'X' || strArray[x] == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
                continue; // is used to start from the beginning of the while loop
            }


            strArray[x] = player;
            printLayout(strArray);

            int numOfX = countChar('X', strArray);
            int numOfO = countChar('O', strArray);

            int difference = Math.abs(numOfX - numOfO);

            boolean xWon = playerWin('X', strArray);
            boolean oWon = playerWin('O', strArray);

            if (xWon) {
                System.out.println("X wins");
                break;
            } else if (oWon) {
                System.out.println("O wins");
                break;
            } else if (numOfX + numOfO == 9) {
                System.out.println("Draw");
                break;
            }


            player = player == 'X' ? 'O' : 'X';

        }
    }


    public static boolean playerWin(char ch, char[] game) {
        boolean rowWin =  playerRowWin(ch, game);
        boolean colWin =  playerColWin(ch, game);
        boolean diagonalWin = playerDiagonalWin(ch, game);

        return rowWin || colWin || diagonalWin;
    }

    public static boolean playerRowWin(char ch, char[] game) {

        return (game[0] == ch && game[1] == ch && game[2] == ch) ||
                (game[3] == ch && game[4] == ch && game[5] == ch) ||
                (game[6] == ch && game[7] == ch && game[8] == ch);

    }

    public static boolean playerColWin(char ch, char[] game) {

        return (game[0] == ch && game[3] == ch && game[6] == ch) ||
                (game[1] == ch && game[4] == ch && game[7] == ch) ||
                (game[2] == ch && game[5] == ch && game[8] == ch);

    }

    public static boolean playerDiagonalWin(char ch, char[] game) {

        return (game[0] == ch && game[4] == ch && game[8] == ch) ||
                (game[2] == ch && game[4] == ch && game[6] == ch);

    }

    public static int countChar(char ch, char[] game) {
        int count = 0;
        for (char c : game) {
            if (c == ch){
                count++;
            }
        }
        return count;
    }


    private static void printLayout(char[] arr) {
        if (arr.length <= 9) {
            System.out.println("---------");

            for (int i = 0; i < arr.length; i += 3) {
                System.out.println("| " + arr[i] + " " + arr[i + 1] + " " + arr[i + 2] + " |");
            }

            System.out.println("---------");
        } else {
            System.out.println("Too many symbols!");
        }
    }
}