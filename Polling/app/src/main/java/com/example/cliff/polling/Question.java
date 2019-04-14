package com.example.cliff.polling;

import java.io.Serializable;
import java.util.ArrayList;

//TODO allow for this class to actually generate algorithmic questionList based off of user input!
public class Question
{
    public static final String ID_TAG = "QUESTION_ID";
    private int id;
    private String name;
    private String question;
    private String answerFormat;
    private int lowerBound;
    private int upperBound;
    private String algorithmicQuestion;
    private boolean isAlgorithmic;

    private ArrayList<Answer> answers = new ArrayList<Answer>();

    public static ArrayList<Question> questions = new ArrayList<Question>(5);

    public Question(int id, String name, String question, String answerFormat, int lowerBound, int upperBound, ArrayList<Answer> answers, boolean isAlgorithmic)
    {
        this.id = id;
        this.name = name;
        this.question = question;
        this.answerFormat = answerFormat;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.answers = answers;
        this.isAlgorithmic = isAlgorithmic;
    }

    //this will search for a question in the arrayList by ID.
    //if the question is not found then it will return a dummy question with an ID of -1.
    public static Question searchForQuestionById(int id)
    {
        for (int i = 0; i < Question.questions.size(); i ++)
        {
            if(Question.questions.get(i).getId() == id)
            {
                return Question.questions.get(i);
            }
        }

        return new Question(-1, null, null, null, 0, 1, null, false);
    }

    public void addAnswer(Answer answer){
        answers.add(answer);
    }

    public int numberOfAnswers(){
        return answers.size();
    }

    public int numberOfCorrect(){
        int correct = 0;

        for(int i = 0; i < answers.size(); i++){
            if(answers.get(i).isCorrect()){
                correct++;
            }
        }

        return correct;
    }

    public int numberOfIncorrect(){
        int incorrect = 0;

        for(int i = 0; i < answers.size(); i++){
            if(answers.get(i).isCorrect() == false){
                incorrect++;
            }
        }

        return incorrect;
    }



    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getAnswerFormat()
    {
        return answerFormat;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public boolean isAlgorithmic()
    {
        return isAlgorithmic;
    }

    public int getLowerBound()
    {
        return lowerBound;
    }

    public int getUpperBound()
    {
        return upperBound;
    }
}
