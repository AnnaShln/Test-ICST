package com.example.testicst;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    ArrayList<ArrayList<Integer>> rememberAnswers = new ArrayList<>();

    /**
     * В каждой ячейке - массив (например [1, 2, 3]) - номера групп, которым нужно добавить балл
     */

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
        questionNumber = 1;
        valueQuestions = allQuestions.size();
    }

    /**
     * При Нажатии идёт заполнение layout следующим вопросом, если он есть
     */
    public void nextQuest (View v) {

        if (checkButtons() || isPreviousQue) {

            if (!isPreviousQue) checkAnswer();
            else isPreviousQue = false;

            if (questionNumber < valueQuestions) {
                questionNumber++; //Следующий вопрос

                checkRememberAns();
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
                    startAnimation(true);
                    mThirdAns.setText(answers.get(2));
                    mThirdAns.setVisibility(View.VISIBLE);
                } else {
                    mThirdAns.setVisibility(View.GONE);
                    startAnimation(false);
                }

            } else {
                Intent intent = new Intent(this, MainActivity.class);

                intent.putExtra(MainActivity.SAVED_RESULTS, getIdDirection(getPointFromArray()));

                startActivity(intent);
                finish();
            }
        }
    }

    private void checkAnswer() {
        ArrayList<Integer> answers = new ArrayList<>();

        if (mFirstAns.isChecked()) {
            answers.add(0);
        }

        if (mSecondAns.isChecked()) {
            answers.add(1);
        }

        if (mThirdAns.isChecked()) {
            answers.add(2);
        }

        if (rememberAnswers.size() >= questionNumber){
            rememberAnswers.remove(questionNumber - 1);
        }

        rememberAnswers.add(questionNumber - 1, answers);

        allUnCheck();
    }

    /**
     * Получаем массив int[8], индекс - номер группы, его значение - баллы у группы
     * Проходимся по userAnswers с помощью foreach
     */
    public int[] getPointFromArray(){

        ArrayList<Integer[]> userAnswers = new ArrayList<>();
        int queNumber = 1;

        for (ArrayList<Integer> i : rememberAnswers){
            ArrayList<String> answers = dbHelper.getAnswersOnQue(queNumber);
            for (int j : i){
                Integer[] points = dbHelper.getPointsForAns(queNumber, answers.get(j));
                userAnswers.add(points);
            }
            queNumber++;
        }

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
            if (checkButtons()) checkAnswer();
            allUnCheck();
            isPreviousQue = true;
            questionNumber -= 2;
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

    private int getIdDirection(int[] points) {
        int max = points[0];
        int maxIndex = 0;
        //Находим группу с максимальным баллом (из равных выбирается первый)
        for (int i = 1; i < points.length; i++){
            if (points[i] > max) {
                max = points[i];
                maxIndex = i;
            }
        }
        return (maxIndex + 1);
    }

    public void setAllAnswers()
    {

    }

    public void startAnimation(boolean thirdOn){
        Animation animation = AnimationUtils.loadAnimation(this,
                R.anim.animation);
        mFirstAns.startAnimation(animation);
        mSecondAns.startAnimation(animation);
        if (thirdOn) mThirdAns.startAnimation(animation);
    }

    public void checkRememberAns()
    {
        if (rememberAnswers.size() >= questionNumber)
        {
            for (int j: rememberAnswers.get(questionNumber - 1)) {
                switch (j) {
                    case 0:
                        mFirstAns.setChecked(true);
                        break;
                    case 1:
                        mSecondAns.setChecked(true);
                        break;
                    case 2:
                        mThirdAns.setChecked(true);
                        break;
                }
            }
        }
    }

}