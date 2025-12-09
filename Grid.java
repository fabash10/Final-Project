import java.util.Random;

public class Grid{
    private int numRows;
    private int numColumns;
    private int numBombs;
    private Cell[][] grid;
    
    public Grid(int initRows, int initColumn, int initBombs){
        //Constructor
        numRows = initRows;
        numColumns = initColumn;
        numBombs = initBombs;
        intializeGrid();
        
        
    }
    public boolean allCellsVisible(){
        boolean results=true;
        for (int i=0;i < numColumns; i++){
            for(int j=0;j<numRows;j++){
                if (!grid[i][j].isVisible() && !grid[i][j].isBomb()){
                    results=false;
                    break;
                }
                if(results==false)
                    break;
            }
        }
        return results;
    }
    public int getNumRows(){
        return numRows;
    }
    public int getNumColumns(){
        return numColumns;
    }
    public boolean isValidRows(int x){
        if(x<0 || x>=numRows){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean isValidColumns(int y){
        if(y<0 || y>=numColumns){
            return false;
        }
        else{

            return true;
        }
    }
    public boolean isValid(int x ,int y){
        if(x == -1 ||  y == -1 || x >= numRows || y >= numColumns ){
            return false;
        }
        else{
            return true;
        }

    }
    public Cell[] getAdjacentCells(int x, int y){
        Cell[] adjacentCells = new Cell[8];
        
        int[] adjacentXs = {x, x-1, x-1, x-1, x, x+1, x+1, x+1};
        int[] adjacentYs = {y-1, y-1, y, y+1, y+1, y+1, y, y-1};
        int actualCells = 0;
        for(int i = 0; i < adjacentCells.length; i++ ){
            if(isValid(adjacentXs[i], adjacentYs[i])){
                int xx = adjacentXs[i];
                int yy = adjacentYs[i];
                Cell cell = grid[adjacentXs[i]][adjacentYs[i]];
                if (cell==null){
                    System.out.println ("NLLLLLL");
                }

                adjacentCells[actualCells]=cell;
                actualCells++;
            }
        }
        return adjacentCells;
    }

    public void intializeGrid(){
        grid = new Cell[numRows][numColumns];
        for(int i = 0; i < numRows; i++){
            

            for(int j = 0; j < numColumns; j++ ){
                grid[i][j] = new Cell(i, j, false, false);
            }
        }
        for(int rowCounter = 0; rowCounter < numRows; rowCounter++){
            

            for(int columCounter = 0; columCounter < numColumns; columCounter++ ){
                grid[rowCounter][columCounter].setAdjacentCells(getAdjacentCells(rowCounter,columCounter ));
            }
        }

    }
private  void printHeader(){
    String results = new String();
    String border = new String();
    results = "    ";
    border = "  +-";

    for (int i=0;i<numColumns;i++){
        results = results + i +" ";
        border = border + "--";
    }
    border = border + "+";
    System.out.println(results);
    System.out.println(border);
   // System.out.println("    0 1 2 3 4 5 6 7 8 ");
    
   // System.out.println("  +-------------------+ ");
    }

private void printFooter(){
    //
    String border = new String();
    border = "  +-";

    for (int i=0;i<numColumns;i++){
        border = border + "--";
    }
    border = border + "+";

    System.out.println(border);
    //System.out.println("  +-------------------+ ");  

    }
public void print(boolean debugMode){
    printHeader();
     for(int i = 0; i < numRows; i++){
        System.out.print(i + " |");
            for(int j = 0; j < numColumns; j++ ){
                System.out.print(" " + grid[i][j].printCell(debugMode));
            }
            System.out.println(" |");
        }
    printFooter();

    }
public void clearAdjacentCells(Cell currentCell){
    Cell[] adjacentCells = getAdjacentCells(currentCell.getX(), currentCell.getY());
    for(int i = 0; i < adjacentCells.length && adjacentCells[i] != null; ++i){
        Cell adjacentCell = adjacentCells[i];
        if(!adjacentCell.isVisible()){
            adjacentCell.setVisible(true);
            if(adjacentCells[i].getnumProxBombs() == 0 ){
                clearAdjacentCells(adjacentCell);
        }}
    }
}
    public int genRanXVal(){
        Random randomGenertor = new Random();
        return randomGenertor.nextInt(numRows);
    }
    public int genRanYVal(){
        Random randomGenertor = new Random();
        return randomGenertor.nextInt(numColumns);
    }
    public void placeNumProxBombs(){
        
    }
    public void populateBombs(){
        int numBombsLeft = numBombs;

        while(numBombsLeft > 0){
            int ranX = genRanXVal();
            int ranY = genRanYVal();
            Cell currentCell = grid[ranX][ranY];
            if(!currentCell.isBomb()){
                currentCell.setIsBomb(true);
                --numBombsLeft;
            }
        }    
    }

    public Cell getCell(int row, int column){
        return grid[row][column];
    }

    public void calcProxBombsCells(){
        for(int i = 0; i < numRows; i++){

            for(int j = 0; j < numColumns; j++ ){
                if(!grid[i][j].isBomb()){
                    int localNumProxBombs = 0;
                    Cell[] adjCells = grid[i][j].getAdjacentCells();
                    if (adjCells==null)
                        System.out.println("NUILL CALC PROX BOMB");
                    for(int indexAdj = 0; (indexAdj < adjCells.length) && (adjCells[indexAdj]!=null); indexAdj++){
                        if(adjCells[indexAdj].isBomb()){
                            ++localNumProxBombs;
                        }
                    }
                    grid[i][j].setNumProxBombs(localNumProxBombs);
                }
            }
        



        }
    }
    
}

