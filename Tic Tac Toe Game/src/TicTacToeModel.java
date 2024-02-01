/** Tic Tac Toe Model Class
  *  The model for the TicTacToe game
  *  Date Created:  15/01/2023
  *  Made By: Tejas Thind
  */

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
//import java.util.Date;

public class TicTacToeModel extends Object 
{
  //Variable Declarations
  public TicTacToeGUI view;  //The view for the game
  
  private String [][] grid = new String [3][3]; /*2D array of 3 rows and columns, 
   * representing the 3 rows and columns of a Tic Tac Toe board */
  private String gamerTurn = ""; // the current turn (either player or computer)
  private String playerSymbol = "X"; // the player's symbol (x)
  private String compSymbol = "O"; // the computer's symbol (O)
  private boolean playGame = true; // the variable that determines whether the start game button has been pressed
  private boolean newGame = false; // Determines if the new game button has been pressed 
  private boolean nextRound = false; // Determines if the next round button has been pressed 
  private boolean isItPlayerTurn = false; // Determines whether it's the players turn or not 
  
  private String roundWinner; // the winner of the round 
  private String finalWinner; // the final game winner
  public int playerWins = 0; // the number of rounds that the player has won
  public int compWins = 0; // the number of rounds that the computer has won
  public int roundsTied = 0; // number of rounds tied
  public int playerMoves = 0; // the total amount of moves the player has made in the game
  public int compMoves = 0; // the total amount of moves the computer has made in the game 
  private int roundMoves = 0; // the total amount of moves the player and computer have made in one round
  public int currentRound = 1; // the number of the current round
  
  
  public TicTacToeModel()
  {
    super();
  }
  
  
  /** Sets the view for the game
    * @param currentGUI        The View used to display the game
    */ 
  public void setGUI(TicTacToeGUI currentGUI)
  {
    this.view = currentGUI;
  }
  
  // Accessor Methods 
  public String getGamersTurn() //returns the String of who's turn it is
  {
    return gamerTurn;
  }
  
  public String getRoundWinner() //returns the String of the round winner
  {
    return roundWinner;
  }
  
  public String getPlayerSymbol() //returns symbol the player is (X)
  {
    return playerSymbol;
  }
  
  public String getCompSymbol() //returns symbol the computer is (0)
  {
    return compSymbol;
  }
  
  public String getFinalWinner() //returns the String of the final winner
  {
    return finalWinner;
  }
  
  public int getPlayerWins() //returns the value of the amount of wins the player currently has in the current game
  {
    return playerWins;
  }
  
  public int getCompWins() //returns the value of the amount of wins the computer currently has in the current game 
  {
    return compWins;
  }
  
  public int getCurrentRound() //returns the value of what current round it is
  {
    return currentRound;
  }
  
  public int getRoundMoves() //returns the value of the number of moves played in the round by both parties
  {
    return roundMoves;
  }
  
  public int getPlayerMoves() //returns the value of the number of player moves played in the round
  {
    return playerMoves;
  }
  
  public int getCompMoves() //returns the value of the number of computer moves played in the round
  {
    return compMoves;
  }
  
  public boolean getPlayGame() // Used to access the playGame variable from other classes.
  {
    return playGame;
  }
  
  public boolean getNewGame() // Used to access the newGame variable from other classes.
  {
    return newGame;
  } 
  
  public boolean getNextRound() // Used to access the nextRound variable from other classes.
  {
    return nextRound;
  } 

  public int getRoundsTied() // Used to access the roundsTied variable from other classes.
  {
    return roundsTied;
  } 
  
  public String [][] getGrid() // Used to access the model's grid from other classes.
  {
    return this.grid;
  }
  
  public boolean getIsItPlayerTurn() // Used to access the isItPlayerTurn variable from other classes.
  {
    return isItPlayerTurn;
  } 
  
  // Sets the play game variable to false (wouldn't work when I set to true)
  public void setPlayGame(boolean boo)
  {
    this.playGame = boo;
    this.firstTurn(); // determines who goes first
  }
  
