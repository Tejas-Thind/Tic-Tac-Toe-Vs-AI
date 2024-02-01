/** Tic Tac Toe View Class
  *  The GUI for the TicTacToe game
  *  Date Created:  15/01/2023
  *  Made By: Tejas Thind
  */

  import javax.swing.*;
  import java.awt.*;
  
  public class TicTacToeGUI extends JPanel
  {
    //Instance Variables
    private TicTacToeModel model; //The game Model
    
    // Frame, panel and text area for displaying end of the game stats
    public JFrame statsFrame = new JFrame("Post-Game Stats");
    private JPanel statsPanel = new JPanel();
    public JTextArea statsText = new JTextArea();
    
    // Starting Panel (Displays starter screen)
    private JPanel startPanel = new JPanel();
    private JPanel startButtonsPanel = new JPanel();
    public JButton startGame = new JButton("Start Game");
    public JButton quitGame = new JButton("Quit Game");
    public JButton viewStats = new JButton("Export Stats");
    private JPanel roundsPanel = new JPanel ();
    private JLabel userRounds = new JLabel(); //User prompt for rounds
    private JLabel roundNum = new JLabel(); //Rounds title
    private JLabel welcomeLabel = new JLabel();
    
    // Player turn panel and Button Panel
    private JPanel titlePanel = new JPanel();
    private JLabel titleLabel = new JLabel("");
    public JPanel buttonPanel = new JPanel(new GridLayout(3,3));
    
    // Options panel 
    private JPanel optionsPanel = new JPanel();
    private JLabel compThinking = new JLabel();
    public JButton newGame = new JButton("New Game");
    public JButton nextRound = new JButton("Next Round");
    
    // 2D array of buttons 
    public JButton [][] buttons = new JButton[3][3];
    
    
    /** Default constructor for the GUI.  Assigns Model and View, identifies controllers
      * and draws the layout
      * @param newGame        The Model for the game
      */ 
    public TicTacToeGUI(TicTacToeModel newGame)
    {
      super();
      this.model = newGame;
      this.model.setGUI(this);
      this.layoutView();
      this.registerControllers();
      this.update();
    }
    
    // The entire GUI of the game created here
    public void layoutView()
    { 
      // For loops to add JButtons to a 3x3 grid 
      for (int row = 0; row < 3; row++) 
      {
        for (int col = 0; col < 3; col++) 
        {
          buttons[row][col] = new JButton();
          buttons[row][col].setText("");
          buttons[row][col].setFocusable(false);
          buttonPanel.add(buttons[row][col]);
          buttons[row][col].setBackground(Color.BLACK);
        }
      }
      // Set preffered size for the buttons panel
      buttonPanel.setPreferredSize(new Dimension(565, 0));
      
      // Setting up welcome label
      welcomeLabel.setText("<html>Welcome To Tejas' <br/> Tic-Tac-Toe Game</html>"); // Creates a new line after Tejas'
      welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER); // Horzontally align to the center
      welcomeLabel.setVerticalAlignment(SwingConstants.CENTER); // Vertically align to the center
      welcomeLabel.setBackground(new Color(0,0,0)); // Set backround colour
      welcomeLabel.setForeground(new Color(255,255,255)); // Set foreground (text) colour
      welcomeLabel.setFont(new Font("Serif", Font.BOLD,75)); // Set font, font style(bold etc) and size
      welcomeLabel.setOpaque(true);
      
      // Setting up title label
      titleLabel.setBackground(new Color(0,0,0));
      titleLabel.setForeground(new Color(255,255,255));
      titleLabel.setFont(new Font("Ink Free", Font.PLAIN,75));
      titleLabel.setOpaque(true);
      
      // Setting up round numbs label
      roundNum.setBackground(new Color(0,0,0));
      roundNum.setForeground(new Color(255,255,255));
      roundNum.setFont(new Font("Serif", Font.PLAIN,45));
      roundNum.setOpaque(true);
      
      // Setting up title panel 
      titlePanel.setLayout(new BorderLayout());
      titlePanel.setBounds(0,0,800,100);
      titlePanel.add(titleLabel, BorderLayout.CENTER);
      
      // Setting up the rounds panel in order to add to the start panel
      roundsPanel.setLayout(new BorderLayout());
      roundsPanel.setBounds(0,0,800,100);
      userRounds.setBackground(new Color(0,0,0));
      userRounds.setForeground(new Color(255,255,255));
      userRounds.setFont(new Font("MV Boli",Font.BOLD,20));
      userRounds.setOpaque(true);
      userRounds.setHorizontalAlignment(SwingConstants.CENTER);
      userRounds.setVerticalAlignment(SwingConstants.CENTER);
      roundsPanel.add(userRounds, BorderLayout.CENTER);
      
      // Setting up start panel
      startPanel.setLayout(new BorderLayout());
      startButtonsPanel.setLayout(new BorderLayout());
      startButtonsPanel.add(startGame, BorderLayout.CENTER);
      startButtonsPanel.add(quitGame, BorderLayout.WEST);
      startButtonsPanel.add(viewStats, BorderLayout.EAST);
      startPanel.setBounds(0,0,800,100);
      startGame.setFont(new Font("MV Boli",Font.BOLD,30));
      quitGame.setFont(new Font("MV Boli",Font.BOLD,25));
      viewStats.setFont(new Font("MV Boli",Font.BOLD,25));
      startPanel.add(welcomeLabel, BorderLayout.NORTH);
      startPanel.add(roundsPanel, BorderLayout.CENTER);
      startPanel.add(startButtonsPanel, BorderLayout.SOUTH);
      
      // Setting up the options panel  
      optionsPanel.setLayout(new BorderLayout());
      compThinking.setText("<html>Computer is <br/> Planning...</html>");
      compThinking.setBackground(new Color(0,0,0));
      compThinking.setForeground(new Color(255,255,255));
      compThinking.setFont(new Font("Ink Free", Font.BOLD,26));
      compThinking.setHorizontalAlignment(SwingConstants.CENTER);
      compThinking.setVerticalAlignment(SwingConstants.CENTER);
      compThinking.setOpaque(true);
      nextRound.setFont(new Font("MV Boli", Font.BOLD,22));
      newGame.setFont(new Font("MV Boli", Font.BOLD,25));
      optionsPanel.add(newGame, BorderLayout.NORTH);
      optionsPanel.add(nextRound, BorderLayout.SOUTH);
      optionsPanel.add(compThinking, BorderLayout.CENTER);
      
      // Adding everything to the main layout
      this.setLayout(new BorderLayout());
      this.add(startPanel, BorderLayout.CENTER);
      this.add(buttonPanel, BorderLayout.WEST);
      this.add(titleLabel, BorderLayout.NORTH);
      this.add(optionsPanel, BorderLayout.EAST);
      this.add(roundNum, BorderLayout.SOUTH);
      titleLabel.setVisible(false);
      buttonPanel.setVisible(false);
      optionsPanel.setVisible(false);
      roundNum.setVisible(false);
      
      // Setting up a different frame that shows the stats at the end of each game 
      statsFrame.setSize(420,340); 
      statsFrame.setLocationRelativeTo(null);
      statsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      statsFrame.setVisible(false);
    }
    
    // Adds action listeners to all the buttons being pressed
    public void registerControllers()
    {
      TicTacToeController controller = new TicTacToeController(model);
      this.startGame.addActionListener(controller);
      this.quitGame.addActionListener(controller);
      this.newGame.addActionListener(controller);
      this.nextRound.addActionListener(controller);
      this.viewStats.addActionListener(controller);
      for (int row = 0; row < 3; row++) 
      {
        for (int col = 0; col < 3; col++) 
        {
          this.buttons[row][col].addActionListener(controller); // Adds action listeners to all 9 of the JButtons in the grid
        }
      }
    }
    public void update()
    {
      // If play game is false (in this case means user wants to start the game), then disable start panel and show other game panels
      if(this.model.getPlayGame() == false)
      {
        this.roundNum.setText("Round #" + this.model.getCurrentRound()); // Show what round # it is
        this.titleLabel.setText(model.getGamersTurn()); // Gets the gamers turn from the model, and sets it to the title lable
        startPanel.setVisible(false); // Set start panel non-visible and all others to visible 
        titleLabel.setVisible(true);
        buttonPanel.setVisible(true);
        optionsPanel.setVisible(true);
        roundNum.setVisible(true);
      }
      
      //If new game = true, reset all the buttons, set all game panels to not visible and set start panel to visible
      if(this.model.getNewGame() == true)
      {
        this.resetButtons();
        this.showStats();
        this.model.setNewGame(false); //set new game to false (so method can be repepeated),
        this.model.setPlayGame(true);
        this.startPanel.setVisible(true); // Set start panel to visible and all others to non-visible
        this.titleLabel.setVisible(false);
        this.buttonPanel.setVisible(false);
        this.optionsPanel.setVisible(false);
        this.roundNum.setVisible(false);
      }
      
      // If the next round variable = true 
      if(this.model.getNextRound() == true)
      {
        this.model.setNextRound(false); // set it back to false to allow the method to be ran again later
        this.model.currentRound++; // increment the current round variable by 1
        this.resetButtons(); // reset all the buttons
        this.roundNum.setText("Round #" + this.model.getCurrentRound()); // Update round number
      }
      
      // If the round winner variable is not null, set the title label to whoever won that round 
      if(model.getRoundWinner() != null)
      {
        this.titleLabel.setText(model.getRoundWinner() + " Wins This Round");
        this.titleLabel.setFont(new Font("Ink Free", Font.BOLD,45));
        for (int row = 0; row < 3; row++) 
        {
          for (int col = 0; col < 3; col++) 
          {
            buttons[row][col].setEnabled(false); // Disable all unclicked buttons 
          }
        }
      }
    }
    
    //Used to set the text of the JButton to either X or O and then disabling it
    public void setSymbolandDisable(int row, int col, String symbol)
    {
      buttons[row][col].setText(symbol); //set text to given text (either "X" or "O")
      buttons[row][col].setFont(new Font("MV Boli",Font.BOLD,125));
      buttons[row][col].setEnabled(false); //disable given JButton
    }
    
    // Clears text from all the buttons and sets them to enabled
    public void resetButtons()
    {
      for (int row = 0; row < 3; row++) 
      {
        for (int col = 0; col < 3; col++) 
        {
          buttons[row][col].setText("");
          buttons[row][col].setEnabled(true);
          buttons[row][col].setBackground(Color.BLACK);
        }
      }
    }
    
    //Used to highlight the winning sqaures green
    public void highlightWin(int row, int col, int row2, int col2, int row3, int col3)
    {
      buttons[row][col].setBackground(Color.GREEN);
      buttons[row2][col2].setBackground(Color.GREEN);
      buttons[row3][col3].setBackground(Color.GREEN);
    }
    
    // Used to show the post-game stats in a new frame for the user to see
    public void showStats()
    {
      // Same output as the game stats file 
      statsText.append("Game Over.\n"); // Used append to add text on to more text, setText() removes all text before it
      
      if (this.model.getFinalWinner().equals("Player")) 
      {
        statsText.append("You won the game!\n");
      } 
      else if (this.model.getFinalWinner().equals("Computer")) 
      {
        statsText.append("The computer won the game!\n");
      } 
      else 
      {
        statsText.append("Both players tied.\n");
      }
      
      statsText.append("# of Player Moves: " + this.model.getPlayerMoves() + "\n# of Computer Moves: " + this.model.getCompMoves() + "\nPlayer Wins: " + this.model.getPlayerWins() + "\nComputer Wins: " + this.model.getCompWins() + "\nRounds Tied: " + this.model.getRoundsTied());
      statsText.setFont(new Font("Serif", Font.BOLD,30));
      statsText.setDisabledTextColor(Color.BLACK);
      statsText.setEnabled(false);
      statsPanel.add(statsText);
      statsPanel.setBounds(0,0,800,100);
      statsFrame.add(statsPanel);
      statsFrame.setVisible(true);
    } // end of method
  } // end of class