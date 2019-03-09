package com.ron.cranajit.quizzler;

public class TrueFalse {
    private int mQuestionId;
    private boolean mAnswer;

    public TrueFalse(int mQuestion, boolean mAnswer){
        this.mQuestionId = mQuestion;
        this.mAnswer = mAnswer;
    }

    public int getQuestionId() {
        return mQuestionId;
    }

    public void setQuestionId(int questionId) {
        mQuestionId = questionId;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}
