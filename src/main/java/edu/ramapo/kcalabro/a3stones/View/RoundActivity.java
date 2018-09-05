/**/
/**
 * Kyle Calabro
 * Dr. Miller
 * Ramapo College of New Jersey
 * School of Theoretical and Applied Sciences
 * Senior Project - Computer Science
 */
/**/

package edu.ramapo.kcalabro.a3stones.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.ramapo.kcalabro.a3stones.Model.Board;
import edu.ramapo.kcalabro.a3stones.Model.Position;
import edu.ramapo.kcalabro.a3stones.Model.Stone;
import edu.ramapo.kcalabro.a3stones.Model.Tournament;

import edu.ramapo.kcalabro.a3stones.R;

/**
 * Created by KyleCalabro on 2/2/18.
 */

public class RoundActivity extends AppCompatActivity
{
    //------------------------ Data Members ------------------------

    // The Tournament object used by the game.
    private Tournament m_tournament;

    // The board position selected by the user.
    private Position m_selectedPosition;

    // The Stone selected by the user for placement.
    private Stone m_selectedStone;

    // The view classes which to push data to.
    private BoardView m_boardView;
    private PlayerView m_playerView;

    // The Buttons for selecting a stone.
    private Button m_blackStoneSelector;
    private Button m_whiteStoneSelector;
    private Button m_clearStoneSelector;
    private Button m_makeMoveButton;
    private Button m_saveGameButton;

    // Boolean flags that indicate if a stone selector and board position have been clicked.
    private Boolean m_isBlackSelected;
    private Boolean m_isWhiteSelected;
    private Boolean m_isClearSelected;
    private Boolean m_isBoardPositionSelected;

    //------------------------ Member Methods ------------------------

     /**/
    /**
     * NAME
     *      onCreate(): The onCreate constructor that is called when the Activity is first created.
     *
     * SYNOPSIS
     *      onCreate(Bundle savedInstance);
     *
     *      @param a_savedInstance The bundle object imported from the previous Activity.
     *
     * DESCRIPTION
     *      The onCreate constructor that is called when the Activity is first created. Initializes
     *      all the Boolean flags of the class. Assigns the TextView stone selectors to their
     *      appropriate onClickListeners. Creates the BoardView and PlayerView objects which update
     *      display accordingly with the board and player information.
     *
     * RETURNS
     *      Void (onCreate Constructor).
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    @Override
    protected void onCreate(Bundle a_savedInstance)
    {
        // Removes the tile bar, for aesthetic purposes.
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(a_savedInstance);
        setContentView(R.layout.activity_round);

        // Determine if the user wants to start a new Tournament.
        Intent mainIntent = getIntent();

        boolean newGame = mainIntent.getExtras().getBoolean("newGame");

        boolean singlePlayer = mainIntent.getExtras().getBoolean("singlePlayer");
        boolean localMultiplayer = mainIntent.getExtras().getBoolean("localMultiplayer");

        int tournamentScore = mainIntent.getExtras().getInt("tournamentScore");

        String fileName = mainIntent.getExtras().getString("selectedFile");

        // If the user wishes to start a new Tournament, do so.
        if(newGame)
        {
            if(singlePlayer)
            {
                m_tournament = new Tournament(singlePlayer, localMultiplayer);
                m_boardView = new BoardView(this);
                m_playerView = new PlayerView(this);
                getExtras();
                m_tournament.setTournamentScoreLimit(tournamentScore);
            }
            else if(localMultiplayer)
            {
                m_tournament = new Tournament(singlePlayer, localMultiplayer);
                m_boardView = new BoardView(this);
                m_playerView = new PlayerView(this);
                getExtras();
                m_tournament.setTournamentScoreLimit(tournamentScore);
            }
        }

        else
        {
            m_tournament = new Tournament(singlePlayer, localMultiplayer);
            m_tournament.getSerializer().restoreFile(m_tournament, "savedGame.txt");

            m_boardView = new BoardView(this);
            m_playerView = new PlayerView(this);
        }

        // Initialize the boolean values for the stone selectors to false.
        m_isBlackSelected = false;
        m_isWhiteSelected = false;
        m_isClearSelected = false;
        m_isBoardPositionSelected = false;

        m_makeMoveButton = findViewById(R.id.makeMoveButton);
        m_makeMoveButton.setOnClickListener(makeMoveButtonListener);

        m_saveGameButton = findViewById(R.id.saveGameButton);
        m_saveGameButton.setOnClickListener(saveGameButtonListener);

        // Find the stone color selectors from the layout.
        m_blackStoneSelector = findViewById(R.id.blackStoneSelector);
        m_whiteStoneSelector = findViewById(R.id.whiteStoneSelector);
        m_clearStoneSelector = findViewById(R.id.clearStoneSelector);

        // Assign the selectors their respective on-click listeners.
        m_blackStoneSelector.setOnClickListener(blackStoneSelectorListener);
        m_whiteStoneSelector.setOnClickListener(whiteStoneSelectorListener);
        m_clearStoneSelector.setOnClickListener(clearStoneSelectorListener);

        addListenersToBoard();
        updateView(true);
    }

    /**/
    /**
     * NAME
     *      getExtras(): To get pertinent information from a Bundle object passed in through previous Activity.
     *
     * SYNOPSIS
     *      getExtras();
     *
     * DESCRIPTION
     *     To get pertinent information from a Bundle object passed in through previous Activity and updates model
     *     class with said data appropriately.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      12 August 2018
     */
    /**/

