package com.example.android.courtcounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * Here I declare all the global variables.
     */

    int currentGameA;
    int currentGameB;

    int setScoreA;
    int setScoreB;

    int currentSet = 1;
    boolean tieBreaker = false;

    private String mScoreValue[] = {"00", "15", "30", "40", "Ad"};
    ArrayList<Score> setCounter;
    ArrayList<Score> tieCounter;

    TextView set1PlayerA;
    TextView set2PlayerA;
    TextView set3PlayerA;
    TextView tie1PlayerA;
    TextView tie2PlayerA;
    TextView tie3PlayerA;

    TextView set1PlayerB;
    TextView set2PlayerB;
    TextView set3PlayerB;
    TextView tie1PlayerB;
    TextView tie2PlayerB;
    TextView tie3PlayerB;

    int breakScoreA = 0;
    int breakScoreB = 0;

    public static String namePlayerA = "Player A";
    public static String namePlayerB = "Player B";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * This code is to set the player names from the variable.
         */

        TextView playerA = (TextView) findViewById(R.id.score_button_a);
        playerA.setText(namePlayerA);
        TextView playerB = (TextView) findViewById(R.id.score_button_b);
        playerB.setText(namePlayerB);

        findViewById(R.id.names).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent namesIntent = new Intent(MainActivity.this, Names.class);
                startActivity(namesIntent);
            }
        });


        /**
         * Here i fill the ArrayList with the Objects Score.
         */

        this.setCounter = new ArrayList<Score>();
        setCounter.add(new Score(0, 0));
        setCounter.add(new Score(0, 0));
        setCounter.add(new Score(0, 0));
        setCounter.add(new Score(0, 0));

        this.tieCounter = new ArrayList<Score>();
        tieCounter.add(new Score(0, 0));
        tieCounter.add(new Score(0, 0));
        tieCounter.add(new Score(0, 0));
        tieCounter.add(new Score(0, 0));

        /**
         * Here I identify the TextViews.
         */
        set1PlayerA = (TextView) findViewById(R.id.set_1_player_a);
        set2PlayerA = (TextView) findViewById(R.id.set_2_player_a);
        set3PlayerA = (TextView) findViewById(R.id.set_3_player_a);
        tie1PlayerA = (TextView) findViewById(R.id.tie_1_player_a);
        tie2PlayerA = (TextView) findViewById(R.id.tie_2_player_a);
        tie3PlayerA = (TextView) findViewById(R.id.tie_3_player_a);

        set1PlayerB = (TextView) findViewById(R.id.set_1_player_b);
        set2PlayerB = (TextView) findViewById(R.id.set_2_player_b);
        set3PlayerB = (TextView) findViewById(R.id.set_3_player_b);
        tie1PlayerB = (TextView) findViewById(R.id.tie_1_player_b);
        tie2PlayerB = (TextView) findViewById(R.id.tie_2_player_b);
        tie3PlayerB = (TextView) findViewById(R.id.tie_3_player_b);
    }

    /**
     * This method updates the score on the screen. It takes into account the current set
     * and tiebreaker scores.
     */
    public void displayScore() {
        TextView gameScoreA = (TextView) findViewById(R.id.player_a_score);
        gameScoreA.setText(mScoreValue[currentGameA]);

        String mA = Integer.toString(setCounter.get(currentSet).getScoreA());
        String mTA = Integer.toString(tieCounter.get(currentSet).getScoreA());

        switch (currentSet) {

            case 1:
                set1PlayerA.setText(mA);
                break;

            case 2:
                set2PlayerA.setText(mA);
                break;

            case 3:
                set3PlayerA.setText(mA);
                break;
        }

        switch (currentSet) {

            case 1:
                tie1PlayerA.setText(mTA);
                break;

            case 2:
                tie2PlayerA.setText(mTA);
                break;

            case 3:
                tie3PlayerA.setText(mTA);
                break;
        }
        /**
         * Update Score for Player B.
         */

        TextView scoreView = (TextView) findViewById(R.id.player_b_score);
        scoreView.setText(mScoreValue[currentGameB]);

        String mB = Integer.toString(setCounter.get(currentSet).getScoreB());
        String mTB = Integer.toString(tieCounter.get(currentSet).getScoreB());
        switch (currentSet) {

            case 1:
                set1PlayerB.setText(mB);
                break;

            case 2:
                set2PlayerB.setText(mB);
                break;

            case 3:
                set3PlayerB.setText(mB);
                break;
        }

        switch (currentSet) {

            case 1:
                tie1PlayerB.setText(mTB);
                break;

            case 2:
                tie2PlayerB.setText(mTB);
                break;

            case 3:
                tie3PlayerB.setText(mTB);
                break;
        }
    }

    /**
     * this is the method is called to score for player A.
     */
    public void scoreA(View view) {
        if (setScoreA == 2 || setScoreB == 2){
            Toast matchOver = Toast.makeText(this, "The match is over",Toast.LENGTH_LONG);
            matchOver.show();
        }
        else if (tieBreaker) {
            tieBreakerScoreA();
        } else if (currentGameA < 3) {
            currentGameA++;
            displayScore();
        } else if (currentGameA == 3 && currentGameB < 3) {
            currentGameA = 0;
            currentGameB = 0;
            scoreSetA();
        } else if (currentGameA == 3 && currentGameB == 3) {
            currentGameA++;
            displayScore();
        } else if (currentGameA == 3 && currentGameB == 4) {
            currentGameB--;
            displayScore();
        } else if (currentGameA == 4 && currentGameB == 3) {
            currentGameA = 0;
            currentGameB = 0;
            scoreSetA();
        }
    }

    /**
     * this is the method is called to score for player B
     */

    public void scoreB(View view) {
        if (setScoreA == 2 || setScoreB == 2) {
            Toast matchOver = Toast.makeText(this, "The match is over", Toast.LENGTH_LONG);
            matchOver.show();
        }
        else if (tieBreaker) {
            tieBreakerScoreB();
        } else if (currentGameB < 3) {
            currentGameB++;
            displayScore();
        } else if (currentGameB == 3 && currentGameA < 3) {
            currentGameA = 0;
            currentGameB = 0;
            scoreSetB();
        } else if (currentGameB == 3 && currentGameA == 3) {
            currentGameB++;
            displayScore();
        } else if (currentGameB == 3 && currentGameA == 4) {
            currentGameA--;
            displayScore();
        } else if (currentGameB == 4 && currentGameA == 3) {
            currentGameA = 0;
            currentGameB = 0;
            scoreSetB();
        }
    }

    /**
     * This method adds a point to the set being played of Player A
     */

    public void scoreSetA() {
        if (setCounter.get(currentSet).getScoreA() < 5) {
            setCounter.get(currentSet).addScoreA();
            displayScore();
        } else if (setCounter.get(currentSet).getScoreA() == 5 && setCounter.get(currentSet).getScoreB() == 5){
            setCounter.get(currentSet).addScoreA();
            displayScore();
        }else if (setCounter.get(currentSet).getScoreA()  >= 5 && setCounter.get(currentSet).getScoreB() < setCounter.get(currentSet).getScoreA()) {
            setCounter.get(currentSet).addScoreA();
            displayScore();
            setScoreA++;
            currentSet++;
        } else if (setCounter.get(currentSet).getScoreA() == 5 && setCounter.get(currentSet).getScoreB() == 6) {
            setCounter.get(currentSet).addScoreA();
            displayScore();
            tieBreaker = true;
        }
    }

    /**
     * This method adds a point to the set being played of Player B.
     */

    public void scoreSetB() {
        if (setCounter.get(currentSet).getScoreB() < 5) {
            setCounter.get(currentSet).addScoreB();
            displayScore();
        } else if (setCounter.get(currentSet).getScoreB() == 5 && setCounter.get(currentSet).getScoreA() == 5){
            setCounter.get(currentSet).addScoreB();
            displayScore();
        } else if (setCounter.get(currentSet).getScoreB() >= 5 && setCounter.get(currentSet).getScoreA() < setCounter.get(currentSet).getScoreB()) {
            setCounter.get(currentSet).addScoreB();
            displayScore();
            setScoreB++;
            currentSet++;
        } else if (setCounter.get(currentSet).getScoreB() == 5 && setCounter.get(currentSet).getScoreA() == 6) {
            setCounter.get(currentSet).addScoreB();
            displayScore();
            tieBreaker = true;
        }

    }

    /**
     * This method adds 1 to the current Tiebreaker for Player B.
     */

    public void tieBreakerScoreA() {
        if (tieCounter.get(currentSet).getScoreA() >= 6 && tieCounter.get(currentSet).getScoreB() < tieCounter.get(currentSet).getScoreA()) {
            tieCounter.get(currentSet).addScoreA();
            setCounter.get(currentSet).addScoreA();
            displayScore();
            currentSet++;
            setScoreA++;
            tieBreaker = false;
        } else {
            tieCounter.get(currentSet).addScoreA();
            displayScore();
        }
    }

    /**
     * This method adds 1 to the current Tiebreaker for Player B.
     */

    public void tieBreakerScoreB() {
        if (tieCounter.get(currentSet).getScoreB() >= 6 && tieCounter.get(currentSet).getScoreA() < tieCounter.get(currentSet).getScoreB()) {
            tieCounter.get(currentSet).addScoreB();
            setCounter.get(currentSet).addScoreB();
            displayScore();
            currentSet++;
            setScoreB++;
            tieBreaker = false;
        } else {
            tieCounter.get(currentSet).addScoreB();
            displayScore();
        }
    }

    /**
     * These methods are called when the break button is pressed
     */

    public void breakPlayerA(View view) {
        breakScoreA++;
        TextView breakViewA = (TextView) findViewById(R.id.break_score_a);
        String bScore= Integer.toString(breakScoreA);
        breakViewA.setText(bScore);
    }

    public void breakPlayerB(View view) {
        breakScoreB++;
        TextView breakViewB = (TextView) findViewById(R.id.break_score_b);
        String bScore= Integer.toString(breakScoreB);
        breakViewB.setText(bScore);
    }
    /**
     * This is the method for the reset button.
     */
    public void reset(View view) {
        currentGameA = 0;
        currentGameB = 0;
        tieCounter.get(1).scoreReset();
        tieCounter.get(2).scoreReset();
        tieCounter.get(3).scoreReset();
        setCounter.get(1).scoreReset();
        setCounter.get(2).scoreReset();
        setCounter.get(3).scoreReset();
        setScoreA=0;
        setScoreB=0;
        currentSet=1;
        displayScore();
        currentSet++;
        displayScore();
        currentSet++;
        displayScore();
        currentSet=1;
        breakScoreA = 0;
        TextView breakViewA = (TextView) findViewById(R.id.break_score_a);
        String bScore= Integer.toString(breakScoreA);
        breakViewA.setText(bScore);
        breakScoreB = 0;
        TextView breakViewB = (TextView) findViewById(R.id.break_score_b);
        String breaksB= Integer.toString(breakScoreB);
        breakViewB.setText(breaksB);


    }
}
