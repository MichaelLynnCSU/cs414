import java.util.ArrayList;

public class ChanceCards extends Deck {


	ChanceCards(MonopolyBoard board) {
		super(board);
	}

	protected String[] cardDrawn(Player player, int card) {
		String[] retString = new String[2];
		retString[0] = "Chance";
		String s = "";
		int pos;
		switch(card) {
		
		case 0:
			s = "Advance to Go.  Collect $200.";
			board.setPosition(player, 0, true);
			break;
		case 1:
			s = "Advance to Illinois Ave.";
			board.setPosition(player,  24,  true);
			break;
		case 2:
			s = "Advance to St. Charles Place.";
			board.setPosition(player,  11,  true);
			break;
		case 3:
			s = "Advance token to nearest Utility.";
			pos = player.getPosition();
			if (pos == 12 || pos == 28); //Do nothing if already on a utility, should never trigger
			else if (pos > 12 && pos < 28)
				board.setPosition(player, 28, true);
			else
				board.setPosition(player,  12,  true);
			break;
		case 4:
			s = "Advance token to the nearest Railroad.";
			pos = player.getPosition();
			if (pos > 5 && pos < 15)
				board.setPosition(player, 15, true);
			if (pos > 15 && pos < 25)
				board.setPosition(player, 25, true);
			if (pos > 25 && pos < 35)
				board.setPosition(player, 35, true);
			else
				board.setPosition(player, 5, true);
			break;
		case 5:
			s = "You have won a crossword competition.  Collect $100.";
			player.addMoney(100);
			break;
		case 6:
			s = "Bank pays you dividend of $50";
			player.addMoney(50);
			break;
		case 7:
			s = "Go Back 3 Spaces";
			pos = player.getPosition();
			board.setPosition(player, pos-3, false);
			break;
		case 8:
			s = "Go Directly to Jail.";
			board.sentToJail(player);
			break;
		case 9:
			s = "Make general repairs on all your property – For each house pay $25 – For each hotel $100.";
			int fine = 0;
			int numHouses = 0;
			int numHotels = 0;

			ArrayList<BuyableProperty> props = player.getProperties();
			for (BuyableProperty prop: props) {
				if(prop instanceof Property) {
					int upgrade = ((Property)prop).checkUpgrades();
					if (upgrade == 5) {
						numHotels++;
						fine += 100;
					}
					else {
						numHouses += upgrade;
						fine += upgrade * 25;
					}
				}

			}
			s += "Your total fine is " + fine + " for " + numHouses + " houses and " + numHotels + " hotels.";
			player.subtractMoney(fine);
			break;
		case 10:
			s = "Pay speeding fine of $15.";
			player.subtractMoney(15);
			break;
		case 11:
			s = "Take a ride on the Reading Railroad.";
			board.setPosition(player, 5, true);
			break;
		case 12:
			s = "Take a walk on the Boardwalk.";
			board.setPosition(player, 39, true);
			break;
		case 13:
			s = "You have been elected Chairman of the Board – Pay each player $50";
			int numPlayers = board.getNumPlayers();
			for(int i=0; i< numPlayers; i++) { //This will also involve the player giving himself $50, which does nothing.
				Player p = board.getPlayer(i);
				p.addMoney(50);
				player.subtractMoney(50);
			}
			break;
		case 14:
			s = "Your building loan matures.  Collect $150.";
			player.addMoney(150);
			break;
		case 15:
			s = "Get Out of Jail Free";
			deckSize--;
			player.leaveJailChance = true;
			break;
			
		}
		retString[1] = s;
		return retString;
	}
}
