package com.example.cliff.polling;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionRecyclerFragment extends Fragment {

    private QuestionCardAdapter adapter;
    private RecyclerView questionRecycler;

    //my adapter
    public static RecyclerAdapter myAdapter;

    public QuestionRecyclerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /**
         *Temporarily commented out your code since I'm implementing my own recycler
         **/

        View rootView = inflater.inflate(R.layout.fragment_question_recycler, container, false);

        //RecyclerView rootView = (RecyclerView)inflater.inflate(R.layout.tab2_card, container, false);

        List<Object> obj = new ArrayList<>();

        for (int i = 0; i < Question.questions.size(); i++){
            obj.add(Question.questions.get(i));
        }

        for (int i = 0; i < User.userAnswers.size(); i++){
            obj.add(User.userAnswers.get(i));
        }

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.question_recycler);

        myAdapter = new RecyclerAdapter(obj);
        recyclerView.setAdapter(myAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        myAdapter.setListener(new RecyclerAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), Details.class);
                intent.putExtra(Details.QUESTION_ID, position);
                getActivity().startActivity(intent);
            }
        });


        return rootView;


//        questionRecycler = (RecyclerView)inflater.inflate(R.layout.fragment_question_recycler, container, false);
//
//        int numberOfQuestions = Question.questionList.size();
//
//        ArrayList<String> questionNames = new ArrayList<String>();
//        for (int i = 0; i < numberOfQuestions; i++)
//        {
//            questionNames.add(Question.questionList.get(i).getName());
//        }
//
//        ArrayList<Integer> questionIDs = new ArrayList<Integer>(Question.questionList.size());
//        for (int i = 0; i < numberOfQuestions; i++)
//        {
//            questionIDs.add(Question.questionList.get(i).getId());
//        }
//
//        adapter = new QuestionCardAdapter(questionNames, questionIDs);
//        questionRecycler.setAdapter(adapter);
//
//        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
//        questionRecycler.setLayoutManager(layoutManager);
//
//        /*adapter.setListener(new HunterCardAdapter.Listener()
//        {
//            @Override
//            public void onClick(int position)
//            {
//                Intent intent = new Intent(getActivity(), DetailActivity.class);
//                intent.putExtra(DetailActivity.EXTRA_HUNTER_ID, (long)position);
//                getActivity().startActivity(intent);
//            }
//        });*/

        //return questionRecycler;
    }

}
