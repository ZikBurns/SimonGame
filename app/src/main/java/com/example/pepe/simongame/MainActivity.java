package com.example.pepe.simongame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import data.Records;

public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "records.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String strCurrentLine;
            String name;
            Integer points;
            Records records = Records.getInstance();
            while ((strCurrentLine = br.readLine()) != null) {
                Log.d("ERROR",strCurrentLine);
                String[] recstr = strCurrentLine.split(";");
                name = recstr[0];
                points = Integer.parseInt(recstr[1]);
                records.addRecord(name, points);
            }
        } catch (FileNotFoundException e){
            Toast.makeText(MainActivity.this,"FIRST GAME PLAYED",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.d("ERROR","ERROR");

        }
        setContentView(R.layout.activity_main);
    }
    public void onClickPlay(View view){
        startActivity(new Intent(this,AftermatchActivity.class));
    }
    public void onClickScores(View view){
        startActivity(new Intent(this,ScoresActivity.class));
    }

}
