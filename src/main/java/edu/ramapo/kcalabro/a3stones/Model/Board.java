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

import java.util.Vector;

public class Board
{
    //----------------------- Data Members -----------------------

    // The maximum number of rows in the board.
    public final static int M_MAX_ROWS = 11;

    // The maximum number of columns in the board.
    public final static int M_MAX_COLS = 11;

    // The actual board itself.
    private Vector<Vector<Position>> m_board = new Vector<>();

    // Boolean flag used to indicate if a player can not place a tile in the previous play's column or row.
    private Boolean m_freePlacement;

    // Boolean flag used to indicate if the first play of a round has been made.
    private Boolean m_hasFirstPlayBeenMade;

    // The row coordinate of the last move made.
    private int m_previousMoveRow;

    // The column coordinate of the last move made.
    private int m_previousMoveCol;

    // The score of the player using white stones.
    private int m_whiteScore;

    // The score of the player using black stones.
    private int m_blackScore;

    //----------------------- Member Methods -----------------------

    /**/
    /**
     * NAME
     *      Board(): Default constructor for the Board class.
     *
     * SYNOPSIS
     *      Board();
     *
     * DESCRIPTION
     *      Default constructor for the Board class. Initializes the 11x11 board
     *      with all empty positions to begin the round.
     *
     * RETURNS
     *      Object of the Board class that was just initialized.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public Board()
    {
        initializeBoard();
        m_freePlacement = true;
        m_hasFirstPlayBeenMade = false;

        m_blackScore = 0;
        m_whiteScore = 0;
    }

    /**/
    /**
     * NAME
     *      initializeBoard(): Initializes the 11x11 board with vacant pockets.
     *
     * SYNOPSIS
     *      initializeBoard();
     *
     * DESCRIPTION
     *      To initialize the 11x11 board with all vacant positions to begin a new round.
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

    public void initializeBoard()
    {
        int col = 0;

        // Add each new row to the board itself.
        for (int row = 0; row < M_MAX_ROWS; row++)
        {
            int temp = row;

            if (row == 5)
            {
                col = 11;
            }
            if (row > 5)
            {
                temp = 10 - row;
            }
            if(row != 5)
            {
                col = temp * 2 + 3;
            }

            Vector<Position> rowToAdd = new Vector<Position>();

            // Create actual position that coordinate with the board.
            for (int colCount = 0; colCount < col; colCount++)
            {
                Position positionToAdd = new Position(row, colCount, Stone.m_OPEN_POCKET);
                rowToAdd.add(positionToAdd);
            }

            m_board.add(rowToAdd);
        }

        // At row 5, column 5 there is a pocket that cannot be played by either player.
        setPositionAtCoordinates(new Position(5, 5, Stone.m_NON_EXISTENT));
    }

    /**/
    /**
     * NAME
     *      isValidMove(): Determines if a given move is valid.
     *
     * SYNOPSIS
     *      isValidMove(Position a_positionToValidate);
     *
     *      @param a_positionToValidate The position of the board to validate a move to. The position
     *                                  also contains the color of stone to be placed on the board.
     *
     * DESCRIPTION
     *      To determine if a given move is valid by either player. A valid move is one in which
     *      the user has selected the row or column coordinate of their opponent's previous move.
     *      The FIRST move of the game however may be made on any position of the board.
     *
     * RETURNS
     *      Boolean value indicating if the given move was valid.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      5 March 2018
     */
    /**/

