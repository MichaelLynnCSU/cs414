import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Controller{
	Window w;
	MonopolyBoard board;
	Die die;
	NewGameWindow ngw;
	int currentPlayer;
	private boolean hasRolled = false;
	private boolean gameEnded = false;
	Timer timer;
	int gameTime = 5;
	int updatedGameTime = 5;
	boolean newGamePressed = false;

	Controller (String arg){
		w = new Window(this);
		die = new Die(arg);
	}

	public void doAction(ActionEvent e){
		Object src = e.getSource();
		if(src==w.bottom.roll) roll();
		else if(src == w.bottom.mortgage) mortgage();
		else if(src == w.top.newGame) newGame();
		else if(src == w.bottom.endTurn) endTurn();
		else if(src == w.bottom.house) house();
		else if(src == ngw.play) play();
	}

	public void roll(){
		if(newGamePressed){
			if (!board.isJailed(currentPlayer) && !hasRolled) {
				die.roll();
				if (die.isDoubles()) {
					if (board.rolledDoubles(currentPlayer)) {
						w.jailedSpeeding();
						w.boardGUI.move(0, 0, currentPlayer, true);
						hasRolled = true;
					}else {
						w.rolledDoubles();
						move();
					}
				}
				else {
					board.noDoubles(currentPlayer);
					hasRolled = true;
					move();
				}
			}
			else if (board.isJailed(currentPlayer) && !hasRolled){
				int option;
				Player player = board.getPlayer(currentPlayer);
				if (player.leaveJailCC || player.leaveJailChance)
					option = w.jailedOptionsCard();
				else
					option = w.jailedOptions();
				
				if (option == 0) {
					die.roll();
					if (die.isDoubles()) {
						w.successEscape();
						board.leftJail(currentPlayer);
						move();
						hasRolled = true;
					}
					else {
						if (board.noDoublesJailed(currentPlayer)) {
							w.forcedFine(die.firstNum, die.secondNum);
						}
						else {
							w.failedEscape(die.firstNum, die.secondNum);
							hasRolled = true;
						}

					}
				} else if (option == 1) {
					board.jailedPayFine(currentPlayer);
					die.roll();
					move();
					if (!die.isDoubles())
						hasRolled=true;
					else
						board.rolledDoubles(currentPlayer);
				}
				else if (option == 2) {
					if (player.leaveJailCC) {
						board.leftJail(currentPlayer);
						board.leaveJailCC(player);
					}
					else {
						board.leftJail(currentPlayer);
						board.leaveJailChance(player);						
					}
				}
			}
			
			else if (hasRolled) {
				w.alreadyRolled();
			}


		}
	}

	public void move() {
		w.append(board.getPlayer(currentPlayer).getName() + " has rolled: " + die.getSum());
		board.setCurrentRoll(die.getSum());
		w.top.updateRollLabel(die.getSum());
		w.boardGUI.move(die.getSum(),board.getPlayer(currentPlayer).getPosition(), currentPlayer, false);
		board.move(board.getPlayer(currentPlayer), die.getSum());
		w.top.updateMoney(board.getPlayer(currentPlayer).getMoney());

	}

	public void mortgage(){
		if(newGamePressed){
			ArrayList<BuyableProperty> props;
			int choice = w.mortgageChoice();
			if (choice == 0) {
				props = board.getPlayer(currentPlayer).getMortgageable();
				int selection = w.mortgageSelect(props, "Mortgage");
				if (selection < props.size()) {
					props.get(selection).mortgageProperty();
				}
				w.top.updateMoney(board.getPlayer(currentPlayer).getMoney());
			}
			else if (choice == 1) {
				props = board.getPlayer(currentPlayer).getUnmortgageable();
				int selection = w.mortgageSelect(props, "Unmortgage");
				if (selection < props.size()) {
					props.get(selection).unmortgageProperty();
				}
				w.top.updateMoney(board.getPlayer(currentPlayer).getMoney());
			}
			else return;
		}

	}

	public void newGame(){
		ngw = new NewGameWindow(this);
		newGamePressed = true;
	}

	public void endTurn(){
		if(newGamePressed){
			if (hasRolled) {
				hasRolled = false;
				int players =  ngw.names.length;
				if(currentPlayer == players - 1){
					currentPlayer =  0;
				}
				else{
					currentPlayer++;
				}
			
				w.top.setPlayerAndMoney(board.getPlayer(currentPlayer).getName(), board.getPlayer(currentPlayer).getMoney());
				w.list.updatePlayer(currentPlayer);
				w.append("The turn has ended.");
				System.out.println();
				w.append("It is now " + board.getPlayer(currentPlayer).getName() + "'s turn.");
				System.out.println(board.getPlayer(currentPlayer).getName() + "'s property: ");
				System.out.println(board.getPlayer(currentPlayer).printProperties());
				System.out.println(board.getPlayer(currentPlayer).getName() + "'s money: $" + board.getPlayer(currentPlayer).getMoney());
				w.append("Awaiting roll...");
				System.out.println("Awaiting roll...");
			}

			else {
				w.notRolled();
			}
		}
		
	}

	public void play(){
		ngw.doPlayButton();
		board = new MonopolyBoard(ngw.getNames().length, ngw.getNames(), ngw.getTokens(), this);
		w.displayPlayers(ngw.getNames().length);
		w.top.setPlayerAndMoney(board.getPlayer(currentPlayer).getName(), board.getPlayer(currentPlayer).getMoney());
		gameTime = ngw.getTime();
		updatedGameTime = ngw.getTime();
		System.out.println(gameTime);
		timer = new Timer();
		timer.schedule(new endGame(), gameTime*1000);
		ngw.close();
		timer.schedule(new timerUpdates(), 100, 1000);
		w.append("A new game has begun.");
		w.append("Awaiting roll...");
	}

	class timerUpdates extends TimerTask{
		public void run(){
			if(updatedGameTime !=0)
				w.top.updateTimer(updatedGameTime--);
		}
	}
	
	class endGame extends TimerTask {
		//code borrowed from http://www.iitk.ac.in/esc101/05Aug/tutorial/essential/threads/timer.html
        public void run() {
			System.out.println();
        	System.out.println("Game has ended.");
        	// Calculate winner
			System.out.println("Calculating winner...");
			int numPlayers = board.getNumPlayers();
			int highestValue = 0;
			Player winner = new Player(null, null, -1);
			for(int i=0; i<numPlayers; i++) {
				Player player = board.getPlayer(i);
				System.out.println(player.getName() + ", Money: $" + player.getMoney() + ", Value: $" + player.calculateValue());
				int playerValue = player.calculateValue();
				if(playerValue>highestValue) {
					highestValue = playerValue;
					winner = player;
				}
			}
        	String name = winner.getName();
			System.out.println("Winner is: " + winner.getName());
        	int money = winner.calculateValue();
            w.gameOver(name, money);
            timer.cancel(); //Terminate the timer thread
            gameEnded = true;  //TODO: need to stop turns from occuring after game ends
        }
    }
	
	public boolean buyChoice(String propName, int propVal) {
		return w.buyChoice(propName, propVal);
		
		
	}
	
	public void addProperty(String propName){
		w.list.addProperty(propName, currentPlayer);
		
	}
	
	public void addProperty(String propName, int playerNum){
		w.list.addProperty(propName, playerNum);
		
	}

	public void auctionProperty(BuyableProperty prop) {
		int highestBid = -1;
		Player winningPlayer = null;
		int playerNum = 0;
		for(int i=0; i < ngw.names.length; i++) {
			Player player = board.getPlayer(i);
			int bid = w.getBid(player.getName(), player.getMoney());
			if (bid > highestBid) {
				highestBid = bid;
				winningPlayer = player;
				playerNum = i;
			}
		}
			
		prop.buyAuction(winningPlayer, highestBid);
		addProperty(prop.name, playerNum);
	}
	
	public void payRent(int rent, String ownerName) {
		w.payRent(rent, ownerName);
	}
	
	public boolean taxChoice() {
		return w.taxChoice();
	}
	
	public void drawnCard(Player player, String[] card) {
		w.drawnCard(player, card);
		w.append("Card has been drawn");
	}
	
	public void house(){
		if(newGamePressed){
			System.out.println("Clicked the house button.");
			ArrayList<Property> props;
			boolean[] values;
			props = board.getPlayer(currentPlayer).getUpgradeable();
			int selection = w.houseHotelSelect(props);
			if (selection < props.size()) {
				values = props.get(selection).buyImprovement(board.getPlayer(currentPlayer));
				w.boardGUI.drawHouseHotel(values, board.nameToSpaceNum(props.get(selection).getName()));
				if(values[0])
					w.list.append(board.getPlayer(currentPlayer).getName() + " bought a house/hotel on " + props.get(selection).getName());
			}
			w.top.updateMoney(board.getPlayer(currentPlayer).getMoney());
	

		}
	}

	public boolean hasRolled() {
		return hasRolled;
	}
}
