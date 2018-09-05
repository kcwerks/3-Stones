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

public class Round
{
    //------------------------ Data Members ------------------------

    // Array of players for the Tournament/Round(s).
    private Player[] m_players;

    // The current player of the game.
    private char m_currentPlayer;

    // The board for the current round.
    private Board m_board;

    // The score limit of the Tournament.
    private int m_tournamentScoreLimit;

    //------------------------ Member Methods ------------------------

    /**/
    /**
     * NAME
     *      Round(): Default constructor for the Round class.
     *
     * SYNOPSIS
     *      Round();
     *
     * DESCRIPTION
     *      Default constructor for the Round class.
     *
     * RETURNS
     *      Object of the Round class with 2 Human players.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public Round()
    {
        this.m_board = new Board();
        this.m_players = new Player[2];

        this.m_players[0] = new Human(Stone.m_BLACK_STONE);
        this.m_players[1] = new Human(Stone.m_WHITE_STONE);

        this.m_players[0].setIsCurrentPlayer(true);
        this.m_players[1].setIsCurrentPlayer(false);

        this.m_currentPlayer = Stone.m_BLACK_STONE;
    }

    /**/
    /**
     * NAME
     *      Round(): Overload constructor for the Round class.
     *
     * SYNOPSIS
     *      Round(boolean a_singlePlayer);
     *
     *      @param a_singlePlayer Boolean flag indicating the round to start is a single player round.
     *                            Single player round is Computer vs. Human.
     *
     * DESCRIPTION
     *      Default constructor for the Round class.
     *
     * RETURNS
     *      Object of the Round class with 1 Human player and 1 Computer player.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public Round(boolean a_singlePlayer)
    {
        this.m_board = new Board();
        this.m_players = new Player[2];

        this.m_players[0] = new Human(Stone.m_BLACK_STONE);
        this.m_players[1] = new Computer(Stone.m_WHITE_STONE);

        this.m_players[0].setIsCurrentPlayer(true);
        this.m_players[1].setIsCurrentPlayer(false);

        this.m_currentPlayer = Stone.m_BLACK_STONE;
    }

    /**/
    /**
     * NAME
     *      getBlackPlayer(): Gets the Player object using black stones.
     *
     * SYNOPSIS
     *      getBlackPlayer();
     *
     * DESCRIPTION
     *      To get the Player object using black stones in the current round.
     *
     * RETURNS
     *      Object of the Player class that is using black stones.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      1 March 2018
     */
    /**/

    public Player getBlackPlayer()
    {
        if(m_players[0].getPlayerStoneColor() == Stone.m_BLACK_STONE)
        {
            return m_players[0];
        }
        else
        {
            return m_players[1];
        }
    }

    /**/
    /**
     * NAME
     *      getWhitePlayer(): Gets the Player Object using white stones.
     *
     * SYNOPSIS
     *      getWhitePlayer();
     *
     * DESCRIPTION
     *      To get the Player Object using white stones in the current round.
     *
     * RETURNS
     *      Object of the Player class that is using white stones.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      1 March 2018
     */
    /**/

    public Player getWhitePlayer()
    {
        if(m_players[0].getPlayerStoneColor() == Stone.m_WHITE_STONE)
        {
            return m_players[0];
        }
        else
        {
            return m_players[1];
        }
    }

    /**/
    /**
     * NAME
     *      hasRoundEnded(): To determine if a Round has ended.
     *
     * SYNOPSIS
     *      hasRoundEnded();
     *
     * DESCRIPTION
     *      To determine if a Round has ended. A Round ends when both player's have emptied
     *      their hands, therefore there are no more moves to be made.
     *
     * RETURNS
     *      Boolean value indicating if a Round has ended.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      15 March 2018
     */
    /**/