    public void getExtras()
    {
        Bundle bundle = getIntent().getExtras();

        m_tournament.getRound().getBlackPlayer().setTournamentScore(bundle.getInt("blackStonesTournScore", 0));
        m_tournament.getRound().getWhitePlayer().setTournamentScore(bundle.getInt("whiteStonesTournScore", 0));

        m_tournament.getRound().setTournamentScoreLimit(bundle.getInt("tournamentScoreLimit", 0));
        m_tournament.setRoundNum(bundle.getInt("roundNumber", 0));
    }

    /**/
    /**
     * NAME
     *      updateView(): To update the view.
     *
     * SYNOPSIS
     *      updateView(Boolean isBoardActive);
     *
     *      @param a_isBoardActive Boolean value indicating if the Board is active when displayed to
     *                             the screen.
     *
     * DESCRIPTION
     *      Updates the view with all the pertinent information. Calls on the PlayerView and BoardView
     *      classes update functions to properly update the information and display it to the screen.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    private void updateView(Boolean a_isBoardActive)
    {
        m_makeMoveButton.setEnabled(false);
        m_playerView.updatePlayerView(m_tournament.getRound().getCurrentPlayer(), m_tournament.getRound().getNextPlayer());
        m_boardView.updateBoardView(m_tournament.getRound().getBoard());
    }

     /**/
    /**
     * NAME
     *      generateToastMessage(): To generate a toast message and display it to the screen.
     *
     * SYNOPSIS
     *      generateToastMessage(String a_message);
     *
     *      @param a_message The string to display to the screen as a toast.
     *
     * DESCRIPTION
     *      Displays a given String to the screen through a toast message.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      1 March 2018
     */
    /**/

    public void generateToastMessage(String a_message)
    {
        Toast toastToDisplay = Toast.makeText(getApplicationContext(), a_message, Toast.LENGTH_LONG);
        toastToDisplay.setGravity(Gravity.CENTER, 0, 0);
        toastToDisplay.show();
    }

     /**/
    /**
     * NAME
     *      getBlackStoneSelector(): To get the Button that acts as the black stone selector.
     *
     * SYNOPSIS
     *      getBlackStoneSelector();
     *
     * DESCRIPTION
     *      Gets the Button that is being used as the selector for black stones.
     *
     * RETURNS
     *      Button that is used by the user to select black stones.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      1 March 2018
     */
    /**/

    public Button getBlackStoneSelector()
    {
        return m_blackStoneSelector;
    }

