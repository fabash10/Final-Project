

public class Cell {
    private int x;
    private int y;
    private int numProxBombs;
    private boolean bomb;
    private boolean visible;
    private Cell[] adjacentCells;
    private boolean flag;
    private boolean hasExploded;
    public Cell(int initX, int initY, boolean initIsBomb, boolean initIsVisible ){
        x = initX;
        y= initY;
        flag = false;
        hasExploded=false;
    }
    public void setExploded(boolean exploded){
        hasExploded=exploded;
    }
    public int getX() {
        return x;
    }
    public void setFlag(boolean setFlag) {
        flag = setFlag;
    }
    public Cell[] getAdjacentCells() {
        return adjacentCells;
    }
    public void setAdjacentCells(Cell[] setAdjacentCells) {
        adjacentCells = setAdjacentCells;
    }
    public int getY() {
        return y;
    }
    public int getnumProxBombs() {
        return numProxBombs;
    }
    public boolean isBomb() {
        return bomb;
    }   
    public void setNumProxBombs(int setNumProxBombs) {
        numProxBombs = setNumProxBombs;
    }
    public boolean isVisible(){
        return visible;
    }
    public void setVisible(boolean setVisible){
        visible = setVisible;
    }
    public String printCell(boolean debug) {
        
        String result;
        if (hasExploded)
            result="B";
        else if(debug){
            if(bomb){
                result="X";
            }
            else if (numProxBombs == 0){
                result= " ";
            }
            else{
                result= "" + numProxBombs;
            }
        
        }
        else{   

                if(visible){
                    if(bomb){
                        result="X";
                    }
                    else if (numProxBombs == 0){
                        result= " ";
                    }
                    else{
                        result= "" + numProxBombs;
                    } 
                }  
                else{
                    if(flag){
                        result = "F";
                    }   
                    else{
                        result= "#";
                    }
                }
        }    
        return result;
        
    }
    public void setIsBomb(boolean setIsBomb){
        bomb = setIsBomb;
    }  

} 