package com.example.testicst.DB;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.testicst.MainActivity;
import com.example.testicst.Question1;
import com.example.testicst.R;

import java.util.ArrayList;
import java.util.List;

public class QuestionsDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Questions.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuestionsDbHelper(@NonNull Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * База данных создаётся только при первом запуске приложения
     * Заполняет она внутри MainActivity (смотреть generateDateBase)
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_TABLE = "CREATE TABLE " +
                QuestionsContract.QuestionTable.TABLE_NAME + " (" +
                QuestionsContract.QuestionTable.COLUMN_Question + " TEXT, " +
                QuestionsContract.QuestionTable.COLUMN_idQuestion + " INTEGER PRIMARY KEY, " +
                QuestionsContract.QuestionTable.COLUMN_ans1 + " TEXT, " +
                QuestionsContract.QuestionTable.COLUMN_ans2 + " TEXT, " +
                QuestionsContract.QuestionTable.COLUMN_ans3 + " TEXT, " +
                QuestionsContract.QuestionTable.COLUMN_point1 + " TEXT, " +
                QuestionsContract.QuestionTable.COLUMN_point2 + " TEXT, " +
                QuestionsContract.QuestionTable.COLUMN_point3 + " TEXT" +
                ")";

        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsContract.QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    /**
     * Получить массив всех вопросов в БД
     */
    public ArrayList<String> getAllQuestions() {
        ArrayList<String> temp = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsContract.QuestionTable.TABLE_NAME, null);

        while (c.moveToNext()){
            String que = c.getString(c.getColumnIndex(QuestionsContract.QuestionTable.COLUMN_Question));
            temp.add(que);
        }

        c.close();
        return temp;
    }

    /**
     * Получить все ответы на вопрос по его id
     */
    public ArrayList<String> getAnswersOnQue(int id) {
        ArrayList<String> temp = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsContract.QuestionTable.TABLE_NAME, null);

        while (c.moveToNext()){
            if (c.getInt(c.getColumnIndex(QuestionsContract.QuestionTable.COLUMN_idQuestion)) == id) {
                String ans1 = c.getString(c.getColumnIndex(QuestionsContract.QuestionTable.COLUMN_ans1));
                String ans2 = c.getString(c.getColumnIndex(QuestionsContract.QuestionTable.COLUMN_ans2));
                String ans3 = c.getString(c.getColumnIndex(QuestionsContract.QuestionTable.COLUMN_ans3));
                temp.add(ans1); temp.add(ans2); temp.add(ans3);
            }
        }

        c.close();
        return temp;
    }

    /**
     * Возвращает массив баллов
     * за вопрос по его id и самому вопросу(string)
     */
    public Integer[] getPointsForAns(int id, String ans) {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsContract.QuestionTable.TABLE_NAME, null);

        while (c.moveToNext()){
            if (c.getInt(c.getColumnIndex(QuestionsContract.QuestionTable.COLUMN_idQuestion)) == id) {
                String ans1 = c.getString(c.getColumnIndex(QuestionsContract.QuestionTable.COLUMN_ans1));
                String ans2 = c.getString(c.getColumnIndex(QuestionsContract.QuestionTable.COLUMN_ans2));
                String ans3 = c.getString(c.getColumnIndex(QuestionsContract.QuestionTable.COLUMN_ans3));
                if (ans.equals(ans1))
                    temp = getArrayFromStr(c.getString(c.getColumnIndex(QuestionsContract.QuestionTable.COLUMN_point1)));
                if (ans.equals(ans2))
                    temp = getArrayFromStr(c.getString(c.getColumnIndex(QuestionsContract.QuestionTable.COLUMN_point2)));
                if (ans.equals(ans3))
                    temp = getArrayFromStr(c.getString(c.getColumnIndex(QuestionsContract.QuestionTable.COLUMN_point3)));
            }
        }
        c.close();

        Integer[] arr = new Integer[temp.size()];
        return temp.toArray(arr);
    }

    //Получает из строки Массив чисел ("123" -> [1, 2, 3])
    public ArrayList<Integer> getArrayFromStr(String str){
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i=0; i < str.length(); i++){
            temp.add(Character.getNumericValue(str.charAt(i)));
        }
        return temp;
    }

    /**
     * Добавить вопрос в БД
     */
    public void addQuestion(Question qv) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsContract.QuestionTable.COLUMN_Question, qv.getQuestion());
        cv.put(QuestionsContract.QuestionTable.COLUMN_idQuestion, qv.getId());
        cv.put(QuestionsContract.QuestionTable.COLUMN_ans1, qv.getAns1());
        cv.put(QuestionsContract.QuestionTable.COLUMN_ans2, qv.getAns2());
        cv.put(QuestionsContract.QuestionTable.COLUMN_ans3, qv.getAns3());
        cv.put(QuestionsContract.QuestionTable.COLUMN_point1, qv.getPoint1());
        cv.put(QuestionsContract.QuestionTable.COLUMN_point2, qv.getPoint2());
        cv.put(QuestionsContract.QuestionTable.COLUMN_point3, qv.getPoint3());

        db.insert(QuestionsContract.QuestionTable.TABLE_NAME, null, cv);

        Log.d("MYTEXT", "--------------------------ADD QUESTIONS------------------");
    }

}