     /**/
    /**
     * NAME
     *      getWhiteStoneSelector(): To get the Button that acts as the white stone selector.
     *
     * SYNOPSIS
     *      getWhiteStoneSelector();
     *
     * DESCRIPTION
     *      Gets the Button that is being used as the selector for white stones.
     *
     * RETURNS
     *      Button that is used by the user to select white stones.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      1 March 2018
     */
    /**/

    public Button getWhiteStoneSelector()
    {
        return m_whiteStoneSelector;
    }

    /**/
    /**
     * NAME
     *      getClearStoneSelector(): To get the Button that acts as the clear stone selector.
     *
     * SYNOPSIS
     *      getClearStoneSelector();
     *
     * DESCRIPTION
     *      Gets the Button that is being used as the selector for clear stones.
     *
     * RETURNS
     *      Button that is used by the user to select clear stones.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      1 March 2018
     */
    /**/

    public Button getClearStoneSelector()
    {
        return m_clearStoneSelector;
    }

    /**/
    /**
     * NAME
     *      addListenersToBoard(): To add onClickListeners to the TextViews that make up the board.
     *
     * SYNOPSIS
     *      addListenersToBoard();
     *
     * DESCRIPTION
     *      To assign onClickListeners to each TextView that makes up the board.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      1 March 2018
     */
    /**/

    private void addListenersToBoard()
    {
        TextView boardPosition;
        int col = 0;

        for (int row = 0; row < Board.M_MAX_ROWS; row++)
        {
            int temp = row;

            if (row == 5)
            {
                col = 11;
            }
            if (row > 5)
            {
                temp = 10 - row;
            }
            if(row != 5)
            {
                col = temp * 2 + 3;
            }

            for (int colCount = 0; colCount < col; colCount++)
            {
                int positionId = getResources().getIdentifier("row_" +
                        Integer.toString(row) + "_col_" + Integer.toString(colCount), "id", getPackageName());

                boardPosition = findViewById(positionId);
                boardPosition.setOnClickListener(boardPositionListener);
            }
        }
    }

    /**/
    /**
     * NAME
     *      getSelectedPosition(): To get the Position that the user clicked on the board.
     *
     * SYNOPSIS
     *      getSelectedView(View a_view);
     *
     *      @param a_view The View Object which to find the position from.
     *
     * DESCRIPTION
     *      To get the actual position that the user clicked on the board.
     *
     * RETURNS
     *      Object of the Position class that contains the coordinates of the Board model
     *      which the user clicked on the screen.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      2 March 2018
     */
    /**/

    private Position getSelectedPosition(View a_view)
    {
        // Get the id string from the view.
        String positionCoordinates = getResources().getResourceEntryName(a_view.getId());

        // The pattern to be matched for position coordinates of the grid.
        String pattern = "row_(\\d+)_col_(\\d+)";

        // The regular expression to use in conjunction with the pattern.
        Pattern regex = Pattern.compile(pattern);

        // Parse the coordinates from the grid.
        Matcher matcher = regex.matcher(positionCoordinates);

        matcher.find();

        int rowPosition = Integer.parseInt(matcher.group(1));
        int colPosition = Integer.parseInt(matcher.group(2));

        Position selectedPosition = new Position(rowPosition, colPosition);

        return selectedPosition;
    }

    /**/
    /**
     * NAME
     *      highlightSelectedPosition(): To highlight the selected board position.
     *
     * SYNOPSIS
     *      highlightSelectedPosition(Position a_selectedPosition);
     *
     *      @param a_selectedPosition The Position of the board which to highlight.
     *
     * DESCRIPTION
     *      To highlight the board position selected by the user.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      15 March 2018
     */
    /**/

    private void highlightSelectedPosition(Position a_selectedPosition)
    {
        TextView boardPosition;

        int positionId = getResources().getIdentifier("row_" +
                Integer.toString(a_selectedPosition.getRowPosition()) + "_col_" +
                Integer.toString(a_selectedPosition.getColPosition()), "id", getPackageName());

        boardPosition = findViewById(positionId);
        boardPosition.setBackgroundResource(R.drawable.empty_pocket_hl);
    }

