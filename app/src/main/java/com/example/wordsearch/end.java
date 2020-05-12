package com.example.wordsearch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class end extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        Intent intent = getIntent();
        String time = intent.getExtras().getString("timeElapsed");

        TextView result = findViewById(R.id.result);
        result.setText("Game finished in "+time+" seconds.");
    }

    public void replay(View view) {
        Intent intent= new Intent(this, gameActivity.class);
        startActivity(intent);
    }

    public void quit(View view) {
        finish();
    }

}