    public Boolean isValidMove(Position a_positionToValidate)
    {
        // The first play of a round has different rules from proceeding plays.
        if(!m_hasFirstPlayBeenMade)
        {
            if(isValidFreePlacementMove(a_positionToValidate))
            {
                m_hasFirstPlayBeenMade = true;
                m_freePlacement = false;
                return true;
            }
            return false;
        }

        else if(getPositionAtCoordinates(a_positionToValidate).getStone().getStoneColor() == Stone.m_OPEN_POCKET)
        {
            if(canMakePlayFromPrevious())
            {
                if (a_positionToValidate.getRowPosition() == m_previousMoveRow)
                {
                    return true;
                }
                if (a_positionToValidate.getColPosition() == getCorrespondingColValue(a_positionToValidate.getRowPosition()))
                {
                    return true;
                }
            }
            else
            {
                return isValidFreePlacementMove(a_positionToValidate);
            }
        }

        return false;
    }

     /**/
    /**
     * NAME
     *      isValidFreePlacementMove(): Determines if a given first move of the game is valid.
     *
     * SYNOPSIS
     *      isValidFreePlacementMove(Position a_positionToValidate);
     *
     *      @param a_positionToValidate The position of the board to validate a first move to. The position
     *                                  also contains the color of stone to be placed on the board.
     *
     * DESCRIPTION
     *      To determine if a given first move is valid by either player. A valid first move is one in which
     *      a stone may be placed on any open position of the board.
     *
     * RETURNS
     *      Boolean value indicating if the given first move was valid.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      6 March 2018
     */
    /**/

    private Boolean isValidFreePlacementMove(Position a_positionToValidate)
    {
        // For the first move, a stone may be placed in any open pocket of the board.
        if(getPositionAtCoordinates(a_positionToValidate).getStone().getStoneColor() == Stone.m_OPEN_POCKET)
        {
            return true;
        }
        return false;
    }

     /**/
    /**
     * NAME
     *      canMakePlayFromPrevious(): Determines if a a move can be made in the row or column of the
     *      previous move made.
     *
     * SYNOPSIS
     *      canMakePlayFromPrevious();
     *
     *
     * DESCRIPTION
     *      To determine if a move can be made in the previous move's row or column. If a move cannot
     *      be made in the previous move's row or column, the player is free to place a stone in any
     *      open pocket on the board.
     *
     * RETURNS
     *      Boolean:
     *          True -> If a move can be made from the previous move's row or column.
     *          False -> If a move cannot be made from the previous move's row or column.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      5 March 2018
     */
    /**/

    public Boolean canMakePlayFromPrevious()
    {
        if(!m_hasFirstPlayBeenMade)
        {
            return false;
        }

        // Go through every row...
        for(int row = 0; row < M_MAX_ROWS; row++)
        {
            // Find the board column value of the last move...
            int col = getCorrespondingColValue(row);

            // Ensure the position is in the board...
            if(col < getNumColsForRow(row) && col >= 0)
            {
                Position possibleMove = new Position(row, col);

                // Check to see if it is open.
                if (getPositionAtCoordinates(possibleMove).getStone().getStoneColor() == Stone.m_OPEN_POCKET)
                {
                    m_freePlacement = false;
                    return true;
                }
            }
        }

        // Go through every column of the last move's row...
        for(int col = 0; col < getNumColsForRow(m_previousMoveRow); col++)
        {
            Position possibleMove = new Position(m_previousMoveRow, col);

            // Check if the position is available to move to.
            if(getPositionAtCoordinates(possibleMove).getStone().getStoneColor() == Stone.m_OPEN_POCKET)
            {
                m_freePlacement = false;
                return true;
            }
        }

        m_freePlacement = true;
        return false;
    }

     /**/
    /**
     * NAME
     *      modifyBoard(): To modify the board at a given position, with a provided stone.
     *
     * SYNOPSIS
     *      modifyBoard();
     *
     *      @param a_positionToModify The position which to modify the board at.
     *      @param a_stoneToPlay The stone which to place on the board at the given position.
     *
     * DESCRIPTION
     *      To modify the board at a given position with a given stone.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      6 March 2018
     */
    /**/

