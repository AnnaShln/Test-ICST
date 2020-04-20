package com.example.testicst;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testicst.DB.QuestionsDbHelper;

import java.util.ArrayList;
import java.util.Arrays;

public class Question1 extends AppCompatActivity {

    ImageButton mNext;
    TextView mCounter;
    TextView mQuestion;
    RadioButton mFirstAns;
    RadioButton mSecondAns;
    RadioButton mThirdAns;
    int questionNumber; //номер вопроса
    QuestionsDbHelper dbHelper;
    ArrayList<String> allQuestions;
    RadioGroup mRadioGroup;

    Integer[] pointsGroup = new Integer[8];  //Баллы у каждой группы

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_quest1);

        mNext = findViewById(R.id.next_quest);
        mCounter = findViewById(R.id.counter);
        mQuestion = findViewById(R.id.quest1);
        mFirstAns = findViewById(R.id.quest1_ans1);
        mSecondAns = findViewById(R.id.quest1_ans2);
        mThirdAns = findViewById(R.id.quest1_ans3);
        mRadioGroup = findViewById(R.id.answerOptions);
        //получаем ссылку на базу данных
        dbHelper = new QuestionsDbHelper(this);
        //получаем все вопросы, содержащихся в БД
        allQuestions = dbHelper.getAllQuestions();
        questionNumber = 1; //Начинаем с первого вопроса
    }

    /**
     * При Нажатии идёт заполнение layout следующим вопросом, если он есть
     */
    public void nextQuest (View v) {

        if (checkButtons()) {

            checkAnswer(v);

            if (questionNumber < 20) {

                questionNumber++; //Следующий вопрос
                ArrayList<String> answers = dbHelper.getAnswersOnQue(questionNumber); //Все ответы на вопрос
                //Счётчик в xml, тут что-то подчёркивается, если знаете как исправить, исправьте плз
                mCounter.setText(Integer.toString(questionNumber) + getString(R.string.counterW));
                //Выбираем из массива некс вопрос(нумерация в массиве идёт с 0)
                mQuestion.setText(allQuestions.get(questionNumber - 1));
                mFirstAns.setText(answers.get(0));
                mSecondAns.setText(answers.get(1));
                //В БД если 3-его вопроса нет, то его строка равна ""
                if (!answers.get(2).equals("")) {
                    mThirdAns.setText(answers.get(2));
                    mThirdAns.setVisibility(View.VISIBLE);
                } else mThirdAns.setVisibility(View.GONE);

            } else {
                Intent intent = new Intent(this, TestResults.class);
                //При отправки Integer[] писало ошибку, т.к. при заполнении
                //некоторые группы не получали баллов и оставались null
                int[] result = new int[8];
                for (int i = 0; i < 8; i++) {
                    if (pointsGroup[i] == null) result[i] = 0;
                    else result[i] = pointsGroup[i];
                }
                //отправляем массив баллов
                intent.putExtra("POINTS", result);

                startActivity(intent);

                finish();
            }
        }
    }

    /**
     * Проверяет выбор юзера и считает баллы
     */
    private void checkAnswer(View v) {
        ArrayList<String> answers = dbHelper.getAnswersOnQue(questionNumber);

        if (mFirstAns.isChecked()) {
            Integer[] points = dbHelper.getPointsForAns(questionNumber, answers.get(0));
            countPoint(points);
        }

        else if (mSecondAns.isChecked()) {
            Integer[] points = dbHelper.getPointsForAns(questionNumber, answers.get(1));
            mSecondAns.setChecked(false);
            countPoint(points);
        }

        else if (mThirdAns.isChecked()) {
            Integer[] points = dbHelper.getPointsForAns(questionNumber, answers.get(2));
            mThirdAns.setChecked(false);
            countPoint(points);
        }
        mRadioGroup.clearCheck();
    }
    /**
     * Добавляет определённым ячейкам pointGroup 1 балл
     */
    private void countPoint(Integer[] points) {
        for (int i = 0; i < points.length; i++){
            if (pointsGroup[points[i]] == null) pointsGroup[points[i]] = 1;
            else pointsGroup[points[i]]++;
        }
    }

    /**
     * Проверяем, выбрал ли пользователь ответ
     */
    private boolean checkButtons(){
        return mFirstAns.isChecked() || mSecondAns.isChecked() || mThirdAns.isChecked();
    }


    /*      Оставлю просто так
    //этот метод чтобы получить стринговое содержание по имени
    public String getStringResourceByName(String aString) {
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return getString(resId);
    }
    //в этих методах я формирую name нужного мне стринга в зависимости от номера вопроса.
    private String createCounter (int c) {
        // так как они однотипные
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

    /** для изменения текста везде (счетчик, вопрос, ответы) мне надо было получить номер

    private int getNumber (TextView textView){
        //вопроса, я беру его из счетчика, и здесь короче читаю ео первые символы как раз для этого
        CharSequence charSeq = textView.getText();
        String str = charSeq.toString();
        int numb1 = Character.getNumericValue(str.charAt(0));
        if (str.charAt(1) != '/') {
            int numb2 = Character.getNumericValue(str.charAt(1));
            return numb1 * 10 + numb2;
        } else return numb1;
    } */
}
