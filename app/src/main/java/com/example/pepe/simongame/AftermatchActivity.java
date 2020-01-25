package com.example.pepe.simongame;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import data.Records;
import data.Score;

public class AftermatchActivity extends AppCompatActivity {
    private static final String FILENAME = "records.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ERROR","Helloem");
        setContentView(R.layout.activity_aftermatch);
        Button button=findViewById(R.id.alrightbutton);
    }
    public void onClickReturn(View view) {
        EditText et=findViewById(R.id.editText);
        TextView temporalscore=findViewById(R.id.temporalscore);
        int score=Score.getInstance().getScore();
        temporalscore.setText(score);
        String playerName=et.getText().toString();
        Records records= Records.getInstance();
        Map<String,Integer> map=records.getOrdered();
        int valor=0;
        if (map.get(playerName)!=null )valor=map.get(playerName);
        //If it's a new player or NEW RECORD FOR THE PLAYER ---->>> UPDATE FILE AND
        if(!map.containsKey(playerName) || (score>valor)) {
            records.addRecord(playerName, score);
            FileOutputStream fos = null;
            try {
                fos=openFileOutput(FILENAME, Context.MODE_PRIVATE);
                String str=records.toFile();
                fos.write(str.getBytes());
                fos.close();
                //Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILENAME, Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                Toast.makeText(AftermatchActivity.this,"FileNotFound",Toast.LENGTH_SHORT).show();
                Log.d("ERROR","ERROR");
            } catch (IOException e) {
                Toast.makeText(AftermatchActivity.this,"Error writing file",Toast.LENGTH_SHORT).show();
                Log.d("ERROR","ERROR");
            }
            Log.d("ERROR","Hello2");
        }

        startActivity(new Intent(this,MainActivity.class));
    }
}
