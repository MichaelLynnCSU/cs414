public class Railroad  extends BuyableProperty {


	public Railroad(String name, int value){
		super(name, value);

	}

	public int calculateRent(MonopolyBoard board) {
		int numOwned = 0;
		int rent = 0;

		if(this.owner != null) {
			if (board.getOwner(5) != null) {
				if (this.owner.equals(board.getOwner(5))) {
					numOwned++;
				}
			}
			if (board.getOwner(15) != null) {
				if (this.owner.equals(board.getOwner(15))) {
					numOwned++;
				}
			}
			if (board.getOwner(25) != null) {
				if (this.owner.equals(board.getOwner(25))) {
					numOwned++;
				}
			}
			if (board.getOwner(35) != null) {
				if (this.owner.equals(board.getOwner(35))) {
					numOwned++;
				}
			}
		}

		switch(numOwned) {
		case 1:
			rent = 25;
			break;
		case 2:
			rent = 50;
			break;
		case 3:
			rent = 100;
			break;
		case 4:
			rent = 200;
			break;
		}
		return rent;
	}

}
