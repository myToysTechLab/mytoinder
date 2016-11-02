package de.mytoys.mobile.mytoinder;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import de.mytoys.mobile.mytoinder.model.Questions;
import de.mytoys.mobile.mytoinder.model.Type;

public class QuestionActivity extends AppCompatActivity implements QuestionView {

    private FragmentManager fragmentManager;

    private List<Type> types;
    private List<Type> colors;
    private List<Type> styles;

    private Type currentQuestion;

    private static final String BASE_URL = "http://www.ambellis.de/bekleidung";

    private static final String BASE_IMAGES_URL = "https://dl.dropboxusercontent.com/u/25301739/img/";

    private String url = BASE_URL;

    private final int COLOR_NODE = 1;

    private final int STYLE_NODE = 2;

    private final int TYPE_NODE = 0;

    private int currentNode = TYPE_NODE;
    private int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        String json = getJsonFromFile();
        Questions questions = new Gson().fromJson(json, Questions.class);

        types = questions.getType();
        colors = questions.getColors();
        styles = questions.getStyle();

        currentQuestion = types.get(0);

        fragmentManager = getSupportFragmentManager();
        QuestionFragment fragment = new QuestionFragment();
        fragment.addListener(this);
        fragment.setQuestion(currentQuestion.getQuestion());
        fragment.setQuestionImage(BASE_IMAGES_URL + currentQuestion.getQuestionImage());
        fragmentManager.beginTransaction().replace(R.id.fragments_container, fragment).commit();

    }

    @Override
    public void onQuestionAnswered(final boolean answer) {

        if (answer) {

            url = url + "/" + currentQuestion.getYesFilter();

            switch (currentNode) {

                case COLOR_NODE:
                    currentQuestion = styles.get(0);
                    currentPosition = 0;
                    currentNode = STYLE_NODE;
                    openNewQuestionFragment(currentQuestion.getQuestion(), currentQuestion.getQuestionImage());
                    break;
                case STYLE_NODE:
                    openWeb();
                    break;
                case TYPE_NODE:
                    currentQuestion = colors.get(0);
                    currentPosition = 0;
                    currentNode = COLOR_NODE;
                    openNewQuestionFragment(currentQuestion.getQuestion(), currentQuestion.getQuestionImage());
                    break;

            }

        } else {

            switch (currentNode) {
                case COLOR_NODE:

                    if (colors.size() == 1 && !TextUtils.isEmpty(currentQuestion.getNoFilter())) {
                        url = url + "/" + currentQuestion.getNoFilter();
                    }

                    if (currentPosition == colors.size() - 1) {

                        currentQuestion = styles.get(0);
                        currentPosition = 0;
                        currentNode = STYLE_NODE;
                    }
                    openNewQuestionFragment(currentQuestion.getQuestion(), currentQuestion.getQuestionImage());

                    break;
                case STYLE_NODE:

                    if (styles.size() == 1 && !TextUtils.isEmpty(currentQuestion.getNoFilter())) {
                        url = url + "/" + currentQuestion.getNoFilter();
                    }

                    if (currentPosition == styles.size() - 1) {

                        openWeb();

                    } else {
                        currentPosition = currentPosition + 1;
                        currentQuestion = styles.get(currentPosition);
                        openNewQuestionFragment(currentQuestion.getQuestion(), currentQuestion.getQuestionImage());
                    }

                    break;
                case TYPE_NODE:

                    if (colors.size() == 1 && !TextUtils.isEmpty(currentQuestion.getNoFilter())) {
                        url = url + "/" + currentQuestion.getNoFilter();
                    }

                    if (currentPosition == types.size() - 1) {

                        currentQuestion = colors.get(0);
                        currentPosition = 0;
                        currentNode = COLOR_NODE;
                    } else {
                        currentPosition = currentPosition + 1;
                        currentQuestion = types.get(currentPosition);
                    }
                    openNewQuestionFragment(currentQuestion.getQuestion(), currentQuestion.getQuestionImage());
                    break;

            }

        }


    }

    private void openWeb() {
        Intent intent = new Intent(QuestionActivity.this, WebViewActivity.class);
        intent.putExtra(WebViewActivity.URL, url);
        startActivity(intent);
        finish();
    }

    private void openNewQuestionFragment(final String question, final String imageUrl) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                QuestionFragment fragment = new QuestionFragment();
                fragment.addListener(QuestionActivity.this);
                fragment.setQuestion(question);
                fragment.setQuestionImage(BASE_IMAGES_URL + imageUrl);
                fragmentManager.beginTransaction().replace(R.id.fragments_container, fragment).commit();
            }
        }, 1000);

    }


    private String getJsonFromFile() {

        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = getResources().getAssets()
                    .open("questions.json", Context.MODE_WORLD_READABLE);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line = "";
            while ((line = input.readLine()) != null) {
                returnString.append(line);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        return returnString.toString();
    }

    public void click(View view){

        if(view.getId()==R.id.yesBtn){
            onQuestionAnswered(true);
        }
        else if(view.getId()==R.id.noBtn){
            onQuestionAnswered(false);
        }

    }
}
