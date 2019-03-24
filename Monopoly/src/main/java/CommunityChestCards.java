import java.util.ArrayList;

public class CommunityChestCards extends Deck {

	CommunityChestCards(MonopolyBoard board) {
		super(board);
	}

	protected String[] cardDrawn(Player player, int card) {
		String[] retString = new String[2];
		retString[0] = "Community Chest";
		String s = "";
		int pos;
		switch(card) {

		case 0:
			s = "Advance to Go.  Collect $200.";
			board.setPosition(player, 0, true);
			break;
		case 1:
			s = "Bank error in your favor - Collect $200.";
			player.addMoney(200);
			break;
		case 2:
			s = "Doctor's fees - Pay $50.";
			player.subtractMoney(50);
			break;
		case 3:
			s = "From sale of stock you get $50.";
			player.addMoney(50);
			break;
		case 4:
			s = "Holiday Xmas fund matures - Collect $100.";
			player.addMoney(100);
			break;
		case 5:
			s = "Receive $25 consultancy fee";
			player.addMoney(25);
			break;
		case 6:
			s = "Income Tax refund - Collect $20";
			player.addMoney(20);
			break;
		case 7:
			s = "Life Insurance Matures - Collect $100";
			player.addMoney(100);
			break;
		case 8:
			s = "Go Directly to Jail.";
			//to be implemented
			break;
		case 9:
			s = "Pay hospital fees of $100.";
			player.subtractMoney(100);
			break;
		case 10:
			s = "Pay school fees of $150.";
			player.subtractMoney(150);
			break;
		case 11:
			s = "You inherit $100.";
			player.addMoney(100);
			break;
		case 12:
			s = "You have won second prize in a beauty contest – Collect $10";
			player.addMoney(10);
			break;
		case 13:
			s = "Grand Opera Night opening - Collect $50 from each player";
			int numPlayers = board.getNumPlayers();
			for(int i=0; i< numPlayers; i++) { //This will also involve the player giving himself $50, which does nothing.
				Player p = board.getPlayer(i);
				p.subtractMoney(50);
				player.addMoney(50);
			}
			break;
		case 14:
			s = "You are assessed for street repairs – $40 per house – $115 per hotel.";
			int fine = 0;
			int numHouses = 0;
			int numHotels = 0;

			ArrayList<BuyableProperty> props = player.getProperties();
			for (BuyableProperty prop: props) {
				if(prop instanceof Property) {
					int upgrade = ((Property)prop).checkUpgrades();
					if (upgrade == 5) {
						numHotels++;
						fine += 115;
					}
					else {
						numHouses += upgrade;
						fine += upgrade * 40;
					}
				}

			}
			s += "Your total fine is " + fine + " for " + numHouses + " houses and " + numHotels + " hotels.";
			player.subtractMoney(fine);
			break;
		case 15:
			s = "Get Out of Jail Free";
			deckSize--;
			player.leaveJailCC = true;
			break;
		}

		retString[1] = s;
		return retString;
	}
}
