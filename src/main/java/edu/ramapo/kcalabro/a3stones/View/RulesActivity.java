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
import android.view.Window;

import android.widget.Button;

import java.io.IOException;

import edu.ramapo.kcalabro.a3stones.R;

/**
 * Created by KyleCalabro on 8/15/18.
 */

public class RulesActivity extends AppCompatActivity
{
    // The button which returns users to the main menu.
    private Button m_mainMenuButton;

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
     *      15 August 2018
     */
    /**/

    @Override
    protected void onCreate(Bundle a_savedInstance)
    {
        // Removes the tile bar, for aesthetic purposes.
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(a_savedInstance);
        setContentView(R.layout.activity_rules);

        // Determine if the user wants to start a new Tournament.
        Intent mainIntent = getIntent();

        m_mainMenuButton = findViewById(R.id.mainMenuButton);
        m_mainMenuButton.setOnClickListener(mainMenuButtonListener);

    }

     /**/
    /**
     * NAME
     *      mainMenuButtonListener(): The onClick Listener for the main menu button.
     *
     * SYNOPSIS
     *      mainMenuButtonListener();
     *
     * DESCRIPTION
     *      The onClick Listener for the main menu button. When clicked, it returns users to the main
     *      menu after viewing the rules of the game.
     *
     * RETURNS
     *      Nothing (onClick Listener).
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      15 August 2018
     */
    /**/

    View.OnClickListener mainMenuButtonListener = (new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            Intent mainMenu = new Intent(RulesActivity.this, MainMenuActivity.class);

            startActivity(mainMenu);
            finish();
        }
    });
}