    /**/
    /**
     * NAME
     *      unhighlightSelectedPosition(): To highlight the selected board position.
     *
     * SYNOPSIS
     *      unhighlightSelectedPosition();
     *
     * DESCRIPTION
     *      To unhighlight the board position selected by the user.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      15 March 2018
     */
    /**/

    private void unhighlightSelectedPosition(Position a_selectedPosition)
    {
        TextView boardPosition;

        int positionId = getResources().getIdentifier("row_" +
                Integer.toString(a_selectedPosition.getRowPosition()) + "_col_" +
                Integer.toString(a_selectedPosition.getColPosition()), "id", getPackageName());

        boardPosition = findViewById(positionId);
        boardPosition.setBackgroundResource(R.drawable.empty_pocket);
    }

    /**/
    /**
     * NAME
     *      resetSelectors(): To reset the selected Stone color selectors.
     *
     * SYNOPSIS
     *      resetSelectors();
     *
     * DESCRIPTION
     *      To deselect all the Stone selectors in the View and reset their Boolean values.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      1 March 2018
     */
    /**/

    private void resetSelectors()
    {
        m_isBlackSelected = false;
        m_isClearSelected = false;
        m_isWhiteSelected = false;
        m_isBoardPositionSelected = false;


        m_blackStoneSelector.setBackgroundResource(R.drawable.black_stone_border);
        m_whiteStoneSelector.setBackgroundResource(R.drawable.white_stone_border);
        m_clearStoneSelector.setBackgroundResource(R.drawable.clear_stone_border);
    }

    /**/
    /**
     * NAME
     *      endActivity(): To end the activity.
     *
     * SYNOPSIS
     *      endActivity();
     *
     * DESCRIPTION
     *      To end the activity and set up the Bundle object to pass on to the next activity.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      20 March 2018
     */
    /**/

    private void endActivity()
    {
        Intent endRound = new Intent(RoundActivity.this, EndRoundActivity.class);

        endRound.putExtra("blackStonesRoundScore", m_tournament.getRound().getBlackPlayer().getRoundScore());
        endRound.putExtra("whiteStonesRoundScore", m_tournament.getRound().getWhitePlayer().getRoundScore());

        endRound.putExtra("roundNumber", m_tournament.getRoundNum());
        endRound.putExtra("tournamentScoreLimit", m_tournament.getTournamentScoreLimit());

        endRound.putExtra("blackStonesTournScore", m_tournament.getRound().getBlackPlayer().getTournamentScore());
        endRound.putExtra("whiteStonesTournScore", m_tournament.getRound().getWhitePlayer().getTournamentScore());

        endRound.putExtra("singlePlayer", getIntent().getExtras().getBoolean("singlePlayer"));
        endRound.putExtra("localMultiplayer", getIntent().getExtras().getBoolean("localMultiplayer"));


        startActivity(endRound);
        finish();
    }

    //------------------------ On Click Listeners ------------------------