    public void modifyBoard(Position a_positionToModify, Stone a_stoneToPlay)
    {
        // Modify the board itself.
        a_positionToModify.setStone(a_stoneToPlay);
        setPositionAtCoordinates(a_positionToModify);

        // Keep track of the column and row position of the last play.
        setPreviousMoveRow(a_positionToModify.getRowPosition());
        setPreviousMoveCol(a_positionToModify.getColPosition());

        setBlackScore(calculateScores(Stone.m_BLACK_STONE));
        setWhiteScore(calculateScores(Stone.m_WHITE_STONE));
    }

    /**/
    /**
     * NAME
     *      getCorrespondingColValue(): To get the column value of a position in terms of the octagonal board.
     *
     * SYNOPSIS
     *      getCorrespondingColValue(a_desiredRow);
     *
     *      @param a_desiredRow The row coordinate of the user's selected board position to make a move to.
     *
     * DESCRIPTION
     *      This function provides the ability for users to make moves in the same board column of their
     *      opponent's previous move. Due to the nature of an octagonal board, a previous position of
     *      10,1 (row, col) does not translate into a new position of 9,1 properly, they represent
     *      two different columns of the actual board.
     *
     * RETURNS
     *      Integer value representing the actual board column value of the new play.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      5 March 2018
     */
    /**/

    public int getCorrespondingColValue(int a_desiredRow)
    {
        // If the number of columns are the same for both the new and previous rows,
        // The column positions will be the same.
        if(getNumColsForRow(a_desiredRow) == getNumColsForRow(m_previousMoveRow))
        {
            return m_previousMoveCol;
        }
        // Otherwise, calculate the difference in the column positions.
        else
        {
            return (((getNumColsForRow(a_desiredRow) - getNumColsForRow(m_previousMoveRow)) / 2) + m_previousMoveCol);
        }
    }

    /**/
    /**
     * NAME
     *      getCorrespondingColValueScoring(): To get the column value of a position in terms of the octagonal board.
     *
     * SYNOPSIS
     *      getCorrespondingColValueScoring(a_baseRow, a_desiredRow, a_baseColumn);
     *
     *      @param a_baseRow The base row from which to originate from.
     *      @param a_desiredRow The row coordinate of the desired new row.
     *      @param a_baseColumn The base column to originate from.
     *
     * DESCRIPTION
     *      This function provides the ability to determine positions in rows above or below the given base row
     *      as the column numbers will not match up due to the nature of the octagonal board.
     *
     * RETURNS
     *      Integer value representing the actual board column value of the desired row in relation to
     *      the given base column and row values.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      21 August 2018
     */
    /**/

    private int getCorrespondingColValueScoring(int a_baseRow, int a_desiredRow, int a_baseColumn)
    {
        if(getNumColsForRow(a_desiredRow) == getNumColsForRow(a_baseRow))
        {
            return a_baseColumn;
        }
        else
        {
            return (((getNumColsForRow(a_desiredRow) - getNumColsForRow(a_baseRow)) / 2) + a_baseColumn);
        }
    }

    /**/
    /**
     * NAME
     *      getNumColsForRow(): To get the number of columns for a given row.
     *
     * SYNOPSIS
     *      getNumColsForRow(int a_desiredRow);
     *
     *      @param a_desiredRow The row coordinate of the user's selected board position to make a move to.
     *
     * DESCRIPTION
     *      This function returns the number of columns that are found within a given row of the octagonal
     *      board.
     *
     * RETURNS
     *      Integer value representing the number of columns in the given row.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    public int getNumColsForRow(int a_desiredRow)
    {
        switch(a_desiredRow)
        {
            case 0:
                return 3;
            case 1:
                return 5;
            case 2:
                return 7;
            case 3:
                return 9;
            case 7:
                return 9;
            case 8:
                return 7;
            case 9:
                return 5;
            case 10:
                return 3;
            default:
                return 11;
        }
    }

    /**/
    /**
     * NAME
     *      setBlackScore(): To set the score for black stones.
     *
     * SYNOPSIS
     *      setBlackScore(int a_blackScore);
     *
     *      @param a_blackScore The value to set the black score to.
     *
     * DESCRIPTION
     *      This function sets the score for the player using black stones.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      5 March 2018
     */
    /**/

