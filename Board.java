import java.util.*;

public class Board
{   
    /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
    // Attributes
    private int numStacks;
    private int numDecks;
    private ArrayList<Deck> stackDeck = new ArrayList<>();
    //Deck stackDeck = new Deck();
    private ArrayList <Deck> newDeck = new ArrayList<>();
    private Deck drawPile = new Deck();
    /**
     *  Sets up the Board and fills the stacks and draw pile from a Deck
     *  consisting of numDecks Decks.  Here are examples:
     *  
     *  # numDecks     #cards in overall Deck
     *      1          13 (all same suit)
     *      2          26 (all same suit)
     *      3          39 (all same suit)
     *      4          52 (all same suit)
     *      
     *  Once the overall Deck is built, it is shuffled and half the cards
     *  are placed as evenly as possible into the stacks.  The other half
     *  of the cards remain in the draw pile.
     */    
    public Board(int numStacks, int numDecks) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
        this.numStacks = numStacks;
        this.numDecks = numDecks;
        Deck d = new Deck();
        Card card;

        String[] symbol = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
        int[] value= {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        ArrayList<Deck> fullD = new ArrayList<>();
        Deck fd = new Deck();

        for(int i =0; i<numDecks;i++) {
            for(int j=0; j<symbol.length; j++) {
                card = new Card(symbol[j], value[j]);
                card.setFaceUp(false);
                fd.addCard(card);
            }
        }
        fd.shuffle();
        for(int i=0; i<numStacks; i++) {
            stackDeck.add(new Deck());
        }
        int ind = 0;
        for(int i =0; i<fd.getLength()/2; i++) {
            while(ind > numStacks-1) {
                ind = 0;
            }
            stackDeck.get(ind).addCard(fd.get(i));
            ind++;
        }

        for(int i =fd.getLength()/2; i<fd.getLength(); i++) {
            drawPile.addCard(fd.get(i));
            //drawPile.addCard(fd.get(i));
            fd.remove(i);
        }
        drawPile.shuffle();
        for(int i =0; i<numStacks; i++) {
            stackDeck.get(i).get(stackDeck.get(i).getLength()-1).setFaceUp(true); 
        }
        for(int i =0; i<drawPile.getLength(); i++) {
            if(drawPile.get(i).isFaceUp()) {
                drawPile.get(i).setFaceUp(false);
            }
        }
    }

    /**
     *  Moves a run of cards from src to dest (if possible) and flips the
     *  next card if one is available.
     */
    public void makeMove(String symbol, int src, int dest) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        Deck sourceD = stackDeck.get(src);
        Deck destinD = stackDeck.get(dest);

        while(destinD.getLength()<=0){
            int ind = sourceD.getLength() -1;
            while(ind<sourceD.getLength()) {
                destinD.addCard(sourceD.get(ind));
                ind++;
            }
            if(destinD.getLength() <= 0) {
                int i = sourceD.getLength() - 1;
                while( i>sourceD.getLength()-2) {
                    sourceD.remove(i);
                    i--;
                }
                if(sourceD.getLength()>0) {
                    if(!(sourceD.get(sourceD.getLength()-1).isFaceUp())) {
                        sourceD.get(sourceD.getLength()-1).setFaceUp(true);
                    }
                }
            }   
            if(sourceD.getLength()>0) {
                if(sourceD.getLength()>0) {
                    if(!(sourceD.get(sourceD.getLength()-1).isFaceUp())) {
                        sourceD.get(sourceD.getLength()-1).setFaceUp(true);
                    }
                }
            } else {
                continue;
            }
            break;
        }
        int i = sourceD.getLength()-1;
        while(symbol.equals(sourceD.get(i).getSymbol()) == false) {
            if(sourceD.get(i).isFaceUp()) {
                if(sourceD.get(i).getValue() == sourceD.get(i-1).getValue()-1) {
                    i--;
                    break;
                }
            }   
            i--;
        }

