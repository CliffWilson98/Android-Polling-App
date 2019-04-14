package com.example.cliff.polling;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//TODO make it so that a user cannot hit the back button from this activity
public class AnswerResultActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_result);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        Button nextButton = findViewById(R.id.answerResultButton);
        nextButton.setOnClickListener(this);

        boolean answerIsCorrect = getIntent().getBooleanExtra(AnswerQuestionActivity.INTENT_TAG, true);
        TextView textView = (TextView)findViewById(R.id.answerResultText);

        if (answerIsCorrect)
        {
            textView.setText("Congratulations! Your answer is correct!");
        }
        else
        {
            textView.setText("Sorry. Your answer is incorrect.");
        }
    }

    public void onClick(View v)
    {
        if (v.getId() == R.id.answerResultButton)
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