    /**/
    /**
     * NAME
     *      boardPositionListener(): onClickListener for the positions of the board.
     *
     * SYNOPSIS
     *      boardPositionListener();
     *
     * DESCRIPTION
     *      The onClickListener for a position of the board displayed to the screen. First ensures
     *      that a user has selected a stone for placement before proceeding. Then ensures that the
     *      selected position on the board is an open pocket. If the selected position is an open pocket
     *      then calls the appropriate methods to validate the move.
     *
     * RETURNS
     *      Void (onClickListener).
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    View.OnClickListener boardPositionListener = (new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            Board board = m_tournament.getRound().getBoard();
            Position selectedPosition = getSelectedPosition(view);

            // If no stone selectors have been chosen, the user cannot make a move.
            if(!m_isWhiteSelected && !m_isBlackSelected && !m_isClearSelected)
            {
                generateToastMessage("Sorry, you must select a stone before selecting a board position!");
            }

            // If a stone selector has been chosen, first ensure that their selected board position
            // is open.
            else
            {
                // If a board position has not already been selected...
                if(!m_isBoardPositionSelected)
                {
                    // Ensure the board position is open.
                    if (board.getPositionAtCoordinates(selectedPosition).getStone().getStoneColor() == Stone.m_OPEN_POCKET)
                    {
                        highlightSelectedPosition(selectedPosition);
                        m_isBoardPositionSelected = true;

                        m_selectedPosition = selectedPosition;

                        m_makeMoveButton.setEnabled(true);
                    }
                    // If the board position is not open, a valid move cannot be made.
                    else
                    {
                        generateToastMessage("Sorry, you must select an open board position!");
                    }
                }

                // A board position had already been selected but the user wishes to change it.
                else
                {
                    // Unhighlight the position.
                    unhighlightSelectedPosition(m_selectedPosition);
                    m_isBoardPositionSelected = false;

                    // Do not allow the user to make a move.
                    m_makeMoveButton.setEnabled(false);

                    // Ensure the board position is open.
                    if (board.getPositionAtCoordinates(selectedPosition).getStone().getStoneColor() == Stone.m_OPEN_POCKET)
                    {
                        highlightSelectedPosition(selectedPosition);
                        m_isBoardPositionSelected = true;

                        m_selectedPosition = selectedPosition;

                        m_makeMoveButton.setEnabled(true);
                    }
                    // If the board position is not open, a valid move cannot be made.
                    else
                    {
                        generateToastMessage("Sorry, you must select an open board position!");
                    }
                }
            }
        }
    });

    /**/
    /**
     * NAME
     *      makeMoveButtonListener(): onClickListener for the Make Move Button.
     *
     * SYNOPSIS
     *       makeMoveButtonListener();
     *
     * DESCRIPTION
     *      The onClickListener for the m_blackStoneSelector Make Move Button. The user may only click
     *      the Make Move button if both a board position and stone selector have both been chosen.
     *      First, determines if the given board position and stone produce a valid move. If the move
     *      is valid, proceeds to have the current player make the move and update the view.
     *
     * RETURNS
     *      Void (onClickListener).
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    View.OnClickListener makeMoveButtonListener = (new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            // Get the user's selected Stone.
            if(m_isBlackSelected)
            {
                m_selectedStone = new Stone(Stone.m_BLACK_STONE);
            }
            else if(m_isWhiteSelected)
            {
                m_selectedStone = new Stone(Stone.m_WHITE_STONE);
            }
            else
            {
                m_selectedStone = new Stone(Stone.m_CLEAR_STONE);
            }

            Boolean validMove = m_tournament.getRound().getCurrentPlayer().isValidMove(m_tournament.getRound().getBoard(), m_selectedPosition);

            if(validMove)
            {
                m_tournament.getRound().getCurrentPlayer().makeMove(m_tournament.getRound(), m_selectedPosition, m_selectedStone);

                if(m_tournament.getRound().hasRoundEnded())
                {
                    endActivity();
                }

                unhighlightSelectedPosition(m_selectedPosition);
                resetSelectors();
                updateView(true);
            }
            else
            {
                generateToastMessage("Sorry, that is not a valid move, try again!");
                unhighlightSelectedPosition(m_selectedPosition);
                resetSelectors();
                updateView(true);
            }
        }
    });

    /**/
    /**
     * NAME
     *      saveGameButtonListener(): onClickListener for the Make Move Button.
     *
     * SYNOPSIS
     *       saveGameButtonListener();
     *
     * DESCRIPTION
     *      The onClickListener for the save game Button. When clicked it saves the game to a text file
     *      and quits the app.
     *
     * RETURNS
     *      Void (onClickListener).
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      20 March 2018
     */
    /**/

