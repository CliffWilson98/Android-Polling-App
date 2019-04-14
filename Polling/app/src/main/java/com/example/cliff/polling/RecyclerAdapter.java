package com.example.cliff.polling;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_QUESTION = 0;
    public static final int TYPE_ANSWER = 1;

    List<Object> objectList;

//    ArrayList<Question> questionList;
//    ArrayList<UserAnswer> userList;
    private Listener listener;
//
//    public RecyclerAdapter(ArrayList<Question> questionList, ArrayList<UserAnswer> userList){
//        this.questionList = questionList;
//        this.userList = userList;
//    }

    public RecyclerAdapter(List<Object> objectList){
        this.objectList = objectList;
    }

    interface Listener{
        void onClick(int position);
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }


    @Override
    public int getItemCount() {
        return objectList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (objectList.get(position) instanceof Question) {
            return TYPE_QUESTION;
        } else if (objectList.get(position) instanceof UserAnswer) {
            return TYPE_ANSWER;
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case TYPE_QUESTION:
                View v1 = inflater.inflate(R.layout.card_question, parent, false);
                viewHolder = new ViewHolder1(v1);
                break;
            case TYPE_ANSWER:
                View v2 = inflater.inflate(R.layout.card_answer, parent, false);
                viewHolder = new ViewHolder2(v2);
                break;
            default:
                View v = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
                viewHolder = new ViewHolder1(v);
                break;
        }
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position){

        switch (holder.getItemViewType()){
            case TYPE_QUESTION:
                ViewHolder1 vh1 = (ViewHolder1) holder;
                configureViewHolder1(vh1, position);
                break;
            case TYPE_ANSWER:
                ViewHolder2 vh2 = (ViewHolder2) holder;
                configureViewHolder2(vh2, position);
                break;
            default:
                ViewHolder1 vh = (ViewHolder1) holder;
                configureViewHolder1(vh, position);
                break;
        }





    }

    public void configureViewHolder1(ViewHolder1 vh1,final int position){
        Question tempQuestion = (Question) objectList.get(position);
        if(tempQuestion != null){
            vh1.questionName.setText("Name: " + tempQuestion.getName());
            vh1.questionID.setText("ID: " + tempQuestion.getId());
            vh1.questionCorrect.setText("Correct: " + tempQuestion.numberOfCorrect());
            vh1.questionIncorrect.setText("Incorrect: " + tempQuestion.numberOfIncorrect());
            vh1.questionTotal.setText("Total: " + tempQuestion.numberOfAnswers());
            vh1.cardView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(listener != null){
                        listener.onClick(position);
                    }
                }
            });
        }
    }

    public void configureViewHolder2(ViewHolder2 vh2, final int position){
        UserAnswer tempAnswer = (UserAnswer) objectList.get(position);

        if(tempAnswer !=null){
            vh2.nameAnswer.setText("Name: " + tempAnswer.getQuestion().getName());
            vh2.idAnswer.setText("ID: " + tempAnswer.getQuestion().getId());
            vh2.dateAnswer.setText("Date: " + 0);

            if(tempAnswer.getAnswer().isCorrect()){
                vh2.color.setBackgroundColor(ContextCompat.getColor(vh2.color.getContext(), R.color.correct));
            }
            else {
                vh2.color.setBackgroundColor(ContextCompat.getColor(vh2.color.getContext(), R.color.incorrect));
            }

            //TODO make an activity to see question details from this on click listener
//            vh2.cardAnswer.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v){
//                    if(listener != null){
//                        listener.onClick(position);
//                    }
//                }
//            });
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder1 extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView questionName;
        TextView questionID;
        TextView questionCorrect;
        TextView questionIncorrect;
        TextView questionTotal;


        public ViewHolder1(View v){
            super(v);
            cardView = (CardView)itemView.findViewById(R.id.questionCard);
            questionName =(TextView)itemView.findViewById(R.id.questionName);
            questionID =(TextView)itemView.findViewById(R.id.questionID);
            questionCorrect =(TextView)itemView.findViewById(R.id.questionCorrect);
            questionIncorrect =(TextView)itemView.findViewById(R.id.questionIncorrect);
            questionTotal = (TextView) itemView.findViewById(R.id.questionTotal);
        }
    }

    public static class ViewHolder2 extends RecyclerView.ViewHolder{
        CardView cardAnswer;
        TextView nameAnswer;
        TextView idAnswer;
        TextView dateAnswer;
        FrameLayout color;


        public ViewHolder2(View v){
            super(v);
            cardAnswer = (CardView)itemView.findViewById(R.id.answerCard);
            nameAnswer =(TextView)itemView.findViewById(R.id.answerName);
            idAnswer =(TextView)itemView.findViewById(R.id.answerID);
            dateAnswer =(TextView)itemView.findViewById(R.id.answerDate);
            color =(FrameLayout)itemView.findViewById(R.id.answerColor);
        }
    }


}

