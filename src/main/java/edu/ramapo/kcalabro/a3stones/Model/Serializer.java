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

import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by KyleCalabro on 3/28/18.
 */

public class Serializer
{
    //------------------------ Data Members ------------------------

    // The pattern to be matched for player's data.
    private String m_playerDataPattern = "(\\s([BW])\\s(\\d+)\\s(\\d+)\\s(\\d+)\\s(\\d+)\\s(\\d+)\\s([NY])\\s([NY]))";

    // The pattern to be matched for the board data.
    private String m_boardDataPattern = "(([BCWO]))";

    // The pattern to be matched for the previous move made.
    private String m_previousMoveDataPattern = "((\\d+)\\s(\\d+))";

    // The pattern to be matched for the available placement options.
    private String m_placementOptionsPattern = "((false|true)\\s(false|true))";

    // The regular expression to use in conjunction with the pattern for player data.
    private Pattern m_playerDataRegex = Pattern.compile(m_playerDataPattern);

    // The regular expression to use in conjunction with the pattern for board data.
    private Pattern m_boardDataRegex = Pattern.compile(m_boardDataPattern);

    // The regular expression to use in conjunction with the pattern for previous move data.
    private Pattern m_previousMoveDataRegex = Pattern.compile(m_previousMoveDataPattern);

    // The regular expression to use in conjunction with the pattern for placement options.
    private Pattern m_placementOptionsRegex = Pattern.compile(m_placementOptionsPattern);

    //------------------------ Member Methods ------------------------

    /**/
    /**
     * NAME
     *      Serializer(): Default constructor for the Serializer class.
     *
     * SYNOPSIS
     *      Serializer();
     *
     * DESCRIPTION
     *      Default constructor for the Serializer class.
     *
     * RETURNS
     *      Object of the Serializer class.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 March 2018
     */
    /**/

    public Serializer()
    {
    }

     /**/
    /**
     * NAME
     *      serializeFile(): To save the current Tournament to a text file.
     *
     * SYNOPSIS
     *      serializeFile(a_tournament);
     *
     *      @param a_tournament Object of the Tournament class that holds all pertinent data required
     *                          to save a tournament to a text file so it can loaded and replayed later.
     *
     * DESCRIPTION
     *      To save a given tournament to a text file so that it can be loaded and played at a later
     *      time.
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

    public void serializeFile(Tournament a_tournament) throws IOException
    {
        // The filename to save the round as.
        String fileName = "savedGame.txt";

        String filepath = Environment.getExternalStorageDirectory().toString();

        File serializedFile = new File(filepath, fileName);

        // If the file exists delete it so we can write a new file.
        if(serializedFile.exists())
        {
            serializedFile.delete();
        }

        try
        {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(serializedFile));

            // Write the tournament score to the file.
            bufferedWriter.write("Tournament Score Limit: ");
            bufferedWriter.write(a_tournament.getTournamentScoreLimit() + "\n\n");

            // Write the round number to the file.
            bufferedWriter.write("Round No.: ");
            bufferedWriter.write(a_tournament.getRoundNum() + "\n\n");

            // Write the black player's data to the file.
            bufferedWriter.write("Black Player: \n");
            serializePlayerData(a_tournament.getRound().getBlackPlayer(), bufferedWriter);

            // Write the white player's data to the file.
            bufferedWriter.write("White Player: \n");
            serializePlayerData(a_tournament.getRound().getWhitePlayer(), bufferedWriter);

            // Write the previous play's row and column to the file.
            bufferedWriter.write("Last Play: " + a_tournament.getRound().getBoard().getPreviousMoveRow() +
                    " " + a_tournament.getRound().getBoard().getPreviousMoveCol() + "\n");

            bufferedWriter.write("Placement Options: " + a_tournament.getRound().getBoard().getFreePlacement() +
                    " " + a_tournament.getRound().getBoard().getHasFirstPlayBeenMade() + "\n");

            // Write the layout to the file.
            bufferedWriter.write("Board: \n");
            serializeBoard(a_tournament.getRound().getBoard(), bufferedWriter);

            // Clean up the buffered writer.
            bufferedWriter.close();
        }
        catch (FileNotFoundException fileException)
        {
            fileException.printStackTrace();
        }
    }

     /**/
    /**
     * NAME
     *      restoreFile(): To restore a tournament from one previously saved in a text file.
     *
     * SYNOPSIS
     *      restoreFile(a_tournament, a_fileName);
     *
     *      @param a_tournament Object of the Tournament class to set all pertinent data required
     *                          to load a saved tournament to a text file within.
     *      @param a_fileName The filename from which to restore a tournament from.
     *
     * DESCRIPTION
     *      To restore a tournament from a given, previously saved text file.
     *
     * RETURNS
     *      Boolean:
     *          True -> File was opened successfully and tournament has been restored.
     *          False -> File could not be opened.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      6 April 2018
     */
    /**/

