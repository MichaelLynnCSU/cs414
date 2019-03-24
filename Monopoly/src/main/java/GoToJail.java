
public class GoToJail extends Square {

	public GoToJail(String name) {
		super(name);
	}

	public void doAction(Player p, MonopolyBoard board, Controller controller){
		board.setPosition(p, 10, false); //sends player to jail
		board.sentToJail(p.getTurn());
	}
}
