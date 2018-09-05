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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

/**
 * Created by KyleCalabro on 4/17/18.
 */

public class Algo
{
    //----------------------- Data Members -----------------------

    // The root value for the best move for minimax of the game tree.
    private Vector<Move> m_root;

    // Best move available for minimax.
    private Move m_minimaxBestMove;

    //----------------------- Member Methods -----------------------

     /**/
    /**
     * NAME
     *      Algo(): Default constructor for the Algo class.
     *
     * SYNOPSIS
     *      Algo();
     *
     * DESCRIPTION
     *      Default constructor for the Algo class.
     *
     * RETURNS
     *      Object of the Algo class that was just initialized.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      2 June 2018
     */
    /**/

    public Algo()
    {
        m_root = new Vector<Move>();
    }

     /**/
    /**
     * NAME
     *      initiateMiniMax(): Initializes minimax algorithm.
     *
     * SYNOPSIS
     *      initiateMiniMax(Round a_round, Player a_player, int a_plyCutoff, Boolean a_isAlphaBetaEnabled);
     *
     *      @param a_round The current Round of the Tournament to base moves off of.
     *      @param a_player The Player to recommend a move to.
     *      @param a_plyCutoff The ply cutoff for the minimax algorithm.
     *      @param a_isAlphaBetaEnabled Boolean value indicating if alpha-beta pruning is enabled.
     *
     * DESCRIPTION
     *      To initiate the minimax function for a given player to recommend a move.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      2 June 2018
     */
    /**/

    public void initiateMiniMax(Round a_round, Player a_player, int a_plyCutoff, Boolean a_isAlphaBetaEnabled)
    {
        m_root.clear();

        if (a_player.getPlayerStoneColor() == Stone.m_WHITE_STONE)
        {
            whitePlayerMiniMax(a_round, 0, a_player, Integer.MIN_VALUE, Integer.MAX_VALUE, a_plyCutoff, a_isAlphaBetaEnabled);
        }
        else
        {
            blackPlayerMinimax(a_round, 0, a_player, Integer.MIN_VALUE , Integer.MAX_VALUE, a_plyCutoff, a_isAlphaBetaEnabled);
        }

        m_minimaxBestMove = getBestMove();
    }

     /**/
    /**
     * NAME
     *      getMinimaxBestMove(): To get the best move recommended by the minimax algorithm.
     *
     * SYNOPSIS
     *      getMinimaxBestMove();
     *
     * DESCRIPTION
     *      To get the best move as recommended by the minimax algorithm.
     *
     * RETURNS
     *      Object of the Move class representing the best available move to the given player.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      2 June 2018
     */
    /**/

    public Move getMinimaxBestMove()
    {
        return m_minimaxBestMove;
    }

    /**/
    /**
     * NAME
     *      getBestMove(): To find the best move for the minimax algorithm.
     *
     * SYNOPSIS
     *      getBestMove();
     *
     * DESCRIPTION
     *      To find the best move for the minimax algorithm.
     *
     * RETURNS
     *      Object of the Move class representing the best move found by the minimax algorithm.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      2 June 2018
     */
    /**/

    private Move getBestMove()
    {
        if (m_root.isEmpty())
        {
            return null;
        }

        Move baseMove = m_root.get(0);
        int maxScore = baseMove.getScore();

        for(Move m : m_root)
        {
            if(m.getScore() > maxScore)
            {
                baseMove = m;
                maxScore = m.getScore();
            }
        }
        return baseMove;
    }

    /**/
    /**
     * NAME
     *      getHeuristicValue(): To determine the heuristic value for the minimax algorithm.
     *
     * SYNOPSIS
     *      getHeuristicValue(Player a_player, Round a_round);
     *
     *      @param a_player The Player to run the minimax algorithm based off of.
     *      @param a_round The Round to base the minimax algorithm based off of.
     *
     * DESCRIPTION
     *      To get the Heuristic value for the minimax algorithm. For the player using black stones
     *      the heuristic value is the number of black stones on the board plus the number of clear
     *      stones on the board as clear stones count for both players. For the player using white
     *      stones the heuristic value is the number of white stones plus the number of clear stones as clear
     *      stones count for both players.
     *
     * RETURNS
     *      Integer value representing the heuristic value for the minimax algorithm.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      2 June 2018
     */
    /**/