    public boolean restoreFile(Tournament a_tournament, String a_fileName)
    {
        File sdcard = Environment.getExternalStorageDirectory().getAbsoluteFile();
        File serializedFile = new File(sdcard, a_fileName);

        try
        {
            String fileLine;
            int lineNumber = 0;
            int rowNum = 0;

            BufferedReader bufferedReader = new BufferedReader(new FileReader(serializedFile));

            try {
                while ((fileLine = bufferedReader.readLine()) != null)
                {
                    if (!fileLine.equals(""))
                    {
                        lineNumber++;

                        // First line contains the tournament score.
                        if (lineNumber == 1)
                        {
                            String tournamentScore = fileLine.substring(fileLine.indexOf(':') + 2);
                            int tournScore = Integer.parseInt(tournamentScore);

                            a_tournament.setTournamentScoreLimit(tournScore);
                        }

                        // Second line contains the round number.
                        else if (lineNumber == 2)
                        {
                            String roundNum = fileLine.substring(fileLine.indexOf(':') + 2);
                            int roundNumber = Integer.parseInt(roundNum);

                            a_tournament.setRoundNum(roundNumber);
                        }

                        // Black stone player data comes next.
                        else if (lineNumber == 4)
                        {
                            // Restore the black player's data.
                            restorePlayerData(a_tournament.getRound().getBlackPlayer(), a_tournament.getRound(), fileLine);
                        }

                        // White stone player data comes next.
                        else if (lineNumber == 6)
                        {
                            // Restore the white player's data.
                            restorePlayerData(a_tournament.getRound().getWhitePlayer(), a_tournament.getRound(), fileLine);
                        }

                        else if(lineNumber == 7)
                        {
                            restorePreviousMoveData(a_tournament.getRound().getBoard(), fileLine);
                        }

                        else if(lineNumber == 8)
                        {
                            restorePlacementOptions(a_tournament.getRound().getBoard(), fileLine);
                        }

                        // Board data comes next.
                        else if (lineNumber > 9)
                        {
                            // Restore the Board, row by row.
                            restoreBoardRow(a_tournament.getRound().getBoard(), fileLine, rowNum);

                            rowNum++;
                        }
                    }
                }

                // Clean up the BufferedReader.
                bufferedReader.close();
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
                return false;
            }
        }
        catch (FileNotFoundException fileException)
        {
            fileException.printStackTrace();
            return false;
        }
        return true;
    }

    /**/
    /**
     * NAME
     *      determineIfComputerPlayerActive(): To determine the game type of a saved game.
     *
     * SYNOPSIS
     *      determineIfComputerPlayerActive(a_fileName);
     *
     *      @param a_fileName The name of the file which to open.
     *
     * DESCRIPTION
     *      To determine if a saved game was of single player or local multiplayer game type.
     *
     * RETURNS
     *      Boolean:
     *          True -> Single player game type.
     *          False -> Local multiplayer game type.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      12 April 2018
     */
    /**/

    public Boolean determineIfComputerPlayerActive(String a_fileName)
    {
        File sdcard = Environment.getExternalStorageDirectory().getAbsoluteFile();
        File serializedFile = new File(sdcard, a_fileName);

        boolean computerPlayerNeeded = false;

        try
        {
            String fileLine;
            int lineNumber = 0;

            BufferedReader bufferedReader = new BufferedReader(new FileReader(serializedFile));

            try
            {
                while ((fileLine = bufferedReader.readLine()) != null)
                {
                    if (!fileLine.equals(""))
                    {
                        lineNumber++;

                        // Board data comes next.
                        if (lineNumber == 4 || lineNumber == 6)
                        {
                            Matcher matcher = m_playerDataRegex.matcher(fileLine);

                            while(matcher.find())
                            {
                                char playerIsComputer = matcher.group(8).charAt(0);

                                if(playerIsComputer == 'Y')
                                {
                                    computerPlayerNeeded = true;
                                }
                            }
                        }
                    }
                }
                // Clean up the BufferedReader.
                bufferedReader.close();
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
                return false;
            }
        }
        catch (FileNotFoundException fileException)
        {
            fileException.printStackTrace();
            return false;
        }
        return computerPlayerNeeded;
    }

