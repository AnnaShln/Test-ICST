package com.example.testicst.DB;

import android.provider.BaseColumns;
//Здесь названия столбцов (атрибутов)
public class QuestionsContract {

    private QuestionsContract() {}

    public static class QuestionTable implements BaseColumns {
        public static final String TABLE_NAME = "Questions";
        public static final String COLUMN_Question = "question";
        public static final String COLUMN_idQuestion = "id";
        public static final String COLUMN_ans1 = "answer1";
        public static final String COLUMN_ans2 = "answer2";
        public static final String COLUMN_ans3 = "answer3";
        public static final String COLUMN_point1 = "point1";
        public static final String COLUMN_point2 = "point2";
        public static final String COLUMN_point3 = "point3";
    }

}
