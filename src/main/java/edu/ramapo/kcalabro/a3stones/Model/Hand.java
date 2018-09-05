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

public class Hand
{
    //------------------------ Data Members ------------------------

    // The maximum number of colored stones to be generated.
    private final static int M_MAX_COLORED_STONES = 15;

    // The maximum number of clear stones to be generated.
    private final static int M_MAX_CLEAR_STONES = 6;

    // The hand of a player itself.
    private Vector<Vector<Stone>> m_hand;

    //------------------------ Member Methods ------------------------

    /**/
    /**
     * NAME
     *      Hand(): Default constructor for the Hand class.
     *
     * SYNOPSIS
     *      Hand();
     *
     * DESCRIPTION
     *      Object of the Hand class that was just created.
     *
     * RETURNS
     *      Nothing (constructor).
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public Hand()
    {
        m_hand = new Vector<Vector<Stone>>();
        initializeHand();
    }

     /**/
    /**
     * NAME
     *      Hand(): Overload constructor for the Hand class.
     *
     * SYNOPSIS
     *      Hand(a_numBlackStones, a_numWhiteStones, a_numClearStones);
     *
     *      @param a_numBlackStones The number of black stones to create a hand with.
     *      @param a_numWhiteStones The number of white stones to create a hand with.
     *      @param a_numClearStones The number of clear stones to create a hand with.
     *
     * DESCRIPTION
     *      Overload constructor for the Hand class.
     *
     * RETURNS
     *      Object of the Hand class that was just created with the given parameters.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public Hand(int a_numBlackStones, int a_numWhiteStones, int a_numClearStones)
    {
        m_hand = new Vector<Vector<Stone>>();
        restoreHand(a_numBlackStones, a_numWhiteStones, a_numClearStones);
    }

    /**/
    /**
     * NAME
     *      isHandEmpty(): To determine if a player's Hand is empty.
     *
     * SYNOPSIS
     *      isHandEmpty();
     *
     * DESCRIPTION
     *      To determine if a player's Hand is empty. A player's Hand is empty when they have
     *      no black, white, or clear stones remaining.
     *
     * RETURNS
     *      Boolean value indicating if the Hand is empty.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      2 March 2018
     */
    /**/

