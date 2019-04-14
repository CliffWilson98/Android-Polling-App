package com.example.cliff.polling;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder> {

    ArrayList<Answer> answerList;
    private Listener listener;

    public AnswerAdapter(ArrayList<Answer> answerList){
        this.answerList = answerList;
    }

    interface Listener{
        void onClick(int position);
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }


    @Override
    public int getItemCount() {
        return answerList.size();
    }

    @Override
    public AnswerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_answer, parent, false);
        ViewHolder temp = new ViewHolder(v);
        return temp;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){

        holder.answerName.setText("Name: " + answerList.get(position).getStudentName());
        holder.answerID.setText("ID: " + answerList.get(position).getStudentID());
        holder.answerDate.setText("Date: " + 0);

        if(answerList.get(position).isCorrect()){
            holder.color.setBackgroundColor(ContextCompat.getColor(holder.color.getContext(), R.color.correct));
        }
        else {
            holder.color.setBackgroundColor(ContextCompat.getColor(holder.color.getContext(), R.color.incorrect));
        }

        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(listener != null){
                    listener.onClick(position);
                }
            }
        });


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView answerName;
        TextView answerID;
        TextView answerDate;
        FrameLayout color;


        public ViewHolder(View v){
            super(v);
            cardView = (CardView)itemView.findViewById(R.id.answerCard);
            answerName =(TextView)itemView.findViewById(R.id.answerName);
            answerID =(TextView)itemView.findViewById(R.id.answerID);
            answerDate =(TextView)itemView.findViewById(R.id.answerDate);
            color =(FrameLayout)itemView.findViewById(R.id.answerColor);
        }
    }


}

