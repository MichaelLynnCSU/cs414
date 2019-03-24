import java.util.ArrayList;
import java.util.Random;

public abstract class Deck {

	protected ArrayList<Integer> deck;
	protected int deckSize;
	protected MonopolyBoard board;
	
	Deck(MonopolyBoard board) {
		this.deck = new ArrayList<Integer>();
		this.board = board;
		deckSize = 16;
		this.shuffle();
	}
	
	protected void shuffle() {
		while (deck.size() > 0)
			deck.remove(0);
		
		ArrayList<Integer> builder = new ArrayList<Integer>();
		Random rand = new Random();

		for (int i=0; i< deckSize; i++)
			builder.add(i);
		while (builder.size() > 0) {
			int selector = rand.nextInt(builder.size());
			int sol = builder.remove(selector);
			deck.add(sol);
		}
	}

	public String[] draw(Player player) {
		int card = deck.get(0);
		String s[] = cardDrawn(player, card);
		this.shuffle();
		return s;
	}
	
	public  void returnCard() {
		deckSize = 16;
		this.shuffle();
	}
	
	protected abstract String[] cardDrawn(Player player, int card);
}
