import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ListPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	PropertyPanel prop;
	DescriptionPanel description;
	
	public ListPanel(){
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		prop = new PropertyPanel();
		description = new DescriptionPanel();
		this.setLayout(new GridLayout(2,1));
		this.add(prop);
		this.add(description);
	}
	
	
	public void append(String s){
		description.append(s);
	}
	
	public void addProperty(String name, int playerNum){
		prop.addProperty(name, playerNum);
		
	}
	
	public void updatePlayer(int num){
		prop.updatePlayer(num);
	}

}
