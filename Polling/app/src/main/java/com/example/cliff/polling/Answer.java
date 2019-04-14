package com.example.cliff.polling;

import java.util.ArrayList;

public class Answer {
    private String studentID;
    private String studentName;
    private String answer;
    private boolean correct;


    Answer(String studentID, String studentName, String answer, boolean correct){
        this.studentID = studentID;
        this.studentName = studentName;
        this.answer = answer;
        this.correct = correct;
    }


    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
