package a1;


import java.util.ArrayList;

public class Rook extends ChessPiece{

	int test = 0;
	
    public Rook(ChessBoard board, Color color) {
        super(board, color);
    //    System.out.println("Rook Constructor");
    }


    private Boolean collison(int x2, int y2) throws IllegalPositionException, IllegalMoveException {

        String alphabet = "abcdefgh";
        String col =  Character.toString(alphabet.charAt(x2));
        String Pos = col+(y2+1);
        if(board.getPiece(Pos) != null) {
            ChessPiece piece = board.getPiece(Pos);
            if (piece.color.equals(color)) {
            	test = 0;
                System.out.println("collision with " + piece + color + piece.color);
                return true;
            }
            if (!piece.color.equals(color)) {
            	if(color.equals(ChessPiece.Color.BLACK))
            	{
            	test = 1; // if moving black move set this
            	}
                System.out.println("collision capture " + piece + color + piece.color);
                return true;
            }
        }

        return false;

    }


    private  ArrayList<String> moveUP() throws IllegalPositionException, IllegalMoveException {
        System.out.println("move up");
        ArrayList<String> ok = new ArrayList<String>();
        String alphabet = "abcdefgh";
        int moveUp = row;
        int moveSides = column;
        String collision = "";
            for(int i = row ; i <  7; i++)
            {
                moveUp++;
                if (moveUp < 0 || moveUp > 8 || moveSides < 0 || moveSides > 8) {
                    System.out.println("bounds dont add this");
                }
                else {
                	
                    int temp = alphabet.indexOf(alphabet.charAt(moveSides));
                    String movesides2 = Character.toString(alphabet.charAt(temp));

                    movesides2 += Integer.toString(moveUp);
                    
             	   System.out.println(movesides2);
             	   
                    if (collison(moveSides, moveUp)) {
                    	
                    	
 
                        String col =  Character.toString(alphabet.charAt(moveSides));
                        String Pos = col+(moveUp+1);
                        ChessPiece piece = board.getPiece(Pos);
                    	 if (!piece.color.equals(color)) {            
                    	       ok.add(movesides2);
                    	       break;
                    	 }
                    	 else{
                    		 for(int k = 0; k < ok.size(); k++)
                 		    {
                 		    	System.out.print(ok.get(k) + " " );
                 		    }
                    		   System.out.println("same color dont add " + movesides2);
                    	         ok.remove(ok.size() - 1);
                    		 break;
                    	 }
                    } else {
                        ok.add(movesides2);
                    }
                }
                
                
            }

        return ok;
    }

    private  ArrayList<String> moveRight() throws IllegalPositionException, IllegalMoveException {
        System.out.println("move right");
            ArrayList<String> ok = new ArrayList<String>();
            String alphabet = "abcdefgh";
            int moveUp = row;
            int moveSides = column;

            for(int i = column; i <  7; i++)
            {
                moveSides++;
                if (moveUp < 0 || moveUp > 8 || moveSides < 0 || moveSides > 8) {
                    System.out.println("bounds dont add this");
                }
                else {
                    int temp = alphabet.indexOf(alphabet.charAt(moveSides));
                    String movesides2 = Character.toString(alphabet.charAt(temp));
                    movesides2 += Integer.toString(moveUp);

                    if (collison(moveSides, moveUp)) {

                    	
                    	
                    	 
                        String col =  Character.toString(alphabet.charAt(moveSides));
                        String Pos = col+(moveUp+1);
                        ChessPiece piece = board.getPiece(Pos);
                    	 if (!piece.color.equals(color)) {            
                    	       ok.add(movesides2);
                    	       break;
                    	 }
                    	 else{
                    		 for(int k = 0; k < ok.size(); k++)
                 		    {
                 		    	System.out.print(ok.get(k) + " " );
                 		    }
                    		   System.out.println("same color dont add " + movesides2);
                    	         ok.remove(ok.size() - 1);
                    		 break;
                    	 }
                    
                    } else {
                        ok.add(movesides2);
                    }
                }

            }
            return ok;
        }

