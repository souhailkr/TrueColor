package com.gen.souhaikr.daltons.utils;

import android.provider.BaseColumns;

/**
 * Created by SouhaiKr on 25/02/2019.
 */

public class QuizContract {

    public static class QEntry implements BaseColumns {
        public static final String TABLE_QUEST = "quest";
        // tasks Table Columns names
        public static final String KEY_ID = "id";
        public static final String KEY_ANSWER = "answer"; //correct option
        public static final String KEY_OPTA= "opta"; //option a
        public static final String KEY_OPTB= "optb"; //option b
        public static final String KEY_OPTC= "optc"; //option c
        public static final String KEY_OPTD= "optd"; //option c

    }
}