  // Sets the gamers turn to either "Player Turn (X)" or "Computer Turn (O)" depending on which string was passed 
  public void setGamersTurn(String turn)
  {
    this.gamerTurn = turn;
  }
  
  public void setNewGame(boolean boo) // Sets the new game variable to true if the "New Game" button is pressed on the GUI
  {
    this.newGame = boo;
    this.clearArray(this.grid); //clear the model's array
    this.finalWinner(); // find out who won the most recently played game
    this.roundMoves = 0; //set round moves back to 0
    this.currentRound = 1; // set current round back to 1
    this.updateView();
  }
  
  public void setNextRound(boolean boo) // Sets the next round variable to true if the "Next Round" button is pressed on the GUI
  {
    this.nextRound = boo;
    this.clearArray(this.grid); //clear the model's array
    this.roundMoves = 0;
    this.firstTurn();
  }
  
  // Is used to determine if it is the players turn
  public void setIsItPlayerTurn(boolean boo) //
  {
    this.isItPlayerTurn = boo;
  }
  
  // Determines who goes first
  public void firstTurn()
  {
    int randomNum = (int)((Math.random() * 10) + 1); // Generate random number between 1 and 10
    if(randomNum > 5) // if the number is greater than 5, then the player goes first 
    {
      this.gamerTurn = "Player Turn (X)"; // Set gamerTurn to player 
      this.updateView();
    }
    else
    {
      this.gamerTurn = "Computer Turn (O)"; // Set gamerTurn to player 
      this.updateView();
      this.computerPlay(); // computer plays the first move
    }
  }
  
  // Adds the players move (X) into the model's 2D array grid and checks if that move resulted in a win, tie or loss
  public void playersMove(int row, int col)
  {
    grid[row][col] = this.playerSymbol;
    this.playerMoves = playerMoves + 1; // Increment the number of player moves by 1
    this.roundMoves = this.roundMoves + 1; //add one to the total amount of moves made in the round
    //If this move resulted in a win
    if (this.checkWin() == true)
    {
      this.roundWinner = "Player"; //set the winner as the player
      this.playerWins = this.playerWins + 1; //add one to the player's wins
      this.updateView(); //update the GUI      
      this.roundWinner = null; //Sets the roundWinner variable to null so that the title Label can be set back to whos turn it is
      //view.displayWinner(); //show the round winner card on the view
    }
    else if (this.checkTie() == true) //If this move resulted in a tie
    {
      this.roundWinner = "No one"; //set the winner as none
      this.updateView(); //update the GUI accordingly
      this.roundsTied = this.roundsTied +1; // increments the rounds tied variable by 1 everytime a round is tied
      this.roundWinner = null; //Sets the roundWinner variable to null so that the title Label can be set back to whos turn it is
      //view.displayWinner(); //show the round winner card on the view
    }
    else //the move did not result in a win or tie
    {
      this.gamerTurn = "Computer Turn (O)"; //set the turn to the computer
      this.updateView(); //update the view
    } //end of if-else statement
  } // end of method
  
  // Adds the computers move (O) into the model's 2D array grid and checks if that move resulted in a win, tie or loss
  public void placeCompMove(int row, int col) 
  {
    grid[row][col] = this.compSymbol;
    this.compMoves = compMoves + 1; // Increment the number of computer moves by 1
    this.roundMoves = this.roundMoves + 1; //add one to the total amount of moves made in the round
    if (this.checkWin() == true) //If this move resulted in a win
    {
      this.roundWinner = "Computer"; //set the winner as the player
      this.compWins = this.compWins + 1; //add one to the player's wins
      this.updateView(); //update the GUI
      this.roundWinner = null; //Sets the roundWinner variable to null so that the title Label can be set back to whos turn it is
      //view.displayWinner(); //show the round winner card on the view
    }
    else if (this.checkTie() == true) //If this move resulted in a tie
    {
      this.roundWinner = "No one"; //set the winner as none
      this.updateView(); //update the GUI accordingly
      this.roundsTied = this.roundsTied +1; // increments the rounds tied variable by 1 everytime a round is tied
      this.roundWinner = null; //Sets the roundWinner variable to null so that the title Label can be set back to whos turn it is
    }
    else //the move did not result in a win or tie
    {
      this.gamerTurn = "Player Turn (X)"; //set the turn to the player
      this.updateView(); //update the view
    }
  } // end of method
  
