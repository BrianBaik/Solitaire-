import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import javax.swing.JFileChooser;
public class SpiderSolitaire
{
    /** Number of stacks on the board **/
    public final int NUM_STACKS = 7;

    /** Number of complete decks used in this game.  A 1-suit deck, which is the
     *  default for this lab, consists of 13 cards (Ace through King).
     */
    public final int NUM_DECKS = 4;

    /** A Board contains stacks and a draw pile **/
    private Board board;

    /** Used for keyboard input **/
    private Scanner input;

    public SpiderSolitaire()
    {
        // Start a new game with NUM_STACKS stacks and NUM_DECKS of cards
        board = new Board(NUM_STACKS, NUM_DECKS);
        input = new Scanner(System.in);
    }

    /** Main game loop that plays games until user wins or quits **/
    public void play() {

        board.printBoard();
        boolean gameOver = false;

        while(!gameOver) {
            System.out.println("\nCommands:");
            System.out.println("   move [card] [source_stack] [destination_stack]");
            System.out.println("   draw");
            System.out.println("   clear [source_stack]");
            System.out.println("   restart");
            System.out.println("   quit");
            System.out.print(">");
            String command = input.next();

            if (command.equals("move")) {
                /* *** TO BE MODIFIED IN ACTIVITY 5 *** */
                String symbol;
                int sourceStack;
                int destinationStack;
                try{
                    symbol = input.next();
                    sourceStack = input.nextInt();
                    destinationStack = input.nextInt();
                    board.makeMove(symbol, sourceStack - 1, destinationStack - 1);
                }catch(InputMismatchException e){

                    System.out.println("Invalid command. \n Error: " + e.getMessage());
                }
            }
            else if (command.equals("draw")) {
                board.drawCards();
            }
            else if (command.equals("clear")) {
                /* *** TO BE MODIFIED IN ACTIVITY 5 *** */
                int sourceStack;
                try {
                    sourceStack = input.nextInt();
                    board.clear(sourceStack - 1);
                }
                catch (InputMismatchException e) {
                    System.out.println("Invalid source stack number. \n Error: " + e.getMessage());

                }

            }
            else if (command.equals("restart")) {
                board = new Board(NUM_STACKS, NUM_DECKS);
            }
            else if (command.equals("quit")) {
                System.out.println("Goodbye!");
                System.exit(0);
            } else if(command.equals("load")) {
                JFileChooser chooser = new JFileChooser(".");
                chooser.showOpenDialog(null);
                File selectedFile = chooser.getSelectedFile();
                FileInputStream f;
                while(selectedFile != null) {
                    try {
                        f = new FileInputStream(selectedFile);
                        byte [] data = new byte[(int)selectedFile.length()];
                        f.read(data);
                        f.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            } else if (command.equals("save")) {
                JFileChooser choose = new JFileChooser(".");
                choose.showSaveDialog(null);
                File sFile = choose.getSelectedFile();
                FileWriter w;
                while(sFile != null) {
                    try {
                        w = new FileWriter(sFile);
                        w.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
            else {
                System.out.println("Invalid command.");
            }

            board.printBoard();

            // If all stacks and the draw pile are clear, you win!
            if (board.isEmpty()) {
                gameOver = true;
            }
        }
        System.out.println("Congratulations!  You win!");
    }
}
