package com.example.cliff.polling;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionCardAdapter extends RecyclerView.Adapter<QuestionCardAdapter.ViewHolder>
{
    private ArrayList<String> names;
    private ArrayList<Integer> ids;

    public QuestionCardAdapter(ArrayList<String> names, ArrayList<Integer> ids)
    {
        this.names = names;
        this.ids = ids;
    }

    @Override
    public int getItemCount()
    {
        return names.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        CardView cardView = holder.cardView;

        TextView questionName = (TextView)cardView.findViewById(R.id.questionName);
        questionName.setText(String.format("Name: %s", names.get(position)));
        TextView questionID = (TextView)cardView.findViewById(R.id.questionID);
        questionID.setText(String.format("ID: %d", ids.get(position)));
    }

    @Override
    public QuestionCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate((R.layout.card_question), parent, false);
        return new QuestionCardAdapter.ViewHolder(cv);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private CardView cardView;

        public ViewHolder(CardView v)
        {
            super(v);
            cardView = v;
        }
    }

    /*public void addItem(int position, Question question)
    {
        names.add(position, question.getName());
        ids.add(position, question.getId());
        notifyItemInserted(position);
    }*/
}
