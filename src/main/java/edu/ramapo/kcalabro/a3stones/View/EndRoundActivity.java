/**/
/**
 * Kyle Calabro
 * Dr. Miller
 * Ramapo College of New Jersey
 * School of Theoretical and Applied Sciences
 * Senior Project - Computer Science
 */
/**/

package edu.ramapo.kcalabro.a3stones.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.ramapo.kcalabro.a3stones.R;

/**
 * Created by KyleCalabro on 2/28/18.
 */

public class EndRoundActivity extends AppCompatActivity
{
    //------------------------ Data Members ------------------------

    private int m_tournamentScoreLimit;
    private int m_roundNumber;

    private int m_blackStonesRoundScore;
    private int m_whiteStonesRoundScore;

    private int m_blackStonesTournScore;
    private int m_whiteStonesTournScore;

    private TextView m_blackStonesRoundScoreView;
    private TextView m_whiteStonesRoundScoreView;

    private TextView m_blackStonesTournScoreView;
    private TextView m_whiteStonesTournScoreView;

    private TextView m_winnerView;
    private TextView m_tournScoreLimitView;

    private Button m_newRoundButton;

    //------------------------ Member Methods ------------------------

     /**/
    /**
     * NAME
     *      onCreate(): The onCreate constructor that is called when the Activity is first created.
     *
     * SYNOPSIS
     *      onCreate(Bundle a_savedInstanceState);
     *
     *      @param a_savedInstanceState The bundle object imported from the previous Activity.
     *
     * DESCRIPTION
     *      The onCreate constructor that is called when the Activity is first created. Initializes
     *      all the Boolean flags of the class. Assigns the TextView to their
     *      appropriate id's.
     *
     * RETURNS
     *      Void (onCreate Constructor).
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      21 April 2018
     */
    /**/

    @Override
    protected void onCreate(Bundle a_savedInstanceState)
    {
        super.onCreate(a_savedInstanceState);

        setContentView(R.layout.activity_round_end);
        Bundle bundle = getIntent().getExtras();

        m_newRoundButton = findViewById(R.id.newRoundButton);
        m_newRoundButton.setOnClickListener(newRoundButtonListener);

        m_blackStonesRoundScoreView = findViewById(R.id.blackStonesRoundScore);
        m_whiteStonesRoundScoreView = findViewById(R.id.whiteStonesRoundScore);

        m_blackStonesTournScoreView = findViewById(R.id.blackStonesTournScore);
        m_whiteStonesTournScoreView = findViewById(R.id.whiteStonesTournScore);

        m_tournScoreLimitView = findViewById(R.id.tournamentScoreLimit);

        m_winnerView = findViewById(R.id.winner);

        m_whiteStonesTournScore = bundle.getInt("whiteStonesTournScore", 0);
        m_blackStonesTournScore = bundle.getInt("blackStonesTournScore", 0);

        m_blackStonesRoundScore = bundle.getInt("blackStonesRoundScore", 0);
        m_whiteStonesRoundScore = bundle.getInt("whiteStonesRoundScore", 0);

        m_roundNumber = bundle.getInt("roundNumber", 1);

        m_tournamentScoreLimit = bundle.getInt("tournamentScoreLimit", 0);

        updateScores();
    }

    /**/
    /**
     * NAME
     *      determineWinner(): To determine the winner of a round.
     *
     * SYNOPSIS
     *      determineWinner();
     *
     * DESCRIPTION
     *      To determine the winner of a round.
     *
     * RETURNS
     *      String representing the winner of the round, to announce to the screen.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      21 April 2018
     */
    /**/

    public String determineWinner()
    {
        // The score's represent the sum of pips from the other player's hand.
        if(m_blackStonesRoundScore > m_whiteStonesRoundScore)
        {
            return "Black";
        }
        else if(m_whiteStonesRoundScore > m_blackStonesRoundScore)
        {
            return "White";
        }
        else
        {
            return "Draw";
        }
    }