    private  ArrayList<String> moveDown() throws IllegalPositionException, IllegalMoveException {
        System.out.println("move down");
        ArrayList<String> ok = new ArrayList<String>();
        String alphabet = "abcdefgh";
        int moveDown = row;
        int moveSides = column;

        for(int i = row ; i >=  0; i--)
        {
            System.out.println(i);
            moveDown--;
            if (moveDown < 0 || moveDown > 8 || moveSides < 0 || moveSides > 8) {
                System.out.println("bounds dont add this");
            }
            else {
                int temp = alphabet.indexOf(alphabet.charAt(moveSides));
                String movesides2 = Character.toString(alphabet.charAt(temp));

                movesides2 += Integer.toString(moveDown);
      
              	System.out.print(movesides2 + " ");
                if (collison(moveSides, moveDown)) {
                    String col =  Character.toString(alphabet.charAt(moveSides));
                    String Pos = col+(moveDown+1);
                    ChessPiece piece = board.getPiece(Pos);
                	 if (!piece.color.equals(color)) {  

                	       ok.add(movesides2);
                	       
                	       for(int k = 0; k < ok.size(); k++)
               		    {
               		    	System.out.print(ok.get(k) + " " );
               		    }
                	       
                	       
                	       break;
                	 }
                	 else{
                		 for(int k = 0; k < ok.size(); k++)
             		    {
             		    	System.out.print("wtf is happening " + ok.get(k) + " " );
             		    }
                		   System.out.println("same color dont add " + movesides2);
                	         ok.remove(ok.size() - 1);
                		 break;
                	 }
                } else {
                    ok.add(movesides2);
                }
            }

        }

        return ok;
    }


    private  ArrayList<String> moveLeft() throws IllegalPositionException, IllegalMoveException {
        System.out.println("move left");
        ArrayList<String> ok = new ArrayList<String>();
        String alphabet = "abcdefgh";
        int moveUp = row;
        int moveSides = column;

        for(int i = column; i >  0; i--)
        {
            moveSides--;
            if (moveUp < 0 || moveUp >= 8 || moveSides < 0 || moveSides >= 8) {
                System.out.println("bounds dont add this");
            }
            else {
                int temp = alphabet.indexOf(alphabet.charAt(moveSides));
                String movesides2 = Character.toString(alphabet.charAt(temp));
                movesides2 += Integer.toString(moveUp);

                if (collison(moveSides, moveUp)) {
                	
                	
                    String col =  Character.toString(alphabet.charAt(moveSides));
                    String Pos = col+(moveUp+1);
                    ChessPiece piece = board.getPiece(Pos);
                	 if (!piece.color.equals(color)) {            
                	       ok.add(movesides2);
                	       break;
                	 }
                	 else{
                		 for(int k = 0; k < ok.size(); k++)
             		    {
             		    	System.out.print(ok.get(k) + " " );
             		    }
                		   System.out.println("same color dont add " + movesides2);
                	        ok.remove(ok.size() - 1);
                		 break;
                	 }
                } else {
                    ok.add(movesides2);
                }
            }

        }
        return ok;
    }

    public ArrayList<String> legalMoves() throws IllegalPositionException, IllegalMoveException {

        System.out.println(" color " + color);

        System.out.println("current position " + column + row);

        ArrayList<String> ok = new ArrayList<String>();




               ok.addAll(moveUP());
                ok.addAll(moveRight()); 
                ok.addAll(moveLeft());
               ok.addAll(moveDown());
              

        
            if(test != 1)
            {
               for(int i = 0; i < ok.size(); i++)
               {     
               String col =  ok.get(i).substring(0,1); 
               int row = Integer.parseInt(ok.get(i).substring(1,2));
               row++;
        //       System.out.println(col + row);               
               String offset = col;
               col += Integer.toString(row);     
               ok.set(i,col);
   		    	}
            }
               
   	     
              

        return ok;
    }


    public String toString()
    {
        if(color.equals(Color.WHITE))
            return   "\u2656";
        else
            return  "\u265C";
    }


}