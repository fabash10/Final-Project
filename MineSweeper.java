import java.util.Scanner;

public class MineSweeper extends Game{
    private Grid board;
    private boolean gameEnd;
    MineSweeper(int initRows, int initColumn, int initBombs){
        super("MineSweeper");
        board = new Grid(initRows, initColumn, initBombs);
        board.populateBombs();
        board.calcProxBombsCells();
    }
     
    // public void playerGameInputs(){
    //     Scanner scannerRowAndColumn = new Scanner(System.in);
    //     System.out.println("Enter row column(e.g. 1 2): ");
    //     String playerSelectedRowAndColumn = scannerRowAndColumn.nextLine();
    //     System.out.println("Enter for action, r for reveal, f for flag");
    // }
    
    public void playGame(){
        int[] inputRowCol;
        boolean debugMode=false;
        String playerActionInput;
        setGameEnded(false);
        while(!isGameEnded()){
            if (debugMode) {
                System.out.println("$$$$$$$$$$$$$$DEBUG BOARD$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                board.print(true);
                System.out.println("$$$$$$$$$$$$$$DEBUG BOARD$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            }
            board.print(false);

            inputRowCol = playerGameCordsInput();
            playerActionInput = playerAction();
            playerFlag(playerActionInput, inputRowCol);
            playerReveal(playerActionInput, inputRowCol);
            if (board.allCellsVisible() && !gameEnd){
                setGameEnded(true);
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("********CONGRATULATIONS!!! YOU WON**********");
                System.out.println();
                System.out.println();
                System.out.println();
                board.print(true);
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("********CONGRATULATIONS!!! YOU WON**********");
                System.out.println();
                System.out.println();
                System.out.println();

            }

            
            
            
            

        }
    }
    public void playerReveal(String playerAction, int[] inputRowCol){
         Cell currentCell = board.getCell(inputRowCol[0], inputRowCol[1]);
        if(playerAction.equals("r") || playerAction.equals("R")){
            currentCell.setVisible(true);
            if(currentCell.isBomb()){
                setGameEnded(true);
                currentCell.setExploded(true);
                currentCell.setVisible(true);
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("********BOMB EXPLODED**********");
                System.out.println();
                System.out.println();
                System.out.println();
                board.print(true);
                System.out.println();
                System.out.println();
                System.out.println();
                 System.out.println("********BOMB EXPLODED**********");
                System.out.println();
                System.out.println();
                System.out.println();

            }
            else if(currentCell.getnumProxBombs() == 0){
                board.clearAdjacentCells(currentCell);
            }

        }
    }
    


    public void playerFlag(String playerAction, int[] inputRowCol){
        if(playerAction.equals("f") || playerAction.equals("F")){
            board.getCell(inputRowCol[0], inputRowCol[1]).setFlag(true);
        }
        else if (playerAction.equals("u") || playerAction.equals("U")){
            board.getCell(inputRowCol[0],inputRowCol[1]).setFlag(false);
        }
    }
    public String playerAction(){
        String result = "";
        Scanner scanner = Session.getScanner();
        System.out.println("Enter action, r for reveal, f for flag, u for unflag: ");
        String playerContinueChoice = scanner.nextLine();
      
        
        while(!playerContinueChoice.equals("r") && !playerContinueChoice.equals("R") && !playerContinueChoice.equals("f") && !playerContinueChoice.equals("F") && !playerContinueChoice.equals("U") && !playerContinueChoice.equals("u"))
        {
            System.out.println("You have entered " + playerContinueChoice + " which is not a vaild choice.");
            System.out.println("Please enter new choice(f/r)");
            playerContinueChoice = scanner.nextLine();

        }
            // Insert method for starting game here
        if(playerContinueChoice.equals("r") || playerContinueChoice.equals("R")){
           result="r";
        }
        else if(playerContinueChoice.equals("F") || playerContinueChoice.equals("f")){
            result = "F";
            
        }else if (playerContinueChoice.equals("u") || playerContinueChoice.equals("U"))
            result="U";
        return result;
    
    }
    public  boolean isNumeric(String s){
        boolean result = true;
        for(int i=0;i< s.length();i++){
          
            if (!Character.isDigit(s.charAt(i))){
                result=false;
                break;
            }

        }
        return result;
    }
    public int[] playerGameCordsInput (){
        int[] RowAndColumn =  new int[2];

        int playerSelectedRow=0;
        int playerSelectedColumn=0;

        Scanner scannerRowAndColumn= Session.getScanner();
        System.out.println("Enter row: ");
        String tempRow;
        tempRow = scannerRowAndColumn.nextLine();
        if (isNumeric(tempRow))
           playerSelectedRow = Integer.parseInt(tempRow);
        else
            playerSelectedRow=-1000;
        RowAndColumn[0] = playerSelectedRow;

        System.out.println("Enter Column: ");
        String tempCol = scannerRowAndColumn.nextLine();
        if (isNumeric(tempCol))
            playerSelectedColumn= Integer.parseInt(tempCol);
        else
            playerSelectedColumn=-1000;

        RowAndColumn[1] = playerSelectedColumn;
        while(!board.isValidColumns(RowAndColumn[0])|| !board.isValidColumns(RowAndColumn[1])){
            System.out.println("Your cordinates are wrong please re-enter");
            
            System.out.println("Enter row: ");
            tempRow = scannerRowAndColumn.nextLine();
            if (isNumeric(tempRow))
                playerSelectedRow = Integer.parseInt(tempRow);
            else
                 playerSelectedRow=-1000;
            RowAndColumn[0] = playerSelectedRow;
            
            System.out.println("Enter Column: ");
             tempCol = scannerRowAndColumn.nextLine();
             if (isNumeric(tempCol))
                playerSelectedColumn= Integer.parseInt(tempCol);
            else
                playerSelectedColumn=-1000;

            RowAndColumn[1] = playerSelectedColumn;
        }
    
        return RowAndColumn;
        
            
    }

    
    public void setGameEnded(boolean setGameEnded){
        gameEnd = setGameEnded;
    }
    public boolean isGameEnded(){
        return gameEnd;
    }
    
    public void startGame(){
        
    }
    public void endGame(){

    }

    

}
