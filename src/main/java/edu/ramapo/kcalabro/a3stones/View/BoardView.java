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
 * Created by KyleCalabro on 2/28/18.
 */

import android.widget.TextView;

import edu.ramapo.kcalabro.a3stones.Model.Board;
import edu.ramapo.kcalabro.a3stones.Model.Position;

import edu.ramapo.kcalabro.a3stones.R;

public class BoardView
{
    //------------------------ Data Members ------------------------

    public final static char m_WHITE_STONE = 'W';

    public final static char m_BLACK_STONE = 'B';

    public final static char m_CLEAR_STONE = 'C';

    public final static char m_OPEN_POCKET = 'O';

    public final static char m_NON_EXISTENT = ' ';

    private RoundActivity m_roundActivity;

    //------------------------ Member Methods ------------------------

    /**/
    /**
     * NAME
     *      BoardView(): Default constructor for the BoardView class.
     *
     * SYNOPSIS
     *      BoardView(RoundActivity a_roundActivity);
     *
     *      @param a_roundActivity the RoundActivity from which to model the BoardView.
     *
     * DESCRIPTION
     *      Default constructor for the BoardView class.
     *
     * RETURNS
     *      Object of the BoardView class.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public BoardView(RoundActivity a_roundActivity)
    {
        this.m_roundActivity = a_roundActivity;
    }

    /**/
    /**
     * NAME
     *      highlightPreviousPlacedStone(): To highlight the last placed stone by either player.
     *
     * SYNOPSIS
     *      BoardView(RoundActivity a_roundActivity);
     *
     *      @param a_board The current board being used by the game.
     *
     * DESCRIPTION
     *      Highlights the last placed stone on the board for either player to aid human players
     *      in keeping track of which row and/or column they can place a stone in.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      10 April 2018
     */
    /**/

    private void highlightPreviousPlacedStone(Board a_board)
    {
        int previousRow = a_board.getPreviousMoveRow();
        int previousCol = a_board.getPreviousMoveCol();

        char previousColor = a_board.getPositionAtCoordinates(new Position(previousRow, previousCol)).getStone().getStoneColor();

        // Find the id of the given slot in the grid representing the board.
        int slotId = m_roundActivity.getResources().getIdentifier("row_" +
                Integer.toString(previousRow) + "_" + "col_" + Integer.toString(previousCol),
                "id", m_roundActivity.getPackageName());

        TextView slotView = m_roundActivity.findViewById(slotId);

        // Set the TextView to the appropriate image.
        switch(previousColor)
        {
            case m_BLACK_STONE:
                slotView.setBackgroundResource(R.drawable.g_board_black_stone);
                break;
            case m_WHITE_STONE:
                slotView.setBackgroundResource(R.drawable.g_board_white_stone);
                break;
            case m_CLEAR_STONE:
                slotView.setBackgroundResource(R.drawable.g_board_clear_stone);
                break;
        }
    }

    /**/
    /**
     * NAME
     *      updateBoardView(): To update the relevant data in the BoardView class and display it to the screen.
     *
     * SYNOPSIS
     *      updateBoardView(Board a_board);
     *
     *      @param a_board The board from which to display to the screen properly.
     *
     * DESCRIPTION
     *      To update the relevant data of the BoardView class and display it to the screen.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      10 April 2018
     */
    /**/

    public void updateBoardView(Board a_board)
    {
        // Iterate through the given board.
        for(int row = 0; row < a_board.getM_board().size(); row++)
        {
            int numCols = a_board.getM_board().elementAt(row).size();

            for(int col = 0; col < numCols; col++)
            {
                Position positionToModel = new Position(row, col);

                // Find the id of the given slot in the grid representing the board.
                int slotId = m_roundActivity.getResources().getIdentifier("row_" +
                        Integer.toString(row) + "_" + "col_" + Integer.toString(col), "id", m_roundActivity.getPackageName());

                TextView slotView = m_roundActivity.findViewById(slotId);

                slotView.clearAnimation();
                // Get the color of the stone at the given position of the board.
                char stoneColor = a_board.getPositionAtCoordinates(positionToModel).getStone().getStoneColor();

                // Properly display the color of the stone at the given position.
                switch(stoneColor)
                {
                    case m_BLACK_STONE:
                        slotView.setBackgroundResource(R.drawable.board_black_stone);
                        break;
                    case m_WHITE_STONE:
                        slotView.setBackgroundResource(R.drawable.board_white_stone);
                        break;
                    case m_CLEAR_STONE:
                        slotView.setBackgroundResource(R.drawable.board_clear_stone);
                        break;
                    case m_NON_EXISTENT:
                        slotView.setBackgroundResource(R.drawable.non_existent_pocket);
                        break;
                    case m_OPEN_POCKET:
                        slotView.setBackgroundResource(R.drawable.empty_pocket);
                        break;
                }
            }
        }
        if(!a_board.getFreePlacement() && a_board.getHasFirstPlayBeenMade())
        {
            highlightPreviousPlacedStone(a_board);
        }
    }
}

