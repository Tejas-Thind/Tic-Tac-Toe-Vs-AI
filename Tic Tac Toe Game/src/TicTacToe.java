/** Tic Tac Toe Main Class
  *  The main for the TicTacToe game
  *  Date Created:  15/01/2023
  *  Made By: Tejas Thind
  */

  import javax.swing.*;
  
  public class TicTacToe
  {
    public static void main (String [] args)
    {
      TicTacToeModel model = new TicTacToeModel();  //The game model    
      TicTacToeGUI view = new TicTacToeGUI(model);  //The game view
      
      //Initialize the Frame
      JFrame f = new JFrame("Tejas' Tic-Tac-Toe Game!");
      f.setSize(750,650); 
      f.setLocationRelativeTo(null);
      f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      f.setContentPane(view);
      f.setVisible(true);
    } // end of main method 
  } // end of class