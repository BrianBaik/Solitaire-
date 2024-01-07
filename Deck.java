import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Deck
{
    /* *** TO BE IMPLEMENTED IN ACTIVITY 3 *** */
    ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<Card>();
    }

    public Deck (String str) {

    }

    public void shuffle() {
        Card card;
        Random rand = new Random();
        for(int i =0; i<deck.size(); i++) {
            int ranIndex = rand.nextInt(deck.size());
            card = deck.get(i);
            deck.set(i, deck.get(ranIndex));
            deck.set(ranIndex, card);
        }
    }
    public Card get(int i) {
        return deck.get(i);
    }
    public int getLength() {
        return deck.size();
    }
    public String toString() {
        return "" + deck + "";
    }    
    public void remove(int c) {
        deck.remove(c);
    }
    public void removeLastInd() {
        deck.remove(deck.size() -1);
    }
    public String getSymbol(int s) {
        return deck.get(s).getSymbol();
    }
    public boolean isFaceUp(int f) {
        return deck.get(f).isFaceUp();
    }
    public void addCard(Card card) {
        deck.add(card);
    }
    
}
