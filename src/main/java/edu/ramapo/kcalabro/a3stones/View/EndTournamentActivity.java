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

public class EndTournamentActivity extends AppCompatActivity
{
    //------------------------ Data Members ------------------------

    private Button m_newTournamentButton;

    private int m_whiteStonesTournScore;
    private int m_blackStonesTournScore;
    private int m_tournamentScoreLimit;

    private String m_winner;

    private TextView m_blackStonesTournScoreView;
    private TextView m_whiteStonesTournScoreView;
    private TextView m_winnerView;
    private TextView m_tournamentScoreLimitView;

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
     *      all the Boolean flags of the class. Assigns the TextViews to their
     *      appropriate id's.
     *
     * RETURNS
     *      Void (onCreate Constructor).
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      23 April 2018
     */
    /**/

    @Override
    protected void onCreate(Bundle a_savedInstanceState)
    {
        super.onCreate(a_savedInstanceState);
        setContentView(R.layout.activity_tournament_end);

        Bundle bundle = getIntent().getExtras();
        m_newTournamentButton = findViewById(R.id.playButton);

        m_whiteStonesTournScoreView = findViewById(R.id.whiteStonesTournScore);
        m_blackStonesTournScoreView = findViewById(R.id.blackStonesTournScore);

        m_winnerView = findViewById(R.id.winner);
        m_tournamentScoreLimitView = findViewById(R.id.tournScoreLimit);

        m_winner = bundle.getString("winner", "draw");
        m_blackStonesTournScore = bundle.getInt("blackStonesTournScore", 0);
        m_whiteStonesTournScore = bundle.getInt("whiteStonesTournScore", 0);
        m_tournamentScoreLimit = bundle.getInt("tournamentScoreLimit",0);

        m_newTournamentButton.setOnClickListener(newTournamentButtonListener);

        updateScores();
    }

     /**/
    /**
     * NAME
     *      updateScores(): To update both player's tournament scores and display them to the screen.
     *
     * SYNOPSIS
     *      updateScores();
     *
     * DESCRIPTION
     *      To update both player's tournament scores and display them to the screen.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      23 April 2018
     */
    /**/

    public void updateScores()
    {
        m_winnerView.setText("Tournament Winner: " + m_winner);

        m_blackStonesTournScoreView.setText("Black Stones Tournament Score: " + m_blackStonesTournScore);
        m_whiteStonesTournScoreView.setText("White Stones Tournament Score: " + m_whiteStonesTournScore);
        m_tournamentScoreLimitView.setText("Tournament Score Limit: " + m_tournamentScoreLimit);
    }

    //------------------------ On Click Listeners ------------------------

    /**/
    /**
     * NAME
     *      newTournamentButtonListener(): The onClickListener for the new tournament button.
     *
     * SYNOPSIS
     *      newTournamentButtonListener();
     *
     * DESCRIPTION
     *      The onClickListener for the new tournament button. Once clicked the method will place the pertinent
     *      information of the in the bundle object for the next activity.
     *
     * RETURNS
     *      Nothing.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      23 April 2018
     */
    /**/

    View.OnClickListener newTournamentButtonListener = (new View.OnClickListener()
    {
        public void onClick(View view)
        {
            Intent intent = new Intent(EndTournamentActivity.this, MainMenuActivity.class);
            startActivity(intent);
            finish();
        }
    });
}
