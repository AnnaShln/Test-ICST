package com.example.testicst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Question1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_quest1);

    }

    public void nextQuest (View v) {

        ImageButton next = findViewById(R.id.next_quest);
        TextView counter = findViewById(R.id.counter);
        TextView question = findViewById(R.id.quest1);
        Button firstAns = findViewById(R.id.quest1_ans1);
        Button secondAns = findViewById(R.id.quest1_ans2);
        Button thirdAns = findViewById(R.id.quest1_ans3);

        int c = getNumber(counter);                  //достаю номер вопроса

        if (c < 20) {                                //если номер вопроса меньше 20 формирую его, если нет возвращаюсь на тестовую страничку

            String s = getStringResourceByName(createCounter(c + 1));
            String p = getStringResourceByName(createQuestion(c + 1));
            counter.setText(s);
            question.setText(p);

            if (c == 4 || c == 6 || c == 8 || c == 14 || c == 18) {                    //это вопросы в которых три варианта ответа
                String ans1 = getStringResourceByName(createAns1(c + 1));
                String ans2 = getStringResourceByName(createAns2(c + 1));
                String ans3 = getStringResourceByName(createAns3(c + 1));
                firstAns.setText(ans1);
                secondAns.setText(ans2);
                thirdAns.setText(ans3);
                thirdAns.setVisibility(View.VISIBLE);                                  //поэтому третий вариант виден
            } else {
                String ans1 = getStringResourceByName(createAns1(c + 1));
                String ans2 = getStringResourceByName(createAns2(c + 1));
                firstAns.setText(ans1);
                secondAns.setText(ans2);
                thirdAns.setVisibility(View.GONE);                                     //а тут не виден
            }
        } else {
            next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {                                             //возвращаемся на фрагмент теста,
                    Intent intent = new Intent(Question1.this, MainActivity.class);    //я еще не прописала его изменение в зависимости от прохождения теста,
                    startActivity(intent);                                                           //или же создам новый, я пока не решила как лучше
                }
            });
        }
    }

    private String getStringResourceByName(String aString) {          //этот метод чтобы получить стринговое содержание по имени
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return getString(resId);
    }

    private String createCounter (int c) {                  //в этих методах я формирую name нужного мне стринга в зависимости от номера вопроса.
        return "counter" + Integer.toString(c);             // так как они однотипные
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

    private int getNumber (TextView textView){             //для изменения текста везде (счетчик, вопрос, ответы) мне надо было получить номер
        CharSequence charSeq = textView.getText();         //вопроса, я беру его из счетчика, и здесь короче читаю ео первые символы как раз для этого
        String str = charSeq.toString();
        int numb1 = Character.getNumericValue(str.charAt(0));
        if (str.charAt(1) != '/') {
            int numb2 = Character.getNumericValue(str.charAt(1));
            return numb1 * 10 + numb2;
        } else return numb1;
    }
}