  // Calls block win method if it is the computers turn 
  public void computerPlay() 
  {
    if (this.getGamersTurn().equals("Computer Turn (O)"))
    {
      this.blockWin();
    }
  } 
  
  // Blocks user's possible wins
  public boolean blockWin()
  {
    //Blocking horizontal wins
    for (int x = 0; x < this.grid.length; x++)
    {
      //Checking if there are any player symbols in any rows
      if (grid[x][0] == this.getPlayerSymbol() || grid[x][1] == this.getPlayerSymbol() || grid[x][2] == this.getPlayerSymbol())
      {
        // Check if the first spot in any row is open while the 2 spots to the right are filled with the players Symbol
        if (grid[x][1] == grid[x][2] && grid[x][1] == this.getPlayerSymbol() && grid[x][0] == null)
        {
          this.placeCompMove(x, 0); //place the move in the model grid
          this.view.setSymbolandDisable(x, 0, this.getCompSymbol()); //show the move on the view
          return true; // Returns true to end the method 
        }
        // Check if the second spot in any row is open while the the spot to the right and left are filled with the players Symbol
        else if (grid[x][0] == grid[x][2] && grid[x][0] == this.getPlayerSymbol() && grid[x][1] == null)
        {
          this.placeCompMove(x, 1); //place the move in the model grid
          this.view.setSymbolandDisable(x, 1, this.getCompSymbol()); //show the move on the view
          return true; // Returns true to end the method 
        }
        // Check if the third spot in any row is open while the 2 spots to the left are filled with the players Symbol
        else if (grid[x][0] == grid[x][1] && grid[x][0] == this.getPlayerSymbol() && grid[x][2] == null)
        {
          this.placeCompMove(x, 2); //place the move in the model grid
          this.view.setSymbolandDisable(x, 2, this.getCompSymbol()); //show the move on the view
          return true; // Returns true to end the method 
        } 
      } 
    } // end of for loop for horizontal move placement  
    
    // Block Vertical Wins
    for (int x = 0; x < this.grid.length; x++)
    {
      //Checking if there are any player symbols in each column
      if (grid[0][x] == this.getPlayerSymbol() || grid[1][x] == this.getPlayerSymbol() || grid[2][x] == this.getPlayerSymbol())
      {
        // Check if any spot in the top row is open while the 2 spots below are filled with the players Symbol
        if (grid[1][x] == grid[2][x] && grid[1][x] == this.getPlayerSymbol() && grid[0][x] == null)
        {
          this.placeCompMove(0, x); //place the move in the model's grid
          this.view.setSymbolandDisable(0, x, this.getCompSymbol()); //show the move on the view
          return true; //return true and end the method
        }
        // Check if any spot in the middle row is open while the spot above and the spot below are filled with the players Symbol
        else if (grid[0][x] == grid[2][x] && grid[0][x] == this.getPlayerSymbol() && grid[1][x] == null)
        {
          this.placeCompMove(1, x); //place the move in the model's grid
          this.view.setSymbolandDisable(1, x, this.getCompSymbol()); //show the move on the view
          return true; //return true and end the method
        }
        // Check if any spot in the bottom row is open while the 2 spots above are filled with the players Symbol
        else if (grid[0][x] == grid[1][x] && grid[0][x] == this.getPlayerSymbol() && grid[2][x] == null)
        { 
          this.placeCompMove(2, x); //place the move in the model's grid
          this.view.setSymbolandDisable(2, x, this.getCompSymbol()); //show the move on the view
          return true; //return true and end the method
        } 
      } 
    } // end of for loop for vertical move placement 
    
    //Blocking diagonal win scenerio #1 (Top Left - Middle - Bottom Right)
    if (grid[0][0] == this.getPlayerSymbol() || grid[1][1] == this.getPlayerSymbol() || grid[2][2] == this.getPlayerSymbol())
    {
      // If the bottom right can stop the users diagonal win
      if (grid[0][0] == grid[1][1] && grid[0][0] == this.getPlayerSymbol() && grid[2][2] == null)
      {
        this.placeCompMove(2, 2); //place the move in the model's grid
        this.view.setSymbolandDisable(2, 2, this.getCompSymbol()); //show the move on the view
        return true; //return true and end the method
      }
      // If the top left can stop the users diagonal win
      else if (grid[1][1] == grid[2][2] && grid[1][1] == this.getPlayerSymbol() && grid[0][0] == null)
      {
        this.placeCompMove(0, 0); //place the move in the model's grid
        this.view.setSymbolandDisable(0, 0, this.getCompSymbol()); //show the move on the view
        return true; //return true and end the method
      }
      // If the middle can stop the users diagonal win
      else if (grid[0][0] == grid[2][2] && grid[0][0] == this.getPlayerSymbol() && grid[1][1] == null)
      {
        this.placeCompMove(1, 1); //place the move in the model's grid
        this.view.setSymbolandDisable(1, 1, this.getCompSymbol()); //show the move on the view
        return true;
      } 
    } // end of if statement for diagonal move #1
    
    //Blocking diagonal win scenario #2 (Top Right - Middle - Bottom Left)
    if (grid[2][0] == this.getPlayerSymbol() || grid[1][1] == this.getPlayerSymbol() || grid[0][2] == this.getPlayerSymbol())
    {
      // If the top right is open to block the user's win
      if (grid[2][0] == grid[1][1] && grid[2][0] == this.getPlayerSymbol() && grid[0][2] == null)
      {
        this.placeCompMove(0, 2); //place the move in the model's grid
        this.view.setSymbolandDisable(0, 2, this.getCompSymbol()); //show the move on the view
        return true; //return true and end the method
      }
      // If the bottom left is open to block the user's win
      else if (grid[1][1] == grid[0][2] && grid[1][1] == this.getPlayerSymbol() && grid[2][0] == null)
      {
        this.placeCompMove(2, 0); //place the move in the model's grid
        this.view.setSymbolandDisable(2, 0, this.getCompSymbol()); //show the move on the view
        return true; //return true and end the method
      }
      // If the middle is open to block the user's win
      else if (grid[2][0] == grid[0][2] && grid[2][0] == this.getPlayerSymbol() && grid[1][1] == null)
      {
        this.placeCompMove(1, 1); //place the move in the model's grid
        this.view.setSymbolandDisable(1, 1, this.getCompSymbol()); //show the move on the view
        return true;
      } 
    } // end of if statement for diagonal move #2
    
    // If no possible win for user, place random move
    while (true)
    {
      //Declaring variables
      int randomX = (int)(Math.random()*3); //random X value
      int randomY = (int)(Math.random()*3); //random Y value
      
      //Check if the the grid with the randome X and Y values is empty
      if (grid[randomX][randomY] == null)
      {
        this.placeCompMove(randomX, randomY); //place the move at the random coordinates 
        this.view.setSymbolandDisable(randomX, randomY, this.getCompSymbol()); //show the move on the view
        return true; //return true and end the method
      }
    }// end of while loop for random move placement
  }// end of method
  
