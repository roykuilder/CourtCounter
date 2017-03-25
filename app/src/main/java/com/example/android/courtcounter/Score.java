package com.example.android.courtcounter;

/**
 * Created by Kuilder on 18-03-17.
 */

public class Score {

    private int mA;
    private int mB;

    public Score(int scoreA, int scoreB){
        mA = scoreA;
        mB = scoreB;
    }

    public int getScoreA(){
        return mA;
    }

    public int getScoreB(){
        return mB;
    }

    public void addScoreA(){
        mA++;
    }
    public void addScoreB(){
        mB++;
    }
    public void scoreReset() {
        mA = 0;
        mB = 0;
    }

}
