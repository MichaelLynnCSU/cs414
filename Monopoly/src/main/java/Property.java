import java.util.ArrayList;

public class Property extends BuyableProperty {

	public enum Color{DARK_PURPLE, LIGHT_BLUE, LIGHT_PURPLE, ORANGE, RED, YELLOW, GREEN, DARK_BLUE}
	int rent;
	Color color;
	ArrayList<PropertyImprovement> upgrades;
	PropertyImprovement upgrader;
	int[][] upgradeRents;

	public Property(String name, int value, int rent, Color color){
		super(name, value);
		this.rent = rent;
		this.color = color;
		this.upgrades = new ArrayList<PropertyImprovement>(4);
		for(int i=0; i<4; i++) {
			this.upgrades.add(null);
		}
		initUpgradeRents();
		upgrader = new PropertyImprovement(this);
	}

	public int calculateRent(MonopolyBoard board) {
		int rent = this.rent;
		if(this.checkUpgrades() == 0) {
			switch (this.color) {

				//rent is doubled is all properties of same color are owned by same player
				case DARK_PURPLE:
					if (board.getOwner(1) != null && board.getOwner(3) != null) {
						if (board.getOwner(1).equals(board.getOwner(3))) {
							rent *= 2;
						}
					}
					break;
				case LIGHT_BLUE:
					if (board.getOwner(6) != null && board.getOwner(8) != null && board.getOwner(9) != null) {
						if (board.getOwner(6).equals(board.getOwner(8)) && (board.getOwner(6).equals(board.getOwner(9)))) {
							rent *= 2;
						}
					}
					break;
				case LIGHT_PURPLE:
					if (board.getOwner(11) != null && board.getOwner(13) != null && board.getOwner(14) != null) {
						if (board.getOwner(11).equals(board.getOwner(13)) && board.getOwner(11).equals(board.getOwner(14))) {
							rent *= 2;
						}
					}
					break;
				case ORANGE:
					if (board.getOwner(16) != null && board.getOwner(18) != null && board.getOwner(19) != null) {
						if (board.getOwner(16).equals(board.getOwner(18)) && board.getOwner(16).equals(board.getOwner(19))) {
							rent *= 2;
						}
					}
					break;
				case RED:
					if (board.getOwner(21) != null && board.getOwner(23) != null && board.getOwner(24) != null) {
						if (board.getOwner(21).equals(board.getOwner(23)) && board.getOwner(21).equals(board.getOwner(24))) {
							rent *= 2;
						}
					}
					break;
				case YELLOW:
					if (board.getOwner(26) != null && board.getOwner(27) != null && board.getOwner(29) != null) {
						if (board.getOwner(26).equals(board.getOwner(27)) && board.getOwner(26).equals(board.getOwner(29))) {
							rent *= 2;
						}
					}
					break;
				case GREEN:
					if (board.getOwner(32) != null && board.getOwner(34) != null) {
						if (board.getOwner(32).equals(board.getOwner(34))) {
							rent *= 2;
						}
					}
					break;
				case DARK_BLUE:
					if (board.getOwner(37) != null && board.getOwner(39) != null) {
						if (board.getOwner(37).equals(board.getOwner(39))) {
							rent *= 2;
						}
					}
					break;
			}

			return rent;
		} else {
			return upgradeRents[this.rent][checkUpgrades()-1];
		}
	}

	// Returns true if successful
	// Return false if unsuccessful
	// Adds a House if there aren't four already
	// Adds a Hotel if there are four houses
	// Fails if there is a Hotel
	public boolean[] upgrade() {
		boolean[] values = {false, false};
		if(upgrades.get(3) != null) {
			for(int i=0; i<4; i++) {
				this.upgrades.set(i, null);
			}
			upgrades.set(0, new Hotel());
			values[0] = true;
			return values;
		} else {
			if(upgrades.get(0) instanceof Hotel) {
				return values;
			} else {
				for (int i = 0; i < 4; i++) {
					if (upgrades.get(i) == null) {
						upgrades.set(i, new House());
						break;
					}
				}
				values[0] = true;
				values[1] = true;
				return values;
			}
		}
	}

	public int checkUpgrades() {
		if(upgrades.get(0) instanceof Hotel) {
			return 5;
		} else {
			int houseCount = 0;
			for (int i = 0; i < 4; i++) {
				if(upgrades.get(i) != null) {
					houseCount++;
				}
			}
			return houseCount;
		}
	}

	private void initUpgradeRents() {
		upgradeRents = new int[51][5];

		int[] two = {10, 30, 90, 160, 250};
		int[] four = {20, 60, 180, 320, 450};
		int[] six = {30, 90, 270, 400, 550};
		int[] eight = {40, 100, 300, 450, 600};
		int[] ten = {50, 150, 450, 625, 750};
		int[] twelve = {60, 180, 500, 700, 900};
		int[] fourteen = {70, 200, 550, 750, 950};
		int[] sixteen = {80, 220, 600, 800, 1000};
		int[] eighteen = {90, 250, 700, 875, 1050};
		int[] twenty = {100, 300, 750, 925, 1100};
		int[] twentytwo = {110, 330, 800, 975, 1150};
		int[] twentyfour = {120, 360, 850, 1025, 1200};
		int[] twentysix = {130, 390, 900, 1110, 1275};
		int[] twentyeight = {150, 450, 1000, 1200, 1400};
		int[] thirtyfive = {175, 500, 1100, 1300, 1500};
		int[] fifty = {200, 600, 1400, 1700, 2000};

		upgradeRents[2] = two;
		upgradeRents[4] = four;
		upgradeRents[6] = six;
		upgradeRents[8] = eight;
		upgradeRents[10] = ten;
		upgradeRents[12] = twelve;
		upgradeRents[14] = fourteen;
		upgradeRents[16] = sixteen;
		upgradeRents[18] = eighteen;
		upgradeRents[20] = twenty;
		upgradeRents[22] = twentytwo;
		upgradeRents[24] = twentyfour;
		upgradeRents[26] = twentysix;
		upgradeRents[28] = twentyeight;
		upgradeRents[35] = thirtyfive;
		upgradeRents[50] = fifty;
	}

	public boolean[] buyImprovement(Player player) {
		return upgrader.buyImprovement(player);
	}
	
	public Color getColor(){
		return this.color;
	}
}
