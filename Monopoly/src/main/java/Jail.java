public class Jail {

	boolean[] isJailed;
	int[] numDoubles; //number of doubles in a row
	int[] turnsInJail;
	MonopolyBoard board;

	public Jail(MonopolyBoard board, int numPlayers){
		isJailed = new boolean[numPlayers];
		numDoubles = new int[numPlayers];
		turnsInJail = new int[numPlayers];
		this.board = board;
	}

	public boolean getJailed(int player) {
		return isJailed[player];
	}

	public int getnumDoubles(int player) {
		return numDoubles[player];
	}

	public int getTurns(int player) {
		return turnsInJail[player];
	}

	public boolean rolledDoubles(int player) {
		numDoubles[player]++;
		if (numDoubles[player] == 3) {
			sentToJail(player);
			return true;
		}
		return false;
	}

	public void sentToJail(int player) {
		board.setPosition(board.getPlayer(player), 10, false);
		isJailed[player] = true;
		numDoubles[player] = 0;
		turnsInJail[player] = 0;
	}

	public void rolledDoublesJailed(int player) {
		isJailed[player] = false;
		numDoubles[player] = 1;
		turnsInJail[player] = 0;
	}

	public void payFine(int player) {
		leftJail(player);
		board.getPlayer(player).subtractMoney(50);
	}

	public void leftJail(int player) {
		isJailed[player] = false;
		turnsInJail[player] = 0;
		numDoubles[player] = 0;
	}

	public void noDoubles(int player) {
		numDoubles[player] = 0;
	}

	public boolean noDoublesJailed(int player) {
		turnsInJail[player]++;
		if (turnsInJail[player] == 3) {
			payFine(player);
			return true;
		}

		return false;
	}
}
