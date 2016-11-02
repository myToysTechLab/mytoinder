package de.mytoys.mobile.mytoinder;


import android.graphics.Bitmap;
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
import com.bumptech.glide.Glide;

import java.util.concurrent.ExecutionException;

/**
 * Created by dgomez on 2/11/16.
 */

public class QuestionFragment extends Fragment {

    private QuestionView listener;

    private String question;

    private String questionImage;

    public void addListener(QuestionView view) {
        this.listener = view;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setQuestionImage(String questionImage) {
        this.questionImage = questionImage;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        final CardContainer mCardContainer = (CardContainer) view.findViewById(R.id.layoutview);
        mCardContainer.setOrientation(Orientations.Orientation.Ordered);
        Log.d("TAG", "URL IMAGE: "+ questionImage);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap theBitmap = null;
                try {
                    theBitmap = Glide.
                            with(getActivity()).
                            load(questionImage).
                            asBitmap().
                            into(-1, -1).
                            get();

                    final Bitmap finalTheBitmap = theBitmap;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            CardModel card = new CardModel("Title1", question, finalTheBitmap);
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
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return view;
    }

}
