package a1;

import java.util.ArrayList;

public class ChessBoard {

    // array of ChessPiece Objects
    ChessPiece[][] board;
    boolean init = true;
    String toPosition = "";

    //constructor
    ChessBoard()
    {
    //    System.out.println("Board Constructor");

        board = new ChessPiece[8][8];
        for(int i=0; i < 8; i++)
        {
            for(int j=0; j < 8; j++) {
                board[i][j] = null;
            }
        }

    }


    public void initialize() throws IllegalPositionException {

       placePiece(new Rook(this, ChessPiece.Color.WHITE),offSetUtility("a1"));
       placePiece(new Knight(this,ChessPiece.Color.WHITE),offSetUtility("b1"));
       placePiece(new Bishop(this,ChessPiece.Color.WHITE),offSetUtility("c1"));
       placePiece(new Queen(this,ChessPiece.Color.WHITE),offSetUtility("d1"));
       placePiece(new King(this,ChessPiece.Color.WHITE),offSetUtility("e1"));
       placePiece(new Bishop(this,ChessPiece.Color.WHITE),offSetUtility("f1"));
       placePiece(new Knight(this,ChessPiece.Color.WHITE),offSetUtility("g1"));
       placePiece(new Rook(this,ChessPiece.Color.WHITE),offSetUtility("h1"));


       String alphabet = "abcdefgh";

       for(int i=0; i < 8; i++)
       {
           String x = Character.toString(alphabet.charAt(i));
           placePiece(new Pawn(this,ChessPiece.Color.WHITE),x + 1);
       }
//
        placePiece(new Rook(this, ChessPiece.Color.BLACK),offSetUtility("a8"));
        placePiece(new Knight(this,ChessPiece.Color.BLACK),offSetUtility("b8"));
        placePiece(new Bishop(this,ChessPiece.Color.BLACK),offSetUtility("c8"));
        placePiece(new Queen(this,ChessPiece.Color.BLACK),offSetUtility("d8"));
        placePiece(new King(this,ChessPiece.Color.BLACK),offSetUtility("e8"));
        placePiece(new Bishop(this,ChessPiece.Color.BLACK),offSetUtility("f8"));
        placePiece(new Knight(this,ChessPiece.Color.BLACK),offSetUtility("g8"));
        placePiece(new Rook(this,ChessPiece.Color.BLACK),offSetUtility("h8"));

        for(int i=0; i < 8; i++)
        {
            String x = Character.toString(alphabet.charAt(i));
           placePiece(new Pawn(this,ChessPiece.Color.BLACK),x + 6);
        }

        init = false;

    }

    private boolean boundsCheck(String position)
    {

        String alphabet = "abcdefgh";
       int column =  alphabet.indexOf(position.charAt(0));
       int row = Character.getNumericValue(position.charAt(1));

        if(row < 0 || row > 8 || column < 0 || column > 8)
        {
            return true;
        }
        return false;
    }

    private boolean CaptureCheck(String fromPosition,String toPosition) throws IllegalMoveException, IllegalPositionException {

        if(getPiece(toPosition) != null)
        {
            if(getPiece(fromPosition).color != getPiece(toPosition).color)
            {
                System.out.println("captured" );
                return true;
            }
        }

        return false;
    }

    public boolean placePiece(ChessPiece piece, String position) throws IllegalPositionException
    {

        if(init)
        {
            piece.setPosition(position);
            board[piece.row][piece.column] = piece;
            return true;
        }
        else
        {
             piece.setPosition(position);
             board[piece.row][piece.column] = piece;
             return true;
        }

    }

    public ChessPiece getPiece(String position) throws IllegalPositionException {
        position = offSetUtility(position);
        String alphabet = "abcdefgh";
        int column =  alphabet.indexOf(position.charAt(0));
        int row = Character.getNumericValue(position.charAt(1));
        
        if(row < 0 || row > 7 || column < 0 || column > 7)
        {
        	 throw new IllegalPositionException("illegal characters");
        }

        return board[row][column];
    }


    private String offSetUtility(String position) throws IllegalPositionException
    {
        String col  = Character.toString(position.charAt(0));
        int row = Character.getNumericValue(position.charAt(1));
        row = row - 1; // offset row so index matches 0 starting point

        String offset = col + row;

        if(boundsCheck(offset))
        {
            throw new IllegalPositionException("illegal characters");
        }

        return offset;
    }

