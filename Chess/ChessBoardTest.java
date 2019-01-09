package a1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class ChessBoardTest {
	
	private static ChessBoard b1;
	private static int boardSize = 8;


	
	
	@BeforeClass
	public static void setupBeforeClass() throws Exception {
		    
		    b1 = new ChessBoard();
			b1.placePiece(new Rook(b1,ChessPiece.Color.WHITE),"a0"); // offset applyed in method
			b1.move("a1", "a8");
	     
				
	}

	@After
	public void tearDown() throws Exception {
	    b1 = new ChessBoard();
		b1.placePiece(new Rook(b1,ChessPiece.Color.WHITE),"a0"); // offset applyed in method
		b1.move("a1", "a8");
	}
	
	@Test
	public void testChessBoard() {
		
		assertEquals(boardSize, b1.board.length);
		assertEquals(boardSize, b1.board[0].length);
		


	}

	@Test
	public void testInitialize() throws IllegalPositionException {
		  
		assertEquals(b1.board[7][0].color, ChessPiece.Color.WHITE);		
		 String posCheck = b1.board[7][0].getPosition(); // return correct getPosition despite offset
		 assertTrue(posCheck.equals("a8"));

	
		
	}

	@Test
	public void testPlacePiece() throws IllegalPositionException {
		
		assertTrue(b1.board[7][0] instanceof Rook);
		
	}

	@Test
	public void testGetPiece() throws IllegalPositionException {
		
		assertTrue(b1.getPiece("a8") instanceof Rook);
		
	       try {
	    	   b1.getPiece("a9");
	    	   fail( "My method didn't throw when I expected it to" );
		} catch (IllegalPositionException e) {
		    assertEquals("illegal characters", e.getMessage());	
		       
		}
	       
    
	

	}

	@Test 
	public void testMove() throws Exception, IllegalPositionException {

	 //Bounds check
	       try {
	   		b1.placePiece(new Rook(b1,ChessPiece.Color.WHITE),"e0"); // offset applyed in method
			b1.move("e1", "e9");
			fail( "My method didn't throw when I expected it to" );
		} catch (IllegalMoveException e) {
		    assertEquals("Collision with same color or illegal pawn move ", e.getMessage());	
		       
		}
	       
       
	} 
	       
	@Test
	public void testCaptureRook() throws IllegalPositionException, IllegalMoveException {
				
		b1.placePiece(new Rook(b1,ChessPiece.Color.WHITE),"a0");	
		b1.placePiece(new Rook(b1,ChessPiece.Color.BLACK),"a7");
	

		     System.out.println("testcase capture" );
		   assertEquals(b1.getPiece("a1").color,ChessPiece.Color.WHITE);
		   assertEquals(b1.getPiece("a8").color,ChessPiece.Color.BLACK);
		   
		  b1.move("a1", "a8"); 
		  
		  assertEquals(b1.getPiece("a8").color,ChessPiece.Color.WHITE);

		
	
		b1.placePiece(new Rook(b1,ChessPiece.Color.BLACK),"h7");
		b1.placePiece(new Rook(b1,ChessPiece.Color.WHITE),"h1");

		   assertEquals(b1.getPiece("h8").color,ChessPiece.Color.BLACK);
		   
		  b1.move("h8", "h1"); 
		  
		   assertEquals(b1.getPiece("h1").color,ChessPiece.Color.BLACK);
		  

		  

	}
	
	@Test
	public void testCaptureBishop() throws IllegalPositionException, IllegalMoveException {
				
		b1.placePiece(new Bishop(b1,ChessPiece.Color.WHITE),"a0");	
		b1.placePiece(new Bishop(b1,ChessPiece.Color.BLACK),"h7");
	

		     System.out.println("testcase capture" );
		   assertEquals(b1.getPiece("a1").color,ChessPiece.Color.WHITE);
		   assertEquals(b1.getPiece("h8").color,ChessPiece.Color.BLACK);
		   
		  b1.move("a1", "h8"); 
		  
		  assertEquals(b1.getPiece("a8").color,ChessPiece.Color.WHITE);
	
		b1.placePiece(new Bishop(b1,ChessPiece.Color.BLACK),"h0");
		b1.placePiece(new Bishop(b1,ChessPiece.Color.WHITE),"a7");

		   assertEquals(b1.getPiece("h1").color,ChessPiece.Color.BLACK);
		   
		  b1.move("h1", "a8"); 

		  
		   assertEquals(b1.getPiece("a8").color,ChessPiece.Color.BLACK);
		  

		  

	}
	
	@Test
	public void testCaptureKing() throws IllegalPositionException, IllegalMoveException {
				
		b1.placePiece(new King(b1,ChessPiece.Color.WHITE),"f0");	
		b1.placePiece(new Rook(b1,ChessPiece.Color.BLACK),"f1");
	

		     System.out.println("testcase capture" );
		   assertEquals(b1.getPiece("f1").color,ChessPiece.Color.WHITE);
		   assertEquals(b1.getPiece("f2").color,ChessPiece.Color.BLACK);
		   
		  b1.move("f1", "f2"); 
		  
		  assertEquals(b1.getPiece("f2").color,ChessPiece.Color.WHITE);
	
		b1.placePiece(new King(b1,ChessPiece.Color.BLACK),"f7");
		b1.placePiece(new Rook(b1,ChessPiece.Color.WHITE),"f6");

		   assertEquals(b1.getPiece("f8").color,ChessPiece.Color.BLACK);
		   
		  b1.move("f8", "f7"); 

		  
		   assertEquals(b1.getPiece("f7").color,ChessPiece.Color.BLACK);
		  

		  

	}

	@Test
	public void movePawnx2() throws IllegalPositionException, IllegalMoveException {
				
		b1.placePiece(new Pawn(b1,ChessPiece.Color.WHITE),"e0");	

		   assertEquals(b1.getPiece("e1").color,ChessPiece.Color.WHITE);
		   
		  b1.move("e1", "e3"); 
		  
		  assertEquals(b1.getPiece("e3").color,ChessPiece.Color.WHITE);
			  

	}
	
	@Test
	public void movePawnx1() throws IllegalPositionException, IllegalMoveException {
				
		b1.placePiece(new Pawn(b1,ChessPiece.Color.WHITE),"e0");	

		   assertEquals(b1.getPiece("e1").color,ChessPiece.Color.WHITE);
		   
		  b1.move("e1", "e2"); 
		  
		  assertEquals(b1.getPiece("e2").color,ChessPiece.Color.WHITE);
			  

	}
	
	@Test
	public void testPawnCapture() throws IllegalPositionException, IllegalMoveException {
				
		b1.placePiece(new Pawn(b1,ChessPiece.Color.WHITE),"e0");	
		b1.placePiece(new Pawn(b1,ChessPiece.Color.BLACK),"d1");

		   assertEquals(b1.getPiece("e1").color,ChessPiece.Color.WHITE);
		   
		  b1.move("e1", "d2"); 
		  
		  assertEquals(b1.getPiece("d2").color,ChessPiece.Color.WHITE);
			  

	}

	@Test
	public void testPawnCollision() throws IllegalPositionException, IllegalMoveException {
				
		b1.placePiece(new Pawn(b1,ChessPiece.Color.WHITE),"e0");	
		b1.placePiece(new Pawn(b1,ChessPiece.Color.WHITE),"e1");

		   assertEquals(b1.getPiece("e1").color,ChessPiece.Color.WHITE);
			 //Bounds check
	       try {
	    	   	b1.move("e1", "e2"); 
	    	   	fail( "My method didn't throw when I expected it to" );
		} catch (IllegalMoveException e) {
		    assertEquals("Collision with same color or illegal pawn move ", e.getMessage());	
		       
		}
	}
	       
	@Test
	public void testPawnCollisionBlack() throws IllegalPositionException, IllegalMoveException {
				
		b1.placePiece(new Pawn(b1,ChessPiece.Color.BLACK),"e7");	
		b1.placePiece(new Pawn(b1,ChessPiece.Color.BLACK),"e6");

		   assertEquals(b1.getPiece("e8").color,ChessPiece.Color.BLACK);
			 //Bounds check
	       try {
	    	   	b1.move("e8", "e7"); 
	    	   	fail( "My method didn't throw when I expected it to" );
		} catch (IllegalMoveException e) {
		    assertEquals("Collision with same color or illegal pawn move ", e.getMessage());	
		       
		}
	}
	
	
	
	   	@Test
		public void testPawnCaptureWrongColor() throws IllegalPositionException, IllegalMoveException {
					
			b1.placePiece(new Pawn(b1,ChessPiece.Color.WHITE),"e0");	
			b1.placePiece(new Pawn(b1,ChessPiece.Color.WHITE),"d1");

			   assertEquals(b1.getPiece("e1").color,ChessPiece.Color.WHITE);
	
			   
		       try {
		    	     b1.move("e1", "d2");
		  		   fail( "My method didn't throw when I expected it to" );
			} catch (IllegalMoveException e) {
			    assertEquals("Collision with same color or illegal pawn move ", e.getMessage());	
			       
			}
			
			  
		
				  

		}
	
	   	@Test
		public void pawnX1toX2MoveTest() throws IllegalPositionException, IllegalMoveException {
					
			b1.placePiece(new Pawn(b1,ChessPiece.Color.WHITE),"e0");	


			   assertEquals(b1.getPiece("e1").color,ChessPiece.Color.WHITE);
			 
				       try {
				    b1.move("e1", "e2");
		    	     b1.move("e2", "e4");
		    	     fail( "My method didn't throw when I expected it to" );
						} catch (IllegalMoveException e) {
						    assertEquals("Collision with same color or illegal pawn move ", e.getMessage());	
						       
						}

			
			  
		
				  

		}
		  

			  

	




}