    private void setBlackScore(int a_blackScore)
    {
        this.m_blackScore = a_blackScore;
    }

     /**/
    /**
     * NAME
     *      setWhiteScore(): To set the score for white stones.
     *
     * SYNOPSIS
     *      setWhiteScore(int a_whiteScore);
     *
     *      @param a_whiteScore The value to set the white score to.
     *
     * DESCRIPTION
     *      This function sets the score for the player using white stones.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      5 March 2018
     */
    /**/

    private void setWhiteScore(int a_whiteScore)
    {
        this.m_whiteScore = a_whiteScore;
    }

    /**/
    /**
     * NAME
     *      getWhiteScore(): To get the score for white stones.
     *
     * SYNOPSIS
     *      getWhiteScore();
     *
     * DESCRIPTION
     *      This function gets the score for the player using white stones.
     *
     * RETURNS
     *      Integer value representing the score for white stones.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      5 March 2018
     */
    /**/

    public int getWhiteScore()
    {
        return m_whiteScore;
    }

    /**/
    /**
     * NAME
     *      getBlackScore(): To get the score for black stones.
     *
     * SYNOPSIS
     *      getBlackScore();
     *
     * DESCRIPTION
     *      This function gets the score for the player using black stones.
     *
     * RETURNS
     *      Integer value representing the score for black stones.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      5 March 2018
     */
    /**/

    public int getBlackScore()
    {
        return m_blackScore;
    }

     /**/
    /**
     * NAME
     *      setFreePlacement(): To set the m_freePlacement Boolean flag.
     *
     * SYNOPSIS
     *      setFreePlacement(Boolean a_playMade);
     *
     *      @param a_playMade The Boolean variable to set the m_freePlacement flag to.
     *
     * DESCRIPTION
     *      To set the m_freePlacement Boolean flag. On the first play of a round, a stone
     *      may be placed anywhere on the board. From then on, stones must be placed in the same row
     *      or column as the opponents last stone placement. If a stone cannot be placed in the same
     *      row or columns as the last move, a player can place a stone in any open pocket.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      5 March 2018
     */
    /**/

    public void setFreePlacement(Boolean a_playMade)
    {
        this.m_freePlacement = a_playMade;
    }

     /**/
    /**
     * NAME
     *      getFreePlacement(): To get the m_freePlacement Boolean flag.
     *
     * SYNOPSIS
     *      getFreePlacement();
     *
     * DESCRIPTION
     *      To get the m_freePlacement Boolean flag. On the first play of a round, a stone
     *      may be placed anywhere on the board. From then on, stones must be placed in the same row
     *      or column as the opponents last stone placement. If a stone cannot be placed in the same
     *      row or columns as the last move, a player can place a stone in any open pocket.
     *
     * RETURNS
     *      Boolean value indicating if free placement is allowed.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      5 March 2018
     */
    /**/

    public Boolean getFreePlacement()
    {
        return m_freePlacement;
    }

    /**/
    /**
     * NAME
     *      getHasFirstPlayBeenMade(): To get the m_hasFirstPlayBeenMade flag.
     *
     * SYNOPSIS
     *      getHasFirstPlayBeenMade();
     *
     * DESCRIPTION
     *      To get the m_hasFirstPlayBeenMade Boolean flag.
     *
     * RETURNS
     *      Boolean value indicating if the first play of a round has been made.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      5 March 2018
     */
    /**/

    public Boolean getHasFirstPlayBeenMade()
    {
        return m_hasFirstPlayBeenMade;
    }

