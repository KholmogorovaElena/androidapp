package com.example.myapplication;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {

    private Button yesBtn;
    private Button noBtn;
    private Button showAnswer;
    private TextView textView;
    private Button isResultAnswer;
    private TextView resultAnswer;
    private StringBuilder resultOfanswer = new StringBuilder();
   

    private final Question[] questions = new Question[]{
            new Question(R.string.question0,true),
            new Question(R.string.question1,false),
            new Question(R.string.question2,false),
            new Question(R.string.question3,true),
            new Question(R.string.question4,false),
            new Question(R.string.question5, true),
            new Question(R.string.question6, false),
            new Question(R.string.question7, true),
            new Question(R.string.question8,true),
            new Question(R.string.question9,false)
    };
    private int questionIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startService(new Intent(MainActivity.this, SoundService.class));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("SYSTEM INFO: ", "Метод onCreate() запущен");

        if(savedInstanceState != null){
            questionIndex = savedInstanceState.getInt("questionIndex");
        }

        yesBtn = findViewById(R.id.btnYes);
        noBtn = findViewById(R.id.btnNo);
        textView = findViewById(R.id.textView);
        showAnswer = findViewById(R.id.showAnswer);
        isResultAnswer = findViewById(R.id.isResultAnswer);
        resultAnswer = findViewById(R.id.resultAnswer);
        textView.setText(questions[questionIndex].getQuestionResId());


        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questions[questionIndex].isAnswerTrue()) {
                    Toast.makeText(MainActivity.this, R.string.correct, Toast.LENGTH_SHORT).show();
                    setResult(questionIndex, true);
                } else {
                    Toast.makeText(MainActivity.this, R.string.incorrect, Toast.LENGTH_SHORT).show();
                    setResult(questionIndex, false);
                }

                if(questionIndex < (questions.length - 1))

            {
                questionIndex++;
                textView.setText(questions[questionIndex].getQuestionResId());
            } else

            {
                showResult();
            }
        }
    });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questions[questionIndex].isAnswerTrue()) {
                    Toast.makeText(MainActivity.this, R.string.incorrect, Toast.LENGTH_SHORT).show();
                    setResult(questionIndex, false);
                } else {
                    Toast.makeText(MainActivity.this, R.string.correct, Toast.LENGTH_SHORT).show();
                    setResult(questionIndex, true);
                }
                if (questionIndex < (questions.length - 1)) {
                    questionIndex++;
                    textView.setText(questions[questionIndex].getQuestionResId());
                } else {
                    showResult();
                }
            }
        });
        showAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
                intent.putExtra("answer",questions[questionIndex].isAnswerTrue());
                startActivity(intent);
            }
        });
        isResultAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(intent);
                showResult();
            }
        });
    }

    public void setResult(int num, boolean question) {
        resultOfanswer.append("Вопрос №" + (num+1) + ". " +  ((question) ? "Правильно !" : "Неправильно! ") +"\n");
    }

    public void showResult() {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);

        intent.putExtra("resultList", resultOfanswer.toString());
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        stopService(new Intent(MainActivity.this, SoundService.class));
        super.onBackPressed();
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d("SYSTEM INFO: ", "Метод onStart() запущен");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d("SYSTEM INFO: ", "Метод onResume() запущен");
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.d("SYSTEM INFO: ", "Метод onSaveInstanceState() запущен");
        savedInstanceState.putInt("questionIndex",questionIndex);

    }
    @Override
    public void onPause(){
        super.onPause();

        Log.d("SYSTEM INFO: ", "Метод onPause() запущен");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d("SYSTEM INFO: ", "Метод onStop() запущен");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("SYSTEM INFO: ", "Метод onDestroy() запущен");
    }
}