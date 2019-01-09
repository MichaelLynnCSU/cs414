import java.util.ArrayList;

public class Tax extends Square {

	public enum Type {INCOME, LUXURY};
	
	Type type;
	
    public Tax(String name, Type type){
        super(name);
        this.type = type;
    }
    
	public void doAction(Player p, MonopolyBoard board, Controller controller){
		switch(this.type) {
		case INCOME:
			boolean flat = true;
			if(controller != null) {
				flat = controller.taxChoice();
			}
			//need to insert choice here
			if(flat)
				p.subtractMoney(200);
			else {
				int totalVal = p.getMoney();
				ArrayList<BuyableProperty> props = p.getProperties();
				for (int i=0; i< props.size(); i++)
					totalVal += ((Property)props.get(i)).value;
				totalVal *= .1;
				p.subtractMoney((int)totalVal);
			}
			break;
		case LUXURY:
			p.subtractMoney(75);
			break;
		}
		return;
	}
}