     /**/
    /**
     * NAME
     *      serializeBoard(): To save the board to a given text file.
     *
     * SYNOPSIS
     *      serializeBoard(a_board, a_bufferedWriter);
     *
     *      @param a_board Object of the board class to write data from to the file.
     *      @param a_bufferedWriter The buffered writer to perform the file writing.
     *
     * DESCRIPTION
     *      To save the board to a given text file.
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

    private void serializeBoard(Board a_board, BufferedWriter a_bufferedWriter) throws IOException
    {
        // Traverse the board row by row...
        for(int i = 0; i < Board.M_MAX_ROWS; i++)
        {
            for (Position position : a_board.getRow(i))
            {
                // Print the board data to the file.
                a_bufferedWriter.write(position.getStone().getStoneColor());
                a_bufferedWriter.write(" ");
            }
            a_bufferedWriter.write("\n");
        }
    }

     /**/
    /**
     * NAME
     *      serializePlayerData(): To save a Player's data to a given text file.
     *
     * SYNOPSIS
     *      serializePlayerData(a_player, a_bufferedWriter);
     *
     *      @param a_player Object of the Player class containing all the pertinent information of
     *                      a Player to write to the given file.
     *      @param a_bufferedWriter The buffered writer to perform the file writing.
     *
     * DESCRIPTION
     *      To save a given Player's data to a text file. Such data includes stone color, round score,
     *      tournament score, hand, isCurrentPlayer and isComputer Boolean flags.
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

    private void serializePlayerData(Player a_player, BufferedWriter a_bufferedWriter) throws IOException
    {
        // Print the player's stone color, round score, number of remaining black, white, and clear stones.
        a_bufferedWriter.write("\t" + a_player.getPlayerStoneColor());
        a_bufferedWriter.write(" " + a_player.getTournamentScore());
        a_bufferedWriter.write(" " + a_player.getRoundScore());

        a_bufferedWriter.write(" " + a_player.getHand().getAvailableBlackStones().size());
        a_bufferedWriter.write(" " + a_player.getHand().getAvailableWhiteStones().size());
        a_bufferedWriter.write(" " + a_player.getHand().getAvailableClearStones().size());

        // Write if the given player is a computer player.
        if(a_player.getIsComputer())
        {
            a_bufferedWriter.write(" Y");
        }
        else
        {
            a_bufferedWriter.write(" N");
        }

        // Write if the given player is the current player.
        if(a_player.getIsCurrentPlayer())
        {
            a_bufferedWriter.write(" Y");
        }
        else
        {
            a_bufferedWriter.write(" N");
        }

        a_bufferedWriter.write("\n");
    }

    /**/
    /**
     * NAME
     *      restorePlayerData(): To restore a Player's data from a given text file.
     *
     * SYNOPSIS
     *      restorePlayerData(a_player, a_fileLine);
     *
     *      @param a_player Object of the Player class containing all the pertinent information of
     *                      a Player to write to the given file.
     *      @param a_fileLine The line of the file from which to restore data from.
     *
     * DESCRIPTION
     *      To save a given Player's data to a text file. Such data includes stone color, round score,
     *      tournament score, hand, isCurrentPlayer and isComputer Boolean flags. Uses regular expression
     *      to parse the given String from the file.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      6 April 2018
     */
    /**/

