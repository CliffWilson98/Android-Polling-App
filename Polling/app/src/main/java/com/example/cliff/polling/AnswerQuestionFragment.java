package com.example.cliff.polling;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnswerQuestionFragment extends Fragment implements View.OnClickListener {


    public AnswerQuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_answer_question, container, false);

        Button searchButton = (Button)v.findViewById(R.id.searchForQuestionButton);
        searchButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v)
    {
        //if the search for question button is clicked then it must first be checked that the question searched for exists
        //before going to the activity to answer it
        if (v.getId() == R.id.searchForQuestionButton)
        {
            int questionID;

            //this try catch block will prevent the app from crashing if there is no number entered in the edit text field
            try
            {
                 questionID = Integer.parseInt(((EditText)(getActivity().findViewById(R.id.answerIDField))).getText().toString());
            }
            catch(NumberFormatException e)
            {
                questionID = -1;
            }

            //if searchForQuestionByID does not return a question with an id of -1 then that must mean that the
            //question exists and the activity is started
            if (Question.searchForQuestionById(questionID).getId() != -1)
            {
                Intent intent = new Intent(getActivity(), AnswerQuestionActivity.class);
                intent.putExtra(Question.ID_TAG, questionID);
                startActivity(intent);
            }
            //otherwise a toast will popup telling the user the question was not found
            else
            {
                Toast toast = Toast.makeText(getActivity(),"No question was found with that ID!", Toast.LENGTH_SHORT);
                toast.show();
            }

        }
    }

}
