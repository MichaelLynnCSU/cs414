import java.awt.Color;
import java.util.ArrayList;

public class Player {

	private String name;
	private String token;
	private int money;
	private int position; // Should be 0-39, representing where they are on the board
	private ArrayList<BuyableProperty> properties;
	private int turn;
	public boolean leaveJailCC;
	public boolean leaveJailChance;
	private int[] colorsOwned;
	
	Player (String name, String token, int turn){
		this.name = name;
		this.token = token;
		this.money = 1500;
		this.turn = turn;
		position = 0;
		properties = new ArrayList<BuyableProperty>();
		colorsOwned = new int[8];
		initColorsOwned();
	}
	
	private void initColorsOwned(){
		for(int i=0; i< colorsOwned.length; i++){
			colorsOwned[i] = 0;
		}
	}
	
	public int[] getColorsOwned(){
		return colorsOwned;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getToken(){
		return this.token;
	}
	
	public int getMoney(){
		return this.money;
	}
	
	public int getPosition(){
		return this.position;
	}
	
	public void setPosition(int position){
		this.position = position;
	}
	
	public void addMoney(int amount){
		this.money += amount;
	}
	
	public void subtractMoney(int amount){
		this.money -= amount;
	}

	public void addProperty(BuyableProperty property) {
		properties.add(property);
		System.out.println(property.getName() + " purchased.");
		if(property instanceof Property){
			updateColorsOwned(((Property) property).getColor());
		}
	}
	
	public ArrayList<BuyableProperty> getProperties() {
		return properties;
	}

	public String printProperties() {
		String ret = "[";
		for(BuyableProperty property:properties) {
			ret+=property.getName();
			ret+=", ";
		}
		if(ret.length()>2) {
			ret = ret.substring(0, ret.length() - 2);
		}
		ret+="]";
		return ret;
	}

	public ArrayList<BuyableProperty> getMortgageable() {
		ArrayList<BuyableProperty> props = new ArrayList<BuyableProperty>();
		for (int i=0; i < properties.size(); i++)
			if (!((BuyableProperty)properties.get(i)).mortgaged)
				props.add(properties.get(i));
		return props;
	}
	
	public ArrayList<BuyableProperty> getUnmortgageable() {
		ArrayList<BuyableProperty> props = new ArrayList<BuyableProperty>();
		for (int i=0; i < properties.size(); i++) {
			int cost = (int)((properties.get(i)).value * 0.6);
			if (((BuyableProperty)properties.get(i)).mortgaged && cost <= this.money)
				props.add(properties.get(i));
		}
		return props;
	}
	
	public void updateColorsOwned(Property.Color color){
		switch(color){
		case DARK_PURPLE:
			colorsOwned[0] += 1;break;
		case LIGHT_BLUE:
			colorsOwned[1] += 1; break;
		case LIGHT_PURPLE:
			colorsOwned[2] += 1; break;
		case ORANGE:
			colorsOwned[3] +=1; break;
		case RED:
			colorsOwned[4] +=1; break;
		case YELLOW:
			colorsOwned[5] +=1; break;
		case GREEN:
			colorsOwned[6] +=1; break;
		case DARK_BLUE:
			colorsOwned[7] +=1; break;
		}
		
	}
	
	public ArrayList<Property> getUpgradeable(){
		ArrayList<Property> props = new ArrayList<Property>();
		for(BuyableProperty p: this.properties){
			if(p instanceof Property){
				int index = colorToIndex(((Property)p).getColor());
				if (((Property)p).getColor() == Property.Color.DARK_PURPLE || ((Property)p).getColor() == Property.Color.DARK_BLUE ){
					if(colorsOwned[index] == 2)
						props.add((Property)p);
				}
				else{
					if(colorsOwned[index] == 3)
						props.add((Property)p);
				}
			}
		}
		
		return props;
	}
	
	public int colorToIndex(Property.Color color){
		switch(color){
			case DARK_PURPLE:
				return 0;
			case LIGHT_BLUE:
				return 1;
			case LIGHT_PURPLE:
				return 2;
			case ORANGE:
				return 3;
			case RED:
				return 4;
			case YELLOW:
				return 5;
			case GREEN:
				return 6;
			case DARK_BLUE:
				return 7;
			default: return -1;//shouldn't hit this case
		}
	}
	
	public int calculateValue() {
		int val = this.money;
		for (int i=0; i < properties.size(); i++)
			val += ((BuyableProperty)properties.get(i)).value;
		return val;
	}
	
	public boolean equals(Object o) {
		if (o == null || this == null)
			return false;

		if (this.getToken().equals(((Player) o).getToken()))
			return true;
		else
			return false;
	}

	public void setTurn(int turn){
		this.turn = turn;
	}
	
	public int getTurn(){
		return turn;

	}
}
