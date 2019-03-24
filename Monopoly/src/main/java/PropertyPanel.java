import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PropertyPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	ArrayList<Image> properties1;
	ArrayList<Image> properties2;
	ArrayList<Image> properties3;
	ArrayList<Image> properties4;
	ArrayList<ArrayList<Image>> playerProperties;
	int currentPlayer = 0;
	

	public PropertyPanel(){
		properties1 = new ArrayList<Image>();
		properties2 = new ArrayList<Image>();
		properties3 = new ArrayList<Image>();
		properties4 = new ArrayList<Image>();
		playerProperties = new ArrayList<ArrayList<Image>>();
		playerProperties.add(properties1);
		playerProperties.add(properties2);
		playerProperties.add(properties3);
		playerProperties.add(properties4);
		
		
	}
	
	public void paint(Graphics g){
		//draws property images
		ArrayList<Image> props = playerProperties.get(currentPlayer);
		int x = 0;
		int y = 0;
		for(Image i: props){
			g.drawImage(i, x, y, this);
			if(x + 100 < 300)
				x+=100;
			else{
				y+=100;
				x=0;
			}
			
		}
		
	}
	
	public static Image getImage(final String pathAndFileName) {
		final URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
		return Toolkit.getDefaultToolkit().getImage(url);
	}
	
	public void addProperty(String name, int playerNum){
		name = name.replaceAll("\\s", "");
		ImageIcon icon = new ImageIcon(getImage("images/" + name + ".jpeg"));
		
		Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		playerProperties.get(playerNum).add(image);
		repaint();
	}
	
	public void updatePlayer(int num){
		currentPlayer = num;
		repaint();
	}
	
}
