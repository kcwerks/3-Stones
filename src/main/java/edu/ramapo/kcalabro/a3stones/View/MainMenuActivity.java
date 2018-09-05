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

/**
 * Created by KyleCalabro on 2/2/18.
 */

import android.os.Environment;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.Vector;
import java.io.File;

import edu.ramapo.kcalabro.a3stones.Model.Serializer;
import edu.ramapo.kcalabro.a3stones.R;

public class MainMenuActivity extends AppCompatActivity
{
    //------------------------ Data Members ------------------------

    private int m_tournamentScore;

    private Serializer m_serializer;

    // The spinner which displays all available text files on the device's external storage.
    private Spinner m_fileSpinner;

    private Spinner m_tournScoreSpinner;

    // The file selected from the file spinner.
    private String m_selectedFile;

    // The button to load a game.
    private Button m_loadGameButton;

    private Button m_newGameButton;

    private Button m_rulesListButton;

    // Storage Permissions variables
    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    //------------------------ Member Methods ------------------------

    /**/
    /**
     * NAME
     *      onCreate(): The onCreate constructor that is called when the Activity is first created.
     *
     * SYNOPSIS
     *      onCreate(Bundle savedInstance);
     *
     *      @param a_savedInstanceState The bundle object imported from the previous Activity.
     *
     * DESCRIPTION
     *      The onCreate constructor that is called when the Activity is first created. Initializes
     *      all the Boolean flags of the class. Assigns TextViews to appropriated ID's, etc...
     *
     * RETURNS
     *      Void (onCreate Constructor).
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      28 February 2018
     */
    /**/

    @Override
    protected void onCreate(Bundle a_savedInstanceState)
    {
        super.onCreate(a_savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        verifyStoragePermissions(this);

        m_loadGameButton = findViewById(R.id.loadGameButton);
        m_newGameButton = findViewById(R.id.newGameButton);
        m_rulesListButton = findViewById(R.id.rulesButton);

        m_serializer = new Serializer();

        m_fileSpinner = (Spinner) findViewById(R.id.fileSpinner);
        m_tournScoreSpinner = (Spinner) findViewById(R.id.tournScoreSpinner);

        //ArrayAdapter filePickerAdapter = new ArrayAdapter<>(MainMenuActivity.this, R.layout.spinner_field, getAllTextFiles());
        ArrayAdapter<CharSequence> filePickerAdapter = ArrayAdapter.createFromResource(this, R.array.filesArray, R.layout.spinner_field);

        filePickerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m_fileSpinner.setAdapter(filePickerAdapter);
        m_fileSpinner.setBackgroundResource(R.drawable.button_border);

        ArrayAdapter<CharSequence> tournScoreAdapter = ArrayAdapter.createFromResource(this, R.array.tournScoreArray, R.layout.spinner_field);
        tournScoreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m_tournScoreSpinner.setAdapter(tournScoreAdapter);
        m_tournScoreSpinner.setBackgroundResource(R.drawable.button_border);

        // Set up the responsiveness of the file spinner...
        m_fileSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (position != 0)
                {
                    m_selectedFile = m_fileSpinner.getItemAtPosition(position).toString();
                    m_loadGameButton.setEnabled(true);
                }
                else
                {
                    m_loadGameButton.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Set up the responsiveness of the Tournament Score spinner...
        m_tournScoreSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (position != 0)
                {
                    m_tournamentScore = Integer.parseInt(m_tournScoreSpinner.getItemAtPosition(position).toString());
                    m_newGameButton.setEnabled(true);
                }
                else
                {
                    m_newGameButton.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**/
    /**
     * NAME
     *      verifyStoragePermissions(): To get storage permissions from the Android device.
     *
     * SYNOPSIS
     *      verifyStoragePermissions(Activity activity);
     *
     * DESCRIPTION
     *      To get storage permissions from the Android device.
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

    public static void verifyStoragePermissions(Activity activity)
    {
        // Check if we have read or write permission
        int writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    /**/
    /**
     * NAME
     *      getAllTextFiles(): To get all the text files stored on an Android device.
     *
     * SYNOPSIS
     *      getAllTextFiles();
     *
     * DESCRIPTION
     *      To get all the text files stored on an Android device.
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


    private Vector<String> getAllTextFiles()
    {
        Vector<String> textFiles = new Vector<>();
        textFiles.addElement(" Select File:");

        // Finding the sdcard path on the tablet.
        File sdcard = Environment.getExternalStorageDirectory().getAbsoluteFile();
        File[] files = sdcard.listFiles();

        for(int i = 0; i < files.length; i++)
        {
            File file = files[i];

            //It's assumed that all file in the path are in supported type.
            String filePath = file.getPath();

            if(filePath.endsWith(".txt"))
            {
                textFiles.add(filePath.substring(filePath.indexOf('0') + 2));
            }
        }
        return textFiles;
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
     *      28 February 2018
     */
    /**/

    public void startNewGame(View a_view)
    {
        // Set the intent to the RoundActivity class.
        Intent intent = new Intent(this, SecondaryMenuActivity.class);

        intent.putExtra("tournamentScore", m_tournamentScore);

        // Set the newround flag to true.
        intent.putExtra("newGame", true);

        // Start the activity.
        startActivity(intent);
    }

    /**/
    /**
     * NAME
     *      loadGame(): To start a new tournament/round with the proper flags.
     *
     * SYNOPSIS
     *      loadGame(View a_view);
     *
     *      @param a_view The view to launch a new activity from.
     *
     *
     * DESCRIPTION
     *      To load a saved tournament/round with the proper flags placed.
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

    public void loadGame(View a_view)
    {
        Boolean computerPlayerNeeded = m_serializer.determineIfComputerPlayerActive(m_selectedFile);

        // Set the intent to the RoundActivity class.
        Intent intent = new Intent(this, RoundActivity.class);

        if(computerPlayerNeeded)
        {
            // Set the singlePlayer flag.
            intent.putExtra("singlePlayer", true);

            // Set the localMultiplayer flag.
            intent.putExtra("localMultiplayer", false);
        }

        else
        {
            // Set the singlePlayer flag.
            intent.putExtra("singlePlayer", false);

            // Set the localMultiplayer flag.
            intent.putExtra("localMultiplayer", true);
        }

        // Set the newgame flag to false.
        intent.putExtra("newGame", false);

        // Send over the filename as well.
        intent.putExtra("m_selectedFile", m_selectedFile);

        // Start the activity.
        startActivity(intent);
    }

    /**/
    /**
     * NAME
     *      displayRulesActivity(): To launch the activity that displays the rules to the user.
     *
     * SYNOPSIS
     *      displayRulesActivity(View a_view);
     *
     *      @param a_view The view to launch a new activity from.
     *
     * DESCRIPTION
     *      To launch the activity that displays the rules to the user.
     *
     * RETURNS
     *      Void.
     *
     * AUTHOR
     *      Kyle Calabro
     *
     * DATE
     *      15 August 2018
     */
    /**/

    public void displayRulesActivity(View a_view)
    {
        // Set the intent to the RoundActivity class.
        Intent intent = new Intent(this, RulesActivity.class);
        startActivity(intent);
    }
}
