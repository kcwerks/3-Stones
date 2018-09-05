/**/
/**
 * Kyle Calabro
 * Dr. Miller
 * Ramapo College of New Jersey
 * School of Theoretical and Applied Sciences
 * Senior Project - Computer Science
 */
/**/

package edu.ramapo.kcalabro.a3stones.Model;

/**
 * Created by KyleCalabro on 2/28/18.
 */

public class Human extends Player
{
    //----------------------- Member Methods -----------------------

    /**/
    /**
     * NAME
     *      Human(): Default constructor for the Human class.
     *
     * SYNOPSIS
     *      Human();
     *
     *      @param a_stoneColor The stone color to be used by the Human player.
     *
     * DESCRIPTION
     *      Default constructor for the Human class.
     *
     * RETURNS
     *      Object of the Human class that was just created.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public Human(char a_stoneColor)
    {
        setPlayerStoneColor(a_stoneColor);
        setIsComputer(false);
    }

    /**/
    /**
     * NAME
     *      makeMove(): To make a move for the Human Player.
     *
     * SYNOPSIS
     *      makeMove(Round a_round, Position a_positionToValidate, Stone a_stoneToPlay);
     *
     *      @param a_round The current Round being used in the Tournament.
     *      @param a_positionToValidate The Position of the board selected by the user to make a move to.
     *      @param a_stoneToPlay The Stone selected by the user to make a move to the selected position with.
     *
     * DESCRIPTION
     *      To make a move for the Human Player. First, checks that the given position and stone produce
     *      a valid move for the given board. If the given move is valid, then proceed to modify the actual
     *      board, remove the given stone from the Player's Hand, swap the current player of the Round and
     *      return true. Will return false if the given move is invalid.
     *
     * RETURNS
     *      Boolean:
     *          True -> If the given move is valid.
     *          False -> If the given move is invalid.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      1 March 2018
     */
    /**/

    public boolean makeMove(Round a_round, Position a_positionToValidate, Stone a_stoneToPlay)
    {
        a_round.getBoard().modifyBoard(a_positionToValidate, a_stoneToPlay);
        getHand().removeStone(a_stoneToPlay);
        a_round.swapCurrentPlayer();
        return true;
    }

    /**/
    /**
     * NAME
     *      isValidMove(): To determine if a move is valid for the Human Player.
     *
     * SYNOPSIS
     *      isValidMove(Board a_board, Position a_positionToValidate);
     *
     *      @param a_board The current Round being used in the Tournament.
     *      @param a_positionToValidate The Position of the board selected by the user to make a move to.
     *
     * DESCRIPTION
     *      To determine if a move for the Human Player is valid. First, checks that the given position and stone produce
     *      a valid move for the given board. If the given move is valid, return true. Will return false
     *      if the given move is invalid.
     *
     * RETURNS
     *      Boolean:
     *          True -> If the given move is valid.
     *          False -> If the given move is invalid.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      1 March 2018
     */
    /**/

    public boolean isValidMove(Board a_board, Position a_positionToValidate)
    {
        if(a_board.isValidMove(a_positionToValidate))
        {
            return true;
        }

        return false;
    }
}