    private void restorePlayerData(Player a_player, Round a_round, String a_fileLine)
    {
        Matcher matcher = m_playerDataRegex.matcher(a_fileLine);

        while(matcher.find())
        {
            char playerStoneColor = matcher.group(2).charAt(0);

            int playerTournScore = Integer.parseInt(matcher.group(3));
            int playerRoundScore = Integer.parseInt(matcher.group(4));

            int numBlackStones = Integer.parseInt(matcher.group(5));
            int numWhiteStones = Integer.parseInt(matcher.group(6));
            int numClearStones = Integer.parseInt(matcher.group(7));

            char playerIsComputer = matcher.group(8).charAt(0);
            char playerIsCurrentPlayer = matcher.group(9).charAt(0);

            a_player.setPlayerStoneColor(playerStoneColor);

            a_player.setTournamentScore(playerTournScore);
            a_player.setRoundScore(playerRoundScore);

            Hand restoredHand = new Hand(numBlackStones, numWhiteStones, numClearStones);

            a_player.setHand(restoredHand);

            if(playerIsComputer == 'Y')
            {
                a_player.setIsComputer(true);
            }
            else
            {
                a_player.setIsComputer(false);
            }

            if(playerIsCurrentPlayer == 'Y' && playerStoneColor == 'W')
            {
                a_player.setIsCurrentPlayer(true);
                a_round.swapCurrentPlayer();
            }
            else
            {
                a_player.setIsCurrentPlayer(false);
            }
        }
    }

    /**/
    /**
     * NAME
     *      restoreBoardRow(): To restore the data of the Board from a given text file.
     *
     * SYNOPSIS
     *      restoreBoardRow(a_board, a_fileLine, a_rowNumber);
     *
     *      @param a_board Object of the Board class to restored based on the given text file.
     *      @param a_fileLine The line of the file from which to restore data from.
     *      @param a_rowNumber The row number to restore based on the given line of the file.
     *
     * DESCRIPTION
     *      To restore the board from a given text file. Regular expression is used to parse the
     *      given string of text from the file.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      6 April 2018
     */
    /**/

    private void restoreBoardRow(Board a_board, String a_fileLine, int a_rowNumber)
    {
        Matcher matcher = m_boardDataRegex.matcher(a_fileLine);

        int colNum = 0;

        while(matcher.find())
        {
            // The Board position at row 5, col 5 is Non-Existent, cannot play there.
            if(a_rowNumber == 5 && colNum == 5)
            {
                colNum++;
                continue;
            }

            char stoneAtPosition = matcher.group(2).charAt(0);

            Position boardPosition = new Position(a_rowNumber, colNum, stoneAtPosition);

            a_board.setPositionAtCoordinates(boardPosition);

            colNum++;
        }
    }

    /**/
    /**
     * NAME
     *      restorePreviousMoveData(): To restore the data of the previous move made.
     *
     * SYNOPSIS
     *      restorePreviousMoveData(a_board, a_fileLine);
     *
     *      @param a_board Object of the Board class to restored based on the given text file.
     *      @param a_fileLine The line of the file from which to restore data from.
     *
     * DESCRIPTION
     *      To restore the data of the previous move made in the saved game.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      6 April 2018
     */
    /**/

    private void restorePreviousMoveData(Board a_board, String a_fileLine)
    {
        Matcher matcher = m_previousMoveDataRegex.matcher(a_fileLine);

        while(matcher.find())
        {
            int previousMoveRow = Integer.parseInt(matcher.group(2));
            int previousMoveCol = Integer.parseInt(matcher.group(3));

            a_board.setPreviousMoveRow(previousMoveRow);
            a_board.setPreviousMoveCol(previousMoveCol);
        }
    }

    /**/
    /**
     * NAME
     *      restorePlacementOptions(): To restore the boolean flags that indicate available placement options.
     *
     * SYNOPSIS
     *      restorePlacementOptions(a_board, a_fileLine);
     *
     *      @param a_board Object of the Board class to restored based on the given text file.
     *      @param a_fileLine The line of the file from which to restore data from.
     *
     * DESCRIPTION
     *      To restore the boolean flags that indicate placement options available to the current player.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *     6 April 2018
     */
    /**/

    private void restorePlacementOptions(Board a_board, String a_fileLine)
    {
        Matcher matcher = m_placementOptionsRegex.matcher(a_fileLine);

        while(matcher.find())
        {
            String freePlacement = matcher.group(2);

            String firstPlayHasBeenMade = matcher.group(3);

            if(freePlacement.equals("false"))
            {
                a_board.setFreePlacement(false);
            }
            else
            {
                a_board.setFreePlacement(true);
            }

            if(firstPlayHasBeenMade.equals("false"))
            {
                a_board.setHasFirstPlayBeenMade(false);
            }
            else
            {
                a_board.setHasFirstPlayBeenMade(true);
            }
        }
    }
}
