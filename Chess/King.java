package a1;


import java.util.ArrayList;

public class King extends ChessPiece {

    public King(ChessBoard board, Color color) {
        super(board, color);
     //   System.out.println("King Constructor");
    }


    private Boolean collison(int x2, int y2) throws IllegalPositionException, IllegalMoveException {

        String alphabet = "abcdefgh";
        String col = Character.toString(alphabet.charAt(x2));
        String Pos = col+(y2+1);;


        if (board.getPiece(Pos) != null) {
            ChessPiece piece = board.getPiece(Pos);
            if (piece.color.equals(color)) {
                System.out.println("collision with " + piece);
                return true;
            }

        }

        return false;

    }

    private  ArrayList<String> moveUPright() throws IllegalPositionException, IllegalMoveException {
        int [] x = {1,-1,0,0,1,-1,1,-1};
        int [] y = {0,0,1,-1,1,1,-1,-1};
   
        ArrayList<String> ok = new ArrayList<String>();
        String alphabet = "abcdefgh";
        int moveUp = row;
        int moveSides = column;

        for (int i = 0; i < 8; i++) {
            moveUp = moveUp + x[i];

            moveSides = moveSides + y[i];

            if (moveUp < 0 || moveUp >= 8 || moveSides < 0 || moveSides >= 8) {
                System.out.println("bounds dont add this");
                moveUp = row;
                moveSides = column;
            }
            else {
                int temp = alphabet.indexOf(alphabet.charAt(moveSides));
                String movesides2 = Character.toString(alphabet.charAt(temp));

                movesides2 += Integer.toString(moveUp);
                System.out.println("move " + movesides2);

                if (collison(moveSides, moveUp)) {
                	break;
                    // do nothing
                } else {
                	System.out.println("movesides2 adderd" + movesides2);
                    ok.add(movesides2);
                }
                moveUp = row;
                moveSides = column;
            }
        }
        return ok;
    }



    public ArrayList<String> legalMoves() throws IllegalPositionException, IllegalMoveException {
        ArrayList<String> ok = new ArrayList<String>();
        ok.addAll(moveUPright());
        
        for(int i = 0; i < ok.size(); i++)
        {     
        String col =  ok.get(i).substring(0,1); 
        int row = Integer.parseInt(ok.get(i).substring(1,2));
        row++;              
        String offset = col;
        col += Integer.toString(row);     
        ok.set(i,col);
	    	}
        
        return ok;
    }


    public String toString()
    {
        if(color.equals(Color.WHITE))
            return  "\u2654";
        else
            return  "\u265A";
    }
}