    /**/
    /**
     * NAME
     *      determineTournWinner(): To determine the winner of a Tournament.
     *
     * SYNOPSIS
     *      determineTournWinner();
     *
     * DESCRIPTION
     *      To determine the winner of a Tournament.
     *
     * RETURNS
     *      String representing the winner of the Tournament, to announce to the screen.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      18 August 2018
     */
    /**/

    public String determineTournWinner()
    {
        // The score's represent the sum of pips from the other player's hand.
        if(m_blackStonesTournScore > m_whiteStonesTournScore)
        {
            return "Black";
        }
        else if(m_whiteStonesTournScore > m_blackStonesTournScore)
        {
            return "White";
        }
        else
        {
            return "Draw";
        }
    }

    /**/
    /**
     * NAME
     *      hasTournamentEnded(): To determine if the Tournament has ended.
     *
     * SYNOPSIS
     *      hasTournamentEnded();
     *
     * DESCRIPTION
     *      To determine if the Tournament has ended and if a new activity needs to be launched.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      21 April 2018
     */
    /**/

    public void hasTournamentEnded()
    {
        Intent intent = new Intent(EndRoundActivity.this, EndTournamentActivity.class);

        intent.putExtra("blackStonesTournScore", m_blackStonesTournScore);
        intent.putExtra("whiteStonesTournScore", m_whiteStonesTournScore);
        intent.putExtra("winner", determineTournWinner());
        intent.putExtra("tournamentScoreLimit", m_tournamentScoreLimit);

        if(m_whiteStonesTournScore >= m_tournamentScoreLimit)
        {
            startActivity(intent);
            finish();
        }
        else if(m_blackStonesTournScore >= m_tournamentScoreLimit)
        {
            startActivity(intent);
            finish();
        }
    }

    /**/
    /**
     * NAME
     *      updateScores(): To update both player's scores and display them to the screen.
     *
     * SYNOPSIS
     *      updateScores();
     *
     * DESCRIPTION
     *      To update both player's scores and display them to the screen.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      21 April 2018
     */
    /**/

    public void updateScores()
    {
        m_winnerView.setText("Round Winner: " + determineWinner());
        hasTournamentEnded();

        m_tournScoreLimitView.setText("Tournament Score Limit: " + m_tournamentScoreLimit);

        m_blackStonesTournScoreView.setText("Black Stones Tournament Score: " + m_blackStonesTournScore);
        m_whiteStonesTournScoreView.setText("White Stones Tournament Score: " + m_whiteStonesTournScore);

        m_blackStonesRoundScoreView.setText("Black Stones Round Score: " + m_blackStonesRoundScore);
        m_whiteStonesRoundScoreView.setText("White Stones Round Score: " + m_whiteStonesRoundScore);
    }

    //------------------------ On Click Listeners ------------------------

    /**/
    /**
     * NAME
     *      newRoundButtonListener(): The onClickListener for the new round button.
     *
     * SYNOPSIS
     *      newRoundButtonListener();
     *
     * DESCRIPTION
     *      The onClickListener for the new round button. Once clicked the method will place the pertinent
     *      information of the in the bundle object for the next activity.
     *
     * RETURNS
     *      Nothing (button listener).
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      21 April 2018
     */
    /**/

    View.OnClickListener newRoundButtonListener = (new View.OnClickListener()
    {
        public void onClick(View view)
        {
            Intent intent = new Intent(EndRoundActivity.this, RoundActivity.class);

            // To begin a new round, push through all the pertinent data so that it can be loaded
            // into the new round, and the tournament can continue.
            intent.putExtra("blackStonesTournScore", m_blackStonesTournScore);
            intent.putExtra("whiteStonesTournScore", m_whiteStonesRoundScore);
            intent.putExtra("tournamentScore", m_tournamentScoreLimit);
            intent.putExtra("roundNumber", m_roundNumber + 1);
            intent.putExtra("newGame", true);

            intent.putExtra("singlePlayer", getIntent().getExtras().getBoolean("singlePlayer"));
            intent.putExtra("localMultiplayer", getIntent().getExtras().getBoolean("localMultiplayer"));

            startActivity(intent);
            finish();
        }
    });
}

