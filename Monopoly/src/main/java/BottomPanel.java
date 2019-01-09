import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BottomPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	JButton roll, endTurn, mortgage, house;
	Window frame;
	
	public BottomPanel(Window w){
		this.setLayout(new GridLayout(2,2));
		this.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5, true));
		frame = w;
		createButtons();
	}

	private void createButtons() {
		roll = new JButton("Roll Dice");
		endTurn = new JButton("End Turn");
		mortgage = new JButton("Mortgage/Unmortgage");
		house = new JButton("Buy House/Hotel");
		roll.addActionListener(frame);
		endTurn.addActionListener(frame);
		mortgage.addActionListener(frame);
		house.addActionListener(frame);
		this.add(roll);
		this.add(endTurn);
		this.add(mortgage);
		this.add(house);
		
		
	}

}
