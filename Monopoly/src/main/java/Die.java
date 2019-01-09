import java.util.Random;

public class Die {
	int firstNum;
	int secondNum;
	Random rand;
	boolean onlyDoubles;
	boolean mortgage;
	boolean propImprov;
	boolean chance;
	boolean community;
	int count =0;
	
	public Die(String arg){
		rand = new Random();

		onlyDoubles = arg.equals("-d");
		mortgage = arg.equals("-m");
		propImprov = arg.equals("-p");
		chance = arg.equals("-h");
		community = arg.equals("-c");
		
	}
	
	public void roll(){
		firstNum = rand.nextInt(6) + 1;
		secondNum = rand.nextInt(6) + 1;
		
		if(onlyDoubles)
			secondNum = firstNum;

		if(mortgage) {
			firstNum = 2;
			secondNum = 4;
		}
		if(propImprov){
			if(count == 0){
				firstNum = 1;
				secondNum = 0;
				count++; 
				return;
			}
			if(count == 1){
				firstNum = 2;
				secondNum = 0;
			}
		}
		if(community) {
			firstNum = 2;
			secondNum = 0;
		}
		if(chance) {
			firstNum = 4;
			secondNum = 3;
		}
	}
	
	public int getFirstNum(){
		return firstNum;
	}
	
	public int getSecondNum(){
		return secondNum;
	}
	
	public int getSum(){
		return firstNum + secondNum;
	}
	
	public boolean isDoubles() {
		return (firstNum == secondNum);
	}
	
	
}
