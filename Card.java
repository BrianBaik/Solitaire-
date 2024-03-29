/**
 * Card.java
 *
 * <code>Card</code> represents a basic playing card.
 */
public class Card implements Comparable<Card>
{
    /** String value that holds the symbol of the card.
    Examples: "A", "Ace", "10", "Ten", "Wild", "Pikachu"
     */
    private String symbol;

    /** int value that holds the value this card is worth */
    private int value;

    /** boolean value that determines whether this card is face up or down */
    private boolean isFaceUp;

    /**
     * Creates a new <code>Card</code> instance.
     *
     * @param symbol  a <code>String</code> value representing the symbol of the card
     * @param value an <code>int</code> value containing the point value of the card
     */    
    public Card(String symbol, int value) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        this.symbol = symbol;
        this.value = value;
    }

    /**
     * Getter method to access this <code>Card</code>'s symbol.
     * 
     * @return this <code>Card</code>'s symbol.
     */
    public String getSymbol() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        return symbol;
    }

    public int getValue() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        return value;
    }
    public boolean isFaceUp() {
        return isFaceUp;
    }

    public void setFaceUp(boolean state) {
        isFaceUp = state;
    }

    /**
     * Returns whether or not this <code>Card</code> is equal to another
     *  
     *  @return whether or not this Card is equal to other.
     */
    public boolean equals(Card other) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        if(this.value == other.getValue() && this.symbol == other.getSymbol()) {
            return true;
        }
        return false;
    }
    
    /**
     * Returns this card as a String.  If the card is face down, "X"
     * is returned.  Otherwise the symbol of the card is returned.
     *
     * @return a <code>String</code> containing the symbol or and X,
     * depending on whether the card is face up or down.
     */
    @Override
    public String toString() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        if(isFaceUp()) {
            return symbol;
        } else {
            return "X";
        }
    }
    
    public int compareTo(Card otherCard) {
        int compare = value - otherCard.getValue();
        return compare;
    }
    
}
