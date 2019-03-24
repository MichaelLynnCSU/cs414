public class Utility  extends BuyableProperty {

	Player owner;

    public Utility(String name, int value){
        super(name, value);
    }

	public int calculateRent(MonopolyBoard board) {
		int rent = 0;
		if(board.getOwner(12) != null & board.getOwner(28) != null) {
			if (board.getOwner(12).equals(board.getOwner(28)))
				rent = 10 * board.getCurrentRoll();
		}
		else
			rent = 4 * board.getCurrentRoll();
		return rent;
	}
}
