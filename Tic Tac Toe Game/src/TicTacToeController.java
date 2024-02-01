/** Tic Tac Toe Controller Class
  *  The controller for the TicTacToe game
  *  Date Created:  15/01/2023
  *  Made By: Tejas Thind
  */

  import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
  
  public class TicTacToeController implements ActionListener
  {
    private TicTacToeModel model;     //The Model used to describe the game
    /** Default constructor
      * Links the component to the Model
      * @param aTic          The Model describing game behaviour
      * @param buttons     The component being interacted with
      */ 
    
    public TicTacToeController(TicTacToeModel aTic)
    {
      this.model = aTic;
    }
    
    public void actionPerformed(ActionEvent e)
    {
      if(e.getSource() == model.view.startGame) // If the start game button is pressed set play game variable to false
      {      
        this.model.playerMoves = 0; // Reset these stats to 0 once the Start Game button is pressed 
        this.model.compMoves = 0;
        this.model.playerWins = 0;
        this.model.compWins = 0;
        this.model.roundsTied = 0;
        this.model.setPlayGame(false);
        this.model.view.statsText.setText(""); //clear the statsText textArea when the start button is pressed
        this.model.view.statsFrame.dispose(); //close the statsFrame when the start button is pressed
      }
      
      if (e.getSource() == model.view.quitGame) // If the quit game button is pressed, close the system
      {
        System.exit(0);
      }
      
      if (e.getSource() == model.view.newGame) // If the new game button is pressed, set the newGame variable to true 
      {
        this.model.setNewGame(true);
      }
      
      if (e.getSource() == model.view.nextRound) // If the next round button is pressed, set the nextRound variable to true
      {
        this.model.setNextRound(true);
      }
      
      if(e.getSource() == model.view.viewStats) // If the view stats button is pressed, run the statsExport method in the model
      {
        this.model.statsExport();
        JOptionPane.showMessageDialog(null,"Stats Successfully exported to the \"game_stats\" file of today's date! ");
      }
      
      // For loops to iterate through the 2D array of buttons
      for(int row = 0; row<3; row++)
      {
        for(int col = 0; col<3; col++)
        {
          if(e.getSource() == model.view.buttons[row][col]) // When a button in the array at [row][col] is pressed
          {
            //If there is a move left in the game
            if (model.getRoundMoves() < 10)
            {
              model.playersMove(row, col); //send parameteres to playersMove() method to place the move in the model grid 
              model.view.setSymbolandDisable(row, col, model.getPlayerSymbol()); //show the move on the view
            }
            //If there is another move left, make the computer place the move instantly after the user places their move
            if (model.getRoundMoves() < 10)
            {
              model.computerPlay();
            }
          }
        } // end of inner for loop
      } // end of outer for loop
    } // end of method
  } // end of class