    /**/
    /**
     * NAME
     *      setHasFirstPlayBeenMade(): To set the m_hasFirstPlayBeenMade flag.
     *
     * SYNOPSIS
     *      setHasFirstPlayBeenMade();
     *
     *      @param a_hasFirstPlayBeenMade Boolean value to set the m_hasFirstPlayBeenMade flag to.
     *
     * DESCRIPTION
     *      To set the m_hasFirstPlayBeenMade Boolean flag.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      5 March 2018
     */
    /**/

    public void setHasFirstPlayBeenMade(Boolean a_hasFirstPlayBeenMade)
    {
        this.m_hasFirstPlayBeenMade = a_hasFirstPlayBeenMade;
    }

    /**/
    /**
     * NAME
     *      getPreviousMoveRow(): To return the row coordinate of the previous move.
     *
     * SYNOPSIS
     *      getPreviousMoveRow();
     *
     * DESCRIPTION
     *      To return the row coordinate of the previous move.
     *
     * RETURNS
     *      An integer value representing the row coordinate of the previous move.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      5 March 2018
     */
    /**/

    public int getPreviousMoveRow()
    {
        return m_previousMoveRow;
    }

    /**/
    /**
     * NAME
     *      getPreviousMoveCol(): To return the column coordinate of the previous move.
     *
     * SYNOPSIS
     *      getPreviousMoveRow();
     *
     * DESCRIPTION
     *      To return the column coordinate of the previous move.
     *
     * RETURNS
     *      An integer value representing the column coordinate of the previous move.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      5 March 2018
     */
    /**/

    public int getPreviousMoveCol()
    {
        return m_previousMoveCol;
    }

    /**/
    /**
     * NAME
     *      getM_board(): To return the current board.
     *
     * SYNOPSIS
     *      getM_board();
     *
     * DESCRIPTION
     *      To return the current board being used in a round.
     *
     * RETURNS
     *      A vector of vectors representing the current board.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      6 March 2018
     */
    /**/

    public Vector<Vector<Position>> getM_board()
    {
        return m_board;
    }

    /**/
    /**
     * NAME
     *      getRow(): To get the vector representing a given row number.
     *
     * SYNOPSIS
     *      getRow(a_rowNumToRetrieve);
     *
     *      @param a_rowNumToRetrieve Integer value representing the row number of the board to retrieve.
     *
     * DESCRIPTION
     *      To get the vector of stones representing a given row of the board.
     *
     * RETURNS
     *      A vector of stones representing a row of the board.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      6 March 2018
     */
    /**/

    public Vector<Position> getRow(int a_rowNumToRetrieve)
    {
        return m_board.elementAt(a_rowNumToRetrieve);
    }

    /**/
    /**
     * NAME
     *      setPositionAtCoordinates(): To set the stone at a certain position of the board.
     *
     * SYNOPSIS
     *      setPositionAtCoordinates(Position a_positionToModify)
     *
     *      @param a_positionToModify The Position which to modify the board at.
     *
     * DESCRIPTION
     *      To set the stone on the board at a given position.
     *
     * RETURNS
     *      Boolean value indicating if the given position was valid and the
     *      modification was made.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      6 March 2018
     */
    /**/

    public Boolean setPositionAtCoordinates(Position a_positionToModify)
    {
        int row = a_positionToModify.getRowPosition();
        int col = a_positionToModify.getColPosition();

        if(row >= 0 && row < M_MAX_ROWS && col >= 0 && col < M_MAX_COLS)
        {
            Position positionToSet = (Position) ((Vector) m_board.get(row)).get(col);
            positionToSet.getStone().setStoneColor(a_positionToModify.getStone().getStoneColor());
            return true;
        }
        return false;
    }

     /**/
    /**
     * NAME
     *      getPositionAtCoordinates(): To get the stone at a certain position of the board.
     *
     * SYNOPSIS
     *      getPositionAtCoordinates(Position a_positionToRetrieve);
     *
     * DESCRIPTION
     *      To get the stone on the board at a given position.
     *
     * RETURNS
     *      A Position object that contains coordinates and the stone at said coordinates.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      6 March 2018
     */
    /**/

