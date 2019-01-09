
public abstract class BuyableProperty extends Square {

	int value;
	Player owner;
	boolean mortgaged;

	public BuyableProperty(String name, int value) {
		super(name);
		this.value = value;
		this.owner = null;
		this.mortgaged = false;
	}

	public void doAction(Player p, MonopolyBoard board, Controller controller){
		if (this.owner == null) {
			if(p.getMoney() >= this.value) {
				//option to buy
				if (controller != null) {
					boolean willBuy = controller.buyChoice(this.name, this.value);
					if (willBuy) {
						buyProperty(p);
						controller.addProperty(this.name);
					}
					else {
						controller.auctionProperty(this);
					}
				}
				else {
					buyProperty(p);
				}
			}
			else {
				if (controller != null)
					controller.auctionProperty(this);
			}
		}
		else if (this.owner == p || this.mortgaged == true)
			return;
		else {

			int rent = calculateRent(board);
			if(controller != null) {
				controller.payRent(rent, this.owner.getName());
			}
			this.owner.addMoney(rent);
			p.subtractMoney(rent);
		}
	}

	public void buyProperty(Player player) {
		player.subtractMoney(this.value);
		this.owner = player;
		this.owner.addProperty(this);
	}

	public void mortgageProperty() {
		this.mortgaged = true;
		this.owner.addMoney(this.value/2);
	}

	public void unmortgageProperty() {
		this.mortgaged = false;
		int cost = (int)(this.value * 0.6);
		this.owner.subtractMoney(cost);
	}

	public void buyAuction(Player player, int bid) {
		player.subtractMoney(bid);
		this.owner = player;
		this.owner.addProperty(this);
	}

	public abstract int calculateRent(MonopolyBoard board);
}
