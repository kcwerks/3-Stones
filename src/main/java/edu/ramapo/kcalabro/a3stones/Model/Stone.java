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

public class Stone
{
    //------------------------ Data Members ------------------------

    // All possible colors of Stones.
    public final static char m_WHITE_STONE = 'W';

    public final static char m_BLACK_STONE = 'B';

    public final static char m_CLEAR_STONE = 'C';

    public final static char m_OPEN_POCKET = 'O';

    public final static char m_NON_EXISTENT = ' ';

    // The color of the Stone.
    private char m_stoneColor;

    //------------------------ Member Methods ------------------------

    /**/
    /**
     * NAME
     *      Stone(): Default constructor for the Stone class.
     *
     * SYNOPSIS
     *      Stone(char a_stoneColor);
     *
     *      @param a_stoneColor Char value which to set stone's color to.
     *
     * DESCRIPTION
     *      Default constructor for the Stone class.
     *
     * RETURNS
     *      Object of the Stone class.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public Stone(char a_stoneColor)
    {
        setStoneColor(a_stoneColor);
    }

    /**/
    /**
     * NAME
     *      getStoneColor(): To get a given stone's color.
     *
     * SYNOPSIS
     *      getStoneColor();
     *
     * DESCRIPTION
     *      To get a given stone's color.
     *
     * RETURNS
     *      Char representing the stone's color.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public char getStoneColor()
    {
        return m_stoneColor;
    }

    /**/
    /**
     * NAME
     *      setStoneColor(): To set a given stone's color.
     *
     * SYNOPSIS
     *      setStoneColor(char a_stoneColor);
     *
     *      @param a_stoneColor Char value to set the given stone's color to.
     *
     * DESCRIPTION
     *      To get a given stone's color.
     *
     * RETURNS
     *      Char representing the stone's color.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public void setStoneColor(char a_stoneColor)
    {
        this.m_stoneColor = a_stoneColor;
    }
}
