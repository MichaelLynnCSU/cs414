import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class NewGameWindow implements ActionListener{
	
	JFrame frame;
	JLabel header;
	JLabel status;
	JPanel control;
	JPanel panel;
	JPanel card1;
	JPanel card2;
	JPanel card3;
	JTextField player1, player2, player3, player4;
	JTextField player5, player6, player7, player8, player9;
	JTextField timer1, timer2, timer3;
	String[] names;
	String[] tokens;
	int time;
	JButton play; 
	Controller controller;
	JComboBox listCombo;
	
	public NewGameWindow(Controller c){
		this.controller = c;
		frame= new JFrame("Welcome to Monopoly");
    	frame.setVisible(true);
    	frame.setSize(800, 700);
    	frame.setLayout(new GridLayout(5,1));
    	header = new JLabel("Welcome to Monopoly!!", JLabel.CENTER);
    	status = new JLabel("How many players?", JLabel.CENTER);
    	frame.add(header); frame.add(status);
    	control = new JPanel(new FlowLayout());
    	panel = new JPanel(new CardLayout());
    	player1 = new JTextField(20);player2 = new JTextField(20);
    	player3 = new JTextField(20);player4 = new JTextField(20);
    	player5 = new JTextField(20);player6 = new JTextField(20);
    	player7 = new JTextField(20);player8 = new JTextField(20);
    	player9 = new JTextField(20);timer1 = new JTextField(20);
    	timer2 = new JTextField(20);timer3 = new JTextField(20);
    	setUpCards();
	}
	
	public void setUpCards(){
		card1 = new JPanel(new GridLayout(3,2));
    	
    	card1.add(new JLabel("Name of Player 1:"));
    	card1.add(player1);
    	card1.add(new JLabel("Name of Player 2:"));
    	card1.add(player2);
    	card1.add(new JLabel("Time for Game(Seconds):"));
    	card1.add(timer1);
    	
    	card2 = new JPanel(new GridLayout(4, 2));
        card2.add(new JLabel("Name of Player 1:"));
        card2.add(player3);
        card2.add(new JLabel("Name of Player 2:"));
        card2.add(player4);
        card2.add(new JLabel("Name of Player 3:"));
        card2.add(player5);
    	card2.add(new JLabel("Time for Game(Seconds):"));
    	card2.add(timer2); 
        
        card3 = new JPanel(new GridLayout(5,2));
        card3.add(new JLabel("Name of Player 1:"));
        card3.add(player6);
        card3.add(new JLabel("Name of Player 2:"));
        card3.add(player7);
        card3.add(new JLabel("Name of Player 3:"));
        card3.add(player8);
        card3.add(new JLabel("Name of Player 4:"));
        card3.add(player9);
    	card3.add(new JLabel("Time for Game(Seconds):"));
    	card3.add(timer3);
        
        panel.add("2", card1);
        panel.add("3", card2);
        panel.add("4", card3);
        
        setUpComboBox();
        
	}
	
	public void setUpComboBox(){
		final DefaultComboBoxModel panelName = new DefaultComboBoxModel();

        panelName.addElement("2");
        panelName.addElement("3");
        panelName.addElement("4");
        listCombo = new JComboBox(panelName);    
        
        listCombo.setSelectedIndex(0);
        JScrollPane listComboScrollPane = new JScrollPane(listCombo);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
               if (listCombo.getSelectedIndex() != -1) {  
                  CardLayout cardLayout = (CardLayout)(panel.getLayout());
                  cardLayout.show(panel, 
                     (String)listCombo.getItemAt(listCombo.getSelectedIndex()));  
               }              
            }
         }); 
        
        
        control.add(listComboScrollPane);
        control.add(okButton);
        control.add(panel);
        play = new JButton("Play");
        play.addActionListener(this);
        
        control.add(play);
        frame.add(control);
	}
	
	public String[] getNames(){
		return names;
	}
	
	public String[] getTokens(){
		return tokens;
	}
	
	public int getTime() {
		return time;
	}
	
//	public void setVisible(){
//		frame.setVisible(true);
//	}
	
	public void doPlayButton(){
		int index = listCombo.getSelectedIndex();
		if(index ==0){
			if(player1.getText() ==null || player2.getText() ==null){
				return;
			}
			names = new String[2];
			names[0] = player1.getText();
			names[1] = player2.getText();
			if(names[0].equals("") || names[1].equals("")){
				names[0] = "jack";
				names[1] = "jill";
			}
			tokens = new String[2];
			tokens[0] = "thimble";
			tokens[1] = "hat";
			if(timer1.getText().equals("")){
				System.out.println("Default time 5 min");
				time = 500;
			}
			else {
				time = Integer.parseInt(timer1.getText());
			}

		}
		else if(index ==1){
			if(player3.getText() ==null || player4.getText() == null || player5.getText() ==null){
				return;
			}
			names = new String[3];
			names[0] = player3.getText();
			names[1] = player4.getText();
			names[2] = player5.getText();
			if(names[0].equals("") || names[1].equals("") || names[2].equals("")){
				names[0] = "jack";
				names[1] = "jill";
				names[2] = "frank";
			}
			tokens = new String[3];
			tokens[0] = "thimble";
			tokens[1] = "hat";
			tokens[2] = "dog";
			if(timer1.getText().equals("")){
				System.out.println("Default time 5 min");
				time = 500;
			}
			else {
				time = Integer.parseInt(timer2.getText());
			}

		}
		else{
			if(player6.getText() ==null || player7.getText() == null || player8.getText() ==null
					|| player9.getText() == null){
				return;
			}
			names = new String[4];
			names[0] = player6.getText();
			names[1] = player7.getText();
			names[2] = player8.getText();
			names[3] = player9.getText();
			if(names[0].equals("") || names[1].equals("") || names[2].equals("") || names[3].equals("")){
				names[0] = "jack";
				names[1] = "jill";
				names[2] = "frank";
				names[2] = "bob";
			}
			tokens = new String[4];
			tokens[0] = "thimble";
			tokens[1] = "hat";
			tokens[2] = "dog";
			tokens[3] = "car";
			if(timer1.getText().equals("")){
				System.out.println("Default time 5 min");
				time = 500;
			}
			else {
				time = Integer.parseInt(timer3.getText());
			}

    		
		}
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.doAction(e);
		
	}
	
	public void close(){
		frame.dispose();
	}
}
