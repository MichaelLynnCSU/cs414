import java.util.Arrays;

public class MonopolyBoard {

	private Square[] board;
	private Player[] players;
	private int numPlayers;
	private Jail jail;
	Controller controller;
	private int currentRoll = 0;
	ChanceCards chanceCards;
	CommunityChestCards communityChestCards;

	MonopolyBoard(int numPlayers, String[] playerNames, String[] playerTokens, Controller controller) {
		
		this.numPlayers = numPlayers;
		players = new Player[numPlayers];
		this.controller = controller;
		for(int i=0; i<numPlayers; i++) {
			players[i] = new Player(playerNames[i], playerTokens[i], i);
		}
		this.board = new Square[40];
		jail = new Jail(this, numPlayers);
		chanceCards = new ChanceCards(this);
		communityChestCards = new CommunityChestCards(this);

		//Create all 40 squares for the board
		board[0] = new Square("Go");
		board[1] = new Property("Mediterranean Avenue", 60, 2, Property.Color.DARK_PURPLE);
		board[2] = new CardSquare("Community Chest", communityChestCards); 
		board[3] = new Property("Baltic Avenue", 60, 4, Property.Color.DARK_PURPLE);
		board[4] = new Tax("Income Tax", Tax.Type.INCOME);
		board[5] = new Railroad("Reading Railroad", 200); 
		board[6] = new Property("Oriental Avenue", 100, 6, Property.Color.LIGHT_BLUE);
		board[7] = new CardSquare("Chance", chanceCards); 
		board[8] = new Property("Vermont Avenue", 100, 6, Property.Color.LIGHT_BLUE);
		board[9] = new Property("Connecticut Avenue", 120, 8, Property.Color.LIGHT_BLUE);
		board[10] = new Square("Jail");
		board[11] = new Property("St. Charles Place", 140, 10, Property.Color.LIGHT_PURPLE);
		board[12] = new Utility("Electric Company", 150);
		board[13] = new Property("States Avenue", 140, 10, Property.Color.LIGHT_PURPLE);
		board[14] = new Property("Virginia Avenue", 160, 12, Property.Color.LIGHT_PURPLE);
		board[15] = new Railroad("Pennsylvania Railroad", 200);
		board[16] = new Property("St. James Place", 180, 14, Property.Color.ORANGE);
		board[17] = new CardSquare("Community Chest", communityChestCards); 
		board[18] = new Property("Tennessee Avenue", 180, 14, Property.Color.ORANGE);
		board[19] = new Property("New York Avenue", 200, 16, Property.Color.ORANGE);
		board[20] = new Square("Free Parking");
		board[21] = new Property("Kentucky Avenue",220, 18, Property.Color.RED);
		board[22] = new CardSquare("Chance", chanceCards); //This will need to change for 2nd iteration
		board[23] = new Property("Indiana Avenue",220, 18, Property.Color.RED);
		board[24] = new Property("Illinois Avenue",240, 20, Property.Color.RED);
		board[25] = new Railroad("B&O Railroad", 200); // B&O Railroad
		board[26] = new Property("Atlantic Avenue", 260, 22, Property.Color.YELLOW);
		board[27] = new Property("Ventnor Avenue", 260, 22, Property.Color.YELLOW);
		board[28] = new Utility("Water Works", 150);
		board[29] = new Property("Marvin Gardens", 280, 24, Property.Color.YELLOW);
		board[30] = new GoToJail("Go To Jail");
		board[31] = new Property("Pacific Avenue", 300, 26, Property.Color.GREEN);
		board[32] = new Property("North Carolina Avenue", 300, 26, Property.Color.GREEN);
		board[33] = new CardSquare("Community Chest", communityChestCards); 
		board[34] = new Property("Pennsylvania Avenue", 320, 28, Property.Color.GREEN);
		board[35] = new Railroad("Short Line", 200); //Short Line
		board[36] = new CardSquare("Chance", chanceCards); 
		board[37] = new Property("Park Place", 350, 35, Property.Color.DARK_BLUE);
		board[38] = new Tax("Luxury Tax", Tax.Type.LUXURY);
		board[39] = new Property("Boardwalk", 400, 50, Property.Color.DARK_BLUE);
	}
  