    public Position getPositionAtCoordinates(Position a_positionToRetrieve)
    {
        int row = a_positionToRetrieve.getRowPosition();
        int col = a_positionToRetrieve.getColPosition();

        Position positionToReturn = (Position) ((Vector) m_board.get(row)).get(col);
        return positionToReturn;
    }

    /**/
    /**
     * NAME
     *      setPreviousMoveRow(): To set the row coordinate of the previous move.
     *
     * SYNOPSIS
     *      setPreviousMoveRow(int a_previousMoveRow);
     *
     *      @param a_previousMoveRow The coordinate which to set the previous move's row position as.
     *
     * DESCRIPTION
     *      To set row coordinate of the previous move.
     *
     * RETURNS
     *      Boolean value indicating if the given value was valid and the modification was made.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      8 March 2018
     */
    /**/

    public Boolean setPreviousMoveRow(int a_previousMoveRow)
    {
        if(a_previousMoveRow >= 0 && a_previousMoveRow < M_MAX_ROWS)
        {
            this.m_previousMoveRow = a_previousMoveRow;
            return true;
        }
        return false;
    }

    /**/
    /**
     * NAME
     *      setPreviousMoveCol(): To set the column coordinate of the previous move.
     *
     * SYNOPSIS
     *      setPreviousMoveCol(int a_previousMoveCol);
     *
     *      @param a_previousMoveCol The coordinate which to set the previous move's column position as.
     *
     * DESCRIPTION
     *      To set the column coordinate of the previous move.
     *
     * RETURNS
     *      Boolean value indicating if the given value was valid and the modification was made.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      8 March 2018
     */
    /**/

    public Boolean setPreviousMoveCol(int a_previousMoveCol)
    {
        if(a_previousMoveCol >= 0 && a_previousMoveCol < M_MAX_COLS)
        {
            this.m_previousMoveCol = a_previousMoveCol;
            return true;
        }
        return false;
    }

    /**/
    /**
     * NAME
     *      calculateScoreForPosition(): To calculate the score for a singular given position.
     *
     * SYNOPSIS
     *      calculateScoreForPosition(Stone a_stone, Position a_basePosition);
     *
     *      @param a_basePosition The position which to calculate the score for.
     *
     * DESCRIPTION
     *      To calculate the score for a singular given position with the given stone at said position. Used
     *      mainly in conjunction with the minimax algorithm/ Algo class.
     *
     * RETURNS
     *      Integer value representing the score available to the given position.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      11 March 2018
     */
    /**/

    public int calculateScoreForPosition(Position a_basePosition)
    {
        int score = 0;

        if(isVerticalScoring(a_basePosition))
        {
            score++;
        }

        if(isHorizontalScoring(a_basePosition))
        {
            score++;
        }

        if(isDiagonalNESWScoring(a_basePosition))
        {
            score++;
        }

        if(isDiagonalSENWScoring(a_basePosition))
        {
            score++;
        }

        return score;
    }

    /**/
    /**
     * NAME
     *      calculateScores(): To traverse the board and calculate scores for both players.
     *
     * SYNOPSIS
     *      calculateScores();
     *
     * DESCRIPTION
     *      Goes through the board and calculates the scores for each player. A player earns one point
     *      for every three stone arrangement of their stone colors. Clear stones count for both players.
     *      An arrangement of three clear stones however awards points to no players.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      11 March 2018
     */
    /**/

    private int calculateScores(char a_stoneColor)
    {
        int baseScore = 0;

        for(Vector <Position> boardRow : m_board)
        {
            for(Position position : boardRow)
            {
                if(position.getStone().getStoneColor() == a_stoneColor || position.getStone().getStoneColor() == Stone.m_CLEAR_STONE)
                {
                    baseScore += calculateScoreForPosition(position);
                }
            }
        }
        return baseScore;
    }

