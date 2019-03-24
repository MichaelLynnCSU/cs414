import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Window extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	BottomPanel bottom;
	TopPanel top;
	ListPanel list;
	BoardPanel boardGUI;
	NewGameWindow ngw;
	Controller controller;

	public Window (Controller c){
		setUpGraphics();
		this.controller = c;
	}

	public void setUpGraphics(){
		//frame = new JFrame("Monopoly");
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setTitle("Monopoly");
		this.setSize(1000, 700);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bottom = new BottomPanel(this);
		top = new TopPanel(this);
		boardGUI = new BoardPanel();
		list = new ListPanel();
		addToFrame();

	}

	private void addToFrame() {
		this.getContentPane().add(top, BorderLayout.NORTH);
		this.getContentPane().add(bottom, BorderLayout.SOUTH);
		this.getContentPane().add(boardGUI, BorderLayout.CENTER);
		this.getContentPane().add(list, BorderLayout.LINE_END);
		this.setVisible(true);
		
	}

	
	

	public void actionPerformed(ActionEvent e) {
		controller.doAction(e);

	}
	
	public void displayPlayers(int numPlayers){
		boardGUI.addPlayers(numPlayers);
	}

	public boolean buyChoice(String propName, int propVal) {
		JFrame frame = new JFrame();
		int buyChoice = JOptionPane.YES_NO_OPTION;
		if (JOptionPane.showConfirmDialog(null, "Would you like to buy " + propName + " for $" + propVal + "?", "Purchase Property",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			return true;
		} else {
			return false;
		}
	}

	public boolean taxChoice() {
		JFrame frame = new JFrame();
		String[] choices = {"flat", "10% net worth"};
		int taxChoice = JOptionPane.showOptionDialog(null, "Would you like to pay a flat tax of $200 or "
				+ "10% of your net worth?", "Income Tax", JOptionPane.WARNING_MESSAGE, 0, null, choices, choices[0]);

		if (taxChoice == 0) {
			//System.out.println("You chose a flat tax of $200");
			return true;
		} else {
			return false;
		}
	}

	public int mortgageChoice() {
		JFrame frame = new JFrame();
		String[] choices = {"Mortgage", "Unmortgage", "Cancel"};

		int mortgageChoice = JOptionPane.showOptionDialog(null, "Would you like to mortgage or unmortgage a property?", "Mortgage", 
				JOptionPane.WARNING_MESSAGE, 0, null, choices, choices[0]);
		return mortgageChoice;
	}
	
	public int mortgageSelect(ArrayList<BuyableProperty> props, String mort) {
		JFrame frame = new JFrame();
		String[] choices = new String[props.size() + 1];
		for(int i=0; i<props.size(); i++) 
			choices[i] = props.get(i).name;
		choices[props.size()] = "Cancel";
		int mortgageChoice = JOptionPane.showOptionDialog(null, "Select property to " + mort, mort, 
				JOptionPane.WARNING_MESSAGE, 0, null, choices, choices[0]);
		return mortgageChoice;
	}

	public int jailedOptions() {
		String[] choices = {"Roll for Doubles", "Pay $50 fine"};
		System.out.println("Jailed Options");
		int jailChoice = JOptionPane.showOptionDialog(null, "You are currently in jail.  You may pay a fine or attempt to escape.", "Jailed!", 
				NORMAL, 0, null, choices, choices[0]);
		return jailChoice;
	}
	
	public int jailedOptionsCard() {
		String[] choices = {"Roll for Doubles", "Pay $50 fine", "Get Out of Jail Free card"};
		System.out.println("Jailed Options");
		int jailChoice = JOptionPane.showOptionDialog(null, "You are currently in jail.  You may pay a fine, attempt to escape, or use your card.", "Jailed!", 
				NORMAL, 0, null, choices, choices[0]);
		return jailChoice;
	}
	
	public void jailedSpeeding() {
		JFrame f = new JFrame();
		JOptionPane.showMessageDialog(f,"You have been jailed for speeding.");  
	}
	
	public void failedEscape(int die1, int die2) {
		JFrame f = new JFrame();
		JOptionPane.showMessageDialog(f,"You rolled " + die1 + " and " + die2 + ".  You did not escape jail.");
	}
	
	public void successEscape() {
		JFrame f = new JFrame();
		JOptionPane.showMessageDialog(f,"You rolled doubles and successfully escaped jail.");
	}
	
	public void forcedFine(int die1, int die2) {
		JFrame f = new JFrame();
		JOptionPane.showMessageDialog(f,"You rolled " + die1 + " and " + die2 + ".  "
				+ "Since this was your third night in jail, you were forced to pay the fine.");
	}
	
	public void alreadyRolled() {
		JFrame f = new JFrame();
		JOptionPane.showMessageDialog(f,"You have already rolled this turn.");
	}
	
	public void rolledDoubles() {
		JFrame f = new JFrame();
		JOptionPane.showMessageDialog(f,"You rolled doubles. You get another turn.");
	}
	
	public void notRolled() {
		JFrame f = new JFrame();
		JOptionPane.showMessageDialog(f,"You must roll before ending your turn.");
	}
	
	public void payRent(int rent, String ownerName) {
		JFrame f = new JFrame();
		JOptionPane.showMessageDialog(f, "You pay rent of $" + rent + " to " + ownerName);
	}
	
	public int getBid(String name, int money) {
		String bidS = JOptionPane.showInputDialog(null, name + ": please enter your bid (MAX" + money + ")");
		int bid = 0;
		try {
			bid = Integer.parseInt(bidS);
		} catch(NumberFormatException e) {}
		if (bid > money)
			bid = money;
		return bid;
	}
	
	public void wonAuction(String name, int money) {
		JFrame f = new JFrame();
		JOptionPane.showMessageDialog(f, "Player " + name + " won the auction with a bid of " + money + ".");
	}
	
	public void gameOver(String name, int money) {
		JFrame f = new JFrame();
		JOptionPane.showMessageDialog(f, "The game has ended. " + name + " has won with $ " + money + ".");
	}
	
	public void drawnCard(Player player, String[] card) {
		JFrame f = new JFrame();
		JOptionPane.showMessageDialog(f, card[0] + ": " + player.getName() + ": " + card[1]);
	}
	
	public void append(String s){
		list.append(s);
	}
	
	public int houseHotelSelect(ArrayList<Property> upgradeableProps) {
		JFrame frame = new JFrame();
		String[] choices = new String[upgradeableProps.size() + 1];
		for(int i=0; i<upgradeableProps.size(); i++) 
			choices[i] = upgradeableProps.get(i).name;
		choices[upgradeableProps.size()] = "Cancel";
		int houseHotelChoice = JOptionPane.showOptionDialog(null, "Select property to upgrade", "Upgrade", 
				JOptionPane.WARNING_MESSAGE, 0, null, choices, choices[0]);
		return houseHotelChoice;
	}

}
