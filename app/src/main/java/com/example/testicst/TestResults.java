package com.example.testicst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.testicst.DB.Question;

import java.util.ArrayList;

public class TestResults extends AppCompatActivity {

    TextView mGroup;
    int[] points;   //Баллы

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_results);

        mGroup = findViewById(R.id.textGroup);

        Intent intent = getIntent();
        //Получаем баллы с теста
        points = intent.getIntArrayExtra("POINTS");

        String recommendedGroup = getGroupFromPoints(points);

        mGroup.setText(recommendedGroup);
    }

    /**
     * Возвращает названия группы, которую мы рекомендуем
     */
    public String getGroupFromPoints(int[] points) {
        int max = points[0];
        int maxIndex = 0;
        //Находим группу с максимальным баллом (из равных выбирается первый)
        for (int i = 1; i < points.length; i++){
            if (points[i] > max) {
                max = points[i];
                maxIndex = i;
            }
        }

        return getStringResourceByName("Group" + Integer.toString(maxIndex + 1));
    }

    public String getStringResourceByName(String aString) {
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return getString(resId);
    }

    /**
     * Возвращаемся в меню
     */
    public void backMenu(View view){
        Intent intent = new Intent(TestResults.this, MainActivity.class);
        finish();
        startActivity(intent);
        finish();
    }
    /**
     * Переходим опять в тест
     */
    public void testAgain(View view) {
        Intent intent = new Intent(TestResults.this, Question1.class);
        finish();
        startActivity(intent);
        finish();
    }
}

/**
 * Если мы будем делать так, чтобы выводилось несколько
 * рекоммендованных групп
        /*ArrayList<String> groups = new ArrayList<>();
        int max = points[0];
        ArrayList<Integer> maxIndex = new ArrayList<>();
        for (int i = 1; i < points.length; i++){
            if (points[i] > max) {
                max = points[i];
                maxIndex.clear();
                maxIndex.add(i);
            }
            if (points[i] == max) {
                maxIndex.add(i);
            }
        }

        for (int i = 0; i < maxIndex.size(); i++) {
            switch (maxIndex.get(i)) {
                case 0:
                    groups.add(getString(R.string.Group1));
                    и т.д.
            }
        }
 */