  //Checks whether a win has been achieved yet or not
  public boolean checkWin() 
  {
    // Check rows
    for (int i = 0; i < grid.length; i++) 
    {
      if (grid[i][0] == "X" && grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) 
      {
        this.view.highlightWin(i, 0, i, 1, i, 2); // Highlight the move on the view
        return true; // return trie to end the method 
      }
      else if (grid[i][0] == "O" && grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) 
      {
        this.view.highlightWin(i, 0, i, 1, i, 2); // Highlight the move on the view
        return true; // return trie to end the method 
      }
    }
    
    // Check columns
    for (int i = 0; i < grid.length; i++) 
    {
      if (grid[0][i] == "X" && grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) 
      {
        this.view.highlightWin(0, i, 1, i, 2, i); // Highlight the move on the view
        return true;
      }
      else if (grid[0][i] == "O" && grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) 
      {
        this.view.highlightWin(0, i, 1, i, 2, i); // Highlight the move on the view
        return true;
      }
    }
    // Check diagonals
    if (grid[1][1] == "X" && (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2])) 
    {
      this.view.highlightWin(0, 0, 1, 1, 2, 2); // Highlight the move on the view
      return true;
    }
    else if (grid[1][1] == "O" && (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2])) 
    {
      this.view.highlightWin(0, 0, 1, 1, 2, 2); // Highlight the move on the view
      return true;
    }
    else if(grid[1][1] == "X" && (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]))
    {
      this.view.highlightWin(0, 2, 1, 1, 2, 0); // Highlight the move on the view
      return true;
    }
    else if(grid[1][1] == "O" && (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]))
    {
      this.view.highlightWin(0, 2, 1, 1, 2, 0); // Highlight the move on the view
      return true;
    }
    return false;
  }// end of method
  
  //Used to check if there is a tie
  public boolean checkTie()
  {
    //If the grid is full and no win has been achieved
    if (this.isArrayFull(this.grid) == true && this.checkWin() == false)
    {
      return true;
    }
    else 
    {
      return false;
    }
  }// end of method
  
  //Returns true if the array is full
  public boolean isArrayFull(String [][] array)
  {
    //Declaring variables
    int full = 0; // counter for checking how many spots are filled
    
    //Looping through the array
    for (int row = 0; row < array.length; row++)
    {
      for (int col = 0; col < array[row].length; col++)
      {
        //If the current array spot is not empty
        if (array[row][col] != null)
        {
          full = full + 1; // increment the variable to show that the spot is filled
        }
      }
    } // end of outer for loop 
    
    //If the number of filled elements is equal to 9 (meaning grid is full)
    if (full == 9)
    {
      return true;
    }
    else
    {
      return false;
    }
  }// end of method
  