    private int getHeuristicValue(Player a_player, Round a_round)
    {
        Board board = a_round.getBoard();

        int numWhiteStones = 0;
        int numBlackStones = 0;
        int numClearStones = 0;

        for (int row = 0; row < Board.M_MAX_ROWS; row++)
        {
            for (int col = 0; col < board.getNumColsForRow(row); col++)
            {
                Position positionToCheck = new Position(row, col);

                if (board.getPositionAtCoordinates(positionToCheck).getStone().getStoneColor() == Stone.m_WHITE_STONE)
                {
                    numWhiteStones++;
                }
                else if (board.getPositionAtCoordinates(positionToCheck).getStone().getStoneColor() == Stone.m_BLACK_STONE)
                {
                    numBlackStones++;
                }
                else if(board.getPositionAtCoordinates(positionToCheck).getStone().getStoneColor() == Stone.m_CLEAR_STONE)
                {
                    numClearStones++;
                }
            }
        }

        if (a_player.getPlayerStoneColor() == Stone.m_BLACK_STONE)
        {
            return numWhiteStones + numClearStones;
        }
        else
        {
            return numBlackStones + numClearStones;
        }
    }

    /**/
    /**
     * NAME
     *      getAllPossibleMoves(): To find all possible moves that can be made by a player.
     *
     * SYNOPSIS
     *      getAllPossibleMoves(char a_color, Board a_board);
     *
     *      @param a_color The color of stone to make a move based on.
     *      @param a_board The board to make a move based on.
     *      @param a_player The player to find all possible moves for.
     *
     * DESCRIPTION
     *      To find all possible moves for a given board. First checks if a move can be made from the
     *      previous row or column. If a move can be made from the previous move or row, check all
     *      possible positions in that row or column if a move can be made. If a move cannot be made
     *      from the previous row or column, traverse the board in its entirety and find all possible
     *      moves.
     *
     * RETURNS
     *      A Vector of Move objects representing all the various moves that can be made based on the
     *      given board.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      3 June 2018
     */
    /**/