    public void move(String fromPosition, String toPosition) throws IllegalMoveException, IllegalPositionException {
        int removeRow = 0;
        int removeCol = 0;
        boolean hasMoved = false;

        String offsetPositionFrom = offSetUtility(fromPosition);
        String offsetPositionTo = offSetUtility(toPosition);


        this.toPosition = toPosition;

          if(getPiece(fromPosition) == null)
          {
              throw new IllegalMoveException("No Piece Here ");
          }
          else
          {
                // get moves based on color
           //   System.out.println("Color of Piece " +getPiece(fromPosition).getColor());
              ArrayList<String> myMoves =  getPiece(fromPosition).legalMoves(); // have to make a copy for some reason accessing elements from return doesn't work?


              System.out.println("# Legal Moves " + myMoves.size());
              for(int i = 0; i < myMoves.size(); i++)
              {

                 if(offSetUtility(myMoves.get(i)).equals(offsetPositionTo))
                 {
                     System.out.println("found match " + offSetUtility(myMoves.get(i)) + " and " + offsetPositionTo);

                      removeRow = getPiece(fromPosition).row;  // Save removal position (never remove a element in a loop )
                      removeCol = getPiece(fromPosition).column; // Save removal position (never remove a element in a loop )

                     if(getPiece(fromPosition) instanceof Pawn)
                     {
                         String col  = Character.toString(fromPosition.charAt(0));
                         String col2  = Character.toString(toPosition.charAt(0));
                         System.out.println(" match? " + col + col2);
                         if(!col.equals(col2))
                         {
                             if(getPiece(toPosition) == null)
                             {
                                 throw new IllegalMoveException("illgal pawn move " + getPiece(fromPosition));
                             }
                             CaptureCheck(fromPosition,toPosition);
                         }
                         else{
                             if(getPiece(toPosition) != null)
                             {
                                 throw new IllegalMoveException("cant capture here " + getPiece(fromPosition));
                             }
                         }
                     }
                    else
                     {
                         CaptureCheck(fromPosition,toPosition);
                     }

                     placePiece(getPiece(fromPosition), offsetPositionTo);
                     hasMoved = true;
                 }
                 else
                 {
  
                	 System.out.println("Not a match " + myMoves.get(i) + " and " + toPosition);                 
                 }

              }

              if(hasMoved) {
                  board[removeRow][removeCol] = null;
              }
              else
              {
                  throw new IllegalMoveException("Collision with same color or illegal pawn move ");
              }
          }

    }

    public String toString(){
        String chess="";
        String upperLeft = "\u250C";
        String upperRight = "\u2510";
        String horizontalLine = "\u2500";
        String horizontal3 = horizontalLine + "\u3000" + horizontalLine;
        String verticalLine = "\u2502";
        String upperT = "\u252C";
        String bottomLeft = "\u2514";
        String bottomRight = "\u2518";
        String bottomT = "\u2534";
        String plus = "\u253C";
        String leftT = "\u251C";
        String rightT = "\u2524";

        String topLine = upperLeft;
        for (int i = 0; i<7; i++){
            topLine += horizontal3 + upperT;
        }
        topLine += horizontal3 + upperRight;

        String bottomLine = bottomLeft;
        for (int i = 0; i<7; i++){
            bottomLine += horizontal3 + bottomT;
        }
        bottomLine += horizontal3 + bottomRight;
        chess+=topLine + "\n";

        for (int row = 7; row >=0; row--){
            String midLine = "";
            for (int col = 0; col < 8; col++){
                if(board[row][col]==null) {
                    midLine += verticalLine + " \u3000 ";
                } else {midLine += verticalLine + " "+board[row][col]+" ";}
            }
            midLine += verticalLine;
            String midLine2 = leftT;
            for (int i = 0; i<7; i++){
                midLine2 += horizontal3 + plus;
            }
            midLine2 += horizontal3 + rightT;
            chess+=midLine+ "\n";
            if(row>=1)
                chess+=midLine2+ "\n";
        }

        chess+=bottomLine;
        return chess;
    }

    public void print() {
        System.out.println();
        for(int i=0; i < 8; i++)
        {
            for(int j=0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }


     public static void main(String[] args) throws IllegalPositionException, IllegalMoveException {
    	    ChessBoard board = new ChessBoard();
    	    board.initialize();
    	    System.out.println(board);
    	    board.move("c2", "c4");
    	    System.out.println(board);

     }





}
