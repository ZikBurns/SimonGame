package com.example.pepe.simongame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import data.Score;

import java.util.LinkedList;

public class GameActivity extends AppCompatActivity {


    private Button red_Button;
    private Button green_Button;
    private Button blue_Button;
    private Button yellow_Button;
    private MediaPlayer buttonClickSound;
    private MediaPlayer winningSound;
    private MediaPlayer errorSound;

    private LinkedList<Integer> colors;
    private int score;  //Increase every turn

    private int sequenceIndex;
    private int colorIndex;  //RED=0, GREEN=1, BLUE=2, YELLOW=3

    private int rand;  //0, 1, 2, or 3

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Inizialize sounds
        buttonClickSound = MediaPlayer.create(this, R.raw.button_click_sound);
        winningSound =  MediaPlayer.create(this, R.raw.winning_sound);
        errorSound =  MediaPlayer.create(this, R.raw.error_sound);

        //RED BUTTON
        red_Button = (Button)findViewById(R.id.red_Button);
        //Listener
        red_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorIndex = 0;
                buttonClickSound.start();

                if(sequenceIndex != colors.size()-1) {
                    if (correctClick(0, sequenceIndex)) {
                        sequenceIndex++;
                    } else {
                        errorSound.start();
                        lost_game();
                    }
                }
                else {
                    if (correctClick(colorIndex, sequenceIndex)) {
                        winningSound.start();
                        win();
                    }
                    else {
                        errorSound.start();
                        lost_game();
                    }
                }
            }
        });

        //GREEN BUTTON
        green_Button = (Button)findViewById(R.id.green_Button);
        //Listener
        green_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorIndex = 1;
                buttonClickSound.start();

                if(sequenceIndex != colors.size()-1) {
                    if (correctClick(colorIndex, sequenceIndex)) {
                        sequenceIndex++;
                    } else{
                        errorSound.start();
                        lost_game();
                    }
                }
                else {
                    if (correctClick(colorIndex, sequenceIndex)){
                        winningSound.start();
                        win();
                    }
                    else{
                        errorSound.start();
                        lost_game();
                    }
                }

            }
        });

        //BLUE BUTTON
        blue_Button = (Button)findViewById(R.id.blue_Button);
        //Listener
        blue_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorIndex = 2;
                buttonClickSound.start();

                if(sequenceIndex != colors.size()-1) {
                    if (correctClick(colorIndex, sequenceIndex)) {
                        sequenceIndex++;
                    } else{
                        errorSound.start();
                        lost_game();
                    }
                }
                else {
                    if (correctClick(colorIndex, sequenceIndex)){
                        winningSound.start();
                        win();
                    }
                    else{
                        errorSound.start();
                        lost_game();
                    }
                }

            }
        });

        //YELLOW BUTTON
        yellow_Button = (Button)findViewById(R.id.yellow_Button);
        //Listener
        yellow_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorIndex = 3;
                buttonClickSound.start();

                if(sequenceIndex != colors.size()-1) {
                    if (correctClick(colorIndex, sequenceIndex)) {
                        sequenceIndex++;
                    } else{
                        errorSound.start();
                        lost_game();
                    }
                }
                else {
                    if (correctClick(colorIndex, sequenceIndex)){
                        winningSound.start();
                        win();
                    }
                    else{
                        errorSound.start();
                        lost_game();
                    }
                }
            }
        });
        playGame();
    }

    //Every turn adds a random number in the LinkedList, the number is refered to a color
    public void fillList() {

        switch (rand = (int)(Math.random() * 4)) {
            case 0:
                colors.add(0);  //Red Button
                break;
            case 1:
                colors.add(1);  //Green Button
                break;
            case 2:
                colors.add(2);  //Blue Button
                break;
            case 3:
                colors.add(3);  //Yellow Button
                break;
            default:
                break;
            }
    }

    //Start the first turn of the game setting all the needed variables
    public void playGame () {
        score = 0;
        colors = new LinkedList<>();
        sequenceIndex = 0;
        fillList();

        startSequence();
    }

    //It is called when you win the turn
    public void win() {
        Toast.makeText(GameActivity.this,
                "CORRECT! +1",
                Toast.LENGTH_SHORT).show();

        sequenceIndex = 0;
        score++;

        fillList();
        TextView tv = (TextView)findViewById(R.id.actualScore);
        tv.setText(String.valueOf(score));

        startSequence();
    }

    //Control the correctness of the botton click on the sequence
    public boolean correctClick(int colorIndex, int sequenceIndex) {
        if(colorIndex == colors.get(sequenceIndex))
            return true;
        else
            return false;
    }

    //It is called when you mistake the colors sequence
    public void lost_game() {
        Toast.makeText(GameActivity.this,
                "YOU LOST",
                Toast.LENGTH_SHORT).show();

        Score.getInstance().setScore(score);
        startActivity(new Intent(this,AftermatchActivity.class));

    }

    //Starts the colors blinking, which represent the sequence to perform
    public void startSequence() {
        red_Button.setClickable(false);
        blue_Button.setClickable(false);
        green_Button.setClickable(false);
        yellow_Button.setClickable(false);

        Thread thread = new Thread() {
            public void run() {
                try {
                    Thread.sleep(1000);
                    for (int i = 0; i < colors.size(); i++) {
                        int currentIndex = colors.get(i);

                        switch (currentIndex) {
                            case 0:
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        red_Button.setBackgroundColor(0xffF80404);
                                    }
                                });

                                Thread.sleep(1000);
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        red_Button.setBackgroundColor(0xffE76B6B);
                                    }
                                });
                                Thread.sleep(500);
                                break;
                            case 1:

                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        green_Button.setBackgroundColor(0xff03F80A);
                                    }
                                });

                                Thread.sleep(1000);

                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        green_Button.setBackgroundColor(0xff86F389);
                                    }
                                });
                                Thread.sleep(500);
                                break;
                            case 2:
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        blue_Button.setBackgroundColor(0xff041DF8);
                                    }
                                });

                                Thread.sleep(1000);

                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        blue_Button.setBackgroundColor(0xff5B6EDB);
                                    }
                                });
                                Thread.sleep(500);
                                break;
                            case 3:
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        yellow_Button.setBackgroundColor(0xffFCE204);
                                    }
                                });
                                Thread.sleep(1000);

                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        yellow_Button.setBackgroundColor(0xffE4D97F);
                                    }
                                });
                                Thread.sleep(500);
                                break;
                        }
                    }
                    red_Button.setClickable(true);
                    blue_Button.setClickable(true);
                    green_Button.setClickable(true);
                    yellow_Button.setClickable(true);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}

