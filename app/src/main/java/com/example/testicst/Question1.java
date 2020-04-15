package com.example.testicst;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Question1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_quest1);

    }

    public void nextQuest (View v) {

        ImageButton customButton = findViewById(R.id.next_quest);
        TextView counter = findViewById(R.id.counter);
        TextView question = findViewById(R.id.quest1);
        Button firstAns = findViewById(R.id.quest1_ans1);
        Button secondAns = findViewById(R.id.quest1_ans2);
        Button thirdAns = findViewById(R.id.quest1_ans3);

        int c = getNumber(counter) + 1;

        String s = getStringResourceByName(createCounter(c));
        String p = getStringResourceByName(createQuestion(c));
        counter.setText(s);
        question.setText(p);

        if (c == 5 || c == 7 || c == 9 || c == 15 || c == 19) {
            String ans1 = getStringResourceByName(createAns1(c));
            String ans2 = getStringResourceByName(createAns2(c));
            String ans3 = getStringResourceByName(createAns3(c));
            firstAns.setText(ans1);
            secondAns.setText(ans2);
            thirdAns.setText(ans3);
            thirdAns.setVisibility(View.VISIBLE);
        } else {
            String ans1 = getStringResourceByName(createAns1(c));
            String ans2 = getStringResourceByName(createAns2(c));
            firstAns.setText(ans1);
            secondAns.setText(ans2);
            thirdAns.setVisibility(View.GONE);
        }

    }

    private String getStringResourceByName(String aString) {
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return getString(resId);
    }

    private String createCounter (int c) {
        return "counter" + Integer.toString(c);
    }

    private String createQuestion (int c) {
        return "quest" + Integer.toString(c);
    }

    private String createAns1 (int c) {
        return "quest" + Integer.toString(c) + "ans1";
    }

    private String createAns2 (int c) {
        return "quest" + Integer.toString(c) + "ans2";
    }

    private String createAns3 (int c) {
        return "quest" + Integer.toString(c) + "ans3";
    }

    private int getNumber (TextView textView){
        CharSequence charSeq = textView.getText();
        String str = charSeq.toString();
        int numb1 = Character.getNumericValue(str.charAt(0));
        if (str.charAt(1) != '/') {
            int numb2 = Character.getNumericValue(str.charAt(1));
            return numb1 * 10 + numb2;
        } else return numb1;
    }
}
