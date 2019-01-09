package a1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

public class KingTest {

	private static ChessBoard b1;

	
	@BeforeClass
	public static void setupBeforeClass() throws Exception {
		    
		    b1 = new ChessBoard();
			b1.placePiece(new King(b1,ChessPiece.Color.WHITE),"a0"); // offset applied in method
	     
				
	}
	
	@Test
	public void testLegalMoves() throws Exception, IllegalPositionException {
		
		ArrayList<String> places = new ArrayList<>(Arrays.asList("a2", "b1", "b2")); // this returns based of offset utility
		
	     ArrayList<String> ok = new ArrayList<String>(b1.getPiece("a1").legalMoves());
	     
	    for(int i = 0; i < ok.size(); i++)
	    {
	    	System.out.print(ok.get(i) + " " );
	    }
	     assertTrue(ok.containsAll(places));
	     			
	}
	
	@Test
	public void collision() throws Exception, IllegalPositionException {
		
		b1.placePiece(new King(b1,ChessPiece.Color.WHITE),"d1"); // offset applyed in method
		
		ArrayList<String> places = new ArrayList<>(Arrays.asList("d3", "d1", "e2", "c2", "e3", "e1", "c3", "c1" )); // this returns based of offset utility
		
	     ArrayList<String> ok = new ArrayList<String>(b1.getPiece("d2").legalMoves());
	     
	    for(int i = 0; i < ok.size(); i++)
	    {
	    	System.out.print(ok.get(i) + " " );
	    }
	     assertTrue(ok.containsAll(places));
	     			
	}

}
