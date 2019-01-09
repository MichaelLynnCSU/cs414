
public class CardSquare extends Square {

	Deck deck;
	
	public CardSquare(String name, Deck deck) {
		super(name);
		
		this.deck = deck;
	}

	public void doAction(Player p, MonopolyBoard board, Controller controller){
		if (controller != null)
			controller.drawnCard(p, deck.draw(p));
	}
	
}
