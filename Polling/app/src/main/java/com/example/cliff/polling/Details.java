package com.example.cliff.polling;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static final String QUESTION_ID = "questionID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        int questionId = (Integer) getIntent().getExtras().get(QUESTION_ID);

        mRecyclerView = (RecyclerView) findViewById(R.id.detail_recycler);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new AnswerAdapter(Question.questions.get(questionId).getAnswers());
        mRecyclerView.setAdapter(mAdapter);

        String questionName = ("Name: " + Question.questions.get(questionId).getName());
        TextView name = (TextView) findViewById(R.id.detailName);
        name.setText(questionName);

        String qid = ("ID: " + Question.questions.get(questionId).getId());
        TextView id = (TextView) findViewById(R.id.detailID);
        id.setText(qid);

        String getCorrect = ("Correct: " + Question.questions.get(questionId).numberOfCorrect());
        TextView correct = (TextView) findViewById(R.id.detailCorrect);
        correct.setText(getCorrect);

        String getIncorrect = ("Incorrect: " + Question.questions.get(questionId).numberOfIncorrect());
        TextView incorrect = (TextView) findViewById(R.id.detailIncorrect);
        incorrect.setText(getIncorrect);

        String getTotal = ("Total: " + Question.questions.get(questionId).numberOfAnswers());
        TextView total = (TextView) findViewById(R.id.detailTotal);
        total.setText(getTotal);

    }
}
