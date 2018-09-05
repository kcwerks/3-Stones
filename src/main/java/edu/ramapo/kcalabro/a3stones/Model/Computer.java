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

import java.util.Vector;

/**
 * Created by KyleCalabro on 2/28/18.
 */

public class Computer extends Player
{
    //----------------------- Data Members -----------------------

    private Algo m_algo;

    //----------------------- Member Methods -----------------------

     /**/
    /**
     * NAME
     *      Computer(): Default constructor for the Human class.
     *
     * SYNOPSIS
     *      Computer(char a_stoneColor);
     *
     *      @param a_stoneColor The stone color to be used by the Computer player.
     *
     * DESCRIPTION
     *      Default constructor for the Computer class.
     *
     * RETURNS
     *      Nothing (constructor).
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      1 June 2018
     */
    /**/

    public Computer(char a_stoneColor)
    {
        setPlayerStoneColor(a_stoneColor);
        setIsComputer(true);

        m_algo = new Algo();
    }

     /**/
    /**
     * NAME
     *      canMakePlay(): Determines if the Computer player has a stone that can be placed on the board.
     *
     * SYNOPSIS
     *      canMakePlay(Round a_round);
     *
     *      @param a_round Object of the Round class representing the current round of the Tournament.
     *
     * DESCRIPTION
     *      Determines if the Computer player has a stone that can be placed on the board in the appropriate
     *      position.
     *
     * RETURNS
     *      Boolean:
     *          True -> If the Computer player has a stone that can be placed on the board.
     *          False -> If the Computer player does not have a stone that can be placed on the board.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      1 June 2018
     */
    /**/

    public boolean canMakePlay(Round a_round)
    {
        return false;
    }

    /**/
    /**
     * NAME
     *      getBestMove(): To get the best move for the current board based on the Minimax algorithm.
     *
     * SYNOPSIS
     *      getBestMove(Round a_round);
     *
     *      @param a_round Object of the Round class representing the current round of the Tournament.
     *
     * DESCRIPTION
     *      Finds the best move for the current board via the Minimax algorithm.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      3 June 2018
     */
    /**/

    public void getBestMove(Round a_round)
    {
        m_algo.initiateMiniMax(a_round,this, 1, true);

        Move bestMove = m_algo.getMinimaxBestMove();

        makeMove(a_round, bestMove.getPosition(), bestMove.getPosition().getStone());
    }

    /**/
    /**
     * NAME
     *      makeMove(): Makes a move for the Computer Player.
     *
     * SYNOPSIS
     *      makeMove(Round a_round, Position a_positionToValidate, Stone a_stoneToPlay);
     *
     *      @param a_round Object of the Round class representing the current round of the Tournament.
     *      @param a_positionToValidate The position to validate the placement of a stone at.
     *      @param a_stoneToPlay The stone to place at the given position.
     *
     * DESCRIPTION
     *      To make a move for the Computer Player. First, checks that the given position and stone produce
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
     *      3 June 2018
     */
    /**/

    public boolean makeMove(Round a_round, Position a_positionToValidate, Stone a_stoneToPlay)
    {
        a_round.getBoard().modifyBoard(a_positionToValidate, a_stoneToPlay);

        getHand().removeStone(a_stoneToPlay);
        a_round.swapCurrentPlayer();

        return true;
    }
}
