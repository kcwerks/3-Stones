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
 * Created by Kyle Calabro on 2/28/18.
 */

public class Position
{
    //----------------------- Data Members -----------------------

    // The row coordinate of the position.
    private int m_rowPosition;

    // The column coordinate of the position.
    private int m_colPosition;

    // The stone object located at the position.
    private Stone m_stone;

    //----------------------- Member Methods -----------------------

     /**/
    /**
     * NAME
     *      Position(): Default constructor for the Position class.
     *
     * SYNOPSIS
     *      Position(int a_rowPosition, int a_colPosition, char a_stoneColor);
     *
     *      @param a_rowPosition The row coordinate of the position to create.
     *      @param a_colPosition The column coordinate of the position to create.
     *      @param a_stoneColor The stone color occupying the given row and column coordinates.
     *
     * DESCRIPTION
     *      Default constructor for the Position class.
     *
     * RETURNS
     *      Object of the Position class that was just created.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public Position(int a_rowPosition, int a_colPosition, char a_stoneColor)
    {
        m_rowPosition = a_rowPosition;
        m_colPosition = a_colPosition;
        m_stone = new Stone(a_stoneColor);
    }

    /**/
    /**
     * NAME
     *      Position(): Overload constructor for the Position class.
     *
     * SYNOPSIS
     *      Position(int a_rowPosition, int a_colPosition);
     *
     *      @param a_rowPosition The row coordinate of the position to create.
     *      @param a_colPosition The column coordinate of the position to create.
     *
     * DESCRIPTION
     *      Overload constructor for the Position class.
     *
     * RETURNS
     *      Object of the Position class that was just created.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public Position(int a_rowPosition, int a_colPosition)
    {
        m_rowPosition = a_rowPosition;
        m_colPosition = a_colPosition;
        m_stone = null;
    }

    /**/
    /**
     * NAME
     *      getRowPosition(): To get the row coordinate of a given Position.
     *
     * SYNOPSIS
     *      int getRowPosition();
     *
     * DESCRIPTION
     *      To get the row coordinate of a position.
     *
     * RETURNS
     *      An integer value representing the row coordinate of the position.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public int getRowPosition()
    {
        return m_rowPosition;
    }

    /**/
    /**
     * NAME
     *      getColPosition(): To get the column coordinate of a position.
     *
     * SYNOPSIS
     *      int getColPosition();
     *
     * DESCRIPTION
     *      To get the column coordinate of a position.
     *
     * RETURNS
     *      An integer value representing the column coordinate of a position.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public int getColPosition()
    {
        return m_colPosition;
    }

    /**/
    /**
     * NAME
     *      getStone(): To get the stone at a certain position of the board.
     *
     * SYNOPSIS
     *      getStone();
     *
     * DESCRIPTION
     *      To get the stone on the board at a given position.
     *
     * RETURNS
     *      An Object of the stone class at a certain position.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public Stone getStone()
    {
        return m_stone;
    }

    /**/
    /**
     * NAME
     *      setStone(): To set the stone for a certain position of the board.
     *
     * SYNOPSIS
     *      setStone(a_stone);
     *
     *      @param a_stone The Stone object which to set at the given Position.
     *
     * DESCRIPTION
     *      To set the stone on the board at a given position.
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

    public void setStone(Stone a_stone)
    {
        this.m_stone = a_stone;
    }
}