    public Boolean hasRoundEnded()
    {
        if(m_players[0].getHand().isHandEmpty() && m_players[1].getHand().isHandEmpty())
        {
            // If the round has ended, update the player's tournament scores...
            m_players[0].setTournamentScore(m_players[0].getTournamentScore() + m_players[0].getRoundScore());
            m_players[1].setTournamentScore(m_players[1].getTournamentScore() + m_players[1].getRoundScore());

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
     *      setTournamentScoreLimit(): To set the score limit of the Tournament.
     *
     * SYNOPSIS
     *      setTournamentScoreLimit(int a_scoreLimit);
     *
     *      @param a_scoreLimit Integer value to set the Tournament's score limit to.
     *
     * DESCRIPTION
     *      To set the Tournament's score limit.
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
     *      getTournamentScoreLimit(): To get the score limit of the Tournament.
     *
     * SYNOPSIS
     *      getTournamentScoreLimit();
     *
     * DESCRIPTION
     *      To get the Tournament's score limit.
     *
     * RETURNS
     *      Integer value representing the score limit of the Tournament.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public int getTournamentScoreLimit()
    {
        return m_tournamentScoreLimit;
    }

    /**/
    /**
     * NAME
     *      getBoard(): To get the Board utilized in the current Round of the Tournament.
     *
     * SYNOPSIS
     *      getBoard();
     *
     * DESCRIPTION
     *      To get the Board used in the current round of the Tournament.
     *
     * RETURNS
     *      Object of the Board class that is currently being utilized in the round.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public Board getBoard()
    {
        return m_board;
    }

    /**/
    /**
     * NAME
     *      setBoard(): To set the Board of the current Round.
     *
     * SYNOPSIS
     *      setBoard(Board a_board);
     *
     *      @param a_board The Board object which to set the current Board of the Round to.
     *
     * DESCRIPTION
     *      To set the Board of the current Round.
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

    public void setBoard(Board a_board)
    {
        this.m_board = a_board;
    }

    /**/
    /**
     * NAME
     *      swapCurrentPlayer(): To swap the current player in the Round.
     *
     * SYNOPSIS
     *      swapCurrentPlayer();
     *
     * DESCRIPTION
     *      To swap the current player in the Round.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      1 March 2018
     */
    /**/

    public void swapCurrentPlayer()
    {
        if(m_currentPlayer == Stone.m_BLACK_STONE)
        {
            this.m_currentPlayer = Stone.m_WHITE_STONE;
            m_players[0].setIsCurrentPlayer(false);
            m_players[1].setIsCurrentPlayer(true);
        }
        else
        {
            this.m_currentPlayer = Stone.m_BLACK_STONE;
            m_players[0].setIsCurrentPlayer(true);
            m_players[1].setIsCurrentPlayer(false);
        }

        if(m_players[1].getIsCurrentPlayer() && m_players[1].getIsComputer())
        {
           m_players[1].getBestMove(this);
        }

        getBlackPlayer().setRoundScore(m_board.getBlackScore());
        getWhitePlayer().setRoundScore(m_board.getWhiteScore());
    }

     /**/
    /**
     * NAME
     *      getCurrentPlayer(): Gets the current Player (Object) of the Round.
     *
     * SYNOPSIS
     *      getCurrentPlayer();
     *
     * DESCRIPTION
     *      Getter function for the currentPlayer Object of the Round.
     *
     * RETURNS
     *      Object of the Player class that is the current player in the Round.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      1 March 2018
     */
    /**/


    public Player getCurrentPlayer()
    {
        if(m_currentPlayer == Stone.m_BLACK_STONE)
        {
            return m_players[0];
        }
        else
        {
            return m_players[1];
        }
    }

    /**/
    /**
     * NAME
     *      getNextPlayer(): Gets the next/previous Player (Object) of the Round.
     *
     * SYNOPSIS
     *      getNextPlayer();
     *
     * DESCRIPTION
     *      Getter function for the next/previous Player Object of the Round.
     *
     * RETURNS
     *      Object of the Player class that is the next/previous player in the Round.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      1 March 2018
     */
    /**/

    public Player getNextPlayer()
    {
        if(m_currentPlayer == Stone.m_BLACK_STONE)
        {
            return m_players[1];
        }
        else
        {
            return m_players[0];
        }
    }
}
