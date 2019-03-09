package com.ron.cranajit.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button mTrueButton;
    Button mFalseButton;
    TextView mQuestionView;
    TextView mScoreView;
    ProgressBar mProgressBar;
    int mIndex;
    int mQuestion;
    int mScore;

    private TrueFalse[] questionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13, true)
    };

    final int PROGRESS_BAR_INCREMENT = (int)Math.ceil(100.0/questionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionView = (TextView) findViewById(R.id.question_text_view);
        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mScoreView = (TextView) findViewById(R.id.score);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);


        mQuestion = questionBank[mIndex].getQuestionId();
        mQuestionView.setText(mQuestion);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("quizzler", "true button clicked");
                checkAnswer(true);
                updateQuestion();
            }
        });

        View.OnClickListener myFalseListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("quizzler", "false button clicked");
                checkAnswer(false);
                updateQuestion();
            }
        };

        mFalseButton.setOnClickListener(myFalseListener);



    }

    private void updateQuestion() {
        mIndex = (mIndex + 1) % questionBank.length;
        if(mIndex == 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(R.string.over);
            alert.setCancelable(false);
            alert.setMessage("Your score is " + mScore + " points");
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();

        }
        mQuestion = questionBank[mIndex].getQuestionId();
        mQuestionView.setText(mQuestion);
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        mScoreView.setText("Score "+ mScore + "/" + questionBank.length);
    }

    private void checkAnswer(boolean userSelect) {
        boolean correctAnswer = questionBank[mIndex].isAnswer();
        if (correctAnswer == userSelect) {
            mScore = mScore + 1;
            Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT).show();

        }
    }
}