	public void move(Player player, int spaces) {
		int newPos = player.getPosition() + spaces;
		if (newPos >= 40) {
			newPos -= 40;
			passGo(player);
		}
		
		player.setPosition(newPos);
		if(controller != null) {
			controller.w.append(player.getName() + " has landed on: " + board[newPos].getName());
		}
		board[newPos].doAction(player, this, controller);
			
	}

	public void setPosition(Player player, int position, boolean canPassGo) {
		if(canPassGo && position <= player.getPosition())
			passGo(player);
		player.setPosition(position);
		board[position].doAction(player, this, controller);
	}
	
	public void passGo(Player player) {
		player.addMoney(200);
	}

	public Player getPlayer(int playerNumber) {
		return players[playerNumber];
	}

	public int getNumPlayers() {
		return players.length;
	}

	public int getCurrentRoll() {
		return currentRoll;
	}

	public void setCurrentRoll(int currentRoll) {
		this.currentRoll = currentRoll;
	}

	public Square[] getBoard() {
		return Arrays.copyOf(board, board.length);
}

	public Player getOwner(int space) {
		if (board[space] instanceof BuyableProperty)
			return ((BuyableProperty)board[space]).owner;
		else
			return null;
	}
	
	public int calculateRent(int space) {
		return ((BuyableProperty)board[space]).calculateRent(this);
	}
	
	public boolean isJailed(int player) {
		return jail.getJailed(player);
	}
	
	public boolean rolledDoubles(int player) {
		//boolean returns true if player is jailed for speeding
		return jail.rolledDoubles(player);
	}
	
	public void noDoubles(int player) {
		jail.noDoubles(player);
	}

	public void leftJail(int player) {
		jail.leftJail(player);
	}

	public boolean noDoublesJailed(int player) {
		return jail.noDoublesJailed(player);
	}

	public void jailedPayFine(int player) {
		jail.payFine(player);
	}
	
	public void sentToJail(int player) {
		jail.sentToJail(player);
	}

	public void sentToJail(Player player) {
		for (int i=0; i<players.length; i++)
			if (players[i].equals(player))
				jail.sentToJail(i);
	}
	
	public Jail getJail() {
		return jail;
	}
	
	public void leaveJailCC(Player player) {
		communityChestCards.returnCard();
		player.leaveJailCC = false;
	}
	
	public void leaveJailChance(Player player) {
		chanceCards.returnCard();
		player.leaveJailChance = false;
	}
	
	public int nameToSpaceNum(String name){
		switch(name){
		case "Mediterranean Avenue":
			return 1;
		case "Baltic Avenue":
			return 3;
		case "Oriental Avenue":
			return 6;
		case "Vermont Avenue":
			return 8;
		case "Connecticut Avenue":
			return 9;
		case "St. Charles Place":
			return 11;
		case "Virginia Avenue":
			return 14;
		case "States Avenue":
			return 13;
		case "St. James Place":
			return 16;
		case "New York Avenue":
			return 19;
		case "Tennessee Avenue":
			return 18;
		case "Kentucky Avenue":
			return 21;
		case "Indiana Avenue":
			return 23;
		case "Illinois Avenue":
			return 24;
		case "Atlantic Avenue":
			return 26;
		case "Marvin Gardens":
			return 29;
		case "Ventnor Avenue":
			return 27;
		case "Pacific Avenue":
			return 31;
		case "North Carolina Avenue":
			return 32;
		case "Pennsylvania Avenue":
			return 34;
		case "Park Place":
			return 37;
		case "Boardwalk":
			return 39;
		default: return -1;
		}
	}

}
