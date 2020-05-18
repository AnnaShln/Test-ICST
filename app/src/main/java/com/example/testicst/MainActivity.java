package com.example.testicst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import com.example.testicst.Catalog.Handbook;
import com.example.testicst.DB.Question;
import com.example.testicst.DB.QuestionsDbHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    final static public  String SAVED_RESULTS =  "idDirection";
    int idDirection;

    public interface OnBackPressedListener {
        public void onBackPressed();
    }

    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        OnBackPressedListener backPressedListener = null;

        for (Fragment fragment: fm.getFragments()) {
            if (fragment instanceof  OnBackPressedListener) {
                backPressedListener = (OnBackPressedListener) fragment;
                break;
            }
        }

        if (backPressedListener != null) {
            backPressedListener.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        int id = intent.getIntExtra(SAVED_RESULTS, -1);
        if (id != -1) {
            SharedPreferences sPref;
            sPref = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor ed = sPref.edit();
            ed.putInt(SAVED_RESULTS, id);
            ed.commit();
            idDirection = id;
        }
        else {
            SharedPreferences sPref;
            sPref = getPreferences(MODE_PRIVATE);
            idDirection = sPref.getInt(SAVED_RESULTS, -1);
        }

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {                 //эта страшная вещь запускает интро один раз на устройстве
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());

                boolean isFirstStart = getPrefs.getBoolean("firstStart", true);

                if (isFirstStart) {
                    //ГЕНЕРАЦИЯ БАЗЫ ДАННЫХ, из самой БД не получалось
                    generateDataBase();

                    final Intent i = new Intent(MainActivity.this, Intro.class);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(i);
                        }
                    });

                    SharedPreferences.Editor e = getPrefs.edit();

                    e.putBoolean("firstStart", false);

                    e.apply();
                }
            }
        });

        t.start();

        Fragment fragment1 = new FragmentTest();
        if (idDirection != -1) {
            fragment1 = new FragmentTestResult();
        }

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                fragment1).commit();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_test:
                            if (idDirection != -1) {
                                selectedFragment = new FragmentTestResult();
                            }
                            else
                                selectedFragment = new FragmentTest();
                            break;
                        case R.id.nav_book:
                            selectedFragment = new Handbook();
                            break;
                        case R.id.nav_about:
                            selectedFragment = new FragmentInfo();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };
    /**
     * Заполнение БД вопросами
     * Т.к. вы все вопросы писали в R.string, а брать их можно только в классе
     * где существует Context(как я понял), то заполняется БД тут
     */
    void generateDataBase(){

        QuestionsDbHelper dbHelper = new QuestionsDbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        dbHelper.addQuestion(new Question(getString(R.string.quest1), 1,
                getString(R.string.quest1ans1), getString(R.string.quest1ans2),
                getString(R.string.quest1ans3), "6", "4", "1"));
        dbHelper.addQuestion(new Question(getString(R.string.quest2), 2,
                getString(R.string.quest2ans1), getString(R.string.quest2ans2),
                "", "1", "0", ""));
        dbHelper.addQuestion(new Question(getString(R.string.quest3), 3,
                getString(R.string.quest3ans1), getString(R.string.quest3ans2),
                "", "7", "1", ""));
        dbHelper.addQuestion(new Question(getString(R.string.quest4), 4,
                getString(R.string.quest4ans1), getString(R.string.quest4ans2),
                "", "6", "251", ""));
        dbHelper.addQuestion(new Question(getString(R.string.quest5), 5,
                getString(R.string.quest5ans1), getString(R.string.quest5ans2),
                getString(R.string.quest5ans3), "27", "30", "6"));
        dbHelper.addQuestion(new Question(getString(R.string.quest6), 6,
                getString(R.string.quest6ans1), getString(R.string.quest6ans2),
                "", "3", "7", ""));
        dbHelper.addQuestion(new Question(getString(R.string.quest7), 7,
                getString(R.string.quest7ans1), getString(R.string.quest7ans2),
                getString(R.string.quest7ans3), "47", "056", "123"));
        dbHelper.addQuestion(new Question(getString(R.string.quest8), 8,
                getString(R.string.quest8ans1), getString(R.string.quest8ans2),
                "", "6", "7", ""));
        dbHelper.addQuestion(new Question(getString(R.string.quest9), 9,
                getString(R.string.quest9ans1), getString(R.string.quest9ans2),
                getString(R.string.quest9ans3), "0", "32", "6"));
        dbHelper.addQuestion(new Question(getString(R.string.quest10), 10,
                getString(R.string.quest10ans1), getString(R.string.quest10ans2),
                "", "306", "27", ""));
        dbHelper.addQuestion(new Question(getString(R.string.quest11), 11,
                getString(R.string.quest11ans1), getString(R.string.quest11ans2),
                "", "347", "56", ""));
        dbHelper.addQuestion(new Question(getString(R.string.quest12), 12,
                getString(R.string.quest12ans1), getString(R.string.quest12ans2),
                "", "37", "6", ""));
        dbHelper.addQuestion(new Question(getString(R.string.quest13), 13,
                getString(R.string.quest13ans1), getString(R.string.quest13ans2),
                "", "420", "167", ""));
        dbHelper.addQuestion(new Question(getString(R.string.quest14), 14,
                getString(R.string.quest14ans1), getString(R.string.quest14ans2),
                "", "02", "35", ""));
        dbHelper.addQuestion(new Question(getString(R.string.quest15), 15,
                getString(R.string.quest15ans1), getString(R.string.quest15ans2),
                getString(R.string.quest15ans3), "1", "0", "4"));
        dbHelper.addQuestion(new Question(getString(R.string.quest16), 16,
                getString(R.string.quest16ans1), getString(R.string.quest16ans2),
                "", "0", "6", ""));
        dbHelper.addQuestion(new Question(getString(R.string.quest17), 17,
                getString(R.string.quest17ans1), getString(R.string.quest17ans2),
                "", "1530", "764", ""));
        dbHelper.addQuestion(new Question(getString(R.string.quest18), 18,
                getString(R.string.quest18ans1), getString(R.string.quest18ans2),
                "", "150", "374", ""));
        dbHelper.addQuestion(new Question(getString(R.string.quest19), 19,
                getString(R.string.quest19ans1), getString(R.string.quest19ans2),
                getString(R.string.quest19ans3), "14", "035", "62"));
        dbHelper.addQuestion(new Question(getString(R.string.quest20), 20,
                getString(R.string.quest20ans1), getString(R.string.quest20ans2),
                "", "4", "3", ""));
        dbHelper.addQuestion(new Question(getString(R.string.quest21), 21,
                getString(R.string.quest21ans1), getString(R.string.quest21ans2),
                "", "146", "37", ""));
    }
}