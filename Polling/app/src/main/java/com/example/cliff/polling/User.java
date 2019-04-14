package com.example.cliff.polling;

import java.util.ArrayList;

public class User {
    private String id;
    private String name;

    public static ArrayList<UserAnswer> userAnswers = new ArrayList<UserAnswer>();

    public static User currentUser = new User();

    User(){

    }

    User(String id, String name){
        this.id = id;
        this.name = name;
    }

    public void fillList(){
        for(int i = 0; i < Question.questions.size(); i++){
            for (int j = 0; j < Question.questions.get(i).getAnswers().size(); i++){
                if (Question.questions.get(j).getAnswers().get(j).getStudentID().equals(User.currentUser.getId())){
                    UserAnswer temp = new UserAnswer(Question.questions.get(i),  Question.questions.get(i).getAnswers().get(j));
                    User.userAnswers.add(temp);
                }
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
