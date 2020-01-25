package com.example.pepe.simongame;

import android.content.Intent;
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

    private LinkedList<Integer> colors= new LinkedList<>();
    private int score;  //incrementa ogni turno
    private int blinks;  //currentTurn +2

    private int sequenceIndex;
    private int colorIndex;  //numero assagnato ad ogni colore RED=0, GREEN=1, BLUE=2, YELLOW=3


    //DEBUG
    //String string;

    private int rand;  //0, 1, 2, or 3

    public int getBlinks() {
        return blinks;
    }

    public LinkedList<Integer> getColors() {
        return colors;
    }

    public Button getRedButton(){
        return red_Button;
    }

    public Button getGreenButton(){
        return green_Button;
    }

    public Button getBlueButton(){
        return blue_Button;
    }

    public Button getYellowButton(){
        return yellow_Button;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        //RED BUTTON
        red_Button = (Button)findViewById(R.id.red_Button);
        //Listener
        red_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorIndex = 0;

                if(sequenceIndex != blinks-1) {
                    if (correctClick(0, sequenceIndex)) {
                        sequenceIndex++;
                    } else
                        lost_game();
                }
                else {
                    if (correctClick(colorIndex, sequenceIndex)) {
                        win();
                        ((TextView)findViewById(R.id.actualScore)).setText(score);
                    }
                    else
                        lost_game();
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

                if(sequenceIndex != blinks-1) {
                    if (correctClick(colorIndex, sequenceIndex)) {
                        sequenceIndex++;
                    } else
                        lost_game();
                }
                else {
                    if (correctClick(colorIndex, sequenceIndex)) {
                        win();
                        ((TextView)findViewById(R.id.actualScore)).setText(score);
                    }
                    else
                        lost_game();
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

                if(sequenceIndex != blinks-1) {
                    if (correctClick(colorIndex, sequenceIndex)) {
                        sequenceIndex++;
                    } else
                        lost_game();
                }
                else {
                    if (correctClick(colorIndex, sequenceIndex)) {
                        win();
                        ((TextView)findViewById(R.id.actualScore)).setText(score);
                    }
                    else
                        lost_game();
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

                if(sequenceIndex != blinks-1) {
                    if (correctClick(colorIndex, sequenceIndex)) {
                        sequenceIndex++;
                    } else
                        lost_game();
                }
                else {
                    if (correctClick(colorIndex, sequenceIndex)) {
                        win();
                        ((TextView)findViewById(R.id.actualScore)).setText(score);
                    }
                    else
                        lost_game();
                }
            }
        });
        playGame();
    }

    public void fillList(int blinks) {

        sequenceIndex = 0;

        colors = new LinkedList<>();

        //DEBUG
        //string = "";

        for(int i = 0; i<blinks; i++){  //Lo fa 3 volte al primo turno, poi va incrementando
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

        //DEBUG
        /*
        for(int i = 0; i<blinks; i++){
            string += colors.get(i) + " ";
        }

         */
    }

    public void playGame () {

        blinks = 3;
        score = 1;
        fillList(blinks);


        //DEBUG
        /*
        Toast.makeText(GameActivity.this,
                string,
                Toast.LENGTH_SHORT).show();

         */

        startSequence();

    }

    public void win() {

        Toast.makeText(GameActivity.this,
                "Win",
                Toast.LENGTH_SHORT).show();

        score++;
        blinks++;
        fillList(blinks);


        //DEBUG
        /*
        Toast.makeText(GameActivity.this,
                string,
                Toast.LENGTH_SHORT).show();

         */

        startSequence();
    }


    public boolean correctClick(int colorIndex, int sequenceIndex) {
        if(colorIndex == colors.get(sequenceIndex))
            return true;
        else
            return false;
    }


    public void lost_game() {

        Toast.makeText(GameActivity.this,
                "YOU LOST",
                Toast.LENGTH_SHORT).show();

        Score.getInstance().setScore(score);
        startActivity(new Intent(this,AftermatchActivity.class));

    }


    public void startSequence() {
        SequenceBlinksRunnable seqRun = new SequenceBlinksRunnable(this);
        Thread thread = new Thread(seqRun);
        thread.start();
    }
}
