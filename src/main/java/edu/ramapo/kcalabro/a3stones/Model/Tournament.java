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

public class Tournament
{
    //------------------------ Data Members ------------------------

    // The Round(s) that make up a Tournament.
    private Round m_round;

    // Provides functionality for serialization.
    private Serializer m_serializer;

    // The current Round number of the Tournament.
    private int m_roundNum;

    // The score limit of the Tournament.
    private int m_tournamentScoreLimit;

    //------------------------ Member Methods ------------------------

    /**/
    /**
     * NAME
     *      Tournament(): Default constructor for the Tournament class.
     *
     * SYNOPSIS
     *      Tournament();
     *
     * DESCRIPTION
     *      Default constructor for the Tournament class.
     *
     * RETURNS
     *      Object of the Tournament class.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public Tournament(boolean singlePlayer, boolean localMultiplayer)
    {
        if(singlePlayer)
        {
            m_round = new Round(singlePlayer);
        }
        else if(localMultiplayer)
        {
            m_round = new Round();
        }

        m_serializer = new Serializer();

        this.m_roundNum = 1;
    }

    /**/
    /**
     * NAME
     *      getRoundNum(): To get the current Round number of the Tournament.
     *
     * SYNOPSIS
     *      getRoundNum();
     *
     * DESCRIPTION
     *      To get the current Round number of the Tournament.
     *
     * RETURNS
     *      Integer value representing the current round number of the Tournament.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public int getRoundNum()
    {
        return m_roundNum;
    }

    /**/
    /**
     * NAME
     *      setRoundNum(): To set the current Round number of the Tournament.
     *
     * SYNOPSIS
     *      setRoundNum(int a_roundNum);
     *
     *      @param a_roundNum The value to set the round number of the Tournament to.
     *
     * DESCRIPTION
     *      To set the current Round number of the Tournament.
     *
     * RETURNS
     *      Boolean value indicating if the given round number was valid and the modification was made.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      3 March 2018
     */
    /**/

    public Boolean setRoundNum(int a_roundNum)
    {
        if(a_roundNum > m_roundNum)
        {
            this.m_roundNum = a_roundNum;
            return true;
        }
        return false;
    }

    /**/
    /**
     * NAME
     *      getRound(): To get the current Round of the Tournament.
     *
     * SYNOPSIS
     *      getRound();
     *
     * DESCRIPTION
     *      To get the current Round of the Tournament.
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

    public Round getRound()
    {
        return m_round;
    }

    /**/
    /**
     * NAME
     *      setRound(): To set the current Round of the Tournament.
     *
     * SYNOPSIS
     *      setRound(Round a_round);
     *
     *      @param a_round The Round Object to set the current instance of Round in Tournament to.
     *
     * DESCRIPTION
     *      To set the current Round of the Tournament.
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

    public void setRound(Round a_round)
    {
        this.m_round = a_round;
    }

    /**/
    /**
     * NAME
     *      getTournamentScoreLimit(): To get the Tournament score limit.
     *
     * SYNOPSIS
     *      getTournamentScoreLimit();
     *
     * DESCRIPTION
     *      To get the score limit of the Tournament.
     *
     * RETURNS
     *      Integer value representing the score limit of the Tournament.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      3 March 2018
     */
    /**/

    public int getTournamentScoreLimit()
    {
        return m_tournamentScoreLimit;
    }

    /**/
    /**
     * NAME
     *      setTournamentScoreLimit(): To set the Tournament score limit.
     *
     * SYNOPSIS
     *      setTournamentScoreLimit(a_scoreLimit);
     *
     *      @param a_scoreLimit The integer value to set the Tournament score limit to.
     *
     * DESCRIPTION
     *      To set the score limit of the Tournament initially.
     *
     * RETURNS
     *      Boolean value indicating if the given value was valid and the modification was made.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      3 March 2018
     */
    /**/

    public Boolean setTournamentScoreLimit(int a_scoreLimit)
    {
        if(a_scoreLimit > 0)
        {
            this.m_tournamentScoreLimit = a_scoreLimit;
            return true;
        }
        return false;
    }

    /**/
    /**
     * NAME
     *      getSerializer(): To get the Serializer Object used for the Tournament.
     *
     * SYNOPSIS
     *      getSerializer();
     *
     * DESCRIPTION
     *      To get the Serializer Object used for the Tournament.
     *
     * RETURNS
     *      Object of the Serializer class.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      3 March 2018
     */
    /**/

    public Serializer getSerializer()
    {
        return m_serializer;
    }

    /**/
    /**
     * NAME
     *      setSerializer(): To set the Serializer Object used for the Tournament.
     *
     * SYNOPSIS
     *      setSerializer();
     *
     * DESCRIPTION
     *      To set the Serializer Object used for the Tournament.
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

    public void setSerializer(Serializer a_serializer)
    {
        this.m_serializer = a_serializer;
    }

}
