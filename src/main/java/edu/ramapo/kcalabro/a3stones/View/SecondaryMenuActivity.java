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

/**
 * Created by KyleCalabro on 4/17/18.
 */

import android.view.View;
import android.view.Window;
import android.widget.Button;

import edu.ramapo.kcalabro.a3stones.R;

public class SecondaryMenuActivity extends AppCompatActivity
{
    //------------------------ Data Members ------------------------

    private Button m_localMultiplayerButton;

    private Button m_singlePlayerButton;

    private boolean m_localMultiplayer;

    private boolean m_singlePlayer;

    private int m_tournamentScore;

    //------------------------ Member Methods ------------------------

     /**/
    /**
     * NAME
     *      onCreate(): The onCreate constructor that is called when the Activity is first created.
     *
     * SYNOPSIS
     *      onCreate(Bundle savedInstance);
     *
     *      @param a_savedInstance The bundle object imported from the previous Activity.
     *
     * DESCRIPTION
     *      The onCreate constructor that is called when the Activity is first created. Initializes
     *      all the Boolean flags of the class. Assigns the Button selectors to their
     *      appropriate onClickListeners.
     *
     * RETURNS
     *      Void (onCreate Constructor).
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      18 April 2018
     */
    /**/

    @Override
    protected void onCreate(Bundle a_savedInstance)
    {
        // Determine if the user wants to start a new Tournament.
        Intent mainIntent = getIntent();

        m_tournamentScore = mainIntent.getExtras().getInt("tournamentScore");

        // Removes the title bar, for aesthetic purposes.
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(a_savedInstance);
        setContentView(R.layout.activity_secondary_menu);

        m_localMultiplayerButton = findViewById(R.id.localMultiplayerButton);
        m_localMultiplayerButton.setOnClickListener(localMultiplayerButtonListener);

        m_singlePlayerButton = findViewById(R.id.singlePlayerButton);
        m_singlePlayerButton.setOnClickListener(singlePlayerButtonListener);

        m_singlePlayer = false;
        m_localMultiplayer = false;
    }

    /**/
    /**
     * NAME
     *      startNewGame(): To start a new tournament/round with the proper flags.
     *
     * SYNOPSIS
     *      startNewGame(a_view);
     *
     *      @param a_view The view to launch a new activity from.
     *
     * DESCRIPTION
     *      To start a new tournament/round with the proper flags placed.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      18 April 2018
     */
    /**/

    public void startNewGame(View a_view)
    {
        // Set the intent to the RoundActivity class.
        Intent intent = new Intent(this, RoundActivity.class);

        intent.putExtra("tournamentScore", m_tournamentScore);

        // Set the m_singlePlayer flag.
        intent.putExtra("singlePlayer", m_singlePlayer);

        intent.putExtra("newGame", true);

        // Set the m_localMultiplayer flag.
        intent.putExtra("localMultiplayer", m_localMultiplayer);

        // Start the activity.
        startActivity(intent);
    }

    /**/
    /**
     * NAME
     *      localMultiplayerButtonListener(): The button listener for the local multiplayer button.
     *
     * SYNOPSIS
     *      localMultiplayerButtonListener();
     *
     * DESCRIPTION
     *      Button listener for the local multiplayer button.
     *
     * RETURNS
     *      Nothing (button listener).
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      18 April 2018
     */
    /**/

    View.OnClickListener localMultiplayerButtonListener = (new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            m_localMultiplayer = true;
            startNewGame(view);
        }
    });

    /**/
    /**
     * NAME
     *      singlePlayerButtonListener(): The button listener for the single player button.
     *
     * SYNOPSIS
     *      singlePlayerButtonListener();
     *
     * DESCRIPTION
     *      Button listener for the single player button.
     *
     * RETURNS
     *      Nothing (button listener).
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      18 April 2018
     */
    /**/

    View.OnClickListener singlePlayerButtonListener = (new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            m_singlePlayer = true;
            startNewGame(view);
        }
    });
}
