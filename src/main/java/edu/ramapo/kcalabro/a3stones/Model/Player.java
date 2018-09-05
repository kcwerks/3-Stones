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
 * Created by Kyle Calabro on 2/28/18.
 */

public class Player
{
    //------------------------ Data Members ------------------------

    // The player's hand.
    private Hand m_playerHand;

    // Flag to determine if given player is the current player.
    private Boolean m_isCurrentPlayer;

    // The round score for the current round of a tournament.
    private int m_roundScore;

    // The tournament score for a player in the tournament.
    private int m_tournamentScore;

    // The color stones being used by a player for the game.
    private char m_playerStoneColor;

    // Flag to determine if given player is a Computer player.
    private boolean m_isComputer;

    //------------------------ Member Methods ------------------------

    /**/
    /**
     * NAME
     *      Player(): Default constructor for the Player class.
     *
     * SYNOPSIS
     *      Player();
     *
     * DESCRIPTION
     *      Default constructor for the Player class.
     *
     * RETURNS
     *      Object of the Player class that was just created.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public Player()
    {
        m_playerHand = new Hand();

        m_tournamentScore = 0;
        m_roundScore = 0;

        m_isCurrentPlayer = false;
    }

    /**/
    /**
     * NAME
     *      Player(): Overload constructor for the Player class.
     *
     * SYNOPSIS
     *      Player(char a_stoneColor);
     *
     *      @param a_stoneColor The color of stones to be used by the player.
     *
     * DESCRIPTION
     *      Overload constructor for the Player class.
     *
     * RETURNS
     *      Nothing (constructor).
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      29 February 2018
     */
    /**/

    public Player(char a_stoneColor)
    {
        m_roundScore = 0;

        setPlayerStoneColor(a_stoneColor);

        m_isCurrentPlayer = false;

        m_playerHand = new Hand();
    }

