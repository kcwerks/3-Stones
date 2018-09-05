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

/**
 * Created by KyleCalabro on 3/29/18.
 */

import android.widget.Button;
import android.widget.TextView;

import edu.ramapo.kcalabro.a3stones.Model.Player;
import edu.ramapo.kcalabro.a3stones.Model.Stone;
import edu.ramapo.kcalabro.a3stones.R;

public class PlayerView
{
    private RoundActivity m_roundActivity;

    // The TextViews to represent the relevant data.
    private TextView m_blackScoreView;
    private TextView m_whiteScoreView;
    private TextView m_currentPlayerView;

    // The TextViews to represent the amount of stones remaining in a player's hand.
    private TextView m_blackStonesRemainingView;
    private TextView m_whiteStonesRemainingView;
    private TextView m_clearStonesRemainingView;

    /**/
    /**
     * NAME
     *      PlayerView(): Default constructor for the PlayerView class.
     *
     * SYNOPSIS
     *      PlayerView(RoundActivity a_roundActivity);
     *
     *      @param a_roundActivity Object of the RoundActivity class used in conjunction with the
     *      activity_round layout file.
     *
     * DESCRIPTION
     *      Default constructor for the PlayerView class.
     *
     * RETURNS
     *      Object of the PlayerView class.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      29 March 2018
     */
    /**/

    public PlayerView(RoundActivity a_roundActivity)
    {
        this.m_roundActivity = a_roundActivity;

        // Find the score TextViews in the layout file.
        m_blackScoreView = m_roundActivity.findViewById(R.id.blackStoneScore);
        m_whiteScoreView = m_roundActivity.findViewById(R.id.whiteStoneScore);
        m_currentPlayerView = m_roundActivity.findViewById(R.id.currentPlayer);

        // Find the remaining stone TextViews in the layout file.
        m_blackStonesRemainingView = m_roundActivity.findViewById(R.id.blackStonesRemaining);
        m_whiteStonesRemainingView = m_roundActivity.findViewById(R.id.whiteStonesRemaining);
        m_clearStonesRemainingView = m_roundActivity.findViewById(R.id.clearStonesRemaining);
    }

    /**/
    /**
     * NAME
     *      updatePlayerView(): To update the relevant data in the PlayerView class to the screen.
     *
     * SYNOPSIS
     *      updatePlayerView(Player a_currentPlayer, Player a_nextPlayer);
     *
     *      @param a_currentPlayer The current player of the Round.
     *      @param a_nextPlayer The next/previous player of the Round.
     *
     * DESCRIPTION
     *      To update the relevant data of the PlayerView class and display it to the screen.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      29 March 2018
     */
    /**/

    public void updatePlayerView(Player a_currentPlayer, Player a_nextPlayer)
    {
        Button blackStoneSelector = m_roundActivity.getBlackStoneSelector();
        Button whiteStoneSelector = m_roundActivity.getWhiteStoneSelector();
        Button clearStoneSelector = m_roundActivity.getClearStoneSelector();

        // Get the amount of stones remaining from the player's hand.
        int blackStonesRemaining = a_currentPlayer.getHand().getAvailableBlackStones().size();
        int whiteStonesRemaining = a_currentPlayer.getHand().getAvailableWhiteStones().size();
        int clearStonesRemaining = a_currentPlayer.getHand().getAvailableClearStones().size();

        // Update the score and current player information to the screen.
        if(a_currentPlayer.getPlayerStoneColor() == Stone.m_BLACK_STONE)
        {
            m_currentPlayerView.setText("Current Player: Black");
            m_blackScoreView.setText("Black Score: " + a_currentPlayer.getRoundScore());
            m_whiteScoreView.setText("White Score: " + a_nextPlayer.getRoundScore());
        }
        else
        {
            m_currentPlayerView.setText("Current Player: White");
            m_whiteScoreView.setText("White Score: " + a_currentPlayer.getRoundScore());
            m_blackScoreView.setText("Black Score: " + a_nextPlayer.getRoundScore());
        }

        // Update the stone remaining data to the screen.
        m_blackStonesRemainingView.setText("Stones Remaining: " + blackStonesRemaining);
        m_whiteStonesRemainingView.setText("Stones Remaining: " + whiteStonesRemaining);
        m_clearStonesRemainingView.setText("Stones Remaining: " + clearStonesRemaining);

        // Enable or disable the black stone selector if necessary.
        if(blackStonesRemaining == 0 || a_currentPlayer.getIsComputer())
        {
            blackStoneSelector.setEnabled(false);
        }
        else
        {
            blackStoneSelector.setEnabled(true);
        }

        // Enable or disable the white stone selector if necessary.
        if(whiteStonesRemaining == 0 || a_currentPlayer.getIsComputer())
        {
            whiteStoneSelector.setEnabled(false);
        }
        else
        {
            whiteStoneSelector.setEnabled(true);
        }

        // Enable or disable the clear stone selector if necessary.
        if(clearStonesRemaining == 0 || a_currentPlayer.getIsComputer())
        {
            clearStoneSelector.setEnabled(false);
        }
        else
        {
            clearStoneSelector.setEnabled(true);
        }
    }
}
