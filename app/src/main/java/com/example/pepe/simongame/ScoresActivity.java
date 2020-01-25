package com.example.pepe.simongame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import data.Records;

public class ScoresActivity extends AppCompatActivity {
    private static final String FILENAME = "records.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        Button resetbutton=findViewById(R.id.reset);
        //UNCOMMENT FOR HIDING RESET BUTTON
        //resetbutton.setVisibility(View.GONE);


        TextView multiLine = findViewById(R.id.textViewNames);
        multiLine.setMovementMethod(new ScrollingMovementMethod());
        Records records= Records.getInstance();
        Map<String,Integer> map=records.getTOP15();

        String text="";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            text=text+entry.getKey() + "\n";
        }
        multiLine.setText(text);
        text="";
        TextView multiLineRecords=findViewById(R.id.textViewscores);
        multiLineRecords.setMovementMethod(new ScrollingMovementMethod());
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            text=text+entry.getValue() + "\n";
        }
        multiLineRecords.setText(text);
    }
    public void onClickReset(View view) {
        FileOutputStream fos = null;
        try {
            fos=openFileOutput(FILENAME, Context.MODE_PRIVATE);
            String str="";
            fos.write(str.getBytes());
            fos.close();
            //Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILENAME, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(ScoresActivity.this,"FileNotFound",Toast.LENGTH_SHORT).show();
            Log.d("ERROR","ERROR");
        } catch (IOException e) {
            Toast.makeText(ScoresActivity.this,"Error writing file",Toast.LENGTH_SHORT).show();
            Log.d("ERROR","ERROR");
        }
        Records records = Records.getInstance();
        records.resetList();
        TextView multiLine = findViewById(R.id.textViewNames);
        multiLine.setText("");
        TextView multiLineRecords=findViewById(R.id.textViewscores);
        multiLineRecords.setText("");
    }
}