// Sets all the elements in an array to null
  public void clearArray(String [][] array)
  {
    //Looping for all the elements in the array
    for (int row = 0; row < array.length; row++)
    {
      for (int col = 0; col < array[row].length; col++)
      {
        array[row][col] = null; //setting all the values to null
      }
    }
  } //end of method
  
  // Determines who the final winner of the game is 
  public void finalWinner()
  {
    if(playerWins > compWins)
    {
      this.finalWinner = "Player";
    }
    else if (compWins > playerWins)
    {
      this.finalWinner = "Computer";
    }
    else
    {
      this.finalWinner = "Tie";
    }
  } // end of method 
  
  public void statsExport()
  {
    try
    {
      // Creates a new SimpleDateFormat object to format the date and time
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
      // Creates a new Date object to get the current date and time
      Date now = new Date();
      // Formats the date and time using the SimpleDateFormat object
      String timestamp = sdf.format(now);
      
      PrintWriter writer = new PrintWriter("game_stats" + timestamp + ".txt");
      writer.println("Game Over.");
      writer.println("");
      if (this.getFinalWinner().equals("Player")) //If the player is the final winner 
      {
        writer.println("You won the game!");
        writer.println("");
      }
      else if (this.getFinalWinner().equals("Computer")) //If the computer is the final winner
      {
        writer.println("The computer won the game!");
        writer.println("");
      }
      else //If it was a tie
      {
        writer.println("Both players tied. "); //out out that it was a tie
        writer.println("");
      } 
      // Output all the recent game stats line by line
      writer.print("# of Player Moves: "+playerMoves+"\n# of Computer Moves: "+compMoves+"\nPlayer Wins: "+playerWins
                     +"\nComputer Wins: "+compWins+"\nRounds Tied: "+roundsTied);
      writer.close();
    }
    catch (IOException e) 
    {
      System.err.println("An error occurred while trying to write to the file.");
      e.printStackTrace();
    }
  }
  
  /**  Updates the view in the GUI*/
  public void updateView()
  {
    view.update();
  }
}// end of class