    View.OnClickListener saveGameButtonListener = (new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
           try
           {
               m_tournament.getSerializer().serializeFile(m_tournament);
           }
           catch(IOException ioException)
           {
                ioException.printStackTrace();
           }

           finish();
        }
    });

    /**/
    /**
     * NAME
     *      blackStoneSelectorListener(): onClickListener for the m_blackStoneSelector.
     *
     * SYNOPSIS
     *      blackStoneSelectorListener();
     *
     * DESCRIPTION
     *      The onClickListener for the m_blackStoneSelector Button. Modifies the Button's background
     *      resource to highlight the stone selector on the first click. Reverses the modification on the
     *      second click. Also modifies the Boolean flag associated with the selector accordingly.
     *
     * RETURNS
     *      Void (onClickListener).
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      3 March 2018
     */
    /**/

    View.OnClickListener blackStoneSelectorListener = (new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            if(m_isBlackSelected)
            {
                m_isBlackSelected = false;
                m_blackStoneSelector.setBackgroundResource(R.drawable.black_stone_border);

                unhighlightSelectedPosition(m_selectedPosition);
                m_isBoardPositionSelected = false;
                m_makeMoveButton.setEnabled(false);
            }
            else
            {
                m_isBlackSelected = true;
                m_blackStoneSelector.setBackgroundResource(R.drawable.black_stone_border_hl);

                if(m_isClearSelected)
                {
                    m_isClearSelected = false;
                    m_clearStoneSelector.setBackgroundResource(R.drawable.clear_stone_border);
                }
                if(m_isWhiteSelected)
                {
                    m_isWhiteSelected = false;
                    m_whiteStoneSelector.setBackgroundResource(R.drawable.white_stone_border);
                }
            }
        }
    });

    /**/
    /**
     * NAME
     *      whiteStoneSelectorListener(): onClickListener for the m_whiteStoneSelector.
     *
     * SYNOPSIS
     *      whiteStoneSelectorListener();
     *
     * DESCRIPTION
     *      The onClickListener for the m_whiteStoneSelector Button. Modifies the Button's background
     *      resource to highlight the stone selector on the first click. Reverses the modification on the
     *      second click. Also modifies the Boolean flag associated with the selector accordingly.
     *
     * RETURNS
     *      Void (onClickListener).
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      3 March 2018
     */
    /**/

    View.OnClickListener whiteStoneSelectorListener = (new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            if(m_isWhiteSelected)
            {
                m_isWhiteSelected = false;
                m_whiteStoneSelector.setBackgroundResource(R.drawable.white_stone_border);

                unhighlightSelectedPosition(m_selectedPosition);
                m_isBoardPositionSelected = false;
                m_makeMoveButton.setEnabled(false);
            }
            else
            {
                m_isWhiteSelected = true;
                m_whiteStoneSelector.setBackgroundResource(R.drawable.white_stone_border_hl);

                if(m_isBlackSelected)
                {
                    m_isBlackSelected = false;
                    m_blackStoneSelector.setBackgroundResource(R.drawable.black_stone_border);
                }
                if(m_isClearSelected)
                {
                    m_isClearSelected = false;
                    m_clearStoneSelector.setBackgroundResource(R.drawable.clear_stone_border);
                }
            }
        }
    });

    /**/
    /**
     * NAME
     *      clearStoneSelectorListener(): onClickListener for the m_blackStoneSelector.
     *
     * SYNOPSIS
     *      clearStoneSelectorListener();
     *
     * DESCRIPTION
     *      The onClickListener for the m_clearStoneSelector Button. Modifies the Button's background
     *      resource to highlight the stone selector on the first click. Reverses the modification on the
     *      second click. Also modifies the Boolean flag associated with the selector accordingly.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      3 March 2018
     */
    /**/

    View.OnClickListener clearStoneSelectorListener = (new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            if(m_isClearSelected)
            {
                m_isClearSelected = false;
                m_clearStoneSelector.setBackgroundResource(R.drawable.clear_stone_border);

                unhighlightSelectedPosition(m_selectedPosition);
                m_isBoardPositionSelected = false;
                m_makeMoveButton.setEnabled(false);
            }
            else
            {
                m_isClearSelected = true;
                m_clearStoneSelector.setBackgroundResource(R.drawable.clear_stone_border_hl);

                if(m_isBlackSelected)
                {
                    m_isBlackSelected = false;
                    m_blackStoneSelector.setBackgroundResource(R.drawable.black_stone_border);
                }
                if(m_isWhiteSelected)
                {
                    m_isWhiteSelected = false;
                    m_whiteStoneSelector.setBackgroundResource(R.drawable.white_stone_border);
                }
            }
        }
    });
}
