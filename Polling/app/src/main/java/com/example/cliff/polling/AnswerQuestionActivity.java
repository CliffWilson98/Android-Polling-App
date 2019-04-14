package com.example.cliff.polling;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.mariuszgromada.math.mxparser.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class AnswerQuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private Question question;
    private String answerFormat;
    public static final String INTENT_TAG = "IS_ANSWER_CORRECT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_question);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        //the question is obtained from the intent
        int questionID = getIntent().getIntExtra(Question.ID_TAG, -1);
        question = Question.searchForQuestionById(questionID);
//        question = new GetQuestionByID().execute(endpointURL);


        //if the question is algorithmic then a version of the question must be generated
        if (question.isAlgorithmic())
        {
            //if the answer format contains brackets then a different process must be used
            //for generating the answer than if it did not
            if (question.getAnswerFormat().contains("["))
            {
                createComplexAlgorithmicVariant();
            }
            else
            {
                createAlgorithmicVariant();
            }
        }
        //otherwise it can be displayed without any changes
        else
        {
            displayStaticQuestion();
        }

        Button submitButton = (Button)findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.submitButton)
        {
            if(isAnswerCorrect())
            {
                Intent intent = new Intent(this, AnswerResultActivity.class);
                intent.putExtra(INTENT_TAG, true);
                startActivity(intent);
            }
            else
            {
                Intent intent = new Intent(this, AnswerResultActivity.class);
                intent.putExtra(INTENT_TAG, false);
                startActivity(intent);
            }

        }
    }

    //TODO make this disregard spaces when answering questionList. Also make it not case sensitive for word answers
    private boolean isAnswerCorrect()
    {
        String userAnswer = ((EditText)(findViewById(R.id.questionAnswer))).getText().toString();

        System.out.println("going to check for the answer!");
        System.out.println(userAnswer);
        System.out.println(answerFormat);

        if(userAnswer.equals(answerFormat))
        {
            Answer answer = new Answer(User.currentUser.getId(), User.currentUser.getName(), userAnswer, true);
            question.addAnswer(answer);
            User.userAnswers.add(new UserAnswer(question, answer));

            //Adding it to the database
            String endpointURL = "";
            try{
                endpointURL = "http://csci4661.com/API.php?endpoint=SaveAnswer&ID="+ question.getId() +"&username="+ URLEncoder.encode(User.currentUser.getName(),"UTF-8") +"&score="+ URLEncoder.encode("100","UTF-8") +"&question="+ URLEncoder.encode(question.getQuestion(),"UTF-8") +"&answer="+ URLEncoder.encode(userAnswer,"UTF-8") +"&attempted="+ URLEncoder.encode("Yes","UTF-8") +"";
            }
            catch(UnsupportedEncodingException e){System.out.println("Encoder Error! for GetQuestionByID API");}
            new SaveAnswer().execute(endpointURL);

            QuestionRecyclerFragment.myAdapter.notifyDataSetChanged();
            return true;
        }
        else
        {
            Answer answer = new Answer(User.currentUser.getId(), User.currentUser.getName(), userAnswer, false);
            question.addAnswer(answer);
            User.userAnswers.add(new UserAnswer(question, answer));

            //Adding it to the database
            String endpointURL = "";
            try{
                endpointURL = "http://csci4661.com/API.php?endpoint=SaveAnswer&ID="+ question.getId() +"&username="+ URLEncoder.encode(User.currentUser.getName(),"UTF-8") +"&score="+ URLEncoder.encode("0","UTF-8") +"&question="+ URLEncoder.encode(question.getQuestion(),"UTF-8") +"&answer="+ URLEncoder.encode(userAnswer,"UTF-8") +"&attempted="+ URLEncoder.encode("Yes","UTF-8") +"";
            }
            catch(UnsupportedEncodingException e){System.out.println("Encoder Error! for GetQuestionByID API");}
            new SaveAnswer().execute(endpointURL);

            QuestionRecyclerFragment.myAdapter.notifyDataSetChanged();
            return false;
        }
    }

    private void displayStaticQuestion()
    {
        TextView questionTitle = findViewById(R.id.questionTitle);
        String questionTitleString = String.format("Title: %s", question.getName());
        questionTitle.setText(questionTitleString);

        TextView questionTextView = findViewById(R.id.questionText);
        String questionTextString = String.format("Question: %s", question.getQuestion());
        questionTextView.setText(questionTextString);

        answerFormat = question.getAnswerFormat();
    }

    //two methods are required for the creation of an algorithmic question.
    //one will create the algorithmic variant and the other will assign its values to the
    //respective textviews
    private void createAlgorithmicVariant()
    {
        String questionText = question.getQuestion();

        int lowerBound = question.getLowerBound();
        int upperBound = question.getUpperBound();

        int randomValue = (int)((Math.random() * ((upperBound - lowerBound) + 1)) + lowerBound);
        String randomValueString = Integer.toString(randomValue);

        questionText = questionText.replace("@", randomValueString);

        answerFormat = question.getAnswerFormat();

        answerFormat = answerFormat.replace("@", randomValueString);

        Expression e = new Expression(answerFormat);
        answerFormat = String.valueOf(e.calculate());

        System.out.println("ANSWER FORMAT IN NEXT LINE!");
        System.out.println(answerFormat);
        displayAlgorithmicQuestion(questionText);
    }

    private void createComplexAlgorithmicVariant()
    {
        String questionText = question.getQuestion();
        int lowerBound = question.getLowerBound();
        int upperBound = question.getUpperBound();
        int randomValue = (int)((Math.random() * ((upperBound - lowerBound) + 1)) + lowerBound);
        String randomValueString = Integer.toString(randomValue);

        questionText = questionText.replace("@", randomValueString);

        answerFormat = question.getAnswerFormat();

        answerFormat = answerFormat.replace("@", randomValueString);

        int timesToLoop = countChar(answerFormat, '[');
        for (int i = 0; i < timesToLoop; i ++)
        {
            String retrieved = answerFormat.substring(answerFormat.indexOf("[") + 1, answerFormat.indexOf("]"));

            Expression e = new Expression(retrieved);
            retrieved = String.valueOf(e.calculate());
            retrieved = retrieved.replaceAll("\\.0*$", "");

            answerFormat = answerFormat.replaceFirst("\\[(.*?)\\]", retrieved);
        }
        System.out.println("Answer format!: " + answerFormat);

        displayAlgorithmicQuestion(questionText);

    }

    private void displayAlgorithmicQuestion(String questionText)
    {
        TextView questionTitle = findViewById(R.id.questionTitle);
        String questionTitleString = String.format("Title: %s", question.getName());
        questionTitle.setText(questionTitleString);

        TextView questionTextView = findViewById(R.id.questionText);
        String questionTextString = String.format("Question: %s", questionText);
        questionTextView.setText(questionTextString);
    }

    private int countChar(String str, char c)
    {
        int count = 0;

        for(int i=0; i < str.length(); i++)
        {    if(str.charAt(i) == c)
            count++;
        }

        return count;
    }

    //TODO make server calls to create a question and then use it onCreate()
//    private Question grabQuestion(int id){
//        Question temp
//        String endpointURL = "";
//        try{
//            endpointURL = "http://csci4661.com/API.php?endpoint=GetQuestionByID&ID="+ Question.ID_TAG +"&element="+ URLEncoder.encode("isAlgorithmic","UTF-8") +"";
//        }
//        catch(UnsupportedEncodingException e){System.out.println("Encoder Error! for GetQuestionByID API");}
//    }
}
