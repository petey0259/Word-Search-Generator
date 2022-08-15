//Peter Hovenier
//CS 145 Data Structures
//August 8, 2022
//Darrell Criss
//Assignment 1: Word Search Generator
import java.util.*;
//class for the word search
public class WordSearch {  
   private static char[][] grid;
   private static boolean[][] sol;
   private static String[] words;
   //main method. Contains the menu for the word search program
   public static void main(String[] args) {
      listCommands();
      Scanner input = new Scanner(System.in);
      String command = input.nextLine().toLowerCase();
      while (!command.equals("q")) {
         switch (command) {
            case "g":
               generate(input);
               break;
            case "p":
               print();
               break;
            case "s":
               solution();
               break;
            case "q":
               break;
         }
         listCommands();
         command = input.nextLine().toLowerCase();
      }
   
   }   
   //Takes input of 5 words to be the words in the word search Then places the words into the grid
   //and fills the grid with random letters
   public static void generate(Scanner input) {
      System.out.println("Please enter 5 words for the word search");
      System.out.print("Word one: ");
      String wordOne = input.next().toLowerCase();
      System.out.print("Word two: ");
      String wordTwo = input.next().toLowerCase();
      System.out.print("Word three: ");
      String wordThree = input.next().toLowerCase();
      System.out.print("Word four: ");
      String wordFour = input.next().toLowerCase();
      System.out.print("Word five: ");
      String wordFive = input.next().toLowerCase();
      String[] words = {wordOne, wordTwo, wordThree, wordFour, wordFive};
      char[][] wordChars = setGrid(words);
      for (int i = 0; i < wordChars.length; i++) {
         placeWord(wordChars, i);
      }
      fillGrid();
   }
   
   //Prints out the word search
   public static void print() {
      String result = "";
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            result += " " + grid[i][j] + " ";
         }
         result += "\r\n";
      }
      System.out.println(result);
   }
   //Prints the solution to the word search
   public static void solution() {
      String result = "";
      for (int i = 0; i< grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            if (sol[i][j]) {
               result += " " + grid[i][j] + " ";
            } else {
               result += " X ";
            }
         }
         result += "\r\n";
      }
      System.out.println(result);
   }
      //Determines the direction and order of the words and places them into a random spot in the word search
      public static void placeWord(char[][] wordChars, int iterate) {
         Random rand = new Random();
         int dir = rand.nextInt(3);
         int reverse = rand.nextInt(2);
         int[] cell = {0, 0};
         if (dir == 0) { //Horizontal
            boolean placed = false;
            while (!placed) {
               cell[0] = rand.nextInt((grid.length - 1) - wordChars[iterate].length);
               cell[1] = rand.nextInt((grid.length - 1) - wordChars[iterate].length);
               placed = true;
            }
            if (placed && reverse == 0) { //Forward
               for (int i = 0; i < wordChars[iterate].length; i++) {
                  grid[cell[0]][cell[1]] = wordChars[iterate][i];
                  sol[cell[0]][cell[1]] = true;
                  cell[0]++;
               }
            }
            if (placed && reverse == 1) { // Reverse
               for (int i = wordChars[iterate].length - 1; i >= 0; i--) {
                  grid[cell[0]][cell[1]] = wordChars[iterate][i];
                  sol[cell[0]][cell[1]] = true;
                  cell[0]++;
               }
            }
         } else if (dir == 1) { // Vertical
            boolean placed = false;
            reverse = rand.nextInt(2);
            while (!placed) {
               cell[0] = rand.nextInt((grid.length - 1) - wordChars[iterate].length);
               cell[1] = rand.nextInt((grid.length - 1) - wordChars[iterate].length);
               placed = true;
            }
            if (placed && reverse == 0) { //Forward
               for (int i = 0; i < wordChars[iterate].length; i++) {
                  grid[cell[0]][cell[1]] = wordChars[iterate][i];
                  sol[cell[0]][cell[1]] = true;
                  cell[1]++;
               }
            }
            if (placed && reverse == 1) { //Reverse
               for (int i = wordChars[iterate].length - 1; i >= 0; i--) {
                  grid[cell[0]][cell[1]] = wordChars[iterate][i];
                  sol[cell[0]][cell[1]] = true;
                  cell[1]++;
               }
            }
            
         } else if (dir == 2) { //Diagonal
            boolean placed = false;
            reverse = rand.nextInt(2);
            while (!placed) {
               cell[0] = rand.nextInt((grid.length - 1) - wordChars[iterate].length);
               cell[1] = rand.nextInt((grid.length - 1) - wordChars[iterate].length);
               placed = true;
            }
            if (placed && reverse == 0) { //Forward
               for (int i = 0; i < wordChars[iterate].length; i++) {
                  grid[cell[0]][cell[1]] = wordChars[iterate][i];
                  sol[cell[0]][cell[1]] = true;
                  cell[1]++;
                  cell[0]++;
               }
            }
            if (placed && reverse == 1) { //Reverse
               for (int i = wordChars[iterate].length - 1; i >= 0; i--) {
                  grid[cell[0]][cell[1]] = wordChars[iterate][i];
                  sol[cell[0]][cell[1]] = true;
                  cell[1]++;
                  cell[0]++;
               }
            }
         }
      }
   //Sets up the wordChars array and also sets the size of the grid for the word search
   public static char[][] setGrid(String[] words) {
      char[][] wordChars = new char[words.length][];
      for (int i = 0; i < words.length; i++) {
         wordChars[i] = words[i].toCharArray();
      }
      grid = new char[20][20];
      sol = new boolean[20][20];
      return wordChars;
   }
   //Fills all of the blank cells in the array with a random lowercase letter
   public static void fillGrid() {
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            Random rand = new Random();
            if (grid[i][j] == '\u0000') {
               grid[i][j] = (char) (rand.nextInt(26) + 97);
            }
         }
      }
   }
   //Lists the commands for the menu of the program
   public static void listCommands() {
      System.out.println("Welcome to the Word search generator");
      System.out.println("Please enter a command");
      System.out.println("(g) to generate a word search");
      System.out.println("(p) to print out your word search");
      System.out.println("(s) to view the solution to the word search");
      System.out.println("(q) to quit the program");
   }
}