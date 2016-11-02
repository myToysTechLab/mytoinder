package de.mytoys.mobile.mytoinder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andtinder.model.CardModel;
import com.andtinder.model.Orientations;
import com.andtinder.view.CardContainer;
import com.andtinder.view.SimpleCardStackAdapter;

/**
 * Created by dgomez on 2/11/16.
 */

public class QuestionFragment extends Fragment {

    private QuestionView listener;

    private String question;

    public void addListener(QuestionView view) {
        this.listener = view;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        CardContainer mCardContainer = (CardContainer) view.findViewById(R.id.layoutview);
        mCardContainer.setOrientation(Orientations.Orientation.Ordered);
        CardModel card = new CardModel("Title1", question, getContext().getDrawable(R.mipmap.ic_launcher));
        card.setOnCardDismissedListener(new CardModel.OnCardDismissedListener() {
            @Override
            public void onLike() {
                listener.onQuestionAnswered(true);
            }

            @Override
            public void onDislike() {
                listener.onQuestionAnswered(false);
            }
        });
        SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(getContext());
        adapter.add(card);
        mCardContainer.setAdapter(adapter);
        return view;
    }

}