    public Vector<Move> getAllPossibleMoves(char a_color, Board a_board, Player a_player)
    {
        Vector<Position> possiblePositions = new Vector<>();

        Vector<Move> possibleMoves = new Vector<>();

        // Find all the possible positions you can make a move from.
        // If the previous row and column can not be played...
        if(!a_board.canMakePlayFromPrevious())
        {
            for (int row = 0; row < Board.M_MAX_ROWS; row++)
            {
                for (int col = 0; col < a_board.getNumColsForRow(row); col++)
                {
                    if (a_board.getPositionAtCoordinates(new Position(row, col)).getStone().getStoneColor() == Stone.m_OPEN_POCKET)
                    {
                        Position possibleMove = new Position(row, col);

                        possiblePositions.add(possibleMove);
                    }
                }
            }
        }

        // If the previous row and column can be played.
        else
        {
            // Go through every row...
            for(int row = 0; row < Board.M_MAX_ROWS; row++)
            {
                // Find the board column value of the last move...
                int col = a_board.getCorrespondingColValue(row);

                // Ensure the position is in the board...
                if(col < a_board.getNumColsForRow(row) && col >= 0)
                {
                    Position possibleMove = new Position(row, col);

                    // Check to see if it is open.
                    if(a_board.getPositionAtCoordinates(possibleMove).getStone().getStoneColor() == Stone.m_OPEN_POCKET)
                    {
                        possiblePositions.add(possibleMove);
                    }
                }
            }

            // Go through every column of the last move's row...
            for(int col = 0; col < a_board.getNumColsForRow(a_board.getPreviousMoveRow()); col++)
            {
                Position possibleMove = new Position(a_board.getPreviousMoveRow(), col);

                // Check if the position is available to move to.
                if(a_board.getPositionAtCoordinates(possibleMove).getStone().getStoneColor() == Stone.m_OPEN_POCKET)
                {
                    possiblePositions.add(possibleMove);
                }
            }
        }

        // Now combine all the possible moves into one cumulative list...
        for(int i = 0; i < possiblePositions.size(); i++)
        {
            Position position = possiblePositions.elementAt(i);

            if(a_player.getHand().getAvailableWhiteStones().size() > 0)
            {
                Position whitePosition = new Position(position.getRowPosition(), position.getColPosition(), Stone.m_WHITE_STONE);
                Move whiteMove = new Move(whitePosition, a_board.calculateScoreForPosition(whitePosition));

                if(a_player.getPlayerStoneColor() == Stone.m_WHITE_STONE)
                {
                    whiteMove.setScore(whiteMove.getScore() + 1);
                }

                possibleMoves.add(whiteMove);
            }

            if(a_player.getHand().getAvailableBlackStones().size() > 0)
            {
                Position blackPosition = new Position(position.getRowPosition(), position.getColPosition(), Stone.m_BLACK_STONE);
                Move blackMove = new Move(blackPosition, a_board.calculateScoreForPosition(blackPosition));

                if(a_player.getPlayerStoneColor() == Stone.m_BLACK_STONE)
                {
                    blackMove.setScore(blackMove.getScore() + 1);
                }

                possibleMoves.add(blackMove);
            }

            if(a_player.getHand().getAvailableClearStones().size() > 0)
            {
                Position clearPosition = new Position(position.getRowPosition(), position.getColPosition(), Stone.m_CLEAR_STONE);
                Move clearMove = new Move(clearPosition, a_board.calculateScoreForPosition(clearPosition));
                possibleMoves.add(clearMove);
            }
        }

        return possibleMoves;
    }

     /**/
    /**
     * NAME
     *      whitePlayerMiniMax(): Executes the minimax algorithm for the Player using white stones.
     *
     * SYNOPSIS
     *      whitePlayerMiniMax(Round a_round, int a_depth, Player a_player, int a_alpha, int a_beta, int a_plyCutoff, Boolean a_isAlphaBetaEnabled);
     *
     *      @param a_round The current round of the Tournament to base the algorithm on.
     *      @param a_depth The depth of the game tree for the algorithm.
     *      @param a_player The Player to base the algorithm on.
     *      @param a_alpha The alpha value for the algorithm.
     *      @param a_beta The beta value for the algorithm.
     *      @param a_plyCutoff The ply cutoff to be used with the algorithm.
     *      @param a_isAlphaBetaEnabled Boolean value indicating if alpha beta pruning is enabled.
     *
     * DESCRIPTION
     *      To execute the minimax algorithm for the Player using white stones.
     *
     * RETURNS
     *      Integer value representing the score of the best available move to the Player using white
     *      stones.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      2 June 2018
     */
    /**/