    /**/
    /**
     * NAME
     *      getIsCurrentPlayer(): Gets the isCurrentPlayer Boolean flag.
     *
     * SYNOPSIS
     *      getIsCurrentPlayer();
     *
     * DESCRIPTION
     *      Getter function for the isCurrentPlayer Boolean flag for a Player.
     *
     * RETURNS
     *      Boolean value indicating if the player is the current player of the Round.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public Boolean getIsCurrentPlayer()
    {
        return m_isCurrentPlayer;
    }

     /**/
    /**
     * NAME
     *      setIsCurrentPlayer(): Sets the isCurrentPlayer Boolean flag.
     *
     * SYNOPSIS
     *      setIsCurrentPlayer(Boolean a_isCurrentPlayer);
     *
     *      @param a_isCurrentPlayer The Boolean value to set the isCurrentPlayerFlag to.
     *
     * DESCRIPTION
     *      Setter function for the isCurrentPlayer Boolean flag for a Player.
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

    public void setIsCurrentPlayer(Boolean a_isCurrentPlayer)
    {
        this.m_isCurrentPlayer = a_isCurrentPlayer;
    }

    /**/
    /**
     * NAME
     *      getHand(): To get a player's current Hand.
     *
     * SYNOPSIS
     *      getHand();
     *
     * DESCRIPTION
     *      To get the player's Hand.
     *
     * RETURNS
     *      Object of the Hand class representing the current player's hand.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public Hand getHand()
    {
        return m_playerHand;
    }

     /**/
    /**
     * NAME
     *      setHand(): To set a player's current Hand.
     *
     * SYNOPSIS
     *      setHand();
     *
     * DESCRIPTION
     *      To set the player's Hand.
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

    public void setHand(Hand a_hand)
    {
        this.m_playerHand = a_hand;
    }

   /**/
    /**
     * NAME
     *      getRoundScore(): To get a player's round score.
     *
     * SYNOPSIS
     *      getRoundScore();
     *
     * DESCRIPTION
     *      To get the player's round score.
     *
     * RETURNS
     *      Integer value representing the player's current round score.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public int getRoundScore()
    {
        return m_roundScore;
    }

   /**/
    /**
     * NAME
     *      setRoundScore(): To set a player's round score.
     *
     * SYNOPSIS
     *      setRoundScore();
     *
     *      @param a_roundScore Integer value to set the player's round score to.
     *
     * DESCRIPTION
     *      To set the player's round score.
     *
     * RETURNS
     *      Boolean value indicating if the given value was valid and the modification was made.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public Boolean setRoundScore(int a_roundScore)
    {
        if(a_roundScore > 0)
        {
            this.m_roundScore = a_roundScore;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**/
    /**
     * NAME
     *      getTournamentScore(): To get a player's Tournament score.
     *
     * SYNOPSIS
     *      getTournamentScore();
     *
     * DESCRIPTION
     *      To get the player's Tournament score.
     *
     * RETURNS
     *      Integer value representing the player's Tournament score.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public int getTournamentScore()
    {
        return m_tournamentScore;
    }

    /**/
    /**
     * NAME
     *      setTournamentScore(): To set a player's Tournament score.
     *
     * SYNOPSIS
     *      setRoundScore();
     *
     *      @param a_tournamentScore Integer value to set the player's Tournament score to.
     *
     * DESCRIPTION
     *      To set the player's Tournament score.
     *
     * RETURNS
     *      Boolean value indicating if the given value was valid and the modification was made.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public Boolean setTournamentScore(int a_tournamentScore)
    {
        if(a_tournamentScore >= 0)
        {
            this.m_tournamentScore = a_tournamentScore;
            return true;
        }
        return false;
    }

    /**/
    /**
     * NAME
     *      getBestMove(): Virtual function definition.
     *
     * SYNOPSIS
     *      getBestMove(Round a_round);
     *
     *      @param a_round The round object to get the best move based off of.
     *
     * DESCRIPTION
     *      Virtual function definition for the getBestMove() function.
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

    public void getBestMove(Round a_round) {}

    /**/
    /**
     * NAME
     *      getPlayerStoneColor(): To get what color of stone is being used by a Player.
     *
     * SYNOPSIS
     *      getPlayerStoneColor();
     *
     * DESCRIPTION
     *      To get the color of stone being used by a Player.
     *
     * RETURNS
     *      A char value indicating what color of stones a Player is using.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public char getPlayerStoneColor()
    {
        return m_playerStoneColor;
    }

    /**/
    /**
     * NAME
     *      setPlayerStoneColor(): To set which color stone is being used by a Player.
     *
     * SYNOPSIS
     *      setPlayerStoneColor(char a_playerStoneColor);
     *
     *      @param a_playerStoneColor char value representing the stone color to set
     *      the Player as using.
     *
     * DESCRIPTION
     *      To set the color of stone being used by a Player.
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

    public void setPlayerStoneColor(char a_playerStoneColor)
    {
        this.m_playerStoneColor = a_playerStoneColor;
    }

    /**/
    /**
     * NAME
     *      isValidMove(): To determine if a move is valid for the Player. (Virtual function definition)
     *
     * SYNOPSIS
     *      isValidMove(Board a_board, Position a_positionToValidate);
     *
     *      @param a_board The current Round being used in the Tournament.
     *      @param a_positionToValidate The Position of the board selected by the user to make a move to.
     *
     * DESCRIPTION
     *      To determine if a move for the Player is valid. First, checks that the given position and stone produce
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
        return false;
    }

    /**/
    /**
     * NAME
     *      canMakePlay(): Virtual function definition.
     *
     * SYNOPSIS
     *      canMakePlay();
     *
     *      @param a_round Object of the Round class representing the current round.
     *      @param a_isBlack Boolean value indicating if the current player is using black stones.
     *
     * DESCRIPTION
     *      Virtual function definition.
     *
     * RETURNS
     *      Boolean value indicating if the move was valid.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      1 March 2018
     */
    /**/

    public boolean canMakePlay(Round a_round, boolean a_isBlack)
    {
        return false;
    }

    /**/
    /**
     * NAME
     *      makeMove(): To make a move for the Player. (Virtual function definition)
     *
     * SYNOPSIS
     *      makeMove(Round a_round, Position a_positionToValidate, Stone a_stoneToPlay);
     *
     *      @param a_round The current Round being used in the Tournament.
     *      @param a_positionToValidate The Position of the board selected by the user to make a move to.
     *      @param a_stoneToPlay The Stone selected by the user to make a move to the selected position with.
     *
     * DESCRIPTION
     *      To make a move for the Player. First, checks that the given position and stone produce
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
        return false;
    }

     /**/
    /**
     * NAME
     *      setIsComputer(): To set the isComputer flag.
     *
     * SYNOPSIS
     *      setIsComputer(a_isComputer);
     *
     *      @param a_isComputer Boolean value to set the m_isComputer flag to.
     *
     * DESCRIPTION
     *      Setter function for the m_isComputer boolean flag.
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

    public void setIsComputer(boolean a_isComputer)
    {
        this.m_isComputer = a_isComputer;
    }

    /**/
    /**
     * NAME
     *      getIsComputer(): To get the isComputer flag.
     *
     * SYNOPSIS
     *      getIsComputer();
     *
     * DESCRIPTION
     *      Getter function for the m_isComputer boolean flag.
     *
     * RETURNS
     *      Boolean value indicating if the given player is a Computer player.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public boolean getIsComputer()
    {
        return m_isComputer;
    }
}