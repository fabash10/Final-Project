import java.util.Scanner;

public class Session {
    static Scanner sessionScanner;
    static public Scanner getScanner(){
        return sessionScanner;
    }
    public static void initializeScanner(){
        sessionScanner = new Scanner(System.in);

    }
    public static void closeScanner(){
        sessionScanner.close();
    }
    public static void main(String[] args) {
        
        Session currentSession = new Session();
        Session.initializeScanner();
        while (currentSession.ShouldIStartNewGame()){
            MineSweeper mineSweeper = new MineSweeper(9, 9, 10);
            mineSweeper.playGame();

        }
         System.out.println();
         System.out.println();
         System.out.println();
         System.out.println("Good Bye!!");
         System.out.println();
         System.out.println();
         System.out.println();



        Session.closeScanner();
        
    }
    
    public boolean ShouldIStartNewGame(){
        
        boolean result = false;
        Scanner scanner = Session.getScanner();
        System.out.println("Would you like to play(y/n)");
        String playerContinueChoice = scanner.nextLine();
      
        
        while(!playerContinueChoice.equals("y") && !playerContinueChoice.equals("Y") && !playerContinueChoice.equals("n") && !playerContinueChoice.equals("N"))
        {
            System.out.println("You have entered " + playerContinueChoice + " which is not a vaild choice.");
            System.out.println("Please enter new choice(y/n)");
            playerContinueChoice = scanner.nextLine();

        }
            // Insert method for starting game here
        if(playerContinueChoice.equals("Y") || playerContinueChoice.equals("y")){
           result=true;
        }
        else if(playerContinueChoice.equals("n") || playerContinueChoice.equals("N")){
            result = false;
        }
        return result;
    
        
    }
    public void runningSession(){

    }
}