    private int whitePlayerMiniMax(Round a_round, int a_depth, Player a_player, int a_alpha, int a_beta, int a_plyCutoff, Boolean a_isAlphaBetaEnabled)
    {
        Board board = a_round.getBoard();
        Player whitePlayer = a_round.getWhitePlayer();
        Player blackPlayer = a_round.getBlackPlayer();

        if ((whitePlayer.getHand().isHandEmpty() && blackPlayer.getHand().isHandEmpty()) || a_depth > a_plyCutoff)
        {
            return getHeuristicValue(whitePlayer, a_round);
        }

        Vector<Move> moves = getAllPossibleMoves(a_player.getPlayerStoneColor(), a_round.getBoard(), a_player);

        if (moves.isEmpty())
        {
            return getHeuristicValue(whitePlayer, a_round);
        }

        ArrayList<Integer> scores = new ArrayList<>();

        Board savedBoard = new Board();

        if (a_plyCutoff >= a_depth)
        {
            savedBoard = makeBoardCopy(board);
        }

        for (int i = 0; i < moves.size(); i++)
        {
            Move move = moves.get(i);

            if (a_player.getPlayerStoneColor() == (Stone.m_WHITE_STONE))
            {
                makeMoveForMinimax(move, board);

                int currentScore = whitePlayerMiniMax(a_round, a_depth + 1, blackPlayer, a_alpha, a_beta, a_plyCutoff, a_isAlphaBetaEnabled);
                scores.add(currentScore);

                if (a_isAlphaBetaEnabled)
                {
                    if (move.getMinimaxValue() > a_alpha)
                    {
                        a_alpha = move.getMinimaxValue();
                    }

                    if (a_beta <= a_alpha)
                    {
                        break;
                    }
                }

                if (a_depth == 0)
                {
                    move.setMinimaxValue(currentScore);
                    m_root.add(move);
                }
            }
            else if (a_player.getPlayerStoneColor() == (Stone.m_BLACK_STONE))
            {
                makeMoveForMinimax(move, board);

                int currentScore = whitePlayerMiniMax(a_round, a_depth + 1, whitePlayer, a_alpha, a_beta, a_plyCutoff, a_isAlphaBetaEnabled);
                scores.add(currentScore);

                if (a_isAlphaBetaEnabled)
                {
                    if (move.getMinimaxValue() < a_beta)
                    {
                        a_beta = move.getMinimaxValue();
                    }

                    if (a_alpha >= a_beta)
                    {
                        break;
                    }
                }
            }

            resetBoard(savedBoard, board);
        }

        if (a_player.getPlayerStoneColor() == (Stone.m_WHITE_STONE))
        {
            return Collections.max(scores);
        }

        return Collections.min(scores);
    }

    /**/
    /**
     * NAME
     *      blackPlayerMiniMax(): Executes the minimax algorithm for the Player using white stones.
     *
     * SYNOPSIS
     *      blackPlayerMiniMax(Round a_round, int a_depth, Player a_player, int a_alpha, int a_beta, int a_plyCutoff, Boolean a_isAlphaBetaEnabled);
     *
     *      @param a_round The current round of the Tournament to base the algorithm on.
     *      @param a_depth The depth of the game tree for the algorithm.
     *      @param a_player The Player to base the algorithm on.
     *      @param a_alpha The alpha value for the algorithm.
     *      @param a_beta The beta value for the algorithm.
     *      @param a_plyCutoff The ply cutoff to be used with the algorithm.
     *      @param a_isAlphaBetaEnabled Boolean value indicating if alpha beta pruning is enabled.
     *
     * DESCRIPTION
     *      To execute the minimax algorithm for the Player using black stones.
     *
     * RETURNS
     *      Integer value representing the score of the best available move to the Player using black
     *      stones.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      2 June 2018
     */
    /**/