    /**/
    /**
     * NAME
     *      isVerticalScoring(): To determine if there is a vertical scoring arrangement based on a given position.
     *
     * SYNOPSIS
     *      isVerticalScoring(Position a_positionToCheck);
     *
     *      @param a_positionToCheck The base position from which to determine if a scoring arrangement
     *                               is made vertically.
     *
     * DESCRIPTION
     *      Determines if there is a scoring arrangement vertically based on the given position.
     *
     * RETURNS
     *      Boolean:
     *          True -> If there is a scoring arrangement vertically.
     *          False -> Otherwise.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      21 August 2018
     */
    /**/

    private Boolean isVerticalScoring(Position a_positionToCheck)
    {
        int baseRow = a_positionToCheck.getRowPosition();
        int baseCol = a_positionToCheck.getColPosition();

        // One row up from the base position.
        Position north = new Position(baseRow - 1, getCorrespondingColValueScoring(baseRow, baseRow - 1, baseCol));

        // Two rows up from the base position.
        Position south = new Position(baseRow + 1, getCorrespondingColValueScoring(baseRow, baseRow + 1, baseCol));

        // First, ensure both positions are in the board.
        if(isPositionValid(north) && isPositionValid(south))
        {
            if(getPositionAtCoordinates(north).getStone().getStoneColor() == a_positionToCheck.getStone().getStoneColor() || getPositionAtCoordinates(north).getStone().getStoneColor() == Stone.m_CLEAR_STONE)
            {
                if(getPositionAtCoordinates(south).getStone().getStoneColor() == a_positionToCheck.getStone().getStoneColor() || getPositionAtCoordinates(south).getStone().getStoneColor() == Stone.m_CLEAR_STONE)
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
     *      isHorizontalScoring(): To determine if there is a horizontal scoring arrangement based on a given position.
     *
     * SYNOPSIS
     *      isHorizontalScoring(Position a_positionToCheck);
     *
     *      @param a_positionToCheck The base position from which to determine if a scoring arrangement
     *                               is made horizontally.
     *
     * DESCRIPTION
     *      Determines if there is a scoring arrangement horizontally based on the given position.
     *
     * RETURNS
     *      Boolean:
     *          True -> If there is a scoring arrangement horizontally.
     *          False -> Otherwise.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      21 August 2018
     */
    /**/

    private Boolean isHorizontalScoring(Position a_positionToCheck)
    {
        int baseRow = a_positionToCheck.getRowPosition();
        int baseCol = a_positionToCheck.getColPosition();

        // One row up from the base position.
        Position east = new Position(baseRow, baseCol + 1);

        // Two rows up from the base position.
        Position west = new Position(baseRow, baseCol - 1);

        // First, ensure both positions are in the board.
        if(isPositionValid(east) && isPositionValid(west))
        {
            if(getPositionAtCoordinates(east).getStone().getStoneColor() == a_positionToCheck.getStone().getStoneColor() || getPositionAtCoordinates(east).getStone().getStoneColor() == Stone.m_CLEAR_STONE)
            {
                if(getPositionAtCoordinates(west).getStone().getStoneColor() == a_positionToCheck.getStone().getStoneColor() || getPositionAtCoordinates(west).getStone().getStoneColor() == Stone.m_CLEAR_STONE)
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
     *      isDiagonalNESWScoring(): To determine if there is a diagonal scoring arrangement running
     *      from the northeast to the southwest based on a given position.
     *
     * SYNOPSIS
     *      isDiagonalNESWScoring(Position a_positionToCheck);
     *
     *      @param a_positionToCheck The base position from which to determine if a scoring arrangement
     *                               is made from the northeast to southwest.
     *
     * DESCRIPTION
     *      Determines if there is a scoring arrangement diagonally based on the given position.
     *
     * RETURNS
     *      Boolean:
     *          True -> If there is a scoring arrangement diagonally.
     *          False -> Otherwise.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      21 August 2018
     */
    /**/

    private Boolean isDiagonalNESWScoring(Position a_positionToCheck)
    {
        int baseRow = a_positionToCheck.getRowPosition();
        int baseCol = a_positionToCheck.getColPosition();

        // One row up from the base position.
        Position northEast = new Position(baseRow - 1, getCorrespondingColValueScoring(baseRow, baseRow - 1, baseCol) + 1);

        // Two rows up from the base position.
        Position southWest = new Position(baseRow + 1, getCorrespondingColValueScoring(baseRow, baseRow + 1, baseCol) - 1);

        // First, ensure both positions are in the board.
        if(isPositionValid(northEast) && isPositionValid(southWest))
        {
            if(getPositionAtCoordinates(northEast).getStone().getStoneColor() == a_positionToCheck.getStone().getStoneColor() || getPositionAtCoordinates(northEast).getStone().getStoneColor() == Stone.m_CLEAR_STONE)
            {
                if(getPositionAtCoordinates(southWest).getStone().getStoneColor() == a_positionToCheck.getStone().getStoneColor() || getPositionAtCoordinates(southWest).getStone().getStoneColor() == Stone.m_CLEAR_STONE)
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
     *      isDiagonalSENWScoring(): To determine if there is a diagonal scoring arrangement running
     *      from the southeast to the northwest based on a given position.
     *
     * SYNOPSIS
     *      isDiagonalSENWScoring(Position a_positionToCheck);
     *
     *      @param a_positionToCheck The base position from which to determine if a scoring arrangement
     *                               is made diagonally from southeast to northwest.
     *
     * DESCRIPTION
     *      Determines if there is a scoring arrangement diagonally based on the given position.
     *
     * RETURNS
     *      Boolean:
     *          True -> If there is a scoring arrangement diagonally.
     *          False -> Otherwise.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      21 August 2018
     */
    /**/

    private Boolean isDiagonalSENWScoring(Position a_positionToCheck)
    {
        int baseRow = a_positionToCheck.getRowPosition();
        int baseCol = a_positionToCheck.getColPosition();

        // One row up from the base position.
        Position northWest = new Position(baseRow - 1, getCorrespondingColValueScoring(baseRow, baseRow - 1, baseCol) - 1);

        // Two rows up from the base position.
        Position southEast = new Position(baseRow + 1, getCorrespondingColValueScoring(baseRow, baseRow + 1, baseCol) + 1);

        // First, ensure both positions are in the board.
        if(isPositionValid(northWest) && isPositionValid(southEast))
        {
            if(getPositionAtCoordinates(northWest).getStone().getStoneColor() == a_positionToCheck.getStone().getStoneColor() || getPositionAtCoordinates(northWest).getStone().getStoneColor() == Stone.m_CLEAR_STONE)
            {
                if(getPositionAtCoordinates(southEast).getStone().getStoneColor() == a_positionToCheck.getStone().getStoneColor() || getPositionAtCoordinates(southEast).getStone().getStoneColor() == Stone.m_CLEAR_STONE)
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
     *      isPositionValid(): To verify that a given position is within the boundaries of the board.
     *
     * SYNOPSIS
     *      isPositionValid(Position a_positionToValidate);
     *
     *      @param a_positionToValidate The position which requires verification of its being in the board.
     *
     * DESCRIPTION
     *      Determines if the given position is within the boundaries of the board.
     *
     * RETURNS
     *      Boolean:
     *          True -> If the position is within the board's boundaries.
     *          False -> Otherwise.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      6 March 2018
     */
    /**/

    private Boolean isPositionValid(Position a_positionToValidate)
    {
        int row = a_positionToValidate.getRowPosition();
        int col = a_positionToValidate.getColPosition();

        if(row >= 0 && row < M_MAX_ROWS)
        {
            if(col >= 0 && col < getNumColsForRow(row))
            {
                return true;
            }
        }
        return false;
    }
}
