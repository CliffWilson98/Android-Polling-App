package com.example.cliff.polling;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Stack;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateQuestionFragment extends Fragment implements View.OnClickListener {

    public CreateQuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //storing the view to be inflated so that it may be used to set the clickListener of buttons
        //in this fragment
        View v = inflater.inflate(R.layout.fragment_create_question, container, false);

        Button helpButton = (Button) v.findViewById(R.id.helpButton);
        helpButton.setOnClickListener(this);

        Button createButton = (Button) v.findViewById(R.id.createButton);
        createButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.helpButton) {
            Intent intent = new Intent(getActivity(), HelpActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.createButton) {
            createQuestion();
        }
    }

    //TODO check whether or not there are appropriate values stored in these edit text fields before creating the question
    //TODO stop app from crashing whenever something that is not a number is entered in the lower and upper bounds
    //TODO maybe gray out the lower and upper bound values whenever the @ character is not present in the answer format.
    //TODO make sure that there is an expression that can be calculate by the parser between any brackets
    //this method will get the values stored in the edit text fields and use that to create a question
    public void createQuestion() {
        int id = (int) (Math.random() * 10000);
        String title = ((EditText) (getView().findViewById(R.id.questionTitle))).getText().toString();
        String questionText = ((EditText) (getView().findViewById(R.id.questionContent))).getText().toString();
        String answerFormat = ((EditText) (getView().findViewById(R.id.answerFormat))).getText().toString();
        int lowerBound = 0;
        int upperBound = 1;
        boolean isAlgorithmic = false;
        boolean inputIsValid = true;

        //if the @ symbol is present in the answer format then the bounds must be obtained from their respective edit text boxes
        //this may throw a NumberFormatException which would crash the app so a try catch block must be used.
        //if the input is not valid (meaning that there are inappropriate arguments for the bounds) then the question will not be created
        //and added to the users question list.
        //It must also be checked that the expression is properly balanced. Meaning that the parenthesis and brackets are correctly aligned
        if (answerFormat.contains("@") || questionText.contains("@")) {
            isAlgorithmic = true;

            try {
                lowerBound = Integer.parseInt(((EditText) (getView().findViewById(R.id.lowerBoundText))).getText().toString());
                upperBound = Integer.parseInt(((EditText) (getView().findViewById(R.id.upperBoundText))).getText().toString());
            } catch (NumberFormatException e) {
                Toast toast = Toast.makeText(getActivity(), "If @ is present in your answer format or question then you must specify an appropriate variable range!", Toast.LENGTH_LONG);
                toast.show();
                inputIsValid = false;
            }

            if (lowerBound >= upperBound) {
                Toast toast = Toast.makeText(getActivity(), "Your lower bound cannot be greater than or equal to your upper bound!", Toast.LENGTH_LONG);
                toast.show();
                inputIsValid = false;
            }
            if (!isExpressionBalanced(answerFormat)) {
                Toast toast = Toast.makeText(getActivity(), "Improper Formatting on '[', '{', or '('!", Toast.LENGTH_LONG);
                toast.show();
                inputIsValid = false;
            }
        }
        //if there is no content in any of the following fields then the question input is not valid
        if (title.equals("") || answerFormat.equals("") || questionText.equals("")) {
            Toast toast = Toast.makeText(getActivity(), "Please fill in the required fields!", Toast.LENGTH_LONG);
            toast.show();
            inputIsValid = false;
        }

        //if the input is valid then the question can be made
        if (inputIsValid == true) {
            Toast toast = Toast.makeText(getActivity(), "Your question is now visible on the my questionList screen!", Toast.LENGTH_SHORT);
            toast.show();

            ArrayList<Answer> answers = new ArrayList<>();

            String endpointURL = "";
            try {
                endpointURL = "http://csci4661.com/API.php?endpoint=CreateQuestion&question=" + URLEncoder.encode(questionText, "UTF-8") + "&answerFormat=" + URLEncoder.encode(answerFormat, "UTF-8") + "&username=" + URLEncoder.encode(User.currentUser.getName(), "UTF-8") + "&isAlgorithmic=" + isAlgorithmic + "&varMin=" + lowerBound + "&varMax=" + upperBound + "&title=" + URLEncoder.encode(title, "UTF-8") + "";
            } catch (UnsupportedEncodingException e) {
                System.out.println("Encoder Error! for createQuestion API");
            }

            Question question = new Question(id, title, questionText, answerFormat, lowerBound, upperBound, answers, isAlgorithmic);
//            NewQuestion.createQuestion(questionText, lowerBound, upperBound, answerFormat, User.currentUser.getName(), title, isAlgorithmic);
            new NewQuestion().execute(endpointURL);
            Question.questions.add(question);
        }
    }

    //This method will make sure that the '(', '[', and '{' in the
    //given expression are aligned properly. If there is a mistake
    //then the app will inevitably crash whenever the answer is calculated
    private boolean isExpressionBalanced(String expression) {
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}') {
                //nothing to match with
                if (stack.isEmpty()) {
                    return false;
                } else {
                    if (ch == ')' && stack.pop() != '(') {
                        return false;
                    } else if (ch == ']' && stack.pop() != '[') {
                        return false;
                    } else if (ch == '}' && stack.pop() != '{') {
                        return false;
                    }
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }


}