        while((destinD.get(destinD.getLength()-1).getValue())-1 == sourceD.get(i).getValue()) {
            for(int j = i; j<sourceD.getLength();j++) {
                destinD.addCard(sourceD.get(j));
            }
            int k = sourceD.getLength() -1;
            while(k>=i) {
                sourceD.remove(k);
                k--;
                if(sourceD.getLength()>0) {
                    if(!(sourceD.get(sourceD.getLength()-1).isFaceUp())) {
                        sourceD.get(sourceD.getLength()-1).setFaceUp(true);
                    }
                }
            }
            if(sourceD.getLength()>0) {
                if(!(sourceD.get(sourceD.getLength()-1).isFaceUp())) {
                    sourceD.get(sourceD.getLength()-1).setFaceUp(true);
                }
            } else {
                continue;
            }
            break;
        }
    }

    /** 
     *  Moves one card onto each stack, or as many as are available
     */
    public void drawCards() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        int card =0;
        while (card == 0) {
            if(drawPile.getLength()>=numStacks) {
                Card newCard;
                for(int i = stackDeck.size()-1; i>=0; i--) {
                    newCard = drawPile.get(drawPile.getLength()-1);
                    if(!newCard.isFaceUp()) {
                        newCard.setFaceUp(true);
                    }
                    stackDeck.get(i).addCard(newCard);
                    drawPile.remove(drawPile.getLength()-1);
                }
                break;
            }
            if(numStacks >drawPile.getLength()){
                Card newCard;
                for(int i = drawPile.getLength()-1; i>=0; i--) {
                    newCard = drawPile.get(drawPile.getLength()-1);
                    if(!newCard.isFaceUp()) {
                        newCard.setFaceUp(true);
                    }
                    stackDeck.get(i).addCard(newCard);
                    drawPile.remove(drawPile.getLength()-1);
                }
                break;
            }
        }
        for(int i =0; i<drawPile.getLength(); i++) {
            while(drawPile.get(i).isFaceUp()) {
                // drawPile.get(i).setFaceUp(false);
                drawPile.get(i).setFaceUp(false);
            }
        }
    }

    /**
     *  Returns true if all stacks and the draw pile are all empty
     */ 
    public boolean isEmpty() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        while(drawPile.getLength() == 0) {
            for(int i =0; i<stackDeck.size(); i++) {
                if(!(stackDeck.get(i).getLength() >= 1)) {
                    return true;
                } else {
                    return false;
                }
            }
            if(drawPile.getLength() == 0) {
                continue;
            }
            break;
        }
        if(drawPile.getLength() != 0) {
            return false;
        }
        return false;
    }

    /**
     *  If there is a run of A through K starting at the end of sourceStack
     *  then the run is removed from the game or placed into a completed
     *  stacks area.
     *  
     *  If there is not a run of A through K starting at the end of sourceStack
     *  then an invalid move message is displayed and the Board is not changed.
     */
    public void clear(int sourceStack) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        int[] value = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        int j = value.length -1;
        Deck deck = stackDeck.get(sourceStack);
        int i = deck.getLength() -1;
        while(i >= 0) {
            while (i > -1) {
                if(j>=value.length) {
                    break;
                }
            }
            if(j>value.length -1) {
                break;
            }
            if(value[j] != deck.get(i).getValue()) {
                System.out.println("Invalid: Stack Not Complete.");
                return;
            }
            if((deck.get(i).isFaceUp()) == false) {
                System.out.println("Invalid: Stack Not Complete.");
                return;
            }

            j--;
            i--;
        }
        int k = value.length;
        while(k >0) {
            deck.removeLastInd();
            k--;
        }
    }

    /**
     * Prints the board to the terminal window by displaying the stacks, draw
     * pile, and done stacks (if you chose to have them)
     */
    public void printBoard() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
        System.out.println("Stacks");
        String str;
        for(int i= 1; i<= numStacks; i++) {
            str = i + ":" + " " + stackDeck.get(i-1);
            System.out.println(str);
        }
        System.out.println("\n" + "Draw Pile: ");
        System.out.println(drawPile.toString());
    }
    
}