    public Boolean isHandEmpty()
    {
        // Black stones...
        if(m_hand.elementAt(0).isEmpty())
        {
            // White stones...
            if(m_hand.elementAt(1).isEmpty())
            {
                // Clear stones...
                if(m_hand.elementAt(2).isEmpty())
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**/
    /**
     * NAME
     *      getHand(): To get the vector of vectors representing a player's Hand.
     *
     * SYNOPSIS
     *      getHand();
     *
     * DESCRIPTION
     *      To get a given player's Hand.
     *
     * RETURNS
     *      Vector of vectors containing stones representing a player's hand.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public Vector<Vector<Stone>> getHand()
    {
        return m_hand;
    }

    /**/
    /**
     * NAME
     *      setHand(): To set the vector of vectors representing a player's Hand.
     *
     * SYNOPSIS
     *      setHand(Vector<Vector<Stone>> a_hand);
     *
     *      @param a_hand The Hand object which to set the current Hand object to.
     *
     * DESCRIPTION
     *      To set a given player's Hand.
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

    public void setHand(Vector<Vector<Stone>> a_hand)
    {
        this.m_hand = a_hand;
    }

    /**/
    /**
     * NAME
     *      getAvailableBlackStones(): To get the vector containing a player's available black stones from the Hand.
     *
     * SYNOPSIS
     *      getAvailableBlackStones();
     *
     * DESCRIPTION
     *      To get a given player's available black stones.
     *
     * RETURNS
     *      Vector containing the black stones available in a player's Hand.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      2 March 2018
     */
    /**/

    public Vector<Stone> getAvailableBlackStones()
    {
        return m_hand.elementAt(0);
    }

    /**/
    /**
     * NAME
     *      getAvailableWhiteStones(): To get the vector containing a player's available white stones from the Hand.
     *
     * SYNOPSIS
     *      getAvailableWhiteStones();
     *
     * DESCRIPTION
     *      To get a given player's available white stones.
     *
     * RETURNS
     *      Vector containing the white stones available in a player's Hand.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      2 March 2018
     */
    /**/

    public Vector<Stone> getAvailableWhiteStones()
    {
        return m_hand.elementAt(1);
    }

    /**/
    /**
     * NAME
     *      getAvailableClearStones(): To get the vector containing a player's available clear stones from the Hand.
     *
     * SYNOPSIS
     *      getAvailableClearStones();
     *
     * DESCRIPTION
     *      To get a given player's available clear stones.
     *
     * RETURNS
     *      Vector containing the clear stones available in a player's Hand.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      2 March 2018
     */
    /**/

    public Vector<Stone> getAvailableClearStones()
    {
        return m_hand.elementAt(2);
    }

    /**/
    /**
     * NAME
     *      removeStone(): Removes a given stone from a player's hand.
     *
     * SYNOPSIS
     *      removeStone(Stone a_stoneToRemove);
     *
     * DESCRIPTION
     *      To remove a given stone from a Player's hand.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      2 March 2018
     */
    /**/

    public void removeStone(Stone a_stoneToRemove)
    {
        if(a_stoneToRemove.getStoneColor() == Stone.m_BLACK_STONE)
        {
            m_hand.elementAt(0).removeElementAt(0);
        }
        else if(a_stoneToRemove.getStoneColor() == Stone.m_WHITE_STONE)
        {
            m_hand.elementAt(1).removeElementAt(0);
        }
        else
        {
            m_hand.elementAt(2).removeElementAt(0);
        }
    }

    /**/
    /**
     * NAME
     *      initializeHand(): To initialize a player's Hand.
     *
     * SYNOPSIS
     *      initializeHand();
     *
     * DESCRIPTION
     *      To initialize a given player's Hand with the appropriate number of colored and clear
     *      stones. 15 Black stones, 15 White stones, 6 Clear stones each.
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

    private void initializeHand()
    {
        addInitialColoredStones();
        addInitialClearStones();
    }

    /**/
    /**
     * NAME
     *      restoreHand(): To restore a player's Hand.
     *
     * SYNOPSIS
     *      restoreHand(a_numBlackStones, a_numWhiteStones, a_numClearStones);
     *
     *      @param a_numBlackStones The number of black stones to create a hand with.
     *      @param a_numWhiteStones The number of white stones to create a hand with.
     *      @param a_numClearStones The number of clear stones to create a hand with.
     *
     * DESCRIPTION
     *      To restore a given player's Hand with the given number of colored and clear
     *      stones. Used for serialization/file restoration purposes.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 March 2018
     */
    /**/

    private void restoreHand(int a_numBlackStones, int a_numWhiteStones, int a_numClearStones)
    {
        Vector<Stone> blackStones = new Vector<>();
        Vector<Stone> whiteStones = new Vector<>();
        Vector<Stone> clearStones = new Vector<>();

        for(int i = 0; i < a_numBlackStones; i++)
        {
            Stone blackStone = new Stone(Stone.m_BLACK_STONE);
            blackStones.add(blackStone);
        }

        for(int i = 0; i < a_numWhiteStones; i++)
        {
            Stone whiteStone = new Stone(Stone.m_WHITE_STONE);
            whiteStones.add(whiteStone);
        }

        for (int i = 0; i < a_numClearStones; i++)
        {
            Stone clearStone = new Stone(Stone.m_CLEAR_STONE);
            clearStones.add(clearStone);
        }

        m_hand.add(blackStones);
        m_hand.add(whiteStones);
        m_hand.add(clearStones);
    }

    /**/
    /**
     * NAME
     *      addInitialColoredStones(): To add the initial number of colored stones to a player's Hand.
     *
     * SYNOPSIS
     *      addInitialColoredStones();
     *
     * DESCRIPTION
     *      To initialize a given player's available colored stones.
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

    private void addInitialColoredStones()
    {
        Vector<Stone> blackStones = new Vector<Stone>();
        Vector<Stone> whiteStones = new Vector<Stone>();

        // Add the proper number of colored stones to each vector.
        for(int i = 0; i < M_MAX_COLORED_STONES; i++)
        {
            Stone blackStone = new Stone(Stone.m_BLACK_STONE);
            Stone whiteStone = new Stone(Stone.m_WHITE_STONE);

            blackStones.add(blackStone);
            whiteStones.add(whiteStone);
        }

        // Add the vectors of colored stones to the player's hand.
        m_hand.add(blackStones);
        m_hand.add(whiteStones);
    }

    /**/
    /**
     * NAME
     *      addInitialClearStones(): To add the initial number of clear stones to a player's Hand.
     *
     * SYNOPSIS
     *      addInitialClearStones();
     *
     * DESCRIPTION
     *      To initialize a given player's available clear stones.
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

    private void addInitialClearStones()
    {
        Vector<Stone> clearStones = new Vector<Stone>();

        // Add the proper number of clear stones to the vector.
        for(int i = 0; i < M_MAX_CLEAR_STONES; i++)
        {
            Stone clearStone = new Stone(Stone.m_CLEAR_STONE);

            clearStones.add(clearStone);
        }

        // Add the vector of clear stones to the player's hand.
        m_hand.add(clearStones);
    }
}
