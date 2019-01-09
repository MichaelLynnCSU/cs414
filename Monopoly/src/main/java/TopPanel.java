import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TopPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	JButton newGame;
	JLabel rollLabel;
	JTextField playerText, moneyText, timerText;
	Window frame;
	
	public TopPanel(Window w){
		this.setBorder(BorderFactory.createLineBorder(Color.red, 7));
		frame = w;
		this.setLayout(new FlowLayout());
		createTextFields();
		createButton();
		addToPanel();
	}

	private void addToPanel() {
		JLabel player = new JLabel("Player: ", JLabel.RIGHT);
		JLabel money = new JLabel("Money: ", JLabel.CENTER);
		JLabel time = new JLabel("Time: ", JLabel.RIGHT);
		this.add(newGame);
		this.add(player);
		this.add(playerText);
		this.add(money);
		this.add(moneyText);
		this.add(time);
		this.add(timerText);
		this.add(rollLabel);
		
	}

	private void createButton() {
		newGame = new JButton("New Game");
		newGame.addActionListener(frame);
		
	}

	private void createTextFields() {
		playerText = new JTextField(10);
		moneyText = new JTextField(15);
		timerText = new JTextField(15);
		playerText.setEditable(false);
		moneyText.setEditable(false);
		rollLabel = new JLabel("You moved:___ ", JLabel.CENTER);
		
	}
	
	public void updateRollLabel(int num){
		rollLabel.setText("You moved: " + num);
		
	}
	
	public void setPlayerAndMoney(String name, int money){
		playerText.setText(name);
		moneyText.setText(Integer.toString(money));
	}
	
	public void updateMoney(int money){
		moneyText.setText(Integer.toString(money));
	}
	
	
	public void updateTimer(int second){
		timerText.setText(Integer.toString(second));
		
	}
	

}
