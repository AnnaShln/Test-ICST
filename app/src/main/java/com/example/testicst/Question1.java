package com.example.testicst;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    CheckBox mFirstAns;
    CheckBox mSecondAns;
    CheckBox mThirdAns;
    ImageButton mPreviousQue;
    int questionNumber; //номер вопроса
    QuestionsDbHelper dbHelper;
    ArrayList<String> allQuestions;
    int valueQuestions; //Количество вопросов
    boolean isPreviousQue = false; //Нужно для нажатии кнопки "Назад"

    /**
     * В каждой ячейке - массив (например [1, 2, 3]) - номера групп, которым нужно добавить балл
     */
    ArrayList<Integer[]> userAnswers = new ArrayList<>();

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
        mPreviousQue = findViewById(R.id.back_quest);
        //получаем ссылку на базу данных
        dbHelper = new QuestionsDbHelper(this);
        //получаем все вопросы, содержащихся в БД
        allQuestions = dbHelper.getAllQuestions();
        questionNumber = 1; //Начинаем с первого вопроса
        valueQuestions = allQuestions.size();
    }

    /**
     * При Нажатии идёт заполнение layout следующим вопросом, если он есть
     */
    public void nextQuest (View v) {

        if (checkButtons() || isPreviousQue) {

            if (!isPreviousQue) checkAnswer(v);
            else isPreviousQue = false;

            if (questionNumber < valueQuestions) {

                questionNumber++; //Следующий вопрос
                if (questionNumber > 1) mPreviousQue.setVisibility(View.VISIBLE);
                else mPreviousQue.setVisibility(View.INVISIBLE);
                ArrayList<String> answers = dbHelper.getAnswersOnQue(questionNumber); //Все ответы на вопрос
                //Счётчик в xml, тут что-то подчёркивается, если знаете как исправить, исправьте плз
                mCounter.setText(Integer.toString(questionNumber) + "/" + Integer.toString(valueQuestions));
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
                Intent intent = new Intent(this, MainActivity.class);

                //отправляем массив баллов
                intent.putExtra("POINTS", getPointFromArray());

                startActivity(intent);

                finish();
            }
        }
    }

    private void checkAnswer(View v) {
        ArrayList<String> answers = dbHelper.getAnswersOnQue(questionNumber);

        if (mFirstAns.isChecked()) {
            Integer[] points = dbHelper.getPointsForAns(questionNumber, answers.get(0));
            userAnswers.add(points);
        }

        if (mSecondAns.isChecked()) {
            Integer[] points = dbHelper.getPointsForAns(questionNumber, answers.get(1));
            userAnswers.add(points);
        }

        if (mThirdAns.isChecked()) {
            Integer[] points = dbHelper.getPointsForAns(questionNumber, answers.get(2));
            userAnswers.add(points);
        }

        allUnCheck();
    }

    /**
     * Получаем массив int[8], индекс - номер группы, его значение - баллы у группы
     * Проходимся по userAnswers с помощью foreach
     */
    public int[] getPointFromArray(){
        int[] point = new int[8];
        for (Integer[] i : userAnswers) {
            for (Integer j: i){
                if (point[j] == 0) point[j]=1;
                else point[j]++;
            }
        }
        return point;
    }

    /**
     * Проверяем, выбрал ли пользователь ответ
     */
    private boolean checkButtons(){
        return mFirstAns.isChecked() || mSecondAns.isChecked() || mThirdAns.isChecked();
    }

    /**
     * Снимаем со всех checkBox
     */
    private void allUnCheck(){
        mFirstAns.setChecked(false); mSecondAns.setChecked(false); mThirdAns.setChecked(false);
    }

    /**
     * Запускаем для (questionNumber - 2) метод nextQuest() - получаем предыдущий вопрос
     * Удаляем из массива баллы, которые юзер получил за предыдущий вопрос
     */
    public void backQuest(View view){
        if (questionNumber != 1)
        {
            allUnCheck();
            questionNumber -= 2;
            userAnswers.remove(userAnswers.size() - 1);
            isPreviousQue = true;
            nextQuest(view);
        }
    }

    /**
     * Если номер вопроса не 16, то выбираем только 1 ответ
     */
    public void setOnlyOneAns(View view){
        //Номер вопросов с множественными ответами, если будут больше, чем 1
        //обновлю базу данных, добавив к сущности Question: boolean isMultiplyAns
        if (questionNumber != 16) {
            int k = view.getId();
            switch (k) {
                case R.id.quest1_ans1:
                    mSecondAns.setChecked(false);
                    mThirdAns.setChecked(false);
                    break;
                case R.id.quest1_ans2:
                    mFirstAns.setChecked(false);
                    mThirdAns.setChecked(false);
                    break;
                case R.id.quest1_ans3:
                    mFirstAns.setChecked(false);
                    mSecondAns.setChecked(false);
                    break;
            }
        }
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