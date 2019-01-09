import java.awt.*;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardPanel extends JPanel{
	
	ImageIcon board;
	Image image, thimble, hat, dog, car;
	JLabel picLabel;
	int players;
	boolean addedPlayers = false;
	boolean addHouses = false;
	Color houseColor = Color.GREEN;
	boolean up, down, left, right;
	int xDiff, yDiff;
	Image[] tokens;
	Image[] images;
	int[][] coordinates;
	int[][] startingPoints;
	int[][] hotelCoordinates = new int[12][2];
	int[][] houseCoordinates = new int[32][2];
	int[] houseSpaces = new int[32];
	int[] hotelSpaces = new int[12];
	int houseCount = 0;
	int hotelCount = 0;
	
	public BoardPanel(){
		board = new ImageIcon(getImage("images/board.jpg"));
		image = board.getImage().getScaledInstance(450, 450, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(getImage("images/thimble.jpg"));
		thimble = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		icon = new ImageIcon(getImage("images/hat.jpeg"));
		hat = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		icon = new ImageIcon(getImage("images/dog.jpeg"));
		dog = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		icon = new ImageIcon(getImage("images/car.jpeg"));
		car = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		images = new Image[4];
		images[0] = thimble;
		images[1] = hat;
		images[2] = dog;
		images[3] = car;
	}

	// Found on stack overflow: https://stackoverflow.com/questions/10464816/maven-packaging-images-in-the-root-of-the-jar-file
	// Use this method to fetch all ImageIcon images
	// This will ensure they are packaged into the jar correctly
	public static Image getImage(final String pathAndFileName) {
		final URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
		return Toolkit.getDefaultToolkit().getImage(url);
	}
	
	
	public void paint(Graphics g){
		g.drawImage(image, 30, 0, this);
		if(addedPlayers){
			int index = 0;
			for(Image image: tokens){
				g.drawImage(image, coordinates[index][0], coordinates[index][1], this);
				index++;
				}
			if(addHouses){
				g.setColor(Color.GREEN);
				for(int i=0; i<32; i++){
					if(houseCoordinates[i][0] !=0 && houseCoordinates[i][1] !=0)
						g.fillRect(houseCoordinates[i][0], houseCoordinates[i][1], 10, 10);
				}
				g.setColor(Color.RED);
				for(int i=0; i<hotelCount; i++){
					g.fillRect(hotelCoordinates[i][0], hotelCoordinates[i][1], 15, 10);
				}
				
			}
		}
	}
	 
	public void addPlayers(int num){
		players = num;
		addedPlayers = true;
		tokens = new Image[num];
		for(int i=0; i< num; i++)
			tokens[i] = images[i];
		coordinates = new int[num][2];
		//430 400,  
		//455 400, 
		//430 430 ,
		//455 430
		for(int i=0; i< num; i++)
			for(int j=0; j<2; j++){
				if((i==0 ||i==2) && j==0)
					coordinates[i][j] = 430;
				if((i == 1 || i== 3) && j==0)
					coordinates[i][j] = 455;
				if((i==0 || i==1) && j==1)
					coordinates[i][j] = 400;
				if((i==2 || i==3) && j==1)
					coordinates[i][j] = 430;
			}
		startingPoints = new int[num][2];
		for(int i=0; i<num;i++)
			for(int j=0; j<2; j++)
				startingPoints[i][j] = coordinates[i][j];
		repaint();
	}
	
	public void move(int spaces, int currPos, int currPlayer, boolean threeDoubles){
		//System.out.println("Spaces: " + spaces + ", currPos: " + currPos + ", currPluer: " + currPlayer);
		if((spaces + currPos == 30) || threeDoubles){//go to jail space
			coordinates[currPlayer][1] = startingPoints[currPlayer][1];
			if (currPlayer == 1 || currPlayer == 3) 
				coordinates[currPlayer][0] = startingPoints[currPlayer][0] - 410;
			else
				coordinates[currPlayer][0] = startingPoints[currPlayer][0] - 380;
			repaint();
			return;
		}
		
		if(currPos < 10){ 
			left = true;
			if((currPos + spaces <=20) &&(currPos + spaces >10))
				up = true;
			else if((currPos + spaces <30) &&(currPos + spaces >20)){
				up = true;
				right = true;
			}
		}
		else if(currPos < 20){
			up = true;
			if((currPos + spaces <=30) && (currPos + spaces > 20))
				right = true;
			else if((currPos+spaces <40) && (currPos + spaces >30)){
				right = true;
				down = true;
			}
		}
		else if(currPos < 30) {
			right = true;
			if((currPos + spaces<=40) && (currPos + spaces >30))
				down = true;
			else if((((currPos + spaces) % 40) <10) && ((currPos + spaces) % 40!=0)){
				down = true;
				left = true;
			}
				
		}
		else {
			down = true;
			if(((currPos + spaces) % 40 <=10) && ((currPos + spaces) % 40!=0))
				left = true;
			else if((currPos + spaces) %40 < 20 && (currPos + spaces) %40 > 10){
				left = true;
				up = true;
			}
		}
		
		int leftover = spaces;
		if(left && !down) leftover = moveLeft(spaces, currPlayer);
		if(up) leftover = moveUp(leftover, currPlayer);
		if(right) leftover = moveRight(leftover, currPlayer);
		if(down) {
			leftover = moveDown(leftover, currPlayer);
			if (left) leftover = moveLeft(leftover, currPlayer);
		}
		
		
		
		left = false; down = false; right = false; up = false;
		repaint();
	}
	
	public int moveLeft(int spaces, int currPlayer){
		int diff = 37; int size = 380;
		if(currPlayer ==1 || currPlayer ==3) {
			diff = 40;
			size = 410;
		}
		int i=0;
		int x = coordinates[currPlayer][0];
		for(; i<spaces; i++){
			if(x - diff > startingPoints[currPlayer][0]-size){
				x -=diff;
			}
			else break;
		}
		coordinates[currPlayer][0] = x;
		return spaces -i;
		
	}
	public int moveUp(int spaces, int currPlayer){
		int diff = 37;int size = 380;
		if(currPlayer ==2 || currPlayer ==3){ 
			diff = 41;
			size = 420;
		}
		int i=0;
		int y = coordinates[currPlayer][1];
		for(; i<spaces; i++){
			if(y -diff > startingPoints[currPlayer][1] - size)
				y -=diff;
			else break;
		}
		coordinates[currPlayer][1] = y;
		return spaces -i;
		
	}
	public int moveRight(int spaces, int currPlayer){
		int diff = 37;
		int size = 20;
		if(currPlayer==1) {
			diff = 40;
			size = 35;
		}
		if(currPlayer==3) diff = 41;
		int i=0;
		int x = coordinates[currPlayer][0];
		for(; i<spaces; i++){
			if(x + diff < startingPoints[currPlayer][0]+size)
				x +=diff;
			else break;
		}
		coordinates[currPlayer][0] = x;
		return spaces -i;
		
	}
	public int moveDown(int spaces, int currPlayer){
		int diff = 37;
		int size = 20;
		if(currPlayer == 2 || currPlayer == 3){
			diff = 41;
			size = 25;
		}
		int i=0;
		int y = coordinates[currPlayer][1];
		for(; i<spaces; i++){
			if(y +diff < startingPoints[currPlayer][1]+size)
				y +=diff;
			else break;
		}
		coordinates[currPlayer][1] = y;
		return spaces -i;
		
	}
	
	public void drawHouseHotel(boolean[] values, int space){
		addHouses = true;
		if(values[0] == false)
			return;
		if(values[1] == true){
			if(houseCount ==32)
				return;
			else calculateHouseCoordinates(space);
		}
		else {
			if(hotelCount == 12)
				return;
			else calculateHotelCoordinates(space);
		}
		
		repaint();
			
	}
	
	public void calculateHouseCoordinates(int space){
		int x = 0;
		int y = 0;

		if(space <=10){
			x = (12-space)*37;
			y = 390;
			
		}
		else if(space <=20){
			x = 40;
			y = (22-space) * 37;
		}
		else if(space <=30){
			x = (space-18)*37;
			y = 70;
		}
		else if(space < 40){
			x = 400;
			y = (space -28) * 37;
		}
		houseCoordinates[houseCount][0] = x;
		houseCoordinates[houseCount][1] = y;
		houseSpaces[houseCount] = space;
		houseCount++;
	}
	
	public void calculateHotelCoordinates(int space){
		int x=0; int y = 0;
		if(space <=10){
			x = (11-space)*37;
			y = 390;
			
		}
		else if(space <=20){
			x = 40;
			y = (21-space) * 37;
		}
		else if(space <=30){
			x = (space-19)*37;
			y = 70;
		}
		else if(space < 40){
			x = 400;
			y = (space -29) * 37;
		}
		hotelCoordinates[hotelCount][0] = x;
		hotelCoordinates[hotelCount][1] = y;
		hotelSpaces[hotelCount-1] = space;
		removeHouses(space);
		hotelCount++;
	}
	
	public void removeHouses(int space){
		int index=0;
		for(int i=0; i< houseSpaces.length; i++){
			if(houseSpaces[i] == space){
				index = i;
				houseSpaces[i] = -1;
				houseCoordinates[index+1][0] = 0;
				houseCoordinates[index+1][1] = 0;
				houseCount--;
			}
		}
		
		
	}
	 
}
