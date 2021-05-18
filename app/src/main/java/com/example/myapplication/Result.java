package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    private TextView resultTextView;
    private boolean isResultTrue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);
     isResultTrue = getIntent().getBooleanExtra("result", false);
        resultTextView = findViewById(R.id.answerTextView);
        resultTextView.setText(isResultTrue?R.string.correct:R.string.incorrect);


    }
}