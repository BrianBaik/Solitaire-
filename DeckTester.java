public class DeckTester
{
    public static void main(String[] args) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 3 *** */
        Deck deck = new Deck();
        Card card;
        
        String[] symbols = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        
        for(int i = 0; i < symbols.length; i++){
            card = new Card(symbols[i], values[i]);
            card.setFaceUp(true);
            deck.addCard(card);
        }
        System.out.println(deck);
        deck.shuffle();
        System.out.println(deck);
        
    }
}
