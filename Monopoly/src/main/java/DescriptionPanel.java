import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DescriptionPanel extends JPanel{


	private static final long serialVersionUID = 1L;
	JTextArea text;
	JScrollPane descriptionBox;
	
	public DescriptionPanel(){
		text = new JTextArea(10,30);
		descriptionBox = new JScrollPane(text);
		this.add(descriptionBox);
	}
	
	public void append(String s){
		text.append(s + "\n");
	}

}
