package com.example.pepe.simongame;

import android.widget.Button;

import java.util.LinkedList;

public class SequenceBlinksRunnable implements Runnable {

    private GameActivity mActivityObj;
    private LinkedList<Integer> colors;
    private Button redButton;
    private Button greenButton;
    private Button blueButton;
    private Button yellowButton;
    private int blinks;

    public SequenceBlinksRunnable(GameActivity activity){
        mActivityObj=activity;

        colors = mActivityObj.getColors();
        redButton = mActivityObj.getRedButton();
        greenButton = mActivityObj.getGreenButton();
        blueButton = mActivityObj.getBlueButton();
        yellowButton = mActivityObj.getYellowButton();
        blinks = mActivityObj.getBlinks();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            for (int i = 0; i < blinks; i++) {
                int currentIndex = colors.get(i);

                switch (currentIndex) {
                    case 0:
                        redButton.setBackgroundColor(0xffF80404);

                        Thread.sleep(1000);

                        redButton.setBackgroundColor(0xffE76B6B);

                        Thread.sleep(500);

                        break;
                    case 1:
                        greenButton.setBackgroundColor(0xff03F80A);

                        Thread.sleep(1000);

                        greenButton.setBackgroundColor(0xff86F389);

                        Thread.sleep(500);

                        break;
                    case 2:
                        blueButton.setBackgroundColor(0xff041DF8);

                        Thread.sleep(1000);

                        blueButton.setBackgroundColor(0xff5B6EDB);

                        Thread.sleep(500);

                        break;
                    case 3:
                        yellowButton.setBackgroundColor(0xffFCE204);

                        Thread.sleep(1000);

                        yellowButton.setBackgroundColor(0xffE4D97F);

                        Thread.sleep(500);

                        break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}