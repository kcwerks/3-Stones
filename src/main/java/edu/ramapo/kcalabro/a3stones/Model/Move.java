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
 * Created by KyleCalabro on 3/19/18.
 */

public class Move
{
    //------------------------ Data Members ------------------------

    // The position from which a move is to be made.
    private Position m_source;

    // The m_score gained in a given move.
    private int m_score;

    // The minimax value/m_score attached to a given move.
    private int m_minimaxValue;

    //------------------------ Member Methods ------------------------

    /**/
    /**
     * NAME
     *      Move(): Default constructor for the Move class.
     *
     * SYNOPSIS
     *      Move(Position a_source, int a_score);
     *
     *      @param a_source The m_source position to make a move from.
     *      @param a_score The m_score of the given move.
     *
     * DESCRIPTION
     *      Default constructor for the Move class.
     *
     * RETURNS
     *      Object of the Move class that was just created.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      22 May 2018
     */
    /**/

    Move(Position a_source, int a_score)
    {
        this.m_source = a_source;
        this.m_score = a_score;
    }

    /**/
    /**
     * NAME
     *      getPosition(): To get the position of a move.
     *
     * SYNOPSIS
     *      getPosition();
     *
     * DESCRIPTION
     *      To get the position of a move.
     *
     * RETURNS
     *      Object of the Position class representing the position of a Move.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      22 May 2018
     */
    /**/

    public Position getPosition()
    {
        return m_source;
    }

    /**/
    /**
     * NAME
     *      getScore(): To get the m_score of a given move.
     *
     * SYNOPSIS
     *      getScore();
     *
     * DESCRIPTION
     *      To get the m_score of a given move.
     *
     * RETURNS
     *      Integer value representing the m_score gained by a player making the given move.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      22 May 2018
     */
    /**/

    public int getScore()
    {
        return m_score;
    }

    /**/
    /**
     * NAME
     *      setScore(): To set the m_score of a given move.
     *
     * SYNOPSIS
     *      setScore();
     *
     *      @param a_scoreToSet Integer value representing the m_score to assign to a move.
     *
     * DESCRIPTION
     *      To set the m_score of a given move.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      22 May 2018
     */
    /**/


    public void setScore(int a_scoreToSet)
    {
        this.m_score = a_scoreToSet;
    }

    /**/
    /**
     * NAME
     *      getMinimaxValue(): To get the weighted value of a move from the minimax algorithm.
     *
     * SYNOPSIS
     *      getMinimaxValue();
     *
     * DESCRIPTION
     *      To get the weighted value of a move from the minimax algorithm.
     *
     * RETURNS
     *      Integer value representing the weighted value of a move based on the minimax algorithm.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      22 May 2018
     */
    /**/

    public int getMinimaxValue()
    {
        return m_minimaxValue;
    }

   /**/
    /**
     * NAME
     *      setMinimaxValue(): To set the weighted value of a move from the minimax algorithm.
     *
     * SYNOPSIS
     *      setMinimaxValue(int a_minimaxValue);
     *
     *      @param a_minimaxValue The weighted value of a move as determined by the minimax algorithm.
     *
     * DESCRIPTION
     *      To set the weighted value of a move from the minimax algorithm.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      22 May 2018
     */
    /**/

    public void setMinimaxValue(int a_minimaxValue)
    {
        this.m_minimaxValue = a_minimaxValue;
    }
}