    private int blackPlayerMinimax(Round a_round, int a_depth, Player a_player, int a_alpha, int a_beta, int a_plyCutoff, Boolean a_isAlphaBetaEnabled)
    {
        Board board = a_round.getBoard();
        Player whitePlayer = a_round.getWhitePlayer();
        Player blackPlayer = a_round.getBlackPlayer();

        if ((!blackPlayer.getHand().isHandEmpty() && !whitePlayer.getHand().isHandEmpty()) || a_depth > a_plyCutoff)
        {
            return getHeuristicValue(blackPlayer, a_round);
        }

        Vector<Move> moves = getAllPossibleMoves(a_player.getPlayerStoneColor(), board, a_player);

        if (moves.isEmpty())
        {
            return getHeuristicValue(blackPlayer, a_round);
        }

        ArrayList<Integer> scores = new ArrayList<>();

        Board savedBoard = new Board();

        if (a_plyCutoff >= a_depth)
        {
            savedBoard = makeBoardCopy(board);
        }

        for (int i = 0; i < moves.size(); i++)
        {
            Move move = moves.get(i);

            if (a_player.getPlayerStoneColor() == (Stone.m_BLACK_STONE))
            {
                makeMoveForMinimax(move, board);

                int currentScore = blackPlayerMinimax(a_round,a_depth + 1, whitePlayer, a_alpha, a_beta, a_plyCutoff, a_isAlphaBetaEnabled);
                scores.add(currentScore);

                if (a_isAlphaBetaEnabled)
                {
                    if (move.getMinimaxValue() > a_alpha)
                    {
                        a_alpha = move.getMinimaxValue();
                    }

                    if (a_beta <= a_alpha)
                    {
                        break;
                    }
                }

                if (a_depth == 0)
                {
                    move.setMinimaxValue(currentScore);
                    m_root.add(move);
                }
            }
            else if (a_player.getPlayerStoneColor() == (Stone.m_WHITE_STONE))
            {
                makeMoveForMinimax(move, board);

                int currentScore = blackPlayerMinimax(a_round, a_depth + 1, blackPlayer, a_alpha, a_beta, a_plyCutoff, a_isAlphaBetaEnabled);
                scores.add(currentScore);

                if (a_isAlphaBetaEnabled)
                {
                    if (move.getMinimaxValue() < a_beta)
                    {
                        a_beta = move.getMinimaxValue();
                    }

                    if (a_alpha >= a_beta)
                    {
                        break;
                    }
                }
            }
            resetBoard(savedBoard, board);
        }

        if (a_player.getPlayerStoneColor() == (Stone.m_BLACK_STONE))
        {
            return Collections.max(scores);
        }

        return Collections.min(scores);
    }

    /**/
    /**
     * NAME
     *      makeMoveForMinimax(): To make a move for the minimax algorithm.
     *
     * SYNOPSIS
     *      makeMoveForMinimax(Move a_move, Board a_board);
     *
     *      @param a_move The Move to make.
     *      @param a_board The Board to make the given move on.
     *
     * DESCRIPTION
     *      To modify the board with a move recommended by the minimax algorithm.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      2 June 2018
     */
    /**/

    private void makeMoveForMinimax(Move a_move, Board a_board)
    {
        Position positionToModify = a_move.getPosition();

        a_board.setPositionAtCoordinates(positionToModify);
    }

     /**/
    /**
     * NAME
     *      resetBoard(): To reset the board for minimax algorithm.
     *
     * SYNOPSIS
     *      resetBoard(Board a_board, Board a_boardToRestore);
     *
     *      @param a_savedBoard The board which to restore from.
     *      @param a_boardToRestore The board which to restore.
     *
     * DESCRIPTION
     *      To reset a board.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      2 June 2018
     */
    /**/

    private void resetBoard(Board a_savedBoard, Board a_boardToRestore)
    {
        for(Vector <Position> boardRow : a_savedBoard.getM_board())
        {
            for(Position position : boardRow)
            {
                a_boardToRestore.setPositionAtCoordinates(a_savedBoard.getPositionAtCoordinates(position));
            }
        }
    }

    /**/
    /**
     * NAME
     *      makeBoardCopy(): To make a copy of a given board.
     *
     * SYNOPSIS
     *      makeBoardCopy(Board a_board)
     *
     *      @param a_board The board to make a copy of.
     *
     * DESCRIPTION
     *      To make a copy of the board for the minimax algorithm.
     *
     * RETURNS
     *      Object of the Board class representing the copied board.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      2 June 2018
     */
    /**/

    private Board makeBoardCopy(Board a_board)
    {
        Board copyBoard = new Board();

        // Make a deep copy of the board.
        for(Vector <Position> boardRow : a_board.getM_board())
        {
            for(Position position : boardRow)
            {
                copyBoard.setPositionAtCoordinates(position);
            }
        }

        return copyBoard;
    }
}
